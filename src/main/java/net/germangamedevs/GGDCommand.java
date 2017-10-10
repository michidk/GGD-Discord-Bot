package net.germangamedevs;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.entities.ChannelType;

import java.time.temporal.ChronoUnit;


/**
 * Created by Michael Lohr on 10.10.2017.
 */
public abstract class GGDCommand extends Command {

    private boolean allowedInGuild;


    public GGDCommand(boolean allowedInGuild) {
        this.allowedInGuild = allowedInGuild;

        this.guildOnly = false;
    }

    @Override
    protected void execute(CommandEvent cmdEvent) {
        GGDCommandEvent event = new GGDCommandEvent(cmdEvent.getEvent(), cmdEvent.getArgs(), cmdEvent.getClient());

        if (!allowedInGuild && event.getChannelType() == ChannelType.TEXT) {
            event.replyErrorInDM("Diesen Befehl bitte hier ausführen!");
            event.getMessage().delete().queue();
            return;
        }

        executeCommand(event);
    }

    protected abstract void executeCommand(GGDCommandEvent event);

}
