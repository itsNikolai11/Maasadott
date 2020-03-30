package no.nkopperudmoen.måsadott.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.commands.stab.Mute;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MuteManager implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (Mute.muted.contains(e.getPlayer())) {
            e.setCancelled(true);
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "Du er mutet!"));
        }
    }
}
