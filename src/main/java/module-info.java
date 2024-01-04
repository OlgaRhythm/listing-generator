module listinggenerator.listinggenerator {
    requires javafx.controls;
    requires javafx.fxml;


    opens listinggenerator.listinggenerator to javafx.fxml;
    exports listinggenerator.listinggenerator;
}