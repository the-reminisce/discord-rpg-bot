package me.rpgbot.attributes;

import me.rpgbot.store.Player;

import java.util.HashMap;

/**
 * Created by Seth on June 6/20/2020, 2020 at 12:33 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class PlayerAttributes {

    public transient Player player;
    public HashMap<Attribute, Integer> attributes;

    public PlayerAttributes(final Player player) {
        this.player = player;
        this.attributes = new HashMap<>();
        for (Attribute attribute : Attribute.values()) {
            attributes.put(attribute, attribute.baseLevel);
        }
    }

    public void increaseLevel(final Attribute attribute, final int increment) {
        attributes.put(attribute, Math.min(attributes.get(attribute) + increment, attribute.maxLevel));
    }

    @Override
    public String toString() {
        return "PlayerAttributes{" +
                "attributes=" + attributes +
                '}';
    }
}
