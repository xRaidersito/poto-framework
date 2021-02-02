package me.raider.poto.cuboid;

import me.raider.poto.serializer.Serialize;

public class CuboidLocationImpl implements CuboidLocation {

    @Serialize(path="x")
    private final int x;
    @Serialize(path="y")
    private final int y;
    @Serialize(path="z")
    private final int z;

    public CuboidLocationImpl(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getZ() {
        return z;
    }

    @Override
    public String getName() {
        return "";
    }

}
