package at.mtxframe.eridiumeconomy.listeners;

import at.mtxframe.eridiumeconomy.EridiumEconomy;
import at.mtxframe.eridiumeconomy.utils.ErzAderDetector;
import at.mtxframe.mtxframe.database.DatabaseJobs;
import at.mtxframe.mtxframe.economy.BalanceHandler;
import at.mtxframe.mtxframe.messaging.MessageHandler;
import at.mtxframe.mtxframe.models.PlayerJobStatModel;
import at.mtxframe.mtxframe.models.PlayerStatsModel;
import at.mtxframe.mtxframe.utilitys.JobsLevelHandler;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.*;

public class MiningListener implements Listener {
    EridiumEconomy plugin = EridiumEconomy.getPlugin();
    BalanceHandler handler = new BalanceHandler();

    public MiningListener(EridiumEconomy plugin, HashMap<Material, Double> blocksMining) {
        this.plugin = plugin;
        this.miningBlocks = blocksMining;

    }
    HashMap<Material, Double> miningBlocks = new HashMap<>();
    DatabaseJobs jobsDatabase = new DatabaseJobs();
    MessageHandler msgHandler = new MessageHandler();
    //Testing
    JobsLevelHandler jobHandler = new JobsLevelHandler();
    public static HashMap<UUID, Long> lastMineAbilityUsed = new HashMap<>();
    public HashMap<Integer, Long> coolDownLM = jobHandler.getMinerAbilityCooldown();
    public  HashMap<Integer,Double> dropChances = jobHandler.getDropChances();

    @EventHandler
    public void onBlockbreak(BlockBreakEvent event) throws SQLException {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material material = block.getType();
        PlayerJobStatModel jobsStats = plugin.getLocalJobStats().get(player);

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            long currentTime = System.currentTimeMillis();
            if (lastMineAbilityUsed.containsKey(player.getUniqueId())) {
                if (jobsStats.getMiningLevel() >= 5) {
                    if (currentTime - lastMineAbilityUsed.get(player.getUniqueId()) >= coolDownLM.get(jobsStats.getMiningLevel())) {
                        lastMineAbilityUsed.remove(player.getUniqueId());
                        // Sende eine Nachricht an den Spieler, dass die Abklingzeit vorbei ist
                        Player p = Bukkit.getPlayer(player.getUniqueId());
                        if (p != null) {
                            p.playSound(p.getLocation(),Sound.BLOCK_NOTE_BLOCK_GUITAR,0.3F,0.1F);
                            msgHandler.actionBarMessage(p, ChatColor.BLUE + "[Erzschürfer]" + " " + ChatColor.GREEN + "Wieder einsatzbereit.");
                        }
                    }
                }
            }
        }, 0, 20);

        if (miningBlocks.containsKey(material)){
            if (jobsStats.getMiningLevel() >= 5 && !lastMineAbilityUsed.containsKey(player.getUniqueId())) {
                ErzAderDetector erzDetector = new ErzAderDetector(block.getWorld());
                Set<Location> erzAder = erzDetector.findOreAder(block);
                if (!(erzAder.size() < 2) && !(player.isSneaking())) {
                    long currentTime = System.currentTimeMillis();
                    lastMineAbilityUsed.put(player.getUniqueId(), currentTime);
                    for (Location erzBlockLocation : erzAder) {
                        Block erzBlock = erzBlockLocation.getBlock();
                        erzBlock.breakNaturally();
                        player.playSound(block.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 0.3F, 0.5F);
                        handler.addMoneyAndEXP(player, miningBlocks.get(material) + (miningBlocks.get(material) * jobsStats.getMiningLevel() * 0.3), miningBlocks.get(material) * (jobsStats.getMiningLevel() * 0.4), "mining", jobsStats);
                        if (isDoubleDrop(jobsStats)) {
                            ItemStack extraDrop = new ItemStack(material, 1);
                            block.getWorld().dropItemNaturally(block.getLocation(), extraDrop);
                        }

                    }
                }

            } else {
                if (isDoubleDrop(jobsStats)){
                    ItemStack extraDrop = new ItemStack(material,1);
                    block.getWorld().dropItemNaturally(block.getLocation(),extraDrop);
                }
                handler.addMoneyAndEXP(player, miningBlocks.get(material) + (miningBlocks.get(material) * jobsStats.getMiningLevel() * 0.3), miningBlocks.get(material) * (jobsStats.getMiningLevel() * 0.4), "mining",jobsStats );
            }

        }

    }

    public boolean isDoubleDrop(PlayerJobStatModel jobStats){
        Random random = new Random();
        double min = 0.0; // Untere Grenze (inklusive)
        double max = 100.0; // Obere Grenze (exklusive)
        double randomDoubleInRange = min + (max - min) * random.nextDouble();
        if (randomDoubleInRange < dropChances.get(jobStats.getMiningLevel())){
            return true;
        } else {
            return false;
        }
    }



}
