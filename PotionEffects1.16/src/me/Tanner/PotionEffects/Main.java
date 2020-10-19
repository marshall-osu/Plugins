package me.Tanner.PotionEffects;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Tanner.PotionEffects.Files.DataManager;

public class Main extends JavaPlugin{

	public DataManager data;
	
	 @Override
	public void onEnable() {
		 
		 this.data = new DataManager(this);
		 
		 this.getCommand("potioneffect").setExecutor(new Potion(this));
		 this.getCommand("potioneffect").setTabCompleter(new TabCompletion());
		 
		 new JoinGameListener(this);
	}

	@Override
	public void onDisable() {

	}
	
	public PotionEffect getEffect(String name, int power) {
		PotionEffect effect = null;
		switch(name) {
			case "speed":
				effect = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, power);
				break;
			case "slowness":
				effect = new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, power);
				break;
			case "haste":
				effect = new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, power);
				break;
			case "miningfatigue":
				effect = new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, power);
				break;
			case "strength":
				effect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, power);
				break;
			case "instanthealth":
				effect = new PotionEffect(PotionEffectType.HEAL, Integer.MAX_VALUE, power);
				break;
			case "instantdamage":
				effect = new PotionEffect(PotionEffectType.HARM, Integer.MAX_VALUE, power);
				break;
			case "jumpboost":
				effect = new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, power);
				break;
			case "nausea":
				effect = new PotionEffect(PotionEffectType.CONFUSION, Integer.MAX_VALUE, power);
				break;
			case "regeneration":
				effect = new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, power);
				break;
			case "resistance":
				effect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, power);
				break;
			case "fireresistance":
				effect = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, power);
				break;
			case "waterbreathing":
				effect = new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, power);
				break;
			case "invisability":
				effect = new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, power);
				break;
			case "blindness":
				effect = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, power);
				break;
			case "nightvison":
				effect = new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, power);
				break;
			case "hunger":
				effect = new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, power);
				break;
			case "weakness":
				effect = new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, power);
				break;
			case "poison":
				effect = new PotionEffect(PotionEffectType.POISON, Integer.MAX_VALUE, power);
				break;
			case "wither":
				effect = new PotionEffect(PotionEffectType.WITHER, Integer.MAX_VALUE, power);
				break;
			case "healthboost":
				effect = new PotionEffect(PotionEffectType.HEALTH_BOOST, Integer.MAX_VALUE, power);
				break;
			case "absorption":
				effect = new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, power);
				break;
			case "saturation":
				effect = new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, power);
				break;
			case "glowing":
				effect = new PotionEffect(PotionEffectType.GLOWING, Integer.MAX_VALUE, power);
				break;
			case "levitation":
				effect = new PotionEffect(PotionEffectType.LEVITATION, Integer.MAX_VALUE, power);
				break;
			case "luck":
				effect = new PotionEffect(PotionEffectType.LUCK, Integer.MAX_VALUE, power);
				break;
			case "badluck":
				effect = new PotionEffect(PotionEffectType.UNLUCK, Integer.MAX_VALUE, power);
				break;
			case "slowfalling":
				effect = new PotionEffect(PotionEffectType.SLOW_FALLING, Integer.MAX_VALUE, power);
				break;
			case "conduitpower":
				effect = new PotionEffect(PotionEffectType.CONDUIT_POWER, Integer.MAX_VALUE, power);
				break;
			case "dolphingrace":
				effect = new PotionEffect(PotionEffectType.DOLPHINS_GRACE, Integer.MAX_VALUE, power);
				break;
			case "badomen":
				effect = new PotionEffect(PotionEffectType.BAD_OMEN, Integer.MAX_VALUE, power);
				break;
			case "hero":
				effect = new PotionEffect(PotionEffectType.HERO_OF_THE_VILLAGE, Integer.MAX_VALUE, power);
				break;
		}
		return effect;
	}
}
