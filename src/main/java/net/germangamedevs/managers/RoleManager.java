package net.germangamedevs.managers;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.germangamedevs.Main;

import java.util.List;

/**
 * Created by Michael Lohr on 10.10.2017.
 */
public class RoleManager {

    public static final String SUPPORT_ROLE_NAME = "Helfer";


    public static Role findRole(Guild guild, String name) {
        List<Role> roles = guild.getRolesByName(RoleManager.SUPPORT_ROLE_NAME, true);

        if (roles.size() <= 0) {
            throw new RuntimeException("Role with name " + name + " on Guild " + guild.getName() + " not found!");
        } else {
            return roles.get(0);
        }
    }


}
