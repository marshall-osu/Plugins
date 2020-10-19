package me.Tanner.EnchantingItems.Events;

import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.github.aasmus.pvptoggle.PvPToggle;

import me.Tanner.EnchantingItems.Enchantments;
import net.md_5.bungee.api.ChatColor;

public class SwordHitEvent implements Listener {
	
	private Enchantments plugin;
	private Random rand;
	
	public SwordHitEvent(Enchantments plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
		rand = new Random();
	}
	
	@EventHandler()
    public void swordHit(EntityDamageByEntityEvent event) {
        if(event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            
            
            if(player.getInventory().getItemInMainHand() == null) {
                return;
            }
            if(!player.getInventory().getItemInMainHand().hasItemMeta()) {
                return;
            }
            if(!player.getInventory().getItemInMainHand().getItemMeta().hasLore()) {
                return;
            }
            if(!(event.getEntity() instanceof LivingEntity)) {
            	return;
            }
            if(event.getEntity() instanceof Player) {
            	if(PvPToggle.instance.players.get(((Player) event.getEntity()).getUniqueId()) || PvPToggle.instance.players.get(((Player) event.getDamager()).getUniqueId())) {
            		return;
            	}
            }
            
            LivingEntity ent = (LivingEntity) event.getEntity();
            List<String> lore = player.getInventory().getItemInMainHand().getItemMeta().getLore();
            
            if (lore.contains(ChatColor.GRAY + "Toxicity")) {
                ent.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 1));
            }
            if (lore.contains(ChatColor.GRAY + "Chains")) {
            	ent.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1));
            }
            if (lore.contains(ChatColor.GRAY + "Corruption")) {
            	ent.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 1));
            }
            if (lore.contains(ChatColor.GRAY + "Blinding")) {
            	ent.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
            }
            if (lore.contains(ChatColor.GRAY + "Crippling")) {
            	ent.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 200, 0));
            }
            if(lore.contains(ChatColor.GRAY + "Heavy")) {
            	Vector direction = ent.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
				ent.setVelocity(direction.multiply(3).setY(2));
            }
            if(lore.contains(ChatColor.GRAY + "Stripper")) {
            	ItemStack[] armor = ent.getEquipment().getArmorContents();
            	int i = rand.nextInt(4);
            	if(armor[i] != null) {
            		ItemStack drop = armor[i];
            		armor[i] = null;
            		ent.getEquipment().setArmorContents(armor);
            		player.getWorld().dropItemNaturally(ent.getLocation(), drop);
            	}
            }
            if(lore.contains(ChatColor.GRAY + "Lifesteal")) {
            	double damage = event.getDamage();
            	double heal = player.getHealth() + (damage * 0.2);
            	if(heal > 20.0) {
            		heal = 20.0;
            	}
            	player.setHealth(heal);
            }
            if(ent.getHealth() - event.getDamage() <= 0) {
            	if(lore.contains(ChatColor.GRAY + "Decapitation")) {
            		ItemStack head = getHead(ent);
            		if(head != null) {
            			ent.getWorld().dropItemNaturally(ent.getLocation(), head);
            		}
            	}
            }
        }
    }
	
	private ItemStack getHead(LivingEntity mob) {
		ItemStack head = null;
		if(rand.nextInt(5) == 0) {
			switch (mob.getType()){
			case CREEPER:
				head = new ItemStack(Material.CREEPER_HEAD);
				break;
			case ZOMBIE:
				head = new ItemStack(Material.ZOMBIE_HEAD);
				break;
			case WITHER_SKELETON:
				head = new ItemStack(Material.WITHER_SKELETON_SKULL);
				break;
			case SKELETON:
				head = new ItemStack(Material.SKELETON_SKULL);
				break;
			case ENDER_DRAGON:
				head = new ItemStack(Material.DRAGON_HEAD);
				break;
			default:
				break;
			}
		}
		return head;
	}
}
