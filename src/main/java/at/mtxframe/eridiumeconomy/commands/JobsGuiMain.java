package at.mtxframe.eridiumeconomy.commands;

import at.mtxframe.eridiumeconomy.EridiumEconomy;
import at.mtxframe.mtxframe.database.DatabaseJobs;
import at.mtxframe.mtxframe.messaging.MessageHandler;
import at.mtxframe.mtxframe.models.PlayerJobStatModel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.sql.SQLException;
import java.util.ArrayList;

public class JobsGuiMain implements CommandExecutor {
    EridiumEconomy plugin;
    public  JobsGuiMain(EridiumEconomy plugin){
        this.plugin = plugin;
    }

    DatabaseJobs jobsDatabase = new DatabaseJobs();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;


            Inventory jobsGui = Bukkit.createInventory(player,9, ChatColor.GREEN + "" + ChatColor.BOLD + "Job Menü");

            ItemStack jobsMiner = new ItemStack(Material.IRON_PICKAXE, 1);
            ItemStack jobsFarmer = new ItemStack(Material.WHEAT, 1);
            ItemStack jobsHunter = new ItemStack(Material.CROSSBOW, 1);
            ItemStack jobsWoodcutter = new ItemStack(Material.IRON_AXE, 1);
            ItemStack jobsFisher = new ItemStack(Material.FISHING_ROD, 1);

            //Filler
            ItemStack filler = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
            ItemMeta fillerMeta = filler.getItemMeta();
            fillerMeta.setDisplayName("");
            filler.setItemMeta(fillerMeta);

            //Miner
            ItemMeta minerMeta = jobsMiner.getItemMeta();
            minerMeta.setDisplayName(ChatColor.BLUE + "" + ChatColor.BOLD + "Miner");
            ArrayList<String> minerLore = new ArrayList<>();

            //Farmer
            ItemMeta farmerMeta = jobsFarmer.getItemMeta();
            farmerMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Farmer");
            ArrayList<String> farmerLore = new ArrayList<>();

            //Jäger
            ItemMeta hunterMeta = jobsHunter.getItemMeta();
            hunterMeta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Jäger");
            ArrayList<String> hunterLore = new ArrayList<>();

            //Holzfäller
            ItemMeta woodcutterMeta = jobsWoodcutter.getItemMeta();
            woodcutterMeta.setDisplayName(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Holzfäller");
            ArrayList<String> woodcutterLore = new ArrayList<>();

            //Fischer
            ItemMeta fisherMeta = jobsFisher.getItemMeta();
            fisherMeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + "Fischer");
            ArrayList<String> fisherLore = new ArrayList<>();

            try {
                //PlayerJobsModel aus der Datenbank um die ItemStacks mit Daten zu füllen
                PlayerJobStatModel jobStats = jobsDatabase.findJobsDataByUUID(String.valueOf(player.getUniqueId()));
                //ItemStacks für die einzelnen Berufe

                //ItemMeta für den Miner Job


                minerLore.add(ChatColor.GRAY + "Über den Miner Job kannst du Geld verdienen indem du");
                minerLore.add(ChatColor.GRAY + "Erze abbaust, mit jedem Level steigert sich dein Gewinn");
                minerLore.add(ChatColor.GRAY + "an Erfahrungspunkten und Geld.");
                minerLore.add("");
                minerLore.add(ChatColor.GRAY + "Dein aktuelles Level: " + ChatColor.GREEN + jobStats.getMiningLevel());
                minerLore.add("");
                minerLore.add(ChatColor.GRAY + "Auf höheren Levels stehen dir Fähigkeiten zur Verfügung,");
                minerLore.add(ChatColor.RED + ">" + ChatColor.GOLD + "Klicke hier um deinen Miner-Level Fortschritt zu sehen." + ChatColor.RED + "<");
                minerMeta.setLore(minerLore);


                //ItemMeta für den Farmer


                farmerLore.add(ChatColor.GRAY + "Über den Farmer Job kannst du Geld verdienen indem du");
                farmerLore.add(ChatColor.GRAY + "Nahrung anbaust und erntest, je mehr du erntest, desto mehr");
                farmerLore.add(ChatColor.GRAY + "Erfahrungspunkte und Geld warten am Ende auf dich.");
                farmerLore.add("");
                farmerLore.add(ChatColor.GRAY + "Dein aktuelles Level: " + ChatColor.GREEN + jobStats.getFarmerLevel());

                farmerLore.add("");
                farmerLore.add(ChatColor.GRAY + "Auf höheren Levels stehen dir Fähigkeiten zur Verfügung,");
                farmerLore.add(ChatColor.RED + ">" + ChatColor.GOLD + "Klicke hier um deinen Farmer-Level Fortschritt zu sehen." + ChatColor.RED + "<");
                farmerMeta.setLore(farmerLore);


                //ItemMeta für den Jäger

                hunterLore.add(ChatColor.GRAY + "Über den Jäger Job kannst du Geld verdienen indem du");
                hunterLore.add(ChatColor.GRAY + "Monster oder auch friedliche Mobs jagst aber Achtung!");
                hunterLore.add(ChatColor.GRAY + "Nachts ist es gefährlicher als draußen.");
                hunterLore.add("");
                hunterLore.add(ChatColor.GRAY + "Dein aktuelles Level: " + ChatColor.GREEN + jobStats.getHunterLevel());
                hunterLore.add("");
                hunterLore.add(ChatColor.GRAY + "Auf höheren Levels stehen dir Fähigkeiten zur Verfügung,");
                hunterLore.add(ChatColor.RED + ">" + ChatColor.GOLD + "Klicke hier um deinen Jäger-Level Fortschritt zu sehen." + ChatColor.RED + "<");
                hunterMeta.setLore(hunterLore);

                //ItemMeta für den Holzfäller

                woodcutterLore.add(ChatColor.GRAY + "Über den Holzfäller Job verdienst du dein Geld mit");
                woodcutterLore.add(ChatColor.GRAY + "dem fällen von Bäumen, du schadest zwar der Umwelt,");
                woodcutterLore.add(ChatColor.GRAY + "aber kannst trotzdem steinreich dadurch werden.");
                woodcutterLore.add("");
                woodcutterLore.add(ChatColor.GRAY + "Dein aktuelles Level: " + ChatColor.GREEN + jobStats.getWoodcutterLevel());
                woodcutterLore.add("");
                woodcutterLore.add(ChatColor.GRAY + "Auf höheren Levels stehen dir mehr Fähigkeiten zur Verfügung,");
                woodcutterLore.add(ChatColor.RED + ">" + ChatColor.GOLD + "Klicke hier um deinen Holzfäller-Level Fortschritt zu sehen." + ChatColor.RED + "<");
                woodcutterMeta.setLore(woodcutterLore);

                //itemMeta für den Fischer
                fisherLore.add(ChatColor.GRAY + "Über den Fischer Job kannst du Geld verdienen indem du");
                fisherLore.add(ChatColor.GRAY + "angelst. Dabei warten aber nicht nur Erfahrungspunkte");
                fisherLore.add(ChatColor.GRAY + "und Geld auf dich, sondern auch ein paar einzigartige Items.");
                fisherLore.add("");
                fisherLore.add(ChatColor.GRAY + "Dein aktuelles Level: " + ChatColor.GREEN + jobStats.getFisherLevel());
                fisherLore.add("");
                fisherLore.add(ChatColor.GRAY + "Auf höheren Levels stehen dir mehr Fähigkeiten zur Verfügung,");
                fisherLore.add(ChatColor.RED + ">" + ChatColor.GOLD + "Klicke hier um deinen Fischer-Level Fortschritt zu sehen." + ChatColor.RED + "<");
                fisherMeta.setLore(fisherLore);

            } catch (SQLException exception){
                plugin.cWarning(exception.getMessage());
            }
            jobsMiner.setItemMeta(minerMeta);
            jobsFarmer.setItemMeta(farmerMeta);
            jobsHunter.setItemMeta(hunterMeta);
            jobsWoodcutter.setItemMeta(woodcutterMeta);
            jobsFisher.setItemMeta(fisherMeta);

            jobsGui.setItem(0,filler);
            jobsGui.setItem(1,filler);
            jobsGui.setItem(2,jobsMiner);
            jobsGui.setItem(3, jobsFarmer);
            jobsGui.setItem(4, jobsHunter);
            jobsGui.setItem(5, jobsWoodcutter);
            jobsGui.setItem(6, jobsFisher);
            jobsGui.setItem(7,filler);
            jobsGui.setItem(8,filler);

            player.openInventory(jobsGui);

        }


        return true;
    }
}
