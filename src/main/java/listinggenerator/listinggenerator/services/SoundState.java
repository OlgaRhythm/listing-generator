package listinggenerator.listinggenerator.services;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class SoundState {
    private static BooleanProperty soundOn = new SimpleBooleanProperty(false);
    private static final Sound music = new Sound("music.wav", true);

    public static void toggle() {
        boolean newValue = !soundOn.get();
        soundOn.set(newValue);

        if (newValue)
            music.playSound();
        else
            music.stopSound();
    }

    public static boolean isSoundOn() {
        return soundOn.get();
    }

    public static void setSoundOn(boolean value) {
        soundOn.set(value);
        if (value)
            music.playSound();
        else
            music.stopSound();
    }

    public static BooleanProperty soundOnProperty() {
        return soundOn;
    }
}
