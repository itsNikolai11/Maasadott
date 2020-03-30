package no.nkopperudmoen.måsadott.events;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import net.minecraft.server.v1_15_R1.SoundEffect;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatFormat implements Listener {
    public Chat chat = Main.chat;
    public Permission perm = Main.permission;

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        p.setPlayerListName(p.getDisplayName());
        //TODO hente spilelrrank og farge - sette farge til displayname
        for (Player t : Bukkit.getServer().getOnlinePlayers()) {
            if (e.getMessage().contains(t.getName())) {
                t.playSound(p.getEyeLocation(), Sound.ENTITY_EGG_THROW, 1, 1);

            }
            e.setMessage(e.getMessage().replaceAll(t.getName(), t.getDisplayName() + ChatColor.GRAY));
        }
        e.setFormat(formatChat(p, e.getMessage()));

    }

    public String formatChat(Player p, String msg) {
        msg = msg.replaceAll("%", "%%");
        msg = msg.replace("\\", "\\\\");
        msg = msg.replace("$", "\\$");
        String format = Messages.CHAT_FORMAT;
        format = format.replaceFirst("§prefix§", ChatColor.translateAlternateColorCodes('&', Main.chat.getPlayerPrefix(p) + " " + p.getName()));
        format = format.replaceFirst("§msg§", msg);

        return format;

    }


    public static boolean checkColor(String regex, String... strings) {
        for (String str : strings) {
            if (str.matches(regex)) {
                return true;
            }
        }
        return false;
    }
}
