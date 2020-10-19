package me.Tanner.CommandsPlus;

import org.bukkit.plugin.java.JavaPlugin;

//import me.Tanner.CommandsPlus.Commands.Bench;
import me.Tanner.CommandsPlus.Commands.Smelt;
//import me.Tanner.CommandsPlus.TabCompletion.BenchCompletion;

public class Main extends JavaPlugin {
	
	 @Override
	public void onEnable() {
		 this.getCommand("smelt").setExecutor(new Smelt(this));
		 this.getCommand("smeltall").setExecutor(new Smelt(this));
		 
		 //this.getCommand("bench").setExecutor(new Bench(this));
		 //this.getCommand("bench").setTabCompleter(new BenchCompletion());
	}

	@Override
	public void onDisable() {

	}
}
