package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.Rover.PlayerTime;
import no.nkopperudmoen.måsadott.controllers.PlayerController;
import no.nkopperudmoen.måsadott.events.OntimeCheckEvent;
import no.nkopperudmoen.måsadott.filbehandling.PlayerFileReader;
import no.nkopperudmoen.måsadott.util.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Ontime implements CommandExecutor {
    private Main plugin;

    public Ontime(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }
        Player p = (Player) sender;
        PlayerFileReader reader = new PlayerFileReader();

        if (args.length > 0) {
            if (p.hasPermission(Permissions.ONTIME_OTHERS)) {
                Player target;
                if (Bukkit.getPlayer(args[0]) == null) {
                    try {
                        PlayerTime targetTime = reader.getOntime(UUIDFetcher.getUUID(args[0]));
                        p.sendMessage(Messages.ONTIME.replaceAll("%ontime%", TimeConverter.toString(TimeConverter.convertTime(targetTime.getMin()))).replaceAll("%rank%", "ingen nye ranks"));
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                target = Bukkit.getPlayer(args[0]);
                RankFileReader rf = new RankFileReader();

                int minOnline = PlayerController.getPlayer(target).getOntime().getMin();
                if (rf.getNextRank(target) == null) {
                    p.sendMessage(Messages.ONTIME.replaceAll("%ontime%", TimeConverter.toString(TimeConverter.convertTime(minOnline))).replaceAll("%rank%", "ingen nye ranks"));
                } else {
                    p.sendMessage(Messages.ONTIME.replaceAll("%ontime%", TimeConverter.toString(TimeConverter.convertTime(minOnline))).replaceAll("%rank%", rf.getNextRank(target)));

                }
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
            }
        } else {
            RankFileReader rf = new RankFileReader();
            int minOnline = PlayerController.getPlayer(p).getOntime().getMin();
            String nextRank = rf.getNextRank(p);
            if (nextRank == null) {
                p.sendMessage(Messages.ONTIME.replaceAll("%ontime%", TimeConverter.toString(TimeConverter.convertTime(minOnline))).replaceAll("%rank%", "ingen nye ranks"));
            } else {
                p.sendMessage(Messages.ONTIME.replaceAll("%ontime%", TimeConverter.toString(TimeConverter.convertTime(minOnline))).replaceAll("%rank%", nextRank).replaceAll("%time%", TimeConverter.toString(TimeConverter.convertTime(rf.getRankupTime(p) * 60))));
                p.sendMessage(Messages.ONTIME_NEXT.replaceAll("%time%", TimeConverter.toString(TimeConverter.convertTime(rf.getRankupTime(p) * 60))));
                OntimeCheckEvent.ontimeChangeEvent(p);
            }


        }


        return false;
    }
}
