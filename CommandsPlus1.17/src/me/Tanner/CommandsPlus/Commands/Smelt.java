package me.Tanner.CommandsPlus.Commands;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import me.Tanner.CommandsPlus.Main;
import net.md_5.bungee.api.ChatColor;

public class Smelt implements CommandExecutor {
	
	Main plugin;
	private Set<Material> logs = new HashSet<>();
	private Set<Material> blacklist = new HashSet<>();
	
	public Smelt(Main plugin) {
		this.plugin = plugin;
		logs.add(Material.ACACIA_LOG);
		logs.add(Material.SPRUCE_LOG);
		logs.add(Material.OAK_LOG);
		logs.add(Material.JUNGLE_LOG);
		logs.add(Material.BIRCH_LOG);
		logs.add(Material.DARK_OAK_LOG);
		logs.add(Material.STRIPPED_ACACIA_LOG);
		logs.add(Material.STRIPPED_SPRUCE_LOG);
		logs.add(Material.STRIPPED_OAK_LOG);
		logs.add(Material.STRIPPED_JUNGLE_LOG);
		logs.add(Material.STRIPPED_BIRCH_LOG);
		logs.add(Material.STRIPPED_DARK_OAK_LOG);
		logs.add(Material.ACACIA_WOOD);
		logs.add(Material.SPRUCE_WOOD);
		logs.add(Material.OAK_WOOD);
		logs.add(Material.JUNGLE_WOOD);
		logs.add(Material.BIRCH_WOOD);
		logs.add(Material.DARK_OAK_WOOD);
		logs.add(Material.STRIPPED_ACACIA_WOOD);
		logs.add(Material.STRIPPED_SPRUCE_WOOD);
		logs.add(Material.STRIPPED_OAK_WOOD);
		logs.add(Material.STRIPPED_JUNGLE_WOOD);
		logs.add(Material.STRIPPED_BIRCH_WOOD);
		logs.add(Material.STRIPPED_DARK_OAK_WOOD);
		
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
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("smelt")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
					if(player.hasPermission("commands.smelt")) {
					ItemStack mainHand = player.getInventory().getItemInMainHand();
					player.getInventory().setItemInMainHand(smelter(mainHand));
					player.sendMessage(ChatColor.GREEN + "Item successfully smelted if possible!");
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command");
				}
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Only players can call this command!");
			return true;
		} else if (label.equals("smeltall")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(player.hasPermission("commands.smeltall")) {	
					Inventory inv = player.getInventory();
					for (int i = 0; i < 36; i++) {
						ItemStack item = inv.getItem(i);
						if(item != null) {
							player.getInventory().removeItem(item);
							player.getInventory().addItem(smelter(item));
						}
					}
					player.sendMessage(ChatColor.GREEN + "All inventory items successfully smelted if possible!");
				} else {
					player.sendMessage(ChatColor.RED + "You don't have permission to use this command");
				}
				return true;
			}
			sender.sendMessage(ChatColor.RED + "Only players can call this command!");
			return true;
		}
		return false;
	}
	
	private ItemStack smelter(ItemStack smelt) {
		int amount = smelt.getAmount();
		if(!blacklist.contains(smelt.getType())) {
			if(logs.contains(smelt.getType())) {
				smelt.setType(Material.CHARCOAL);
				smelt.setAmount(amount);
			} else if (smelt.getType().equals(Material.SAND)) {
				smelt.setType(Material.GLASS);
				smelt.setAmount(amount);
			} else {
				Iterator<Recipe> iter = Bukkit.recipeIterator();
				boolean found = false;
				while(!found && iter.hasNext()) {
					Recipe recipe = iter.next();
					if(recipe instanceof FurnaceRecipe && ((FurnaceRecipe) recipe).getInput().getType() == smelt.getType()) {
						smelt = recipe.getResult();
						found = true;
						smelt.setAmount(amount);
					}
				}	
			}
			
		}
		return smelt;
	}
}
