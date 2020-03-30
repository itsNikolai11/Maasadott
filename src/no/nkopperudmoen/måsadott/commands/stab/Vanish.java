package no.nkopperudmoen.måsadott.commands.stab;

import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.controllers.TabListController;
import no.nkopperudmoen.måsadott.events.VanishManager;
import no.nkopperudmoen.måsadott.util.Debugger;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("[StaffAssister] Only players may perform this command");
            return false;
        }
        Player p = (Player) commandSender;

        if (p.hasPermission("staffassister.vanish")) {
            if (args.length > 0) {
                Player t = Bukkit.getServer().getPlayer(args[0]);
                if (p.hasPermission(Permissions.VANISH)) {
                    if (t == null) {
                        //TODO feilmelding - ikke funnet
                    } else {
                        toggleVanish(t);
                    }
                } else {
                    p.sendMessage("perm");
                }
            } else {
                toggleVanish(p);
            }
        } else {
            p.sendMessage("perm");
        }
        return false;
    }

    public void toggleVanish(Player p) {
        if (VanishManager.vanishedPlayers.contains(p)) {
            VanishManager.vanishedPlayers.remove(p);
            p.setInvulnerable(false);
            p.sendMessage(Messages.DISABLE_VANISH);
            PlayerController.updateDisplayName(p);
            int onlineCount = (Bukkit.getOnlinePlayers().size() - VanishManager.vanishedPlayers.size());
            Debugger.debug("ONLINE PLAYERS " + onlineCount);
            for (Player t : Bukkit.getServer().getOnlinePlayers()) {
                TabListController.updateTablist();
                if (t.hasPermission(Permissions.VANISH)) {
                    Debugger.debug(t.getName() + " ALREADY SHOWING " + p.getName());
                    return;
                } else {
                    t.showPlayer(Messages.plugin, p);
                    Debugger.debug("SHOWING " + p.getName() + " TO " + t.getName());
                }
            }


        } else {
            VanishManager.vanishedPlayers.add(p);
            p.setAllowFlight(true);
            p.setInvulnerable(true);
            p.setDisplayName(ChatColor.WHITE + p.getName());
            for (Player t : Bukkit.getServer().getOnlinePlayers()) {
                TabListController.updateTablist();
                if (t.hasPermission(Permissions.VANISH)) {
                    Debugger.debug("DIDN'T HIDE " + p.getName() + " FROM " + t.getName());
                } else {
                    t.hidePlayer(Messages.plugin, p);
                    Debugger.debug("HIDING " + p.getName() + " FROM " + t.getName());
                }
            }
            p.sendMessage(Messages.ENABLE_VANISH);

        }
    }
}
