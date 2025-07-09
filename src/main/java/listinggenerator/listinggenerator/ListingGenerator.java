package listinggenerator.listinggenerator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import listinggenerator.listinggenerator.controllers.Page1Controller;
import listinggenerator.listinggenerator.controllers.Page2Controller;
import listinggenerator.listinggenerator.controllers.Page3Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ListingGenerator extends Application {
    private static Stage primaryStage;
    private static final Map<String, Pane> pageCache = new HashMap<>();
    private static final DataModel dataModel = new DataModel();
    private static Scene mainScene;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("Генератор листинга");
        primaryStage.setResizable(false);

        // Создаем главную сцену
        mainScene = new Scene(new Pane(), 750, 400);
        primaryStage.setScene(mainScene);

        // Предварительная загрузка всех страниц
        loadAllPages();

        // Показываем первую страницу
        showPage("page1");

        primaryStage.show();
    }

    private void loadAllPages() throws IOException {
        pageCache.put("page1", loadPage("page1.fxml", Page1Controller.class));
        pageCache.put("page2", loadPage("page2.fxml", Page2Controller.class));
        pageCache.put("page3", loadPage("page3.fxml", Page3Controller.class));
    }

    private Pane loadPage(String fxmlFile, Class<?> controllerClass) throws IOException {
        FXMLLoader loader = new FXMLLoader(ListingGenerator.class.getResource(fxmlFile));
        Pane pane = loader.load();

        // Передаем параметры контроллерам
        Object controller = loader.getController();
        if (controller instanceof Page1Controller) {
            ((Page1Controller) controller).init(primaryStage, mainScene, dataModel);
        } else if (controller instanceof Page2Controller) {
            ((Page2Controller) controller).init(primaryStage, mainScene, dataModel);
        } else if (controller instanceof Page3Controller) {
            ((Page3Controller) controller).init(primaryStage, mainScene, dataModel);
        }

        return pane;
    }

    public static void showPage(String pageName) {
        Pane pane = pageCache.get(pageName);
        if (pane != null) {
            mainScene.setRoot(pane);

            // Обновляем заголовок окна
            switch (pageName) {
                case "page1" -> primaryStage.setTitle("Генератор листинга - Шаг 1");
                case "page2" -> primaryStage.setTitle("Генератор листинга - Шаг 2");
                case "page3" -> primaryStage.setTitle("Генератор листинга - Шаг 3");
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }

    // Модель данных для передачи между страницами
    public static class DataModel {
        private String projectDir;
        private String saveDir;
        private String docName;
        private Set<String> formats = new HashSet<>();
        private String titleFont;
        private String codeFont;

        // Геттеры и сеттеры
        public String getProjectDir() { return projectDir; }
        public void setProjectDir(String projectDir) { this.projectDir = projectDir; }
        public String getSaveDir() { return saveDir; }
        public void setSaveDir(String saveDir) { this.saveDir = saveDir; }
        public String getDocName() { return docName; }
        public void setDocName(String docName) { this.docName = docName; }
        public Set<String> getFormats() { return formats; }
        public void setFormats(Set<String> formats) { this.formats = formats; }
        public String getTitleFont() { return titleFont; }
        public void setTitleFont(String titleFont) { this.titleFont = titleFont; }
        public String getCodeFont() { return codeFont; }
        public void setCodeFont(String codeFont) { this.codeFont = codeFont; }
    }
}