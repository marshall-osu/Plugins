package me.Tanner.WackyWarping.Events;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

import me.Tanner.WackyWarping.Main;
import me.Tanner.WackyWarping.Warp;
import net.milkbowl.vault.economy.plugins.Economy_Essentials;

public class OnChat implements Listener {

	private Main plugin;
	private Economy_Essentials methods;

	public OnChat(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
		methods = new Economy_Essentials(plugin);
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (this.plugin.confirmations.containsKey(player.getName())) {
			event.setCancelled(true);
			Warp warp = this.plugin.confirmations.remove(player.getName());

			if (ChatColor.stripColor(event.getMessage()).equals("y")) {
				if (methods.getBalance(player) > this.plugin.cost) {
					methods.withdrawPlayer(player, this.plugin.cost + 0.0);
					this.plugin.warps.put(warp.getName(), warp);
					if (this.plugin.ownerLookup.containsKey(player.getUniqueId())) {
						this.plugin.ownerLookup.get(player.getUniqueId()).add(warp);
					} else {
						Set<Warp> temp = new HashSet<>();
						temp.add(warp);
						this.plugin.ownerLookup.put(player.getUniqueId(), temp);
					}
					this.plugin.addToInventory(warp.getIcon());

					player.sendMessage(ChatColor.GREEN + "Warp successfully created!");
				}
			}
		} else if (this.plugin.confirmationsOwner.containsKey(player.getName())) {
			event.setCancelled(true);
			Warp warp = this.plugin.confirmationsOwner.remove(player.getName());

			if (ChatColor.stripColor(event.getMessage()).equals("y")) {
				Player newPlayer = this.plugin.confirmationsOwnerName.remove(player.getName());
				ItemStack old = warp.getIcon();
				warp.setOwner(newPlayer.getName());
				warp.setOwnerId(newPlayer.getUniqueId());
				this.plugin.command.updateGUI(old, warp.getIcon());
				player.sendMessage(ChatColor.GREEN + "Warp owner successfully changed!");
			}
		} else if (this.plugin.confirmationsDelete.containsKey(player.getName())) {
			event.setCancelled(true);
			Warp warp = this.plugin.confirmationsDelete.remove(player.getName());

			if (ChatColor.stripColor(event.getMessage()).equals("y")) {
				this.plugin.warps.remove(warp.getName());
				this.plugin.ownerLookup.get(player.getUniqueId()).remove(warp);
				this.plugin.deleteIcon(warp.getIcon());
				player.sendMessage(ChatColor.GREEN + "Warp successfully deleted!");
				this.plugin.reloadInv();
			}
		}
	}
}
