package me.raider.poto.utils;

import me.raider.poto.serializer.Serialize;
import me.raider.poto.storage.types.Storable;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Map;

public class StorableLocation implements Storable {

    @Serialize(path = "x")
    private final double x;

    @Serialize(path = "y")
    private final double y;

    @Serialize(path = "z")
    private final double z;

    @Serialize(path = "world")
    private final String worldName;

    public StorableLocation(double x, double y, double z, String worldName) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.worldName = worldName;
    }

    public StorableLocation(Location location) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.worldName = location.getWorld().getName();
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(worldName), x, y, z);
    }

    public static StorableLocation deserialize(Map<String, Object> serializedMap) {

        double x = (double) serializedMap.get("x");
        double y = (double) serializedMap.get("y");
        double z = (double) serializedMap.get("z");
        String worldName = (String) serializedMap.get("world");

        return new StorableLocation(x, y, z, worldName);
    }

    @Override
    public String getName() {
        return "";
    }
}
