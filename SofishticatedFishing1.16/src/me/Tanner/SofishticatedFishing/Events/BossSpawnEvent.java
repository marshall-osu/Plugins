package me.Tanner.SofishticatedFishing.Events;

import me.Tanner.SofishticatedFishing.Main;
import me.Tanner.SofishticatedFishing.HelperMethods.DropItemMethods;
import me.Tanner.SofishticatedFishing.HelperMethods.SpawnMobMethods;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;

public class BossSpawnEvent implements Listener {
	Main plugin;

	SpawnMobMethods spawns;

	DropItemMethods items;

	public BossSpawnEvent(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, (Plugin) plugin);
		this.plugin = plugin;
		this.spawns = this.plugin.spawns;
		this.items = this.plugin.items;
	}

	@EventHandler
	public void onClick(PlayerInteractEvent event) {
		if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.STICK)) {
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				Player player = event.getPlayer();
				if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					event.getPlayer().sendMessage(String.valueOf(this.plugin.header) + "The ender rider has awoken!");
					this.spawns.enderRider(player);
					player.getInventory().getItemInMainHand()
							.setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
				}
			}
		} else if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.EMERALD)) {
			if (event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
				Player player = event.getPlayer();
				if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
					event.getPlayer().sendMessage(String.valueOf(this.plugin.header) + "What's that in the sky!");
					this.spawns.UFO(player);
					player.getInventory().getItemInMainHand()
							.setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
				}
			}
		} else if (event.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.LEATHER_HORSE_ARMOR)
				&& event.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasLore()) {
			Player player = event.getPlayer();
			if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				event.getPlayer().sendMessage(
						String.valueOf(this.plugin.header) + "Hunter has arrived and he doesn't look too happy!");
				this.spawns.hunter(player);
				player.getInventory().getItemInMainHand()
						.setAmount(player.getInventory().getItemInMainHand().getAmount() - 1);
			}
		}
	}
}
