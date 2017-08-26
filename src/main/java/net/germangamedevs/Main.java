package net.germangamedevs;

import com.google.common.io.Resources;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JDA3Handler;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.germangamedevs.commands.PingCommand;

import javax.security.auth.login.LoginException;
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
            token = Resources.toString(Resources.getResource(TOKEN_FILE_NAME), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (token == null) {
            System.err.println("Can't read token.txt. Exit");
            return;
        }

        JDA jda = null;
        try {
            jda = new JDABuilder(AccountType.BOT).setToken(token).buildBlocking();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RateLimitedException e) {
            e.printStackTrace();
        }

        if (jda == null) {
            System.err.println("Something went wrong. Exit");
            return;
        }

        CommandHandler handler = new JDA3Handler(jda);
        handler.registerCommand(new PingCommand());
    }

}
