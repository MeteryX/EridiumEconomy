package at.mtxframe.eridiumeconomy.listeners;

import at.mtxframe.eridiumeconomy.EridiumEconomy;
import at.mtxframe.eridiumeconomy.backpack.BackPackUtils;
import at.mtxframe.mtxframe.MtxFrame;
import at.mtxframe.mtxframe.database.DatabaseJobs;
import at.mtxframe.mtxframe.models.PlayerJobStatModel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.sql.SQLException;
import java.util.HashMap;

//Diese Klasse ist dazu da um:
//Das JobStatsModel eines Spielers aus der Datenbank zu laden und Lokal speichern bis der Spieler den Server wieder verlässt
public class JoinQuitListener implements Listener {

    EridiumEconomy plugin;
    MtxFrame frame = MtxFrame.getPlugin();
    HashMap<Player, PlayerJobStatModel> localJobStats = new HashMap<>();
    DatabaseJobs dbJobs = new DatabaseJobs();
    BackPackUtils backPackUtils = new BackPackUtils();
    public JoinQuitListener(EridiumEconomy plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public  void onPlayerJoin(PlayerJoinEvent event) throws SQLException {
        Player player = event.getPlayer();
        localJobStats = plugin.getLocalJobStats();
        if(dbJobs.findJobsDataByUUID(String.valueOf(player.getUniqueId())) != null) {
            PlayerJobStatModel playerStats = dbJobs.findJobsDataByUUID(String.valueOf(player.getUniqueId()));
            localJobStats.put(player,playerStats);
            plugin.setLocalJobStats(localJobStats);
        } else if (dbJobs.findJobsDataByUUID(String.valueOf(player.getUniqueId())) == null){
            PlayerJobStatModel playerStats = new PlayerJobStatModel(player.getUniqueId().toString(), 1, 0.00, 1, 0.00, 1, 0.00, 1, 0.00, 1, 0.00);
            dbJobs.createPlayerJobStats(playerStats, playerStats.getUuid());
            localJobStats.put(player,playerStats);
            plugin.setLocalJobStats(localJobStats);
        }
        PersistentDataContainer psd = player.getPersistentDataContainer();
        if (!(psd.has(BackPackUtils.backPackKey,PersistentDataType.STRING))){
            psd.set(BackPackUtils.backPackKey,PersistentDataType.STRING,BackPackUtils.getnoActiveBackPack());
        }


    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) throws SQLException {
        Player player = event.getPlayer();
        localJobStats = plugin.getLocalJobStats();
        PlayerJobStatModel playerStats = localJobStats.get(player);
        dbJobs.updatePlayerJobStats(playerStats);
        localJobStats.remove(player);
        plugin.setLocalJobStats(localJobStats);

    }


}
