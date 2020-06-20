package me.rpgbot.activities.commands;

import me.rpgbot.activities.CommandTask;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Created by Seth on June 6/20/2020, 2020 at 2:48 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class CommandAttribute extends CommandTask {

    @Override
    public String name() {
        return "Attribute Check";
    }

    @Override
    public String prefix() {
        return "attr";
    }

    @Override
    public boolean canPerformTask(Player player) {
        return true;
    }

    @Override
    public void onCommand(Player player, MessageChannel channel) {
        sendMentionMessage(channel, player, "You take a glance inside of your body and determine your attributes: %s", player.getAttributes());
    }

}
