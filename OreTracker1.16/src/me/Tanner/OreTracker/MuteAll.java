package me.Tanner.OreTracker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MuteAll implements CommandExecutor{
	
	Main plugin;
	
	public MuteAll(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equals("muteall")) {
			if(sender.hasPermission("oretracker.muteall")) {
				this.plugin.stopChat = !this.plugin.stopChat;
				if (this.plugin.stopChat){
					sender.sendMessage(ChatColor.GREEN + "Chat has been muted");
				} else {
					sender.sendMessage(ChatColor.GREEN + "Chat has been unmuted");
				}
				
				return true;
			}
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
			return true;
		}
		return false;
	}
	
	
}
