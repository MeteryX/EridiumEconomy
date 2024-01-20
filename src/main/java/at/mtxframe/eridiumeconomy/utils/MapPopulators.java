package at.mtxframe.eridiumeconomy.utils;

import at.mtxframe.mtxframe.gui.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;

public class MapPopulators {
    MenuManager menuManager = new MenuManager();

    HashMap<Material, Double> blocksMapFarming = new HashMap<>();
    HashMap<Material, Double> blocksMapMining = new HashMap<>();
    HashMap<Material, Double> blocksMapWoodcutting = new HashMap<>();
    HashMap<EntityType, Double> huntingEntities = new HashMap<>();
    HashMap<Integer,ItemStack> backpackFarming = new HashMap<>();
    HashMap<Integer,ItemStack> backpackMining = new HashMap<>();
    HashMap<Integer,ItemStack> backpackWoodcutting = new HashMap<>();
    HashMap<Integer, EntityType> backpackHunting = new HashMap<>();


    HashMap<Integer, ItemStack> selectorItems = new HashMap<>();



    public HashMap<Material, Double> getBlocksMapFarming() {
        blocksMapFarming.put(Material.PUMPKIN, 00.12);
        blocksMapFarming.put(Material.MELON, 00.12);
        blocksMapFarming.put(Material.CACTUS, 00.15);
        blocksMapFarming.put(Material.WHEAT, 00.09);
        blocksMapFarming.put(Material.SUGAR_CANE, 00.08);
        blocksMapFarming.put(Material.CARROTS,00.23);
        blocksMapFarming.put(Material.POTATOES,00.23);
        blocksMapFarming.put(Material.BEETROOTS,00.23);
        blocksMapFarming.put(Material.COCOA_BEANS,00.23);
        //Todo: Nether Job erstellen
        blocksMapFarming.put(Material.NETHER_WART,00.23);

        return blocksMapFarming;
    }

    public HashMap<Material, Double> getBlocksMapMining() {
        blocksMapMining.put(Material.IRON_ORE,00.16);
        blocksMapMining.put(Material.DEEPSLATE_IRON_ORE,00.32);
        blocksMapMining.put(Material.GOLD_ORE,00.19);
        blocksMapMining.put(Material.DEEPSLATE_GOLD_ORE,00.38);
        blocksMapMining.put(Material.COPPER_ORE,00.12);
        blocksMapMining.put(Material.DEEPSLATE_COPPER_ORE,00.24);
        blocksMapMining.put(Material.REDSTONE_ORE,00.13);
        blocksMapMining.put(Material.DEEPSLATE_REDSTONE_ORE,00.26);
        blocksMapMining.put(Material.LAPIS_ORE,00.11);
        blocksMapMining.put(Material.DEEPSLATE_LAPIS_ORE,00.22);
        blocksMapMining.put(Material.DIAMOND_ORE,00.42);
        blocksMapMining.put(Material.DEEPSLATE_DIAMOND_ORE, 00.84);
        blocksMapMining.put(Material.COAL_ORE,00.09);
        blocksMapMining.put(Material.DEEPSLATE_COAL_ORE,00.20);
        blocksMapMining.put(Material.EMERALD_ORE,00.20);
        blocksMapMining.put(Material.DEEPSLATE_EMERALD_ORE,00.20);

        //Todo: Nether Job erstellen
        blocksMapMining.put(Material.NETHER_QUARTZ_ORE,00.14);
        blocksMapMining.put(Material.NETHER_GOLD_ORE,00.20);

        return blocksMapMining;
    }

    public HashMap<Material, Double> getBlocksMapWoodcutting() {
        blocksMapWoodcutting.put(Material.OAK_LOG, 00.25);
        blocksMapWoodcutting.put(Material.STRIPPED_OAK_LOG, 00.25);
        blocksMapWoodcutting.put(Material.BIRCH_LOG, 00.25);
        blocksMapWoodcutting.put(Material.DARK_OAK_LOG, 00.25);
        blocksMapWoodcutting.put(Material.JUNGLE_LOG, 00.25);
        blocksMapWoodcutting.put(Material.ACACIA_LOG, 00.25);
        blocksMapWoodcutting.put(Material.CHERRY_LOG, 00.25);
        blocksMapWoodcutting.put(Material.MANGROVE_LOG, 00.25);
        blocksMapWoodcutting.put(Material.SPRUCE_LOG, 00.25);
        blocksMapWoodcutting.put(Material.CRIMSON_STEM, 00.50);
        blocksMapWoodcutting.put(Material.WARPED_STEM, 00.50);

        return blocksMapWoodcutting;
    }

    public HashMap<EntityType, Double> getHuntingEntities() {
        huntingEntities.put(EntityType.PIG, 00.20);
        huntingEntities.put(EntityType.COW, 00.20);
        huntingEntities.put(EntityType.SHEEP, 00.20);
        huntingEntities.put(EntityType.HORSE, 00.20);
        huntingEntities.put(EntityType.PANDA, 00.20);
        huntingEntities.put(EntityType.PARROT, 00.20);
        huntingEntities.put(EntityType.DONKEY, 00.20);
        huntingEntities.put(EntityType.CAMEL, 00.20);
        huntingEntities.put(EntityType.CAT, 00.20);
        huntingEntities.put(EntityType.WOLF, 00.20);
        huntingEntities.put(EntityType.RABBIT,0.43);
        huntingEntities.put(EntityType.POLAR_BEAR,0.43);
        huntingEntities.put(EntityType.SILVERFISH,0.43);
        huntingEntities.put(EntityType.SNIFFER,0.43);
        //Todo: Nether Job erstellen
        huntingEntities.put(EntityType.STRIDER,0.43);
        huntingEntities.put(EntityType.TRADER_LLAMA,0.43);
        huntingEntities.put(EntityType.ZOMBIE, 00.20);
        huntingEntities.put(EntityType.SKELETON, 00.20);
        huntingEntities.put(EntityType.CREEPER, 00.20);
        huntingEntities.put(EntityType.ENDERMAN, 00.20);
        huntingEntities.put(EntityType.SPIDER, 00.20);
        huntingEntities.put(EntityType.CAVE_SPIDER, 00.20);
        huntingEntities.put(EntityType.ZOMBIE_VILLAGER, 00.20);
        huntingEntities.put(EntityType.PIGLIN, 00.20);
        huntingEntities.put(EntityType.PIGLIN_BRUTE, 00.20);
        huntingEntities.put(EntityType.ZOMBIFIED_PIGLIN, 00.20);
        huntingEntities.put(EntityType.HOGLIN,0.43);
        huntingEntities.put(EntityType.MAGMA_CUBE,0.43);
        huntingEntities.put(EntityType.HUSK,0.43);
        huntingEntities.put(EntityType.GHAST,0.43);
        huntingEntities.put(EntityType.WITCH,0.43);
        huntingEntities.put(EntityType.BLAZE,0.43);
        huntingEntities.put(EntityType.SHULKER,0.43);
        huntingEntities.put(EntityType.PHANTOM,0.43);
        huntingEntities.put(EntityType.SLIME,0.43);
        huntingEntities.put(EntityType.STRAY,0.43);
        huntingEntities.put(EntityType.VEX,0.43);

        huntingEntities.put(EntityType.WITHER_SKELETON,0.43);
        huntingEntities.put(EntityType.WITHER,4.50);
        huntingEntities.put(EntityType.WARDEN,5.00);
        huntingEntities.put(EntityType.VINDICATOR,0.43);
        huntingEntities.put(EntityType.PILLAGER,0.43);
        huntingEntities.put(EntityType.RAVAGER,0.43);

        return huntingEntities;
    }


    public HashMap<Integer, ItemStack> getBackpackFarming() {
        backpackFarming.put(9 ,convertToItemStack(Material.WHEAT));
        backpackFarming.put(18 ,convertToItemStack(Material.WHEAT_SEEDS));
        backpackFarming.put(10 ,convertToItemStack(Material.CARROT));
        backpackFarming.put(19 ,convertToItemStack(Material.POTATO));
        backpackFarming.put(11 ,convertToItemStack(Material.BEETROOT));
        backpackFarming.put(20 ,convertToItemStack(Material.BEETROOT_SEEDS));
        backpackFarming.put(12 ,convertToItemStack(Material.COCOA_BEANS));
        backpackFarming.put(21 ,convertToItemStack(Material.SUGAR_CANE));
        backpackFarming.put(13 ,convertToItemStack(Material.CACTUS));
        backpackFarming.put(22 ,convertToItemStack(Material.MELON));
        backpackFarming.put(14 ,convertToItemStack(Material.MELON_SLICE));
        backpackFarming.put(23 ,convertToItemStack(Material.MELON_SEEDS));
        backpackFarming.put(15 ,convertToItemStack(Material.PUMPKIN));
        backpackFarming.put(24 ,convertToItemStack(Material.PUMPKIN_SEEDS));

        return backpackFarming;
    }

    public HashMap<Integer, ItemStack> getBackpackMining() {
        backpackMining.put(18 ,convertToItemStack(Material.IRON_ORE));
        backpackMining.put(27 ,convertToItemStack(Material.DEEPSLATE_IRON_ORE));
        backpackMining.put(36 ,convertToItemStack(Material.IRON_INGOT));
        backpackMining.put(45 ,convertToItemStack(Material.RAW_IRON));

        backpackMining.put(19 ,convertToItemStack(Material.GOLD_ORE));
        backpackMining.put(28 ,convertToItemStack(Material.DEEPSLATE_GOLD_ORE));
        backpackMining.put(37 ,convertToItemStack(Material.GOLD_INGOT));
        backpackMining.put(46 ,convertToItemStack(Material.RAW_GOLD));

        backpackMining.put(20 ,convertToItemStack(Material.COPPER_ORE));
        backpackMining.put(29 ,convertToItemStack(Material.DEEPSLATE_COPPER_ORE));
        backpackMining.put(38 ,convertToItemStack(Material.COPPER_INGOT));
        backpackMining.put(47 ,convertToItemStack(Material.RAW_COPPER));

        backpackMining.put(21 ,convertToItemStack(Material.REDSTONE_ORE));
        backpackMining.put(30 ,convertToItemStack(Material.DEEPSLATE_REDSTONE_ORE));
        backpackMining.put(39 ,convertToItemStack(Material.REDSTONE));

        backpackMining.put(22 ,convertToItemStack(Material.LAPIS_ORE));
        backpackMining.put(31 ,convertToItemStack(Material.DEEPSLATE_LAPIS_ORE));
        backpackMining.put(40 ,convertToItemStack(Material.LAPIS_LAZULI));

        backpackMining.put(23 ,convertToItemStack(Material.DIAMOND_ORE));
        backpackMining.put(32 ,convertToItemStack(Material.DEEPSLATE_DIAMOND_ORE));
        backpackMining.put(41 ,convertToItemStack(Material.DIAMOND));

        backpackMining.put(24 ,convertToItemStack(Material.COAL_ORE));
        backpackMining.put(33 ,convertToItemStack(Material.DEEPSLATE_COAL_ORE));
        backpackMining.put(42 ,convertToItemStack(Material.COAL));

        backpackMining.put(25 ,convertToItemStack(Material.EMERALD_ORE));
        backpackMining.put(34 ,convertToItemStack(Material.DEEPSLATE_EMERALD_ORE));
        backpackMining.put(43 ,convertToItemStack(Material.EMERALD));

        //Todo: Nether Job erstellen
        backpackMining.put(26,convertToItemStack(Material.NETHER_QUARTZ_ORE));
        backpackMining.put(35 ,convertToItemStack(Material.QUARTZ));
        backpackMining.put(44,convertToItemStack(Material.NETHER_GOLD_ORE));
        return backpackMining;
    }

    public HashMap<Integer, ItemStack> getBackpackWoodcutting() {
        backpackWoodcutting.put( 9,convertToItemStack(Material.OAK_LOG));
        backpackWoodcutting.put( 18,convertToItemStack(Material.OAK_SAPLING));
        backpackWoodcutting.put( 10,convertToItemStack(Material.BIRCH_LOG));
        backpackWoodcutting.put( 19,convertToItemStack(Material.BIRCH_SAPLING));
        backpackWoodcutting.put( 11,convertToItemStack(Material.DARK_OAK_LOG));
        backpackWoodcutting.put( 20,convertToItemStack(Material.DARK_OAK_SAPLING));
        backpackWoodcutting.put( 12,convertToItemStack(Material.JUNGLE_LOG));
        backpackWoodcutting.put( 21,convertToItemStack(Material.JUNGLE_SAPLING));
        backpackWoodcutting.put( 13,convertToItemStack(Material.ACACIA_LOG));
        backpackWoodcutting.put( 22,convertToItemStack(Material.ACACIA_SAPLING));
        backpackWoodcutting.put( 14,convertToItemStack(Material.CHERRY_LOG));
        backpackWoodcutting.put( 23,convertToItemStack(Material.CHERRY_SAPLING));
        backpackWoodcutting.put( 15,convertToItemStack(Material.MANGROVE_LOG));
        backpackWoodcutting.put( 24,convertToItemStack(Material.MUDDY_MANGROVE_ROOTS));
        backpackWoodcutting.put( 16,convertToItemStack(Material.SPRUCE_LOG));
        backpackWoodcutting.put( 25,convertToItemStack(Material.SPRUCE_SAPLING));
        backpackWoodcutting.put( 17,convertToItemStack(Material.CRIMSON_STEM));
        backpackWoodcutting.put( 26,convertToItemStack(Material.WARPED_STEM));

        backpackWoodcutting.put( 39,convertToItemStack(Material.APPLE));
        backpackWoodcutting.put( 40,convertToItemStack(Material.GOLDEN_APPLE));
        backpackWoodcutting.put( 41,convertToItemStack(Material.ENCHANTED_GOLDEN_APPLE));

        return backpackWoodcutting;
    }

    public HashMap<Integer, EntityType> getBackpackHunting() {


        return backpackHunting;
    }







    public ItemStack getDeactivateItem(){
        ItemStack deactivateItem = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta itemMeta = deactivateItem.getItemMeta();
        itemMeta.setDisplayName(ChatColor.RED + "Rucksack deaktivieren");
        ArrayList<String> itemLore = new ArrayList<>();
        itemLore.add(ChatColor.GRAY + "Klicke hier, um deinen aktiven Rucksack zu deaktivieren.");
        itemLore.add(ChatColor.GRAY + "Um den Rucksack zu deaktivieren, musst du zuerst");
        itemLore.add(ChatColor.GRAY + "alle Items aussortieren.");
        itemMeta.setLore(itemLore);
        deactivateItem.setItemMeta(itemMeta);

        return deactivateItem;
    }


    public HashMap<Integer, ItemStack> getSelectorItems() {
        ArrayList<String> infoLore = new ArrayList<>();
        infoLore.add(ChatColor.GRAY + "Klicke auf das Item um den");
        infoLore.add(ChatColor.GRAY + "Rucksack für diesen Job zu öffnen.");
        infoLore.add(ChatColor.GRAY + "(Du kannst immer nur einen Rucksack aktivieren)");
        ItemStack miner = menuManager.createItemStack(Material.IRON_PICKAXE,ChatColor.BLUE + "Miner Rucksack",infoLore);
        ItemStack farmer = menuManager.createItemStack(Material.IRON_HOE,ChatColor.GREEN + "Farmer Rucksack",infoLore);
        ItemStack woodcutter = menuManager.createItemStack(Material.IRON_AXE,ChatColor.DARK_GREEN + "Holzfäller Rucksack",infoLore);
        ItemStack fisher = menuManager.createItemStack(Material.FISHING_ROD,ChatColor.AQUA + "Fischer Rucksack",infoLore);
        ItemStack hunter = menuManager.createItemStack(Material.IRON_SWORD,ChatColor.RED + "Jäger Rucksack",infoLore);
        selectorItems.put(0,miner);
        selectorItems.put(1,farmer);
        selectorItems.put(2,woodcutter);
        selectorItems.put(3,fisher);
        selectorItems.put(4,hunter);

        return selectorItems;
    }
    public ItemStack convertToItemStack(Material material){
        ItemStack itemStack = new ItemStack(material);

        return itemStack;

    }


}
