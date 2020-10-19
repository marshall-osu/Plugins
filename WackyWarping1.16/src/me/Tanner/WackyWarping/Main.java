package me.Tanner.WackyWarping;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import me.Tanner.WackyWarping.Commands.WackyWarps;
import me.Tanner.WackyWarping.Events.OnChat;
import me.Tanner.WackyWarping.Events.OnClick;
import me.Tanner.WackyWarping.Files.DataManager;
import me.Tanner.WackyWarping.TabCompletion.TabCompletion;
import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin {
	
	public Map<UUID,Set<Warp>> ownerLookup = new HashMap<>();
	public Map<String,Warp> warps = new HashMap<>();
	public List<Inventory> gui = new ArrayList<>();
	
	public Map<String,Warp> confirmations = new HashMap<>();
	public Map<String, Warp> confirmationsOwner = new HashMap<>();
	public Map<String, Player> confirmationsOwnerName = new HashMap<>();
	public Map<String, Warp> confirmationsDelete = new HashMap<>();
	
	public Map<String,Integer> permissions = new HashMap<>();
	
	public WackyWarps command;
	
	public int cost;
	public int defaultNum;
	
	public DataManager data;
	
	public Set<String> test;
	
	@Override
	public void onEnable() {
		
		this.data = new DataManager(this);
		
		FileConfiguration config = this.data.getConfig();
		ConfigurationSection setup = config.getConfigurationSection("warps");
		
		this.gui.add(createInventory());
		
		if(setup != null) {
			for(String name : setup.getKeys(false)) {
				ConfigurationSection info = setup.getConfigurationSection(name);
				Location loc = info.getLocation("location");
				UUID id = UUID.fromString(info.getString("ownerid"));
				String des = info.getString("description");
				ItemStack icon = info.getItemStack("icon");
				List<String> bans = info.getStringList("bans");

				String owner = this.getServer().getOfflinePlayer(id).getName();
				Warp warp = new Warp(name, loc, id, icon, des, owner, bans);
				
				this.warps.put(name, warp);
				
				if(this.ownerLookup.containsKey(id)) {
					this.ownerLookup.get(id).add(warp);
				} else {
					Set<Warp> owned = new HashSet<>();
					owned.add(warp);
					this.ownerLookup.put(id, owned);
				}
				
				addToInventory(warp.getIcon());
			}
		}
		
		ConfigurationSection settings = config.getConfigurationSection("settings");
		this.cost = settings.getInt("cost");
		this.defaultNum = settings.getInt("defaultnum");
	
		ConfigurationSection permission = config.getConfigurationSection("permissions");
		if(permission != null) {
			for(String perm : permission.getKeys(false)) {
				permissions.put(perm, permission.getInt(perm));
			}
		}
		
		new OnClick(this);
		new OnChat(this);
		
		command = new WackyWarps(this);
		getCommand("wwarp").setExecutor(command);
		getCommand("wwarp").setTabCompleter(new TabCompletion(this));
	}

	@Override
	public void onDisable() {
		
		this.data.reloadConfig();
		FileConfiguration config = this.data.getConfig();
		
		config.set("warps", null);
		
		for(String name : this.warps.keySet()) {
			Warp warp = this.warps.get(name);
			config.set("warps." + name + ".location", warp.getLocation());
			config.set("warps." + name + ".ownerid", warp.getOwnerId().toString());
			config.set("warps." + name + ".description", warp.getDescription());
			config.set("warps." + name + ".icon", warp.getIcon());
			config.set("warps." + name + ".bans", warp.getBans());
		}
		
		for(String perm : permissions.keySet()) {
			config.set("permissions." + perm, permissions.get(perm));
		}
		
		this.data.saveConfig();
	}
	
	public void addToInventory(ItemStack icon) {
		if(this.gui.size() == 0 || this.gui.get(this.gui.size()-1).firstEmpty() == -1) {
			this.gui.add(createInventory());
		}
		this.gui.get(this.gui.size()-1).addItem(icon);
	}
	
	public void deleteIcon(ItemStack icon) {
		for(Inventory inv : this.gui) {
			if(inv.contains(icon)) {
				if(inv.getItem(inv.first(icon)).hasItemMeta() && inv.getItem(inv.first(icon)).getItemMeta().equals(icon.getItemMeta())) {
					inv.remove(icon);
					return;
				}
			}
		}
	}
	
	public Inventory createInventory() {
		Inventory newInv = Bukkit.createInventory(null, 54, ChatColor.LIGHT_PURPLE + "WackyWarps");
		for(int i = 0; i < 9; i++) {
			if (i == 0) {
				ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
				ItemMeta meta = filler.getItemMeta();
				meta.setDisplayName("" + this.gui.size() +1);
				filler.setItemMeta(meta);
				newInv.setItem(45+i, filler);
			} else if(i == 2) {
				ItemStack filler = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
				ItemMeta meta = filler.getItemMeta();
				meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Previous Page");
				filler.setItemMeta(meta);
				newInv.setItem(45+i, filler);
			} else if (i == 6) {
				ItemStack filler = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE);
				ItemMeta meta = filler.getItemMeta();
				meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Next Page");
				filler.setItemMeta(meta);
				newInv.setItem(45+i, filler);
			} else {
				ItemStack filler = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
				ItemMeta meta = filler.getItemMeta();
				meta.setDisplayName("");
				filler.setItemMeta(meta);
				newInv.setItem(45+i, filler);
			}
		}
		return newInv;
	}
	
	public void reloadInv() {
		List<Inventory> replace = new ArrayList<>();
		replace.addAll(this.gui);
		gui.clear();
		for(Inventory g : replace) {
			for(ItemStack i : g.getContents()) {
				if(i != null) {
					addToInventory(i);
				}
			}
		}
	}
}
