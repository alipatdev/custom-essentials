package com.transcendence.customchat.commands;

import com.transcendence.customchat.config.ManagerConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements GenericCommand {

    private final ManagerConfig config;

    public ReloadCommand(ManagerConfig config) {
        this.config = config;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if (sender.hasPermission("customchat.reload")) {
            config.reloadConfig();
            sender.sendMessage(ChatColor.GREEN + "Config aggiornato correttamente.");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "Non hai i permessi per eseguire questo comando.");
        return true;
    }
}
