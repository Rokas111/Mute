package me.Rokaz.Mute.lib.config;

import me.Rokaz.Mute.core.Mute;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class Config implements IConfig {
    private String name;
    private YamlConfiguration yaml;
    public Config(String name) {
        this.name = name;
        reload();
        setup();
    }
    public File getFile() {
        return new File("plugins//"+ Mute.PLUGIN_FOLDER + "//" + name + ".yml");
    }
    public void setup() {
        if (!getFile().exists()) {
            try {
                getFile().createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void save() {
        try {
            yaml.save(getFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public YamlConfiguration getYaml() {
        return this.yaml;
    }
    public void reload() {
        yaml = YamlConfiguration.loadConfiguration(getFile());
    }
}
