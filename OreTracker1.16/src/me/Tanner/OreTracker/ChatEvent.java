package me.Tanner.OreTracker;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {
	
	Main plugin;
	
	public ChatEvent(Main plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		if(plugin.stopChat) {
			event.setCancelled(true);
		}
	}

}
