package me.Tanner.SofishticatedFishing.TabCompleters;

import java.util.ArrayList;
import java.util.List;
import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class MichaelComplainedCompletion implements TabCompleter {
	Main plugin;

	public MichaelComplainedCompletion(Main plugin) {
		this.plugin = plugin;
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> completions = new ArrayList<>();
		if (args.length == 1) {
			completions.add("recipes");
			return completions;
		}
		return null;
	}
}
