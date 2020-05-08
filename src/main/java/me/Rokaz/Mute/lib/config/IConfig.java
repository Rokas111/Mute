package me.Rokaz.Mute.lib.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public interface IConfig {
    File getFile();
    YamlConfiguration getYaml();
    void setup();
    void save();
    void reload();
}
