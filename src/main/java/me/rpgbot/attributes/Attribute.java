package me.rpgbot.attributes;

/**
 * Created by Seth on June 6/20/2020, 2020 at 12:26 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public enum Attribute {

    STRENGTH("Strength", 1, 255),
    CONSTITUION("Constitution", 10, 999),
    DEXTERITY("Dexterity", 1, 112),
    INTELLIGENCE("Intelligence", 1, 512),
    WISDOM("Wisdom", 1, 512),
    CHARISMA("Charisma", 1, 255),
    ARCHERY("Archery", 1, 255),
    MAGESTRY("Magestry",1 , 144),

    ;

    public final String name;

    public final int baseLevel;

    public final int maxLevel;

    Attribute(final String name, final int baseLevel, final int maxLevel) {
        this.name = name;
        this.baseLevel = baseLevel;
        this.maxLevel = maxLevel;
    }



}
