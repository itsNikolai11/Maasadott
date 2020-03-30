package no.nkopperudmoen.måsadott.commands.stab;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StabChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.STABCHAT)) {
                if (args.length > 0) {
                    String msg = "";
                    for (int i = 0; i < args.length; i++) {
                        msg += args[i] + " ";
                    }
                    for (Player t : Bukkit.getOnlinePlayers()) {
                        if (t.hasPermission(Permissions.STABCHAT)) {
                            t.sendMessage(Messages.STABCHAT.replaceAll("%spiller%", p.getName()).replaceAll("%msg%", msg));
                        }
                    }
                    return true;
                } else {
                    return false;
                }

            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
                return true;
            }
        }
        return false;
    }
}
