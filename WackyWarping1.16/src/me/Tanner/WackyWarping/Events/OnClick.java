package me.Tanner.WackyWarping.Events;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import me.Tanner.WackyWarping.Main;
import me.Tanner.WackyWarping.Warp;

public class OnClick implements Listener {

	private Main plugin;

	public OnClick(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}

	@EventHandler
	public void clickEvent(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player && event.getSlotType() != InventoryType.SlotType.OUTSIDE) {
			Player player = (Player) event.getWhoClicked();
			if (event.getView().getTitle().equals(ChatColor.LIGHT_PURPLE + "WackyWarps")) {
				event.setCancelled(true);
				ItemStack item = event.getCurrentItem();
				if (item != null) {
					String key = item.getItemMeta().getDisplayName();
					if (this.plugin.warps.containsKey(key)) {
						Warp warp = this.plugin.warps.get(key);
						OfflinePlayer owner = this.plugin.getServer().getOfflinePlayer(warp.getOwnerId());
						if(!warp.getBans().contains(player.getUniqueId().toString())){
							if (owner.isOnline()) {
								((Player)owner).sendMessage(ChatColor.GREEN + "" + player.getName() + " has teleported to your warp, "
										+ warp.getName());
							}
							player.teleport(warp.getLocation());
						} else {
							player.sendMessage(ChatColor.RED + "You've been banned from this warp");
						}
					} else if (key.equals(ChatColor.LIGHT_PURPLE + "Next Page")) {
						int number = Integer.parseInt(event.getInventory().getItem(45).getItemMeta().getDisplayName());
						if (number < this.plugin.gui.size()) {
							player.closeInventory();
							player.openInventory(this.plugin.gui.get(number));
						}
					} else if (key.equals(ChatColor.LIGHT_PURPLE + "Previous Page")) {
						int number = Integer.parseInt(event.getInventory().getItem(45).getItemMeta().getDisplayName());
						if (number > 1) {
							player.closeInventory();
							player.openInventory(this.plugin.gui.get(number - 2));
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void dragEvent(InventoryDragEvent event) {
		if (event.getView().getTitle().equals(ChatColor.LIGHT_PURPLE + "WackyWarps")) {
			event.setCancelled(true);
		}
	}
}
