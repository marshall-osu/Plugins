package me.Tanner.WackyWarping.Commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import me.Tanner.WackyWarping.Main;
import me.Tanner.WackyWarping.Warp;
import net.md_5.bungee.api.ChatColor;

public class WackyWarps implements CommandExecutor {

	private Main plugin;

	public WackyWarps(Main plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (label.equals("wwarp")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 0) {
					openGUI(player);
					return true;
				}
				String option = args[0];
				if (option.equals("delete")) {
					if (args.length == 2) {
						if (ownsWarp(player, args[1])) {
							if (this.plugin.warps.containsKey(args[1])) {
								player.sendMessage(ChatColor.GREEN + "Are you sure you would like to delete warp " + args[1] + ". Respond with y to confirm");
								this.plugin.confirmationsDelete.put(player.getName(), this.plugin.warps.get(args[1]));
								return true;
							}
							player.sendMessage(ChatColor.RED + "ERROR: warp doesn't exist");
							return true;
						}
						player.sendMessage(ChatColor.RED + "ERROR: you don't own this warp");
						return true;
					}
					player.sendMessage(ChatColor.RED + "ERROR: use the format, /wwarp create [name]");
					return true;
				} else if (option.equals("ban")) {
					if (args.length == 3) {
						if (ownsWarp(player, args[1])) {
							if (this.plugin.warps.containsKey(args[1])) {
								OfflinePlayer p = this.plugin.getServer().getOfflinePlayer(args[2]);
								if(this.plugin.warps.get(args[1]).ban(p.getUniqueId().toString())) {
									player.sendMessage(ChatColor.GREEN + "" + args[2] + " has been banned from " + args[1]);
								} else {
									player.sendMessage(ChatColor.GREEN + "" + args[2] + " has been unbanned from " + args[1]);
								}
								return true;
							}
							player.sendMessage(ChatColor.RED + "ERROR: warp doesn't exist");
							return true;
						}
						player.sendMessage(ChatColor.RED + "ERROR: you don't own this warp");
						return true;
					}
				} else if (option.equals("create")) {
					if (args.length == 2) {
						if (!this.plugin.warps.containsKey(args[1])) {
							if (!this.plugin.ownerLookup.containsKey(player.getUniqueId()) || (this.plugin.ownerLookup
									.get(player.getUniqueId()).size() < getPermission(player))) {
								player.sendMessage(ChatColor.GREEN + "Warp " + args[1] + " will cost you "
										+ this.plugin.cost + "$. Respond with y to confirm this warp creation");
								this.plugin.confirmations.put(player.getName(),
										createWarp(args[1], player.getLocation(), player));
								return true;
							}
							player.sendMessage(ChatColor.RED + "ERROR: you already have your max amount of warps");
							player.sendMessage(ChatColor.RED + "" + this.plugin.ownerLookup.get(player.getUniqueId()).toString());
							return true;
						}
						player.sendMessage(ChatColor.RED + "ERROR: warp name already taken");
						return true;
					}
					player.sendMessage(ChatColor.RED + "ERROR: use the format, /wwarp create [name]");
					return true;
				} else if (option.equals("set")) {
					if (args.length >= 3) {
						if (this.plugin.warps.containsKey(args[2])) {
							if (ownsWarp(player, args[2])) {
								if (args[1].equals("icon")) {
									if (args.length == 3) {
										if (player.getInventory().getItemInMainHand().getType() != Material.AIR) {
											set(args[2], player.getInventory().getItemInMainHand());
											player.sendMessage(ChatColor.GREEN + "Icon has been set for " + args[2]);
											return true;
										}
										player.sendMessage(ChatColor.RED
												+ "ERROR: you must have an item in your hand to set the icon!");
										return true;
									}
									player.sendMessage(ChatColor.RED + "ERROR: use the format, /wwarp set icon [name]");
									return true;
								} else if (args[1].equals("description")) {
									if (args.length > 3) {
										StringBuilder descript = new StringBuilder();
										for (int i = 3; i < args.length - 1; i++) {
											descript.append(args[i] + " ");
										}
										descript.append(args[args.length - 1]);
										set(args[2], descript.toString(), true);
										player.sendMessage(ChatColor.GREEN + "Description has been set for " + args[2]);
										return true;
									}
									player.sendMessage(ChatColor.RED
											+ "ERROR: use the format, /wwarp set description [name] [description]");
									return true;
								} else if (args[1].equals("name")) {
									if (args.length == 4) {
										if (!this.plugin.warps.containsKey(args[3])) {
											set(args[2], args[3]);
											player.sendMessage(ChatColor.GREEN + "Name has been set for " + args[2]);
											return true;
										}
										player.sendMessage(ChatColor.RED + "ERROR: that warp name already exists");
										return true;
									}
									player.sendMessage(
											ChatColor.RED + "ERROR: use the format, /wwarp set name [name] [new name]");
									return true;
								} else if (args[1].equals("location")) {
									if (args.length == 3) {
										set(args[2], player.getLocation());
										player.sendMessage(ChatColor.GREEN + "Location has been set for " + args[2]);
										return true;
									}
									player.sendMessage(
											ChatColor.RED + "ERROR: use the format, /wwarp set location [name]");
									return true;
								} else if (args[1].equals("owner")) {
									if (args.length == 4) {
										Player newOwner = (Player) this.plugin.getServer().getOfflinePlayer(args[3]);
										if (newOwner != null) {
											if (!this.plugin.ownerLookup.containsKey(newOwner.getUniqueId())
													|| (this.plugin.ownerLookup.get(newOwner.getUniqueId())
															.size() < getPermission(newOwner))) {
												this.plugin.confirmationsOwner.put(player.getName(),
														this.plugin.warps.get(args[2]));
												this.plugin.confirmationsOwnerName.put(player.getName(), newOwner);
												player.sendMessage(ChatColor.GREEN + "Warp " + args[1]
														+ " will be transfered to " + args[3]
														+ ". Respond with y to confirm this new owner");
												return true;
											}
											player.sendMessage(ChatColor.RED
													+ "ERROR: this player already has the max amount of warps they are alowed");
											return true;
										}
										player.sendMessage(ChatColor.RED + "ERROR: that player does not exist");
										return true;
									}
									player.sendMessage(ChatColor.RED
											+ "ERROR: use the format, /wwarp set owner [name] [new owner]");
									return true;
								}
								player.sendMessage(ChatColor.RED
										+ "ERROR: use the format, /wwarp set [icon:description:name:location:owner] [name] ...");
								return true;
							}
							player.sendMessage(ChatColor.RED + "ERROR: you do not own this warp!");
							return true;
						}
						player.sendMessage(ChatColor.RED + "ERROR: warp doesn't exist, use \"/wwarp create " + args[2]
								+ "\" to create this warp");
						return true;
					}
					player.sendMessage(ChatColor.RED + "ERROR: use the format, /wwarp create [name]");
					return true;
				} else if (this.plugin.warps.containsKey(option)) {
					Warp warp = this.plugin.warps.get(option);
					OfflinePlayer owner = this.plugin.getServer().getOfflinePlayer(warp.getOwnerId());
					if(!warp.getBans().contains(player.getUniqueId().toString())){
						if (owner.isOnline()) {
							((Player)owner).sendMessage(ChatColor.GREEN + "" + player.getName() + " has teleported to your warp, "
									+ warp.getName());
						}
						player.teleport(warp.getLocation());
					} else {
						player.sendMessage(ChatColor.RED + "You've been banned from this warp");
					}
					return true;
				}
				player.sendMessage(ChatColor.RED + "ERROR: use the format, /wwarp [create:set:warp name] ...");
				return true;
			}
			sender.sendMessage(ChatColor.RED + "ERROR: only players can call this command!");
			return true;
		}
		return false;

	}

	private void openGUI(Player player) {
		player.openInventory(this.plugin.gui.get(0));
	}

	private Warp createWarp(String name, Location loc, Player owner) {
		Warp warp = new Warp(name, loc, owner.getUniqueId(), owner.getName());
		return warp;
	}

	private void set(String name, ItemStack icon) {
		Warp warp = this.plugin.warps.get(name);
		ItemStack old = warp.getIcon();
		warp.setIcon(icon);
		updateGUI(old, warp.getIcon());
	}

	private void set(String name, Location location) {
		Warp warp = this.plugin.warps.get(name);
		ItemStack old = warp.getIcon();
		warp.setLocation(location);
		updateGUI(old, warp.getIcon());
	}

	private void set(String name, String newname) {
		Warp warp = this.plugin.warps.remove(name);
		ItemStack old = warp.getIcon();
		warp.setName(newname);
		this.plugin.warps.put(newname, warp);
		updateGUI(old, warp.getIcon());
	}

	private void set(String name, String description, boolean identifier) {
		Warp warp = this.plugin.warps.get(name);
		ItemStack old = warp.getIcon();
		warp.setDescription(description);
		updateGUI(old, warp.getIcon());
	}

	private boolean ownsWarp(Player player, String name) {
		if (player.isOp()) {
			return true;
		}
		if (this.plugin.ownerLookup.containsKey(player.getUniqueId())) {
			for (Warp warp : this.plugin.ownerLookup.get(player.getUniqueId())) {
				if (warp.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	private int getPermission(Player player) {
		for (String perm : this.plugin.permissions.keySet()) {
			if (player.hasPermission("wackywarps." + perm)) {
				return this.plugin.permissions.get(perm);
			}
		}
		return this.plugin.defaultNum;
	}

	public void updateGUI(ItemStack oldIcon, ItemStack newIcon) {
		for (Inventory inv : this.plugin.gui) {
			int location = inv.first(oldIcon);
			if (location != -1) {
				inv.setItem(location, newIcon);
				return;
			}
		}
	}

}
