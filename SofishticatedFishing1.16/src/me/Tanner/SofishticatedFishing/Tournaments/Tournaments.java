package me.Tanner.SofishticatedFishing.Tournaments;

import me.Tanner.SofishticatedFishing.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class Tournaments implements CommandExecutor {
	Main plugin;

	public Tournaments(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equals("tournaments")) {
			if (sender.hasPermission("tournaments.use")) {
				if (!this.plugin.tournamentsOn) {
					this.plugin.tournTracker = new FishingTournament(this.plugin);
					this.plugin.game = this.plugin.tournTracker.runTaskTimer((Plugin) this.plugin, 0L, 20L);
					sender.sendMessage(ChatColor.GREEN + "Fishing tournaments have been turned on");
				} else {
					this.plugin.game.cancel();
					this.plugin.activeTournament = false;
					sender.sendMessage(ChatColor.GREEN + "Fishing tournaments have been turned off");
				}
				this.plugin.tournamentsOn = !this.plugin.tournamentsOn;
				return true;
			}
			sender.sendMessage("Error: You don't have permission to use this command");
			return true;
		}
		return false;
	}
}
