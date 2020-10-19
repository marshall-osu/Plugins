package me.Tanner.SofishticatedFishing.Loot;

import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class EditLoot implements CommandExecutor {
	private Main plugin;

	public EditLoot(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equals("editloot")) {
			if (sender.hasPermission("loot.use")) {
				if (args.length == 3) {
					FileConfiguration config = this.plugin.data.getConfig();
					ConfigurationSection pools = config.getConfigurationSection("pools");
					if (pools.getKeys(false).contains(args[0])) {
						ConfigurationSection table = config.getConfigurationSection("pools." + args[0]);
						if (table.getKeys(false).contains(args[1])) {
							String path = "pools." + args[0] + "." + args[1] + ".weight";
							int oldWeight = config.getInt(path);
							config.set(path, Integer.valueOf(Integer.parseInt(args[2])));
							int total = config.getInt("pools." + args[0] + ".totalweight");
							config.set(String.valueOf(table.getCurrentPath()) + ".totalweight",
									Integer.valueOf(total + Integer.parseInt(args[2]) - oldWeight));
							this.plugin.data.saveConfig();
							sender.sendMessage(ChatColor.GREEN + "Item's weight has successfully been changed!");
						}
						sender.sendMessage(ChatColor.RED + "Error, no Item in the loot table has that identifier!");
						return true;
					}
					sender.sendMessage(ChatColor.RED + "Error, that loot table doesn't exist");
					sender.sendMessage(ChatColor.RED + "Current Tables: " + pools.getKeys(false).toString());
					return true;
				}
				sender.sendMessage(ChatColor.RED + "Error, 'use /editloot [table] [identifier] [weight]'");
				return true;
			}
			sender.sendMessage("Error: You don't have permission to use that command");
			return true;
		}
		return false;
	}
}
