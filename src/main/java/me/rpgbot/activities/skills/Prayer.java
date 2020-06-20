package me.rpgbot.activities.skills;

import me.rpgbot.activities.Task;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.entities.MessageChannel;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Seth on June 6/19/2020, 2020 at 4:33 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class Prayer extends Task {

    @Override
    public String name() {
        return "Prayer";
    }

    @Override
    public String prefix() {
        return "pray";
    }

    @Override
    public long taskCompletionTime() {
        return 1000 * 60 * 2;
    }

    @Override
    public boolean canPerformTask(Player player) {
        return true;
    }

    @Override
    public void startTask(Player player, MessageChannel channel) {
        sendMentionMessage(channel, player, "you stop what you're doing and take out your compass and prayer rug from your backpack....\n" +
                "You find the direction of the kaaba and lay your prayer rug flat on the ground in that direction...\n" +
                "You begin to pray %s salaat to please allah and to keep the shiatana's away. (%s till completion)", getPrayer(), "2 minutes");
    }

    @Override
    public void completeTask(Player player, MessageChannel channel) {
        sendMentionMessage(channel, player, "*Assalaamu 'Alaikum Wa Rahmatullahi* *Assalaamu 'Alaikum Wa Rahmatullahi*\n" +
                "You finish praying your salaat and make a dua to keep yourself on the sunni path and to never stray towards the shiatana's...\n" +
                "You suddenly feel a sense of holiness run through your body and shout *Alhamdulillah*....");
    }

    public String getPrayer() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
        Date currentDate = calendar.getTime();
        final int hours = currentDate.getHours();
        final int minutes = currentDate.getMinutes();
        System.out.println("Time: " + hours + ":" + minutes);
        if ((hours >= 3 && minutes >= 43) && hours < 13) {
            return "Fajr";
        } else if (hours >= 13 && hours < 17) {
            return "Dhuhr";
        } else if ((hours >= 17/* && minutes >= 56*/) && (hours < 20/* && minutes <= 29*/)) {
            return "Asr";
        } else if ((hours >= 20/* && minutes >= 29*/) && (hours < 22/* && minutes <= 9*/)) {
            return "Maghrib";
        } else if ((hours >= 22/* && minutes >= 10*/) && hours < 24) {
            return "Isha";
        }
        return "";
    }

}
