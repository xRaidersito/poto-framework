package me.raider.poto.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public interface ItemBuilder {

    ItemBuilder material(Material material);

    ItemBuilder amount(int amount);

    ItemBuilder lore(List<String> lore);

    ItemBuilder name(String name);

    ItemBuilder enchantment(Enchantment enchantment, int level);

    ItemBuilder glow();

    ItemBuilder flags(ItemFlag... itemFlags);

    ItemBuilder unbreakable();

    ItemStack build();

    static ItemBuilder create(Material material) {
        return new SimpleItemBuilder(material);
    }

    static ItemBuilder create(Material material, int amount) {
        return new SimpleItemBuilder(material, amount);
    }

}
