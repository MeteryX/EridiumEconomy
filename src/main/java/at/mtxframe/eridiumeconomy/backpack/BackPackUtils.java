package at.mtxframe.eridiumeconomy.backpack;

import at.mtxframe.mtxframe.MtxFrame;
import org.bukkit.NamespacedKey;

import java.util.ArrayList;

public class BackPackUtils {


    public static final String noActiveBackPack = "NONE";

    public static final String minerBackPack = "BP_MINER";
    public static final String farmerBackPack = "BP_FARMER";
    public static final String woodcutterBackPack = "BP_WOODCUTTER";
    public static final String fisherBackPack = "BP_FISHER";
    public static final String hunterBackPack = "BP_HUNTER";



    public static final NamespacedKey backPackKey = new NamespacedKey(MtxFrame.getPlugin(),getnoActiveBackPack());


    //Getter für Main Key
    public static String getnoActiveBackPack() {
        return noActiveBackPack;
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

    //Getters für NamedSpaceKeys
    public static NamespacedKey getBackPackKey(){
        return backPackKey;
    }








    //Checks und andere Rucksack bezogene Methoden
    //Serialization


    //Deseralization


    //Check ob der Rucksack leer ist
    public Boolean checkBackPack(){
        //TODO: Backpack Inhalte serialisieren und hier implementieren (Wenn leer = true, Items im Rucksack = False)

        return true;
    }


}
