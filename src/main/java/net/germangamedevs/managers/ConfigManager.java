package net.germangamedevs.managers;

import com.google.common.collect.Streams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import net.germangamedevs.json.AwtColorTypeAdapter;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager {
    private static final File CONFIG_FILE = new File("config.json");
    private static Config config;

    private static Gson gson = new GsonBuilder()
            .registerTypeAdapter(Color.class, new AwtColorTypeAdapter())
            .setPrettyPrinting()
            .create();

    public static void loadConfig() throws IOException {
        if (CONFIG_FILE.createNewFile()) {
            try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
                try (InputStream input = ConfigManager.class.getResourceAsStream("/config.template.json")) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                }
            }
            System.out.println("Default config saved");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            config = gson.fromJson(reader, Config.class);
        }
    }

    public static Config getConfig() {
        return config;
    }

    public static class Config {
        private String token;
        private long server;

        public String getToken() {
            return token;
        }

        public long getServer() {
            return server;
        }
    }
}
