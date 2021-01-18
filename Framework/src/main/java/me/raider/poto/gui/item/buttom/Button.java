package me.raider.poto.gui.item.buttom;

import me.raider.poto.gui.item.SimpleGuiItem;
import me.raider.poto.gui.item.action.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public abstract class Button extends SimpleGuiItem {

    public Button(int slot, Action<InventoryClickEvent> action, ItemStack item) {
        super(slot, action, item);
    }
}
