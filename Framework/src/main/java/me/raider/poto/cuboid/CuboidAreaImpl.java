package me.raider.poto.cuboid;

import me.raider.poto.serializer.Serialize;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.List;

public class CuboidAreaImpl implements CuboidArea {

    @Serialize(path = "min")
    private final CuboidLocation minPoint;
    @Serialize(path = "max")
    private final CuboidLocation maxPoint;
    @Serialize(path = "world")
    private final String world;
    @Serialize(path = "name")
    private final String name;

    public CuboidAreaImpl(String name, Location locOne, Location locTwo) {

        if (!locOne.getWorld().equals(locTwo.getWorld())) {
            throw new IllegalArgumentException("Locations arent in the same world");
        }

        this.name=name;
        this.world=locOne.getWorld().getName();

        this.minPoint=new CuboidLocationImpl(Math.min(locOne.getBlockX(), locTwo.getBlockX()),
                Math.min(locOne.getBlockY(), locTwo.getBlockY()), Math.min(locOne.getBlockX(), locTwo.getBlockX()));

        this.maxPoint=new CuboidLocationImpl(Math.max(locOne.getBlockX(), locTwo.getBlockX()),
                Math.max(locOne.getBlockY(), locTwo.getBlockY()), Math.max(locOne.getBlockX(), locTwo.getBlockX()));

    }

    @Override
    public CuboidLocation getMinPoint() {
        return minPoint;
    }

    @Override
    public CuboidLocation getMaxPoint() {
        return maxPoint;
    }

    @Override
    public Location getCenter() {
       return new Location(Bukkit.getWorld(world), minPoint.getX() + (maxPoint.getX() - minPoint.getX()) / 2.0D
                                                 , minPoint.getY() + (maxPoint.getY() - minPoint.getY()) / 2.0D
                                                 , minPoint.getZ() + (maxPoint.getZ() - minPoint.getZ()) / 2.0D);
    }

    @Override
    public boolean contains(Location location) {
        return (minPoint.getX() <= location.getX()
                && minPoint.getY() <= location.getY()
                && minPoint.getZ() <= location.getZ()
                && maxPoint.getX() >= location.getX()
                && maxPoint.getY() >= location.getY()
                && maxPoint.getZ() >= location.getZ());
    }

    @Override
    public boolean contains(int x, int y, int z) {
        return (minPoint.getX() <= x
                && minPoint.getY() <= y
                && minPoint.getZ() <= z
                && maxPoint.getX() >= x
                && maxPoint.getY() >= y
                && maxPoint.getZ() >= z);
    }

    @Override
    public List<Block> getBlocksInside() {

        List<Block> blocks = new ArrayList<>();

        for (int x = minPoint.getX() ; x <= maxPoint.getX() ; x++) {
            for (int y = minPoint.getY() ; y <= maxPoint.getY() ; y++) {
                for (int z = minPoint.getZ(); z <= maxPoint.getZ(); z++) {
                    blocks.add(Bukkit.getWorld(world).getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
    }

    @Override
    public List<Chunk> getImplicatedChunks() {

        List<Chunk> chunks = new ArrayList<>();

        for (Block block : getBlocksInside()) {

            Chunk chunk = block.getChunk();

            if (!chunks.contains(chunk)) {
                chunks.add(chunk);
            }
        }
        return chunks;
    }

    @Override
    public String getName() {
        return name;
    }
}
