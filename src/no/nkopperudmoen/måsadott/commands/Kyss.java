package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Kyss implements CommandExecutor {
    static ArrayList<Player> cooldown = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                return false;
            } else {
                Player t = Bukkit.getPlayer(args[0]);
                if (t == p) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.KYSS_SELV));
                    return true;
                }
                if (t == null) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.PAYER_NOT_FOUND.replaceAll("%spiller%", args[0])));
                    return true;
                }
                if (cooldown.contains(p)) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.COOLDOWN));
                    return true;
                }
                Bukkit.broadcastMessage(Messages.KYSS.replaceAll("%spiller%", p.getName()).replaceAll("%target%", t.getName()));
                t.playSound(p.getEyeLocation(), Sound.ENTITY_CAT_PURREOW, 1, 1);
                cooldown.add(p);
                Bukkit.getScheduler().runTaskLater(Messages.plugin, () -> cooldown.remove(p), 300 * 20);

            }
            return true;
        } else {
            return false;
        }
    }
}
