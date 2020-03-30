package no.nkopperudmoen.måsadott.commands.stab;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class GameMode implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.GAMEMODE)) {
                if (args.length < 1) {
                    return false;
                }
                switch (args[0]) {
                    case "1":
                        p.setGameMode(org.bukkit.GameMode.CREATIVE);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.GAMEMODE.replaceAll("%gm%", "Kreativ")));
                        break;
                    case "2":
                        p.setGameMode(org.bukkit.GameMode.ADVENTURE);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.GAMEMODE.replaceAll("%gm%", "Adventure")));

                        break;
                    case "3":
                        p.setGameMode(org.bukkit.GameMode.SPECTATOR);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.GAMEMODE.replaceAll("%gm%", "Spectator")));

                        break;
                    case "0":
                        p.setGameMode(org.bukkit.GameMode.SURVIVAL);
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.GAMEMODE.replaceAll("%gm%", "Survival")));

                        break;
                }
                return true;
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
            }
        }
        return false;
    }
}
