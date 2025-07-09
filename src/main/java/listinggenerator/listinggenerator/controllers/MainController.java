package listinggenerator.listinggenerator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import listinggenerator.listinggenerator.ListingGenerator;
import listinggenerator.listinggenerator.services.DocGenerator;
import listinggenerator.listinggenerator.services.Sound;

import java.io.InputStream;

public class MainController {

    @FXML
    protected ImageView pic;
    @FXML
    protected Button mus;

    protected Sound music;
    protected boolean musOn = false;
    protected Stage stage;
    protected Scene scene;

    // Общая инициализация для всех контроллеров
    public void initializeCommon(Stage stage, Scene scene) {
        this.stage = stage;
        this.scene = scene;

        pic.setImage(loadPic("anime.png"));
        loadPicMusButton("musoff.png");
        musOn = false;
        music = new Sound("music.wav", true);
    }

    // Загрузка изображения
    protected Image loadPic(String fileName) {
        InputStream stream = getClass().getResourceAsStream("/images/"+fileName);
        if (stream == null) {
            System.out.println("Ошибка при загрузке изображения");
            return null;
        }
        return new Image(stream);
    }

    // Установка изображения на кнопку музыки
    protected void loadPicMusButton(String fileName) {
        ImageView imageView = new ImageView(loadPic(fileName));

        imageView.setFitHeight(25);
        imageView.setFitWidth(25);

        mus.setText("");
        mus.setStyle("-fx-background-color: transparent;");
        mus.setGraphic(imageView);

        mus.setPrefSize(32, 32);
        mus.setMaxSize(32, 32);
        mus.setMinSize(32, 32);
    }

    // Обработчик клика по кнопке музыки
    @FXML
    protected void musClicked(ActionEvent event) {
        if (musOn) {
            musOn = false;
            loadPicMusButton("musoff.png");
            music.stopSound();
        } else {
            musOn = true;
            loadPicMusButton("muson.png");
            music.playSound();
        }
    }

    // Метод для перехода между страницами
    protected void navigateTo(String page) {
        ListingGenerator.showPage(page);
    }
}