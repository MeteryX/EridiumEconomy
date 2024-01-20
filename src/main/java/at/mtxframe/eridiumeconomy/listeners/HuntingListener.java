package at.mtxframe.eridiumeconomy.listeners;

import at.mtxframe.eridiumeconomy.EridiumEconomy;
import at.mtxframe.mtxframe.database.DatabaseJobs;
import at.mtxframe.mtxframe.economy.BalanceHandler;
import at.mtxframe.mtxframe.models.PlayerJobStatModel;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class HuntingListener implements Listener {

    EridiumEconomy plugin;
    BalanceHandler handler = new BalanceHandler();
    HashMap<EntityType, Double> huntingEntities = new HashMap<>();
    public HuntingListener(EridiumEconomy plugin, HashMap<EntityType, Double> huntingEntities) {
        this.plugin = plugin;
        this.huntingEntities = huntingEntities;


    }
    DatabaseJobs jobsDatabase = new DatabaseJobs();

    @EventHandler
    public void onMobKill(EntityDeathEvent event) throws SQLException {
        EntityType mob = event.getEntity().getType();
        Player player = event.getEntity().getKiller();

        if (player!=null) {
            HashMap<UUID, Long> lastBlockBreak = handler.getLastBlockBreakTime();
            lastBlockBreak.put(player.getUniqueId(),System.currentTimeMillis());
            handler.setLastBlockBreakTime(lastBlockBreak);
            PlayerJobStatModel jobsStats = jobsDatabase.findJobsDataByUUID(String.valueOf(player.getUniqueId()));
            if (huntingEntities.containsKey(mob)) {
                handler.addMoneyAndEXP(player, huntingEntities.get(mob) + (huntingEntities.get(mob) * jobsStats.getHunterLevel() * 0.3), huntingEntities.get(mob) * (jobsStats.getHunterLevel() * 0.7), "hunting",jobsStats );
            }

        }
    }

}
