package me.Tanner.EnchantingItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.Tanner.EnchantingItems.Commands.Enchant;
import me.Tanner.EnchantingItems.Events.AnvilEvent;
import me.Tanner.EnchantingItems.Events.ArmorEvents;
import me.Tanner.EnchantingItems.Events.ArrowEvents;
import me.Tanner.EnchantingItems.Events.BlockBreak;
import me.Tanner.EnchantingItems.Events.BlockInteract;
import me.Tanner.EnchantingItems.Events.SwordHitEvent;
import me.Tanner.EnchantingItems.TabCompletion.EnchantsCompletion;
import net.coreprotect.CoreProtect;
import net.coreprotect.CoreProtectAPI;

public class Enchantments extends JavaPlugin{
	
	public CustomEnchants enchants;
	
	public Map<String, List<String>> conflicts;
	public Set<Location> placedBlocks;

	@Override
	public void onEnable() {
		new BlockBreak(this);
		new BlockInteract(this);
		new AnvilEvent(this);
		new SwordHitEvent(this);
		new ArrowEvents(this);
		new ArmorEvents(this);
		
		this.getCommand("enchants").setExecutor(new Enchant(this));
		this.getCommand("enchants").setTabCompleter(new EnchantsCompletion(this));
		
		this.conflicts = new HashMap<>();
		
		List<String> blackhole = new ArrayList<>();
		blackhole.add(ChatColor.GRAY + "Repulsion");
		
		List<String> repulsion = new ArrayList<>();
		repulsion.add(ChatColor.GRAY + "Black Hole");
		
		List<String> egg = new ArrayList<>();
		egg.add(ChatColor.GRAY + "Fireball");
		egg.add(ChatColor.GRAY + "Party Popper");
		
		List<String> fireball = new ArrayList<>();
		fireball.add(ChatColor.GRAY + "E g g");
		fireball.add(ChatColor.GRAY + "Party Popper");
		
		List<String> firework = new ArrayList<>();
		firework.add(ChatColor.GRAY + "E g g");
		firework.add(ChatColor.GRAY + "Fireball");
		
		this.conflicts.put("Black Hole", blackhole);
		this.conflicts.put("Repulsion", repulsion);
		this.conflicts.put("E g g", egg);
		this.conflicts.put("Fireball", fireball);
		this.conflicts.put("Party Popper", firework);
		
		this.enchants = new CustomEnchants();
		this.placedBlocks = new HashSet<>();
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack addEnchantment(ItemStack item, Enchantment enchant) {
		item.addUnsafeEnchantment(enchant,  1);
		
		ItemMeta meta = item.getItemMeta();
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "" + enchant.getName());
		if(meta.hasLore()) {
			for(String l : meta.getLore()) {
				lore.add(l);
			}
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}

	public CoreProtectAPI getCoreProtect() {
	        Plugin plugin = getServer().getPluginManager().getPlugin("CoreProtect");
	     
	        // Check that CoreProtect is loaded
	        if (plugin == null || !(plugin instanceof CoreProtect)) {
	            return null;
	        }

	        // Check that the API is enabled
	        CoreProtectAPI CoreProtect = ((CoreProtect) plugin).getAPI();
	        if (CoreProtect.isEnabled() == false) {
	            return null;
	        }

	        // Check that a compatible version of the API is loaded
	        if (CoreProtect.APIVersion() < 6) {
	            return null;
	        }

	        return CoreProtect;
	}

	@Override
	public void onDisable() {

	}
}
