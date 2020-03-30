package no.nkopperudmoen.måsadott.commands.stab;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Mute implements CommandExecutor {
    public static ArrayList<Player> muted = new ArrayList<>();

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
                toggleMute(t);
            }
        }

        return false;
    }

    void toggleMute(Player p) {
        if (muted.contains(p)) {
            muted.remove(p);
            //melding om unmute
            for(Player t:Bukkit.getOnlinePlayers()){
                if(t.hasPermission(Permissions.MUTE)){
                    t.sendMessage("");
                }
            }
        } else {
            muted.add(p);
            //melding om mute
            for(Player t:Bukkit.getOnlinePlayers()){
                if(t.hasPermission(Permissions.MUTE)){
                    t.sendMessage("");
                }
            }
        }
    }
}
