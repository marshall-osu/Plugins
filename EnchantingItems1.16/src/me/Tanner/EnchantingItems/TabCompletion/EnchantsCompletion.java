package me.Tanner.EnchantingItems.TabCompletion;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.Tanner.EnchantingItems.Enchantments;

public class EnchantsCompletion implements TabCompleter{

	Enchantments plugin;
	
	public EnchantsCompletion(Enchantments plugin){
		this.plugin = plugin;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd,
			String label, String[] args) {
		List<String> completions = new ArrayList<>();
		if( args.length == 1) {
			for(Player player : this.plugin.getServer().getOnlinePlayers()) {
				completions.add(player.getName());
			}
			return completions;
		} else if (args.length == 2) {
			String[] commands = {"autosmelt", "autoplank", "hammer", "scyth", "telepathy", "treefeller", "plow", "planter","fertilizer","toxicity","corruption",
					"blinding","chains","crippling","explosive","blackhole","repulsion","egg","fireball","heavy","stripper","lifesteal","decapitation","lavawalker",
					"phdflopper","molten","shotgun","partypopper"};
			for(String command : commands) {
				if(command.contains(args[1])) {
					completions.add(command);
				}
			}
			return completions;
		}
		return null;
	}
}