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
public class Tipo {
    
    public Tipo() {}
    
    public Tipo select() {
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select * from Tipos where id = " + id + ";");
        try {
            while (resultSet.next()) {
                descripcion = resultSet.getString(2);
                report = resultSet.getInt(3);
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
        return queryManager.insertOrUpdate("insert into Tipos(id, descripcion, id_report) values(" + id + ", '" + descripcion + "', " + report + ");");
    }
    
    public boolean update() {
        queryManager = new QueryManager();
        return queryManager.insertOrUpdate("update Tipos set descripcion = '" + descripcion + "', id_report = " + report + " where id = " + id + ";");
    }
    
    public boolean delete() {
        return true;
    }
    
    public int getNextId() {
        int nextId = 0;
        
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select max(id) as maxId from Tipos;");
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }
    
    private QueryManager queryManager;
    private int id;
    private String descripcion;
    private int report;
}
