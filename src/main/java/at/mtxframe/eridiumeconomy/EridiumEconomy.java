package at.mtxframe.eridiumeconomy;


import at.mtxframe.eridiumeconomy.backpack.BackPackSelector;
import at.mtxframe.eridiumeconomy.commands.JobsGuiMain;
import at.mtxframe.eridiumeconomy.commands.PayCommand;
import at.mtxframe.eridiumeconomy.listeners.*;
import at.mtxframe.eridiumeconomy.userinterface.*;
import at.mtxframe.eridiumeconomy.utils.MapPopulators;
import at.mtxframe.mtxframe.MtxFrame;
import at.mtxframe.mtxframe.database.DatabaseConnection;
import at.mtxframe.mtxframe.database.handlers.DbJobsHandler;
import at.mtxframe.mtxframe.economy.BalanceHandler;
import at.mtxframe.mtxframe.models.PlayerJobStatModel;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public final class EridiumEconomy extends JavaPlugin {
    private static EridiumEconomy plugin;
    private ArrayList<String> dbC = new ArrayList<>();
    public DatabaseConnection database;
    DbJobsHandler dbJobsHandler = new DbJobsHandler();
    BalanceHandler handler;

    HashMap<Material, Double> blocksMapFarming;
    HashMap<Material, Double> blocksMapMining;
    HashMap<Material, Double> blocksMapWoodcutting;
    HashMap<EntityType, Double> huntingEntities;
    HashMap<Player, PlayerJobStatModel> localJobStats;
    MapPopulators mapPopulators = new MapPopulators();


    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        MtxFrame frame = MtxFrame.getPlugin();
        cLog("EridiumEconomy gestartet.");
        //Getting config and set Data for Database
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        dbC.add(0,getConfig().getString("database.host"));
        dbC.add(1,getConfig().getString("database.port"));
        dbC.add(2,getConfig().getString("database.user"));
        dbC.add(3,getConfig().getString("database.password"));
        dbC.add(4,getConfig().getString("database.database_name"));
        this.database = new DatabaseConnection(dbC.get(0),dbC.get(1),dbC.get(2),dbC.get(3),dbC.get(4));
        try {
            database.initializeDatabase(dbJobsHandler.getCreateJobStatsTable());
            cLog("JobXP Table erstellt.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        //Initialisierung für Maps
        this.handler = new BalanceHandler();
        this.blocksMapFarming = new HashMap<>();
        this.blocksMapMining = new HashMap<>();
        this.blocksMapWoodcutting = new HashMap<>();
        this.huntingEntities = new HashMap<>();
        this.localJobStats = new HashMap<>();

        //BlockMaps
        blocksMapFarming = mapPopulators.getBlocksMapFarming();
        blocksMapMining = mapPopulators.getBlocksMapMining();
        blocksMapWoodcutting = mapPopulators.getBlocksMapWoodcutting();
        huntingEntities = mapPopulators.getHuntingEntities();



        //Listeners
        getServer().getPluginManager().registerEvents(new MiningListener(this,blocksMapMining),this);
        getServer().getPluginManager().registerEvents(new FarmingListener(this,blocksMapFarming), this);
        getServer().getPluginManager().registerEvents(new HuntingListener(this,huntingEntities), this);
        getServer().getPluginManager().registerEvents(new WoodcuttingListener(this,blocksMapWoodcutting),this);
        getServer().getPluginManager().registerEvents(new EconomyGuiListener(this),this);
        getServer().getPluginManager().registerEvents(new JoinQuitListener(this),this);

        getCommand("jobs").setExecutor(new JobsGuiMain(this));
        getCommand("jobminer").setExecutor(new MinerGui(this));
        getCommand("jobfarmer").setExecutor(new FarmerGui(this));
        getCommand("jobhunter").setExecutor(new HunterGui(this));
        getCommand("jobwoodcutter").setExecutor(new WoodcutterGui(this));
        getCommand("jobfisher").setExecutor(new FisherGui(this));
        getCommand("pay").setExecutor(new PayCommand(this));
        getCommand("backpack").setExecutor(new BackPackSelector());

        //EConomy Hook?



    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        cLog("EridiumEconomy gestoppt.");
    }




    //Getters und Setters für HashMaps usw auf die das Modul zugreifen muss
    public HashMap<Player, PlayerJobStatModel> getLocalJobStats() {
        return localJobStats;
    }
    public void setLocalJobStats(HashMap<Player, PlayerJobStatModel> localJobStats) {
        this.localJobStats = localJobStats;
    }







    public static EridiumEconomy getPlugin() {
        return plugin;
    }

    public ArrayList<String> getDbC() {
        return dbC;
    }

    public void cLog(String logMessage){
        plugin.getLogger().info("KONSOLE-EridiumEconomy: " + logMessage);
    }
    public void cWarning(String logMessage){
        plugin.getLogger().info("WARNUNG-EridiumEconomy: " + logMessage);
    }



}
