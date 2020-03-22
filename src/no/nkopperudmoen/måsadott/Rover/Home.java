package no.nkopperudmoen.måsadott.Rover;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;

public class Home implements Serializable {
    private String name;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private String world;

    public Home(String name, double x, double y, double z, float yaw, float pitch, World world) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world.getName();
    }

    public Location getLocation() {
        return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
    }

    public String getName() {
        return name;
    }
}
