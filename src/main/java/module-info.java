module listinggenerator.listinggenerator {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.ooxml;
    requires java.desktop;


    opens listinggenerator.listinggenerator to javafx.fxml;
    exports listinggenerator.listinggenerator;
    exports listinggenerator.listinggenerator.services;
    opens listinggenerator.listinggenerator.services to javafx.fxml;
    exports listinggenerator.listinggenerator.controllers;
    opens listinggenerator.listinggenerator.controllers to javafx.fxml;
}