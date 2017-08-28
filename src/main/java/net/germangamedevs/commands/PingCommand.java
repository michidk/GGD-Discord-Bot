package net.germangamedevs.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;

import java.time.temporal.ChronoUnit;

/**
 * Created by Michael Lohr on 26-Aug-17.
 */
public class PingCommand extends Command {

    public PingCommand() {
        this.name = "ping";
        this.aliases = new String[]{"test", "perf"};
        this.help = "returns the ping";
    }

    @Override
    protected void execute(CommandEvent event) {
        event.reply("Ping: ...", m -> {
            m.editMessage("Ping: " + event.getMessage().getCreationTime().until(m.getCreationTime(), ChronoUnit.MILLIS) + "ms | Websocket: " + event.getJDA().getPing() + "ms").queue();
        });
    }

}