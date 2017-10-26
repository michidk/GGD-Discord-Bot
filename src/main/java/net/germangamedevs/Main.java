package net.germangamedevs;

import com.google.common.io.Resources;
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
import net.germangamedevs.features.WelcomeFeature;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Michael Lohr on 26-Aug-17.
 */
public class Main {

    private static final long GGD_ID = 367310388737605632L;//287308543273730051L;
    private static final String TOKEN_FILE_NAME = "token.txt";

    private static JDA jdaInstance = null;


    public static void main(String[] args) {
        initializeJda();

        new AuthorizedServerCheckFeature(jdaInstance);
        new WelcomeFeature(jdaInstance);
    }

    private static void initializeJda() {

        String token = null;

        try {
            token = Resources.toString(Resources.getResource(TOKEN_FILE_NAME), StandardCharsets.UTF_8); //TODO: create a config-file? (json/xml/properties?)
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (token == null) {
            System.err.println("Can't read token.txt. Exit");
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
                new RoleCommand(),
                new SupportCommand(),
                new WelcomeCommand()
        );

        // start getting a bot account set up
        try {
            jdaInstance = new JDABuilder(AccountType.BOT)
                    // set the token
                    .setToken(token)

                    // set the game for when the bot is loading
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .setGame(Game.of("loading..."))

                    // add the listeners
                    .addEventListener(waiter)
                    .addEventListener(client.build())

                    // start it up!
                    .buildAsync();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (RateLimitedException e) {
            e.printStackTrace();
        }
    }

    public static void leaveServerIfUnauthorized(Guild guild) {
        long serverId = guild.getIdLong();

        if (serverId != GGD_ID) {
            System.out.println("Bot was added to unauthorized server " + serverId + "!");
            guild.leave().queue();
        }
    }

    public static JDA getJdaInstance() {
        return jdaInstance;
    }

}
