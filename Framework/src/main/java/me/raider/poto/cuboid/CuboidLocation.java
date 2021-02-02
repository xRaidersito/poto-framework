package me.raider.poto.cuboid;

import me.raider.poto.storage.types.Storable;

import java.util.Map;

public interface CuboidLocation extends Storable {

    int getX();

    int getY();

    int getZ();

    static CuboidLocation deserialize(Map<String, Object> serializedMap) {

        int x = (int) serializedMap.get("x");
        int y = (int) serializedMap.get("y");
        int z = (int) serializedMap.get("z");

        return new CuboidLocationImpl(x, y, z);
    }

}
