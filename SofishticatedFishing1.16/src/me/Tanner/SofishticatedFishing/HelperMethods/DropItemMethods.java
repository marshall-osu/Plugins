package me.Tanner.SofishticatedFishing.HelperMethods;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Tanner.SofishticatedFishing.Main;

public class DropItemMethods {
	Main plugin;

	public DropItemMethods(Main plugin) {
		this.plugin = plugin;
	}

	public ItemStack basicFishingRod() {
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Basic Fishing Rod");
		meta.addEnchant(Enchantment.LURE, 1, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Your First Step On Your Grand Adventure");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack secondFishingRod() {
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Better Than The Basics");
		meta.addEnchant(Enchantment.LURE, 2, true);
		meta.addEnchant(Enchantment.LUCK, 1, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Your Second Step Is Always Hard");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack thirdFishingRod() {
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Squid Rod");
		meta.addEnchant(Enchantment.LURE, 2, true);
		meta.addEnchant(Enchantment.LUCK, 2, true);
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Hold On Tight");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack fourthFishingRod() {
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Deep Water Rod");
		meta.addEnchant(Enchantment.LURE, 3, true);
		meta.addEnchant(Enchantment.LUCK, 2, true);
		meta.addEnchant(Enchantment.DURABILITY, 2, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "How Far Down Could This Go?");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack fifthFishingRod() {
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Alien " + ChatColor.YELLOW + "Rod");
		meta.addEnchant(Enchantment.LURE, 4, true);
		meta.addEnchant(Enchantment.LUCK, 3, true);
		meta.addEnchant(Enchantment.DURABILITY, 4, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.DARK_GREEN + "" + ChatColor.ITALIC + "This rod feel's unearthly sturdy");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack sixthFishingRod() {
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Fish " + ChatColor.DARK_RED + "Rod");
		meta.addEnchant(Enchantment.LURE, 4, true);
		meta.addEnchant(Enchantment.LUCK, 4, true);
		meta.addEnchant(Enchantment.DURABILITY, 5, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "This rod is unbearably good");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack ancientHelmet() {
		ItemStack item = new ItemStack(Material.GOLDEN_HELMET);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Ancient Helmet");
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		meta.addEnchant(Enchantment.DURABILITY, 100, true);
		meta.addEnchant(Enchantment.OXYGEN, 1, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Crafted From the Shards Of A");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "UFO Pilot");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack ancientLeggings() {
		ItemStack item = new ItemStack(Material.GOLDEN_LEGGINGS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Ancient Leggings");
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		meta.addEnchant(Enchantment.DURABILITY, 100, true);
		meta.addEnchant(Enchantment.PROTECTION_FIRE, 3, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Crafted From the Shards Of A");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "UFO Pilot");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack ancientChestplate() {
		ItemStack item = new ItemStack(Material.GOLDEN_CHESTPLATE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Ancient Chestplate");
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		meta.addEnchant(Enchantment.DURABILITY, 100, true);
		meta.addEnchant(Enchantment.PROTECTION_FIRE, 3, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Crafted From the Shards Of A");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "UFO Pilot");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack ancientBoots() {
		ItemStack item = new ItemStack(Material.GOLDEN_BOOTS);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Ancient Boots");
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);
		meta.addEnchant(Enchantment.DURABILITY, 100, true);
		meta.addEnchant(Enchantment.PROTECTION_FALL, 3, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Crafted From the Shards Of A");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "UFO Pilot");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack bait() {
		ItemStack item = new ItemStack(Material.SALMON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Bait");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.WHITE + "" + ChatColor.ITALIC + "Bait for a rather large animal");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack bossEggItem() {
		ItemStack item = new ItemStack(Material.STICK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "First Boss");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Boss??");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack secondBossItem() {
		ItemStack item = new ItemStack(Material.EMERALD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "UFO Beacon");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "A Beacon To Call Down A UFO");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack deepWaterCod() {
		ItemStack item = new ItemStack(Material.COD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_AQUA + "Deep Water Cod");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "A Fish From The Deep");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack ice() {
		ItemStack item = new ItemStack(Material.ICE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_AQUA + "Ancient Ice");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Ice From an Ancient Civilization");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack scale() {
		ItemStack item = new ItemStack(Material.PRISMARINE_SHARD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Hardened Scale");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "The scale from an unidentified being");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack bearMeat() {
		ItemStack item = new ItemStack(Material.ROTTEN_FLESH);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW + "Imitation Bear Meat");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.GOLD + "" + ChatColor.ITALIC + "Why would someone make this?");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack bearPelt() {
		ItemStack item = new ItemStack(Material.LEATHER);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_GRAY + "Bear Pelt");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "The pelt of a large bear");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack salmonEssence() {
		ItemStack item = new ItemStack(Material.COOKED_SALMON);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Salmon Essence");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "A strange trinket held by a fierce hunter");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack salmonBar() {
		ItemStack item = new ItemStack(Material.BRICK);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Salmon " + ChatColor.DARK_RED + "Bar");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "One of the strongest materials out there");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack salmonPickaxe() {
		ItemStack item = new ItemStack(Material.DIAMOND_PICKAXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Salmon " + ChatColor.DARK_RED + "Pickaxe");
		meta.addEnchant(Enchantment.DURABILITY, 5, true);
		meta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 4, true);
		meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Pickaxe of the fish gods");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack salmonShovel() {
		ItemStack item = new ItemStack(Material.DIAMOND_SHOVEL);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Salmon " + ChatColor.DARK_RED + "Shovel");
		meta.addEnchant(Enchantment.DURABILITY, 5, true);
		meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack salmonAxe() {
		ItemStack item = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.RED + "Salmon " + ChatColor.DARK_RED + "Axe");
		meta.addEnchant(Enchantment.DURABILITY, 5, true);
		meta.addEnchant(Enchantment.DIG_SPEED, 5, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Axe of the fish gods");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack imitationBear() {
		ItemStack item = new ItemStack(Material.LEATHER_HORSE_ARMOR);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_RED + "Imitation Bear");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "This would trick anyone");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack reinforcedRod() {
		ItemStack item = new ItemStack(Material.END_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_PURPLE + "Reinforced Rod");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.LIGHT_PURPLE + "" + ChatColor.ITALIC + "This feels stronger than earth materials");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack SeventhSonsSoul() {
		ItemStack item = new ItemStack(Material.BLAZE_POWDER);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.DARK_RED + "Seventh Sons Soul");
		
		meta.addEnchant(Enchantment.DURABILITY, 7, true);
		
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "Taken from the Seventh Son,");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "Useful for the dark arts.");
		meta.setLore(lore);

		item.setItemMeta(meta);
		
		return item;
	}
	
	public ItemStack SeventhSonsSword() {
		ItemStack item = new ItemStack(Material.NETHERITE_SWORD);
		ItemMeta meta = item.getItemMeta();
	
		meta.setDisplayName(ChatColor.DARK_RED + "Seventh Sons Sword");
		
		meta.addEnchant(Enchantment.DURABILITY, 10, true);
		meta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);
		meta.addEnchant(Enchantment.SWEEPING_EDGE, 6, true);
		meta.addEnchant(Enchantment.FIRE_ASPECT, 6, true);
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "Given to the Seventh Son,");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "in hopes he lives longer than the others");
		meta.setLore(lore);

		item.setItemMeta(meta);
		
		return item;
	}
	
	public ItemStack SeventhSonsHelm() {
		ItemStack item = new ItemStack(Material.NETHERITE_HELMET);
		ItemMeta meta = item.getItemMeta();
	
		meta.setDisplayName(ChatColor.DARK_RED + "Seventh Sons Helmet");
		
		meta.addEnchant(Enchantment.DURABILITY, 10, true);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		meta.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "Given to the Seventh Son,");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "in hopes he lives longer than the others");
		meta.setLore(lore);

		item.setItemMeta(meta);
		
		return item;
	}
	
	public ItemStack SeventhSonsCloak() {
		ItemStack item = new ItemStack(Material.NETHERITE_CHESTPLATE);
		ItemMeta meta = item.getItemMeta();
	
		meta.setDisplayName(ChatColor.DARK_RED + "Seventh Sons Cloak");
		
		meta.addEnchant(Enchantment.DURABILITY, 10, true);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		meta.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "Given to the Seventh Son,");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "in hopes he lives longer than the others");
		meta.setLore(lore);

		item.setItemMeta(meta);
		
		return item;
	}
	
	public ItemStack SeventhSonsLeggings() {
		ItemStack item = new ItemStack(Material.NETHERITE_LEGGINGS);
		ItemMeta meta = item.getItemMeta();
	
		meta.setDisplayName(ChatColor.DARK_RED + "Seventh Sons Leggings");
		
		meta.addEnchant(Enchantment.DURABILITY, 10, true);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		meta.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "Given to the Seventh Son,");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "in hopes he lives longer than the others");
		meta.setLore(lore);

		item.setItemMeta(meta);
		
		return item;
	}
	
	public ItemStack SeventhSonsBoots() {
		ItemStack item = new ItemStack(Material.NETHERITE_BOOTS);
		ItemMeta meta = item.getItemMeta();
	
		meta.setDisplayName(ChatColor.DARK_RED + "Seventh Sons Boots");
		
		meta.addEnchant(Enchantment.DURABILITY, 10, true);
		meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
		meta.addEnchant(Enchantment.PROTECTION_FIRE, 10, true);
		
		List<String> lore = new ArrayList<String>();
		lore.add("");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "Given to the Seventh Son,");
		lore.add(ChatColor.RED + "" + ChatColor.ITALIC + "in hopes he lives longer than the others");
		meta.setLore(lore);

		item.setItemMeta(meta);
		
		return item;
	}

	public ItemStack tentacle() {
		ItemStack item = new ItemStack(Material.INK_SAC);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_AQUA + "Tentacle");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "The Limb of a Stupid Creature");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack UFOShard() {
		ItemStack item = new ItemStack(Material.GOLD_NUGGET);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "UFO Shard");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Dropped From The Ancient UFO");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack waterLoggedPlank() {
		ItemStack item = new ItemStack(Material.WOODEN_SWORD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Water Logged Plank");
		meta.addEnchant(Enchantment.DAMAGE_ALL, 10, true);
		meta.addEnchant(Enchantment.DURABILITY, 10, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Crafted From the Pearls Of an");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Under Water Rider");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack waterPearl() {
		ItemStack item = new ItemStack(Material.HEART_OF_THE_SEA);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.BLUE + "Water Pearl");
		meta.addEnchant(Enchantment.DURABILITY, 1000, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Dropped From The Fearsome EnderRider");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack salmonRod() {
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_RED + "Salmon Rod");
		meta.addEnchant(Enchantment.DURABILITY, 5, true);
		meta.addEnchant(Enchantment.LURE, 4, true);
		meta.addEnchant(Enchantment.LUCK, 5, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Wow, this is a slimy rod.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack axeHead() {
		ItemStack item = new ItemStack(Material.IRON_INGOT);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_RED + "Axe Head");
		meta.addEnchant(Enchantment.DURABILITY, 5, true);
		meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Taken From a Very Lost Lumberjack");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public ItemStack lumberBow() {
		ItemStack item = new ItemStack(Material.BOW);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.DARK_RED + "Lumber Bow");
		meta.addEnchant(Enchantment.DURABILITY, 5, true);
		meta.addEnchant(Enchantment.MENDING, 1, true);
		meta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
		meta.addEnchant(Enchantment.ARROW_KNOCKBACK, 5, true);
		meta.addEnchant(Enchantment.ARROW_FIRE, 1, true);
		meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
		meta.addEnchant(Enchantment.DURABILITY, 5, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Who Knew Iron is Stronger Than Sticks");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public ItemStack PrecursorRod() {
		ItemStack item = new ItemStack(Material.FISHING_ROD);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.YELLOW + "Precusor" + ChatColor.GRAY + "Rod");
		meta.addEnchant(Enchantment.DURABILITY, 10, true);
		meta.addEnchant(Enchantment.LURE, 5, true);
		meta.addEnchant(Enchantment.LUCK, 5, true);
		List<String> lore = new ArrayList<>();
		lore.add("");
		lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "You can feel this rod pulsate with power.");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}

	public void giveItem(ItemStack item, Player player) {
		if (player.getInventory().firstEmpty() == -1) {
			Location loc = player.getLocation();
			World world = player.getWorld();
			world.dropItemNaturally(loc, item);
			return;
		}
		player.getInventory().addItem(new ItemStack[] { item });
	}
}
