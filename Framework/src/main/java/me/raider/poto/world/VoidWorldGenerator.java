package me.raider.poto.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VoidWorldGenerator extends ChunkGenerator {

    public List<BlockPopulator> getDefaultPopulators(World param1World) {
        return Collections.emptyList();
    }

    public boolean canSpawn(World param1World, int param1Int1, int param1Int2) {
        return true;
    }

    public byte[] generate(World param1World, Random param1Random, int param1Int1, int param1Int2) {
        return new byte[32768];
    }

    public Location getFixedSpawnLocation(World param1World, Random param1Random) {
        return new Location(param1World, 0.0D, 64.0D, 0.0D);
    }

}