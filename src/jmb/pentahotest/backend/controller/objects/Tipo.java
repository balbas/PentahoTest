package jmb.pentahotest.backend.controller.objects;

/**
 *
 * @author jmbalbas
 */
public class Tipo {

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
    
    private int id;
    private String descripcion;
    private Report report;
}
