package me.Tanner.SofishticatedFishing.Tournaments;

import me.Tanner.SofishticatedFishing.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ForceTournament implements CommandExecutor {
	Main plugin;

	public ForceTournament(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equals("forcetourn")) {
			if (sender.hasPermission("tournaments.use")) {
				if (this.plugin.tournamentsOn) {
					this.plugin.forceTourn = true;
					sender.sendMessage(ChatColor.GREEN + "Force Tournament has been Successful");
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
