package net.germangamedevs.commands;

import net.germangamedevs.GGDCommand;
import net.germangamedevs.GGDCommandEvent;

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