package at.mtxframe.eridiumeconomy.commands;

import at.mtxframe.eridiumeconomy.EridiumEconomy;
import at.mtxframe.mtxframe.MtxFrame;
import at.mtxframe.mtxframe.database.DatabaseJobs;
import at.mtxframe.mtxframe.economy.BalanceHandler;
import at.mtxframe.mtxframe.messaging.MessageHandler;
import at.mtxframe.mtxframe.models.PlayerStatsModel;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.List;

public class PayCommand implements CommandExecutor{
    MessageHandler msgHandler = new MessageHandler();

    BalanceHandler balanceHandler = new BalanceHandler();
    EridiumEconomy plugin;

    public PayCommand(EridiumEconomy plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length < 2){
                msgHandler.economyMessage(player, "Du musst einen namen und Betrag angeben: /pay <Name> <Betrag>");
            } else if (!(args[0].equals(player.getName()))){
                Player receiver = Bukkit.getPlayerExact(args[0]);
                double payAmount = Double.valueOf(args[1]);
                    try {
                        balanceHandler.sendMoney(player,receiver,payAmount);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }

            } else {
                msgHandler.economyMessage(player,"Du kannst keine Münzen an dich selbst überweisen.");
            }

            return true;

        }


        return true;
    }


}
