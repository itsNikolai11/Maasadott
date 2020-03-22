package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Warning;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        Player p = (Player) sender;
        if (p.hasPermission("")) {
            Warning warning = new Warning(p.getName(), strings[0]);
            PlayerController.getPlayer(Bukkit.getPlayer(strings[0])).warnings.add(warning);
        } else {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
        }
        return false;
    }
}
