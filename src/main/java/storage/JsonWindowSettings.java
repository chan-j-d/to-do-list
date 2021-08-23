package storage;

import com.fasterxml.jackson.annotation.JsonProperty;
import settings.WindowSettings;

public class JsonWindowSettings implements JsonStorer<WindowSettings> {

    private final double prefHeight;
    private final double prefWidth;

    public JsonWindowSettings(@JsonProperty("prefHeight") double prefHeight,
            @JsonProperty("prefWidth") double prefWidth) {
        this.prefHeight = prefHeight;
        this.prefWidth = prefWidth;
    }

    public WindowSettings toJavaType() {
        return new WindowSettings(prefHeight, prefWidth);
    }
}
