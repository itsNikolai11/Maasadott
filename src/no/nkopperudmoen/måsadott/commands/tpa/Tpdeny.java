package no.nkopperudmoen.måsadott.commands.tpa;

import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpdeny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if (Rover.getPlayerObject(p).getTprequest() == null) {
            p.sendMessage(Messages.TPA_INGEN);

        } else {
            Player target = Rover.getPlayerObject(p).getTprequest();
            target.sendMessage(Messages.TPA_AVSLÅTT.replaceAll("%spiller%", p.getName()));
            Rover.getPlayerObject(p).setTprequest(null);
            p.sendMessage(Messages.AVSLO_TPA);
        }
        return false;
    }
}
