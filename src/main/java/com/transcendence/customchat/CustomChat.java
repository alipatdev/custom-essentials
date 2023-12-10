package com.transcendence.customchat;

import com.transcendence.customchat.commands.ManagerCommand;
import com.transcendence.customchat.config.ManagerConfig;
import com.transcendence.customchat.listener.ManagerListener;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class CustomChat extends JavaPlugin {

    private static LuckPerms luckPerms;
    private static CustomChat instance;
    private static ManagerConfig managerConfig;

    @Override
    public void onEnable() {
        setupPlugin();
        registerEvents();
        registerCommands();
        getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }

    public static LuckPerms getLuckPerms() {
        return luckPerms;
    }

    private void registerEvents() {
        getServer().getPluginManager().registerEvents(new ManagerListener(), this);
    }

    private void registerCommands() {
        getCommand("customchat").setExecutor(new ManagerCommand());
    }

    private void setupPlugin() {
        instance = this;
        managerConfig = new ManagerConfig(this);
        luckPerms = LuckPermsProvider.get();
        managerConfig.saveDefaultFiles();
    }

    public static CustomChat getInstance() {
        return instance;
    }

    public static ManagerConfig getManagerConfig() {
        return managerConfig;
    }
}
