package at.mtxframe.eridiumeconomy.listeners;

import at.mtxframe.eridiumeconomy.EridiumEconomy;
import at.mtxframe.eridiumeconomy.backpack.BackPackUtils;
import at.mtxframe.eridiumeconomy.models.BackPackModel;
import at.mtxframe.eridiumeconomy.utils.MapPopulators;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EconomyGuiListener implements Listener {
    private  final EridiumEconomy plugin;
    public EconomyGuiListener(EridiumEconomy plugin){
        this.plugin = plugin;
    }
    //Inventories
   // FarmerGui farmerGui = new FarmerGui(plugin);
    HashMap<String,String> itemMap;
    Boolean isActiveBackpack = false;
    BackPackUtils backPackUtils = new BackPackUtils();
    HashMap<ItemStack,Integer> tempBackPackItems;

    BackPackModel backPackModel = new BackPackModel();
    MapPopulators mapPopulators = new MapPopulators();

    public HashMap<String, Boolean> getMenus() {
        menus.put(ChatColor.GREEN + "" + ChatColor.BOLD + "Job Menü",true);
        menus.put(ChatColor.BLUE + "" + ChatColor.BOLD + "Miner",true);
        menus.put(ChatColor.GREEN + "" + ChatColor.BOLD + "Farmer",true);
        menus.put(ChatColor.RED + "" + ChatColor.BOLD + "Jäger",true);
        menus.put(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Holzfäller",true);
        menus.put(ChatColor.AQUA + "" + ChatColor.BOLD + "Fischer",true);
        menus.put(ChatColor.DARK_GRAY + "Rucksack Auswahl",true);
        menus.put(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Rucksack",true);


        return menus;
    }

    HashMap<String,Boolean> menus = new HashMap<>();

    //Inventories

    @EventHandler
    public void clickEvent(InventoryClickEvent event) {
        HashMap<String, Boolean> guis = getMenus();
        String title = event.getView().getTitle();
        if (getMenus().containsKey(title)) {
            Player player = (Player) event.getWhoClicked();
            if (event.getCurrentItem() != null) {
                String itemName = event.getCurrentItem().getItemMeta().getDisplayName();
                if (getItemMap().containsKey(itemName)) {
                    checkBackPack(player,itemName);
                    if (!getItemMap().get(itemName).equals("NOCOMMAND")) {
                        player.performCommand(getItemMap().get(itemName));
                        event.setCancelled(true);
                    }
                }
                if (event.getView().getTitle().equals(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Rucksack")){

                    //TODO: Später von BackpackUtils regeln lassen
                    ItemStack clickedItem = event.getCurrentItem();
                    //TODO: Wahrscheinlich das clicked Item wegen der verschiedenen ItemMeta von Material zu Itemstack wandeln
                    Material clickedMaterial = clickedItem.getType();
                    PersistentDataContainer psd = player.getPersistentDataContainer();
                    String backPackState = psd.get(BackPackUtils.backPackItemsKey,PersistentDataType.STRING);
                    HashMap<ItemStack,Integer> tempBackPackItems = backPackModel.deserializeItems(backPackState);
                    HashMap<Material,ItemStack> backPackItemMaterials = new HashMap<>();
                    for (Map.Entry<ItemStack,Integer> entry : tempBackPackItems.entrySet()){
                        ItemStack currentStack = entry.getKey();
                        Material currentMaterial = currentStack.getType();
                        backPackItemMaterials.put(currentMaterial,currentStack);
                    }
                    if (backPackItemMaterials.containsKey(clickedMaterial)){
                        Inventory clickedInventory = event.getClickedInventory();
                        if (clickedInventory.getType().equals(InventoryType.PLAYER)){
                            int itemAmount = clickedItem.getAmount();
                            int newAmount = itemAmount + (tempBackPackItems.get(backPackItemMaterials.get(clickedMaterial))-1);
                            if (event.isLeftClick()){
                                tempBackPackItems.put(backPackItemMaterials.get(clickedMaterial),newAmount);
                                String newBackpack = backPackModel.serializeItems(tempBackPackItems);
                                psd.set(BackPackUtils.backPackItemsKey,PersistentDataType.STRING,newBackpack);
                                player.sendMessage("DEBUG: Sollte einlagern");
                                player.sendMessage("New Amount: " + newAmount);

                            }

                        }

                    }

                }


                event.setCancelled(true);

            } else if (guis.containsKey(title)) {
                event.setCancelled(getMenus().get(title));
            }


        }

    }

    public HashMap<String,String>getItemMap(){
        itemMap = new HashMap<>();
        itemMap.put(ChatColor.BLUE + "" + ChatColor.BOLD + "Miner", "jobminer");
        itemMap.put(ChatColor.GREEN + "" + ChatColor.BOLD + "Farmer" , "jobfarmer");
        itemMap.put(ChatColor.RED + "" + ChatColor.BOLD + "Jäger" , "jobhunter");
        itemMap.put(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "Holzfäller" , "jobwoodcutter");
        itemMap.put(ChatColor.AQUA + "" + ChatColor.BOLD + "Fischer" , "jobfisher");
        itemMap.put(ChatColor.DARK_RED + "Zurück" , "jobs");

        itemMap.put(ChatColor.BLUE + "Miner Rucksack" , "backpack");
        itemMap.put(ChatColor.GREEN + "Farmer Rucksack" , "backpack");
        itemMap.put(ChatColor.DARK_GREEN + "Holzfäller Rucksack" , "backpack");
        itemMap.put(ChatColor.AQUA + "Fischer Rucksack" , "backpack");
        itemMap.put(ChatColor.RED + "Jäger Rucksack" , "backpack");
        itemMap.put(ChatColor.RED + "Rucksack deaktivieren", "NOCOMMAND");

        return itemMap;
    }

    public void checkBackPack(Player player,String itemName){
        PersistentDataContainer psd = player.getPersistentDataContainer();
        String backPackState = psd.get(BackPackUtils.backPackKey, PersistentDataType.STRING);
        if (backPackState.equals(BackPackUtils.noActiveBackPack)) {
           if (itemName.equals(ChatColor.BLUE + "Miner Rucksack")){
               psd.set(BackPackUtils.backPackKey,PersistentDataType.STRING,BackPackUtils.getMinerBackPack());
               psd.set(BackPackUtils.backPackItemsKey,PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
           } else if (itemName.equals(ChatColor.GREEN + "Farmer Rucksack")) {
               psd.set(BackPackUtils.backPackKey,PersistentDataType.STRING,BackPackUtils.getFarmerBackPack());
               psd.set(BackPackUtils.backPackItemsKey,PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
           } else if (itemName.equals(ChatColor.DARK_GREEN + "Holzfäller Rucksack")) {
               psd.set(BackPackUtils.backPackKey,PersistentDataType.STRING,BackPackUtils.getWoodcutterBackPack());
               psd.set(BackPackUtils.backPackItemsKey,PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
           }else if (itemName.equals(ChatColor.AQUA + "Fischer Rucksack")){
               psd.set(BackPackUtils.backPackKey,PersistentDataType.STRING,BackPackUtils.getFisherBackPack());
               psd.set(BackPackUtils.backPackItemsKey,PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
           } else if (itemName.equals(ChatColor.RED + "Jäger Rucksack")) {
               psd.set(BackPackUtils.backPackKey,PersistentDataType.STRING,BackPackUtils.getHunterBackPack());
               psd.set(BackPackUtils.backPackItemsKey,PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
           }

        }
        else if (itemName.equals(ChatColor.RED + "Rucksack deaktivieren")) {
            player.sendMessage("DEBUG: Deaktivieren geklickt");
            Inventory openInventory = (Inventory) player.getOpenInventory().getTopInventory();
            if (backPackUtils.checkBackPack(psd, openInventory)){
                psd.remove(BackPackUtils.backPackKey);
                psd.set(BackPackUtils.backPackKey,PersistentDataType.STRING,BackPackUtils.noActiveBackPack);
                player.closeInventory();
                player.sendMessage("DEBUG: Rucksack erfolgreich deaktiviert");
            }
        }


    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) throws IOException {
        Player player = (Player) event.getPlayer();
        Inventory inventory = event.getView().getTopInventory();

        if (event.getView().getTitle().equals(ChatColor.DARK_GRAY + "" + ChatColor.BOLD + "Rucksack")){
            for (int i = 0; i < inventory.getSize();  i++){
                ItemStack item = inventory.getItem(i);
                int value = item.getAmount();
                tempBackPackItems.put(item,value);
            }
            String serializedBackPack = backPackModel.serializeItems(tempBackPackItems);
            PersistentDataContainer psd = player.getPersistentDataContainer();
            if (!(psd.has(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING))){
                psd.set(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING,serializedBackPack);
            }
            else {
                psd.set(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING,serializedBackPack);
                backPackModel.configDebug(serializedBackPack);
            }


        }




    }






}
