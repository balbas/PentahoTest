package jmb.pentahotest.backend.controller.pdf;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;

/**
 *
 * @author jmbalbas
 */
public class PrintToPdf {
    
    public PrintToPdf(String[] ficheros, String pathSalida) {
        this.ficheros = ficheros;
        this.pathSalida = pathSalida;
    }

    public String[] getFicheros() {
        return ficheros;
    }

    public void setFicheros(String[] ficheros) {
        this.ficheros = ficheros;
    }

    public String getPathSalida() {
        return pathSalida;
    }

    public void setPathSalida(String pathSalida) {
        this.pathSalida = pathSalida;
    }
    
    /**
     * Método que lanza la impresión en pdf.
     * 
     * @param id
     * @param nombreReport
     * @throws java.io.IOException
     * @throws org.pentaho.reporting.engine.classic.core.ReportProcessingException
     * @throws org.apache.pdfbox.exceptions.COSVisitorException
     */
    public void print(String id, String nombreReport) throws IllegalArgumentException, IOException, ReportProcessingException, COSVisitorException {
        for (int i = 0; i < ficheros.length; i++) {
            new RenderReport(Integer.valueOf(id), nombreReport).generateReport(AbstractReportGenerator.OutputType.PDF, new File(pathSalida + File.separator + ficheros[i]));
        }
        
        if (ficheros.length > 1) {
            merge();            
        } else {
            // Actualizamos el valor de pathSalida para poder abrir el fichero una vez generado
            pathSalida += File.separator + ficheros[0];
        }
    }

    /**
    * Método que junta los distintos pdf en un único fichero pdf.
    * 
    * @throws COSVisitorException
    * @throws IOException
    */
    private void merge() throws COSVisitorException, IOException {
        String auxPathSalida = pathSalida;
        PDFFileMergerUtility mergerUtility = new PDFFileMergerUtility();
        pathSalida += File.separator + ficheros[0].substring(0, ficheros[0].length() - 5) + "COMPLETO.pdf";
        mergerUtility.setDestinationFileName(pathSalida);
        for (int i = 0; i < ficheros.length; i++) {
            mergerUtility.addSource(auxPathSalida + File.separator + ficheros[i]);
        }
        // Merge files
        mergerUtility.mergeDocuments();
        
        // Delete files
        File deleteFile;
        for (int i = 0; i < ficheros.length; i++) {
            deleteFile = new File(auxPathSalida + File.separator + ficheros[i]);
            deleteFile.delete();
        }
    }
    
    String[] ficheros;
    String pathSalida;
}
