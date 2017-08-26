package net.germangamedevs.commands;

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;

/**
 * Created by Michael Lohr on 26-Aug-17.
 */
public class PingCommand implements CommandExecutor {

    @Command(aliases = {"!ping"}, description = "Pong!")
    public String onCommand(String command, String[] args) {
        return "Pong!";
    }

}