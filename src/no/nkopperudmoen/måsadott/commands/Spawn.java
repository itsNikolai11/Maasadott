package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    private FileConfiguration config;

    public Spawn(FileConfiguration config) {
        this.config = config;

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {


        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0) {
                try {
                    World world = Bukkit.getServer().getWorld(config.getString("Spawn.world"));
                    double x = Double.parseDouble(config.getString("Spawn.x"));
                    double y = Double.parseDouble(config.getString("Spawn.y"));
                    double z = Double.parseDouble(config.getString("Spawn.z"));
                    float yaw = Float.parseFloat((String) config.get("Spawn.yaw"));
                    float pitch = Float.parseFloat((String) config.get("Spawn.pitch"));
                    Location spawn = new Location(world, x, y, z, yaw, pitch);
                    p.teleport(spawn);
                } catch (Exception exc) {
                    p.teleport(p.getWorld().getSpawnLocation());
                }
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING));

            } else {
                if (p.hasPermission(Permissions.SPAWN_OTHERS)) {
                    Player t = Bukkit.getPlayer(args[0]);
                    if (t == null) {
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.PAYER_NOT_FOUND.replaceAll("%spiller%", args[0])));
                        return true;
                    }
                    try {
                        World world = Bukkit.getServer().getWorld(config.getString("Spawn.world"));
                        double x = Double.parseDouble(config.getString("Spawn.x"));
                        double y = Double.parseDouble(config.getString("Spawn.y"));
                        double z = Double.parseDouble(config.getString("Spawn.z"));
                        float yaw = Float.parseFloat((String) config.get("Spawn.yaw"));
                        float pitch = Float.parseFloat((String) config.get("Spawn.pitch"));
                        Location spawn = new Location(world, x, y, z, yaw, pitch);
                        t.teleport(spawn);
                        t.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING));
                    } catch (Exception exc) {
                        t.teleport(p.getWorld().getSpawnLocation());
                        t.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.TELEPORTING));
                    }
                } else {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
                }
                return true;
            }
        }
        return false;
    }
}
