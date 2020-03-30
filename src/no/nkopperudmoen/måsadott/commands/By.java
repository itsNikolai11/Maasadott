package no.nkopperudmoen.måsadott.commands;

import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class By implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        sender.sendMessage(Messages.BY_HJELP);
        return false;
    }
}
