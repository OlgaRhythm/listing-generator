package listinggenerator.listinggenerator.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import listinggenerator.listinggenerator.services.SoundState;

import java.io.InputStream;

public class SoundButton extends Button {

    private final String MUS_ON_ICON_FILEPATH = "/images/muson.png";
    private final String MUS_OFF_ICON_FILEPATH = "/images/musoff.png";

    private final ImageView soundIcon = new ImageView();

    public SoundButton() {
        setPrefSize(32, 32);
        setMaxSize(32, 32);
        setMinSize(32, 32);
        setStyle("-fx-background-color: transparent;");
        setText("");

        soundIcon.setFitHeight(25);
        soundIcon.setFitWidth(25);
        soundIcon.setPreserveRatio(true);
        setGraphic(soundIcon);

        updateIcon(SoundState.isSoundOn());

        SoundState.soundOnProperty().addListener((obs, oldVal, newVal) -> {
            updateIcon(newVal);
        });

        setOnAction(event -> {
            SoundState.toggle();
        });
    }

    private void updateIcon(boolean isOn) {
        try {
            String iconPath = isOn
                    ? MUS_ON_ICON_FILEPATH
                    : MUS_OFF_ICON_FILEPATH;

            InputStream stream = getClass().getResourceAsStream(iconPath);
            if (stream == null) {
                throw new RuntimeException("Resource not found: " + iconPath);
            }
            soundIcon.setImage(new Image(stream));
            setGraphic(soundIcon);
        } catch (Exception e) {
            System.out.println("Ошибка при загрузке иконки звука: " + e.getMessage());
        }
    }
}
