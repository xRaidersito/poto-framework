package me.raider.poto.gui.item;

import me.raider.poto.gui.item.action.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SimpleGuiItem implements GuiItem {

    private final int slot;
    private final Action<InventoryClickEvent> action;
    private final ItemStack item;

    public SimpleGuiItem(int slot, Action<InventoryClickEvent> action, ItemStack item) {
        this.slot = slot;
        this.action = action;
        this.item = item;
    }

    @Override
    public int getSlot() {
        return slot;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public Action<InventoryClickEvent> getAction() {
        return action;
    }

    public static class Builder implements GuiItem.Builder {

        private int slot;
        private Action<InventoryClickEvent> action;
        private ItemStack item;

        public Builder(ItemStack item) {
            this.item=item;
        }

        public Builder() {}

        @Override
        public Builder item(ItemStack item) {
            this.item=item;
            return this;
        }

        @Override
        public Builder action(Action<InventoryClickEvent> action) {
            this.action=action;
            return this;
        }

        @Override
        public Builder slot(int slot) {
            this.slot=slot;
            return this;
        }

        @Override
        public GuiItem build() {
            return new SimpleGuiItem(slot, action, item);
        }
    }

}
