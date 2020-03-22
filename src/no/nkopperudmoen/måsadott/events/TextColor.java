package no.nkopperudmoen.måsadott.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class TextColor implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("m.chatcolor")) {
            String msg = ChatColor.translateAlternateColorCodes('&', e.getMessage());
            e.setMessage(msg);
        }
    }

    @EventHandler
    public void onpPlayerSign(SignChangeEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("m.signcolor")) {
            for (int i = 0; i < 4; i++) {
                String msg = ChatColor.translateAlternateColorCodes('&', e.getLine(i));
                e.setLine(i, msg);
            }
        }
    }
}
