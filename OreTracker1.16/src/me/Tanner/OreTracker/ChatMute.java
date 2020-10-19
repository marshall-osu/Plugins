package me.Tanner.OreTracker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ChatMute implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("scmute")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("Console can not perform this command.");
                return true;
            }
            Player player = (Player) sender;
            if(args.length == 0) {
	            if(player.hasPermission("oretracker.scmute")) {
	            	if(Main.scmuted.contains(player)) {
	            		Main.scmuted.remove(player);
	            		player.sendMessage(ChatColor.GOLD + "[Staff] " + ChatColor.DARK_GRAY + ">>" + ChatColor.YELLOW + " StaffChat has been unmuted");
	            	} else {
	            		Main.scmuted.add(player);
	            		player.sendMessage(ChatColor.GOLD + "[Staff] " + ChatColor.DARK_GRAY + ">>" + ChatColor.YELLOW + " StaffChat has been muted");
	            	}
	            	return true;
	            }
	            player.sendMessage(ChatColor.RED+"You do not have permission to use this command.");
	            return true;
            } else {
            	player.sendMessage(ChatColor.RED + "Error in format, use: /ot");
            	return true;
            }
        }
        return false;
	}
	
}
