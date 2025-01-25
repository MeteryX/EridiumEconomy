package at.mtxframe.eridiumeconomy.backpack;

import at.mtxframe.mtxframe.MtxFrame;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class BackPackUtils {


    public static final String noActiveBackPack = "NONE";
    public static final String emptyBackPack = "EMPTY";

    public static final String minerBackPack = "BP_MINER";
    public static final String farmerBackPack = "BP_FARMER";
    public static final String woodcutterBackPack = "BP_WOODCUTTER";
    public static final String fisherBackPack = "BP_FISHER";
    public static final String hunterBackPack = "BP_HUNTER";
    public static final String backPackItems = "BP_ITEMS";



    public static final NamespacedKey backPackKey = new NamespacedKey(MtxFrame.getPlugin(),getnoActiveBackPack());
    public static final NamespacedKey backPackItemsKey = new NamespacedKey(MtxFrame.getPlugin(),getBackPackItems());


    //Getter für Main Key und backPackItemsKey
    public static String getnoActiveBackPack() {
        return noActiveBackPack;
    }
    public static String getBackPackItems(){
        return backPackItems;
    }


    //Getters und Setters für BackPack Keys
    public static String getMinerBackPack() {
        return minerBackPack;
    }
    public static String getFarmerBackPack() {
        return farmerBackPack;
    }
    public static String getWoodcutterBackPack() {
        return woodcutterBackPack;
    }
    public static String getFisherBackPack() {
        return fisherBackPack;
    }
    public static String getHunterBackPack() {
        return hunterBackPack;
    }
    public static String getEmptyBackPack(){
        return  emptyBackPack;
    }

    //Getters für NamedSpaceKeys
    public static NamespacedKey getBackPackKey(){
        return backPackKey;
    }
    public static NamespacedKey getBackPackItemsKey(){
        return backPackItemsKey;
    }









    //Checks und andere Rucksack bezogene Methoden
    //Serialization


    //Deseralization


    //Check, ob der Rucksack leer ist
    public Boolean checkBackPack(PersistentDataContainer psd, Inventory inventory){
        //TODO: Backpack Inhalte serialisieren und hier implementieren (Wenn leer = true, Items im Rucksack = False)
        if (psd.has(BackPackUtils.getBackPackItemsKey(), PersistentDataType.STRING)){
            String backPackStatus = psd.get(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING);
            int highestAmount = 1;
            for (int i = 0; i < inventory.getSize(); i++){
                if (inventory.getItem(i).getAmount() != 1){
                    highestAmount = inventory.getItem(i).getAmount();
                }
            }
            if (highestAmount == 1){
                psd.set(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
                return true;
            }
            if (backPackStatus.equals(BackPackUtils.getEmptyBackPack())){
                return true;
            }

        }
            return false;

    }

    public void moveItems(){

    }


}
