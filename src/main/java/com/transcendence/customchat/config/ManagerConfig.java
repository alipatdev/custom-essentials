package com.transcendence.customchat.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ManagerConfig {

    private final JavaPlugin plugin;
    private YamlConfiguration config;

    public ManagerConfig(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void saveDefaultFiles() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");

        if (!configFile.exists())
            plugin.saveResource("config.yml", false);

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void reloadConfig() {
        try {
            config.load(new File(plugin.getDataFolder(), "config.yml"));
        } catch (InvalidConfigurationException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public YamlConfiguration getConfig() {
        return config;
    }

}
