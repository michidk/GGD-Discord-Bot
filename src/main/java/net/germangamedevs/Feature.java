package net.germangamedevs;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public abstract class Feature extends ListenerAdapter {

    private JDA jda;

    public Feature(JDA jda) {
        this.jda = jda;

        jda.addEventListener(this);
    }

}
