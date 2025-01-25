package at.mtxframe.eridiumeconomy.backpack;

import at.mtxframe.eridiumeconomy.utils.MapPopulators;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.io.IOException;
import java.util.List;

public class BackPackSelector implements CommandExecutor {
    public Boolean isActiveBackPack = false;
    MapPopulators mapPopulators = new MapPopulators();
    BackPackGui backPackGui = new BackPackGui();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.isOp() || player.hasPermission("eridiumrpg.backpack")) {

                    PersistentDataContainer psd = player.getPersistentDataContainer();
                    String backPackKey = psd.get(BackPackUtils.backPackKey, PersistentDataType.STRING);
                    switch (backPackKey) {
                        case BackPackUtils.noActiveBackPack:
                            backPackGui.backPackSelector(player, mapPopulators.getSelectorItems());
                            player.sendMessage("Debug: BackPackSelector angefragt");
                            break;

                        case BackPackUtils.minerBackPack:
                            if (!psd.has(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING)){
                                psd.set(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
                            }
                            try {
                                backPackGui.openBackPack(player, mapPopulators.getBackpackMining());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            player.sendMessage("Debug: BackPackMiner angefragt");
                            break;

                        case BackPackUtils.farmerBackPack:
                            if (!psd.has(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING)){
                                psd.set(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
                            }
                            try {
                                backPackGui.openBackPack(player,mapPopulators.getBackpackFarming());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            player.sendMessage("DEBUG: BackPackFarmer angefragt");
                            break;

                        case BackPackUtils.fisherBackPack:
                            if (!psd.has(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING)){
                                psd.set(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
                            }
                            //TODO: Fischer entwerfen
                            player.sendMessage("Noch nicht vorhanden");
                            break;

                        case BackPackUtils.woodcutterBackPack:if (!psd.has(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING)){
                            psd.set(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
                        }

                            try {
                                backPackGui.openBackPack(player,mapPopulators.getBackpackWoodcutting());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (ClassNotFoundException e) {
                                throw new RuntimeException(e);
                            }
                            player.sendMessage("DEBUG: BackPackWoodcutter angefragt");
                            break;

                        case BackPackUtils.hunterBackPack:
                            if (!psd.has(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING)){
                                psd.set(BackPackUtils.getBackPackItemsKey(),PersistentDataType.STRING,BackPackUtils.getEmptyBackPack());
                            }
                            player.sendMessage("Noch nicht vorhanden");
                            break;

                        default:
                            break;
                    }

            }else {

                player.sendMessage("Keine permission (Persmission Messages programmieren)");
            }
        }
        return true;
    }



}





