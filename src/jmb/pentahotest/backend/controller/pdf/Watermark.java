package jmb.pentahotest.backend.controller.pdf;

import java.awt.Color;
import java.io.File;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * Clase para insertar un texto como marca de agua en un pdf.
 * 
 * @author jmbalbas
 */
public class Watermark {
    
    /**
     * Constructor de la clase
     * 
     * @param text
     */
    public Watermark(String[] text) {
        this.text = text;
    }
    
    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getRotation() {
        return rotation;
    }

    public void setRotation(int rotation) {
        this.rotation = rotation;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }
    
    public PDFont getFont() {
        return font;
    }
    
    public void setFont(PDFont font) {
        this.font = font;
    }

    private synchronized File getTemp() throws Exception {
        File f = File.createTempFile("Watermark", ".wmk");
        f.deleteOnExit();
        return f;
    }

    /**
     * Añade el texto como marca de agua
     * 
     * @param posX
     * @param posY
     * @param rotation
     * @param font
     * @param fontSize
     * @param fontColor
     * @param pathPdf
     * @throws Exception 
     */
    public void addTextWatermark(int posX, int posY, int rotation, PDFont font, int fontSize, String fontColor, String pathPdf) throws Exception {
        if (text.length > 0) {
            this.posX = posX;
            this.posY = posY;
            this.rotation = rotation;
            this.font = (font != null) ? font : PDType1Font.COURIER;
            this.fontSize = fontSize;
            String[] rgb = (!fontColor.equals("")) ? fontColor.split(",") : "0,0,0".split(",");
            this.fontColor = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));            
            AddInternalTextWatermark(text, pathPdf);
        }
    }
    
    public void addTextWatermark(String pathPdf) throws Exception {
        AddInternalTextWatermark(text, pathPdf);
    }

    private void AddInternalTextWatermark(String[] contenido, String path_pdf) throws Exception {
        addMessagePages(new File(path_pdf), contenido);
    }

    private void addMessagePages(File file, String[] message) throws Exception {
        PDDocument doc = null;
        File outfile = null;
        try {
            outfile = getTemp();
            doc = PDDocument.load(file);
            List allPages = doc.getDocumentCatalog().getAllPages();
            if (text.length < allPages.size()) {
                if (fromPage != 0) {
                    toPage = toPage + text.length;
                } else {
                    fromPage = 0;
                    toPage = text.length;
                }
            } else {
                fromPage = 0;
                toPage = allPages.size();
            }
            for (int i = fromPage; i < toPage; i++) {
                PDPage page = (PDPage) allPages.get(i);
                double centeredXPosition = posX;
                double centeredYPosition = posY;
                PDPageContentStream contentStream = new PDPageContentStream(doc, page, true, true, true);
                try {
                    contentStream.beginText();
                    contentStream.setFont(font, fontSize);
                    contentStream.setNonStrokingColor(fontColor);
                    contentStream.setTextRotation(rotation, centeredXPosition, centeredYPosition);
                    contentStream.drawString(message[i]);
                    contentStream.endText();
                } finally {
                    contentStream.close();
                }
            }
            fromPage = toPage;
            doc.save(outfile);
        } finally {
            if (doc != null) {
                doc.close();
            }
        }
        try {
            String path = file.getAbsolutePath();
            FileUtils.deleteQuietly(file);
            FileUtils.copyFile(outfile, new File(path));
        } finally {
            if (outfile != null) {
                FileUtils.deleteQuietly(outfile);
            }
        }
    }
    
    private final String[] text;
    private PDFont font;
    private int posX;
    private int posY;
    private int fontSize;
    private int rotation;
    private Color fontColor;
    private int fromPage;
    private int toPage;
}

