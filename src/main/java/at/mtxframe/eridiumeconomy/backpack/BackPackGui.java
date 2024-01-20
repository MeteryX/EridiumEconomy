package at.mtxframe.eridiumeconomy.backpack;

import at.mtxframe.eridiumeconomy.utils.MapPopulators;
import at.mtxframe.mtxframe.gui.MenuManager;
import at.mtxframe.mtxframe.messaging.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackPackGui{
    MessageHandler msgHandler = new MessageHandler();
    HashMap<Integer, ItemStack> menuItems;
    MapPopulators maps = new MapPopulators();
    int loopCounter = 9;

    public void backPackSelector(Player player,HashMap<Integer, ItemStack> menuItems){
        this.menuItems = menuItems;
        MenuManager menuManager = new MenuManager();
        menuManager.setPlayer(player);
        String inventoryType = menuManager.getTypeHopper();

        menuItems = maps.getSelectorItems();
        menuManager.buildMenu(inventoryType,ChatColor.DARK_GRAY + "Rucksack Auswahl",menuItems,4,player);

    }

    public void openBackPack(Player player,HashMap<Integer,ItemStack> menuItems){
        this.menuItems = menuItems;
        //TODO: Check Welcher Rucksack aktiviert werden soll
        //Zum testen wird jetzt einfach mal ein Rucksack erstellt der Mining Sachen beinhaltet
        //Items für das Menü erstellen
        MenuManager menuManager = new MenuManager();
        menuManager.setPlayer(player);
        String inventoryType = menuManager.getTypeChest();
        String inventoryName = ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Rucksack";
        int inventorySize = 54;
        ItemStack closeButton = menuManager.getCloseButton();
        ItemStack backButon = menuManager.getBackButton();
        ItemStack upgradesButton = menuManager.getUpgradesButton();
        ItemStack capacityButton = menuManager.getCapacityButton();

        for (Map.Entry<Integer, ItemStack> entry : menuItems.entrySet()) {
            int index = entry.getKey();
            ItemStack currentItem = entry.getValue();
            //TODO: Lore eines Items an den Inhalt des Rucksacks anpassen
            ItemMeta currentMeta = currentItem.getItemMeta();
            ArrayList<String> currentLore = new ArrayList<>();
            //TODO Integer Value einfügen
            int currentValue = 1;
            currentLore.add(ChatColor.GRAY + "Items im Rucksack: " + currentValue);
            currentLore.add(ChatColor.GRAY + "Linksklick > ganzen Stack nehmen");
            currentLore.add(ChatColor.GRAY + "Rechtsklick > ein Stück nehmen");
            currentLore.add(ChatColor.GRAY + "SHIFT + Linksklick > alles nehmen");
            currentMeta.setLore(currentLore);
            currentItem.setItemMeta(currentMeta);
            if (currentValue < 65) {
                currentItem.setAmount(currentValue);
            } else {
                currentItem.setAmount(64);
            }
                menuItems.put(index, currentItem);

        }
        menuItems.put(0,backButon);
        menuItems.put(3,capacityButton);
        menuItems.put(4,upgradesButton);
        menuItems.put(7,maps.getDeactivateItem());
        menuItems.put(8, closeButton);


        menuManager.buildMenu(inventoryType, inventoryName, menuItems, inventorySize, player);
    }


    private void setLoopCounter(int loopCounter) {
        this.loopCounter = loopCounter;
    }

}
