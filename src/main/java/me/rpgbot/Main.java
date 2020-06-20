package me.rpgbot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;

/**
 * Created by Seth on June 6/18/2020, 2020 at 11:47 PM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class Main extends ListenerAdapter {

    public static void main(String[] args) {
        System.out.println("Initializing RPG Bot....");
        new RpgBot();
        System.out.println("Successfully initialized RPG Bot....");
    }

}
