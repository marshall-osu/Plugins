package me.Tanner.WackyWarping.TabCompletion;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.Tanner.WackyWarping.Main;
import me.Tanner.WackyWarping.Warp;

public class TabCompletion implements TabCompleter {
	
	private Main plugin;
	
	public TabCompletion(Main plugin) {
		this.plugin = plugin;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		
		List<String> complete = new ArrayList<>();
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 1) {
				complete.add("create");
				complete.add("delete");
				complete.add("set");
				complete.add("ban");
				if(args[0].length() > 0) {
					complete.addAll(this.plugin.warps.keySet());
				}
				return complete;
			}
			if(args.length == 2) {
				if(args[0].equals("delete")) {
					if(this.plugin.ownerLookup.containsKey(player.getUniqueId())) {
						for( Warp owned : this.plugin.ownerLookup.get(player.getUniqueId())) {
							complete.add(owned.getName());
						}
					}
					return complete;
				}
				if(args[0].equals("set")) {
					complete.add("icon");
					complete.add("description");
					complete.add("owner");
					complete.add("name");
					complete.add("location");
					return complete;
				}
			}
			if(args.length == 3 && args[0].equals("set")) {
				if(this.plugin.ownerLookup.containsKey(player.getUniqueId())) {
					for( Warp owned : this.plugin.ownerLookup.get(player.getUniqueId())) {
						complete.add(owned.getName());
					}
				}
				return complete;
			}
		}
		return complete;
	}
}
