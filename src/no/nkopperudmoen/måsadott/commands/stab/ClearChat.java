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

public class ClearChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.CLEARCHAT)) {
                for (Player t : Bukkit.getOnlinePlayers()) {
                    if (t.hasPermission(Permissions.CLEARCHAT)) {
                        t.sendMessage(Messages.CLEARCHAT.replaceAll("%spiller%", p.getName()));
                    } else {
                        for (int i = 0; i < 30; i++) {
                            t.sendMessage("");
                        }
                        t.sendMessage(Messages.CLEARCHAT.replaceAll("%spiller%", "en stab"));
                    }
                }
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));

            }
            return true;
        } else {
            for (Player t : Bukkit.getOnlinePlayers()) {
                if (t.hasPermission(Permissions.CLEARCHAT)) {
                    t.sendMessage(Messages.CLEARCHAT.replaceAll("%spiller%", "CONSOLE"));
                } else {
                    for (int i = 0; i < 50; i++) {
                        t.sendMessage("");
                    }
                    t.sendMessage(Messages.CLEARCHAT.replaceAll("%spiller%", "en stab"));
                }
            }
        }
        return false;
    }
}
