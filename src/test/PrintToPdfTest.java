package test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmb.pentahotest.backend.controller.pdf.PrintToPdf;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;

/**
 *
 * @author jmbalbas
 */
public class PrintToPdfTest {

    
    public static void main(String[] args) {
        String[] ficheros = new String[2];
        ficheros[0] = "RPT_1_20160921_100000_1.pdf";
        ficheros[1] = "RPT_1_20160921_100000_2.pdf";
        
        PrintToPdf pdf = new PrintToPdf(ficheros, "out", true);
        try {
            pdf.print("1", "PRFACTURA");
        } catch (IllegalArgumentException | IOException | ReportProcessingException | COSVisitorException ex) {
            Logger.getLogger(PrintToPdfTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
