package com.transcendence.customchat.listener;

import com.transcendence.customchat.CustomChat;
import com.transcendence.customchat.config.ConfigUtils;
import com.transcendence.customchat.config.ManagerConfig;
import net.luckperms.api.LuckPerms;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class ManagerListener implements Listener {

    private final LuckPerms luckPerms;
    private final ManagerConfig config;

    public ManagerListener() {
        luckPerms = CustomChat.getLuckPerms();
        config = CustomChat.getManagerConfig();
    }

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        ConfigUtils configUtils = new ConfigUtils(config, luckPerms);
        configUtils.setChatFormat(event);
    }

}
