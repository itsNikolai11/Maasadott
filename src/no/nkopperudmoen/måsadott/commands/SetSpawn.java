package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {
    private FileConfiguration config;
    private Main plugin;

    public SetSpawn(FileConfiguration config, Main plugin) {
        this.config = config;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.SET_SPAWN)) {
                Location l = p.getLocation();
                config.set("Spawn.x", l.getX() + "");
                config.set("Spawn.y", l.getY() + "");
                config.set("Spawn.z", l.getZ() + "");
                config.set("Spawn.yaw", l.getYaw() + "");
                config.set("Spawn.pitch", l.getPitch() + "");
                config.set("Spawn.world", l.getWorld().getName());
                plugin.saveConfig();
                plugin.reloadConfig();

                p.sendMessage("Satt spawn");
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
            }


        } else {
            return false;
        }

        return false;
    }
}
