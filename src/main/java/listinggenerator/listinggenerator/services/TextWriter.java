package listinggenerator.listinggenerator.services;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;

public class TextWriter {

    static void writeToDocument (XWPFDocument document, String directoryToSaveValue, String docNameValue) {
        if (!directoryToSaveValue.isEmpty()) directoryToSaveValue+="/";
        try (FileOutputStream out = new FileOutputStream(directoryToSaveValue  + docNameValue + ".docx")) {
            System.out.println(directoryToSaveValue + docNameValue + ".docx");
            document.write(out);
            System.out.println("Документ успешно создан");
        } catch (IOException e) {
            System.out.println("Ошибка при создании документа: " + e.getMessage());
        }
    }

    static void addContentToDocument(XWPFDocument document,
                                     String fileName,
                                     String content,
                                     String titleFont,
                                     String codeFont) {
        // Разбираем настройки шрифтов
        String[] titleFontParts = titleFont.split(",");
        String titleFontName = titleFontParts.length > 0 ? titleFontParts[0] : "Times New Roman";
        int titleFontSize = titleFontParts.length > 1 ? Integer.parseInt(titleFontParts[1]) : 14;

        String[] codeFontParts = codeFont.split(",");
        String codeFontName = codeFontParts.length > 0 ? codeFontParts[0] : "Courier New";
        int codeFontSize = codeFontParts.length > 1 ? Integer.parseInt(codeFontParts[1]) : 10;

        // file name
        XWPFParagraph fileNameTitle = document.createParagraph();
        XWPFRun runFileNameTitle = fileNameTitle.createRun();
        runFileNameTitle.setFontFamily("Times New Roman");
        runFileNameTitle.setFontSize(14);
        runFileNameTitle.setText(fileName + "\n");
        runFileNameTitle.addBreak();

        // code
        XWPFParagraph codeContent = document.createParagraph();
        XWPFRun run = codeContent.createRun();
        run.setFontFamily("Courier New");
        for (String line : content.split("\n")) {
            run.setText(line);
            run.addBreak();
        }
    }


}
