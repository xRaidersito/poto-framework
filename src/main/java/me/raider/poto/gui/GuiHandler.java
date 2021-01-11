package me.raider.poto.gui;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class GuiHandler {

    private Map<String, Gui> guiMap = new HashMap<>();

    public Map<String, Gui> get() {
        return guiMap;
    }

    public Gui getGuiByViewer(Player viewer) {

        for (String key : get().keySet()) {

            Gui gui = get().get(key);

            for (String viewing : gui.getViewers().keySet()) {

                if (viewing.equals(viewer.getUniqueId().toString())) {
                    return gui;
                }
            }
        }
        return null;
    }
}
