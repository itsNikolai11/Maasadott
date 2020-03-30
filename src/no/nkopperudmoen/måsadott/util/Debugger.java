package no.nkopperudmoen.måsadott.util;

import no.nkopperudmoen.måsadott.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Debugger {
    private static Main plugin = Main.getPlugin(no.nkopperudmoen.måsadott.Main.class);
    private static boolean debugging = plugin.getConfig().getBoolean("Debugging");

    public static void debug(String msg) {
        msg = ChatColor.translateAlternateColorCodes('&', "&c[DEBUG] &7") + msg;
        if (debugging) {
            for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                if (p.hasPermission(Permissions.DEBUG) || p.isOp()) {
                    p.sendMessage(msg);
                }
                System.out.println(msg);
            }
        }

    }
}
