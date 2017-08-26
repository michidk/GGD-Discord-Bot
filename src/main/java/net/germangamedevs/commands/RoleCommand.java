package net.germangamedevs.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.managers.GuildController;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Michael Lohr on 26-Aug-17.
 */
public class RoleCommand extends Command {

    public static final List<String> ROLES = new ArrayList<String>(Arrays.asList("Programmierer", "2D Artist", "3D Artist"));


    public RoleCommand() {
        this.name = "role";
        this.help = "Manages your roles";
    }

    @Override
    protected void execute(CommandEvent event) {
        String[] args = event.getArgs().split(" ");
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            replyHelp(event);
        } else if (args.length == 1 || args[0].equalsIgnoreCase("list")) {
            replyList(event);
        } else if (args[0].equalsIgnoreCase("add")) {
            addOrRemoveRole(event, args[1], true);
        } else if (args[0].equalsIgnoreCase("remove")) {
            addOrRemoveRole(event, args[1], false);
        }
    }

    private void replyHelp(CommandEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.orange);
        builder.setAuthor("Role Commands", null, null);
        builder.setDescription(
                "- list: list of all roles\n" +
                "- add: assigns you to a role\n" +
                "- remove: removes a role from you"
        );
        event.reply(builder.build());
    }

    private void replyList(CommandEvent event) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.orange);
        builder.setAuthor("Role List", null, null);

        StringBuilder sb = new StringBuilder(ROLES.size());
        for (String role : ROLES) {
            sb.append("- " + role + "\n");
        }

        builder.setDescription(sb.toString());
        event.reply(builder.build());
    }

    private void addOrRemoveRole(CommandEvent event, String roleName, boolean add) {
        List<Role> roles = event.getGuild().getRolesByName(roleName, true);

        if (roles.size() == 0 || !ROLES.contains(roles.get(0).getName())) {
            event.replyError("Role not found!");
            return;
        }
        Role role = roles.get(0);

        if (add)
            event.getGuild().getController().addRolesToMember(event.getMember(), roles.get(0)).reason("Roles Command").complete();
        else
            event.getGuild().getController().removeRolesFromMember(event.getMember(), roles.get(0)).reason("Roles Command").complete();

        event.reactSuccess();
    }

}