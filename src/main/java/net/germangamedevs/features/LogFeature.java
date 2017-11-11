package net.germangamedevs.features;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.MessageBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.guild.GuildBanEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberLeaveEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberNickChangeEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.core.events.user.UserAvatarUpdateEvent;
import net.germangamedevs.Feature;
import net.germangamedevs.managers.ConfigManager;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogFeature extends Feature {
    private SimpleDateFormat dateFormat;

    public LogFeature(JDA jda) {
        super(jda);
        dateFormat = new SimpleDateFormat(ConfigManager.getConfig().getDateFormat());
    }

    private TextChannel getLog(Guild guild) {
        return guild.getTextChannelById(ConfigManager.getConfig().getLog());
    }

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        TextChannel log = getLog(event.getGuild());
        long creation = (event.getMember().getUser().getIdLong() >> 22) + 1420070400000L;
        log.sendMessage(new MessageBuilder()
                .append(event.getMember().getAsMention())
                .append(" ist dem Server beigetreten.")
                .setEmbed(new EmbedBuilder()
                        .addField("Name", event.getUser().getName(), false)
                        .addField("Erstellungsdatum", dateFormat.format(new Date(creation)), false)
                        .setThumbnail(event.getUser().getEffectiveAvatarUrl())
                        .setColor(System.currentTimeMillis() - creation > 86400000 ? Color.lightGray : Color.yellow)
                        .build())
                .build()).queue();
    }

    @Override
    public void onGuildMemberLeave(GuildMemberLeaveEvent event) {
        TextChannel log = getLog(event.getGuild());
        log.sendMessage(new MessageBuilder()
                .append(event.getMember().getAsMention())
                .append(" hat den Server verlassen.")
                .build()).queue();
    }

    @Override
    public void onGuildMemberNickChange(GuildMemberNickChangeEvent event) {
        TextChannel log = getLog(event.getGuild());
        log.sendMessage(new MessageBuilder()
                .append(event.getMember().getAsMention())
                .append(" hat seinen Nickname verändert.")
                .setEmbed(new EmbedBuilder()
                        .addField("Von", event.getPrevNick() != null ? event.getPrevNick() : event.getUser().getName(), true)
                        .addField("Zu", event.getNewNick() != null ? event.getNewNick() : event.getUser().getName(), true)
                        .setColor(Color.lightGray)
                        .build())
                .build()).queue();
    }
}
