package no.nkopperudmoen.måsadott.events;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import no.nkopperudmoen.måsadott.Main;
import no.nkopperudmoen.måsadott.util.Messages;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class MobMoneyDrop implements Listener {


    @EventHandler
    public void onMobKill(EntityDeathEvent e) {
        ArrayList<EntityType> entities = new ArrayList<>(Arrays.asList(EntityType.CREEPER,
                EntityType.SKELETON, EntityType.ZOMBIE, EntityType.CAVE_SPIDER, EntityType.BLAZE,
                EntityType.SPIDER, EntityType.HUSK, EntityType.DROWNED, EntityType.PILLAGER,
                EntityType.GHAST, EntityType.PHANTOM, EntityType.ENDERMAN, EntityType.RAVAGER,
                EntityType.WITHER_SKELETON, EntityType.WITCH, EntityType.VINDICATOR,
                EntityType.ZOMBIE_VILLAGER, EntityType.VEX, EntityType.STRAY, EntityType.EVOKER));
        Entity killed = e.getEntity();
        Player killer = e.getEntity().getKiller();
        double money = 2.0;
        if (entities.contains(killed.getType())) {
            if (killer != null) {
                Main.economy.depositPlayer(killer, money);
                killer.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Messages.MOB_DROP.replaceAll("%money%", money + " Krager").replaceAll("%entity%", killed.getName())));
            }
        }

    }

}
