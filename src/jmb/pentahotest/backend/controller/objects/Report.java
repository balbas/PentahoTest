package jmb.pentahotest.backend.controller.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmb.pentahotest.backend.model.QueryManager;

/**
 *
 * @author jmbalbas
 */
public class Report {
    
    public Report() {}
    
    public Report select() {
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select * from Reports where id = " + id + ";");
        try {
            while (resultSet.next()) {
                nombre = resultSet.getString(2);
                descripcion = resultSet.getString(3);
                numeroCopias = resultSet.getInt(4);
                controlImpresion = (resultSet.getBoolean(5)) ? true : false;
                System.out.println(controlImpresion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            queryManager.statementClose();
            queryManager.connectionClose();
        }
        
        return this;
    }
    
    public boolean insert() {
        queryManager = new QueryManager();
        return queryManager.insertOrUpdateOrDelete("insert into Reports(id, nombre, descripcion, numero_copias, control_impresion) values(" + id + ", '" + nombre + "', '" + descripcion + "', " + numeroCopias + ", " + ((controlImpresion) ? 1 : 0) + ");");
    }
    
    public boolean update() {
        queryManager = new QueryManager();
        return queryManager.insertOrUpdateOrDelete("update Reports set nombre = '" + nombre + "', descripcion = '" + descripcion + "', numero_copias = " + numeroCopias + ", control_impresion = " + ((controlImpresion) ? 1 : 0) + " where id = " + id + ";");
    }
    
    public boolean delete() {
        return true;
    }
    
    public int getNextId() {
        int nextId = 0;
        
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select max(id) as maxId from Reports;");
        try {
            while (resultSet.next()) {
                nextId = resultSet.getInt("maxId") + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            queryManager.statementClose();
            queryManager.connectionClose();
        }
        
        return nextId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumeroCopias() {
        return numeroCopias;
    }

    public void setNumeroCopias(int numeroCopias) {
        this.numeroCopias = numeroCopias;
    }
    
    public boolean getControlImpresion() {
        return controlImpresion;
    }
    
    public void setControlImpresion(boolean controlImpresion) {
        this.controlImpresion = controlImpresion;
    }
    
    private QueryManager queryManager;
    private int id;
    private String nombre;
    private String descripcion;
    private int numeroCopias;
    private boolean controlImpresion;
}
