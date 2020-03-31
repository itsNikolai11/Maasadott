package no.nkopperudmoen.måsadott.commands.stab;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.events.PlayerManager;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Mute implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.MUTE)) {
                if (args.length == 0) {
                    return false;
                }
                Player t = Bukkit.getPlayer(args[0]);
                if (t == null) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.PAYER_NOT_FOUND.replaceAll("%spiller%", args[0])));
                }
                toggleMute(t, p);
                return true;
            }
        }
        return false;
    }

    void toggleMute(Player p, Player m) {
        Rover r = PlayerController.getPlayer(p);
        if (PlayerManager.muted.contains(p.getUniqueId())) {
            PlayerManager.muted.remove(p.getUniqueId());
            p.sendMessage(Messages.UNMUTED.replaceAll("%target%", "Du"));
            for (Player t : Bukkit.getOnlinePlayers()) {
                if (t.hasPermission(Permissions.MUTE)) {
                    t.sendMessage(Messages.UNMUTED.replaceAll("%target%", p.getName()));
                }
            }
        } else {
            PlayerManager.muted.add(p.getUniqueId());
            p.sendMessage(Messages.MUTED.replaceAll("%target%", "Du").replaceAll("%spiller%", " en stab"));
            for (Player t : Bukkit.getOnlinePlayers()) {
                if (t.hasPermission(Permissions.MUTE)) {
                    t.sendMessage(Messages.MUTED.replaceAll("%target%", p.getName()).replaceAll("%spiller%", m.getName()));
                }
            }
        }
    }
}
