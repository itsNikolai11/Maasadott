package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.permission.Permission;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Rank implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.RANK)) {
                if (args.length <= 1) {
                    return false;
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    String rank = args[1];
                    if (target == null) {
                        rankPlayer(target, rank);
                    } else {
                        rankPlayer(target, rank);
                        PlayerController.updateDisplayName(target);
                        Bukkit.broadcastMessage(Messages.ONTIME_RANKUP.replaceAll("%spiller%", target.getName()).replaceAll("%rank%", rank).replaceAll("%ranker%", p.getName()));
                    }
                }
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
            }
        } else {
            if (args.length <= 1) {
                return false;
            } else {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                String rank = args[1];
                rankPlayer(target, rank);
                PlayerController.updateDisplayName(target);
                Bukkit.broadcastMessage(Messages.ONTIME_RANKUP.replaceAll("%spiller%", target.getName()).replaceAll("%rank%", rank).replaceAll("%ranker%", "CONSOLE"));
            }
        }
        return true;
    }

    public static void rankPlayer(Player p, String rank) {
        Permission perm = Main.permission;
        String prevRank = perm.getPrimaryGroup(p);
        for (String s : perm.getPlayerGroups(p)) {
            perm.playerRemoveGroup(p, s);
        }
        PlayerController.getPlayer(p).setPrefix(Main.chat.getPlayerPrefix(p));
        PlayerController.updateDisplayName(p);
        perm.playerAddGroup(null, p, rank);

    }
}