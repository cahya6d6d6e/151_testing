package controller;

import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class PDFReport {

    public static void generateReport(String title,String text_content) {
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        try {
            doc.addPage(page);
            PDFont font = PDType1Font.HELVETICA_BOLD;

            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.beginText();
            content.setFont(font, 12);
            content.moveTextPositionByAmount(100, 700);
            content.drawString(text_content);
            content.endText();
            content.close();
            doc.save("REPORT_"+title+".pdf");
            doc.close();
        } catch (IOException | COSVisitorException e) {
            System.err.println(e);
        }
    }
}
