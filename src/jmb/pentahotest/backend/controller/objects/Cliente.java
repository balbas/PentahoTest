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
public class Cliente {
    
    public Cliente() {}
    
    public Cliente select() {
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select * from Clientes where id = " + id + ";");
        try {
            while (resultSet.next()) {
                nombre = resultSet.getString(2);
                apellidos = resultSet.getString(3);
                direccion = resultSet.getString(4);
                cp = resultSet.getString(5);
                localidad = resultSet.getString(6);
                provincia = resultSet.getString(7);
                pais = resultSet.getString(8);
                documento = resultSet.getString(9);
                telefono = resultSet.getString(10);
                movil = resultSet.getString(11);
                fax = resultSet.getString(12);
                email = resultSet.getString(13);
                web = resultSet.getString(14);
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
        return queryManager.insertOrUpdate("insert into Clientes(id, nombre, apellidos, direccion, cp, localidad, provincia, pais, documento, telefono, movil, fax, email, web) values(" + id + ", '" + nombre + "', '" + apellidos + "', '" + direccion + "', '" + cp + "', '" + localidad + "', '" + provincia + "', '" + pais + "', '" + documento + "', '" + telefono + "', '" + movil + "', '" + fax + "', '" + email + "', '" + web + "');");
    }
    
    public boolean update() {
        queryManager = new QueryManager();
        return queryManager.insertOrUpdate("update Cientes set nombre = '" + nombre + "', apellidos = '" + apellidos + "', direccion = '" + direccion + "', cp = '" + cp + "', localidad = '" + localidad + "', provincia = '" + provincia + "', pais = '" + pais + "', documento = '" + documento + "', telefono = '" + telefono + "', movil = '" + movil + "', fax = '" + fax + "', email = '" + email + "', web = '" + web + "' where id = " + id + ";");
    }
    
    public boolean delete() {
        return true;
    }
    
    public int getNextId() {
        int nextId = 0;
        
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select max(id) as maxId from Clientes;");
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }
    
    private QueryManager queryManager;
    private int id;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String cp;
    private String localidad;
    private String provincia;
    private String pais;
    private String documento;
    private String telefono;
    private String movil;
    private String fax;
    private String email;
    private String web;
}
