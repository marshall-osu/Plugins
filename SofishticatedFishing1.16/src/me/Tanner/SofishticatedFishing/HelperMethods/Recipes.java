  package me.Tanner.SofishticatedFishing.HelperMethods;
  
  import java.util.HashSet;
  import java.util.LinkedHashMap;
  import java.util.Map;
  import java.util.Set;
  import me.Tanner.SofishticatedFishing.Main;
  import net.md_5.bungee.api.ChatColor;
  import org.bukkit.Bukkit;
  import org.bukkit.Material;
  import org.bukkit.NamespacedKey;
  import org.bukkit.inventory.Inventory;
  import org.bukkit.inventory.ItemStack;
  import org.bukkit.inventory.RecipeChoice;
  import org.bukkit.inventory.ShapedRecipe;
  import org.bukkit.inventory.meta.ItemMeta;
  import org.bukkit.plugin.Plugin;
  
  @SuppressWarnings("deprecation")
  public class Recipes {
    DropItemMethods items;
    
    Main plugin;
    
    public final Map<ItemStack, Inventory> rods = new LinkedHashMap<>();
    
    public final Map<ItemStack, Inventory> bosses = new LinkedHashMap<>();
    
    public final Map<ItemStack, Inventory> armor = new LinkedHashMap<>();
    
    public final Map<ItemStack, Inventory> tools = new LinkedHashMap<>();
    
    public final Map<ItemStack, Inventory> materials = new LinkedHashMap<>();
    
    public final Set<ShapedRecipe> recipes = new HashSet<>();
    
    public Recipes(Main plugin) {
      this.plugin = plugin;
      this.items = this.plugin.items;
      
      ShapedRecipe temp = BasicFishingRod();
      this.recipes.add(temp);
      this.rods.put(temp.getResult(), newInventory(temp, "rod"));
      temp = SecondFishingRod();
      this.recipes.add(temp);
      this.rods.put(temp.getResult(), newInventory(temp, "rod"));
      temp = ThirdFishingRod();
      this.recipes.add(temp);
      this.rods.put(temp.getResult(), newInventory(temp, "rod"));
      temp = FourthFishingRod();
      this.recipes.add(temp);
      this.rods.put(temp.getResult(), newInventory(temp, "rod"));
      temp = FifthFishingRod();
      this.recipes.add(temp);
      this.rods.put(temp.getResult(), newInventory(temp, "rod"));
      temp = SixthFishingRod();
      this.recipes.add(temp);
      this.rods.put(temp.getResult(), newInventory(temp, "rod"));
      temp = SalmonRod();
      this.recipes.add(temp);
      this.rods.put(temp.getResult(), newInventory(temp, "rod"));
      temp = SeventhFishingRod();
      this.recipes.add(temp);
      this.rods.put(temp.getResult(), newInventory(temp, "rod"));
      
      
      
      temp = BossEgg();
      this.recipes.add(temp);
      this.bosses.put(temp.getResult(), newInventory(temp, "boss"));
      temp = SecondBossEgg();
      this.recipes.add(temp);
      this.bosses.put(temp.getResult(), newInventory(temp, "boss"));
      temp = BearDecoy();
      this.recipes.add(temp);
      this.bosses.put(temp.getResult(), newInventory(temp, "boss"));
      
      
      
      temp = AncientHelmRec();
      this.recipes.add(temp);
      this.armor.put(temp.getResult(), newInventory(temp, "armor"));
      temp = AncientChestRec();
      this.recipes.add(temp);
      this.armor.put(temp.getResult(), newInventory(temp, "armor"));
      temp = AncientLeggingsRec();
      this.recipes.add(temp);
      this.armor.put(temp.getResult(), newInventory(temp, "armor"));
      temp = AncientBootsRec();
      this.recipes.add(temp);
      this.armor.put(temp.getResult(), newInventory(temp, "armor"));
      temp = SeventhSonsBoots();
      this.recipes.add(temp);
      this.armor.put(temp.getResult(), newInventory(temp, "armor"));
      temp = SeventhSonsLeggings();
      this.recipes.add(temp);
      this.armor.put(temp.getResult(), newInventory(temp, "armor"));
      temp = SeventhSonsCloak();
      this.recipes.add(temp);
      this.armor.put(temp.getResult(), newInventory(temp, "armor"));
      temp = SeventhSonsHelm();
      this.recipes.add(temp);
      this.armor.put(temp.getResult(), newInventory(temp, "armor"));
      
      
      
      temp = WaterLoggedPlank();
      this.recipes.add(temp);
      this.tools.put(temp.getResult(), newInventory(temp, "tool"));
      temp = SalmonPickaxe();
      this.recipes.add(temp);
      this.tools.put(temp.getResult(), newInventory(temp, "tool"));
      temp = SalmonAxe();
      this.recipes.add(temp);
      this.tools.put(temp.getResult(), newInventory(temp, "tool"));
      temp = SalmonShovel();
      this.recipes.add(temp);
      this.tools.put(temp.getResult(), newInventory(temp, "tool"));
      temp = LumberBow();
      this.recipes.add(temp);
      this.tools.put(temp.getResult(), newInventory(temp, "tool"));
      temp = SeventhSonsSword();
      this.recipes.add(temp);
      this.tools.put(temp.getResult(), newInventory(temp, "tool"));
      
      temp = ReinforcedRod();
      this.recipes.add(temp);
      this.materials.put(temp.getResult(), newInventory(temp, "material"));
      temp = Bait();
      this.recipes.add(temp);
      this.materials.put(temp.getResult(), newInventory(temp, "material"));
      temp = Stuffing();
      this.recipes.add(temp);
      this.materials.put(temp.getResult(), newInventory(temp, "material"));
      temp = SalmonBar();
      this.recipes.add(temp);
      this.materials.put(temp.getResult(), newInventory(temp, "material"));
    }
    
    public ShapedRecipe BasicFishingRod() {
      ItemStack item = this.items.basicFishingRod();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "basic_rod");
      ShapedRecipe Rod = new ShapedRecipe(key, item);
      Rod.shape(new String[] { "  F", " FS", "F S" });
      Rod.setIngredient('F', Material.COOKED_COD);
      Rod.setIngredient('S', Material.STRING);
      return Rod;
    }
    
    public ShapedRecipe SecondFishingRod() {
      ItemStack item = this.items.secondFishingRod();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "second_rod");
      ShapedRecipe Rod = new ShapedRecipe(key, item);
      Rod.shape(new String[] { "  F", " FS", "F P" });
      Rod.setIngredient('F', Material.TROPICAL_FISH);
      Rod.setIngredient('S', Material.STRING);
      Rod.setIngredient('P', Material.PUFFERFISH);
      return Rod;
    }
    
    public ShapedRecipe ThirdFishingRod() {
      ItemStack item = this.items.thirdFishingRod();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "squid_rod");
      ShapedRecipe Rod = new ShapedRecipe(key, item);
      Rod.shape(new String[] { "  T", " TS", "T P" });
      Rod.setIngredient('T', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.tentacle()));
      Rod.setIngredient('S', Material.STRING);
      Rod.setIngredient('P', Material.PUFFERFISH);
      return Rod;
    }
    
    public ShapedRecipe FourthFishingRod() {
      ItemStack item = this.items.fourthFishingRod();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "deep_water_rod");
      ShapedRecipe Rod = new ShapedRecipe(key, item);
      Rod.shape(new String[] { "  C", " CS", "C P" });
      Rod.setIngredient('C', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.deepWaterCod()));
      Rod.setIngredient('S', Material.STRING);
      Rod.setIngredient('P', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.waterPearl()));
      return Rod;
    }
    
    public ShapedRecipe FifthFishingRod() {
      ItemStack item = this.items.fifthFishingRod();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "alien_rod");
      ShapedRecipe Rod = new ShapedRecipe(key, item);
      Rod.shape(new String[] { "  C", " CS", "C P" });
      ItemStack ufo = this.items.UFOShard();
      Rod.setIngredient('C', (RecipeChoice)new RecipeChoice.ExactChoice(ufo));
      Rod.setIngredient('S', Material.STRING);
      Rod.setIngredient('P', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.ice()));
      return Rod;
    }
    
    public ShapedRecipe SixthFishingRod() {
      ItemStack item = this.items.sixthFishingRod();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "fish_rod");
      ShapedRecipe Rod = new ShapedRecipe(key, item);
      Rod.shape(new String[] { "  C", " CS", "C P" });
      Rod.setIngredient('C', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.reinforcedRod()));
      Rod.setIngredient('S', Material.STRING);
      Rod.setIngredient('P', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.bait()));
      return Rod;
    }
    
    public ShapedRecipe SeventhFishingRod() {
		ItemStack item = this.items.PrecursorRod();
		
		NamespacedKey key = new NamespacedKey(this.plugin, "axe_rod");
			
		ShapedRecipe Rod = new ShapedRecipe(key, item);
			
		Rod.shape("  C", " CS", "C P");
		Rod.setIngredient('C', new RecipeChoice.ExactChoice(this.items.axeHead()));
		Rod.setIngredient('S', Material.COBWEB);
		Rod.setIngredient('P', new RecipeChoice.ExactChoice(this.items.bait()));

		return Rod;
	}
    
    public ShapedRecipe BossEgg() {
      ItemStack item = this.items.bossEggItem();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "first_boss");
      ShapedRecipe Egg = new ShapedRecipe(key, item);
      Egg.shape(new String[] { "DCD", "SDT", "DPD" });
      Egg.setIngredient('D', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.deepWaterCod()));
      Egg.setIngredient('C', Material.COD);
      Egg.setIngredient('S', Material.SALMON);
      Egg.setIngredient('T', Material.TROPICAL_FISH);
      Egg.setIngredient('P', Material.PUFFERFISH);
      return Egg;
    }
    
    public ShapedRecipe SecondBossEgg() {
      ItemStack item = this.items.secondBossItem();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "second_boss");
      ShapedRecipe Egg = new ShapedRecipe(key, item);
      Egg.shape(new String[] { "T T", "C C", "I I" });
      Egg.setIngredient('T', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.tentacle()));
      Egg.setIngredient('C', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.deepWaterCod()));
      Egg.setIngredient('I', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.ice()));
      return Egg;
    }
    
    public ShapedRecipe BearDecoy() {
      ItemStack item = this.items.imitationBear();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "bear_decoy");
      ShapedRecipe Egg = new ShapedRecipe(key, item);
      Egg.shape(new String[] { "PPP", "PMP", "PPP" });
      Egg.setIngredient('P', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.bearPelt()));
      Egg.setIngredient('M', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.bearMeat()));
      return Egg;
    }
    
    public ShapedRecipe Stuffing() {
      ItemStack item = this.items.bearMeat();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "imitation_bear_meat");
      ShapedRecipe meat = new ShapedRecipe(key, item);
      meat.shape(new String[] { "ABC", "DED", "CBA" });
      meat.setIngredient('A', (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(Material.PORKCHOP)));
      meat.setIngredient('B', (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(Material.BEEF)));
      meat.setIngredient('C', (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(Material.CHICKEN)));
      meat.setIngredient('D', (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(Material.MUTTON)));
      meat.setIngredient('E', (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(Material.RABBIT)));
      return meat;
    }
    
    public ShapedRecipe SalmonBar() {
      ItemStack item = this.items.salmonBar();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "salmon_bar");
      ShapedRecipe meat = new ShapedRecipe(key, item);
      meat.shape(new String[] { "DGD", "ESE", "DGD" });
      meat.setIngredient('G', (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(Material.GOLD_INGOT)));
      meat.setIngredient('S', (RecipeChoice)new RecipeChoice.ExactChoice(this.plugin.items.salmonEssence()));
      meat.setIngredient('D', (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(Material.DIAMOND)));
      meat.setIngredient('E', (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(Material.EMERALD)));
      return meat;
    }
    
    public ShapedRecipe Bait() {
      ItemStack item = this.items.bait();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "bait");
      ShapedRecipe Egg = new ShapedRecipe(key, item);
      Egg.shape(new String[] { "BBB", "BBB", "BBB" });
      Egg.setIngredient('B', (RecipeChoice)new RecipeChoice.ExactChoice(new ItemStack(Material.SALMON)));
      return Egg;
    }
    
    public ShapedRecipe AncientHelmRec() {
      ItemStack item = this.items.ancientHelmet();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "ancient_helmet");
      ShapedRecipe A = new ShapedRecipe(key, item);
      A.shape(new String[] { "NNN", "N N", "   " });
      A.setIngredient('N', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.UFOShard()));
      return A;
    }
    
    public ShapedRecipe AncientBootsRec() {
      ItemStack item = this.items.ancientBoots();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "ancient_boots");
      ShapedRecipe A = new ShapedRecipe(key, item);
      A.shape(new String[] { "   ", "N N", "N N" });
      A.setIngredient('N', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.UFOShard()));
      return A;
    }
    
    public ShapedRecipe AncientChestRec() {
      ItemStack item = this.items.ancientChestplate();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "ancient_Chestplate");
      ShapedRecipe A = new ShapedRecipe(key, item);
      A.shape(new String[] { "N N", "NNN", "NNN" });
      A.setIngredient('N', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.UFOShard()));
      return A;
    }
    
	
	public ShapedRecipe SeventhSonsCloak() {
		ItemStack item = this.items.SeventhSonsCloak();
		
		NamespacedKey key = new NamespacedKey(this.plugin, "Seventh_Sons_Cloak");
		
		ShapedRecipe Armor = new ShapedRecipe(key,item);
		
		Armor.shape("S S", "SSS", "SSS");
		Armor.setIngredient('S', new RecipeChoice.ExactChoice(this.items.SeventhSonsSoul()));
		
		return Armor;
	}
	
	public ShapedRecipe SeventhSonsSword() {
		ItemStack item = this.items.SeventhSonsSword();
		
		NamespacedKey key = new NamespacedKey(this.plugin, "Seventh_Sons_Sword");
		
		ShapedRecipe Sword = new ShapedRecipe(key, item);
			
		Sword.shape(" S ", " S ", " R ");
		Sword.setIngredient('S', new RecipeChoice.ExactChoice(this.items.SeventhSonsSoul()));
		Sword.setIngredient('R', new RecipeChoice.ExactChoice(this.items.reinforcedRod()));

		return Sword;
	}
	
	public ShapedRecipe SeventhSonsHelm() {
		ItemStack item = this.items.SeventhSonsHelm();
		
		NamespacedKey key = new NamespacedKey(this.plugin, "Seventh_Sons_Helm");
		
		ShapedRecipe Armor = new ShapedRecipe(key,item);
		
		Armor.shape("SSS", "S S", "   ");
		Armor.setIngredient('S', new RecipeChoice.ExactChoice(this.items.SeventhSonsSoul()));
		
		return Armor;
	}
	
	public ShapedRecipe SeventhSonsLeggings() {
		ItemStack item = this.items.SeventhSonsLeggings();
		
		NamespacedKey key = new NamespacedKey(this.plugin, "Seventh_Sons_Leggings");
		
		ShapedRecipe Armor = new ShapedRecipe(key,item);
		
		Armor.shape("SSS", "S S", "S S");
		Armor.setIngredient('S', new RecipeChoice.ExactChoice(this.items.SeventhSonsSoul()));
		
		return Armor;
	}
	
	public ShapedRecipe SeventhSonsBoots() {
		ItemStack item = this.items.SeventhSonsBoots();
		
		NamespacedKey key = new NamespacedKey(this.plugin, "Seventh_Sons_Boots");
		
		ShapedRecipe Armor = new ShapedRecipe(key,item);
		
		Armor.shape("   ", "S S", "S S");
		Armor.setIngredient('S', new RecipeChoice.ExactChoice(this.items.SeventhSonsSoul()));
		
		return Armor;
	}
    
    public ShapedRecipe AncientLeggingsRec() {
      ItemStack item = this.items.ancientLeggings();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "ancient_leggings");
      ShapedRecipe A = new ShapedRecipe(key, item);
      A.shape(new String[] { "NNN", "N N", "N N" });
      A.setIngredient('N', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.UFOShard()));
      return A;
    }
    
    public ShapedRecipe WaterLoggedPlank() {
      ItemStack item = this.items.waterLoggedPlank();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "water_sword");
      ShapedRecipe sword = new ShapedRecipe(key, item);
      sword.shape(new String[] { " W ", " W ", " D " });
      sword.setIngredient('W', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.waterPearl()));
      sword.setIngredient('D', Material.STICK);
      return sword;
    }
    
    public ShapedRecipe SalmonPickaxe() {
      ItemStack item = this.items.salmonPickaxe();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "salmon_pickaxe");
      ShapedRecipe sword = new ShapedRecipe(key, item);
      sword.shape(new String[] { "SSS", " I ", " I " });
      sword.setIngredient('S', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.salmonBar()));
      sword.setIngredient('I', Material.IRON_INGOT);
      return sword;
    }
    
    public ShapedRecipe SalmonAxe() {
      ItemStack item = this.items.salmonAxe();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "salmon_axe");
      ShapedRecipe sword = new ShapedRecipe(key, item);
      sword.shape(new String[] { " SS", " IS", " I " });
      sword.setIngredient('S', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.salmonBar()));
      sword.setIngredient('I', Material.IRON_INGOT);
      return sword;
    }
    
    public ShapedRecipe SalmonShovel() {
      ItemStack item = this.items.salmonShovel();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "salmon_shovel");
      ShapedRecipe sword = new ShapedRecipe(key, item);
      sword.shape(new String[] { " S ", " I ", " I " });
      sword.setIngredient('S', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.salmonBar()));
      sword.setIngredient('I', Material.IRON_INGOT);
      return sword;
    }
    
    public ShapedRecipe ReinforcedRod() {
      ItemStack item = this.items.reinforcedRod();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "reinforced_rod");
      ShapedRecipe rod = new ShapedRecipe(key, item);
      rod.shape(new String[] { "  S", " S ", "   " });
      rod.setIngredient('S', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.scale()));
      return rod;
    }
    
    public ShapedRecipe SalmonRod() {
      ItemStack item = this.items.salmonRod();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "Salmon_Rod");
      ShapedRecipe r = new ShapedRecipe(key, item);
      r.shape(new String[] { "  S", " ST", "S B" });
      r.setIngredient('S', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.salmonBar()));
      r.setIngredient('T', Material.COBWEB);
      r.setIngredient('B', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.imitationBear()));
      return r;
    }
    
    public ShapedRecipe LumberBow() {
      ItemStack item = this.items.lumberBow();
      NamespacedKey key = new NamespacedKey((Plugin)this.plugin, "Lumber_Bow");
      ShapedRecipe r = new ShapedRecipe(key, item);
      r.shape(new String[] { "SI ", "S I", "SI " });
      r.setIngredient('S', Material.COBWEB);
      r.setIngredient('I', (RecipeChoice)new RecipeChoice.ExactChoice(this.items.axeHead()));
      return r;
    }
    
    private Inventory newInventory(ShapedRecipe recipe, String type) {
      ItemStack border;
      Inventory recipeGUI = Bukkit.createInventory(null, 45, ChatColor.AQUA + "Recipe");
      String[] shape = recipe.getShape();
      if (type.equals("rod")) {
        border = new ItemStack(Material.CYAN_STAINED_GLASS_PANE);
      } else if (type.equals("boss")) {
        border = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
      } else if (type.equals("armor")) {
        border = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
      } else if (type.equals("tool")) {
        border = new ItemStack(Material.RED_STAINED_GLASS_PANE);
      } else {
        border = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
      } 
      Map<Character, ItemStack> map = recipe.getIngredientMap();
      for (int i = 0; i < 45; i++) {
        if (i > 11 && i < 15) {
          int j = i - 12;
          if (map.containsKey(Character.valueOf(shape[0].charAt(j))))
            recipeGUI.setItem(i, map.get(Character.valueOf(shape[0].charAt(j)))); 
        } else if (i == 19) {
          ItemStack exit = new ItemStack(Material.BARRIER);
          ItemMeta meta = exit.getItemMeta();
          meta.setDisplayName(ChatColor.RED + "" + ChatColor.BOLD + "Exit");
          exit.setItemMeta(meta);
          recipeGUI.setItem(i, exit);
        } else if (i > 20 && i < 24) {
          int j = i - 21;
          if (map.containsKey(Character.valueOf(shape[1].charAt(j))))
            recipeGUI.setItem(i, map.get(Character.valueOf(shape[1].charAt(j)))); 
        } else if (i == 25) {
          recipeGUI.setItem(i, recipe.getResult());
        } else if (i > 29 && i < 33) {
          int j = i - 30;
          if (map.containsKey(Character.valueOf(shape[2].charAt(j))))
            recipeGUI.setItem(i, map.get(Character.valueOf(shape[2].charAt(j)))); 
        } else {
          recipeGUI.setItem(i, border);
        } 
      } 
      return recipeGUI;
    }
  }
 