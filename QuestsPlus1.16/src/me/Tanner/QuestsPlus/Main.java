package me.Tanner.QuestsPlus;

import org.bukkit.plugin.java.JavaPlugin;

import com.leonardobishop.quests.Quests;
import com.leonardobishop.quests.quests.tasktypes.TaskTypeManager;

public class Main extends JavaPlugin{
	
	private static TaskTypeManager taskTypeManager;
	
	@Override
	public void onEnable() {
		 
		taskTypeManager = Quests.get().getTaskTypeManager();
		
		taskTypeManager.registerTaskType(new HealingZombieTask());
		taskTypeManager.registerTaskType(new MooshroomMilkTask());
		taskTypeManager.registerTaskType(new FishInBucketTask());
		taskTypeManager.registerTaskType(new TillSoilTask());
		taskTypeManager.registerTaskType(new TradingTask());
		
	}

	@Override
	public void onDisable() {

	}
}
