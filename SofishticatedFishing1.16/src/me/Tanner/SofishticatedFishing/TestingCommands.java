package me.Tanner.SofishticatedFishing;

import me.Tanner.SofishticatedFishing.HelperMethods.DropItemMethods;
import me.Tanner.SofishticatedFishing.HelperMethods.SpawnMobMethods;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class TestingCommands implements CommandExecutor {
	DropItemMethods items;

	SpawnMobMethods spawns;

	Main plugin;

	public TestingCommands(Main plugin) {
		this.plugin = plugin;
		this.items = this.plugin.items;
		this.spawns = this.plugin.spawns;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("testing")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (player.hasPermission("testing.use")) {
					if (args.length >= 1) {
						ItemStack axe;
						switch (args[0]) {
						case "plank":
							this.items.giveItem(this.items.waterLoggedPlank(), player);
							return true;
						case "salmonshovel":
							this.items.giveItem(this.items.salmonShovel(), player);
							return true;
						case "reinforcedrod":
							this.items.giveItem(this.items.reinforcedRod(), player);
							return true;
						case "salmonpickaxe":
							this.items.giveItem(this.items.salmonPickaxe(), player);
							return true;
						case "tentacle":
							this.items.giveItem(this.items.tentacle(), player);
							return true;
						case "hunter":
							this.spawns.hunter(player);
							return true;
						case "sailor":
							this.spawns.spawnSailor(player);
							return true;
						case "waterp":
							this.items.giveItem(this.items.waterPearl(), player);
							return true;
						case "ancientwar":
							this.spawns.spawnWarrior(player);
							return true;
						case "axehead":
							axe = this.items.axeHead();
							axe.setAmount(64);
							this.items.giveItem(axe, player);
							return true;
						case "cod":
							this.items.giveItem(this.items.deepWaterCod(), player);
							return true;
						case "ufo":
							this.spawns.UFO(player);
							return true;
						case "lumberjack":
							this.spawns.LostLumberjack(player);
							return true;
						case "bait":
							this.items.giveItem(this.items.bait(), player);
							return true;
						case "mama":
							this.spawns.spawnMamaBear(player);
							return true;
						case "pelt":
							this.items.giveItem(this.items.bearPelt(), player);
							return true;
						case "rod1":
							this.items.giveItem(this.items.basicFishingRod(), player);
							return true;
						case "rod2":
							this.items.giveItem(this.items.secondFishingRod(), player);
							return true;
						case "rod3":
							this.items.giveItem(this.items.thirdFishingRod(), player);
							return true;
						case "rod4":
							this.items.giveItem(this.items.fourthFishingRod(), player);
							return true;
						case "rod5":
							this.items.giveItem(this.items.fifthFishingRod(), player);
							return true;
						case "rod6":
							this.items.giveItem(this.items.sixthFishingRod(), player);
							return true;
						case "rod7":
							this.items.giveItem(this.items.salmonRod(), player);
							return true;
						case "rod8":
							this.items.giveItem(this.items.PrecursorRod(), player);
							return true;
						case "alien":
							this.spawns.spawnAlien(player);
							return true;
						case "bossspawn1":
							this.items.giveItem(this.items.bossEggItem(), player);
							return true;
						case "bossspawn2":
							this.items.giveItem(this.items.secondBossItem(), player);
							return true;
						case "scale":
							this.items.giveItem(this.items.scale(), player);
							return true;
						case "squid":
							this.spawns.spawnSquid(player);
							return true;
						case "seventhson":
							this.spawns.spawnSeventhSon(player);
							return true;
						case "sonsword":
							this.items.giveItem(this.items.SeventhSonsSword(), player);
							return true;
						case "sonsarmour":
							this.items.giveItem(this.items.SeventhSonsBoots(), player);
							this.items.giveItem(this.items.SeventhSonsCloak(), player);
							this.items.giveItem(this.items.SeventhSonsHelm(), player);
							this.items.giveItem(this.items.SeventhSonsLeggings(), player);
							return true;
						case "soul":
							this.items.giveItem(this.items.SeventhSonsSoul(), player);
							return true;
						case "salmonaxe":
							this.items.giveItem(this.items.salmonAxe(), player);
							return true;
						case "salmonbar":
							this.items.giveItem(this.items.salmonBar(), player);
							return true;
						case "decoybear":
							this.items.giveItem(this.items.imitationBear(), player);
							return true;
						case "coldice":
							this.items.giveItem(this.items.ice(), player);
							return true;
						case "ancientarmour":
							this.items.giveItem(this.items.ancientBoots(), player);
							this.items.giveItem(this.items.ancientChestplate(), player);
							this.items.giveItem(this.items.ancientHelmet(), player);
							this.items.giveItem(this.items.ancientLeggings(), player);
							return true;
						case "ufoshard":
							this.items.giveItem(this.items.UFOShard(), player);
							return true;
						case "enderrider":
							this.spawns.enderRider(player);
							return true;
						case "enderspawn":
							this.items.giveItem(this.items.bossEggItem(), player);
							return true;
						case "stuffing":
							this.items.giveItem(this.items.bearMeat(), player);
							return true;
						case "salmonessence":
							this.items.giveItem(this.items.salmonEssence(), player);
							return true;
						}
						player.sendMessage("Error: that is not a valid item/mob");
						return true;
					}
					player.sendMessage("Error: Use format /test [item/mob]");
					return true;
				}
				player.sendMessage("Error: You don't have permission to use this command");
				return true;
			}
			sender.sendMessage("Error: Console cannot call this command");
			return true;
		}
		return false;
	}
}
