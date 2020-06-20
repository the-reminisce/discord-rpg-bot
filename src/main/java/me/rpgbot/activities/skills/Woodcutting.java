package me.rpgbot.activities.skills;

import me.rpgbot.activities.Task;
import me.rpgbot.containers.Item;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Created by Seth on June 6/19/2020, 2020 at 1:46 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class Woodcutting extends Task {

/*    public Woodcutting(MessageReceivedEvent event) {
        super(event);
    }*/

    @Override
    public String name() {
        return "Woodcutting";
    }

    @Override
    public String prefix() {
        return "wc";
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
        sendMentionMessage(channel, player, " you begin to swing your axe at the tree hopping your strength is enough to take it down... (%s till completion)", "10 seconds");
    }

    @Override
    public void completeTask(Player player, final MessageChannel channel) {
        player.getInventory().addItem(new Item("Logs", 5));
        sendMentionMessage(channel, player, "you hear the tree crack.... you run to avoid it from falling on top of you...\n+%d exp", "50");
    }

}