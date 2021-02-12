package me.raider.poto.storage.types.flat.yaml;

import com.google.common.util.concurrent.ListeningExecutorService;
import me.raider.poto.file.YamlFile;
import me.raider.poto.serializer.Serializer;
import me.raider.poto.storage.AbstractStorage;
import me.raider.poto.storage.types.Storable;
import me.raider.poto.storage.StorageType;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Map;

public abstract class YamlStorage<T extends Storable> extends AbstractStorage<T> {

    private final String folder;
    private final Plugin plugin;

    public YamlStorage(String name, Serializer<T> serializedObject, String folder, Plugin plugin, ListeningExecutorService executorService) {
        super(name, StorageType.YAML, serializedObject, executorService);
        this.folder=folder;
        this.plugin = plugin;
    }

    @Override
    public T load(String key) {

        YamlFile file = new YamlFile(plugin, key, ".yml", new File(plugin.getDataFolder().getAbsolutePath() + folder));

        if (!file.contains("data")) {
            return createIfAbsent(key);
        }

        Map<String, Object> dataMap = file.getValues(true);

        T object = getSerializer().deserialize(dataMap).createWithData();

        get().put(key, object);

        return object;
    }

    @Override
    public void save(String key) {

        YamlFile file = new YamlFile(plugin, key, ".yml", new File(plugin.getDataFolder().getAbsolutePath() + folder));

        T toSerialize = get().get(key);

        if (toSerialize!=null) {

            file.set("data", getSerializer().serialize(toSerialize).getLinkedMap());

            get().remove(key);
        }
    }
}
