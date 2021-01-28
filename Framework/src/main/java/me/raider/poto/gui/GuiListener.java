package me.raider.poto.gui;

import me.raider.poto.gui.item.GuiItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

public class GuiListener implements Listener {

    private final GuiHandler handler;

    public GuiListener(GuiHandler handler) {
        this.handler=handler;
    }

    @EventHandler
    public void onItemClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        Player player = (Player) event.getWhoClicked();

        Gui gui = handler.getGuiByViewer(player);

        ItemStack clickedItem = event.getCurrentItem();

        if (clickedItem == null || clickedItem.getType() == Material.AIR) {
            return;
        }
        if (gui!=null) {

            event.setCancelled(true);

            GuiPage guiPage = gui.getActualGuiPage(player);
            GuiItem guiItem = guiPage.getItemFromSlot(event.getRawSlot());

            if (guiItem!=null) {
                guiItem.getAction().start(event, gui);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){

        Player player = (Player) event.getPlayer();

        Gui gui = handler.getGuiByViewer(player);

        if (gui!=null) {
                gui.getViewers().remove(player.getUniqueId().toString());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        Gui gui = handler.getGuiByViewer(player);

        if (gui!=null) {
                gui.getViewers().remove(player.getUniqueId().toString());
        }
    }
}
