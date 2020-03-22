package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteHome implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (args.length == 0) {
                return false;
            } else {
                PlayerController.getPlayer(p).removeHome(args[0]);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.PREFIX + Messages.DELETE_HOME.replaceAll("%home%", args[0])));
                return true;
            }
        }
        return false;
    }
}
