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
    @FXML private CheckBox jsCheck;
    @FXML private CheckBox cssCheck;
    @FXML private CheckBox swiftCheck;
    @FXML private CheckBox tsCheck;

    @FXML private CheckBox cppCheck;
    @FXML private CheckBox pyCheck;
    @FXML private CheckBox sqlCheck;
    @FXML private CheckBox csharpCheck;
    @FXML private CheckBox ktCheck;

    @FXML private CheckBox cCheck;
    @FXML private CheckBox phpCheck;
    @FXML private CheckBox jsonCheck;
    @FXML private CheckBox csCheck;
    @FXML private CheckBox php2Check;

    @FXML private CheckBox hsCheck;
    @FXML private CheckBox htmlCheck;
    @FXML private CheckBox goCheck;
    @FXML private CheckBox rbCheck;

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
        if (jsCheck.isSelected()) dataModel.getFormats().add(".js");
        if (cssCheck.isSelected()) dataModel.getFormats().add(".css");
        if (swiftCheck.isSelected()) dataModel.getFormats().add(".swift");
        if (tsCheck.isSelected()) dataModel.getFormats().add(".ts");

        if (cppCheck.isSelected()) dataModel.getFormats().add(".cpp");
        if (pyCheck.isSelected()) dataModel.getFormats().add(".py");
        if (sqlCheck.isSelected()) dataModel.getFormats().add(".sql");
        if (csharpCheck.isSelected()) dataModel.getFormats().add(".csharp");
        if (ktCheck.isSelected()) dataModel.getFormats().add(".kt");

        if (cCheck.isSelected()) dataModel.getFormats().add(".c");
        if (phpCheck.isSelected()) dataModel.getFormats().add(".php");
        if (jsonCheck.isSelected()) dataModel.getFormats().add(".json");
        if (csCheck.isSelected()) dataModel.getFormats().add(".cs");
        if (php2Check.isSelected()) dataModel.getFormats().add(".php");

        if (hsCheck.isSelected()) dataModel.getFormats().add(".hs");
        if (htmlCheck.isSelected()) dataModel.getFormats().add(".html");
        if (goCheck.isSelected()) dataModel.getFormats().add(".go");
        if (rbCheck.isSelected()) dataModel.getFormats().add(".rb");

        navigateTo("page2");
    }
}