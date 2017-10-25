package net.germangamedevs.commands;

import net.germangamedevs.GGDCommand;
import net.germangamedevs.GGDCommandEvent;
import net.germangamedevs.managers.MessageManager;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashSet;

public class CatCommand extends GGDCommand {
    private final String API_URL = "http://thecatapi.com/api/images/get?format=xml";
    //Use a HashSet containing all categories, because it's faster compared to valueOf & values()
    private HashSet<String> catCategories;

    public CatCommand() {
        super(true);
        this.name = "cat";
        this.aliases = new String[]{"catpic"};
        this.help = "Postet ein Katzen Bild - `!cat categories` zum Auflisten aller Kategorien";

        this.catCategories = new HashSet<>();
        for (CatCategory catCategory : CatCategory.values()) {
            this.catCategories.add(catCategory.name);
        }
    }

    @Override
    protected void executeCommand(GGDCommandEvent event) {
        if(event.getArgsArray().length > 0) {
            if(event.getArgsArray()[0].equalsIgnoreCase("categories")) {
                try {
                    event.reply(MessageManager.getMessage("catcategories"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                return;
            } else if(catCategories.contains(event.getArgsArray()[0].toLowerCase())) {
                event.reply(getCat(CatCategory.valueOf(event.getArgsArray()[0].toUpperCase())));
                return;
            }
        }
        event.reply(getCat(null));
    }

    public String getCat(CatCategory catCategory) {
        String TargetURL = API_URL;
        if(catCategory != null)
            TargetURL += "&category=" + catCategory.toString();

        try {
            InputStream inputStream = new URL(TargetURL).openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            int cp;
            while ((cp = bufferedReader.read()) != -1) {
                sb.append((char) cp);
            }
            return sb.toString().substring(sb.toString().indexOf("<url>") + "<url>".length(), sb.toString().indexOf("</url>"));
        } catch (IOException e) {
            e.printStackTrace();
            return "We weren't able to retrieve a picture from the API, sorry! :(";
        }
    }

    private enum CatCategory {
        HATS("hats"),
        SPACE("space"),
        FUNNY("funny"),
        SUNGLASSES("sunglasses"),
        BOXES("boxes"),
        CATURDAY("caturday"),
        TIES("ties"),
        DREAM("dream"),
        KITTENS("kittens"),
        SINKS("sinks"),
        CLOTHES("clothes");

        private String name;

        CatCategory(String name) {
            this.name = name;
        }

    }

}

