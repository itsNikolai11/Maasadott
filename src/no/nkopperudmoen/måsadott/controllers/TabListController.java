package no.nkopperudmoen.måsadott.controllers;

import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TabListController implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        p.setPlayerListHeader(Messages.TABLIST_HEADER);
        p.setPlayerListName(p.getDisplayName());
        p.setPlayerListFooter(Messages.TABLIST_FOOTER.replaceAll("%online%", Bukkit.getServer().getOnlinePlayers().size()
                - PlayerController.vanishedPlayers.size() + "").replaceAll("%max%", Bukkit.getServer().getMaxPlayers() + ""));
    }
}
