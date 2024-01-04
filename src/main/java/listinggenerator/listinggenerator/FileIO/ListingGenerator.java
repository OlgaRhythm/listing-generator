package listinggenerator.listinggenerator.FileIO;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;

public class ListingGenerator {
    public static String generate(String directoryValue, String docNameValue) {
        File directory = new File(directoryValue);
        if (directory.exists() && directory.isDirectory()) {
            if (checkDocName(docNameValue)) {
                processFilesInDirectory(directory, docNameValue);
                return "Успешно! ^_^";
            } else {
                return "Ошибка в имени файла";
            }
        } else {
            return "Директория не найдена или не является директорией";
        }
    }

    private static boolean checkDocName(String docNameValue) {
        return true;
    }

    private static void processFilesInDirectory(File directory, String docNameValue) { // fileFormat = ".java"
        // Создание нового документа
        XWPFDocument document = new XWPFDocument();

        processFilesRecursive(directory, document);

        // Сохранение документа в файл
        TextWriter.writeToDocument(document, docNameValue);
    }

    private static void processFilesRecursive(File directory, XWPFDocument document) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    // Если текущий файл является директорией, рекурсивно обходим её содержимое
                    processFilesRecursive(file, document);
                } else if (file.isFile() && file.getName().endsWith(".java")) {
                    // Чтение только .java файлов и добавление их содержимого в документ
                    String content = CodeReader.readCodeFromFile(file);
                    if (content != null) {
                        TextWriter.addContentToDocument(document, file.getName(), content);
                    }
                }
            }
        }
    }

}
