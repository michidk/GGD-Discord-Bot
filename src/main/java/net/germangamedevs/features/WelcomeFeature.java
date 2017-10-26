package net.germangamedevs.features;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.germangamedevs.Feature;
import net.germangamedevs.managers.MessageManager;

import java.io.FileNotFoundException;

public class WelcomeFeature extends Feature {

    public WelcomeFeature(JDA jda) {
        super(jda);
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        User user = event.getUser();

        final MessageEmbed message;
        try {
            message = MessageManager.getMessage("welcome");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        user.openPrivateChannel().queue((channel -> {
            channel.sendMessage(message).queue();
        }));
    }

}
