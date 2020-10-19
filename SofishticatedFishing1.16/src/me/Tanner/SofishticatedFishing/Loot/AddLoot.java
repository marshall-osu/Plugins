package me.Tanner.SofishticatedFishing.Loot;

import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AddLoot implements CommandExecutor {
	private Main plugin;

	public AddLoot(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equals("addloot")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("loot.use")) {
					if (args.length == 3) {
						FileConfiguration config = this.plugin.data.getConfig();
						ConfigurationSection pools = config.getConfigurationSection("pools");
						if (pools.getKeys(false).contains(args[0])) {
							ConfigurationSection table = config.getConfigurationSection("pools." + args[0]);
							if (!table.getKeys(false).contains(args[1])) {
								String path = "pools." + args[0] + "." + args[1];
								ItemStack item = player.getInventory().getItemInMainHand();
								config.set(String.valueOf(path) + ".itemstack", item);
								config.set(String.valueOf(path) + ".weight",
										Integer.valueOf(Integer.parseInt(args[2])));
								if (item.hasItemMeta()) {
									config.set(String.valueOf(path) + ".name", item.getItemMeta().getDisplayName());
								} else {
									config.set(String.valueOf(path) + ".name", item.getType().toString());
								}
								int total = config.getInt("pools." + args[0] + ".totalweight");
								config.set("pools." + args[0] + ".totalweight",
										Integer.valueOf(total + Integer.parseInt(args[2])));
								this.plugin.data.saveConfig();
								player.sendMessage(
										ChatColor.GREEN + "Item has successfully been added to the loot table");
							} else {
								sender.sendMessage(
										ChatColor.RED + "Error, Item in loot table already contains this identifier");
							}
							return true;
						}
						sender.sendMessage(ChatColor.RED + "Error, that loot table doesn't exist");
						sender.sendMessage(ChatColor.RED + "Current Tables: " + pools.getKeys(false).toString());
						return true;
					}
					sender.sendMessage(ChatColor.RED
							+ "Error, 'use /addloot [table] [identifier] [weight]' while holding the object you want added to the table.");
					return true;
				}
				player.sendMessage("Error: You don't have permission to use this command");
				return true;
			}
			sender.sendMessage("Console can't use this command!");
			return true;
		}
		return false;
	}
}
