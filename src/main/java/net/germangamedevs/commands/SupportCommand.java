package net.germangamedevs.commands;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Role;
import net.germangamedevs.GGDCommand;
import net.germangamedevs.GGDCommandEvent;
import net.germangamedevs.Main;
import net.germangamedevs.managers.RoleManager;

import java.awt.*;
import java.util.List;

public class SupportCommand extends GGDCommand {

    private Role supportRole;


    public SupportCommand() {
        super(false);
        this.name = "helfer";
        this.aliases = new String[]{"support"};
        this.help = "Gibt dir die @Helfer Rolle, die jedem Mitglied erlaubt dich zu Erwähnen und um Hilfe zu bitten.";
    }

    @Override
    protected void executeCommand(GGDCommandEvent event) {

        Guild guild = event.getJDA().getGuildById(Main.GGD_ID);
        Member member = guild.getMember(event.getAuthor());

        if (supportRole == null)
            supportRole = RoleManager.findRole(guild, RoleManager.SUPPORT_ROLE_NAME);

        List<Role> roles = member.getRoles();

        if (!roles.contains(supportRole)) {
            guild.getController().addSingleRoleToMember(member, supportRole).queue();

            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.green);
            builder.setTitle("Erfolgreich als Helfer registriert!");
            builder.setDescription(
                    "Du hast dich als Helfer eingetragen und wirst nun Benachrichtigt wenn ein Mitglied Hilfe zu einem Themengebiet braucht, das du dir zugewie\u00DFen hast.\n" +
                            "F\u00FChre erneut dieses Kommando erneut aus, um nicht mehr Benachrichtigt zu werden."
            );

            event.reply(builder.build());
        } else {
            guild.getController().removeSingleRoleFromMember(member, supportRole).queue();

            EmbedBuilder builder = new EmbedBuilder();
            builder.setColor(Color.green);
            builder.setTitle("Du bist nun kein Helfer mehr.");
            builder.setDescription(
                    "Du wirst nun nicht mehr benachrichtigt falls ein Mitglied Hilfe braucht.\n" +
                            "F\u00FChre dieses Kommando erneut aus um dich als Helfer zu registrieren."
            );

            event.reply(builder.build());
        }

    }

}