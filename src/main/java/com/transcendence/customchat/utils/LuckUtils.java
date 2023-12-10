package com.transcendence.customchat.utils;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;

public class LuckUtils {

    public static String getUserPrefix(User user) {
        return user.getCachedData().getMetaData().getPrefix();
    }

    public static String getUserSuffix(User user) {
        return user.getCachedData().getMetaData().getSuffix();
    }

    public static User getLuckPermsUser(Player player, LuckPerms luckPerms) {
        return luckPerms.getPlayerAdapter(Player.class).getUser(player);
    }

}
