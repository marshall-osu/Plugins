package me.Tanner.SofishticatedFishing.Tournaments;

import me.Tanner.SofishticatedFishing.Main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TournamentStatus implements CommandExecutor {
	Main plugin;

	public TournamentStatus(Main plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equals("tournamentstatus")) {
			if (!this.plugin.tournamentsOn) {
				sender.sendMessage(ChatColor.YELLOW + "Fishing tournaments are currently off");
			} else if (this.plugin.activeTournament) {
				this.plugin.tournTracker.status(sender);
			} else {
				this.plugin.tournTracker.getClass();
				sender.sendMessage(ChatColor.YELLOW + "There are " + (this.plugin.tournTracker.counter - 600)
						+ "seconds till a tournament starts!");
			}
			return true;
		}
		return false;
	}
}
