package no.nkopperudmoen.måsadott.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class URLSpotter implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        String msg = e.getMessage();
        if (msg.matches("[a-zøæåA-ZÆØÅ][\\.][a-zøæåA-ZÆØÅ][\\.][a-zøæåA-ZÆØÅ]{1,3}")){
            System.out.println("LINK POSTED IN CHAT");
        }
    }
}
