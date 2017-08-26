package net.germangamedevs;

import com.google.common.io.Resources;
import com.jagrosh.jdautilities.commandclient.CommandClientBuilder;
import com.jagrosh.jdautilities.commandclient.examples.AboutCommand;
import com.jagrosh.jdautilities.waiter.EventWaiter;
import net.dv8tion.jda.core.*;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.germangamedevs.commands.IDCommand;
import net.germangamedevs.commands.PingCommand;
import net.germangamedevs.commands.RoleCommand;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Michael Lohr on 26-Aug-17.
 */
public class Main {

    private static final String TOKEN_FILE_NAME = "token.txt";

    public static void main(String [] args){

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

        // define an eventwaiter, dont forget to add this to the JDABuilder!
        EventWaiter waiter = new EventWaiter();

        // define a command client
        CommandClientBuilder client = new CommandClientBuilder();

        // The default is "Type !!help" (or whatver prefix you set)
        client.useDefaultGame();

        // sets the owner of the bot
        client.setOwnerId("98126233753120768");

        // sets emojis used throughout the bot on successes, warnings, and failures
        client.setEmojis("✅", "❗", "❌");

        // sets the bot prefix
        client.setPrefix("!!");

        // adds commands
        client.addCommands(
                // command to show information about the bot
                new AboutCommand(
                        Color.BLUE,
                        "A bot for the German Game Developers Discord",
                        new String[]{"Swag"}
                ),

                // command to check bot latency
                new PingCommand(),
                // command to check the user ID
                new IDCommand(),
                new RoleCommand()
        );

        // start getting a bot account set up
        try {
            new JDABuilder(AccountType.BOT)
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

}
