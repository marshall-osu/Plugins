package me.Tanner.SofishticatedFishing.Loot;

import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class DeleteLoot implements CommandExecutor {
	private Main plugin;

	public DeleteLoot(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equals("deleteloot")) {
			if (sender.hasPermission("loot.use")) {
				if (args.length == 2) {
					FileConfiguration config = this.plugin.data.getConfig();
					ConfigurationSection pools = config.getConfigurationSection("pools");
					if (pools.getKeys(false).contains(args[0])) {
						ConfigurationSection table = config.getConfigurationSection("pools." + args[0]);
						if (table.getKeys(false).contains(args[1])) {
							String path = "pools." + args[0] + "." + args[1];
							int total = config.getInt("pools." + args[0] + ".totalweight");
							int weight = config.getInt(String.valueOf(path) + ".weight");
							config.set(path, null);
							config.set("pools." + args[0] + ".totalweight", Integer.valueOf(total - weight));
							this.plugin.data.saveConfig();
							sender.sendMessage(ChatColor.GREEN + "Item has successfully been removed from the "
									+ args[0] + " table!");
							return true;
						}
						sender.sendMessage(ChatColor.RED + "Error, no Item in the loot table has that identifier!");
						return true;
					}
					sender.sendMessage(ChatColor.RED + "Error, that loot table doesn't exist");
					sender.sendMessage(ChatColor.RED + "Current Tables: " + pools.getKeys(false).toString());
					return true;
				}
				sender.sendMessage(ChatColor.RED + "Error, 'use /deleteloot [table] [identifier]'");
				return true;
			}
			sender.sendMessage("Error: You don't have permission to use this command");
			return true;
		}
		return false;
	}
}
