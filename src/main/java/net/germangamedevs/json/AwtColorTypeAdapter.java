package net.germangamedevs.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.awt.*;
import java.io.IOException;

public class AwtColorTypeAdapter extends TypeAdapter<Color> {

    @Override
    public Color read(JsonReader in) throws IOException {
        int value = Integer.valueOf(in.nextString(), 16);
        return new Color(value >> 16 & 255, value >> 8 & 255, value & 255);
    }

    @Override
    public void write(JsonWriter out, Color color) throws IOException {
        if (color == null) {
            out.nullValue();
            return;
        }

        out.value(Integer.toHexString(color.getRGB()));
    }

}