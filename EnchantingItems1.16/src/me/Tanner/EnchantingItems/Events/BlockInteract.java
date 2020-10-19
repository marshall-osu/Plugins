package me.Tanner.EnchantingItems.Events;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;

import me.Tanner.EnchantingItems.Enchantments;
import me.angeschossen.lands.api.integration.LandsIntegration;
import me.angeschossen.lands.api.land.Land;
import net.coreprotect.CoreProtectAPI;
import net.md_5.bungee.api.ChatColor;

public class BlockInteract implements Listener {

	Enchantments plugin;
	LandsIntegration lands;

	public BlockInteract(Enchantments plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
		lands = new LandsIntegration(this.plugin);
	}

	@EventHandler()
	public void onHoe(PlayerInteractEvent event) {
		boolean worked = false;
		Player player = event.getPlayer();
		ItemStack tool = player.getInventory().getItemInMainHand();

		if (tool != null && tool.hasItemMeta() && tool.getItemMeta().hasLore()) {
			List<String> lore = tool.getItemMeta().getLore();
			if (lore.contains(ChatColor.GRAY + "Plow")) {
				if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					Block block = event.getClickedBlock();
					Location origin = block.getLocation();
					Location loc = block.getLocation();
					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) {
							loc.setX(origin.getX() + i);
							loc.setZ(origin.getZ() + j);
							Block hoe = loc.getBlock();
							if (canBreak(player, loc)) {
								if (hoe.getType().equals(Material.DIRT) || hoe.getType().equals(Material.GRASS_BLOCK)) {
									hoe.setType(Material.FARMLAND);
									log(player, hoe, "interact");

								}
							}
						}
					}
				}
			}
			
			if (lore.contains(ChatColor.GRAY + "Planter")) {
				if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					Block block = event.getClickedBlock();
					Location origin = block.getLocation();
					Location loc = block.getLocation();
					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) {
							loc.setX(origin.getX() + i);
							loc.setZ(origin.getZ() + j);
							Block hoe = loc.getBlock();
							if (canBreak(player, loc)) {
								if (hoe.getType().equals(Material.FARMLAND)) {
									PlayerInventory inv = player.getInventory();
									Material plant = null;
									Material seed = null;
									if (inv.contains(Material.WHEAT_SEEDS)) {
										plant = Material.WHEAT;
										seed = Material.WHEAT_SEEDS;
									} else if (inv.contains(Material.BEETROOT_SEEDS)) {
										plant = Material.BEETROOTS;
										seed = Material.BEETROOT_SEEDS;
									} else if (inv.contains(Material.CARROT)) {
										plant = Material.CARROTS;
										seed = Material.CARROT;
									} else if (inv.contains(Material.POTATO)) {
										plant = Material.POTATOES;
										seed = Material.POTATO;
									}
									if (plant != null) {
										Location planter = hoe.getLocation();
										planter.setY(planter.getBlockY() + 1);
										Block air = planter.getBlock();
										if (air.getType().equals(Material.AIR)) {
											air.setType(plant);
											ItemStack seeds = inv.getItem(inv.first(seed));
											seeds.setAmount(seeds.getAmount() - 1);
											log(player,hoe,"add");
										}
									}
								} else if (hoe.getType().equals(Material.SOUL_SAND)) {
									PlayerInventory inv = player.getInventory();
									Material plant = null;
									Material seed = null;
									if (inv.contains(Material.NETHER_WART)) {
										plant = Material.NETHER_WART;
										seed = Material.NETHER_WART;
									}
									if (plant != null) {
										Location planter = hoe.getLocation();
										planter.setY(planter.getBlockY() + 1);
										Block air = planter.getBlock();
										if (air.getType().equals(Material.AIR)) {
											air.setType(plant);
											ItemStack seeds = inv.getItem(inv.first(seed));
											seeds.setAmount(seeds.getAmount() - 1);
											log(player,hoe,"add");
										}
									}
								}
							}
						}
					}
				}
			}
			if (lore.contains(ChatColor.GRAY + "Fertilizer")) {
				if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
					Block block = event.getClickedBlock();
					Location origin = block.getLocation();
					Location loc = block.getLocation();
					for (int i = -1; i <= 1; i++) {
						for (int j = -1; j <= 1; j++) {
							loc.setX(origin.getX() + i);
							loc.setZ(origin.getZ() + j);
							Block hoe = loc.getBlock();
							if (canBreak(player, loc)) {
								if (hoe.getBlockData() instanceof Ageable && ((Ageable) hoe.getBlockData())
										.getAge() != ((Ageable) hoe.getBlockData()).getMaximumAge()) {
									PlayerInventory inv = player.getInventory();
									int bone = inv.first(Material.BONE_MEAL);
									if (bone != -1) {
										ItemStack meal = inv.getItem(bone);
										meal.setAmount(meal.getAmount() - 1);
										Ageable age = ((Ageable) hoe.getBlockData());
										age.setAge(((Ageable) hoe.getBlockData()).getMaximumAge());
										hoe.setBlockData(age);
										log(player,hoe,"interact");
									}
								}
							}
						}
					}
				}
			}
			if (worked) {
				event.setCancelled(true);
			}
		}

	}

	private boolean canBreak(Player player, Location loc) {
		ApplicableRegionSet regions = WorldGuard.getInstance().getPlatform().getRegionContainer()
				.get(BukkitAdapter.adapt(player.getWorld())).getApplicableRegions(BukkitAdapter.asBlockVector(loc));
		if (regions == null || regions.isOwnerOfAll(WorldGuardPlugin.inst().wrapPlayer(player))) {
			if (lands.isClaimed(loc)) {
				Land base = lands.getLand(loc);
				if (base.getTrustedPlayers().contains(player.getUniqueId()) || player.hasPermission("lands.bypass.*")) {
					return true;
				}
			} else {
				return true;
			}
		}
		return false;
	}

	private void log(Player p, Block b, String option) {
		CoreProtectAPI api = this.plugin.getCoreProtect();
		if (api != null) { // Ensure we have access to the API
			if (option.equals("interact")) {
				api.logInteraction(p.getName(), b.getLocation());
			} else if (option.equals("remove")) {
				api.logRemoval(p.getName(), b.getLocation(), b.getType(), b.getBlockData());
			} else if (option.equals("add")) {
				api.logPlacement(p.getName(), b.getLocation(), b.getType(), b.getBlockData());
			}
		}
	}
}