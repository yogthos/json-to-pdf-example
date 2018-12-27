package example;

import cljpdf.text.Document;
import cljpdf.text.PageSize;
import cljpdf.text.pdf.PdfContentByte;
import cljpdf.text.pdf.PdfWriter;
import org.yogthos.JsonPDF;
import cljpdf.text.pdf.PdfPageEventHelper;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Main {
    
    public static void main(String[] args) throws Exception {
        String jsonDoc1 = "[{}, [\"paragraph\", \"hello world\"]]";
        String jsonDoc2 = "[{\"pages\":true,\"orientation\":\"landscape\"}, [\"paragraph\", \"hello world\"]]";

        JsonPDF.writeToFile(jsonDoc1, "out.pdf", null);

        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        JsonPDF.writeToStream(new ByteArrayInputStream(jsonDoc1.getBytes()),
                new FileOutputStream("outstream.pdf"), null);

        JsonPDF.writeToFile(jsonDoc1, "out.pdf", new HeaderFooter());
    }

    static class HeaderFooter extends PdfPageEventHelper {
        
        @Override
        public void onStartPage(PdfWriter writer, Document document) {
            try {
                PdfContentByte under = writer.getDirectContentUnder();
                under.saveState();
                under.setRGBColorFill(0xFF, 0xD7, 0x00);
                under.rectangle(5, 5,
                        PageSize.POSTCARD.getWidth() - 10, PageSize.POSTCARD.getHeight() - 10);
                under.fill();
                under.restoreState();
            } catch (Exception x) {
                x.printStackTrace();
            }
        }
    }
}
