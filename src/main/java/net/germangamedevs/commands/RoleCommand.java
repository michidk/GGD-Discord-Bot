package net.germangamedevs.commands;

import com.jagrosh.jdautilities.commandclient.Command;
import com.jagrosh.jdautilities.commandclient.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Role;

import java.awt.*;
import java.util.List;

/**
 * Created by Michael Lohr on 26-Aug-17.
 */
public class RoleCommand extends Command {

    public static final Color ROLE_COLOR = new Color(52, 152, 219);

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
            addOrRemoveRole(event, args, true);
        } else if (args[0].equalsIgnoreCase("remove")) {
            addOrRemoveRole(event, args, false);
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

        List<Role> roles = event.getGuild().getRoles();

        StringBuilder sb = new StringBuilder(roles.size());
        roles.stream()
                .filter(r -> r.getColor() != null)
                .filter(r -> r.getColor().equals(ROLE_COLOR))
                .forEach(r -> sb.append("- " + r.getName() + "\n"));

        builder.setDescription(sb.toString());
        event.reply(builder.build());
    }

    private void addOrRemoveRole(CommandEvent event, String[] args, boolean add) {
        StringBuilder sb = new StringBuilder((args.length - 1) * 2);
        for (int i = 1; i < args.length; i++) {
            sb.append(args[i]);
            if (i != args.length - 1)
                sb.append(" ");
        }
        String roleName = sb.toString();

        List<Role> roles = event.getGuild().getRolesByName(roleName, true);

        if (roles.size() == 0) {
            event.replyError("Role " + roleName + " not found!");
            return;
        }

        Role role = roles.get(0);
        if (!role.getColor().equals(ROLE_COLOR)) {
            event.replyError("Role " + roleName + "not allowed!");
            return;
        }

        if (add)
            event.getGuild().getController().addRolesToMember(event.getMember(), role).reason("Roles Command").complete();
        else
            event.getGuild().getController().removeRolesFromMember(event.getMember(), role).reason("Roles Command").complete();

        event.reactSuccess();
    }

}