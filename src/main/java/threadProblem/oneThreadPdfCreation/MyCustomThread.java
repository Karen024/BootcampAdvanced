package threadProblem.oneThreadPdfCreation;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.*;

public class MyCustomThread extends Thread {
    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            try (OutputStream out = new FileOutputStream(new File("C:\\Users\\Mher\\IdeaProjects\\AdvancedExercises\\src\\main\\java\\threadProblem\\oneThreadPdfCreation\\files\\PdfFile" + i + ".pdf"))) {
                Document document = new Document();
                PdfWriter.getInstance(document, out);
                document.open();
                document.newPage();
                document.add(new Paragraph("This is Page 1"));
                document.newPage();
                document.add(new Paragraph("This is Page 2"));
                document.newPage();
                document.add(new Paragraph("This is Page 3"));
                document.close();
            } catch (DocumentException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
