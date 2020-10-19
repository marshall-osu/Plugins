package me.Tanner.EnchantingItems.Events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.github.aasmus.pvptoggle.PvPToggle;

import me.Tanner.EnchantingItems.Enchantments;
import net.md_5.bungee.api.ChatColor;

public class ArmorEvents implements Listener{
	
	private Enchantments plugin;
	
	public ArmorEvents(Enchantments plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onFall(EntityDamageEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if(e.getCause().equals(DamageCause.FALL)) {
				if(player.getInventory().getBoots() != null) {
					ItemStack boots = player.getInventory().getBoots();
					if(boots.hasItemMeta() && boots.getItemMeta().hasLore() && boots.getItemMeta().getLore().contains(ChatColor.GRAY + "PhD Flopper")) {
						e.setCancelled(true);
						List<Entity> entList = e.getEntity().getNearbyEntities(2, 2, 2);
						for (Entity ent : entList) {
							if(ent instanceof LivingEntity) {
								if(!(ent instanceof Player) || pvp(player,(Player)ent)) {
									Vector direction = ent.getLocation().toVector().subtract(e.getEntity().getLocation().toVector()).normalize();
									ent.setVelocity(direction);
									((LivingEntity) ent).damage(e.getDamage()*3);
								}
							}
						}
					}
				}
				
			}
		}
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
		if(e.getEntity() instanceof Player) {
			Player player = (Player) e.getEntity();
			if(player.getInventory().getChestplate() != null) {
				ItemStack chest = player.getInventory().getChestplate();
				if(chest.hasItemMeta() && chest.getItemMeta().hasLore() && chest.getItemMeta().getLore().contains(ChatColor.GRAY + "Molten")) {
					if(!(e.getDamager() instanceof Player) || pvp(player,(Player) e.getDamager())) {
						e.getDamager().setFireTicks(100);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		if(p.getInventory().getBoots() != null) {
			ItemStack boots = p.getInventory().getBoots();
			if(boots.hasItemMeta() && boots.getItemMeta().hasLore() && boots.getItemMeta().getLore().contains(ChatColor.GRAY + "Lava Walker")) {
				Location origin = p.getLocation().subtract(0,1,0);
				Location loc = origin.clone();
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						loc.setX(origin.getX() + i);
						loc.setZ(origin.getZ() + j);
						Block change = loc.getBlock();
						if(change.getType().equals(Material.LAVA)) {
							change.setType(Material.OBSIDIAN);
							Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
								change.setType(Material.LAVA);
							},60L);
						}
					}
				}
			}
		}
	}
	
	private boolean pvp(Player damager, Player victum) {
		if (PvPToggle.instance.players.get(damager.getUniqueId())
				|| PvPToggle.instance.players.get(victum.getUniqueId())) {
			return false;
		}
		return true;
	}
}
