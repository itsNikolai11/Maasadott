package no.nkopperudmoen.måsadott.events;

import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.util.RankFileReader;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OntimeCheckEvent {
    private static Main plugin = Main.getPlugin(no.nkopperudmoen.måsadott.Main.class);

    public static void ontimeChangeEvent(Player p) {
        RankFileReader rf = new RankFileReader();
        int playerTimeTotal = PlayerController.getPlayer(p).getOntime().getMin() / 60;
        if ((rf.getRankupTime(p)) <= playerTimeTotal) {
            String nextRank = rf.getNextRank(p);
            String prevGroup = Main.permission.getPrimaryGroup(p);
            if (!(nextRank == null)) {
                Bukkit.broadcastMessage(Messages.ONTIME_RANKUP.replaceAll("%spiller%", p.getName()).replaceAll("%rank%", nextRank));
                PlayerController.updateDisplayName(p);
                Main.permission.playerRemoveGroup(null, p, prevGroup);
                Main.permission.playerAddGroup(null, p, nextRank);

            }
        }
    }
}
