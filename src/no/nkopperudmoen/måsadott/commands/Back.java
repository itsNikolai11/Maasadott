package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Back implements CommandExecutor {
    private Main plugin;

    public Back(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.BACK)) {
                Rover po = Rover.getPlayerObject(p);
                if (po == null) {
                    po = PlayerController.createPlayer(p);
                }
                p.teleport(po.getLastLocation());
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING));
                return true;
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
                return true;
            }
        }
        return false;
    }
}
