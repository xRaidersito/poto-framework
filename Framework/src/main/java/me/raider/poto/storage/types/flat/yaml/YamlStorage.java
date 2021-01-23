package me.raider.poto.storage.types.flat.yaml;

import me.raider.poto.file.YamlFile;
import me.raider.poto.internal.SerializableObject;
import me.raider.poto.storage.AbstractStorage;
import me.raider.poto.storage.types.Storable;
import me.raider.poto.storage.StorageType;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Map;

public abstract class YamlStorage<T extends Storable> extends AbstractStorage<T> {

    private final String folder;
    private final Plugin plugin;

    public YamlStorage(String name, SerializableObject<T> serializableObject, String folder, Plugin plugin) {
        super(name, StorageType.YAML, serializableObject);
        this.folder=folder;
        this.plugin = plugin;
    }

    @Override
    public void load(String key) {

        YamlFile file = new YamlFile(plugin, key, ".yml", new File(plugin.getDataFolder().getAbsolutePath() + folder));

        if (!file.contains("data")) {
            createIfAbsent(key);
            return;
        }

        Map<String, Object> dataMap = file.getValues(true);

        get().put(key, getSerializable().deserialize(dataMap));

    }

    @Override
    public void save(String key) {

        YamlFile file = new YamlFile(plugin, key, ".yml", new File(plugin.getDataFolder().getAbsolutePath() + folder));

        T toSerialize = get().get(key);

        if (toSerialize!=null) {
            try {
                file.set("data.", getSerializable().serialize(toSerialize.getClass(), toSerialize, true));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            get().remove(key);
        }
    }
}