package listinggenerator.listinggenerator.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import listinggenerator.listinggenerator.ListingGenerator.DataModel;

import java.io.File;

public class Page1Controller extends MainController {
    @FXML private TextField directoryField;
    @FXML private CheckBox javaCheck;
    @FXML private CheckBox cppCheck;
    @FXML private CheckBox pythonCheck;
    @FXML private CheckBox txtCheck;
    @FXML private Button nextButton;
    @FXML private Button backButton;

    private DataModel dataModel;

    public void init(Stage stage, Scene scene, DataModel dataModel) {
        super.initializeCommon(stage, scene);
        this.dataModel = dataModel;
        backButton.setDisable(true);

        // Восстановление предыдущих значений
        if (dataModel.getProjectDir() != null) {
            directoryField.setText(dataModel.getProjectDir());
            nextButton.setDisable(false);
        }
    }

    @FXML
    private void chooseDirClicked() {
        DirectoryChooser chooser = new DirectoryChooser();
        File dir = chooser.showDialog(stage);
        if (dir != null) {
            directoryField.setText(dir.getAbsolutePath());
            dataModel.setProjectDir(dir.getAbsolutePath());
            nextButton.setDisable(false);
        }
    }

    @FXML
    private void nextClicked() {
        // Сохраняем выбранные форматы
        dataModel.getFormats().clear();
        if (javaCheck.isSelected()) dataModel.getFormats().add(".java");
        if (cppCheck.isSelected()) dataModel.getFormats().add(".cpp");
        if (pythonCheck.isSelected()) dataModel.getFormats().add(".py");
        if (txtCheck.isSelected()) dataModel.getFormats().add(".txt");

        navigateTo("page2");
    }
}