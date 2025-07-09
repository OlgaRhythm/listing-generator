package listinggenerator.listinggenerator.controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import listinggenerator.listinggenerator.ListingGenerator.DataModel;

public class Page2Controller extends MainController {
    @FXML private ComboBox<String> titleFontCombo;
    @FXML private ComboBox<Integer> titleSizeCombo;
    @FXML private ComboBox<String> codeFontCombo;
    @FXML private ComboBox<Integer> codeSizeCombo;
    @FXML private Button nextButton;
    @FXML private Button backButton;

    private DataModel dataModel;

    public void init(Stage stage, Scene scene, DataModel dataModel) {
        super.initializeCommon(stage, scene);
        this.dataModel = dataModel;
        initFontControls();
    }

    private void initFontControls() {
        titleFontCombo.getItems().addAll("Arial", "Times New Roman", "Courier New", "Verdana");
        codeFontCombo.getItems().addAll("Consolas", "Monospaced", "Courier New", "Lucida Console");
        titleSizeCombo.getItems().addAll(10, 12, 14, 16, 18, 20);
        codeSizeCombo.getItems().addAll(8, 9, 10, 11, 12);

        // Установка значений по умолчанию
        titleFontCombo.getSelectionModel().selectFirst();
        codeFontCombo.getSelectionModel().selectFirst();
        titleSizeCombo.getSelectionModel().select(2); // 14
        codeSizeCombo.getSelectionModel().select(2);  // 10

        // Восстановление предыдущих настроек
        if (dataModel.getTitleFont() != null) {
            String[] titleParts = dataModel.getTitleFont().split(",");
            if (titleParts.length == 2) {
                titleFontCombo.setValue(titleParts[0]);
                titleSizeCombo.setValue(Integer.parseInt(titleParts[1]));
            }
        }

        if (dataModel.getCodeFont() != null) {
            String[] codeParts = dataModel.getCodeFont().split(",");
            if (codeParts.length == 2) {
                codeFontCombo.setValue(codeParts[0]);
                codeSizeCombo.setValue(Integer.parseInt(codeParts[1]));
            }
        }
    }

    @FXML
    private void nextClicked() {
        dataModel.setTitleFont(titleFontCombo.getValue() + "," + titleSizeCombo.getValue());
        dataModel.setCodeFont(codeFontCombo.getValue() + "," + codeSizeCombo.getValue());
        navigateTo("page3");
    }

    @FXML
    private void backClicked() {
        navigateTo("page1");
    }
}