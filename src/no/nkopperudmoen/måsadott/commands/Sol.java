package no.nkopperudmoen.måsadott.commands;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getScheduler;
import static org.bukkit.Bukkit.getServer;


public class Sol implements CommandExecutor {

    private static int antallStemt = 0;
    static Player start;
    ArrayList<String> stemt = new ArrayList<>();
    public ArrayList<String> verdenerPåCooldown = new ArrayList<>();
    public static ArrayList<String> verdenerStemmer = new ArrayList<>();
    static int stem;

    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Kun spillere kan gjøre dette.");
            return false;
        }
        Player p = (Player) sender;

        if (verdenerPåCooldown.contains(p.getWorld().getName())) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.SOL_COOLDOWN));
            return false;
        }
        if (Messages.plugin.getConfig().getList("Blacklisted").contains(p.getWorld().getName())) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.SOL_BLOKKERT));
            return false;
        }
        if ((!(verdenerPåCooldown.contains(p.getWorld().getName())))
                && (!(verdenerStemmer.contains(p.getWorld().getName()))) && p.getWorld().hasStorm()) {
            float i = p.getWorld().getPlayers().size();
            start = p;
            solAvstemning(start, p.getWorld(), i);
            if (antallStemt == p.getWorld().getPlayers().size()) {
                instantSol(p.getWorld());
            }
            return true;
        } else if (!p.getWorld().hasStorm()) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.SOL_IKKE_REGN));
            return false;
        }
        if (stemt.contains(p.getName())) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.SOL_ALLEREDE_STEMT));
            return false;
        }
        if (verdenerStemmer.contains(p.getWorld().getName())) {
            antallStemt++;
            stemt.add(p.getName());
            float i = p.getWorld().getPlayers().size();
            float pros = ((antallStemt / i) * 100);
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.STEMME_REGISTRERT.replaceAll("%stemt%", antallStemt + "")));
            if (pros >= 50) {
                instantSol(p.getWorld());
            }
            return false;

        }
        return false;
    }

    private void solAvstemning(Player p, World world, float i) {
        antallStemt++;
        stemt.add(p.getName());
        verdenerStemmer.add(world.getName());
        for (Player s : world.getPlayers()) {
            s.sendMessage(Messages.SOL_STARTET.replaceAll("%spiller%", p.getName()));
        }
        getServer().getScheduler().scheduleSyncDelayedTask(Messages.plugin,
                () -> {
                    if (p.getWorld().hasStorm()) {
                        settSol(p, world, i);
                    }
                    verdenerPåCooldown.add(world.getName());
                    verdenerStemmer.remove(world.getName());
                }, 60 * 20);
    }

    private void settSol(Player p, World world, float i) {
        float pros = ((antallStemt / i) * 100);
        String antallStemmer = antallStemt + "";
        verdenerPåCooldown.add(world.getName());
        if (!world.hasStorm()) {
            stemt.clear();
            antallStemt = 0;
            return;
        }
        for (Player s : world.getPlayers()) {
            if (pros < 50) {
                s.sendMessage(Messages.IKKE_NOK_STEMMER.replaceAll("%antall%", antallStemmer));
                stemt.clear();
                antallStemt = 0;
                getScheduler().runTaskLater(Messages.plugin,
                        () -> verdenerPåCooldown.remove(world.getName()), 900 * 20);
            } else if (pros >= 50) {
                s.sendMessage(Messages.SOL.replaceAll("%antall%", antallStemmer));
                world.setStorm(false);
                antallStemt = 0;
                stemt.clear();
                getScheduler().scheduleSyncDelayedTask(Messages.plugin,
                        () -> verdenerPåCooldown.remove(world.getName()), 900 * 20);

            }
        }

    }

    private void instantSol(World world) {
        for (Player s : world.getPlayers()) {
            s.sendMessage(Messages.SOL_INSTANT);
            world.setStorm(false);

            antallStemt = 0;
            stemt.clear();
            verdenerStemmer.remove(world.getName());
            getScheduler().cancelTask(stem);
            getScheduler().scheduleSyncDelayedTask(Messages.plugin, () -> verdenerPåCooldown.remove(world.getName()), 900 * 20);
        }
    }
}

