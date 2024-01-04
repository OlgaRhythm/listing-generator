module listinggenerator.listinggenerator {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;


    opens listinggenerator.listinggenerator to javafx.fxml;
    exports listinggenerator.listinggenerator;
    exports listinggenerator.listinggenerator.FileIO;
    opens listinggenerator.listinggenerator.FileIO to javafx.fxml;
}