package me.Tanner.SofishticatedFishing.Events;

import org.bukkit.entity.Blaze;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.plugin.Plugin;

import me.Tanner.SofishticatedFishing.Main;

public class BlazeSaver implements Listener{
	
	private Main plugin;
	
	public BlazeSaver(Main plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, (Plugin) plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onHit(EntityDamageEvent e) {
		if(e.getEntity() instanceof Blaze) {
			Blaze b = (Blaze) e.getEntity();
			if(e.getCause().equals(DamageCause.DROWNING)) {
				if(b.getMaxHealth() > 399.5 && b.getMaxHealth() < 400.5) {
					e.setCancelled(true);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onShotHit(EntityDamageByEntityEvent e) {
		if(e.getDamager().getType().equals(EntityType.SMALL_FIREBALL) && ((Projectile) e.getDamager()).getShooter() instanceof Blaze){
			Blaze b = (Blaze)((Projectile) e.getDamager()).getShooter();
			if(b.getMaxHealth() > 399.5 && b.getMaxHealth() < 400.5) {
				e.setDamage(50D);
			}
		}
	}
}
