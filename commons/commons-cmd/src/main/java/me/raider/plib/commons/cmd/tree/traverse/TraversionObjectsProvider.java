package me.raider.plib.commons.cmd.tree.traverse;

import java.util.HashMap;
import java.util.Map;

public class TraversionObjectsProvider {

    private final Map<String, Object> providers = new HashMap<>();

    public void addProvider(String key, Object provider) {
        this.providers.put(key, provider);
    }

    public Object getProvider(String key) {
        return this.providers.get(key);
    }

}
