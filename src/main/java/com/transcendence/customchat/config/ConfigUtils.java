package com.transcendence.customchat.config;

import com.transcendence.customchat.utils.LuckUtils;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Set;

public class ConfigUtils {

    private final ManagerConfig config;
    private final LuckPerms luckPerms;

    public ConfigUtils(ManagerConfig config, LuckPerms luckPerms) {
        this.config = config;
        this.luckPerms = luckPerms;
    }

    public void setChatFormat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        User user = LuckUtils.getLuckPermsUser(player, luckPerms);
        String message = event.getMessage();

        ConfigurationSection section = config.getConfig().getConfigurationSection("chat.format");
        Set<String> formatKeys = section.getKeys(false);

        String format = "";
        for (String key : formatKeys) {
            if (user.getPrimaryGroup().equals(key)) {
                format = section.getString(key);
                format = getFormattedString(format, user, player, message);
                event.setFormat(format);
                break;
            }
        }
    }

    private String getFormattedString(String format, User user, Player player, String message) {
        String prefix = LuckUtils.getUserPrefix(user);
        String suffix = LuckUtils.getUserSuffix(user);
        String username = player.getDisplayName();

        format = format.replace("[PREFIX]", prefix != null ? prefix : "")
                .replace("[SUFFIX]", suffix != null ? suffix : "")
                .replace("[USERNAME]", username)
                .replace("[MESSAGE]", message);

        format = ChatColor.translateAlternateColorCodes('&', format);
        return format;
    }

}
