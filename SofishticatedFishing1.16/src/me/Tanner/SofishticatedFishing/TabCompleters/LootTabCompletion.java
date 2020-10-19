package me.Tanner.SofishticatedFishing.TabCompleters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class LootTabCompletion implements TabCompleter {
	Main plugin;

	public LootTabCompletion(Main plugin) {
		this.plugin = plugin;
	}

	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> completions = new ArrayList<>();
		if (args.length == 1) {
			Set<String> tables = this.plugin.data.getConfig().getConfigurationSection("pools").getKeys(false);
			for (String table : tables) {
				if (table.contains(args[0]))
					completions.add(table);
			}
			return completions;
		}
		return null;
	}
}
