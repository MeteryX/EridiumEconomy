package at.mtxframe.eridiumeconomy.models;

import at.mtxframe.mtxframe.MtxFrame;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;

public class BackPackModel implements Serializable {

    HashMap<ItemStack,Integer> backPackItems = new HashMap<>();
    String encodedObject;


    public void addItem(ItemStack itemStack){
        backPackItems.put(itemStack, itemStack.getAmount());

    }

    public void serializeItems() throws IOException {
        try {
            ByteArrayOutputStream io = new ByteArrayOutputStream();
            BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);
            os.writeObject(backPackItems);
            os.flush();

            byte[] serializedMap = io.toByteArray();

            encodedObject = Base64.getEncoder().encodeToString(serializedMap);

        }catch (IOException ex){
            System.out.println(ex);
        }
    }

    public  void deserializeItems(String itemsToDecode){
         = Base64.getDecoder().decode(itemsToDecode);

    }






}
