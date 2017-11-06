package net.germangamedevs.commands;

import net.germangamedevs.GGDCommand;
import net.germangamedevs.GGDCommandEvent;
import net.germangamedevs.managers.MessageManager;

import java.io.FileNotFoundException;

public class AboutCommand extends GGDCommand {

    public AboutCommand() {
        super(true);
        this.name = "about";
        this.aliases = new String[]{"info"};
        this.help = "Erfahre mehr über diesen Bot.";
    }

    @Override
    protected void executeCommand(GGDCommandEvent event) {
        try {
            event.reply(MessageManager.getMessage("about"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}