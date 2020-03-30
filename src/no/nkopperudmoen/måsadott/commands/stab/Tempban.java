package no.nkopperudmoen.måsadott.commands.stab;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.UUID;

public class Tempban implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.BAN)) {
                if (args.length == 1) {
                    //Spesifiser tid
                    return false;
                }
                long time;
                Player t = Bukkit.getPlayer(args[0]);
                if (args.length == 2) {
                    switch (args[1].charAt(args[1].length() - 1)) {
                        case 'd':
                            time = Long.parseLong(args[1].replaceAll("d", ""));
                            time = time * 86400000;
                            break;
                        case 't':
                            time = Long.parseLong(args[1].replaceAll("t", ""));
                            time = time * 3600000;
                            break;
                        case 'm':
                            time = Long.parseLong(args[1].replaceAll("m", ""));
                            time = time * 60000;
                            break;
                        default:
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(args[1] + " er ikke en gyldig tid"));
                            return true;
                    }
                    if (t != null) {
                        banOnlinePlayer(p.getName(), t, "Ingen grunn oppgitt", time);
                    } else {
                        banOfflinePlayer(args[0], "Ingen grunn oppgitt", p.getName(), time);
                    }
                    return true;
                }
                String reason = "";
                for (int i = 2; i < args.length; i++) {
                    reason += args[i] + " ";
                }
                switch (args[1].charAt(args[1].length() - 1)) {
                    case 'd':
                        time = Long.parseLong(args[1].replaceAll("d", ""));
                        time = time * 86400000;
                        break;
                    case 't':
                        time = Long.parseLong(args[1].replaceAll("t", ""));
                        time = time * 3600000;
                        break;
                    case 'm':
                        time = Long.parseLong(args[1].replaceAll("m", ""));
                        time = time * 60000;
                        break;
                    default:
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(args[1] + " er ikke en gyldig tid"));
                        return true;
                }
                if (t != null) {
                    banOnlinePlayer(p.getName(), t, reason, time);
                } else {
                    banOfflinePlayer(args[0], reason, p.getName(), time);
                }
                return true;
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
            }
        } else {
            if (args.length == 1) {
                //Spesifiser tid
                return false;
            }
            long time;
            Player t = Bukkit.getPlayer(args[0]);
            if (args.length == 2) {
                switch (args[1].charAt(args[1].length() - 1)) {
                    case 'd':
                        time = Long.parseLong(args[1].replaceAll("d", ""));
                        time = time * 86400000;
                        break;
                    case 't':
                        time = Long.parseLong(args[1].replaceAll("t", ""));
                        time = time * 3600000;
                        break;
                    case 'm':
                        time = Long.parseLong(args[1].replaceAll("m", ""));
                        time = time * 60000;
                        break;
                    default:
                        System.out.println(args[1] + " er ikke en gyldig tid");
                        return true;
                }
                if (t != null) {
                    banOnlinePlayer("CONSOLE", t, "Ingen grunn oppgitt", time);
                } else {
                    banOfflinePlayer(args[0], "Ingen grunn oppgitt", "CONSOLE", time);
                }
                return true;
            }
            String reason = "";
            for (int i = 2; i < args.length; i++) {
                reason += args[i] + " ";
            }
            switch (args[1].charAt(args[1].length() - 1)) {
                case 'd':
                    time = Long.parseLong(args[1].replaceAll("d", ""));
                    time = time * 86400000;
                    break;
                case 't':
                    time = Long.parseLong(args[1].replaceAll("t", ""));
                    time = time * 3600000;
                    break;
                case 'm':
                    time = Long.parseLong(args[1].replaceAll("m", ""));
                    time = time * 60000;
                    break;
                default:
                    System.out.println(args[1] + " er ikke en gyldig tid");
                    return true;
            }
            if (t != null) {
                banOnlinePlayer("CONSOLE", t, reason, time);
            } else {
                banOfflinePlayer(args[0], reason, "CONSOLE", time);
            }
            return true;
        }
        return false;
    }

    static void banOnlinePlayer(String banner, Player banned, String reason, long duration) {

        TemporaryBan ban = new TemporaryBan(banned.getUniqueId(), reason, duration);
        Debugger.debug("BANNING " + banned.getName() + " UNTIL " + new Date(System.currentTimeMillis() + duration).toString());
        TemporaryBan.addBan(banned.getUniqueId(), ban);
        banned.kickPlayer("Du har blitt bannet:\n " + reason);
        for (Player t : Bukkit.getOnlinePlayers()) {
            if (t.hasPermission(Permissions.BAN)) {
                t.sendMessage(Messages.TEMPBAN.replaceAll("%spiller%", banner).replaceAll("%target%", banned.getName()).replaceAll("%msg%", reason).replaceAll("%time%", TimeConverter.toString(System.currentTimeMillis() + duration)));
            }
        }
    }

    static void banOfflinePlayer(String banned, String reason, String banner, long duration) {
        UUID uuid = UUIDFetcher.getUUID(banned);
        TemporaryBan ban = new TemporaryBan(uuid, reason, duration);
        TemporaryBan.addBan(uuid, ban);
        Debugger.debug("BANNET SPILLEREN " + banned + " MED UUID " + uuid + " TIL " + TimeConverter.toString(System.currentTimeMillis() + duration));
        for (Player t : Bukkit.getOnlinePlayers()) {
            if (t.hasPermission(Permissions.BAN)) {
                t.sendMessage(Messages.TEMPBAN.replaceAll("%spiller%", banner).replaceAll("%target%", banned).replaceAll("%msg%", reason).replaceAll("%time%", TimeConverter.toString(System.currentTimeMillis() + duration)));
            }
        }
    }
}
