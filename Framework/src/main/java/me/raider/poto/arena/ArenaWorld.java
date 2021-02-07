package me.raider.poto.arena;

import me.raider.poto.utils.Utils;
import me.raider.poto.world.VoidWorldGenerator;
import org.apache.commons.io.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.File;
import java.io.IOException;

public class ArenaWorld {

    private World world;
    private final AbstractArena arena;
    private final String path;


    public ArenaWorld(AbstractArena arena) {
        this.arena=arena;
        this.path=arena.getPlugin().getDataFolder().getAbsolutePath() +  "/worlds/" + arena.getName();
    }

    public World generateVoidWorld(String worldName) {

        WorldCreator worldCreator = new WorldCreator(worldName);

        worldCreator.generator(new VoidWorldGenerator());
        worldCreator.environment(World.Environment.NORMAL);
        worldCreator.generateStructures(false);

        World world = worldCreator.createWorld();

        world.setDifficulty(Difficulty.NORMAL);
        world.setSpawnFlags(true, true);
        world.setPVP(true);
        world.setStorm(false);
        world.setThundering(false);
        world.setWeatherDuration(2147483647);
        world.setAutoSave(false);
        world.setKeepSpawnInMemory(false);
        world.setTicksPerAnimalSpawns(1);
        world.setTicksPerMonsterSpawns(1);
        world.setGameRuleValue("doMobSpawning", "false");
        world.setGameRuleValue("mobGriefing", "false");
        world.setGameRuleValue("doFireTick", "false");
        world.setGameRuleValue("showDeathMessages", "false");

        return world;

    }

    public void saveWorld() {

        if (world==null) {
            return;
        }

        try {
            FileUtils.copyDirectory(world.getWorldFolder(), new File(path));
            deleteWorld();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadWorld() {

        if (Utils.worldFolderExist(arena.getName())) {

            WorldCreator worldCreator = new WorldCreator(arena.getName());

            worldCreator.environment(World.Environment.NORMAL);
            worldCreator.generateStructures(false);

            world = worldCreator.createWorld();

        } else {

            world = generateVoidWorld(arena.getName());

        }
    }

    public boolean loadFromDirectory() {

        if (!new File(path).exists()) {
            return false;
        }

        try {
            FileUtils.copyDirectory(new File(path), new File(Bukkit.getWorldContainer().getAbsolutePath() + "/" + arena.getName()));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteWorld() {

        if (world==null) {
            return;
        }

        Bukkit.getServer().unloadWorld(this.world, true);

        if (Utils.worldFolderExist(world.getName())) {

            File worldFile = world.getWorldFolder();
            try {
                FileUtils.deleteDirectory(worldFile);
                world=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public World getWorld() {
        return world;
    }
}
