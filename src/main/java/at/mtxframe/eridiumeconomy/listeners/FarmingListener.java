package at.mtxframe.eridiumeconomy.listeners;

import at.mtxframe.eridiumeconomy.EridiumEconomy;
import at.mtxframe.mtxframe.MtxFrame;
import at.mtxframe.mtxframe.database.DatabaseJobs;
import at.mtxframe.mtxframe.economy.BalanceHandler;
import at.mtxframe.mtxframe.models.PlayerJobStatModel;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class FarmingListener implements Listener {
    EridiumEconomy plugin;
    BalanceHandler handler = new BalanceHandler();

    public FarmingListener(EridiumEconomy plugin, HashMap<Material, Double> blocksFarming) {
        this.plugin = plugin;
        this.farmingBlocks = blocksFarming;

    }
    HashMap<Material, Double> farmingBlocks = new HashMap<>();

    @EventHandler
    public void onBlockbreak(BlockBreakEvent event) throws SQLException {
        Player player = event.getPlayer();
        HashMap<UUID, Long> lastBlockBreak = handler.getLastBlockBreakTime();
        lastBlockBreak.put(player.getUniqueId(),System.currentTimeMillis());
        handler.setLastBlockBreakTime(lastBlockBreak);
        Block block = event.getBlock();
        Material material = block.getType();
        String blockType = block.getType().toString();
        PlayerJobStatModel jobsStats = plugin.getLocalJobStats().get(player);
        if (farmingBlocks.containsKey(material)){
            handler.addMoneyAndEXP(player, farmingBlocks.get(material) * (jobsStats.getFarmerLevel() * 0.7), farmingBlocks.get(material) * (jobsStats.getFarmerLevel() * 0.7), "farming", jobsStats);
            if (material == Material.SUGAR_CANE || material == Material.CACTUS) {
                checkForAbove(block, material, player);
            }
        } //TESTING
        else if (material.equals(Material.SAND)){
            handler.addMoneyPlayer(player,00.25);
        }

        //TESTING
    }




    public void checkForAbove(Block block,Material material,Player player) throws SQLException {
        PlayerJobStatModel jobsStats = plugin.getLocalJobStats().get(player);
        Block blockAbove = block.getRelative(BlockFace.UP);
        Material materialAbove = blockAbove.getType();
        while (materialAbove == material){
            blockAbove = blockAbove.getRelative(BlockFace.UP);
            materialAbove = blockAbove.getType();
            handler.addMoneyAndEXP(player, farmingBlocks.get(material) + (farmingBlocks.get(material) *  jobsStats.getFarmerLevel() * 0.3), farmingBlocks.get(material) * (jobsStats.getFarmerLevel() * 0.7), "farming", jobsStats);
        }

    }


}
