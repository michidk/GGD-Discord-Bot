package net.germangamedevs;

import com.jagrosh.jdautilities.commandclient.CommandClient;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

/**
 * Created by Michael Lohr on 10.10.2017.
 */
public class GGDCommandEvent extends CommandEvent {

    /**
     * Constructor for a CommandEvent.
     * <p>
     * <p><b>You should not call this!</b>
     * <br>It is a generated wrapper for a {@link MessageReceivedEvent MessageReceivedEvent}.
     *
     * @param event  The initial MessageReceivedEvent
     * @param args   The String arguments after the command call
     * @param client The {@link CommandClient CommandClient}
     */
    public GGDCommandEvent(MessageReceivedEvent event, String args, CommandClient client) {
        super(event, args, client);
    }

    public String[] getArgsArray() {
        return this.getArgs().split(" ");
    }

    public void replySuccessInDM(String message) {
        this.replyInDM(this.getClient().getSuccess() + " " + message);
    }

    public void replyErrorInDM(String message) {
        this.replyInDM(this.getClient().getError() + " " + message);
    }

}
