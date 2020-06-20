package me.rpgbot.activities.actions;

import me.rpgbot.activities.Task;
import me.rpgbot.containers.Item;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

/**
 * Created by Seth on June 6/19/2020, 2020 at 3:11 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class InventoryCheck extends Task {
    @Override
    public String name() {
        return "Inventory Check";
    }

    @Override
    public String prefix() {
        return "inv";
    }

    @Override
    public long taskCompletionTime() {
        return 0;
    }

    @Override
    public boolean canPerformTask(Player player) {
        return true;
    }

    @Override
    public void startTask(Player player, MessageChannel channel) {
        String inv = " You search your backpack and find that you have the following items: [\n";
        for (Item item : player.getInventory().itemList) {
            inv += "\t" + item + "\n";
        }
        inv += "]";
       channel.sendMessage(player.getDiscordUser().getAsMention() +  inv).queue();
    }

    @Override
    public void completeTask(Player player, MessageChannel channel) {

    }
}
