package no.nkopperudmoen.måsadott.Rover;

import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.events.AfkCheck;
import no.nkopperudmoen.måsadott.events.OntimeCheckEvent;
import no.nkopperudmoen.måsadott.exceptions.HomeNotFoundException;
import no.nkopperudmoen.måsadott.filbehandling.PlayerFileSaver;
import no.nkopperudmoen.måsadott.util.*;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class Rover {

    private Player replier;
    private Location lastLocation;
    private UUID uuid;
    private Location afkLocation;
    private boolean isAfk;
    private boolean isTeleporting;
    private Player tprequest;
    private Player tphRequest;
    private boolean isOnline;
    private String prefix;
    private PlayerTime ontime;

    public PlayerTime getOntime() {
        return ontime;
    }

    public void setOntime(PlayerTime ontime) {
        if (ontime == null) {
            this.ontime = new PlayerTime();
        } else {
            this.ontime = ontime;
        }
    }


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }


    public ArrayList<Home> homes = new ArrayList<>();
    public ArrayList<Warning> warnings = new ArrayList<>();

    public Rover(UUID uuid) {
        this.uuid = uuid;
    }

    public Player getReplier() {
        return replier;
    }

    public void setReplier(Player replier) {
        this.replier = replier;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(Location lastLocation) {
        this.lastLocation = lastLocation;
    }

    public void setTprequest(Player t) {
        this.tprequest = t;
    }

    public Player getTprequest() {
        return tprequest;
    }

    public void setTphRequest(Player t) {
        this.tphRequest = t;
    }

    public Player getTphRequest() {
        return tphRequest;
    }

    public UUID getUuid() {

        return uuid;
    }

    public void setAfkLocation(Player p) {
        afkLocation = p.getLocation();
    }

    public Location getAfkLocation() {
        return afkLocation;
    }

    public Home getHome(String name) throws HomeNotFoundException {
        Home home = null;
        for (Home h : homes) {
            if (h.getName().equalsIgnoreCase(name)) {
                home = h;
            }
        }
        if (home == null) {
            throw new HomeNotFoundException("Fant ikke hjemmet " + name);
        }
        return home;
    }

    public void addHome(Home home) {
        for (int i = 0; i < homes.size(); i++) {
            if (homes.get(i).getName().equals(home.getName())) {
                homes.remove(i);
            }
        }
        homes.add(home);
        saveHomes();
    }

    public void removeHome(String name) {
        for (int i = 0; i < homes.size(); i++) {
            if (homes.get(i).getName().equals(name)) {
                homes.remove(i);
            }
        }
    }

    public ArrayList<Home> getHomes() {
        return homes;
    }

    public String getHomeNames() {
        String homeList = "";
        for (int i = 0; i < homes.size(); i++) {
            if (i < homes.size() - 1) {
                homeList += homes.get(i).getName() + ", ";
            } else {
                homeList += homes.get(i).getName();
            }
        }
        if (homeList.isEmpty()) {
            return "Du har ingen hjem";
        }
        return homeList;
    }

    public void saveHomes() {
        PlayerFileSaver saver = new PlayerFileSaver();
        try {
            saver.savePlayerFile(this);
        } catch (FileNotFoundException e) {
            File playerFile = new File(Messages.plugin.getDataFolder() + "\\Players\\", uuid + ".jobj");
            playerFile.mkdirs();
            saveHomes();
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public String getWarnings() {
        String warnings = "";
        for (Warning w : this.warnings) {
            warnings += this.warnings.indexOf(w) + ": " + w.getText() + "\n";
        }
        if (warnings.isEmpty()) {
            return "Du har ingen advarsler";
        } else {
            return warnings;
        }
    }

    public void warn(Warning w) {
        warnings.add(w);
        if (warnings.size() == 3) {
            Bukkit.getPlayer(uuid).kickPlayer("Du har blitt bannet: Du har 3 advarsler");
            Bukkit.getBanList(BanList.Type.NAME).addBan(Bukkit.getPlayer(uuid).getName(), "Du har 3 advarsler", null, "Console");

        }
    }

    public void setAfk(boolean afk, Player p) {
        isAfk = afk;
        if (afk) {
            String playerIsAfk = Messages.playerIsAfk.replaceAll("%name%", p.getName());
            Bukkit.broadcastMessage(playerIsAfk);
            p.setPlayerListName(ChatColor.DARK_GRAY + p.getName());
            int kickTimeout = Messages.plugin.getConfig().getInt("AFKTimeout");
            if (kickTimeout == 0) {

            } else {
                Bukkit.getScheduler().runTaskLater(Messages.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (afk) {
                            p.kickPlayer("Du ble kicket for å være AFK i " + kickTimeout + " minutter");
                        }
                    }
                }, (kickTimeout * 60) * 20);
            }
        } else {
            String playerIsNotAfk = Messages.playerIsNotAfk.replaceAll("%name%", p.getName());
            Bukkit.broadcastMessage(playerIsNotAfk);
            p.setPlayerListName(ChatColor.DARK_GRAY + p.getDisplayName());
        }
    }

    public boolean isAfk() {
        return isAfk;
    }

    public static Rover getPlayerObject(Player player) {

        for (Rover po : PlayerController.onlinePlayers) {
            if (po.getUuid() == player.getUniqueId()) {
                return po;
            }

        }
        return null;

    }

    public void setTeleporting(boolean teleporting) {
        this.isTeleporting = teleporting;
    }

    public boolean isTeleporting() {
        return this.isTeleporting;
    }

    public void checkAfk(Player p, Main plugin) {
        setAfkLocation(p);
        BukkitTask afkTask = new BukkitRunnable() {
            public void run() {
                if (AfkCheck.checkAfk(p) && !isAfk()) {
                    setAfk(true, p);
                    checkAfk(p, plugin);
                } else {
                    checkAfk(p, plugin);
                }
            }
        }.runTaskLater(plugin, 300 * 20);
        PlayerController.afkTasks.put(p.getUniqueId(), afkTask.getTaskId());
        //Shcedule ny task med kick timeout.
        //Hvis afk cancel, cancel task via task id
        /*Bukkit.getServer().getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                if (!PlayerObject.getPlayerObject(p).isOnline()) {
                    return;
                } else if (AfkCheck.checkAfk(p) && !isAfk()) {
                    setAfk(true, p);
                    checkAfk(p, plugin);
                } else {
                    checkAfk(p, plugin);
                }
            }
        }, 300 * 20);*/
    }

    public void setOnline(boolean online) {
        this.isOnline = online;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void ontimeTimer(Player p, Main plugin) {
        BukkitTask ontime = new BukkitRunnable() {

            @Override
            public void run() {
                if (p.isOnline()) {
                    getOntime().increaseTime();
                    PlayerFileSaver fs = new PlayerFileSaver();
                    try {
                        fs.savePlayerFile(PlayerController.getPlayer(p));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    OntimeCheckEvent.ontimeChangeEvent(p);
                    ontimeTimer(p, plugin);
                }
            }
        }.runTaskLater(plugin, 300 * 20);
        PlayerController.ontimeTasks.remove(p.getUniqueId());
        PlayerController.ontimeTasks.put(p.getUniqueId(), ontime.getTaskId());
    }
  /*  public void ontimeTimer(Player p, Main plugin, PlayerDataReader pfo) {
        BukkitTask ontime = new BukkitRunnable() {

            @Override
            public void run() {
                if (p.isOnline()) {
                    int ontimeMin = pfo.getFileConfig(p).getInt("ontime") + 5;
                    pfo.writeToFile(p, "ontime", ontimeMin);
                    OntimeCheckEvent.ontimeChangeEvent(p);
                    ontimeTimer(p, plugin, pfo);
                }
            }
        }.runTaskLater(plugin, 300 * 20);
        PlayerController.ontimeTasks.remove(p.getUniqueId());
        PlayerController.ontimeTasks.put(p.getUniqueId(), ontime.getTaskId());
    }*/
}
