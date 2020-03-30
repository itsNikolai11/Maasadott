package no.nkopperudmoen.måsadott.commands.stab;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.*;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Unban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.BAN)) {
                if (args.length == 0) {
                    return false;
                }
                unbanPlayer(args[0], p.getName());
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
            }
            return true;
        } else {
            if (args.length == 0) {
                return false;
            }
            unbanPlayer(args[0], "CONSOLE");
            return true;
        }
    }

    static void unbanPlayer(String unbanned, String unbanner) {
        UUID uuid = UUIDFetcher.getUUID(unbanned);
        TemporaryBan.removeBan(uuid);
        Debugger.debug("UNBANNET SPILLEREN " + unbanned + " MED UUID " + uuid);
        for (Player t : Bukkit.getOnlinePlayers()) {
            if (t.hasPermission(Permissions.BAN)) {
                t.sendMessage(Messages.Unban.replaceAll("%target%", unbanned).replaceAll("%spiller%", unbanner));
            }
        }
    }
}
