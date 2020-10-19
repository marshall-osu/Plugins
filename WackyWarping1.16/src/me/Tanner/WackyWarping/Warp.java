package me.Tanner.WackyWarping;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.bukkit.ChatColor;

public class Warp {
	private Location location;
	private ItemStack icon;
	private UUID ownerId;
	private String owner;
	private String description;
	private String name;
	private List<String> bans;
	
	public Warp(String warpName, Location loc, UUID ownerId, String owner) {
		
		this.ownerId = ownerId;
		this.owner = owner;
		this.name = warpName;
		this.location = loc;
		this.description = "Warp to this players shop!";
		this.icon = createIcon(new ItemStack(Material.LODESTONE));
		this.bans = new ArrayList<>();
	}
	
	public Warp(String warpName, Location loc, UUID ownerId, ItemStack icon, String owner) {
		
		this.ownerId = ownerId;
		this.owner = owner;
		this.name = warpName;
		this.location = loc;
		this.description = "Warp to this players shop!";
		this.icon = createIcon(icon);
		this.bans = new ArrayList<>();
	}

	public Warp(String warpName, Location loc, UUID ownerId, ItemStack icon, String description, String owner) {
	
		this.ownerId = ownerId;
		this.owner = owner;
		this.name = warpName;
		this.location = loc;
		this.description = description;
		this.icon = createIcon(icon);
		this.bans = new ArrayList<>();
	}
	
	public Warp(String warpName, Location loc, UUID ownerId, ItemStack icon, String description, String owner, List<String> bans) {
		
		this.ownerId = ownerId;
		this.owner = owner;
		this.name = warpName;
		this.location = loc;
		this.description = description;
		this.icon = createIcon(icon);
		this.bans = bans;
	}
	
	public void setIcon(ItemStack item) {
		this.icon = createIcon(new ItemStack(item.getType()));
	}
	
	public void setDescription(String dsc) {
		this.description = dsc;
		this.icon = createIcon(this.icon.clone());
	}
	
	public void setLocation(Location loc) {
		this.location = loc;
		this.icon = createIcon(this.icon.clone());
	}
	
	public void setName(String name) {
		this.name = name;
		this.icon = createIcon(this.icon.clone());
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
		this.icon = createIcon(this.icon.clone());
	}
	
	public void setOwnerId(UUID id) {
		this.ownerId = id;
		this.icon = createIcon(this.icon.clone());
	}
	
	public boolean ban(String playerId) {
		
		boolean banned = true;
		if(this.bans.contains(playerId)) {
			this.bans.remove(playerId);
			banned = false;
		} else {
			this.bans.add(playerId);
		}
		return banned;
	}
	
	public ItemStack getIcon() {
		return this.icon;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public String getName() {
		return this.name;
	}
	
	public UUID getOwnerId() {
		return this.ownerId;
	}
	
	public String getOwner() {
		return this.owner;
	}
	
	public List<String> getBans(){
		return this.bans;
	}
	
	private ItemStack createIcon(ItemStack material) {
		ItemMeta meta = material.getItemMeta();
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', this.name));
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.YELLOW+ "Owner: " + this.owner);
		lore.add(ChatColor.GRAY + "" + ChatColor.BOLD + "--------------------");
		lore.add(this.description);
		meta.setLore(lore);
		material.setItemMeta(meta);
		return material;
	}
}
