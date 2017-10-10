package net.germangamedevs.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.germangamedevs.GGDCommand;
import net.germangamedevs.GGDCommandEvent;

/**
 * Created by Michael Lohr on 26-Aug-17.
 */
public class IDCommand extends GGDCommand {

    public IDCommand() {
        super(false);
        this.name = "id";
        this.help = "Gibt deine Discord Nutzer-ID zurück.";
    }

    @Override
    protected void executeCommand(GGDCommandEvent event) {
        event.reply("ID: " + event.getAuthor().getIdLong());
    }

}