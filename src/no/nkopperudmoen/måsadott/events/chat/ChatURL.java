package no.nkopperudmoen.måsadott.events.chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatURL implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {


    }

    public static boolean isLink(String string) {

        return false;
    }
}
