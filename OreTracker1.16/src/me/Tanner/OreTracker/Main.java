package me.Tanner.OreTracker;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.Tanner.OreTracker.Data.DataManager;

import org.bukkit.ChatColor;

public class Main extends JavaPlugin implements Listener {
	
	public static Set<Player> muted;
	public static Set<Player> scmuted;
	public Map<String, List<UUID>> tracker;
	
	public DataManager data;
	
	public boolean stopChat;
	
	@Override
	public void onEnable() {
		
		this.data = new DataManager(this);
		
		FileConfiguration config = data.getConfig();
		
		tracker = new HashMap<>();
	
		ConfigurationSection uuids = config.getConfigurationSection("ips");
		if(uuids != null) {
			for( int i = 0 ; i < uuids.getKeys(false).size() ; i++) {
				List<String> playersUUIDs = uuids.getStringList(i + ".list");
				List<UUID> finished = new ArrayList<UUID>();
				for(String player : playersUUIDs) {
					finished.add(UUID.fromString(player));
				}
				String ip = uuids.getString(i + ".ip");
				tracker.put(ip, finished);
			}
		}
		
		new ChatEvent(this);
		
		muted = new HashSet<Player>();
		scmuted = new HashSet<Player>();
		stopChat = false;
		
		//this.getCommand("scmute").setExecutor(new ChatMute());
		this.getCommand("ipseen").setExecutor(new IPSeen(this));
		this.getCommand("ot").setExecutor(new Mute());
		//this.getCommand("sc").setExecutor(new Chat(this));
		this.getCommand("muteall").setExecutor(new MuteAll(this));
		this.getCommand("randomp").setExecutor(new RandomP(this));
		this.getCommand("clearchat").setExecutor(new ClearChat());
		this.getServer().getPluginManager().registerEvents(this, this);
	}

	@Override
	public void onDisable() {
		
		FileConfiguration config = this.data.getConfig();
		
		int i = 0;
		for(String ip : this.tracker.keySet()) {
			List<String> ids = new ArrayList<>();
			for(UUID string : this.tracker.get(ip)) {
				ids.add(string.toString());
			}
			config.set("ips." + i + ".list", ids);
			config.set("ips." + i + ".ip", ip);
			i++;
		}
		
		this.data.saveConfig();
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if(event.getBlock().getBlockData().getMaterial().equals(Material.DIAMOND_ORE) || event.getBlock().getBlockData().getMaterial().equals(Material.EMERALD_ORE) || event.getBlock().getBlockData().getMaterial().equals(Material.SPAWNER) || event.getBlock().getBlockData().getMaterial().equals(Material.ANCIENT_DEBRIS)) {
			String block;
			if(event.getBlock().getBlockData().getMaterial().equals(Material.DIAMOND_ORE)){
				block = "Diamond Ore";
			} else if(event.getBlock().getBlockData().getMaterial().equals(Material.ANCIENT_DEBRIS)) {
				block = "Ancient Debris";
			} else if (event.getBlock().getBlockData().getMaterial().equals(Material.EMERALD_ORE)) {
				block = "Emerald Ore";
			} else {
				block = "Spawner";
			}
			Player player = event.getPlayer();
			for(World w : getServer().getWorlds()){
			    for(Player p : w.getPlayers()){
			        if(p.hasPermission("oretracker.ot") && !muted.contains(p)){
			            p.sendMessage(ChatColor.GOLD + "[OreTracker]" + ChatColor.DARK_GRAY + " >> " + ChatColor.YELLOW + "" + player.getName() + ChatColor.GRAY + " has mined " +
			            		ChatColor.GOLD + "" + block);
			        }
			    }
			}
		}
	}
}
