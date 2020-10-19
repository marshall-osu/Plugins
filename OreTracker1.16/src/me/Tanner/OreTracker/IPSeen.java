package me.Tanner.OreTracker;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class IPSeen implements CommandExecutor, Listener{
	
	private Main plugin;

	public IPSeen(Main plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("ipseen")) {
			if(sender.hasPermission("oretracker.ipseen")) {
				if(args.length == 1) {
					OfflinePlayer player = this.plugin.getServer().getOfflinePlayer(args[0]);
					if(player != null) {
						List<UUID> ip = new ArrayList<>();
						UUID name = player.getUniqueId();
						for(List<UUID> list : this.plugin.tracker.values()) {
							if(list.contains(name)) {
								ip = list;
								break;
							}
						}
						sender.sendMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Players from " + args[0] + "'s ip");
						for(UUID play : ip) {
							sender.sendMessage(ChatColor.ITALIC + "- " + this.plugin.getServer().getOfflinePlayer(play).getName());
						}
						return true;
					}
					sender.sendMessage(ChatColor.RED + "Player doesn't exist");
					return true;
				}
				sender.sendMessage(ChatColor.RED + "Use the format, /ipseen [player]");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "You don't have permission for this command");
			return true;
		}
		return false;
	}

	@EventHandler
	public void onJoin(PlayerLoginEvent e) {
		if(this.plugin.tracker.containsKey(e.getAddress().getHostAddress())) {
			List<UUID> players = this.plugin.tracker.get(e.getAddress().getHostAddress());
			if(!players.contains(e.getPlayer().getUniqueId())) {
				players.add(e.getPlayer().getUniqueId());
			}
		} else {
			List<UUID> newP = new ArrayList<>();
			newP.add(e.getPlayer().getUniqueId());
			this.plugin.tracker.put(e.getAddress().getHostAddress(), newP);
		}
	}
}
