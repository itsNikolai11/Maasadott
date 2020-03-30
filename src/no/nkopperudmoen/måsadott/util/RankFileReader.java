package no.nkopperudmoen.måsadott.util;

import net.milkbowl.vault.permission.Permission;
import no.nkopperudmoen.måsadott.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class RankFileReader {
    private Player p;
    Permission perm = Main.permission;
    public static FileConfiguration fc = new YamlConfiguration();
    private static Main plugin = Main.getPlugin(no.nkopperudmoen.måsadott.Main.class);

    public static void loadRankFile() {

        File file = new File(plugin.getDataFolder(), "rankup.yml");

        try {
            if (file.createNewFile()) {
                System.out.println("Rankup-fil ikke funnet. Oppretter..");
                fc.load(file);

            } else {
                fc.load(file);
            }
        } catch (InvalidConfigurationException e) {
            System.err.println("Ugyldig config!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getNextRank(Player p) {
        Debugger.debug("NESTE RANK FOR " + p.getName() + " ER " + fc.getString(perm.getPrimaryGroup(p).toLowerCase() + ".next"));
        return fc.getString(perm.getPrimaryGroup(p).toLowerCase() + ".next");
    }

    public int getRankupTime(Player p) {
        return fc.getInt(perm.getPrimaryGroup(p).toLowerCase() + ".time");
    }

}
