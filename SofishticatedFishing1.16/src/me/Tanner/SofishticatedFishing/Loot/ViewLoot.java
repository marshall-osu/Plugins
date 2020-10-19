package me.Tanner.SofishticatedFishing.Loot;

import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;

public class ViewLoot implements CommandExecutor {
	private Main plugin;

	public ViewLoot(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equals("viewloot")) {
			if (sender.hasPermission("loot.use")) {
				if (args.length == 1) {
					ConfigurationSection pools = this.plugin.data.getConfig().getConfigurationSection("pools");
					if (pools.getKeys(false).contains(args[0])) {
						ConfigurationSection table = pools.getConfigurationSection(args[0]);
						sender.sendMessage("Here's the table!");
						sender.sendMessage("Total of weights = " + table.getInt("totalweight"));
						for (String item : table.getKeys(false)) {
							if (!item.equals("totalweight")) {
								sender.sendMessage(ChatColor.DARK_GRAY + "------------------------");
								sender.sendMessage("Identifier: " + ChatColor.LIGHT_PURPLE + item);
								sender.sendMessage("Item Name: " + ChatColor.YELLOW
										+ table.getString(String.valueOf(item) + ".name"));
								sender.sendMessage("Item Stack: " + ChatColor.YELLOW
										+ table.getString(String.valueOf(item) + ".itemstack"));
								sender.sendMessage("Weight: " + ChatColor.YELLOW
										+ table.getString(String.valueOf(item) + ".weight"));
								sender.sendMessage(ChatColor.DARK_GRAY + "------------------------");
								sender.sendMessage("");
							}
						}
						return true;
					}
					sender.sendMessage(ChatColor.RED + "Error, that loot table doesn't exist");
					sender.sendMessage(ChatColor.RED + "Current Tables: " + pools.getKeys(false).toString());
					return true;
				}
				sender.sendMessage(ChatColor.RED + "Error, 'use /viewloot [table]'");
				return true;
			}
			sender.sendMessage("Error: You don't have permission to use this command");
			return true;
		}
		return false;
	}
}
