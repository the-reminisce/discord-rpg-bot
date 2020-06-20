package me.rpgbot.utils;

import java.util.Random;

/**
 * Created by Seth on June 6/20/2020, 2020 at 3:50 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class UtilMath {

    public static final Random RANDOM = new Random();

    public static int random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

}
