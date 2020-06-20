package me.rpgbot.activities;

import me.rpgbot.RpgBot;
import me.rpgbot.activities.actions.InventoryCheck;
import me.rpgbot.activities.commands.CommandAttribute;
import me.rpgbot.activities.commands.CommandGamble;
import me.rpgbot.activities.commands.CommandGold;
import me.rpgbot.activities.commands.CommandHelp;
import me.rpgbot.activities.skills.Firemaking;
import me.rpgbot.activities.skills.Fishing;
import me.rpgbot.activities.skills.Prayer;
import me.rpgbot.activities.skills.Woodcutting;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.util.*;

/**
 * Created by Seth on June 6/19/2020, 2020 at 1:35 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class TaskManager {

    private final RpgBot bot;
    private final HashSet<Task> taskSet;
    private final HashMap<Player, PlayerTask> playerTasks;

    public TaskManager(final RpgBot bot) {
        this.bot = bot;
        this.taskSet = new HashSet<>();
        this.playerTasks = new HashMap<>();

        //skills and actions
        taskSet.addAll(Arrays.asList(new Fishing(), new Woodcutting(), new Prayer(), new Firemaking()));
        //commands
        taskSet.addAll(Arrays.asList(new CommandHelp(), new CommandGamble(), new InventoryCheck(), new CommandAttribute(), new CommandGold()));
        taskLoop();
    }

    private void taskLoop() {
        // Super wacking way to run an a task but hey its a discord bot
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (playerTasks != null && !playerTasks.isEmpty()) {
                    HashSet<Player> remove = new HashSet<>();
                    for (PlayerTask t : playerTasks.values()) {
                        if (t.elapsed()) {
                            t.task.completeTask(t.player, t.channel);
                            bot.savePlayer(t.player);
                            remove.add(t.player);
                        }
                    }
                    for (Player player : remove) {
                        playerTasks.remove(player);
                    }
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public boolean performingTask(final Player player) {
        return playerTasks.containsKey(player);
    }

    public Task taskFromCommand(final String command) {
        for (Task task : taskSet) {
            if (command.equalsIgnoreCase(task.prefix())) {
                return task;
            }
        }
        return null;
    }

    public void processPlayerTask(final Player player, final String taskCommand, final String[] args, final MessageChannel channel) {
        if (player == null || channel == null) {
            return;
        }
        final Task task = taskFromCommand(taskCommand);
        if (task == null) {
            return;
        }
        if (task.taskCompletionTime() <= 0) { // Allows for the execution of instantaneous tasks such as checking your inventory
            task.startTask(player, channel);
            task.completeTask(player, channel);
            return;
        }
        if (performingTask(player)) {
            bot.sendMentionMessage(channel, player, "you're already performing a task, please wait before trying to start another one!", task.name());
            return;
        }
        if (!task.canPerformTask(player)) {
            //bot.sendMentionMessage(channel, player, "you don't meet the requirements to perform: %s", task.name());
            return;
        }
        final PlayerTask playerTask = new PlayerTask(player, task, channel);
        playerTasks.put(player, playerTask);
        task.startTask(player, channel);
    }

}
