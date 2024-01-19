package listinggenerator.listinggenerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import listinggenerator.listinggenerator.FileIO.ListingGenerator;

import java.io.File;
import java.io.InputStream;

public class MenuController {

    @FXML
    private Button chooseDir;

    @FXML
    private TextField directory;

    @FXML
    private TextField docName;

    @FXML
    private Text errorMessage;

    @FXML
    private ImageView pic;

    @FXML
    private Button start;

    @FXML
    private Button mus;

    Sound music;
    //Sound butt
    boolean musOn = false;

    private Stage stage;

    public void start(Stage stage, Scene scene) {
        stage.setTitle("Генератор листинга");

        // Установка изображения в ImageView
        pic.setImage(loadPic("anime.jpg"));
        // Установка изображения в кнопку музыки
        loadPicMusButton("musoff.png");
        musOn = false;

        // объект для воспроизведения музыки
        music = new Sound("music.wav", true);

        stage.setScene(scene);
        stage.show();
        directory.setEditable(false);
        this.stage = stage;

    }

    private Image loadPic(String fileName) {
        // Загрузка изображения из файла
        InputStream stream = getClass().getResourceAsStream("/images/"+fileName);
        if (stream == null) {
            System.out.println("Ошибка при загрузке изображения");
            return null;
        }
        return new Image(stream);
    }

    private void loadPicMusButton(String fileName) {
        ImageView imageView = new ImageView(loadPic(fileName));
        mus.setText("");
        mus.setStyle("-fx-background-color: transparent;");
        mus.setGraphic(imageView);
    }

    @FXML
    void chooseDirClicked(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выберите директорию проекта");

        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            directory.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    void musClicked(ActionEvent event) {
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

    @FXML
    void startClicked(ActionEvent event) {
        errorMessage.setText("");
        String directoryValue = directory.getText();
        String docNameValue = docName.getText();
        errorMessage.setText(ListingGenerator.generate(directoryValue, docNameValue));
    }
}