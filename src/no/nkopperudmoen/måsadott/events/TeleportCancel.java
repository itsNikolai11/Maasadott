package no.nkopperudmoen.måsadott.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TeleportCancel implements Listener {


    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Rover po = Rover.getPlayerObject(p);
        if (po == null) {
            PlayerController.createPlayer(p);
        }

        if (po.isTeleporting()) {
            if (e.getTo().distance(e.getFrom()) * 7 > 1) {
                po.setTeleporting(false);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.RED + "Teleportering avbrutt. Du bevegde deg!"));
            }
        } else {
            return;
        }
    }
}
