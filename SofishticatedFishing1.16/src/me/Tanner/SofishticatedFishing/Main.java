package me.Tanner.SofishticatedFishing;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import me.Tanner.SofishticatedFishing.Events.BlazeSaver;
import me.Tanner.SofishticatedFishing.Events.BossSpawnEvent;
import me.Tanner.SofishticatedFishing.Events.BurningMobEvent;
import me.Tanner.SofishticatedFishing.Events.FishEvent;
import me.Tanner.SofishticatedFishing.Events.MobDropEvent;
import me.Tanner.SofishticatedFishing.Files.DataManager;
import me.Tanner.SofishticatedFishing.GUI.RecipesCommand;
import me.Tanner.SofishticatedFishing.HelperMethods.DropItemMethods;
import me.Tanner.SofishticatedFishing.HelperMethods.Recipes;
import me.Tanner.SofishticatedFishing.HelperMethods.SpawnMobMethods;
import me.Tanner.SofishticatedFishing.Loot.AddLoot;
import me.Tanner.SofishticatedFishing.Loot.DeleteLoot;
import me.Tanner.SofishticatedFishing.Loot.EditLoot;
import me.Tanner.SofishticatedFishing.Loot.ViewLoot;
import me.Tanner.SofishticatedFishing.TabCompleters.LootTabCompletion;
import me.Tanner.SofishticatedFishing.TabCompleters.MichaelComplainedCompletion;
import me.Tanner.SofishticatedFishing.TabCompleters.TestingTabCompletion;
import me.Tanner.SofishticatedFishing.Tournaments.FishingTournament;
import me.Tanner.SofishticatedFishing.Tournaments.ForceTournament;
import me.Tanner.SofishticatedFishing.Tournaments.StopTournament;
import me.Tanner.SofishticatedFishing.Tournaments.TournamentStatus;
import me.Tanner.SofishticatedFishing.Tournaments.Tournaments;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin {
	public String header = ChatColor.AQUA + "[S Fishing]" + ChatColor.DARK_GRAY + " >> " + ChatColor.GRAY;

	public DataManager data;

	public DropItemMethods items;

	public SpawnMobMethods spawns;

	public Recipes recipes;

	public FishingTournament tournTracker;

	public BukkitTask game;

	public boolean tournamentsOn = false;

	public boolean stopTourn = false;

	public boolean forceTourn = false;

	public boolean activeTournament = false;

	public Map<String, Integer> tournament = new HashMap<>();

	public void onEnable() {
		
		this.data = new DataManager(this);
		this.items = new DropItemMethods(this);
		this.recipes = new Recipes(this);
		this.spawns = new SpawnMobMethods(this);
		
		new BossSpawnEvent(this);
		new BurningMobEvent(this);
		new FishEvent(this);
		new MobDropEvent(this);
		new BlazeSaver(this);
		
		for (ShapedRecipe recipe : this.recipes.recipes) {
			Bukkit.addRecipe(recipe);
		}
		
		getCommand("addloot").setExecutor((CommandExecutor) new AddLoot(this));
		getCommand("addloot").setTabCompleter((TabCompleter) new LootTabCompletion(this));
		
		getCommand("editloot").setExecutor((CommandExecutor) new EditLoot(this));
		getCommand("editloot").setTabCompleter((TabCompleter) new LootTabCompletion(this));
		
		getCommand("deleteloot").setExecutor((CommandExecutor) new DeleteLoot(this));
		getCommand("deleteloot").setTabCompleter((TabCompleter) new LootTabCompletion(this));
		
		getCommand("viewloot").setExecutor((CommandExecutor) new ViewLoot(this));
		getCommand("viewloot").setTabCompleter((TabCompleter) new LootTabCompletion(this));
		
		getCommand("sffish").setExecutor((CommandExecutor) new RecipesCommand(this));
		getCommand("sffish").setTabCompleter((TabCompleter) new MichaelComplainedCompletion(this));
		
		getCommand("tournaments").setExecutor((CommandExecutor) new Tournaments(this));
		
		getCommand("tournamentstatus").setExecutor((CommandExecutor) new TournamentStatus(this));
		
		getCommand("forcetourn").setExecutor((CommandExecutor) new ForceTournament(this));
		
		getCommand("stoptourn").setExecutor((CommandExecutor) new StopTournament(this));
		
		getCommand("testing").setExecutor(new TestingCommands(this));
		getCommand("testing").setTabCompleter((TabCompleter) new TestingTabCompletion());
	}

	public void onDisable() {
		
	}

	public ItemStack lootTable(String lootTable) {
		ConfigurationSection table = this.data.getConfig().getConfigurationSection("pools." + lootTable);
		if(table != null) {
			int max = table.getInt("totalweight");
			Random random = new Random();
			int pick = random.nextInt(max);
			int sum = 0;
			for (String item : table.getKeys(false)) {
				if (!item.equals("totalweight")) {
					sum += this.data.getConfig().getInt("pools." + lootTable + "." + item + ".weight");
					if (sum > pick)
						return new ItemStack(
								this.data.getConfig().getItemStack("pools." + lootTable + "." + item + ".itemstack"));
				}
			}
		}
		return null;
	}

	public boolean commandTable(Player player) {
		ConfigurationSection table = this.data.getConfig().getConfigurationSection("commands.fishing");
		int max = table.getInt("totalweight");
		Random random = new Random();
		int pick = random.nextInt(max);
		int sum = 0;
		for (String item : table.getKeys(false)) {
			if (!item.equals("totalweight")) {
				sum += this.data.getConfig().getInt("commands.fishing." + item + ".weight");
				if (sum > pick) {
					String command = this.data.getConfig().getString("commands.fishing." + item + ".cmd");
					if (!command.equals("air")) {
						player.sendMessage(
								String.valueOf(this.header) + " looks like you've caught something special!");
						command = command.replace("[player]", player.getName());
						Bukkit.dispatchCommand((CommandSender) Bukkit.getConsoleSender(), command);
						return true;
					}
					return false;
				}
			}
		}
		return false;
	}
}
