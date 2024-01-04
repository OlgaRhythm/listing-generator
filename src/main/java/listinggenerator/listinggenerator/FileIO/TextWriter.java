package listinggenerator.listinggenerator.FileIO;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.FileOutputStream;
import java.io.IOException;

public class TextWriter {


    static void writeToDocument (XWPFDocument document) {
        try (FileOutputStream out = new FileOutputStream("output.docx")) {
            document.write(out);
            System.out.println("Документ успешно создан");
        } catch (IOException e) {
            System.out.println("Ошибка при создании документа: " + e.getMessage());
        }
    }

    static void addContentToDocument(XWPFDocument document, String fileName, String content) {
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("File: " + fileName + "\n" + content);
        run.addBreak();
    }


}
