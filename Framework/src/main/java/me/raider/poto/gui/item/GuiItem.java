package me.raider.poto.gui.item;

import me.raider.poto.gui.item.action.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;


public interface GuiItem {

    int getSlot();

    ItemStack getItem();

    Action<InventoryClickEvent> getAction();

    interface Builder {

        Builder item(ItemStack item);

        Builder action(Action<InventoryClickEvent> action);

        Builder slot(int slot);

        static Builder create(ItemStack item) {
            return new SimpleGuiItem.Builder(item);
        }

        static Builder create() {
            return new SimpleGuiItem.Builder();
        }

        GuiItem build();

    }

}
