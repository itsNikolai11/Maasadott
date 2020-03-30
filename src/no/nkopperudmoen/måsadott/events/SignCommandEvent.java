package no.nkopperudmoen.måsadott.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.util.Messages;
import no.nkopperudmoen.måsadott.util.Permissions;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class SignCommandEvent implements Listener {
    @EventHandler
    public void onSignInteract(PlayerInteractEvent e) {
        ArrayList<Material> skilttyper = new ArrayList<>(Arrays.asList(Material.OAK_WALL_SIGN, Material.ACACIA_WALL_SIGN,
                Material.SPRUCE_WALL_SIGN, Material.JUNGLE_WALL_SIGN,
                Material.BIRCH_WALL_SIGN, Material.DARK_OAK_WALL_SIGN, Material.OAK_SIGN, Material.ACACIA_SIGN,
                Material.SPRUCE_SIGN, Material.JUNGLE_SIGN,
                Material.BIRCH_SIGN, Material.DARK_OAK_SIGN));
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (skilttyper.contains(e.getClickedBlock().getType())) {
                Sign s = (Sign) e.getClickedBlock().getState();
                if (s.getLine(0).toLowerCase().contains("rtp") && s.getLine(1).equalsIgnoreCase("klikk for rtp")) {
                    p.performCommand("rtp");
                }
            }
        }

    }

    @EventHandler
    public void onSignCreate(SignChangeEvent e) {
        if (e.getLine(0).equalsIgnoreCase("rtp") || e.getLine(1).equalsIgnoreCase("klikk for rtp")) {
            if (e.getPlayer().hasPermission(Permissions.SIGN)) {
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("Skilt opprettet!"));
            } else {
                e.setCancelled(true);
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.NO_ACCESS));
            }
        }

    }
}
