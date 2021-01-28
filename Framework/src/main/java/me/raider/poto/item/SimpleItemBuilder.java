package me.raider.poto.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class SimpleItemBuilder implements ItemBuilder {

    private final ItemStack item;

    public SimpleItemBuilder(Material material) {
        item=new ItemStack(material);
    }

    public SimpleItemBuilder(Material material, int amount) {
        item = new ItemStack(material, amount);
    }

    @Override
    public ItemBuilder material(Material material) {
        item.setType(material);
        return this;
    }

    @Override
    public ItemBuilder amount(int amount) {
        item.setAmount(amount);
        return this;
    }

    @Override
    public ItemBuilder lore(List<String> lore) {

        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);

        return this;
    }

    @Override
    public ItemBuilder name(String name) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);

        return this;
    }

    @Override
    public ItemBuilder enchantment(Enchantment enchantment, int level) {

        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return this;
    }

    @Override
    public ItemBuilder glow() {

        ItemMeta meta = item.getItemMeta();

        meta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return this;
    }

    @Override
    public ItemBuilder flags(ItemFlag... itemFlags) {

        ItemMeta meta = item.getItemMeta();
        meta.addItemFlags(itemFlags);
        item.setItemMeta(meta);
        return this;
    }

    @Override
    public ItemBuilder unbreakable() {

        ItemMeta meta = item.getItemMeta();
        meta.spigot().setUnbreakable(true);
        item.setItemMeta(meta);
        return this;
    }

    @Override
    public ItemStack build() {
        return item;
    }
}
