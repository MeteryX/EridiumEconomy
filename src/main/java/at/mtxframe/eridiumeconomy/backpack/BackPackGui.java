package at.mtxframe.eridiumeconomy.backpack;

import at.mtxframe.eridiumeconomy.models.BackPackModel;
import at.mtxframe.eridiumeconomy.utils.MapPopulators;
import at.mtxframe.mtxframe.gui.MenuManager;
import at.mtxframe.mtxframe.messaging.MessageHandler;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BackPackGui{
    MessageHandler msgHandler = new MessageHandler();
    HashMap<Integer, ItemStack> menuItems;
    MapPopulators maps = new MapPopulators();
    int loopCounter = 9;
    int currentValue = 1;
    BackPackModel backPackModel = new BackPackModel();
    HashMap<ItemStack,Integer> backPackItems = new HashMap<>();
    public void backPackSelector(Player player,HashMap<Integer, ItemStack> menuItems){
        this.menuItems = menuItems;
        MenuManager menuManager = new MenuManager();
        menuManager.setPlayer(player);
        String inventoryType = menuManager.getTypeHopper();

        menuItems = maps.getSelectorItems();
        menuManager.buildMenu(inventoryType,ChatColor.DARK_GRAY + "Rucksack Auswahl",menuItems,4,player);

    }

    public void openBackPack(Player player,HashMap<Integer,ItemStack> menuItems) throws IOException, ClassNotFoundException {
        PersistentDataContainer psd = player.getPersistentDataContainer();
        String backPackState = psd.get(BackPackUtils.backPackItemsKey,PersistentDataType.STRING);
        HashMap<ItemStack,Integer> tempBackPackItems = backPackModel.deserializeItems(backPackState);
        HashMap<Material,ItemStack> backPackItemMaterials = new HashMap<>();
        if (psd.has(BackPackUtils.backPackKey,PersistentDataType.STRING)){


            if (!(backPackState.equals(BackPackUtils.getEmptyBackPack()))){
                 backPackItems = backPackModel.deserializeItems(backPackState);

                for (Map.Entry<ItemStack,Integer> entry : tempBackPackItems.entrySet()){
                    ItemStack currentStack = entry.getKey();
                    Material currentMaterial = currentStack.getType();
                    backPackItemMaterials.put(currentMaterial,currentStack);
                }
            }
        }

        this.menuItems = menuItems;
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
            Material currentMaterial = currentItem.getType();
            int currentBackPackValue = 0;
            if (backPackItemMaterials.containsKey(currentMaterial)){
                currentBackPackValue = backPackItems.get(backPackItemMaterials.get(currentMaterial));
            }

            //DEBUG:
            ItemMeta currentMeta = currentItem.getItemMeta();
            ArrayList<String> currentLore = new ArrayList<>();
            //TODO Integer Value einfügen

            if (currentBackPackValue > 1){
                currentValue = currentBackPackValue-1;
            }

            currentLore.add(ChatColor.GRAY + "Items im Rucksack: " + currentValue);
            currentLore.add(ChatColor.GRAY + "Linksklick > ganzen Stack nehmen");
            currentLore.add(ChatColor.GRAY + "Rechtsklick > ein Stück nehmen");
            currentLore.add(ChatColor.GRAY + "SHIFT + Linksklick > alles nehmen");
            currentMeta.setLore(currentLore);

            currentMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
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

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }
}
