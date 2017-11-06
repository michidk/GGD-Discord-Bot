package net.germangamedevs.managers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.germangamedevs.json.AwtColorTypeAdapter;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class MessageManager {

    private static final String FOLDER = "messages/";
    private static final String SUFFIX = ".txt";


    private static Gson gson = new GsonBuilder().registerTypeAdapter(Color.class, new AwtColorTypeAdapter()).create();

    private static HashMap<String, MessageEmbed> cache = new HashMap<>();


    public static MessageEmbed getMessage(String id) throws FileNotFoundException {
        if (cache.containsKey(id))
            return cache.get(id);

        List<String> lines = null;
        ClassLoader classLoader = MessageManager.class.getClassLoader();
        Path path = null;
        try {
            path = Paths.get(classLoader.getResource(FOLDER + id + SUFFIX).toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        try {
            lines = Files.readAllLines(path, Charset.defaultCharset());
        } catch (IOException e) {
            throw new FileNotFoundException("Message " + id + " not in " + path + " found!");
        }

        String str = lines.stream().collect(Collectors.joining("\n"));

        MessageEmbed message = gson.fromJson(str, EmbedBuilder.class).build();

        cache.put(id, message);
        return message;
    }

}
