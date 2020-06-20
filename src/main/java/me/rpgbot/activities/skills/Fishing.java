package me.rpgbot.activities.skills;

import me.rpgbot.activities.Task;
import me.rpgbot.containers.Item;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Created by Seth on June 6/19/2020, 2020 at 1:26 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class Fishing extends Task {

/*    public Fishing(MessageReceivedEvent event) {
        super(event);
    }*/

    @Override
    public String name() {
        return "Fishing";
    }

    @Override
    public String prefix() {
        return "fish";
    }

    @Override
    public long taskCompletionTime() {
        return 10000; // 10 seconds
    }

    @Override
    public boolean canPerformTask(Player player) {
        return true;
    }

    @Override
    public void startTask(Player player, final MessageChannel channel) {
        sendMentionMessage(channel, player, "you throw your rod into the water hoping to catch something... (%s till completion)", "10 seconds");
    }

    @Override
    public void completeTask(Player player, final MessageChannel channel) {
        player.getInventory().addItem(new Item("Raw Shrimp", 1, true));
        sendMentionMessage(channel, player, "you reel in your rod and find that you've caught some %s!\n+%s exp", "raw shrimps", "50");
    }
}
