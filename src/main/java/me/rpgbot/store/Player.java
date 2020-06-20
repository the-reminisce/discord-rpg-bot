package me.rpgbot.store;

import me.rpgbot.containers.ItemContainer;
import net.dv8tion.jda.core.entities.User;

import java.util.HashSet;

/**
 * Created by Seth on June 6/19/2020, 2020 at 12:25 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class Player {

    private final long playerId;

    private String lastMessage;
    private HashSet<Integer> completedQuests = new HashSet<>();
    private ItemContainer inventory;

    private transient User discordUser;
    private transient String playerName;

    public Player(final long playerId) {
        this.playerId = playerId;
        getCompletedQuests();
        getInventory();
    }

    public long getPlayerId() {
        return playerId;
    }

    public User getDiscordUser() {
        return discordUser;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setDiscordUser(User discordUser) {
        this.discordUser = discordUser;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getMentionName() {
        return getDiscordUser().getAsMention();
    }

    public HashSet<Integer> getCompletedQuests() {
        if (completedQuests == null) {
            completedQuests = new HashSet<>();
        }
        return completedQuests;
    }

    public ItemContainer getInventory() {
        if (inventory == null) {
            inventory = new ItemContainer("Inventory", 42);
        }
        return inventory;
    }

}
