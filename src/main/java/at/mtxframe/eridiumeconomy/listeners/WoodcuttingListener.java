package at.mtxframe.eridiumeconomy.listeners;

import at.mtxframe.eridiumeconomy.EridiumEconomy;
import at.mtxframe.eridiumeconomy.utils.ErzAderDetector;
import at.mtxframe.eridiumeconomy.utils.TreeDetector;
import at.mtxframe.mtxframe.database.DatabaseJobs;
import at.mtxframe.mtxframe.economy.BalanceHandler;
import at.mtxframe.mtxframe.messaging.MessageHandler;
import at.mtxframe.mtxframe.models.PlayerJobStatModel;
import at.mtxframe.mtxframe.utilitys.JobsLevelHandler;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class WoodcuttingListener implements Listener {
    EridiumEconomy plugin;
    BalanceHandler handler = new BalanceHandler();
    HashMap<Material, Double> woodcuttingBlocks = new HashMap<>();
    JobsLevelHandler jobHandler = new JobsLevelHandler();
    MessageHandler msgHandler= new MessageHandler();

    public static HashMap<UUID, Long> lastWoodAbilityUsed = new HashMap<>();
    public HashMap<Integer, Long> coolDownLM = jobHandler.getMinerAbilityCooldown();
    public  HashMap<Integer,Double> dropChances = jobHandler.getDropChances();

    public WoodcuttingListener(EridiumEconomy plugin, HashMap<Material, Double> blocksWoodcutting) {
        this.plugin = plugin;
        this.woodcuttingBlocks = blocksWoodcutting;

    }


    //Testing

    @EventHandler
    public void onBlockbreak(BlockBreakEvent event) throws SQLException {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        Material material = block.getType();
        PlayerJobStatModel jobsStats = plugin.getLocalJobStats().get(player);

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            long currentTime = System.currentTimeMillis();
            if (lastWoodAbilityUsed.containsKey(player.getUniqueId())) {
                if (jobsStats.getMiningLevel() >= 5) {
                    if (currentTime - lastWoodAbilityUsed.get(player.getUniqueId()) >= coolDownLM.get(jobsStats.getWoodcutterLevel())) {
                        lastWoodAbilityUsed.remove(player.getUniqueId());
                        // Sende eine Nachricht an den Spieler, dass die Abklingzeit vorbei ist
                        Player p = Bukkit.getPlayer(player.getUniqueId());
                        if (p != null) {
                            p.playSound(p.getLocation(),Sound.BLOCK_NOTE_BLOCK_GUITAR,0.3F,0.1F);
                            msgHandler.actionBarMessage(p, ChatColor.DARK_GREEN + "[Kettensäge]" + " " + ChatColor.GREEN + "Wieder einsatzbereit.");
                        }
                    }
                }
            }
        }, 0, 20);
        
        
        
        if (woodcuttingBlocks.containsKey(material)) {
            if (jobsStats.getWoodcutterLevel() >= 5 && !lastWoodAbilityUsed.containsKey(player.getUniqueId())) {
                TreeDetector treeDetector = new TreeDetector(block.getWorld());
                Set<Location> erzAder = treeDetector.findOreAder(block);
                handler.addMoneyAndEXP(player, woodcuttingBlocks.get(material) + (woodcuttingBlocks.get(material) * jobsStats.getWoodcutterLevel() * 0.7), woodcuttingBlocks.get(material) * (jobsStats.getWoodcutterLevel() * 0.7), "woodcutting",jobsStats);
                long currentTime = System.currentTimeMillis();
                lastWoodAbilityUsed.put(player.getUniqueId(), currentTime);
                if (jobsStats.getWoodcutterLevel() < 35 && !block.getType().equals(Material.JUNGLE_LOG)) {
                    for (Location woodBlockLocation : erzAder) {
                        Block woodBlock = woodBlockLocation.getBlock();
                        woodBlock.breakNaturally();
                        player.playSound(block.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 0.3F, 0.5F);
                        handler.addMoneyAndEXP(player, woodcuttingBlocks.get(material) + (woodcuttingBlocks.get(material) * jobsStats.getWoodcutterLevel() * 0.3), woodcuttingBlocks.get(material) * (jobsStats.getWoodcutterLevel() * 0.4), "mining",jobsStats);
                        if (isDoubleDrop(jobsStats)) {
                            ItemStack extraDrop = new ItemStack(material, 1);
                            block.getWorld().dropItemNaturally(block.getLocation(), extraDrop);
                        }
                    }
                } else {
                    if (jobsStats.getWoodcutterLevel() >= 35 ){
                        for (Location woodBlockLocation : erzAder) {
                            Block woodBlock = woodBlockLocation.getBlock();
                            woodBlock.breakNaturally();
                            player.playSound(block.getLocation(), Sound.BLOCK_NOTE_BLOCK_XYLOPHONE, 0.3F, 0.5F);
                            handler.addMoneyAndEXP(player, woodcuttingBlocks.get(material) + (woodcuttingBlocks.get(material) * jobsStats.getWoodcutterLevel() * 0.3), woodcuttingBlocks.get(material) * (jobsStats.getWoodcutterLevel() * 0.4), "woodcutting",jobsStats);
                            if (isDoubleDrop(jobsStats)) {
                                ItemStack extraDrop = new ItemStack(material, 1);
                                block.getWorld().dropItemNaturally(block.getLocation(), extraDrop);
                            }
                        }
                    }
                }
            }else {
                if (isDoubleDrop(jobsStats)){
                    ItemStack extraDrop = new ItemStack(material,1);
                    block.getWorld().dropItemNaturally(block.getLocation(),extraDrop);
                }
                handler.addMoneyAndEXP(player, woodcuttingBlocks.get(material) + (woodcuttingBlocks.get(material) * jobsStats.getWoodcutterLevel() * 0.3), woodcuttingBlocks.get(material) * (jobsStats.getWoodcutterLevel() * 0.4), "woodcutting",jobsStats);
            }

        }
        
    }
    
    public boolean isDoubleDrop(PlayerJobStatModel jobStats){
        Random random = new Random();
        double min = 0.0; // Untere Grenze (inklusive)
        double max = 100.0; // Obere Grenze (exklusive)
        double randomDoubleInRange = min + (max - min) * random.nextDouble();
        if (randomDoubleInRange < dropChances.get(jobStats.getWoodcutterLevel())){
            return true;
        } else {
            return false;
        }
    }
    
    
    
    
}