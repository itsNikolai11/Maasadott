package no.nkopperudmoen.måsadott.events;

import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.filbehandling.PlayerFileSaver;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.PlayerDataReader;
import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.IOException;

public class PlayerObjectListener implements Listener {
    private Main plugin;

    public PlayerObjectListener(Main plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {


        Player p = e.getPlayer();

        Rover po = PlayerController.createPlayer(e.getPlayer());
        //po.checkAfk(p, plugin);
        System.out.println("Player created");
        PlayerDataReader pfo = new PlayerDataReader(plugin);
        pfo.writeToFile(p, "lastName", p.getName());
        pfo.writeToFile(p, "lastIP", p.getAddress().getAddress().toString());
        //po.ontimeTimer(p, plugin, pfo);
        e.setJoinMessage(Messages.CONNECT.replaceAll("%spiller%", ChatColor.translateAlternateColorCodes('&', Main.chat.getPlayerPrefix(p)) + " " + p.getName()));
        PlayerFileSaver fs = new PlayerFileSaver();
        try {
            fs.savePlayerFile(po);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        if (!p.hasPlayedBefore()) {
            World world = Bukkit.getServer().getWorld(plugin.getConfig().getString("Spawn.world"));
            double x = Double.parseDouble(plugin.getConfig().getString("Spawn.x"));
            double y = Double.parseDouble(plugin.getConfig().getString("Spawn.y"));
            double z = Double.parseDouble(plugin.getConfig().getString("Spawn.z"));
            float yaw = Float.parseFloat((String) plugin.getConfig().get("Spawn.yaw"));
            float pitch = Float.parseFloat((String) plugin.getConfig().get("Spawn.pitch"));
            Location spawn = new Location(world, x, y, z, yaw, pitch);
            p.teleport(spawn);
        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Rover.getPlayerObject(e.getPlayer()).setOnline(false);
        Bukkit.getScheduler().cancelTask(PlayerController.afkTasks.get(e.getPlayer().getUniqueId()));
        Bukkit.getScheduler().cancelTask(PlayerController.ontimeTasks.get(e.getPlayer().getUniqueId()));
        PlayerController.ontimeTasks.remove(e.getPlayer().getUniqueId());
        PlayerController.afkTasks.remove(e.getPlayer().getUniqueId());
        PlayerController.onlinePlayers.remove(Rover.getPlayerObject(e.getPlayer()));
        e.setQuitMessage(Messages.DISCONNECT.replaceAll("%spiller%", ChatColor.translateAlternateColorCodes('&', Main.chat.getPlayerPrefix(e.getPlayer())) + " " + e.getPlayer().getName()));

    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        FileConfiguration config = plugin.getConfig();
        try {
            World world = Bukkit.getServer().getWorld(config.getString("Spawn.world"));
            double x = Double.parseDouble(config.getString("Spawn.x"));
            double y = Double.parseDouble(config.getString("Spawn.y"));
            double z = Double.parseDouble(config.getString("Spawn.z"));
            float yaw = Float.parseFloat((String) config.get("Spawn.yaw"));
            float pitch = Float.parseFloat((String) config.get("Spawn.pitch"));
            Location spawn = new Location(world, x, y, z, yaw, pitch);
            if (!e.isBedSpawn()) {
                e.setRespawnLocation(spawn);
            }
        } catch (ClassCastException exc) {
            e.setRespawnLocation(p.getWorld().getSpawnLocation());
        }
    }

    @EventHandler
    public void onPlayerAdvance(PlayerAdvancementDoneEvent e) {

    }
}
