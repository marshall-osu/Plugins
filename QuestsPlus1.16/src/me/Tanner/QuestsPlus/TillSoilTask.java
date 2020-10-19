package me.Tanner.QuestsPlus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEvent;

import com.leonardobishop.quests.api.QuestsAPI;
import com.leonardobishop.quests.player.QPlayer;
import com.leonardobishop.quests.player.questprogressfile.QuestProgress;
import com.leonardobishop.quests.player.questprogressfile.QuestProgressFile;
import com.leonardobishop.quests.player.questprogressfile.TaskProgress;
import com.leonardobishop.quests.quests.Quest;
import com.leonardobishop.quests.quests.Task;
import com.leonardobishop.quests.quests.tasktypes.ConfigValue;
import com.leonardobishop.quests.quests.tasktypes.TaskType;

public class TillSoilTask extends TaskType {

	private List<ConfigValue> creatorConfigValues = new ArrayList<>();
	
	public TillSoilTask() {
		super("tillsoil", "deathkillall1", "Till soil");
		this.creatorConfigValues.add(new ConfigValue("amount", true, "Amount of soil to be tilled"));
	}
	
	@Override
    public List<ConfigValue> getCreatorConfigValues() {
        return creatorConfigValues;
    }
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void soilTill(PlayerInteractEvent e) {
		
		Player player = e.getPlayer();
		Material tool = player.getInventory().getItemInMainHand().getType();
		if(!(tool.equals(Material.DIAMOND_HOE) || tool.equals(Material.GOLDEN_HOE) || tool.equals(Material.IRON_HOE)
				|| tool.equals(Material.STONE_HOE) || tool.equals(Material.WOODEN_HOE))) {
			return;
		}
		
		Block block = e.getClickedBlock();
		if(!(block.getType() != Material.GRASS && block.getType() != Material.DIRT)) {
			return;
		}

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

        			int tillNeeded = (int) task.getConfigValue("amount");
        			int tillProgress;

        			if (taskProgress.getProgress() == null) {
        				tillProgress = 0;
        			} else {
        				tillProgress = (int) taskProgress.getProgress();
        			}

        			taskProgress.setProgress(tillProgress + 1);
        			
        			if (((int) taskProgress.getProgress()) >= tillNeeded) {
        				taskProgress.setCompleted(true);
        			}
        		}
        	}
        }
	}
}
