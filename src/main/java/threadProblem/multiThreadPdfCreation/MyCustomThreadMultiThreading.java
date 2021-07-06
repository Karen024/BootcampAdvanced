package threadProblem.multiThreadPdfCreation;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

public class MyCustomThreadMultiThreading extends Thread {
    private int startFrom;
    private int to;

    public MyCustomThreadMultiThreading(int number1, int number2) {
        startFrom = number1;
        to = number2;
    }

    @Override
    public void run() {
        for (int i = startFrom; i <= to; i++) {
            try (OutputStream out = new FileOutputStream(new File("C:\\Users\\Mher\\IdeaProjects\\AdvancedExercises\\src\\main\\java\\threadProblem\\multiThreadPdfCreation\\files\\PdfFile" + i + ".pdf"))) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyCustomThreadMultiThreading)) return false;
        MyCustomThreadMultiThreading that = (MyCustomThreadMultiThreading) o;
        return startFrom == that.startFrom &&
                to == that.to;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startFrom, to);
    }

    @Override
    public String toString() {
        return "MyCustomThreadMultiThreading{" +
                "startFrom=" + startFrom +
                ", to=" + to +
                '}';
    }
}
