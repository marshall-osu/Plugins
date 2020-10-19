package me.Tanner.PotionEffects;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabCompletion implements TabCompleter{

	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		
		List<String> completions = new ArrayList<>();
		if (args.length == 1) {
			String[] effects = {"speed","slowness","haste","miningfatigue","strength","instanthealth","instantdamage", "jumpboost", "nausea", "regeneration", "resistance", "fireresistance", "waterbreathing","invisability", "blindness", "nightvison", "hunger", "weakness", "poison", "wither", "healthboost", "absorption", "saturation", "glowing", "levitation", "luck", "badluck", "slowfalling", "conduitpower", "dolphingrace", "badomen", "hero"};
			for(String effect : effects) {
				completions.add(effect);
			}
			return completions;
		}
		
		return null;
	}

}
