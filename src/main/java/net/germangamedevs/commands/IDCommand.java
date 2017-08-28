package net.germangamedevs.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;

/**
 * Created by Michael Lohr on 26-Aug-17.
 */
public class IDCommand extends Command {

    public IDCommand() {
        this.name = "id";
        this.help = "returns your Discord user id";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("ID: " + event.getAuthor().getIdLong());
    }

}