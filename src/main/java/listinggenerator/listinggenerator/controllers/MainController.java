package listinggenerator.listinggenerator.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import listinggenerator.listinggenerator.ListingGenerator;

import java.io.InputStream;

public class MainController {

    private final String LEFT_PIC_FILEPATH = "/images/anime.png";

    @FXML
    protected ImageView pic;

    @FXML
    protected AnchorPane rightPane;

    protected Stage stage;
    protected Scene scene;

    // Общая инициализация для всех контроллеров
    public void initializeCommon(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;
        pic.setImage(loadPic(LEFT_PIC_FILEPATH));

        SoundButton button = new SoundButton();

        if (!rightPane.getChildren().contains(button)) {
            button.setLayoutX(397);
            button.setLayoutY(12);
            rightPane.getChildren().add(button);
        }
    }

    // Загрузка изображения
    protected Image loadPic(String fileName) {
        InputStream stream = getClass().getResourceAsStream(fileName);
        if (stream == null) {
            System.out.println("Ошибка при загрузке изображения");
            return null;
        }
        return new Image(stream);
    }

    // Метод для перехода между страницами
    protected void navigateTo(String page) {
        ListingGenerator.showPage(page);
    }
}