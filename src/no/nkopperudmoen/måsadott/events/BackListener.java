package no.nkopperudmoen.måsadott.events;

import no.nkopperudmoen.måsadott.Rover.Rover;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class BackListener implements Listener {
    @EventHandler
    public void onTeleport(PlayerTeleportEvent e) {
        Rover po = Rover.getPlayerObject(e.getPlayer());
        try {
            po.setLastLocation(e.getFrom());
        } catch (NullPointerException exc) {

        }
    }
}
