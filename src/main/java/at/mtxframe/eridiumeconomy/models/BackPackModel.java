package at.mtxframe.eridiumeconomy.models;

import at.mtxframe.mtxframe.MtxFrame;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BackPackModel implements Serializable {

    HashMap<ItemStack,Integer> backPackItems = new HashMap<>();
    String encodedObject;
    byte[] serializedMap;



    public void addItem(ItemStack itemStack) {
        backPackItems.put(itemStack, itemStack.getAmount());
    }

    public String serializeItems(HashMap<ItemStack, Integer> backPackItems) {
        try {
             ByteArrayOutputStream io = new ByteArrayOutputStream();
             BukkitObjectOutputStream os = new BukkitObjectOutputStream(io);

            os.writeObject(backPackItems);
            os.flush();

            serializedMap = io.toByteArray();
            encodedObject = Base64.getEncoder().encodeToString(serializedMap);

            return encodedObject;

        } catch (IOException ex) {
            Logger.getLogger(BackPackModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public HashMap<ItemStack, Integer> deserializeItems(String itemsToDecode) {
        try {
            serializedMap = Base64.getDecoder().decode(itemsToDecode);
            ByteArrayInputStream in = new ByteArrayInputStream(serializedMap);
            BukkitObjectInputStream is = new BukkitObjectInputStream(in);
            HashMap<ItemStack,Integer> itemMap = (HashMap<ItemStack,Integer>) is.readObject();

            return itemMap;


        } catch (IOException | ClassNotFoundException | IllegalArgumentException ex) {
            Logger.getLogger(BackPackModel.class.getName()).log(Level.SEVERE, "Error during deserialization", ex);
        }
        return null;
    }




    public void configDebug(String base64){
        // Beispiel-Base64-String (ersetzen Sie dies durch Ihren eigenen Base64-String)
        String base64String;
        base64String = base64;

        // Beispiel-Dateipfad (ersetzen Sie dies durch den Pfad Ihrer Konfigurationsdatei)
        String filePath = "plugins/EridiumEconomy/config.yml";

        // Konfiguration laden oder erstellen
        File configFile = new File(filePath);
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        // Base64-String decodieren
        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        String decodedText = new String(decodedBytes, StandardCharsets.UTF_8);

        // Wert in der Konfiguration setzen
        config.set("decodedValue", decodedText);

        // Konfiguration in Datei speichern
        try {
            config.save(configFile);
            System.out.println("Base64-String erfolgreich dekodiert und in die Konfiguration geladen.");
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern der Konfigurationsdatei: " + e.getMessage());
        }
    }

}
