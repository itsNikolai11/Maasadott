package no.nkopperudmoen.måsadott.commands.stab;

import com.mojang.brigadier.arguments.StringArgumentType;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (sender.hasPermission(Permissions.KICK)) {
                if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.PAYER_NOT_FOUND.replaceAll("%spiller%", args[0])));
                        return true;
                    }
                    target.kickPlayer("Sparket ut av serveren");
                    return true;
                }
                if (args.length > 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    String kickMsg = "";
                    for (int i = 1; i < args.length; i++) {
                        kickMsg += args[i] + " ";
                    }
                    target.kickPlayer(kickMsg);
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        if (pl.hasPermission(Permissions.KICK)) {
                            pl.sendMessage(Messages.KICK.replaceAll("%spiller%", target.getName()).replaceAll("%kicker%", p.getName()).replaceAll("%grunn%", kickMsg));
                        }
                    }
                    return true;
                }
                if (args.length == 0) {
                    return false;
                }
            }
        } else {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                target.kickPlayer("Sparket ut av serveren");
                return true;
            }
            if (args.length > 1) {
                Player target = Bukkit.getPlayer(args[0]);
                String kickMsg = "";
                for (int i = 1; i < args.length; i++) {
                    kickMsg += args[i] + " ";
                }
                target.kickPlayer(kickMsg);
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    if (pl.hasPermission(Permissions.KICK)) {
                        pl.sendMessage(Messages.KICK.replaceAll("%spiller%", target.getName()).replaceAll("%kicker%", "CONSOLE").replaceAll("%grunn%", kickMsg));
                    }
                }
                return true;
            }

        }
        return false;
    }
}
