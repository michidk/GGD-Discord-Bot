package net.germangamedevs.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.germangamedevs.GGDCommand;
import net.germangamedevs.GGDCommandEvent;

import java.time.temporal.ChronoUnit;

/**
 * Created by Michael Lohr on 26-Aug-17.
 */
public class PingCommand extends GGDCommand {

    public PingCommand() {
        super(true);
        this.name = "ping";
        this.aliases = new String[]{"test", "perf"};
        this.help = "Führt einen Ping-Test vom Bot zum Discord-Server aus.";
    }

    @Override
    protected void executeCommand(GGDCommandEvent event) {
        event.reply("Ping: ...", m -> {
            m.editMessage("Ping: " + event.getMessage().getCreationTime().until(m.getCreationTime(), ChronoUnit.MILLIS) + "ms | Websocket: " + event.getJDA().getPing() + "ms").queue();
        });
    }

}