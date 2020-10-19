package me.Tanner.SofishticatedFishing.Events;

import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.plugin.Plugin;

public class BurningMobEvent implements Listener {
	public BurningMobEvent(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, (Plugin) plugin);
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onEntityCombust(EntityCombustEvent event) {
		if (event.getEntity() instanceof LivingEntity) {
			LivingEntity entity = (LivingEntity) event.getEntity();
			if (entity.getMaxHealth() >= 49.5D && entity.getMaxHealth() <= 50.5D
					&& entity.getType().equals(EntityType.PHANTOM))
				event.setCancelled(true);
		}
	}
}
