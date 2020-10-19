package me.Tanner.EnchantingItems.Events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.github.aasmus.pvptoggle.PvPToggle;

import me.Tanner.EnchantingItems.Enchantments;
import net.md_5.bungee.api.ChatColor;

public class ArrowEvents implements Listener {

	private Enchantments plugin;

	public ArrowEvents(Enchantments plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onHit(ProjectileHitEvent e) {

		if (e.getEntity().getShooter() instanceof Player && (e.getEntity() instanceof Arrow
				|| e.getEntity() instanceof Egg || e.getEntity() instanceof Fireball)) {
			Player player = (Player) e.getEntity().getShooter();
			ItemStack bow = player.getInventory().getItemInMainHand();
			if ((bow.getType().equals(Material.BOW) || bow.getType().equals(Material.CROSSBOW)) && bow.hasItemMeta()
					&& bow.getItemMeta().hasLore()) {

				if (e.getHitEntity() != null && e.getHitEntity() instanceof Player) {
					Player hit = (Player) e.getHitEntity();
					if (PvPToggle.instance.players.get(hit.getUniqueId())
							|| PvPToggle.instance.players.get(player.getUniqueId())) {
						return;
					}
				}
				List<String> lore = bow.getItemMeta().getLore();
				if (!e.getEntity().getPassengers().isEmpty()) {
					e.getEntity().eject();
				}
				if (e.getHitEntity() != null && e.getHitEntity() instanceof LivingEntity) {
					LivingEntity victim = (LivingEntity) e.getHitEntity();
					if(!(victim instanceof Player) || pvp(player,(Player)victim)) {
						if (lore.contains(ChatColor.GRAY + "Toxicity")) {
							victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 1));
						}
						if (lore.contains(ChatColor.GRAY + "Chains")) {
							victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1));
						}
						if (lore.contains(ChatColor.GRAY + "Corruption")) {
							victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1));
						}
						if (lore.contains(ChatColor.GRAY + "Blinding")) {
							victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
						}
						if (lore.contains(ChatColor.GRAY + "Crippling")) {
							victim.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0));
						}
					}
				}
				if (lore.contains(ChatColor.GRAY + "Explosive")) {
					Location loc;
					if (e.getHitBlock() != null) {
						loc = e.getHitBlock().getLocation();
					} else {
						loc = e.getHitEntity().getLocation();
					}
					e.getEntity().getWorld().createExplosion(loc, 1, false, false);
				}
				if (lore.contains(ChatColor.GRAY + "Black Hole")) {
					Location loc;
					if (e.getHitBlock() != null) {
						loc = e.getHitBlock().getLocation();
					} else {
						loc = e.getHitEntity().getLocation();
					}
					List<Entity> entList = e.getEntity().getNearbyEntities(10, 10, 10);
					for (Entity ent : entList) {
						if (!ent.equals(e.getHitEntity()) && ent instanceof LivingEntity) {
							if(!(ent instanceof Player) || pvp(player,(Player)ent)) {
								Vector direction = loc.toVector().subtract(ent.getLocation().toVector()).normalize();
								ent.setVelocity(direction.multiply(2));
							}
						}
					}
				} else if (lore.contains(ChatColor.GRAY + "Repulsion")) {
					Location loc;
					if (e.getHitBlock() != null) {
						loc = e.getHitBlock().getLocation();
					} else {
						loc = e.getHitEntity().getLocation();
					}
					List<Entity> entList = e.getEntity().getNearbyEntities(10, 10, 10);
					for (Entity ent : entList) {
						if (!ent.equals(e.getHitEntity()) && ent instanceof LivingEntity) {
							if(!(ent instanceof Player) || pvp(player,(Player)ent)) {
								Vector direction = ent.getLocation().toVector().subtract(loc.toVector()).normalize();
								ent.setVelocity(direction.multiply(2));
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void OnArrowShoot(ProjectileLaunchEvent e) {
		if (e.getEntity().getShooter() instanceof Player && e.getEntity() instanceof Arrow) {
			Player player = (Player) e.getEntity().getShooter();
			ItemStack bow = player.getInventory().getItemInMainHand();
			ItemStack bowOff = player.getInventory().getItemInMainHand();
			List<String> lore = new ArrayList<>();
			
			if ((bow.getType().equals(Material.BOW) || bow.getType().equals(Material.CROSSBOW)) && bow.hasItemMeta()
					&& bow.getItemMeta().hasLore()) {
				lore = bow.getItemMeta().getLore();
			} else if ((bowOff.getType().equals(Material.BOW) || bowOff.getType().equals(Material.CROSSBOW)) && bowOff.hasItemMeta()
					&& bowOff.getItemMeta().hasLore()) {
				lore = bowOff.getItemMeta().getLore();
			}
			
			if(!lore.isEmpty()) {
				if (lore.contains(ChatColor.GRAY + "E g g")) {
					e.setCancelled(true);
					Projectile egg = (Projectile) e.getEntity().getWorld().spawnEntity(e.getLocation(), EntityType.EGG);
					egg.setShooter(e.getEntity().getShooter());
					egg.setVelocity(e.getEntity().getVelocity());
				} else if (lore.contains(ChatColor.GRAY + "Fireball")) {
					e.setCancelled(true);
					Projectile fireball = (Projectile) e.getEntity().getWorld().spawnEntity(
							((Player) e.getEntity().getShooter()).getEyeLocation(), EntityType.SMALL_FIREBALL);
					fireball.setShooter(e.getEntity().getShooter());
					fireball.setVelocity(e.getEntity().getVelocity());
				} else if (lore.contains(ChatColor.GRAY + "Party Popper")) {
					e.setCancelled(true);
					Projectile firework = (Projectile) e.getEntity().getWorld().spawnEntity(e.getLocation(),
							EntityType.FIREWORK);
					firework.setShooter(e.getEntity().getShooter());
					firework.setVelocity(e.getEntity().getVelocity());
				}
				if (lore.contains(ChatColor.GRAY + "Shotgun")) {
					Projectile arrow1 = (Projectile) e.getEntity().getWorld().spawnEntity(e.getLocation(),
							EntityType.ARROW);
					arrow1.setShooter(e.getEntity().getShooter());
					arrow1.setVelocity(rotateVector(e.getEntity().getVelocity(), -5));
					((Arrow) arrow1).setPickupStatus(PickupStatus.DISALLOWED);
					((Arrow) arrow1).setDamage(((Arrow)e.getEntity()).getDamage());
					Projectile arrow2 = (Projectile) e.getEntity().getWorld().spawnEntity(e.getLocation(),
							EntityType.ARROW);
					arrow2.setShooter(e.getEntity().getShooter());
					arrow2.setVelocity(rotateVector(e.getEntity().getVelocity(), 5));
					((Arrow) arrow2).setPickupStatus(PickupStatus.DISALLOWED);
					((Arrow) arrow2).setDamage(((Arrow)e.getEntity()).getDamage());
				}
			}
		}
	}

	private Vector rotateVector(Vector v, double angle) {
		double sin = Math.sin(Math.toRadians(angle));
		double cos = Math.cos(Math.toRadians(angle));

		double x = v.getX() * cos + v.getZ() * sin;
		double z = v.getX() * -sin + v.getZ() * cos;

		return v.setX(x).setZ(z);
	}

	private boolean pvp(Player damager, Player victum) {
		if (PvPToggle.instance.players.get(damager.getUniqueId())
				|| PvPToggle.instance.players.get(victum.getUniqueId())) {
			return false;
		}
		return true;
	}
}
