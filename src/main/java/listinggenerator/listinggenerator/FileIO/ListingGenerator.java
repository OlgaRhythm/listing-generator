package listinggenerator.listinggenerator.FileIO;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ListingGenerator {
    public static void generate(String directoryValue, String fileFormat) {
        File directory = new File(directoryValue);
        if (directory.exists() && directory.isDirectory()) {
            // обработка файлов с кодом
            processFilesInDirectory(directory, fileFormat);
        } else {
            System.out.println("Директория не найдена или не является директорией");
        }
    }

    private static void processFilesInDirectory(File directory, String fileFormat) { // fileFormat = ".java"
        // Создание нового документа
        XWPFDocument document = new XWPFDocument();

        // Получение всех файлов из директории
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(fileFormat)) { // Чтение только .java файлов
                    String content = CodeReader.readCodeFromFile(file);
                    if (content != null) {
                        // запись в документ
                        TextWriter.addContentToDocument(document, file.getName(), content);
                    }
                }
            }
            // Сохранение документа в файл
            TextWriter.writeToDocument(document);
        }
    }
}
