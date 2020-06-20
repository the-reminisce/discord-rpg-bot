package me.rpgbot.activities;

import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Created by Seth on June 6/20/2020, 2020 at 2:46 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public abstract class CommandTask extends Task {

    public abstract void onCommand(final Player player, MessageChannel channel);

    @Override
    public long taskCompletionTime() {
        return 0;
    }

    @Override
    public void startTask(Player player, MessageChannel channel) {
        onCommand(player, channel);
    }

    @Override
    public void completeTask(Player player, MessageChannel channel) {

    }
}
