package jmb.pentahotest.backend.controller;

import java.io.IOException;
import org.apache.pdfbox.exceptions.COSVisitorException;

/**
 *
 * @author jmbalbas
 */
public class PrintToPdf {

    /**
    * Metodo que junta los distintos reports de un impreso en un unico fichero
    * PDF
    * 
    * @param ficheros
    * @param pathSalida   
    * @throws COSVisitorException
    * @throws IOException
    */
    private void simpleMerge(String[] ficheros, String pathSalida) throws COSVisitorException, IOException {
        PDFFileMergerUtility merge = new PDFFileMergerUtility();
        merge.setDestinationFileName(pathSalida);
        for (int i = 0; i <= ficheros.length - 1; i++) {
            merge.addSource(ficheros[i]);
        }
        merge.mergeDocuments();
    }

}
