package me.raider.poto.gui.item.buttom;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class NextPageButton extends Button {

    public NextPageButton(int slot, ItemStack item, boolean rightClick, boolean leftClick) {
        super(slot,
                (inventoryClickEvent, gui) -> {
                    if (rightClick && inventoryClickEvent.isRightClick()) {
                        gui.nextPage((Player) inventoryClickEvent.getWhoClicked());
                    }
                    else if (leftClick && inventoryClickEvent.isLeftClick()) {
                        gui.nextPage((Player) inventoryClickEvent.getWhoClicked());
                    }

                }, item);
    }
}
