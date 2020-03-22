package no.nkopperudmoen.måsadott.commands;

import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrivateMessage implements CommandExecutor {
    private Main plugin;

    public PrivateMessage(Main plugin) {
        this.plugin = plugin;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        String msg = "";
        String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + " ");
        String noArgs = prefix + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PrivateMessageNoArgs"));
        String noPlayer = prefix + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PlayerNotFound"));
        if (sender instanceof Player) {
            Player p = (Player) sender;

            if (args.length > 1) {
                if (Bukkit.getServer().getPlayer(args[0]) == null) {
                    noPlayer = noPlayer.replaceAll("%spiller%", args[0]);
                    p.sendMessage(noPlayer);
                    return false;
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    for (int i = 1; i < args.length; i++) {
                        msg += args[i] + " ";
                    }
                    String socialspy = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("SocialSpy"));
                    socialspy = socialspy.replaceAll("%sender%", p.getName());
                    socialspy = socialspy.replaceAll("%receiver%", target.getName());
                    socialspy = socialspy.replaceAll("%msg%", msg);
                    for (Player stab : Bukkit.getServer().getOnlinePlayers()) {
                        if (stab.hasPermission(Permissions.SOCIALSPY) && stab != p) {
                            stab.sendMessage(socialspy);
                        }
                    }
                    String pm = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PrivateMessageFormat"));
                    pm = pm.replaceAll("%sender%", p.getName());
                    pm = pm.replaceAll("%msg%", msg);
                    pm = pm.replaceAll("%receiver%", target.getName());


                    target.sendMessage(pm);
                    pm = pm.replaceAll("%receiver%", target.getName());

                    p.sendMessage(pm);

                    PlayerController.getPlayer(p).setReplier(target);
                    PlayerController.getPlayer(target).setReplier(p);
                    return true;
                }

            } else {
                p.sendMessage(noArgs);

            }

        }

        return false;
    }
}
