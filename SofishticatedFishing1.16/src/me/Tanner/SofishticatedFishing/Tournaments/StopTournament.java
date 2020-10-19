package me.Tanner.SofishticatedFishing.Tournaments;

import me.Tanner.SofishticatedFishing.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StopTournament implements CommandExecutor {
	Main plugin;

	public StopTournament(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equals("stoptourn")) {
			if (sender.hasPermission("tournaments.use")) {
				if (this.plugin.tournamentsOn) {
					this.plugin.stopTourn = true;
					this.plugin.activeTournament = false;
					sender.sendMessage(ChatColor.GREEN + "Stop Tournament has been Successful");
					for (Player player : this.plugin.getServer().getOnlinePlayers()) {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&',
								"&7&lTournament Status: &3" + sender.getName() + " has stopped the tournament!"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
					}
					return true;
				}
				sender.sendMessage(ChatColor.RED + "Error: Tournaments are not turned on");
				return true;
			}
			sender.sendMessage("Error: You don't have permission to use this command");
			return true;
		}
		return false;
	}
}
