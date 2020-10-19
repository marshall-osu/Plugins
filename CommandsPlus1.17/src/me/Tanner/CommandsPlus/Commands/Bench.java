package me.Tanner.CommandsPlus.Commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tanner.CommandsPlus.Main;
import net.md_5.bungee.api.ChatColor;

public class Bench implements CommandExecutor, Listener{
	
	private Main plugin;
	private List<InventoryType> check;
	private Map<String, Inventory> invs;
	
	public Bench(Main plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
		
		this.check = new ArrayList<>();
		this.check.add(InventoryType.LOOM);
		this.check.add(InventoryType.GRINDSTONE);
		this.check.add(InventoryType.CARTOGRAPHY);
		this.check.add(InventoryType.ANVIL);
		this.check.add(InventoryType.CRAFTING);
		this.check.add(InventoryType.SMITHING);
		this.check.add(InventoryType.STONECUTTER);
		
		this.invs = new HashMap<>();
		this.invs.put("loom", Bukkit.createInventory(null, InventoryType.LOOM, "CommandsPlus"));
		this.invs.put("grindstone", Bukkit.createInventory(null, InventoryType.GRINDSTONE, "CommandsPlus"));
		this.invs.put("cartography", Bukkit.createInventory(null, InventoryType.CARTOGRAPHY, "CommandsPlus"));
		this.invs.put("anvil", Bukkit.createInventory(null, InventoryType.ANVIL, "CommandsPlus"));
		this.invs.put("smithing", Bukkit.createInventory(null, InventoryType.SMITHING, "CommandsPlus"));
		this.invs.put("stonecutter", Bukkit.createInventory(null, InventoryType.STONECUTTER, "CommandsPlus"));
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equals("bench")) {
			if(sender instanceof Player) {
				Player player = (Player) sender;
				if(args.length == 1) {
					if(args[0].equals("loom") && player.hasPermission("commands.loom")) {
						player.openInventory(this.invs.get(args[0]));
					} else if(args[0].equals("grindstone") && player.hasPermission("commands.grindstone")) {
						player.openInventory(Bukkit.createInventory(player, InventoryType.GRINDSTONE, "CommandsPlus"));
					} else if(args[0].equals("cartography") && player.hasPermission("commands.cartography")) {
						player.openInventory(this.invs.get(args[0]));
					} else if(args[0].equals("anvil") && player.hasPermission("commands.anvil")) {
						player.openInventory(this.invs.get(args[0]));
					} else if(args[0].equals("crafting") && player.hasPermission("commands.crafting")) {
						player.openWorkbench(null, true);
					} else if(args[0].equals("smithing") && player.hasPermission("commands.smithing")) {
						player.openInventory(this.invs.get(args[0]));
					} else if(args[0].equals("stonecutter") && player.hasPermission("commands.stonecutter")) {
						player.openInventory(this.invs.get(args[0]));
					} else {
						player.sendMessage(ChatColor.RED + "Error: you either don't have permission for that bench or it doesn't exist");
					}
					return true;
				}
				sender.sendMessage(ChatColor.RED + "Error: use the format /bench [work bench]");
			}
			sender.sendMessage(ChatColor.RED + "Error: only players can call this command");
			return true;
		}
		return false;
	}
	
	@EventHandler
	public void closeInventory(InventoryCloseEvent e) {
		Inventory inv = e.getInventory();
		//e.getView().getTitle().equals("CommandsPlus") && 
		if(this.check.contains(inv.getType()) && e.getPlayer() instanceof Player) {
			ItemStack[] items = inv.getContents();
			Player player = (Player) e.getPlayer();
			for(ItemStack item : items) {
				if(item != null) {
					player.sendMessage(item.toString());
					if(player.getInventory().firstEmpty() != -1) {
						player.getInventory().addItem(item);
					} else {
						player.getWorld().dropItemNaturally(player.getLocation(), item);
					}
				} else {
					player.sendMessage("NULL");
				}
			}
		}
	}

}
