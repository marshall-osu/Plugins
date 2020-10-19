package me.Tanner.EnchantingItems.Events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Tanner.EnchantingItems.Enchantments;

public class AnvilEvent implements Listener {

	Enchantments plugin;

	public AnvilEvent(Enchantments plugin) {
		this.plugin = plugin;
		this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void anvilClick(InventoryClickEvent anvilEvent) {
        ItemStack item1 = anvilEvent.getInventory().getItem(0);
        ItemStack item2 = anvilEvent.getInventory().getItem(1);
        
        if(anvilEvent.getInventory().getType().equals(InventoryType.ANVIL) && item1 == null && anvilEvent.getCurrentItem().getType() == Material.ENCHANTED_BOOK && hasCustomEnchant(anvilEvent.getCurrentItem())) {
        		anvilEvent.setCancelled(true);
        		anvilEvent.getWhoClicked().sendMessage(ChatColor.RED + "You cannot rename or add enchantments to custom enchantment books");
        	return;
        }
        if(item1 == null || item2 == null)
            return;
        if (anvilEvent.getInventory().getType().equals(InventoryType.ANVIL)
                && item2.getType().equals(Material.ENCHANTED_BOOK) && hasCustomEnchant(item2)){
        	
        	if(anvilEvent.getInventory().getItem(2) != null) {
	            if (anvilEvent.getSlotType().equals(InventoryType.SlotType.RESULT)
	                    && (anvilEvent.getClick().equals(ClickType.LEFT)
	                    || anvilEvent.getClick().equals(ClickType.SHIFT_LEFT))) {
	                anvilEvent.getWhoClicked().setItemOnCursor(anvilEvent.getInventory().getItem(2));
	                anvilEvent.getInventory().clear();
	            }
        	}
        }
    }

	@SuppressWarnings("deprecation")
	@EventHandler
	public void anvilGUI(PrepareAnvilEvent anvilEvent) {
		ItemStack item1 = anvilEvent.getInventory().getItem(0);
		ItemStack item2 = anvilEvent.getInventory().getItem(1);
		if (item1 == null || item2 == null) {
			return;
		} else if (anvilEvent.getInventory().getType().equals(InventoryType.ANVIL)
				&& item2.getType() == Material.ENCHANTED_BOOK) {
			if (hasCustomEnchant(item2)) {
				Enchantment enchantment = null;
				for (Enchantment enchant : item2.getEnchantments().keySet()) {
					enchantment = enchant;
				}

				if (enchantment.canEnchantItem(item1) && !containsEnchant(item1, enchantment.getName())) {
					ItemStack enchanted = item1.clone();
					enchanted.addUnsafeEnchantment(enchantment, 1);
					ItemMeta meta = enchanted.getItemMeta();
					List<String> lore = new ArrayList<String>();
					lore.add(ChatColor.GRAY + "" + enchantment.getName());
					if (meta.hasLore()) {
						for (String l : meta.getLore()) {
							lore.add(l);
						}
					}
					meta.setLore(lore);
					enchanted.setItemMeta(meta);
					anvilEvent.getInventory().setItem(2, enchanted);
					anvilEvent.setResult(enchanted);
				}
			}
		}
	}

	private boolean hasCustomEnchant(ItemStack book) {
		for (Enchantment e : this.plugin.enchants.enchants) {
			if (book.containsEnchantment(e)) {
				return true;
			}
		}
		return false;
	}

	private boolean containsEnchant(ItemStack item, String enchantment) {
		if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
			if (item.getItemMeta().getLore().contains(ChatColor.GRAY + "" + enchantment)) {
				return true;
			}
			if (this.plugin.conflicts.containsKey(enchantment)) {
				for (String enchant : this.plugin.conflicts.get(enchantment)) {
					if (item.getItemMeta().getLore().contains(enchant)) {
						return true;
					}
				}
			}
			if (item.getItemMeta().getLore().contains(ChatColor.DARK_RED + "Unenchantable")) {
				return true;
			}
		}
		return false;
	}
}