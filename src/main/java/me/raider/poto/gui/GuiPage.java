package me.raider.poto.gui;

import me.raider.poto.Nameable;
import me.raider.poto.gui.item.GuiItem;
import me.raider.poto.gui.item.buttom.Button;
import org.bukkit.inventory.Inventory;

public interface GuiPage extends Nameable {

    int getSlots();

    GuiItem[] getItems();

    Inventory getInventory();

    GuiItem getItemFromSlot(int slot);

    interface Builder {

        Builder slots(int slots);

        Builder addItem(GuiItem item);

        Builder addButton(Button button);

        static Builder create(String name, int slots) {
            return new SimpleGuiPage.Builder(name, slots);
        }

        GuiPage build();

    }
}
