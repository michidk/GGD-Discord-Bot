package net.germangamedevs.features;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.events.guild.GuildJoinEvent;
import net.germangamedevs.Feature;
import net.germangamedevs.Main;

/**
 * Created by Michael Lohr on 10.10.2017.
 */
public class AuthorizedServerCheckFeature extends Feature {

    public AuthorizedServerCheckFeature(JDA jda) {
        super(jda);
    }

    @Override
    public void onGuildJoin(GuildJoinEvent event) {
        Main.leaveServerIfUnauthorized(event.getGuild());
    }

    @Override
    public void onReady(ReadyEvent event) {

        for (Guild guild : event.getJDA().getGuilds()) {
            Main.leaveServerIfUnauthorized(guild);
        }

    }

}
