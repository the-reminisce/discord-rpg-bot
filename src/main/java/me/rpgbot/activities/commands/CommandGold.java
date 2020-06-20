package me.rpgbot.activities.commands;

import me.rpgbot.activities.CommandTask;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Created by Seth on June 6/20/2020, 2020 at 3:32 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class CommandGold extends CommandTask {

    @Override
    public void onCommand(Player player, MessageChannel channel) {
        sendMentionMessage(channel, player, "You open your purse and count your gold pieces....\n" +
                "You find that you have %d gold pieces.", player.getPoints().gold);
    }

    @Override
    public String name() {
        return "Gold Check";
    }

    @Override
    public String prefix() {
        return "gold";
    }

    @Override
    public boolean canPerformTask(Player player) {
        return true;
    }
}
