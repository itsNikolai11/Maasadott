package no.nkopperudmoen.måsadott.commands.economy;

import net.md_5.bungee.api.ChatMessageType;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Money implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 1) {
//perm for money others
            } else {
                double money = Main.economy.getBalance(p);
                String msg = Messages.MONEY;
                if (money == 1) {
                    msg = msg.replaceAll("%curr%", "Krage");
                } else {
                    msg = msg.replaceAll("%curr%", "Krager");
                }
                p.sendMessage(msg.replaceAll("%money%", money + ""));
            }
        }
        return false;
    }
}
