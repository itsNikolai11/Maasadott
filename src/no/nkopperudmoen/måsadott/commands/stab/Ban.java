package no.nkopperudmoen.måsadott.commands.stab;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Ban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.BAN)) {
                if (args.length == 0) {
                    return false;
                }
                if (args.length == 1) {

                    Player t = Bukkit.getPlayer(args[0]);
                    if (t != null) {
                        banOnlinePlayer(t, "Ingen grunn oppgitt", p.getName());
                    } else {
                        banOfflinePlayer(args[0], "Ingen grunn oppgitt", p.getName());
                    }
                    return true;
                }
                String msg = "";
                for (int i = 1; i < args.length; i++) {
                    msg += args[i] + " ";
                }
                Player t = Bukkit.getPlayer(args[0]);
                if (t != null) {
                    banOnlinePlayer(t, msg, p.getName());
                } else {
                    banOfflinePlayer(args[0], msg, p.getName());
                }
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
            }

        } else {
            if (args.length == 0) {
                return false;
            }
            if (args.length == 1) {

                Player t = Bukkit.getPlayer(args[0]);
                if (t != null) {
                    banOnlinePlayer(t, "Ingen grunn oppgitt", "CONSOLE");
                } else {
                    banOfflinePlayer(args[0], "Ingen grunn oppgitt", "CONSOLE");
                }
                return true;
            }
            String msg = "";
            for (int i = 1; i < args.length; i++) {
                msg += args[i] + " ";
            }
            Player t = Bukkit.getPlayer(args[0]);
            if (t != null) {
                banOnlinePlayer(t, msg, "CONSOLE");
            } else {
                banOfflinePlayer(args[0], msg, "CONSOLE");
            }
        }
        return true;
    }

    public static void banOnlinePlayer(Player banned, String reason, String banner) {
        TemporaryBan ban = new TemporaryBan(banned.getUniqueId(), reason, 0);
        TemporaryBan.addBan(banned.getUniqueId(), ban);
        banned.kickPlayer("Du har blitt bannet for:\n" + reason);
        for (Player stab : Bukkit.getOnlinePlayers()) {
            stab.sendMessage(Messages.BAN.replaceAll("%target%", banned.getName()).replaceAll("%spiller%", banner).replaceAll("%msg%", reason));
            Debugger.debug("BANNET SPILLEREN " + banned.getName() + " MED UUID " + banned.getUniqueId());
        }
    }

    public static void banOfflinePlayer(String banned, String reason, String banner) {
        UUID uuid = UUIDFetcher.getUUID(banned);
        TemporaryBan ban = new TemporaryBan(uuid, reason, 0);
        TemporaryBan.addBan(uuid, ban);
        Debugger.debug("BANNET SPILLEREN " + Bukkit.getPlayer(uuid).getName() + " MED UUID " + uuid);
    }
}
