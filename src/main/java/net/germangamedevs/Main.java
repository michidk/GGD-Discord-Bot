package net.germangamedevs;

import com.jagrosh.jdautilities.commandclient.CommandClient;
import com.jagrosh.jdautilities.commandclient.CommandClientBuilder;
import com.jagrosh.jdautilities.waiter.EventWaiter;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.germangamedevs.commands.*;
import net.germangamedevs.features.AuthorizedServerCheckFeature;
import net.germangamedevs.managers.ConfigManager;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public class Main {

    private static JDA jdaInstance = null;
    private static CommandClient commandClientInstance = null;


    public static void main(String[] args) {
        initializeJda();

        new AuthorizedServerCheckFeature(jdaInstance);
    }

    private static void initializeJda() {
        try {
            ConfigManager.loadConfig();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // define an eventwaiter
        EventWaiter waiter = new EventWaiter();

        // define a command client
        CommandClientBuilder client = new CommandClientBuilder();

        // The default is "Type !help"
        client.setGame(Game.of("Info: !help"));

        // sets the owner of the bot
        client.setOwnerId("98126233753120768");

        // sets emojis used throughout the bot on successes, warnings, and failures
        client.setEmojis("\u2705", "\u2757", "\u274c");

        // sets the bot prefix
        client.setPrefix("!");

        // adds commands
        client.addCommands(
                new AboutCommand(),
                new CatCommand(),
                new PingCommand(),
                new IDCommand(),
                new SupportCommand()
        );

        commandClientInstance = client.build();

        // start getting a bot account set up
        try {
            jdaInstance = new JDABuilder(AccountType.BOT)
                    // set the token
                    .setToken(ConfigManager.getConfig().getToken())

                    // set the game for when the bot is loading
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .setGame(Game.of("loading..."))

                    // add the listeners
                    .addEventListener(waiter)
                    .addEventListener(commandClientInstance)

                    // start it up!
                    .buildAsync();
        } catch (LoginException | RateLimitedException e) {
            e.printStackTrace();
        }

    }

    public static void leaveServerIfUnauthorized(Guild guild) {
        long serverId = guild.getIdLong();

        if (serverId != ConfigManager.getConfig().getServer()) {
            System.out.println("Bot was added to unauthorized server " + serverId + "!");
            guild.leave().queue();
        }
    }

    public static JDA getJdaInstance() {
        return jdaInstance;
    }

    public static CommandClient getCommandClientInstance() {
        return commandClientInstance;
    }

}
