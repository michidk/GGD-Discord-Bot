package net.germangamedevs.commands;

import net.germangamedevs.GGDCommand;
import net.germangamedevs.GGDCommandEvent;

import java.time.temporal.ChronoUnit;

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