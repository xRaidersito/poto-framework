package me.raider.poto.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }

    public static boolean worldFolderExist(String folderName) {

        File file = new File(Bukkit.getServer().getWorldContainer(), folderName);
        return file.exists();

    }

    public static boolean directoryExist(String pathName) {

        File file = new File(pathName);
        return file.exists() && file.isDirectory();
    }

    public static Location locationFromString(String stringLoc) {
        if (stringLoc == null || stringLoc.trim().equals("")) {
            return null;
        }
        String[] parts = stringLoc.split(":");

        if (parts.length == 4) {
            World world = Bukkit.getServer().getWorld(parts[0]);
            int x = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            int z = Integer.parseInt(parts[3]);
            return new Location(world, x, y, z);
        }
        return null;
    }

    public static String locationToString(Location loc) {
        if (loc == null) {
            return "";
        }
        return loc.getWorld().getName() + ":" + loc.getBlockX() + ":" + loc.getBlockY() + ":" + loc.getBlockZ();
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
