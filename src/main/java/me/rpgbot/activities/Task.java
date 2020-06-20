package me.rpgbot.activities;

import me.rpgbot.RpgBot;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by Seth on June 6/19/2020, 2020 at 1:23 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public abstract class Task {

/*    public MessageReceivedEvent event;

    public Task(MessageReceivedEvent event) {
        this.event = event;
    }*/

    /**
     * The formatted name of this task
     * @return
     */
    public abstract String name();

    /**
     * The command prefix to perform this task
     * @return
     */
    public abstract String prefix();

    /**
     * The time it takes in ms's to complete the task
     * @return
     */
    public abstract long taskCompletionTime();

    /**
     * Returns whether the player can perform this task or not
     * @param player
     * @return
     */
    public abstract boolean canPerformTask(final Player player);

    /**
     * The action to perform when the task has begun
     * @param player
     */
    public abstract void startTask(final Player player, final MessageChannel channel);

    /**
     * The action to perform when the task has been completed
     * @param player
     */
    public abstract void completeTask(final Player player, final MessageChannel channel);

    public void sendMessage(final MessageChannel channel, final Player player, final String message, final Object... objects) {
        RpgBot.sendMessage(channel, player, message, objects);
    }

    public void sendMentionMessage(final MessageChannel channel, final Player player, final String message, final Object... objects) {
        RpgBot.sendMentionMessage(channel, player, message, objects);
    }

}
