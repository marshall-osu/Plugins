package me.Tanner.SofishticatedFishing.Events;

import java.util.Random;
import me.Tanner.SofishticatedFishing.Main;
import me.Tanner.SofishticatedFishing.HelperMethods.DropItemMethods;
import me.Tanner.SofishticatedFishing.HelperMethods.SpawnMobMethods;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class FishEvent implements Listener {
	Main plugin;

	SpawnMobMethods spawns;

	DropItemMethods items;

	public FishEvent(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, (Plugin) plugin);
		this.plugin = plugin;
		this.items = this.plugin.items;
		this.spawns = new SpawnMobMethods(this.plugin);
	}

	@EventHandler
	public void onFish(PlayerFishEvent event) {
		Player player = event.getPlayer();
		if (event.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)) {
			boolean gotItem = itemFishedUp(player.getInventory().getItemInMainHand(), event.getPlayer(),
					(Entity) event.getHook());
			if (this.plugin.activeTournament) {
				int length = getLength();
				if (!gotItem) {
					ItemStack tournDrop = this.plugin.lootTable("tournfish");
					if (tournDrop.getType() != Material.AIR) {
						this.items.giveItem(tournDrop, player);
						player.sendMessage(
								String.valueOf(this.plugin.header) + " looks like you've caught something special!");
					}
				}
				player.sendMessage(ChatColor.translateAlternateColorCodes('&',
						"&7You've caught a fish thats &3&l" + length + " &7cm long!"));
				if (!this.plugin.tournament.containsKey(player.getName())
						|| ((Integer) this.plugin.tournament.get(player.getName())).intValue() < length)
					this.plugin.tournament.put(player.getName(), Integer.valueOf(length));
			}
		}
	}

	private boolean itemFishedUp(ItemStack rod, Player player, Entity hook) {
		Boolean bigFish = Boolean.valueOf(false);
		Random spawnmob = new Random();
		if (rod.hasItemMeta() && rod.getItemMeta().hasLore()
				&& rod.getItemMeta().getLore().equals(this.items.secondFishingRod().getItemMeta().getLore())) {
			int spawn = spawnmob.nextInt(100);
			if (spawn < 20) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnSquid(player);
			}
		} else if (rod.hasItemMeta() && rod.getItemMeta().hasLore()
				&& rod.getItemMeta().getLore().equals(this.items.thirdFishingRod().getItemMeta().getLore())) {
			int spawn = spawnmob.nextInt(100);
			if (spawn < 10) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnSailor(player);
			} else if (spawn < 20) {
				this.spawns.spawnSquid(player);
				bigFish = Boolean.valueOf(true);
			}
		} else if (rod.hasItemMeta() && rod.getItemMeta().hasLore()
				&& rod.getItemMeta().getLore().equals(this.items.fourthFishingRod().getItemMeta().getLore())) {
			int spawn = spawnmob.nextInt(100);
			if (spawn < 5) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnSquid(player);
			} else if (spawn < 10) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnSailor(player);
			} else if (spawn < 15) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnWarrior(player);
			}
		} else if (rod.hasItemMeta() && rod.getItemMeta().hasLore()
				&& rod.getItemMeta().getLore().equals(this.items.fifthFishingRod().getItemMeta().getLore())) {
			int spawn = spawnmob.nextInt(100);
			if (spawn < 7) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnAlien(player);
			} else if (spawn < 10) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnSailor(player);
			} else if (spawn < 14) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnWarrior(player);
			}
		} else if (rod.hasItemMeta() && rod.getItemMeta().hasLore()
				&& rod.getItemMeta().getLore().equals(this.items.sixthFishingRod().getItemMeta().getLore())) {
			int spawn = spawnmob.nextInt(100);
			if (spawn < 7) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnMamaBear(player);
			} else if (spawn < 10) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnAlien(player);
			} else if (spawn < 14) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnWarrior(player);
			}
		} else if (rod.hasItemMeta() && rod.getItemMeta().hasLore()
				&& rod.getItemMeta().getLore().equals(this.items.salmonRod().getItemMeta().getLore())) {
			int spawn = spawnmob.nextInt(100);
			if (spawn < 7) {
				bigFish = Boolean.valueOf(true);
				this.spawns.LostLumberjack(player);
			} else if (spawn < 10) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnMamaBear(player);
			} else if (spawn < 14) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnAlien(player);
			}
		} else if (rod.hasItemMeta() && rod.getItemMeta().hasLore()
				&& rod.getItemMeta().getLore().equals(this.items.PrecursorRod().getItemMeta().getLore())) {
			int spawn = spawnmob.nextInt(100);
			if (spawn < 7) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnSeventhSon(player);
			} else if (spawn < 10) {
				bigFish = Boolean.valueOf(true);
				this.spawns.LostLumberjack(player);
			} else if (spawn < 14) {
				bigFish = Boolean.valueOf(true);
				this.spawns.spawnMamaBear(player);
			}
		}
		if (bigFish.booleanValue())
			player.sendMessage(String.valueOf(this.plugin.header) + "WHOA that's a big fish!");
		return this.plugin.commandTable(player);
	}

	private int getLength() {
		Random randomMultiplier = new Random();
		double length = (new Random()).nextDouble() * 100.0D + 1.0D;
		double chance = randomMultiplier.nextDouble();
		if (chance < 0.01D) {
			length *= 100.0D;
		} else if (chance < 0.1D) {
			length *= 10.0D;
		}
		return (int) length;
	}
}
