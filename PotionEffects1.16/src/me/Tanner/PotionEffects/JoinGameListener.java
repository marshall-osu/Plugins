package me.Tanner.PotionEffects;

import java.util.Set;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinGameListener implements Listener {
	
	private Main plugin;
	
	public JoinGameListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if(this.plugin.getConfig().contains(player.getUniqueId().toString())) {
			Set<String> playerEffects = this.plugin.getConfig().getConfigurationSection("effects." + player.getUniqueId().toString()).getKeys(false);
			for(String effect : playerEffects) {
				player.addPotionEffect(this.plugin.getEffect(effect, this.plugin.getConfig().getInt("effects." + player.getUniqueId().toString() + "." + effect + ".power")));
			}
		}
	}
}
