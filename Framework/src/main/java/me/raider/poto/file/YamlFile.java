package me.raider.poto.file;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

public class YamlFile extends YamlConfiguration {

    private final String fileName;

    private final Plugin plugin;

    private File folder;

    public YamlFile(Plugin plugin, String fileName, String fileExtension, File folder) {
        this.folder = folder;
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(fileExtension) ? "" : fileExtension);
        createFile();
    }

    public YamlFile(Plugin plugin, String fileName) {

        this(plugin, fileName, ".yml");
    }

    public YamlFile(Plugin plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }

    private void createFile() {
        try {
            File file = new File(this.folder, this.fileName);
            if (file.exists()) {
                load(file);
                save(file);
                System.out.println("hola");
                return;
            }
            if (this.plugin.getResource(this.fileName) != null) {
                this.plugin.saveResource(this.fileName, false);
            } else {
                save(file);
            }
            load(file);
        } catch (InvalidConfigurationException | IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Creation of Configuration '" + this.fileName + "' failed.", e);
        }
    }

    public void save() {

        checkFolder();
        File file = new File(folder, this.fileName);
        try {
            save(file);
        } catch (IOException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Save of the file '" + this.fileName + "' failed.", e);
        }
    }

    public void reload() {
        checkFolder();
        File file = new File(folder, this.fileName);
        try {
            load(file);
        } catch (IOException|InvalidConfigurationException e) {
            this.plugin.getLogger().log(Level.SEVERE, "Reload of the file '" + this.fileName + "' failed.", e);
        }
    }

    private void checkFolder() {
        if (folder==null) {
            folder = this.plugin.getDataFolder();
        }
    }

}
