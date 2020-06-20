package me.rpgbot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.rpgbot.activities.TaskManager;
import me.rpgbot.containers.Item;
import me.rpgbot.containers.ItemContainer;
import me.rpgbot.store.Player;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.stream.Collectors;

/**
 * Created by Seth on June 6/19/2020, 2020 at 12:29 AM
 *
 * @author Seth Davis <sethdavis321@gmail.com>
 * @Discord Reminisce#1707
 */
public class RpgBot extends ListenerAdapter {

    private final String BOT_DIRECTORY = System.getProperty("user.home") + File.separator + "rpgbot" + File.separator;
    private final String BOT_TOKEN = "NzIzMzg0NzQ0NjI4MzIyMzI2.Xuw2rQ.42zoEioO3vskx1yaZfRlJ4R8o_E";

    private HashMap<Long, Player> players;
    private final GsonBuilder gsonBuilder;
    private MessageChannel textChannel;

    private TaskManager taskManager;

    public RpgBot() {
        this.players = new HashMap<>();
        this.gsonBuilder = new GsonBuilder();
        this.gsonBuilder.setPrettyPrinting();

        initBot();
        loadPlayers();

        this.taskManager = new TaskManager(this);
    }

    public void log(String msg, Object... objects) {
        System.out.println(String.format(msg, objects));
    }

    private void initBot() {
        try {
            JDABuilder builder = new JDABuilder(AccountType.BOT);
            builder.setToken(BOT_TOKEN);
            builder.addEventListener(this);
            builder.buildAsync();
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }

    private void loadPlayers() {
        try {
            log("Loading player files");
            File directory = new File(BOT_DIRECTORY + "saves/");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            for (Path path : Files.list(Paths.get(BOT_DIRECTORY + "saves/")).collect(Collectors.toList())) {
                File file = new File(path.toUri());
                if (!file.getName().endsWith(".json")) {
                    continue;
                }
                Gson gson = gsonBuilder.create();
                Player player = gson.fromJson(new FileReader(file), Player.class);
                players.put(player.getPlayerId(), player);
                //log("Player: %s - lastMSG=%s", player.getPlayerId(), player.getLastMessage());
            }
            System.out.println("Successfully loaded all player files");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Player grabPlayer(long playerId) {
        if (!players.containsKey(playerId)) {
            players.put(playerId, new Player(playerId));
        }
        return players.get(playerId);
    }

    public void savePlayer(final Player player) {
        try {
            final File file = new File(BOT_DIRECTORY + "saves/" + player.getPlayerId() + ".json");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(file);
            Gson gson = gsonBuilder.create();

            writer.write(gson.toJson(player));

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        if (e.getAuthor().isBot() || e.getAuthor().isFake()) {
            return;
        }
        final String msg = e.getMessage().getContentDisplay();
        final Player player = grabPlayer(e.getAuthor().getIdLong());
        if (player.getDiscordUser() == null) {
            player.setDiscordUser(e.getAuthor());
        }

        player.setPlayerName(e.getAuthor().getName());
        player.setLastMessage(msg);
        savePlayer(player);
        log("%s - %s", e.getAuthor().getName(), msg);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
        Date currentDate = calendar.getTime();

        System.out.println(currentDate.getHours());
        if (!msg.startsWith("!rpg ")) {
            return;
        }
        String[] args = msg.split(" ");
        if (args.length > 1) {
            taskManager.processPlayerTask(player, args[1], e.getMessage().getChannel());
        }
    }

    public static void sendMessage(final MessageChannel channel, final Player player, final String message, final Object... objects) {
        channel.sendMessage(String.format(message, objects)).queue();
    }

    public static void sendMentionMessage(final MessageChannel channel, final Player player, final String message, final Object... objects) {
        channel.sendMessage(player.getMentionName() + " " + String.format(message, objects)).queue();
    }

}
