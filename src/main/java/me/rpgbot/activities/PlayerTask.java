package me.rpgbot.activities;

import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Created by Seth on June 6/19/2020, 2020 at 1:47 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class PlayerTask {

    public final Task task;

    public final Player player;

    public final long startTime;

    public final MessageChannel channel;

    public PlayerTask(final Player player, final Task task, final MessageChannel channel) {
        this.player = player;
        this.task = task;
        this.channel = channel;
        this.startTime = System.currentTimeMillis();
    }

    public boolean elapsed() {
        return System.currentTimeMillis() >= startTime + task.taskCompletionTime();
    }

}
