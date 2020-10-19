package me.Tanner.OreTracker;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class ClearChat implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("clearchat")) {
			if(sender.hasPermission("oretracker.clearchat")) {
				for(Player player : Bukkit.getOnlinePlayers()) {
					for(int i=0;i<100;i++) {
						player.sendMessage("");
					}
					player.sendMessage(ChatColor.GREEN + "Chat has successfully been cleared!");
				}
				return true;
			}
			sender.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
			return true;
		}
		return false;
	}
	
}
