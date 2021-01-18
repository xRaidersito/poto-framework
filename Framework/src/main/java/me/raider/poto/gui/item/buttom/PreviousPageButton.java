package me.raider.poto.gui.item.buttom;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PreviousPageButton extends Button {

    public PreviousPageButton(int slot, ItemStack item, boolean rightClick, boolean leftClick) {
        super(slot,
                (inventoryClickEvent, gui) -> {
                    if (rightClick && inventoryClickEvent.isRightClick()) {
                        gui.previousPage((Player) inventoryClickEvent.getWhoClicked());
                    }
                    else if (leftClick && inventoryClickEvent.isLeftClick()) {
                        gui.previousPage((Player) inventoryClickEvent.getWhoClicked());
                    }

                }, item);
    }

}
