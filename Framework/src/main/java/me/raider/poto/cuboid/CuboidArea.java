package me.raider.poto.cuboid;

import me.raider.poto.storage.types.Storable;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.List;

public interface CuboidArea extends Storable {

    CuboidLocation getMinPoint();

    CuboidLocation getMaxPoint();

    Location getCenter();

    boolean contains(Location location);

    boolean contains(int x, int y, int z);

    List<Block> getBlocksInside();

    List<Chunk> getImplicatedChunks();

}
