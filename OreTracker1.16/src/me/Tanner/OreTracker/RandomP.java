package me.Tanner.OreTracker;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RandomP implements CommandExecutor{
	
	Main plugin;
	private int counter = 0;
	private Random rand;
	
	public RandomP(Main plugin) {
		this.plugin = plugin;
		rand = new Random();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(label.equals("randomp")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.hasPermission("oretracker.randomp")) {
					ArrayList<Player> players = new ArrayList<Player>(Bukkit.getOnlinePlayers());
					counter = rand.nextInt(players.size());
					Player rand = players.get(counter);
					player.teleport(rand);
					player.sendMessage(ChatColor.GREEN + "You were teleported to " + rand.getName());
					return true;
				}
				sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Only players can call this command");
		}
		return false;
	}
	
	
}

