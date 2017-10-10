package net.germangamedevs.json;

import java.awt.Color;
import java.io.IOException;

import com.google.common.io.BaseEncoding;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Created by Michael Lohr on 10.10.2017.
 */
public class AwtColorTypeAdapter extends TypeAdapter<Color> {

    @Override
    public Color read(JsonReader in) throws IOException {
        int value = Integer.valueOf(in.nextString(), 16);
        return  new Color(value >> 16 & 255, value >> 8 & 255, value & 255);
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