package me.Tanner.QuestsPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;

import com.leonardobishop.quests.api.QuestsAPI;
import com.leonardobishop.quests.player.QPlayer;
import com.leonardobishop.quests.player.questprogressfile.QuestProgress;
import com.leonardobishop.quests.player.questprogressfile.QuestProgressFile;
import com.leonardobishop.quests.player.questprogressfile.TaskProgress;
import com.leonardobishop.quests.quests.Quest;
import com.leonardobishop.quests.quests.Task;
import com.leonardobishop.quests.quests.tasktypes.ConfigValue;
import com.leonardobishop.quests.quests.tasktypes.TaskType;

public class SwimTask extends TaskType {

	private List<ConfigValue> creatorConfigValues = new ArrayList<>();
	
	public SwimTask() {
		super("swim", "deathkillall1", "Swim");
		this.creatorConfigValues.add(new ConfigValue("amount", true, "Meters left to swim"));
	}
	
	@Override
    public List<ConfigValue> getCreatorConfigValues() {
        return creatorConfigValues;
    }
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void healListener(PlayerMoveEvent e) {
        
		
        Player player = e.getPlayer();
		if(e.getTo().getBlock().getType().equals(Material.WATER) && e.getFrom().getBlock().getType().equals(Material.WATER)) {
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

                        int levelsNeeded = (int) task.getConfigValue("amount");
                        int levelsProgress;

                        if (taskProgress.getProgress() == null) {
                            levelsProgress = 0;
                        } else {
                            levelsProgress = (int) taskProgress.getProgress();
                        }

                        taskProgress.setProgress(levelsProgress + e.getFrom().distance(e.getTo()));

                        if (((int) taskProgress.getProgress()) >= levelsNeeded) {
                            taskProgress.setCompleted(true);
                        }
                    }
                }
            }
		}
                
    }
}