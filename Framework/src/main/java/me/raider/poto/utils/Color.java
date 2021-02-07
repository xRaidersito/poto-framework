package me.raider.poto.utils;
import org.bukkit.DyeColor;

public enum Color {

    BLACK("&0", DyeColor.BLACK),
    DARK_BLUE("&1", DyeColor.BLUE),
    DARK_GREEN("&2", DyeColor.GREEN),
    DARK_AQUA("&3", DyeColor.CYAN),
    DARK_RED("&4", DyeColor.RED),
    DARK_PURPLE("&5", DyeColor.PURPLE),
    GOLD("&6", DyeColor.ORANGE),
    GRAY("&7", DyeColor.SILVER),
    DARK_GRAY("&8", DyeColor.GRAY),
    BLUE("&9", DyeColor.LIGHT_BLUE),
    GREEN("&a", DyeColor.LIME),
    AQUA("&b", DyeColor.BROWN),
    RED("&c", DyeColor.PINK),
    LIGHT_PURPLE("&d", DyeColor.MAGENTA),
    YELLOW("&e", DyeColor.YELLOW),
    WHITE("&f", DyeColor.WHITE);


    private String colorCode;
    private DyeColor dyeColor;

    Color(String colorCode, DyeColor dyeColor) {
            this.colorCode = colorCode;
            this.dyeColor = dyeColor;
    }

    public String getColorCode() {
        return colorCode;
    }

    public DyeColor getDyeColor() {
        return dyeColor;
    }
}
