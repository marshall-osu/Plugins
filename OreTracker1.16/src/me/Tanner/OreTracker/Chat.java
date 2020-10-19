package me.Tanner.OreTracker;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.ChatColor;

public class Chat implements CommandExecutor {

	private Main plugin;
	
	public Chat(Main instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if( label.equalsIgnoreCase("sc")) {
			if( args.length != 0) {
				StringBuilder message = new StringBuilder();
				for(String add: args) {
					message.append(" ");
					message.append(add);
				}
				if(!(sender instanceof Player)) {
					for(World w : this.plugin.getServer().getWorlds()){
					    for(Player p : w.getPlayers()){
					        if(p.hasPermission("oretracker.sc") && !Main.scmuted.contains(p)){
					            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[Staff] &fConsole &8>> &b" + message.toString()) );
					        }
					    }
					}
					return true;
				}
				Player player = (Player) sender;
				if( player.hasPermission("oretracker.sc")) {
					for(World w : this.plugin.getServer().getWorlds()){
					    for(Player p : w.getPlayers()){
					        if(p.hasPermission("oretracker.sc") && !Main.scmuted.contains(p)){
					            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[Staff] &f" + player.getName() + " &8>> &b" + message.toString()));
					        }
					    }
					}
				} else {
					player.sendMessage(ChatColor.RED + "You do not have permission to use this command :(");
				}
				return true;
			}	else {
				sender.sendMessage(ChatColor.RED + "Error, use /sc [message]");
			}
		}
		return false;
	}
	
}
