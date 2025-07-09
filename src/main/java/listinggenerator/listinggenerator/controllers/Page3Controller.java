package listinggenerator.listinggenerator.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import listinggenerator.listinggenerator.services.DocGenerator;
import listinggenerator.listinggenerator.ListingGenerator.DataModel;

import java.io.File;

public class Page3Controller extends MainController {
    @FXML private TextField saveDirField;
    @FXML private TextField docNameField;
    @FXML private Button finishButton;
    @FXML private Button backButton;
    @FXML private Text errorMessage;

    private DataModel dataModel;

    public void init(Stage stage, Scene scene, DataModel dataModel) {
        super.initializeCommon(stage, scene);
        this.dataModel = dataModel;

        // Восстановление предыдущих значений
        if (dataModel.getSaveDir() != null) {
            saveDirField.setText(dataModel.getSaveDir());
        }
        if (dataModel.getDocName() != null) {
            docNameField.setText(dataModel.getDocName());
        }
    }

    @FXML
    private void chooseSaveDirClicked() {
        DirectoryChooser chooser = new DirectoryChooser();
        File dir = chooser.showDialog(stage);
        if (dir != null) {
            saveDirField.setText(dir.getAbsolutePath());
            dataModel.setSaveDir(dir.getAbsolutePath());
        }
    }

    @FXML
    private void finishClicked() {
        dataModel.setDocName(docNameField.getText());

        // Вызов генератора с новыми параметрами
        errorMessage.setText(DocGenerator.generate(
                dataModel.getProjectDir(),
                dataModel.getSaveDir(),
                dataModel.getDocName(),
                dataModel.getFormats(),
                dataModel.getTitleFont(),
                dataModel.getCodeFont()
        ));
    }

    @FXML
    private void backClicked() {
        navigateTo("page2");
    }
}