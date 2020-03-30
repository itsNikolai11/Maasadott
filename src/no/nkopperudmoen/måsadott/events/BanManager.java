package no.nkopperudmoen.måsadott.events;

import no.nkopperudmoen.måsadott.filbehandling.BanFileSaver;
import no.nkopperudmoen.måsadott.util.TemporaryBan;
import org.bukkit.entity.Player;

import java.io.IOException;

public class BanManager {

    public static boolean isBanned(Player p) {
        if (TemporaryBan.tempbans.get(p.getUniqueId()) == null) {
            return false;
        }
        if (TemporaryBan.tempbans.get(p.getUniqueId()).getBanDuration() == 0) {
            return true;
        }
        if (TemporaryBan.tempbans.get(p.getUniqueId()).getExpiration() > System.currentTimeMillis()) {
            return true;
        }
        if (TemporaryBan.tempbans.get(p.getUniqueId()).getExpiration() <= System.currentTimeMillis()) {
            TemporaryBan.tempbans.remove(p.getUniqueId());
            BanFileSaver saver = new BanFileSaver();
            try {
                saver.saveBans();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;
    }
}
