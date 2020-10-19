package me.Tanner.PotionEffects;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

public class Potion implements CommandExecutor {

	private Main plugin;
	
	public Potion(Main plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equals("potioneffect")) {
			if(args.length >= 1) {
				if(sender instanceof Player) {
					Player player = (Player) sender;
					if(player.hasPermission("potioneffect." + args[0] + ".use")) {
						int power;
						try {
							power = Integer.parseInt(args[1]) -1;
						} catch (NumberFormatException e) {
							power = 0;
						} catch (IndexOutOfBoundsException e) {
							power = 0;
						}
						PotionEffect effect = this.plugin.getEffect(args[0], power);
						if(effect != null) {
							if(player.hasPotionEffect(effect.getType())) {
								player.removePotionEffect(effect.getType());
								this.plugin.data.getConfig().set("effects." + player.getUniqueId() + "." + args[0], null);
								this.plugin.data.saveConfig();
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lNoble&e&lRealms &8» &e&l" + args[0] + " &7has been removed from &e" + player.getDisplayName()));
							} else {
								player.addPotionEffect(effect);
								this.plugin.data.getConfig().set("effects." + player.getUniqueId() + "." + args[0] + ".power", power);
								this.plugin.data.saveConfig();
								player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lNoble&e&lRealms &8» &e&l" + args[0] + " &7has been added to &e" + player.getDisplayName()));
							}
							return true;
						}
						player.sendMessage(ChatColor.RED + "Error: Use a real potion effect");
						return true;
					}
					player.sendMessage(ChatColor.RED + "Error: Your don't have permission to use that potion effect");
				}
				sender.sendMessage(ChatColor.RED + "Error: Console cannot use this command");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Error: Use format /potion [effect] [strength]");
			return true;
		}
		return false;
	}
	
}
