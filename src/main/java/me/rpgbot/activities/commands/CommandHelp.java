package me.rpgbot.activities.commands;

import me.rpgbot.activities.CommandTask;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Created by Seth on June 6/20/2020, 2020 at 4:07 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class CommandHelp extends CommandTask {

    @Override
    public void onCommand(Player player, MessageChannel channel) {
        sendMessage(channel, player, "Discord RPG Bot Command List:\n" +
                "- !rpg help\n" +
                "- !rpg pray\n" +
                "- !rpg fish\n" +
                "- !rpg wc\n" +
                "- !rpg fm\n" +
                "- !rpg gold\n" +
                "- !rpg gamble\n" +
                "- !rpg inv\n" +
                "- !rpg attr\n" +
                "");
    }

    @Override
    public String name() {
        return "Help command";
    }

    @Override
    public String prefix() {
        return "help";
    }

    @Override
    public boolean canPerformTask(Player player) {
        return true;
    }
}
