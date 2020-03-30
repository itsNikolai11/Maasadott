package no.nkopperudmoen.måsadott.events;

import no.nkopperudmoen.måsadott.Rover.Rover;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AfkCheck implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Rover po = Rover.getPlayerObject(e.getPlayer());
        if (po.isAfk()) {
            if (e.getTo().distance(e.getFrom()) * 7 > 1) {
                po.setAfk(false, e.getPlayer());
            }
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Rover po = Rover.getPlayerObject(e.getPlayer());
        if (po.isAfk()) {
            po.setAfk(false, e.getPlayer());
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandSendEvent e) {
        Rover po = Rover.getPlayerObject(e.getPlayer());
        try {
            if (po.isAfk()) {
                po.setAfk(false, e.getPlayer());
            }
        } catch (NullPointerException exc) {

        }
    }

    public static boolean checkAfk(Player p) {
        Location afk = Rover.getPlayerObject(p).getAfkLocation();
        Location player = p.getLocation();
        if (afk.getX() == player.getX() && afk.getY() == player.getY() && afk.getZ() == player.getZ()) {
            return true;
        } else {
            return false;
        }

    }
}
