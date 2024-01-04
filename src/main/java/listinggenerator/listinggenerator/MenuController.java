package listinggenerator.listinggenerator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.DirectoryChooser;
import listinggenerator.listinggenerator.FileIO.ListingGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
    private Text errorMessageDir;

    @FXML
    private ImageView pic;

    @FXML
    private Button start;

    @FXML
    private Button mus;

    private Stage stage;

    public void start(Stage stage, Scene scene) {
        stage.setTitle("Генератор листинга");

        loadPic();

        stage.setScene(scene);
        stage.show();
        directory.setEditable(false);
        this.stage = stage;

    }

    private void loadPic() {
        // Загрузка изображения из файла
        InputStream stream = getClass().getResourceAsStream("/anime.jpg");
        if (stream == null) {
            System.out.println("Ошибка при загрузке изображения");
            return;
        }
        Image image = new Image(stream);
        // Установка изображения в ImageView
        pic.setImage(image);
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

    }

    @FXML
    void startClicked(ActionEvent event) {
        String directoryValue = directory.getText();
        String docNameValue = docName.getText();
        errorMessage.setText(ListingGenerator.generate(directoryValue, docNameValue));
    }
}