package com.transcendence.customchat.commands;

import org.bukkit.command.CommandSender;

public interface GenericCommand {

    boolean execute(CommandSender sender, String[] args);

}
