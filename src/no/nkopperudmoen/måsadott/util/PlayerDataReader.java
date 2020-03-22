package no.nkopperudmoen.måsadott.util;

import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class PlayerDataReader {
    //TODO Endre filtype til binær fil.
    private Main plugin;

    public PlayerDataReader(Main plugin) {
        this.plugin = plugin;
    }

    public File loadConfigFile(Player p) {
        Rover po = PlayerController.getPlayer(p);
        File configFile = new File(plugin.getDataFolder() + "\\Players\\", po.getUuid() + ".yml");
        try {
            if (configFile.createNewFile()) {
                System.out.println("Opprettet konfigurasjonsfil for " + p.getUniqueId() + "(" + p.getName() + ")");
                FileConfiguration fc = new YamlConfiguration();
                fc.set("ontime.min", 0);
                fc.set("ontime.time", 0);
                fc.set("ontime.dag", 0);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return configFile;
    }

    public void writeToFile(Player p, String option, String value) {
        File configFile = this.loadConfigFile(p);
        try {
            if (configFile.createNewFile()) {
            }
            FileConfiguration fc = new YamlConfiguration();
            fc.load(configFile);
            fc.set(option, value);
            fc.save(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            System.err.println("Kunne ikke skrive til fil");
        }
    }

    public void writeToFile(Player p, String option, int value) {
        File configFile = this.loadConfigFile(p);
        try {
            if (configFile.createNewFile()) {
            }
            FileConfiguration fc = new YamlConfiguration();
            fc.load(configFile);
            fc.set(option, value);
            fc.save(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            System.err.println("Kunne ikke skrive til fil");
        }
    }

    public void writeToFile(Player p, String option, double value) {
        File configFile = this.loadConfigFile(p);
        try {
            if (configFile.createNewFile()) {
            }
            FileConfiguration fc = new YamlConfiguration();
            fc.load(configFile);
            fc.set(option, value);
            fc.save(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            System.err.println("Kunne ikke skrive til fil");
        }
    }

    public FileConfiguration getFileConfig(Player p) {
        FileConfiguration fc = new YamlConfiguration();
        try {
            fc.load(this.loadConfigFile(p));
        } catch (IOException | InvalidConfigurationException e) {
            System.err.println("UGYLDIG CONFIG");
        }
        return fc;
    }
}
