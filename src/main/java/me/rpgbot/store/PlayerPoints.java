package me.rpgbot.store;

/**
 * Created by Seth on June 6/20/2020, 2020 at 3:36 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class PlayerPoints {

    public int gold;

    public int attributePoints;

    public transient Player player;

    public PlayerPoints(final Player player) {
        this.player = player;
    }
}
