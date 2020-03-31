package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.Rover.Home;
import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.exceptions.HomeNotFoundException;
import no.nkopperudmoen.måsadott.filbehandling.PlayerFileReader;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import no.nkopperudmoen.måsadott.util.UUIDFetcher;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;

public class TPHome implements CommandExecutor {
    private Main plugin;

    public TPHome(Main plugin) {
        this.plugin = plugin;
    }

    PlayerFileReader reader = new PlayerFileReader();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            Rover po = PlayerController.getPlayer(p);
            if (args.length == 0) {
                //TODO hvis flere enn et hjem, vis liste over hjem + melding om å velge et hjem å tp til
                try {
                    p.teleport(po.getHome("hjem").getLocation());
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING));
                } catch (HomeNotFoundException e) {
                    p.sendMessage(Messages.HOME_LIST.replaceAll("%homes%", po.getHomeNames()));
                }
            } else {
                if (args[0].endsWith(":")) {
                    if (p.hasPermission(Permissions.HOME_OTHERS)) {
                        String targetName = args[0].replaceAll(":", "");
                        Player t = Bukkit.getPlayer(targetName);
                        ArrayList<Home> hjem;
                        try {
                            hjem = reader.loadHomes(t);
                        } catch (IOException | ClassNotFoundException e) {
                            p.sendMessage("Spiller ikke funnet");
                            e.printStackTrace();
                            hjem = null;
                        } catch (NullPointerException e) {
                            //TODO legg til meldling i lang.yml
                            p.sendMessage("Spiller ikke funnet");
                            hjem = null;
                        }
                        if (args.length > 1) {
                            try {
                                p.teleport(getHome(args[1], hjem).getLocation());
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING));
                            } catch (HomeNotFoundException e) {
                                p.sendMessage(e.getMessage());
                            }
                        } else {
                            if (hjem != null) {
                                p.sendMessage(Messages.HOME_LIST.replaceAll("%homes%", reader.toString(hjem)));
                            }
                        }
                    } else {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
                    }
                } else {
                    try {
                        p.teleport(po.getHome(args[0]).getLocation());
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING));
                    } catch (HomeNotFoundException e) {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.HOME_NOT_FOUND.replaceAll("%home%", args[0])));

                    }
                }
            }
        }
        return true;
    }

    public Home getHome(String name, ArrayList<Home> homes) throws HomeNotFoundException {
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
}
