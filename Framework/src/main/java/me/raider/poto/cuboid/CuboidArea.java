package me.raider.poto.cuboid;

import me.raider.poto.storage.types.Storable;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.List;
import java.util.Map;

public interface CuboidArea extends Storable {

    CuboidLocation getMinPoint();

    CuboidLocation getMaxPoint();

    Location getCenter();

    boolean contains(Location location);

    boolean contains(int x, int y, int z);

    List<Block> getBlocksInside();

    List<Chunk> getImplicatedChunks();

    static CuboidArea deserialize(Map<String, Object> serializedMap) {

       CuboidLocation min = CuboidLocation.deserialize((Map<String, Object>) serializedMap.get("min"));
       CuboidLocation max = CuboidLocation.deserialize((Map<String, Object>) serializedMap.get("max"));

       String name = (String) serializedMap.get("name");
       World world = Bukkit.getWorld((String) serializedMap.get("world"));

       return new CuboidAreaImpl(name, new Location(world, min.getX(), min.getY(), min.getZ()), new Location(world, max.getX(), max.getY(), max.getZ()));

    }

}
