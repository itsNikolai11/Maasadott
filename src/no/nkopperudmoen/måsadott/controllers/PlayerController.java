package no.nkopperudmoen.måsadott.controllers;

import net.milkbowl.vault.chat.Chat;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.filbehandling.PlayerFileReader;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerController {
    public static HashMap<UUID, Rover> players = new HashMap<>();
    private static Chat chat = Main.chat;
    public static HashMap<UUID, Integer> ontimeTasks = new HashMap<>();
    public static HashMap<UUID, Integer> afkTasks = new HashMap<>();

    public static Rover getPlayer(Player p) {
        return PlayerController.players.get(p.getUniqueId());
    }

    public static Rover createPlayer(Player p) {
        Rover po = new Rover(p.getUniqueId());
        PlayerFileReader reader = new PlayerFileReader();
        try {
            reader.loadPlayer(po);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            p.kickPlayer("Feil under innlasting av spillerdata! Vi har blitt varslet og er på saken.");
        }
        PlayerController.players.put(p.getUniqueId(), po);
        po.setOnline(true);
        po.checkAfk(p, Main.getPlugin(no.nkopperudmoen.måsadott.Main.class));
        po.ontimeTimer(p, Main.getPlugin(no.nkopperudmoen.måsadott.Main.class));
        updateDisplayName(p);
        return po;
    }

    public static String getPrefixColor(Player p) {
        String color;
        String prefix = Main.chat.getPlayerPrefix(p);
        if (prefix.length() < 1) {
            color = "&f";
        } else {
            color = "&" + prefix.charAt(1);
        }
        return color;
    }

    public static void updateDisplayName(Player p) {
        p.setDisplayName(ChatColor.translateAlternateColorCodes('&', getPrefixColor(p) + p.getName()));
        p.setPlayerListName(p.getDisplayName());


    }
}
