package no.nkopperudmoen.måsadott.util;

import no.nkopperudmoen.måsadott.filbehandling.BanFileSaver;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

public class TemporaryBan implements Serializable {
    public static HashMap<UUID, TemporaryBan> tempbans = new HashMap<>();
    private long banTime;
    private long banDuration;
    private UUID player;
    private String reason;

    public TemporaryBan(UUID uuid, String reason, long duration) {
        this.banTime = System.currentTimeMillis();
        this.banDuration = duration;
        this.player = uuid;
        this.reason = reason;
        BanFileSaver saver = new BanFileSaver();
        try {
            saver.saveBans();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long getBanTime() {
        return banTime;
    }

    public long getExpiration() {
        return banTime + banDuration;
    }

    public void setBanTime(long banTime) {
        this.banTime = banTime;
    }

    public long getBanDuration() {
        return banDuration;
    }

    public void setBanDuration(long banDuration) {
        this.banDuration = banDuration;
    }

    public UUID getPlayer() {
        return player;
    }

    public void setPlayer(UUID player) {
        this.player = player;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static void addBan(UUID uuid, TemporaryBan ban) {
        tempbans.put(uuid, ban);
        BanFileSaver saver = new BanFileSaver();
        try {
            saver.saveBans();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeBan(UUID uuid) {
        tempbans.remove(uuid);
        BanFileSaver saver = new BanFileSaver();
        try {
            saver.saveBans();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
