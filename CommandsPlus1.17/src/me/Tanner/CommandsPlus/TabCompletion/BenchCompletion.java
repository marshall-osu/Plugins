package me.Tanner.CommandsPlus.TabCompletion;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class BenchCompletion implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("bench")) {
			List<String> complete = new ArrayList<>();
			complete.add("crafting");
			complete.add("loom");
			complete.add("anvil");
			complete.add("stonecutter");
			complete.add("smithing");
			complete.add("cartography");
			complete.add("grindstone");
			return complete;
		}
		return null;
	}

}
