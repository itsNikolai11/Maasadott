package no.nkopperudmoen.måsadott.commands.tpa;

import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpaccept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        Player target;
        if (Rover.getPlayerObject(p).getTprequest() == null && Rover.getPlayerObject(p).getTphRequest() == null) {
            p.sendMessage(Messages.TPA_INGEN);
        } else if (Rover.getPlayerObject(p).getTphRequest() == null) {
            target = Rover.getPlayerObject(p).getTprequest();
            target.teleport(p);
            target.sendMessage(Messages.TPA_GODTATT.replaceAll("%spiller%", p.getName()));
            Rover.getPlayerObject(p).setTprequest(null);
        } else {
            target = Rover.getPlayerObject(p).getTphRequest();
            p.teleport(target);
            target.sendMessage(Messages.TPA_GODTATT.replaceAll("%spiller%", p.getName()));
            Rover.getPlayerObject(p).setTphRequest(null);

        }
        return false;
    }
}
