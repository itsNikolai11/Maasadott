package no.nkopperudmoen.måsadott.commands.tpa;

import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TPA implements CommandExecutor {
    private Main plugin;

    public TPA(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length > 0) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage(Messages.TPA_NOT_ONLINE);
                } else if (p == target) {
                    p.sendMessage(Messages.TPA_SELV);

                } else {
                    Rover.getPlayerObject(target).setTprequest(p);
                    Rover.getPlayerObject(target).setTphRequest(null);
                    String tpa = Messages.TPA_MOTATT;
                    tpa = tpa.replaceAll("%spiller%", target.getName());
                    target.sendMessage(tpa);
                    p.sendMessage(Messages.TPA_SENDT);
                    Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, new Runnable() {
                        @Override
                        public void run() {
                            Rover.getPlayerObject(target).setTprequest(null);
                        }
                    }, 300 * 20);
                }

            } else {
                return false;
            }
        } else {

        }
        return true;
    }
}
