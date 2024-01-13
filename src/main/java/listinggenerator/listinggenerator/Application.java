package listinggenerator.listinggenerator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 400);
        stage.setResizable(false);
        MenuController controller = fxmlLoader.getController();
        controller.start(stage, scene);
    }

    public static void main(String[] args) {
        launch();
    }
}