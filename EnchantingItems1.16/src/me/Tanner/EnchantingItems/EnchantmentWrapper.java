package me.Tanner.EnchantingItems;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.inventory.ItemStack;

public class EnchantmentWrapper extends Enchantment{

	private final String name;
	private final int maxLvl;
	private final Set<Material> items;
	
	public EnchantmentWrapper(String namespace, String name, int lvl, Set<Material> set) {
		super(NamespacedKey.minecraft(namespace));
		this.name = name;
		this.maxLvl = lvl;
		this.items = set;
	}

	@Override
	public boolean canEnchantItem(ItemStack tool) {
		return this.items.contains(tool.getType()) || this.items.isEmpty();
	}

	@Override
	public int getMaxLevel() {
		// TODO Auto-generated method stub
		return this.maxLvl;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public int getStartLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isCursed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTreasure() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean conflictsWith(Enchantment arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
