package no.nkopperudmoen.måsadott.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.VehicleEnterEvent;

import java.util.ArrayList;

public class VanishManager implements Listener {

    public static ArrayList<Player> vanishedPlayers = new ArrayList<Player>();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        if(vanishedPlayers.contains(p)){
            e.setQuitMessage(null);
        }

    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void onBucketFill(PlayerBucketFillEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void onBucketEmpty(PlayerBucketEmptyEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.getEgg().remove();
        }


    }

    @EventHandler
    public void playerExpChange(PlayerExpChangeEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {

        }


    }

    @EventHandler
    public void playerItemConsume(PlayerItemConsumeEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void playerArrowPickup(PlayerPickupArrowEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void onLecternBookTake(PlayerTakeLecternBookEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockFertilize(BlockFertilizeEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlockMultiplace(BlockMultiPlaceEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        Player p = e.getPlayer();
        if (vanishedPlayers.contains(p)) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityCombust(EntityCombustEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (vanishedPlayers.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (vanishedPlayers.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onItemPickup(EntityPickupItemEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (vanishedPlayers.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onArrowShoot(EntityShootBowEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (vanishedPlayers.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntityTarget(EntityTargetEvent e) {
        if (e.getTarget() instanceof Player) {
            Player p = (Player) e.getTarget();
            if (vanishedPlayers.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (vanishedPlayers.contains(p)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onVehicleEnter(VehicleEnterEvent e) {
        if (e.getEntered() instanceof Player) {
            Player p = (Player) e.getEntered();
            if (vanishedPlayers.contains(p)) {
                e.setCancelled(true);
            }
        }
    }
}
