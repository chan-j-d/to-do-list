package settings;

public class WindowSettings {

    private final double prefHeight;
    private final double prefWidth;

    public WindowSettings(double prefHeight, double prefWidth) {
        this.prefHeight = prefHeight;
        this.prefWidth = prefWidth;
    }

    public double getPrefHeight() {
        return prefHeight;
    }

    public double getPrefWidth() {
        return prefWidth;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof WindowSettings)) {
            return false;
        }

        WindowSettings ws = (WindowSettings) o;
        return prefWidth == ws.prefWidth
                && prefHeight == ws.prefHeight;
    }
}
