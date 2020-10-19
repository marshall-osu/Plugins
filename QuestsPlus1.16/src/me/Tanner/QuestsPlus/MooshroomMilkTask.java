package me.Tanner.QuestsPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.leonardobishop.quests.api.QuestsAPI;
import com.leonardobishop.quests.player.QPlayer;
import com.leonardobishop.quests.player.questprogressfile.QuestProgress;
import com.leonardobishop.quests.player.questprogressfile.QuestProgressFile;
import com.leonardobishop.quests.player.questprogressfile.TaskProgress;
import com.leonardobishop.quests.quests.Quest;
import com.leonardobishop.quests.quests.Task;
import com.leonardobishop.quests.quests.tasktypes.ConfigValue;
import com.leonardobishop.quests.quests.tasktypes.TaskType;

public class MooshroomMilkTask extends TaskType {

	private List<ConfigValue> creatorConfigValues = new ArrayList<>();
	
	public MooshroomMilkTask() {
		super("mooshroommilk", "deathkillall1", "Milk a mooshroom");
		this.creatorConfigValues.add(new ConfigValue("amount", true, "Amount of mooshrooms to be milked"));
	}
	
	@Override
    public List<ConfigValue> getCreatorConfigValues() {
        return creatorConfigValues;
    }
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void milkListener(PlayerInteractEntityEvent e) {
		if (!(e.getRightClicked() instanceof MushroomCow)) {
            return;
        }

        if (!e.getPlayer().getInventory().getItemInMainHand().equals(new ItemStack(Material.BOWL))) {
        	return;
        }
        
        if(e.getPlayer().getInventory().firstEmpty() == -1) {
        	return;
        }
        
        // Check if there is a player in the list, otherwise: return.
        Player player = e.getPlayer();
        QPlayer qPlayer = QuestsAPI.getPlayerManager().getPlayer(player.getUniqueId(), true);
        QuestProgressFile questProgressFile = qPlayer.getQuestProgressFile();

        for (Quest quest : super.getRegisteredQuests()) {
        	if (questProgressFile.hasStartedQuest(quest)) {
        		QuestProgress questProgress = questProgressFile.getQuestProgress(quest);

        		for (Task task : quest.getTasksOfType(super.getType())) {
        			TaskProgress taskProgress = questProgress.getTaskProgress(task.getId());

        			if (taskProgress.isCompleted()) {
        				continue;
        			}

        			int milkNeeded = (int) task.getConfigValue("amount");
        			int milkProgress;

        			if (taskProgress.getProgress() == null) {
        				milkProgress = 0;
        			} else {
        				milkProgress = (int) taskProgress.getProgress();
        			}

        			taskProgress.setProgress(milkProgress + 1);
        			
        			if (((int) taskProgress.getProgress()) >= milkNeeded) {
        				taskProgress.setCompleted(true);
        			}
        		}
        	}
        }
	}
}
