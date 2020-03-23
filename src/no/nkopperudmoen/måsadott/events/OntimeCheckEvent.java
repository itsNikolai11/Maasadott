package no.nkopperudmoen.måsadott.events;

import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.util.PlayerDataReader;
import no.nkopperudmoen.måsadott.util.RankFileReader;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class OntimeCheckEvent {
    private static Main plugin = Main.getPlugin(no.nkopperudmoen.måsadott.Main.class);

    public static void ontimeChangeEvent(Player p) {
        RankFileReader rf = new RankFileReader();
        PlayerDataReader reader = new PlayerDataReader(plugin);
        int playerTimeTotal = reader.getFileConfig(p).getInt("ontime");
        if ((rf.getRankupTime(p)) <= playerTimeTotal) {
            String prevGroup = Main.permission.getPrimaryGroup(p);
            if (rf.getNextRank(p) == null) {

            } else {
                Bukkit.broadcastMessage(Messages.ONTIME_RANKUP.replaceAll("%spiller%", p.getName()).replaceAll("%rank%", rf.getNextRank(p)));
                PlayerController.updateDisplayName(p);
                Main.permission.playerRemoveGroup(null, p, prevGroup);
                Main.permission.playerAddGroup(null, p, rf.getNextRank(p));

            }
        }
    }
}
