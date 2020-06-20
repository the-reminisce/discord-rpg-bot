package me.rpgbot.activities.skills;

import me.rpgbot.activities.Task;
import me.rpgbot.containers.Item;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Created by Seth on June 6/19/2020, 2020 at 8:07 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class Firemaking extends Task {

    @Override
    public String name() {
        return "Firemaking";
    }

    @Override
    public String prefix() {
        return "fm";
    }

    @Override
    public long taskCompletionTime() {
        return 5000;
    }

    @Override
    public boolean canPerformTask(Player player) {
        if (!player.getInventory().containsItem(new Item("Logs", 1), 1)) {
            return false;
        }
        return true;
    }

    @Override
    public void startTask(Player player, MessageChannel channel) {
        player.getInventory().removeItem(new Item("Logs"), 1);
        sendMentionMessage(channel, player, "You set your logs on the ground and take your spark rock out of your backpack....\n" +
                "You begin your attempt to starting a small cozy fire....");
    }

    @Override
    public void completeTask(Player player, MessageChannel channel) {
        sendMentionMessage(channel, player, "with the final stroke of your rock you successfully started a small bonfire.\n+%s exp", "50");
    }

}
