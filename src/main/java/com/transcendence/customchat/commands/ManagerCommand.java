package com.transcendence.customchat.commands;

import com.transcendence.customchat.CustomChat;
import com.transcendence.customchat.config.ManagerConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class ManagerCommand implements CommandExecutor {

    private final Map<String, GenericCommand> commandMap = new HashMap<>();
    private final ManagerConfig config;

    public ManagerCommand() {
        this.config = CustomChat.getManagerConfig();
        commandMap.put("reload", new ReloadCommand(config));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender.hasPermission("customchat.use")) {
            if (command.getName().equals("customchat")) {
                if (args.length > 0) {
                    GenericCommand genericCommand = commandMap.get(args[0].toLowerCase());
                    if (genericCommand == null)
                        // L'argomento inserito non esiste.
                        return false;
                    return genericCommand.execute(sender, args);
                }
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    player.sendMessage(helpMessage());
                    return true;
                }
                else if (sender instanceof ConsoleCommandSender) {
                    Bukkit.getConsoleSender().sendMessage(helpMessage());
                    return true;
                }
                return false;
            }
            return false;
        }
        sender.sendMessage(ChatColor.RED + "Non hai i permessi per eseguire questo comando.");
        return true;
    }

    private String helpMessage() {
        String helpMessage = "\n" +
                "&bCustomChat 1.0 developed by Transcendence_ \n";
        return ChatColor.translateAlternateColorCodes('&', helpMessage);
    }
}
