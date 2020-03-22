package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.events.OntimeCheckEvent;
import no.nkopperudmoen.måsadott.util.*;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

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

        if (args.length > 0) {
            if (p.hasPermission(Permissions.ONTIME_OTHERS)) {
                Player target = Bukkit.getPlayer(args[0]);
                PlayerDataReader pd = new PlayerDataReader(plugin);
                if (pd.getFileConfig(p) == null) {
                    //TODO Spiller ikke funnet melding
                } else {
                    FileConfiguration playerConfig = pd.getFileConfig(target);
                    RankFileReader rf = new RankFileReader();
                    int minOnline = playerConfig.getInt("ontime");
                    if (rf.getNextRank(target) == null) {
                        p.sendMessage(Messages.ONTIME.replaceAll("%ontime%", TimeConverter.toString(TimeConverter.convertTime(minOnline))).replaceAll("%rank%", "ingen nye ranks"));
                    } else {
                        p.sendMessage(Messages.ONTIME.replaceAll("%ontime%", TimeConverter.toString(TimeConverter.convertTime(minOnline))).replaceAll("%rank%", rf.getNextRank(target)));
                    }
                }
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
            }
        } else {
            PlayerDataReader pd = new PlayerDataReader(plugin);
            FileConfiguration playerConfig = pd.getFileConfig(p);
            RankFileReader rf = new RankFileReader();
            int minOnline = playerConfig.getInt("ontime");
            if (rf.getNextRank(p) == null) {
                p.sendMessage(Messages.ONTIME.replaceAll("%ontime%", TimeConverter.toString(TimeConverter.convertTime(minOnline))).replaceAll("%rank%", "ingen nye ranks"));
            } else {
                p.sendMessage(Messages.ONTIME.replaceAll("%ontime%", TimeConverter.toString(TimeConverter.convertTime(minOnline))).replaceAll("%rank%", rf.getNextRank(p)).replaceAll("%time%", TimeConverter.toString(TimeConverter.convertTime(rf.getRankupTime(p) * 60))));
            }

            OntimeCheckEvent.ontimeChangeEvent(p);
        }


        return false;
    }
}
