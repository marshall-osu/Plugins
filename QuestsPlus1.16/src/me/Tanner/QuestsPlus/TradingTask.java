package me.Tanner.QuestsPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;

import com.leonardobishop.quests.api.QuestsAPI;
import com.leonardobishop.quests.player.QPlayer;
import com.leonardobishop.quests.player.questprogressfile.QuestProgress;
import com.leonardobishop.quests.player.questprogressfile.QuestProgressFile;
import com.leonardobishop.quests.player.questprogressfile.TaskProgress;
import com.leonardobishop.quests.quests.Quest;
import com.leonardobishop.quests.quests.Task;
import com.leonardobishop.quests.quests.tasktypes.ConfigValue;
import com.leonardobishop.quests.quests.tasktypes.TaskType;

public class TradingTask extends TaskType {

	private List<ConfigValue> creatorConfigValues = new ArrayList<>();
	
	public TradingTask() {
		super("villagerlevel", "deathkillall1", "Level up a villager");
		this.creatorConfigValues.add(new ConfigValue("amount", true, "Villagers to be leveled up"));
	}
	
	@Override
    public List<ConfigValue> getCreatorConfigValues() {
        return creatorConfigValues;
    }
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void healListener(VillagerAcquireTradeEvent e) {

        Entity ent = e.getEntity();
        List<Entity> entList = ent.getNearbyEntities(10, 10, 10);

        if (entList.isEmpty()) {
            return;
        }
        
        // Check if there is a player in the list, otherwise: return.
        for (Entity current : entList) {

            if (current instanceof Player) {
                Player player = (Player) current;
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

                            taskProgress.setProgress(levelsProgress + 1);

                            if (((int) taskProgress.getProgress()) >= levelsNeeded) {
                                taskProgress.setCompleted(true);
                            }
                        }
                    }
                }
            }
        }
	}
}