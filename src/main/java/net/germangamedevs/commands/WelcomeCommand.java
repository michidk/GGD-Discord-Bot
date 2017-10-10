package net.germangamedevs.commands;

import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.germangamedevs.GGDCommand;
import net.germangamedevs.GGDCommandEvent;
import net.germangamedevs.managers.MessageManager;

import java.io.FileNotFoundException;

/**
 * Created by Michael Lohr on 10.10.2017.
 */
public class WelcomeCommand extends GGDCommand {

    public WelcomeCommand() {
        super(false);
        this.name = "welcome";
        this.aliases = new String[]{"hello", "rules"};
        this.help = "Willkommensnachricht.";
    }

    @Override
    protected void executeCommand(GGDCommandEvent event) {
        try {
            event.reply(MessageManager.getMessage("welcome"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}