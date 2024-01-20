package at.mtxframe.eridiumeconomy.userinterface;

import at.mtxframe.eridiumeconomy.EridiumEconomy;
import at.mtxframe.mtxframe.database.DatabaseJobs;
import at.mtxframe.mtxframe.models.PlayerJobStatModel;
import at.mtxframe.mtxframe.utilitys.JobsLevelHandler;
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
import java.util.HashMap;

public class HunterGui implements CommandExecutor {
    private final EridiumEconomy plugin;
    DatabaseJobs jobsDatabase = new DatabaseJobs();

    public HunterGui(EridiumEconomy plugin) {
        this.plugin = plugin;
    }
    JobsLevelHandler lvHandler = new JobsLevelHandler();
    private HashMap<Integer,Double> dropChances = lvHandler.getDropChances();
    HashMap<Player,PlayerJobStatModel> localStats;


    @Override
    public boolean onCommand (CommandSender sender, Command command, String s, String[]args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            localStats = plugin.getLocalJobStats();
            PlayerJobStatModel jobStats = localStats.get(player);

            Inventory minerInventory = Bukkit.createInventory(player, 36, ChatColor.RED + "" + ChatColor.BOLD + "Jäger");
            //Info-Buch
            ItemStack info = new ItemStack(Material.BOOK, 1);
            ItemMeta infoMeta = info.getItemMeta();
            ArrayList<String> infoLore = new ArrayList<>();
            infoMeta.setDisplayName(ChatColor.GREEN + "" + ChatColor.BOLD + "Informationen über Levels");
            infoLore.add(ChatColor.GRAY + "Mit jedem Level erhältst du mehr Geld und Erfahrungspunkte" );
            infoLore.add( ChatColor.GREEN + "und " + ChatColor.GRAY + "deine chance auf doppelte Drops steigt um:  " + ChatColor.LIGHT_PURPLE + "0.05%");
            infoLore.add(ChatColor.GREEN + "Aktuelle Chance: " + ChatColor.LIGHT_PURPLE + dropChances.get(jobStats.getHunterLevel()));
            infoMeta.setLore(infoLore);
            info.setItemMeta(infoMeta);
            //back Tür
            ItemStack back = new ItemStack(Material.OAK_DOOR,1);
            ItemMeta backMeta = back.getItemMeta();
            backMeta.setDisplayName(ChatColor.DARK_RED + "Zurück");
            ArrayList<String> backLore = new ArrayList<>();
            backLore.add(ChatColor.GRAY + "Hier kommst du zurück");
            backLore.add(ChatColor.GRAY + "zur Job-Übersicht.");
            backMeta.setLore(backLore);
            back.setItemMeta(backMeta);

            //Level 1
            ItemStack m1 = new ItemStack(Material.PAPER, 5);
            ItemMeta m1M = m1.getItemMeta();
            ArrayList<String> m1Lore = new ArrayList<>();
            if (jobStats.getHunterLevel() < 5) {
                m1M.setDisplayName(ChatColor.RED + "Level 5");
            } else {
                m1M.setDisplayName(ChatColor.GREEN + "Level 5");
            }
            m1Lore.add(ChatColor.GREEN + "SPEZIAL");
            m1Lore.add(ChatColor.GRAY + "Mit dem " + ChatColor.RED + "Teleportbogen " + ChatColor.GRAY + "kannst du dich zum landepunkt deines Pfeils teleportieren.");
            m1Lore.add(ChatColor.RED + "Teleportbogen" + ChatColor.GRAY + " Abklingzeit: " + ChatColor.GREEN + "1 Minute");
            m1M.setLore(m1Lore);
            m1.setItemMeta(m1M);


            //Level 2
            ItemStack m2 = new ItemStack(Material.PAPER, 10);
            ItemMeta m2M = m2.getItemMeta();
            if (jobStats.getHunterLevel() < 10) {
                m2M.setDisplayName(ChatColor.RED + "Level 10");
            } else {
                m2M.setDisplayName(ChatColor.GREEN + "Level 10");
            }
            ArrayList<String> m2Lore = new ArrayList<>();
            m2Lore.add(ChatColor.GRAY + "Auf diesem Level schaltest du das abbauen einer ganzen Erzader frei.");
            m2Lore.add(ChatColor.GRAY + "Die Fähigkeit wird später verschiedene Abklingzeiten je nach Level haben.");
            m2.setItemMeta(m2M);
            m2M.setLore(m2Lore);

            //Level 3
            ItemStack m3 = new ItemStack(Material.PAPER, 15);
            ItemMeta m3M = m3.getItemMeta();

            if (jobStats.getHunterLevel() < 15) {
                m3M.setDisplayName(ChatColor.RED + "Level 15");
            } else {
                m3M.setDisplayName(ChatColor.GREEN + "Level 15");
            }
            ArrayList<String> m3Lore = new ArrayList<>();
            m3Lore.add(ChatColor.GREEN + "SPEZIAL");
            m3Lore.add(ChatColor.RED + "Teleportbogen" + ChatColor.GRAY + " Abklingzeit: " + ChatColor.GREEN + "50 Sekunden");
            m3Lore.add(ChatColor.GRAY + "Chance auf doppelte Drops: " + ChatColor.GREEN + "+" + ChatColor.DARK_PURPLE + "1.50%");
            m3M.setLore(m3Lore);
            m3.setItemMeta(m3M);

            //Level 4
            ItemStack m4 = new ItemStack(Material.PAPER, 20);
            ItemMeta m4M = m4.getItemMeta();
            if (jobStats.getHunterLevel() < 20) {
                m4M.setDisplayName(ChatColor.RED + "Level 20");
            } else {
                m4M.setDisplayName(ChatColor.GREEN + "Level 20");
            }
            ArrayList<String> m4Lore = new ArrayList<>();
            m4Lore.add(ChatColor.RED + "Teleportbogen" + ChatColor.GRAY + " Abklingzeit: " + ChatColor.GREEN + "35 Sekunden");
            m4Lore.add(ChatColor.GRAY + "Chance auf doppelte Drops: " + ChatColor.GREEN + "+" + ChatColor.DARK_PURPLE + "1.50%");
            m4M.setLore(m4Lore);

            m4.setItemMeta(m4M);

            //Level 5
            ItemStack m5 = new ItemStack(Material.PAPER, 25);
            ItemMeta m5M = m5.getItemMeta();
            if (jobStats.getHunterLevel() < 25) {
                m5M.setDisplayName(ChatColor.RED + "Level 25");
            } else {
                m5M.setDisplayName(ChatColor.GREEN + "Level 25");
            }
            ArrayList<String> m5Lore = new ArrayList<>();
            m5Lore.add(ChatColor.GREEN + "SPEZIAL");
            m5Lore.add(ChatColor.RED + "Teleportbogen" + ChatColor.GRAY + " Abklingzeit: " + ChatColor.GREEN + "25 Sekunden");
            m5Lore.add(ChatColor.GRAY + "Chance auf doppelte Drops: " + ChatColor.GREEN + "+" + ChatColor.DARK_PURPLE + "1.50%");
            m5M.setLore(m5Lore);
            m5.setItemMeta(m5M);

            //Level 6
            ItemStack m6 = new ItemStack(Material.PAPER, 30);
            ItemMeta m6M = m6.getItemMeta();
            if (jobStats.getHunterLevel() < 30) {
                m6M.setDisplayName(ChatColor.RED + "Level 30");
            } else {
                m6M.setDisplayName(ChatColor.GREEN + "Level 30");
            }
            ArrayList<String> m6Lore = new ArrayList<>();
            m6Lore.add(ChatColor.RED + "Teleportbogen" + ChatColor.GRAY + " Abklingzeit: " + ChatColor.GREEN + "20 Sekunden");
            m6Lore.add(ChatColor.GRAY + "Chance auf doppelte Drops: " + ChatColor.GREEN + "+" + ChatColor.DARK_PURPLE + "1.50%");
            m6M.setLore(m6Lore);
            m6.setItemMeta(m6M);

            //Level 7
            ItemStack m7 = new ItemStack(Material.PAPER, 35);
            ItemMeta m7M = m7.getItemMeta();
            if (jobStats.getHunterLevel() < 35) {
                m7M.setDisplayName(ChatColor.RED + "Level 35");
            } else {
                m7M.setDisplayName(ChatColor.GREEN + "Level 35");
            }
            ArrayList<String> m7Lore = new ArrayList<>();
            m7Lore.add(ChatColor.GREEN + "SPEZIAL");
            m7Lore.add(ChatColor.GRAY + "Gelegentlich spawnt eine Mob Horde wenn du einen Jäger-Mob tötest.");
            m7Lore.add(ChatColor.GRAY + "Chance auf doppelte Drops: " + ChatColor.GREEN + "+" + ChatColor.DARK_PURPLE + "1.50%");
            m7M.setLore(m7Lore);
            m7.setItemMeta(m7M);

            //Level 8
            ItemStack m8 = new ItemStack(Material.PAPER, 40);
            ItemMeta m8M = m8.getItemMeta();
            if (jobStats.getHunterLevel() < 40) {
                m8M.setDisplayName(ChatColor.RED + "Level 40");
            } else {
                m8M.setDisplayName(ChatColor.GREEN + "Level 40");
            }
            ArrayList<String> m8Lore = new ArrayList<>();

            m8Lore.add(ChatColor.RED + "Teleportbogen" + ChatColor.GRAY + " Abklingzeit: " + ChatColor.GREEN + "15 Sekunden");
            m8Lore.add(ChatColor.GRAY + "Chance auf doppelte Drops: " + ChatColor.GREEN + "+" + ChatColor.DARK_PURPLE + "1.50%");
            m8M.setLore(m8Lore);
            m8.setItemMeta(m8M);

            //Level 9
            ItemStack m9 = new ItemStack(Material.PAPER, 45);
            ItemMeta m9M = m9.getItemMeta();
            if (jobStats.getHunterLevel() < 45) {
                m9M.setDisplayName(ChatColor.RED + "Level 45");
            } else {
                m9M.setDisplayName(ChatColor.GREEN + "Level 45");
            }
            ArrayList<String> m9Lore = new ArrayList<>();
            m9Lore.add(ChatColor.GRAY + "Du kannst dein gehaltenes" + ChatColor.BLUE + " Schwert " + ChatColor.GRAY + "alle " + ChatColor.GREEN);
            m9Lore.add("15" + ChatColor.GRAY + " Minuten mit Shift-Rechtsklick reparieren.");
            m9Lore.add(ChatColor.GRAY + "Chance auf doppelte Drops: " + ChatColor.GREEN + "+" + ChatColor.DARK_PURPLE + "1.50%");
            m9M.setLore(m9Lore);
            m9.setItemMeta(m9M);

            //Level 10
            ItemStack m10 = new ItemStack(Material.PAPER, 50);
            ItemMeta m10M = m10.getItemMeta();
            if (jobStats.getHunterLevel() < 50) {
                m10M.setDisplayName(ChatColor.RED + "Level 50");
            } else {
                m10M.setDisplayName(ChatColor.GREEN + "Level 50");
            }
            ArrayList<String> m10Lore = new ArrayList<>();
            m10Lore.add(ChatColor.GOLD + "SPEZIAL");
            m10Lore.add(ChatColor.GRAY + "Auf diesem Level hast du das Ende deiner Karriere erreicht,");
            m10Lore.add(ChatColor.GRAY + "du bekommst jetzt Massen an Geld erhältst aber keine XP mehr.");
            m10Lore.add(ChatColor.GRAY + "Chance auf doppelte Drops: " + ChatColor.GREEN + "+" + ChatColor.DARK_PURPLE + "2.50%");
            m10M.setLore(m10Lore);
            m10.setItemMeta(m10M);


            minerInventory.setItem(0, m1);
            minerInventory.setItem(1, m2);
            minerInventory.setItem(2, m3);
            minerInventory.setItem(3, m4);
            minerInventory.setItem(4, m5);
            minerInventory.setItem(5, m6);
            minerInventory.setItem(6, m7);
            minerInventory.setItem(7, m8);
            minerInventory.setItem(8, m9);
            minerInventory.setItem(9, m10);


            minerInventory.setItem(27,back);
            minerInventory.setItem(35, info);

            player.openInventory(minerInventory);

        } return  true;
    }
}
