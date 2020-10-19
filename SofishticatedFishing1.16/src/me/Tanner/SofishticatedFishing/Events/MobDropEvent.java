package me.Tanner.SofishticatedFishing.Events;

import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class MobDropEvent implements Listener {
	Main plugin;

	public MobDropEvent(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, (Plugin) plugin);
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDeath(EntityDeathEvent event) {
		LivingEntity mob = event.getEntity();
		Player player = event.getEntity().getKiller();
		String lootTable = "", message = "";
		if (mob.getMaxHealth() > 10.5D && mob.getMaxHealth() < 11.5D && mob.getType().equals(EntityType.SQUID)) {
			lootTable = "squid";
			message = "That wasn't a very smart squid";
		} else if (mob.getMaxHealth() > 32.5D && mob.getMaxHealth() < 33.5D
				&& mob.getType().equals(EntityType.DROWNED)) {
			lootTable = "sailor";
			message = "May he rest in peace";
		} else if (mob.getMaxHealth() > 49.5D && mob.getMaxHealth() < 50.5D
				&& mob.getType().equals(EntityType.ENDERMITE)) {
			lootTable = "ender";
			message = "Well that was strange";
		} else if (mob.getMaxHealth() > 49.5D && mob.getMaxHealth() < 50.5D
				&& mob.getType().equals(EntityType.SKELETON)) {
			lootTable = "ufo";
			message = "I always knew they were real";
		} else if (mob.getMaxHealth() > 99.5D && mob.getMaxHealth() < 100.5D
				&& mob.getType().equals(EntityType.STRAY)) {
			lootTable = "warrior";
			message = "He's just a pile of bones now";
		} else if (mob.getMaxHealth() > 149.5D && mob.getMaxHealth() < 150.5D
				&& mob.getType().equals(EntityType.GUARDIAN)) {
			lootTable = "alien";
			message = "Hopefuly that's the last of them";
		} else if (mob.getMaxHealth() > 149.5D && mob.getMaxHealth() < 150.5D
				&& mob.getType().equals(EntityType.POLAR_BEAR)) {
			lootTable = "mama";
			message = "That was a big one!";
		} else if (mob.getMaxHealth() > 249.5D && mob.getMaxHealth() < 250.5D
				&& mob.getType().equals(EntityType.EVOKER)) {
			lootTable = "hunter";
			message = "The hunter has become the prey";
		} else if (mob.getMaxHealth() > 349.5D && mob.getMaxHealth() < 350.5D
				&& mob.getType().equals(EntityType.VINDICATOR)) {
			lootTable = "lumberjack";
			message = "That's the last tree he'll chop";
		} else if (mob.getMaxHealth() > 399.5D && mob.getMaxHealth() < 400.5D
				&& mob.getType().equals(EntityType.BLAZE)) {
			lootTable = "seventhson";
			message = "You feel the soul of your defeated enemy rush through your body";
		}

		if (!lootTable.equals("")) {
			ItemStack drop = this.plugin.lootTable(lootTable);
			if(!drop.equals(new ItemStack(Material.AIR))) {
				player.sendMessage(String.valueOf(this.plugin.header) + message);
				event.getDrops().clear();
				event.getDrops().add(drop);
			}
		}
	}
}
