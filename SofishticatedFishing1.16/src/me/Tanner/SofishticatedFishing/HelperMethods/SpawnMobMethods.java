package me.Tanner.SofishticatedFishing.HelperMethods;

import java.util.HashSet;
import java.util.Set;
import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.BlockFace;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Drowned;
import org.bukkit.entity.Endermite;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Evoker;
import org.bukkit.entity.Guardian;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Phantom;
import org.bukkit.entity.Player;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Ravager;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Stray;
import org.bukkit.entity.Vindicator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpawnMobMethods {
	private Main plugin;

	private DropItemMethods items;

	public Set<String> bosses = new HashSet<>();

	public SpawnMobMethods(Main plugin) {
		this.plugin = plugin;
		this.items = this.plugin.items;
		this.bosses.add("death");
		this.bosses.add("abomination");
	}

	@SuppressWarnings("deprecation")
	public void spawnSquid(Player player) {
      player.setVelocity(player.getFacing().getDirection().multiply(2).setY(1));
      Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
            World world = player.getWorld();
            Location loc = spawnLocation(player, 2, 1);
            loc.setY(loc.getY() + 2.0D);
            Squid squid = (Squid)world.spawnEntity(loc, EntityType.SQUID);
            squid.setCustomName("Stupid Squid");
            squid.setCustomNameVisible(true);
            squid.setMaxHealth(11.0D);
            squid.setHealth(11.0D);
            squid.setRemoveWhenFarAway(true);
          },30L);
    }

	@SuppressWarnings("deprecation")
	public void spawnSailor(Player player) {
      player.setVelocity(player.getFacing().getDirection().multiply(2).setY(1));
      player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 1));
      Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, () -> {
            World world =  player.getWorld();
            Location loc = spawnLocation( player, 2, 1);
            loc.setY(loc.getY() + 2.0D);
            Drowned sailor = (Drowned)world.spawnEntity(loc, EntityType.DROWNED);
            sailor.setCustomName("Forgotten Sailor");
            sailor.setCustomNameVisible(true);
            sailor.setMaxHealth(33.0D);
            sailor.setHealth(33.0D);
            sailor.setBaby(false);
            sailor.setCanPickupItems(false);
            sailor.getEquipment().getItemInMainHand().setType(Material.TRIDENT);
            ItemStack fishingHelm = new ItemStack(Material.LEATHER_HELMET);
            LeatherArmorMeta meta = (LeatherArmorMeta)fishingHelm.getItemMeta();
            meta.setColor(Color.YELLOW);
            meta.setDisplayName("Fisherman's Trusty Hat");
            fishingHelm.setItemMeta((ItemMeta)meta);
            sailor.getEquipment().setHelmet(fishingHelm);
            sailor.setRemoveWhenFarAway(true);
          },30L);
    }

	@SuppressWarnings("deprecation")
	public void spawnWarrior(Player player) {
      player.setVelocity(player.getFacing().getDirection().multiply(2).setY(1));
      Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, () -> {
            World world =  player.getWorld();
            Location loc = spawnLocation( player, 2, 1);
            loc.setY(loc.getY() + 2.0D);
            Stray warrior = (Stray)world.spawnEntity(loc, EntityType.STRAY);
            warrior.setCustomName("Ancient Warrior");
            warrior.setCustomNameVisible(true);
            warrior.setCanPickupItems(false);
             player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));
            ItemStack helm = new ItemStack(Material.IRON_HELMET);
            ItemStack boots = new ItemStack(Material.IRON_BOOTS);
            ItemStack chest = new ItemStack(Material.IRON_CHESTPLATE);
            ItemStack legs = new ItemStack(Material.IRON_LEGGINGS);
            ItemStack bow = new ItemStack(Material.BOW);
            ItemMeta bowMeta = bow.getItemMeta();
            bowMeta.addEnchant(Enchantment.ARROW_DAMAGE, 5, true);
            bowMeta.addEnchant(Enchantment.ARROW_KNOCKBACK, 2, true);
            bowMeta.setDisplayName("Bow of the Warrior");
            bow.setItemMeta(bowMeta);
            warrior.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(15.0D);
            ItemMeta meta = helm.getItemMeta();
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 3, true);
            helm.setItemMeta(meta);
            boots.setItemMeta(meta);
            chest.setItemMeta(meta);
            legs.setItemMeta(meta);
            ItemStack[] armor = { boots, legs, chest, helm };
            warrior.getEquipment().setArmorContents(armor);
            warrior.getEquipment().setBootsDropChance(0.0F);
            warrior.getEquipment().setHelmetDropChance(0.0F);
            warrior.getEquipment().setLeggingsDropChance(0.0F);
            warrior.getEquipment().setChestplateDropChance(0.0F);
            warrior.getEquipment().setItemInMainHand(bow);
            warrior.setMaxHealth(100.0D);
            warrior.setHealth(100.0D);
            warrior.setRemoveWhenFarAway(true);
          },30L);
    }

	@SuppressWarnings("deprecation")
	public void spawnAlien(Player player) {
      player.setVelocity(player.getFacing().getDirection().multiply(2).setY(1));
      Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, () -> {
             player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 250, 3));
            World world =  player.getWorld();
            Location loc = spawnLocation( player, 2, 1);
            loc.setY(loc.getY() + 2.0D);
            Guardian alien = (Guardian)world.spawnEntity(loc, EntityType.GUARDIAN);
            alien.setCustomName("Alien Soldier");
            alien.setCustomNameVisible(true);
            alien.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20.0D);
            alien.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 3));
            alien.setMaxHealth(150.0D);
            alien.setHealth(150.0D);
            alien.setRemoveWhenFarAway(true);
          },30L);
    }

	@SuppressWarnings("deprecation")
	public void spawnMamaBear(Player player) {
      player.setVelocity(player.getFacing().getDirection().multiply(2).setY(1));
      Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, () -> {
             player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 500, 1));
            World world =  player.getWorld();
            Location loc = spawnLocation( player, 2, 1);
            loc.setY(loc.getY() + 2.0D);
            PolarBear bear = (PolarBear)world.spawnEntity(loc, EntityType.POLAR_BEAR);
            bear.setCustomName("Mama Bear");
            bear.setAdult();
            bear.setTarget((LivingEntity) player);
            bear.setCustomNameVisible(true);
            bear.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 1));
            bear.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(20.0D);
            bear.setMaxHealth(150.0D);
            bear.setHealth(150.0D);
            bear.setRemoveWhenFarAway(true);
          },30L);
    }
	
	@SuppressWarnings("deprecation")
	public void spawnSeventhSon(Player player) {
        player.setVelocity(player.getFacing().getDirection().multiply(2).setY(1));
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
            player.setFireTicks(80);
            World world = player.getWorld();
            Location loc = spawnLocation(player, 2, 1);
            loc.setY(loc.getY() + 2);
            Blaze sSon = (Blaze) world.spawnEntity(loc, EntityType.BLAZE);
            sSon.setCustomName("The Seventh Son");
            sSon.setCustomNameVisible(true);
            sSon.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40D);
            sSon.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 3));
            sSon.setMaxHealth(400.0);
            sSon.setHealth(400.0);
            sSon.setRemoveWhenFarAway(true);
        }, 30L);
    }

	@SuppressWarnings("deprecation")
	public void hunter(Player player) {
		World world = player.getWorld();
		Location loc = spawnLocation(player, 4, 1);
		loc = spawnLocation(player, 6, 1);
		Ravager mount = (Ravager) world.spawnEntity(loc, EntityType.RAVAGER);
		mount.setMaxHealth(150.0D);
		mount.setHealth(150.0D);
		mount.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 1));
		mount.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40.0D);
		mount.setCustomName(ChatColor.DARK_RED + "Tamed Beast");
		Evoker hunter = (Evoker) world.spawnEntity(loc, EntityType.EVOKER);
		hunter.setMaxHealth(250.0D);
		hunter.setHealth(250.0D);
		hunter.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 1));
		hunter.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(30.0D);
		hunter.setPatrolLeader(false);
		hunter.setCustomName(ChatColor.RED + "Bloodthirsty " + ChatColor.YELLOW + "Hunter");
		mount.setPassenger((Entity) hunter);
		hunter.setRemoveWhenFarAway(true);
		mount.setRemoveWhenFarAway(true);
	}

	@SuppressWarnings("deprecation")
	public void enderRider(Player player) {
		World world = player.getWorld();
		Location loc = spawnLocation(player, 4, 1);
		Squid squid = (Squid) world.spawnEntity(loc, EntityType.SQUID);
		Endermite ender = (Endermite) world.spawnEntity(loc, EntityType.ENDERMITE);
		ender.setPassenger((Entity) squid);
		ender.setMaxHealth(50.0D);
		ender.setHealth(50.0D);
		ender.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 3));
		ender.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(10.0D);
		squid.setMaxHealth(50.0D);
		squid.setHealth(50.0D);
		squid.setCustomName(ChatColor.AQUA + "Ender Rider");
		squid.setCustomNameVisible(true);
		squid.setCollidable(false);
		squid.setMaximumAir(2147483647);
		squid.setRemainingAir(2147483647);
		ender.setCustomName(ChatColor.DARK_PURPLE + "Le Char");
		ender.setCustomNameVisible(true);
		squid.setRemoveWhenFarAway(true);
		ender.setRemoveWhenFarAway(true);
	}

	@SuppressWarnings("deprecation")
	public void UFO(Player player) {
		World world = player.getWorld();
		Location loc = spawnLocation(player, 4, 7);
		Phantom p = (Phantom) world.spawnEntity(loc, EntityType.PHANTOM);
		Skeleton s = (Skeleton) world.spawnEntity(loc, EntityType.SKELETON);
		p.setPassenger((Entity) s);
		p.setMaxHealth(50.0D);
		p.setHealth(50.0D);
		s.setMaxHealth(50.0D);
		s.setHealth(50.0D);
		s.getEquipment().setBoots(this.items.ancientBoots());
		s.getEquipment().setHelmet(this.items.ancientHelmet());
		s.getEquipment().setChestplate(this.items.ancientChestplate());
		s.getEquipment().setLeggings(this.items.ancientLeggings());
		s.getEquipment().setBootsDropChance(0.01F);
		s.getEquipment().setHelmetDropChance(0.01F);
		s.getEquipment().setLeggingsDropChance(0.01F);
		s.getEquipment().setChestplateDropChance(0.01F);
		s.setCanPickupItems(false);
		s.getEquipment().getItemInHand().addEnchantment(Enchantment.ARROW_DAMAGE, 5);
		s.getEquipment().getItemInHand().addEnchantment(Enchantment.ARROW_FIRE, 1);
		p.setCustomName(ChatColor.DARK_PURPLE + "UFO");
		s.setCustomName(ChatColor.AQUA + "UFO Pilot");
		p.setRemoveWhenFarAway(true);
		s.setRemoveWhenFarAway(true);
	}

	@SuppressWarnings("deprecation")
	public void LostLumberjack(Player player) {
      player.setVelocity(player.getFacing().getDirection().multiply(2).setY(1));
      Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, () -> {
            World world =  player.getWorld();
            Location loc = spawnLocation( player, 2, 1);
            loc.setY(loc.getY() + 2.0D);
             player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 500, 1));
            Vindicator v = (Vindicator)world.spawnEntity(loc, EntityType.VINDICATOR);
            v.setMaxHealth(350.0D);
            v.setHealth(350.0D);
            v.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2147483647, 2));
            v.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(40.0D);
          },30L);
    }

	@SuppressWarnings("deprecation")
	public void mob(Location loc) {
		for (int i = 0; i < 30; i++) {
			Blaze b = (Blaze) loc.getWorld().spawnEntity(loc, EntityType.BLAZE);
			b.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(100.0D);
			b.setMaxHealth(50.0D);
			b.setHealth(50.0D);
		}
	}

	public Location spawnLocation(Player player, int xOffset, int yOffset) {
		Location loc = player.getLocation();
		if (player.getFacing() == BlockFace.EAST) {
			loc.setX(loc.getX() + xOffset);
		} else if (player.getFacing() == BlockFace.WEST) {
			loc.setX(loc.getX() - xOffset);
		} else if (player.getFacing() == BlockFace.NORTH) {
			loc.setZ(loc.getZ() - xOffset);
		} else if (player.getFacing() == BlockFace.SOUTH) {
			loc.setZ(loc.getZ() + xOffset);
		}
		loc.setY(loc.getY() + yOffset);
		return loc;
	}
}
