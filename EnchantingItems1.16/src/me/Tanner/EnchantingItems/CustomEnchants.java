package me.Tanner.EnchantingItems;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

public class CustomEnchants {
	
	public Enchantment TELEPATHY;
	public Enchantment HAMMER;
	public Enchantment SCYTH;
	public Enchantment PLOW;
	public Enchantment PLANTER;
	public Enchantment AUTOPLANKER;
	public Enchantment TREEFELLER;
	public Enchantment AUTOSMELTER;
	public Enchantment FERTILIZER;
	public Enchantment TOXICITY;
	public Enchantment CORRUPTION;
	public Enchantment BLINDING;
	public Enchantment CHAINS;
	public Enchantment CRIPPLING;
	public Enchantment EXPLOSIVE;
	public Enchantment BLACKHOLE;
	public Enchantment REPULSION;
	public Enchantment EGG;
	public Enchantment FIREBALL;
	public Enchantment FIREWORK;
	public Enchantment LAVAWALKER;
	public Enchantment PHDFLOPPER;
	public Enchantment MOLTEN;
	
	public Enchantment HEAVY;
	public Enchantment STRIPPER;
	public Enchantment LIFESTEAL;
	public Enchantment DECAPITATION;
	public Enchantment SHOTGUN;
	
	public Set<Enchantment> enchants = new HashSet<>();
	
	public CustomEnchants() {
		Set<Material> tele = new HashSet<>();
		tele.add(Material.NETHERITE_PICKAXE);
		tele.add(Material.DIAMOND_PICKAXE);
		tele.add(Material.GOLDEN_PICKAXE);
		tele.add(Material.IRON_PICKAXE);
		tele.add(Material.STONE_PICKAXE);
		tele.add(Material.WOODEN_PICKAXE);
		tele.add(Material.NETHERITE_SHOVEL);
		tele.add(Material.DIAMOND_SHOVEL);
		tele.add(Material.GOLDEN_SHOVEL);
		tele.add(Material.IRON_SHOVEL);
		tele.add(Material.STONE_SHOVEL);
		tele.add(Material.WOODEN_SHOVEL);
		tele.add(Material.NETHERITE_AXE);
		tele.add(Material.DIAMOND_AXE);
		tele.add(Material.GOLDEN_AXE);
		tele.add(Material.IRON_AXE);
		tele.add(Material.STONE_AXE);
		tele.add(Material.WOODEN_AXE);
		tele.add(Material.NETHERITE_HOE);
		tele.add(Material.DIAMOND_HOE);
		tele.add(Material.GOLDEN_HOE);
		tele.add(Material.IRON_HOE);
		tele.add(Material.STONE_HOE);
		tele.add(Material.WOODEN_HOE);
		tele.add(Material.SHEARS);
		TELEPATHY = new EnchantmentWrapper("telepathy", "Telepathy", 1, tele);
		
		Set<Material> diggas = new HashSet<>();
		diggas.add(Material.NETHERITE_PICKAXE);
		diggas.add(Material.DIAMOND_PICKAXE);
		diggas.add(Material.GOLDEN_PICKAXE);
		diggas.add(Material.IRON_PICKAXE);
		diggas.add(Material.STONE_PICKAXE);
		diggas.add(Material.WOODEN_PICKAXE);
		diggas.add(Material.NETHERITE_SHOVEL);
		diggas.add(Material.DIAMOND_SHOVEL);
		diggas.add(Material.GOLDEN_SHOVEL);
		diggas.add(Material.IRON_SHOVEL);
		diggas.add(Material.STONE_SHOVEL);
		diggas.add(Material.WOODEN_SHOVEL);
		diggas.add(Material.SHEARS);
		HAMMER = new EnchantmentWrapper("hammer", "Hammer", 1, diggas);
		
		AUTOSMELTER = new EnchantmentWrapper("autosmelt", "Auto Smelter", 1, diggas);
		
		Set<Material> hoes = new HashSet<>();
		hoes.add(Material.NETHERITE_HOE);
		hoes.add(Material.DIAMOND_HOE);
		hoes.add(Material.GOLDEN_HOE);
		hoes.add(Material.IRON_HOE);
		hoes.add(Material.STONE_HOE);
		hoes.add(Material.WOODEN_HOE);
		SCYTH = new EnchantmentWrapper("scyth", "Scyth", 1, hoes);
		
		PLOW = new EnchantmentWrapper("plow", "Plow", 1, hoes);
		
		PLANTER = new EnchantmentWrapper("planter", "Planter", 1, hoes);
		
		FERTILIZER = new EnchantmentWrapper("fertilizer", "Fertilizer", 1, hoes);
		
		Set<Material> axes = new HashSet<>();
		axes.add(Material.NETHERITE_AXE);
		axes.add(Material.DIAMOND_AXE);
		axes.add(Material.GOLDEN_AXE);
		axes.add(Material.IRON_AXE);
		axes.add(Material.STONE_AXE);
		axes.add(Material.WOODEN_AXE);
		TREEFELLER = new EnchantmentWrapper("treefeller", "Tree Feller", 1, axes);
		
		AUTOPLANKER = new EnchantmentWrapper("autoplank", "Auto Planker", 1, axes);
		
		Set<Material> weapons = new HashSet<>();
		weapons.add(Material.NETHERITE_AXE);
		weapons.add(Material.DIAMOND_AXE);
		weapons.add(Material.GOLDEN_AXE);
		weapons.add(Material.IRON_AXE);
		weapons.add(Material.STONE_AXE);
		weapons.add(Material.WOODEN_AXE);
		weapons.add(Material.NETHERITE_SWORD);
		weapons.add(Material.DIAMOND_SWORD);
		weapons.add(Material.GOLDEN_SWORD);
		weapons.add(Material.IRON_SWORD);
		weapons.add(Material.STONE_SWORD);
		weapons.add(Material.WOODEN_SWORD);
		weapons.add(Material.BOW);
		weapons.add(Material.CROSSBOW);
		TOXICITY = new EnchantmentWrapper("toxicity", "Toxicity", 1, weapons);
		
		CORRUPTION = new EnchantmentWrapper("corruption", "Corruption", 1, weapons);
		
		CHAINS = new EnchantmentWrapper("chains", "Chains", 1, weapons);
		
		CRIPPLING = new EnchantmentWrapper("crippling", "Crippling", 1, weapons);
		
		BLINDING = new EnchantmentWrapper("blinding", "Blinding", 1, weapons);
		
		Set<Material> sharpy = new HashSet<>();
		sharpy.add(Material.NETHERITE_AXE);
		sharpy.add(Material.DIAMOND_AXE);
		sharpy.add(Material.GOLDEN_AXE);
		sharpy.add(Material.IRON_AXE);
		sharpy.add(Material.STONE_AXE);
		sharpy.add(Material.WOODEN_AXE);
		sharpy.add(Material.NETHERITE_SWORD);
		sharpy.add(Material.DIAMOND_SWORD);
		sharpy.add(Material.GOLDEN_SWORD);
		sharpy.add(Material.IRON_SWORD);
		sharpy.add(Material.STONE_SWORD);
		sharpy.add(Material.WOODEN_SWORD);
		
		HEAVY = new EnchantmentWrapper("heavy", "Heavy", 1, sharpy);
		
		STRIPPER = new EnchantmentWrapper("stripper", "Stripper", 1, sharpy);
		
		LIFESTEAL = new EnchantmentWrapper("lifesteal", "Lifesteal", 1, sharpy);
		
		DECAPITATION = new EnchantmentWrapper("decapitation", "Decapitation", 1, sharpy);
		
		Set<Material> bows = new HashSet<>();
		bows.add(Material.BOW);
		bows.add(Material.CROSSBOW);
		
		EXPLOSIVE = new EnchantmentWrapper("explosive", "Explosive", 1, bows);
		
		BLACKHOLE = new EnchantmentWrapper("blackhole", "Black Hole", 1, bows);
		
		REPULSION = new EnchantmentWrapper("repulsion", "Repulsion", 1, bows);
		
		EGG = new EnchantmentWrapper("egg", "E g g", 1, bows);
		
		FIREBALL = new EnchantmentWrapper("fireball", "Fireball", 1, bows);
		
		FIREWORK = new EnchantmentWrapper("partypopper", "Party Popper", 1, bows);
		
		SHOTGUN = new EnchantmentWrapper("shotgun", "Shotgun", 1, bows);
		
		//XD imagine
		Set<Material> boots = new HashSet<>();
		boots.add(Material.LEATHER_BOOTS);
		boots.add(Material.IRON_BOOTS);
		boots.add(Material.CHAINMAIL_BOOTS);
		boots.add(Material.GOLDEN_BOOTS);
		boots.add(Material.DIAMOND_BOOTS);
		boots.add(Material.NETHERITE_BOOTS);
		
		LAVAWALKER = new EnchantmentWrapper("lavawalker", "Lava Walker", 1, boots);
		
		PHDFLOPPER = new EnchantmentWrapper("phdflopper", "PhD Flopper", 1, boots);
		
		Set<Material> chests = new HashSet<>();
		chests.add(Material.LEATHER_CHESTPLATE);
		chests.add(Material.IRON_CHESTPLATE);
		chests.add(Material.CHAINMAIL_CHESTPLATE);
		chests.add(Material.GOLDEN_CHESTPLATE);
		chests.add(Material.DIAMOND_CHESTPLATE);
		chests.add(Material.NETHERITE_CHESTPLATE);

		MOLTEN = new EnchantmentWrapper("molten", "Molten", 1, chests);
		
		enchants.add(AUTOPLANKER);
		enchants.add(AUTOSMELTER);
		enchants.add(TREEFELLER);
		enchants.add(PLANTER);
		enchants.add(PLOW);
		enchants.add(SCYTH);
		enchants.add(FERTILIZER);
		enchants.add(HAMMER);
		enchants.add(TELEPATHY);
		enchants.add(TOXICITY);
		enchants.add(CORRUPTION);
		enchants.add(CHAINS);
		enchants.add(CRIPPLING);
		enchants.add(BLINDING);
		enchants.add(EXPLOSIVE);
		enchants.add(BLACKHOLE);
		enchants.add(REPULSION);
		enchants.add(EGG);
		enchants.add(FIREBALL);
		enchants.add(FIREWORK);
		enchants.add(LAVAWALKER);
		enchants.add(PHDFLOPPER);
		enchants.add(MOLTEN);
		enchants.add(HEAVY);
		enchants.add(STRIPPER);
		enchants.add(LIFESTEAL);
		enchants.add(DECAPITATION);
		enchants.add(SHOTGUN);
		
		register();
	}
	
	public void register() {
		boolean registered;
		for(Enchantment e : enchants) {
			registered = Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(e);
			if(!registered) {
				registerEnchantment(e);
			}
		}
	}
	
	public static void registerEnchantment(Enchantment enchantment) {
		boolean registered = true;
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
			Enchantment.registerEnchantment(enchantment);
		} catch(Exception e) {
			registered = false;
			e.printStackTrace();
		}
		if(registered) {
			// send message to console for register of enchant
		}
	}

}
