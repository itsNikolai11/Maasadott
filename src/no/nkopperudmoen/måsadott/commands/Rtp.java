package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.Rover.Rover;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.*;
import org.bukkit.block.Biome;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class Rtp implements CommandExecutor {
    private Main plugin;

    public Rtp(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission(Permissions.RTP)) {
                Location rtp = randomLocation(p);
                Rover.getPlayerObject(p).setTeleporting(true);
                if (plugin.getConfig().getStringList("Blacklisted").contains(p.getWorld().getName())) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.RTP_BLOCKED));
                } else {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "Finner tilfeldig posisjon.."));
                    countdown(p, 3, rtp);
                }
            } else {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
            }
        } else {
            return false;
        }
        return false;
    }

    public Location randomLocation(Player p) {
        int x = 0;
        int z = 0;

        World world = p.getWorld();
        int y = world.getHighestBlockYAt(x, z);
        Random r = new Random();
        do {
            x = r.nextInt((int) (world.getWorldBorder().getSize())) - (int) (world.getWorldBorder().getSize() / 2);
            z = r.nextInt((int) (world.getWorldBorder().getSize())) - (int) (world.getWorldBorder().getSize() / 2);
        }
        while (world.getBlockAt(x, world.getHighestBlockYAt(x, z) + 2, z).getType() != Material.AIR ||
                world.getHighestBlockAt(x, z).isLiquid() ||
                world.getHighestBlockAt(x, z).getBiome() == Biome.DEEP_COLD_OCEAN ||
                world.getHighestBlockAt(x, z).getBiome() == Biome.COLD_OCEAN ||
                world.getHighestBlockAt(x, z).getBiome() == Biome.OCEAN ||
                world.getHighestBlockAt(x, z).getBiome() == Biome.DEEP_FROZEN_OCEAN ||
                world.getHighestBlockAt(x, z).getBiome() == Biome.DEEP_LUKEWARM_OCEAN ||
                world.getHighestBlockAt(x, z).getBiome() == Biome.DEEP_OCEAN ||
                world.getHighestBlockAt(x, z).getBiome() == Biome.DEEP_WARM_OCEAN ||
                world.getHighestBlockAt(x, z).getBiome() == Biome.FROZEN_OCEAN ||
                world.getHighestBlockAt(x, z).getBiome() == Biome.LUKEWARM_OCEAN ||
                world.getHighestBlockAt(x, z).getBiome() == Biome.WARM_OCEAN ||
                world.getBlockAt(x, y + 1, z).getType() == Material.FIRE ||
                world.getBlockAt(x, y + 1, z).getType() == Material.LAVA);
        //world.getBlockAt(x, world.getHighestBlockYAt(x, z) + 2, z).getType() == Material.LAVA ||
        Location rtp = new Location(world, x, world.getHighestBlockYAt(x, z) + 3, z);
        return rtp;
    }

    public void countdown(Player p, int sec, Location rtp) {
        String teleported = Messages.RTP_TELEPORTED;
        teleported = teleported.replaceAll("%position%", rtp.getX() + ", " + rtp.getY() + ", " + rtp.getZ());
        String finalTeleported = teleported;
        Bukkit.getScheduler().runTaskLater(plugin, new Runnable() {
            @Override
            public void run() {
                if (!Rover.getPlayerObject(p).isTeleporting()) {
                    return;
                } else if (sec > 0) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "-->> " + sec + " <<--"));
                    countdown(p, sec - 1, rtp);
                } else if (sec == 0) {
                    p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(ChatColor.GOLD + "Teleporterer"));
                    if (Rover.getPlayerObject(p).isTeleporting()) {
                        Rover.getPlayerObject(p).setTeleporting(false);
                        p.teleport(rtp);
                        p.sendMessage(finalTeleported);

                    }
                }
            }
        }, 20);

    }
}
