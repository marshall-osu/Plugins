package me.Tanner.SofishticatedFishing.TabCompleters;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TestingTabCompletion implements TabCompleter {
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> completions = new ArrayList<>();
		if (args.length == 1) {
			String[] commands = { "alien", "ancientarmour", "ancientwar", "bait", "cod", "coldice", "decoybear",
					"enderrider", "enderspawn", "hunter", "pelt", "mama", "reinforcedrod", "rod1", "rod2", "rod3",
					"rod4", "rod5", "rod6", "sailor", "salmonbar", "salmonessence", "salmonaxe", "salmonpickaxe",
					"salmonshovel", "scale", "stuffing", "axehead", "bone", "reinforcedrod", "squid", "tentacle", "ufo",
					"ufoshard", "waterloggedplank", "waterp", "bossspawn1", "bossspawn2", "cloth", "death",
					"abomination", "lumberjack", "sonsword", "sonsarmour", "soul", "seventhson", "rod7", "rod8" };
			byte b;
			int i;
			String[] arrayOfString1;
			for (i = (arrayOfString1 = commands).length, b = 0; b < i;) {
				String command = arrayOfString1[b];
				if (command.contains(args[0]))
					completions.add(command);
				b++;
			}
			return completions;
		}
		return null;
	}
}
