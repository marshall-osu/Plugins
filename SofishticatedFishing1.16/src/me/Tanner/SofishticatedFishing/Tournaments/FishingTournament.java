package me.Tanner.SofishticatedFishing.Tournaments;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class FishingTournament extends BukkitRunnable {
	private final Main plugin;

	private final String[] placements = new String[] { "&6&l1st", "&e&l2nd", "&a&l3rd" };

	public final int totalTime = 9000;

	public int counter = 9000;

	public final int tournTime = 600;

	public FishingTournament(Main plugin) {
		this.plugin = plugin;
	}

	public void run() {
		if (this.plugin.forceTourn) {
			this.counter = 600;
			this.plugin.forceTourn = false;
		} else if (this.plugin.stopTourn) {
			this.counter = 9000;
			this.plugin.tournament.clear();
			this.plugin.stopTourn = false;
		}
		if (this.counter == 600) {
			for (Player player : this.plugin.getServer().getOnlinePlayers()) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&7&lA fishing tourney is beginning. &3&lGood Luck!"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&lGoal: &3Catch the longest fish"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
			}
			this.plugin.activeTournament = true;
		} else if (this.counter == 150 || this.counter == 450 || this.counter == 300) {
			status();
		} else if (this.counter == 0) {
			this.counter = 9000;
			this.plugin.activeTournament = false;
			List<Map.Entry<String, Integer>> winners = findGreatest(this.plugin.tournament, 3);
			for (Player player : this.plugin.getServer().getOnlinePlayers()) {
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&lTournament Status: &3Time's Up!"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Here are your winners:"));
				for (int k = winners.size() - 1, m = 0; k >= 0; k--, m++)
					player.sendMessage(ChatColor.translateAlternateColorCodes('&',
							String.valueOf(this.placements[m]) + " - &7" + (String) (winners.get(k)).getKey() + " &f(&e"
									+ (winners.get(k)).getValue() + " &fcm)"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
			}
			this.plugin.tournament.clear();
			String[] tables = { "first", "second", "third" };
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			for (int i = winners.size() - 1, j = 0; i >= 0; i--, j++) {
				String command = this.plugin.data.getConfig().getString("commands." + tables[j]);
				command = command.replace("[player]", (CharSequence) (winners.get(i)).getKey());
				Bukkit.dispatchCommand((CommandSender) console, command);
			}
		}
		this.counter--;
	}

	private static <K, V extends Comparable<? super V>> List<Map.Entry<K, V>> findGreatest(Map<K, V> map, int n) {
		Comparator<? super Map.Entry<K, V>> comparator = new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> e0, Map.Entry<K, V> e1) {
				return e0.getValue().compareTo(e1.getValue());
			}
		};
		PriorityQueue<Map.Entry<K, V>> highest = new PriorityQueue<>(n, comparator);
		for (Map.Entry<K, V> entry : map.entrySet()) {
			highest.offer(entry);
			while (highest.size() > n && map.size() > 0)
				highest.poll();
		}
		List<Map.Entry<K, V>> result = new ArrayList<>();
		while (highest.size() > 0)
			result.add(highest.poll());
		result.sort(comparator);
		return result;
	}

	public void status() {
		List<Map.Entry<String, Integer>> winners = findGreatest(this.plugin.tournament, 3);
		for (Player player : this.plugin.getServer().getOnlinePlayers()) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&',
					"&7&lTournament Status: &3" + this.counter + " seconds left"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
			for (int i = winners.size() - 1, j = 0; i >= 0; i--, j++)
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						String.valueOf(this.placements[j]) + " - &7" + (String) (winners.get(i)).getKey() + " &f(&e"
								+ (winners.get(i)).getValue() + " &fcm)"));
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
		}
	}

	public void status(CommandSender player) {
		List<Map.Entry<String, Integer>> winners = findGreatest(this.plugin.tournament, 3);
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&',
				"&7&lTournament Status: &3" + this.counter + " seconds left"));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
		for (int i = winners.size() - 1, j = 0; i >= 0; i--, j++)
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', String.valueOf(this.placements[j]) + " - &7"
					+ (String) (winners.get(i)).getKey() + " &f(&e" + (winners.get(i)).getValue() + " &fcm)"));
		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f&l&m-------------------"));
	}
}
