package me.Tanner.OreTracker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Mute implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("ot")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage("Console can not perform this command.");
                return true;
            }
            Player player = (Player) sender;
            if(args.length == 0) {
	            if(player.hasPermission("oretracker.ot")) {
	            	if(Main.muted.contains(player)) {
	            		Main.muted.remove(player);
	            		player.sendMessage(ChatColor.GOLD + "[OreTracker] " + ChatColor.DARK_GRAY + ">>" + ChatColor.YELLOW + " OreTracker has been unmuted");
	            	} else {
	            		Main.muted.add(player);
	            		player.sendMessage(ChatColor.GOLD + "[OreTracker] " + ChatColor.DARK_GRAY + ">>" + ChatColor.YELLOW + " OreTracker has been muted");
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
