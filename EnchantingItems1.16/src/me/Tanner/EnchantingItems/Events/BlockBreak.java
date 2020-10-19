package me.Tanner.EnchantingItems.Events;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.Recipe;
import org.bukkit.util.Vector;

import com.gmail.nossr50.datatypes.player.McMMOPlayer;
import com.gmail.nossr50.datatypes.skills.PrimarySkillType;
import com.gmail.nossr50.datatypes.skills.SubSkillType;
import com.gmail.nossr50.util.BlockUtils;
import com.gmail.nossr50.util.player.UserManager;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;

import org.bukkit.block.Container;
import org.bukkit.block.data.Ageable;

import me.Tanner.EnchantingItems.Enchantments;
import me.angeschossen.lands.api.integration.LandsIntegration;
import me.angeschossen.lands.api.land.Land;
import net.coreprotect.CoreProtectAPI;
import net.md_5.bungee.api.ChatColor;

public class BlockBreak implements Listener {

	Enchantments plugin;
	LandsIntegration lands;
	CoreProtectAPI api;
	private Map<Material, Material> logs = new HashMap<>();
	private Set<Material> blacklist = new HashSet<>();

	public BlockBreak(Enchantments plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
		lands = new LandsIntegration(this.plugin);
		api = this.plugin.getCoreProtect();

		logs.put(Material.ACACIA_LOG, Material.ACACIA_PLANKS);
		logs.put(Material.SPRUCE_LOG, Material.SPRUCE_PLANKS);
		logs.put(Material.OAK_LOG, Material.OAK_PLANKS);
		logs.put(Material.JUNGLE_LOG, Material.JUNGLE_PLANKS);
		logs.put(Material.BIRCH_LOG, Material.BIRCH_PLANKS);
		logs.put(Material.DARK_OAK_LOG, Material.DARK_OAK_PLANKS);
		logs.put(Material.STRIPPED_ACACIA_LOG, Material.ACACIA_PLANKS);
		logs.put(Material.STRIPPED_SPRUCE_LOG, Material.SPRUCE_PLANKS);
		logs.put(Material.STRIPPED_OAK_LOG, Material.OAK_PLANKS);
		logs.put(Material.STRIPPED_JUNGLE_LOG, Material.JUNGLE_PLANKS);
		logs.put(Material.STRIPPED_BIRCH_LOG, Material.BIRCH_PLANKS);
		logs.put(Material.STRIPPED_DARK_OAK_LOG, Material.DARK_OAK_PLANKS);
		logs.put(Material.ACACIA_WOOD, Material.ACACIA_PLANKS);
		logs.put(Material.SPRUCE_WOOD, Material.SPRUCE_PLANKS);
		logs.put(Material.OAK_WOOD, Material.OAK_PLANKS);
		logs.put(Material.JUNGLE_WOOD, Material.JUNGLE_PLANKS);
		logs.put(Material.BIRCH_WOOD, Material.BIRCH_PLANKS);
		logs.put(Material.DARK_OAK_WOOD, Material.DARK_OAK_PLANKS);
		logs.put(Material.STRIPPED_ACACIA_WOOD, Material.ACACIA_PLANKS);
		logs.put(Material.STRIPPED_SPRUCE_WOOD, Material.SPRUCE_PLANKS);
		logs.put(Material.STRIPPED_OAK_WOOD, Material.OAK_PLANKS);
		logs.put(Material.STRIPPED_JUNGLE_WOOD, Material.JUNGLE_PLANKS);
		logs.put(Material.STRIPPED_BIRCH_WOOD, Material.BIRCH_PLANKS);
		logs.put(Material.STRIPPED_DARK_OAK_WOOD, Material.DARK_OAK_PLANKS);
		logs.put(Material.CRIMSON_HYPHAE, Material.CRIMSON_PLANKS);
		logs.put(Material.STRIPPED_CRIMSON_HYPHAE, Material.CRIMSON_PLANKS);
		
		blacklist.add(Material.IRON_AXE);
		blacklist.add(Material.IRON_PICKAXE);
		blacklist.add(Material.IRON_HOE);
		blacklist.add(Material.IRON_SHOVEL);
		blacklist.add(Material.IRON_SWORD);
		blacklist.add(Material.IRON_CHESTPLATE);
		blacklist.add(Material.IRON_HELMET);
		blacklist.add(Material.IRON_LEGGINGS);
		blacklist.add(Material.IRON_BOOTS);
		blacklist.add(Material.IRON_HORSE_ARMOR);
		blacklist.add(Material.CHAINMAIL_CHESTPLATE);
		blacklist.add(Material.CHAINMAIL_HELMET);
		blacklist.add(Material.CHAINMAIL_LEGGINGS);
		blacklist.add(Material.CHAINMAIL_BOOTS);
		blacklist.add(Material.GOLDEN_AXE);
		blacklist.add(Material.GOLDEN_PICKAXE);
		blacklist.add(Material.GOLDEN_HOE);
		blacklist.add(Material.GOLDEN_SHOVEL);
		blacklist.add(Material.GOLDEN_SWORD);
		blacklist.add(Material.GOLDEN_CHESTPLATE);
		blacklist.add(Material.GOLDEN_HELMET);
		blacklist.add(Material.GOLDEN_LEGGINGS);
		blacklist.add(Material.GOLDEN_BOOTS);
		blacklist.add(Material.GOLDEN_HORSE_ARMOR);

	}
	
	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		this.plugin.placedBlocks.add(event.getBlockPlaced().getLocation());
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		if(event.getBlock().getLocation().getBlockY() == 1) {
			return;
		}
		Player player = event.getPlayer();
		Boolean custom = false;
		Boolean dealt = false;
		PlayerInventory inventory = player.getInventory();
		ItemStack tool = inventory.getItemInMainHand();
		Collection<ItemStack> drops = event.getBlock().getDrops(tool);
		if (tool != null && tool.hasItemMeta() && tool.getItemMeta().hasLore()) {
			McMMOPlayer p = UserManager.getPlayer(player);
			p.getSkillLevel(PrimarySkillType.MINING);
			List<String> lore = tool.getItemMeta().getLore();
			if (lore.contains(ChatColor.GRAY + "Hammer")) {
				event.setDropItems(false);
				dealt = true;
				custom = true;
				drops.clear();
				Location origin = event.getBlock().getLocation();
				float pitch = event.getPlayer().getLocation().getPitch();
				boolean vertical = false;
				if (pitch < -45 || pitch > 45) {
					vertical = true;
				}
				Vector direction = event.getPlayer().getFacing().getDirection();
				int xTest = 1 - Math.abs(direction.getBlockX());
				int zTest = 1 - Math.abs(direction.getBlockZ());
				Location loc = origin.clone();
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (xTest != 0 || vertical) {
							loc.setX(origin.getX() + i);
						}
						if (!vertical) {
							if (xTest != 0) {
								loc.setY(origin.getY() + j);
							} else {
								loc.setY(origin.getY() + i);
							}
						}
						if (zTest != 0 || vertical) {
							loc.setZ(origin.getZ() + j);
						}
						Block block = loc.getBlock();
						if (canBreak(player, loc)) {
							if (!block.getDrops(tool).isEmpty()) {
								Collection<ItemStack> temp = block.getDrops(tool);
								if(!this.plugin.placedBlocks.contains(loc)) {
									if (BlockUtils.checkDoubleDrops(player, block.getState(), PrimarySkillType.MINING,
											SubSkillType.MINING_DOUBLE_DROPS)) {
										for (ItemStack item : temp) {
											if(item.getAmount()*2 <= 64) {
												item.setAmount(item.getAmount() * 2);
											} else {
												item.setAmount(64);
											}
										}
									}
								}
								drops.addAll(temp);
								log(player, block, "remove");
								block.setType(Material.AIR);
							}
						}
					}
				}
			}
			if (lore.contains(ChatColor.GRAY + "Scyth")) {
				drops.clear();
				dealt = true;
				event.setDropItems(false);
				custom = true;
				Block block = event.getBlock();
				Location origin = block.getLocation();
				Location loc = block.getLocation();
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						loc.setX(origin.getX() + i);
						loc.setZ(origin.getZ() + j);
						Block hoe = loc.getBlock();
						if (canBreak(player, loc)) {
							if (hoe.getBlockData() instanceof Ageable && ((Ageable) hoe.getBlockData())
									.getAge() == ((Ageable) hoe.getBlockData()).getMaximumAge()) {
								Collection<ItemStack> temp = hoe.getDrops(tool);
								if (BlockUtils.checkDoubleDrops(player, block.getState(), PrimarySkillType.HERBALISM,
										SubSkillType.HERBALISM_DOUBLE_DROPS)) {
									for (ItemStack item : temp) {
										item.setAmount(item.getAmount() * 2);
									}
								}
								drops.addAll(temp);
								log(player, hoe, "remove");
								hoe.setType(Material.AIR);
							}
						}
					}
				}
			}
			if (lore.contains(ChatColor.GRAY + "Tree Feller")) {
				event.setDropItems(false);
				custom = true;
				dealt = true;
				Block block = event.getBlock();
				ArrayList<Block> blocks = new ArrayList<Block>();
				if (canBreak(player, block.getLocation())) {
					if (block.getType() == Material.ACACIA_LOG || block.getType() == Material.OAK_LOG
							|| block.getType() == Material.DARK_OAK_LOG || block.getType() == Material.BIRCH_LOG
							|| block.getType() == Material.JUNGLE_LOG || block.getType() == Material.SPRUCE_LOG) {
						for (int y = -1; y <= 1; y++) {
							for (int x = -1; x <= 1; x++) {
								for (int z = -1; z <= 1; z++) {
									if (x != 0 || y != 0 || z != 0) {
										if (block.getRelative(x, y, z).getType() == block.getType()) {
											blocks.add(block.getRelative(x, y, z));
										}
									}
								}
							}
						}
						if (BlockUtils.checkDoubleDrops(player, block.getState(), PrimarySkillType.WOODCUTTING,
								SubSkillType.WOODCUTTING_HARVEST_LUMBER)) {
							for (ItemStack item : drops) {
								item.setAmount(item.getAmount() * 2);
							}
						}

						log(player, block, "remove");

						block.setType(Material.AIR);
						for (Block b : blocks) {
							treeFellerRecurse(b, player, tool);
						}
					}
				}
			}
			if (lore.contains(ChatColor.GRAY + "Auto Planker")) {
				event.setDropItems(false);
				custom = true;
				Block block = event.getBlock();
				if (canBreak(player, block.getLocation())) {
					if (!dealt && BlockUtils.checkDoubleDrops(player, block.getState(), PrimarySkillType.WOODCUTTING,
							SubSkillType.WOODCUTTING_HARVEST_LUMBER)) {
						for (ItemStack item : drops) {
							item.setAmount(item.getAmount() * 2);
						}
					}
					drops = planker(drops);
				}
			}
			if (lore.contains(ChatColor.GRAY + "Auto Smelter")) {
				event.setDropItems(false);
				custom = true;
				Block block = event.getBlock();
				if (canBreak(player, event.getBlock().getLocation())) {
					if (!dealt) {
						if (BlockUtils.checkDoubleDrops(player, block.getState(), PrimarySkillType.WOODCUTTING,
								SubSkillType.WOODCUTTING_HARVEST_LUMBER)
								|| BlockUtils.checkDoubleDrops(player, block.getState(), PrimarySkillType.MINING,
										SubSkillType.MINING_DOUBLE_DROPS)) {
							for (ItemStack item : drops) {
								item.setAmount(item.getAmount() * 2);
							}
						}
					}
					drops = smelter(drops, event.getBlock(), tool);
				}
			}
			if (lore.contains(ChatColor.GRAY + "Telepathy")) {
				event.setDropItems(false);
				Block block = event.getBlock();
				if (canBreak(player, event.getBlock().getLocation())) {
					if (!dealt) {
						if (BlockUtils.checkDoubleDrops(player, block.getState(), PrimarySkillType.WOODCUTTING,
								SubSkillType.WOODCUTTING_HARVEST_LUMBER)
								|| BlockUtils.checkDoubleDrops(player, block.getState(), PrimarySkillType.MINING,
										SubSkillType.MINING_DOUBLE_DROPS)
								|| BlockUtils.checkDoubleDrops(player, block.getState(), PrimarySkillType.HERBALISM,
										SubSkillType.HERBALISM_DOUBLE_DROPS)) {
							for (ItemStack item : drops) {
								item.setAmount(item.getAmount() * 2);
							}
						}
					}
					for (ItemStack drop : drops) {
						if (inventory.firstEmpty() != -1) {
							player.getInventory().addItem(drop);
						} else {
							player.getWorld().dropItemNaturally(player.getLocation(), drop);
						}
					}
				}
			} else if (custom) {
				if (canBreak(player, event.getBlock().getLocation())) {
					for (ItemStack drop : drops) {
						player.getWorld().dropItemNaturally(event.getBlock().getLocation(), drop);
					}
				}
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

	private void treeFellerRecurse(Block block, Player player, ItemStack tool) {
		if(canBreak(player, block.getLocation())) {
			ArrayList<Block> blocks = new ArrayList<Block>();
			if (block.getType() == Material.ACACIA_LOG || block.getType() == Material.OAK_LOG
					|| block.getType() == Material.DARK_OAK_LOG || block.getType() == Material.BIRCH_LOG
					|| block.getType() == Material.JUNGLE_LOG || block.getType() == Material.SPRUCE_LOG) {
				for (int y = -1; y <= 1; y++) {
					for (int x = -1; x <= 1; x++) {
						for (int z = -1; z <= 1; z++) {
							if (x != 0 || y != 0 || z != 0) {
								if (block.getRelative(x, y, z).getType() == block.getType()) {
									blocks.add(block.getRelative(x, y, z));
								}
							}
						}
					}
				}
				ItemStack drop = block.getDrops(tool).iterator().next();
				if (BlockUtils.checkDoubleDrops(player, block.getState(), PrimarySkillType.WOODCUTTING,
						SubSkillType.WOODCUTTING_HARVEST_LUMBER)) {
					drop.setAmount(drop.getAmount() * 2);
				}
				if (tool.getItemMeta().hasEnchant(this.plugin.enchants.AUTOPLANKER)) {
					Collection<ItemStack> temp = new ArrayList<>();
					temp.add(drop);
					drop = planker(temp).iterator().next();
				}
	
				log(player, block, "remove");
	
				block.setType(Material.AIR);
				if (tool.getItemMeta().hasEnchant(this.plugin.enchants.TELEPATHY)
						&& player.getInventory().firstEmpty() != -1 && !(block.getState() instanceof Container)) {
					player.getInventory().addItem(drop);
				} else {
					World world = player.getWorld();
					world.dropItemNaturally(block.getLocation(), drop);
				}
	
				for (Block b : blocks) {
					treeFellerRecurse(b, player, tool);
				}
			}
		}
	}

	private Collection<ItemStack> smelter(Collection<ItemStack> drops, Block b, ItemStack tool) {

		ArrayList<ItemStack> smelted = new ArrayList<>();

		for (ItemStack smelt : drops) {
			if(!blacklist.contains(smelt.getType())) {
				if (logs.containsKey(smelt.getType())) {
					smelted.add(new ItemStack(Material.CHARCOAL));
				} else if (smelt.getType().equals(Material.SAND) || smelt.getType().equals(Material.RED_SAND)) {
					smelted.add(new ItemStack(Material.GLASS));
				} else {
					Iterator<Recipe> iter = Bukkit.recipeIterator();
					boolean found = false;
					while (!found && iter.hasNext()) {
						Recipe recipe = iter.next();
						if (recipe instanceof FurnaceRecipe
								&& ((FurnaceRecipe) recipe).getInput().getType().equals(smelt.getType())) {
							int amount;
							ItemStack temp;
							if (smelt.getType() == Material.ANCIENT_DEBRIS || smelt.getType() == Material.IRON_ORE
									|| smelt.getType() == Material.GOLD_ORE) {
								Material type = b.getType();
								b.setType(Material.DIAMOND_ORE);
								amount = b.getDrops(tool).iterator().next().getAmount();
								b.setType(type);
							} else {
								amount = 1;
							}
							temp = new ItemStack(recipe.getResult());
							temp.setAmount(amount);
							smelted.add(temp);
							found = true;
						}
					}
					if (!found) {
						smelted.add(smelt);
					}
				}
			}
		}
		return smelted;
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

	private Collection<ItemStack> planker(Collection<ItemStack> drops) {
		ArrayList<ItemStack> planked = new ArrayList<>();
		Material drop = null;
		for (ItemStack block : drops) {
			if (logs.containsKey(block.getType())) {
				drop = logs.get(block.getType());
			}
			ItemStack item;
			if (drop == null) {
				planked.add(block);
			} else {
				item = new ItemStack(drop);
				item.setAmount(6);
				planked.add(item);
			}
		}
		return planked;
	}
}
