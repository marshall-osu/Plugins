package me.Tanner.SofishticatedFishing.GUI;

import me.Tanner.SofishticatedFishing.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class RecipesCommand implements CommandExecutor, Listener {
	Main plugin;

	public Inventory menu;

	public Inventory t;

	public Inventory a;

	public Inventory b;

	public Inventory r;

	public Inventory m;

	public RecipesCommand(Main plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, (Plugin) plugin);
		createInventories();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (label.equals("sffish")) {
			if (args.length == 1) {
				if (sender instanceof Player) {
					if (args[0].equalsIgnoreCase("recipes")) {
						Player player = (Player) sender;
						player.openInventory(this.menu);
						return true;
					}
					sender.sendMessage("Error: use /sffish recipes");
					return true;
				}
				sender.sendMessage("Console cannot call this command!");
				return true;
			}
			sender.sendMessage("Error: use /sffish recipes");
			return true;
		}
		return false;
	}

	public void createInventories() {
		this.menu = Bukkit.createInventory(null, 45, ChatColor.AQUA + "SofishticatedFishing Menu");
		for (int i = 0; i < 45; i++) {
			if (i == 10) {
				ItemStack roditem = new ItemStack(Material.FISHING_ROD);
				ItemMeta meta = roditem.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_AQUA + "Rods");
				roditem.setItemMeta(meta);
				this.menu.setItem(i, roditem);
			} else if (i == 12) {
				ItemStack bossspawn = new ItemStack(Material.CREEPER_SPAWN_EGG);
				ItemMeta meta = bossspawn.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "Boss Spawners");
				bossspawn.setItemMeta(meta);
				this.menu.setItem(i, bossspawn);
			} else if (i == 14) {
				ItemStack armoritem = new ItemStack(Material.GOLDEN_HELMET);
				ItemMeta meta = armoritem.getItemMeta();
				meta.setDisplayName(ChatColor.YELLOW + "Armor");
				armoritem.setItemMeta(meta);
				this.menu.setItem(i, armoritem);
			} else if (i == 16) {
				ItemStack toolitem = new ItemStack(Material.WOODEN_SWORD);
				ItemMeta meta = toolitem.getItemMeta();
				meta.setDisplayName(ChatColor.RED + "Tools");
				toolitem.setItemMeta(meta);
				this.menu.setItem(i, toolitem);
			} else if (i == 31) {
				ItemStack materialitem = new ItemStack(Material.BRICK);
				ItemMeta meta = materialitem.getItemMeta();
				meta.setDisplayName(ChatColor.GRAY + "Materials");
				materialitem.setItemMeta(meta);
				this.menu.setItem(i, materialitem);
			} else {
				this.menu.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
			}
		}
		int space = (this.plugin.recipes.rods.size() / 7 + 1) * 9 + 18;
		this.r = Bukkit.createInventory(null, space, ChatColor.DARK_AQUA + "Rods");
		for (int k = 0; k < space; k++) {
			if (k < 9 || k > space - 9 || k % 9 == 0 || k % 9 == 8)
				if (k > space - 9 && k % 9 == 4) {
					ItemStack exit = new ItemStack(Material.BARRIER);
					ItemMeta meta = exit.getItemMeta();
					meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Exit");
					exit.setItemMeta(meta);
					this.r.setItem(k, exit);
				} else {
					this.r.setItem(k, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
				}
		}
		int j = 10;
		for (ItemStack item : this.plugin.recipes.rods.keySet()) {
			this.r.setItem(j, item);
			j++;
			if (j % 9 == 8)
				j += 2;
		}
		space = (this.plugin.recipes.bosses.size() / 7 + 1) * 9 + 18;
		this.b = Bukkit.createInventory(null, space, ChatColor.GREEN + "Boss Spawns");
		int m;
		for (m = 0; m < space; m++) {
			if (m < 9 || m > space - 9 || m % 9 == 0 || m % 9 == 8)
				if (m > space - 9 && m % 9 == 4) {
					ItemStack exit = new ItemStack(Material.BARRIER);
					ItemMeta meta = exit.getItemMeta();
					meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Exit");
					exit.setItemMeta(meta);
					this.b.setItem(m, exit);
				} else {
					this.b.setItem(m, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
				}
		}
		j = 10;
		for (ItemStack item : this.plugin.recipes.bosses.keySet()) {
			this.b.setItem(j, item);
			j++;
			if (j % 9 == 8)
				j += 2;
		}
		space = (this.plugin.recipes.armor.size() / 7 + 1) * 9 + 18;
		this.a = Bukkit.createInventory(null, space, ChatColor.YELLOW + "Armor");
		for (m = 0; m < space; m++) {
			if (m < 9 || m > space - 9 || m % 9 == 0 || m % 9 == 8)
				if (m > space - 9 && m % 9 == 4) {
					ItemStack exit = new ItemStack(Material.BARRIER);
					ItemMeta meta = exit.getItemMeta();
					meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Exit");
					exit.setItemMeta(meta);
					this.a.setItem(m, exit);
				} else {
					this.a.setItem(m, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
				}
		}
		j = 10;
		for (ItemStack item : this.plugin.recipes.armor.keySet()) {
			this.a.setItem(j, item);
			j++;
			if (j % 9 == 8)
				j += 2;
		}
		space = (this.plugin.recipes.tools.size() / 7 + 1) * 9 + 18;
		this.t = Bukkit.createInventory(null, space, ChatColor.RED + "Tools");
		for (m = 0; m < space; m++) {
			if (m < 9 || m > space - 9 || m % 9 == 0 || m % 9 == 8)
				if (m > space - 9 && m % 9 == 4) {
					ItemStack exit = new ItemStack(Material.BARRIER);
					ItemMeta meta = exit.getItemMeta();
					meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Exit");
					exit.setItemMeta(meta);
					this.t.setItem(m, exit);
				} else {
					this.t.setItem(m, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
				}
		}
		j = 10;
		for (ItemStack item : this.plugin.recipes.tools.keySet()) {
			this.t.setItem(j, item);
			j++;
			if (j % 9 == 8)
				j += 2;
		}
		space = (this.plugin.recipes.materials.size() / 7 + 1) * 9 + 18;
		this.m = Bukkit.createInventory(null, space, ChatColor.GRAY + "Materials");
		for (m = 0; m < space; m++) {
			if (m < 9 || m > space - 9 || m % 9 == 0 || m % 9 == 8)
				if (m > space - 9 && m % 9 == 4) {
					ItemStack exit = new ItemStack(Material.BARRIER);
					ItemMeta meta = exit.getItemMeta();
					meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Exit");
					exit.setItemMeta(meta);
					this.m.setItem(m, exit);
				} else {
					this.m.setItem(m, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
				}
		}
		j = 10;
		for (ItemStack item : this.plugin.recipes.materials.keySet()) {
			this.m.setItem(j, item);
			j++;
			if (j % 9 == 8)
				j += 2;
		}
	}

	@EventHandler
	public void clickEvent(InventoryClickEvent event) {
		if (event.getWhoClicked() instanceof Player && event.getSlotType() != InventoryType.SlotType.OUTSIDE) {
			Player player = (Player) event.getWhoClicked();
			if (event.getClickedInventory().equals(this.menu)) {
				event.setCancelled(true);
				if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
						&& event.getCurrentItem().getItemMeta().hasDisplayName()) {
					String name = event.getCurrentItem().getItemMeta().getDisplayName();
					if (name.equals(ChatColor.DARK_AQUA + "Rods")) {
						player.closeInventory();
						player.openInventory(this.r);
					} else if (name.equals(ChatColor.GREEN + "Boss Spawners")) {
						player.closeInventory();
						player.openInventory(this.b);
					} else if (name.equals(ChatColor.YELLOW + "Armor")) {
						player.closeInventory();
						player.openInventory(this.a);
					} else if (name.equals(ChatColor.RED + "Tools")) {
						player.closeInventory();
						player.openInventory(this.t);
					} else if (name.equals(ChatColor.GRAY + "Materials")) {
						player.closeInventory();
						player.openInventory(this.m);
					}
				}
			} else if (event.getView().getTitle().equals(ChatColor.AQUA + "Recipe")) {
				event.setCancelled(true);
				if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.BARRIER)) {
					player.closeInventory();
					ItemStack border = event.getClickedInventory().getItem(0);
					if (border.getType().equals(Material.CYAN_STAINED_GLASS_PANE)) {
						player.openInventory(this.r);
					} else if (border.getType().equals(Material.GREEN_STAINED_GLASS_PANE)) {
						player.openInventory(this.b);
					} else if (border.getType().equals(Material.YELLOW_STAINED_GLASS_PANE)) {
						player.openInventory(this.a);
					} else if (border.getType().equals(Material.RED_STAINED_GLASS_PANE)) {
						player.openInventory(this.t);
					} else if (border.getType().equals(Material.BLACK_STAINED_GLASS_PANE)) {
						player.openInventory(this.m);
					}
				}
			} else if (event.getView().getTitle().equals(ChatColor.DARK_AQUA + "Rods")) {
				event.setCancelled(true);
				if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.BARRIER)) {
					player.closeInventory();
					player.openInventory(this.menu);
				} else if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
						&& event.getCurrentItem().getItemMeta().hasLore()) {
					player.closeInventory();
					player.openInventory((Inventory) this.plugin.recipes.rods.get(event.getCurrentItem()));
				}
			} else if (event.getView().getTitle().equals(ChatColor.GREEN + "Boss Spawns")) {
				event.setCancelled(true);
				if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.BARRIER)) {
					player.closeInventory();
					player.openInventory(this.menu);
				} else if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
						&& event.getCurrentItem().getItemMeta().hasLore()) {
					player.closeInventory();
					player.openInventory((Inventory) this.plugin.recipes.bosses.get(event.getCurrentItem()));
				}
			} else if (event.getView().getTitle().equals(ChatColor.YELLOW + "Armor")) {
				event.setCancelled(true);
				if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.BARRIER)) {
					player.closeInventory();
					player.openInventory(this.menu);
				} else if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
						&& event.getCurrentItem().getItemMeta().hasLore()) {
					player.closeInventory();
					player.openInventory((Inventory) this.plugin.recipes.armor.get(event.getCurrentItem()));
				}
			} else if (event.getView().getTitle().equals(ChatColor.RED + "Tools")) {
				event.setCancelled(true);
				if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.BARRIER)) {
					player.closeInventory();
					player.openInventory(this.menu);
				} else if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
						&& event.getCurrentItem().getItemMeta().hasLore()) {
					player.closeInventory();
					player.openInventory((Inventory) this.plugin.recipes.tools.get(event.getCurrentItem()));
				}
			} else if (event.getView().getTitle().equals(ChatColor.GRAY + "Materials")) {
				event.setCancelled(true);
				if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.BARRIER)) {
					player.closeInventory();
					player.openInventory(this.menu);
				} else if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta()
						&& event.getCurrentItem().getItemMeta().hasLore()) {
					player.closeInventory();
					player.openInventory((Inventory) this.plugin.recipes.materials.get(event.getCurrentItem()));
				}
			}
		}
	}

	@EventHandler
	public void dragEvent(InventoryDragEvent event) {
		Inventory dragged = event.getInventory();
		if (event.getView().getTitle().equals(ChatColor.AQUA + "Recipe") || dragged.equals(this.menu)
				|| dragged.equals(this.r) || dragged.equals(this.b) || dragged.equals(this.a) || dragged.equals(this.t)
				|| dragged.equals(this.m))
			event.setCancelled(true);
	}
}
