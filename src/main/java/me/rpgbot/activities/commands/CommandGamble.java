package me.rpgbot.activities.commands;

import me.rpgbot.activities.CommandTask;
import me.rpgbot.store.Player;
import me.rpgbot.utils.UtilMath;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Created by Seth on June 6/20/2020, 2020 at 3:49 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class CommandGamble extends CommandTask {

    @Override
    public void onCommand(Player player, MessageChannel channel) {
        final int roll = UtilMath.random(1, 100);
        sendMentionMessage(channel, player, "You roll a %d on the die and %s %d gold pieces.", roll, (roll >= 55 ? "win" : "lose"), 100);
        player.getPoints().gold += roll >= 55 ? 100 : -100;
    }

    @Override
    public String name() {
        return "Gambling";
    }

    @Override
    public String prefix() {
        return "dice";
    }

    @Override
    public boolean canPerformTask(Player player) {
        return true;
    }
}
