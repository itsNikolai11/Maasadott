package no.nkopperudmoen.måsadott.commands;

import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.events.VanishManager;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PrivateMessageReply implements CommandExecutor {
    private Main plugin;

    public PrivateMessageReply(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        String noReply = Messages.PREFIX + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PrivateMessageNoReply"));
        String noArgs = Messages.PREFIX + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PrivateMessageNoArgs"));
        String msg = "";
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {
                Player replier = PlayerController.getPlayer(p).getReplier();
                if (replier == null || VanishManager.vanishedPlayers.contains(replier)) {
                    p.sendMessage(noReply);
                } else {
                    Player target = PlayerController.getPlayer(p).getReplier();
                    for (int i = 0; i < args.length; i++) {
                        msg += args[i] + " ";
                    }

                    String pm = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("PrivateMessageFormat"));
                    String socialspy = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("SocialSpy"));
                    socialspy = socialspy.replaceAll("%sender%", p.getName());
                    socialspy = socialspy.replaceAll("%receiver%", target.getName());
                    socialspy = socialspy.replaceAll("%msg%", msg);
                    for (Player stab : Bukkit.getServer().getOnlinePlayers()) {
                        if (stab.hasPermission(Permissions.SOCIALSPY) && stab != p) {
                            stab.sendMessage(socialspy);
                        }
                    }

                    pm = pm.replaceAll("%sender%", p.getName());
                    pm = pm.replaceAll("%msg%", msg);
                    pm = pm.replaceAll("%receiver%", target.getName());

                    target.sendMessage(pm);
                    p.sendMessage(pm);
                    return true;
                }

            } else {
                p.sendMessage(noArgs);
                return true;

            }

        }

        return false;
    }
}
