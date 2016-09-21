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
public class Empresa {
    
    public Empresa() {}
    
    public Empresa select() {
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select * from Empresas where id = " + id + ";");
        try {
            while (resultSet.next()) {
                nombre = resultSet.getString(2);                
                direccion = resultSet.getString(3);
                localidad = resultSet.getString(4);
                cp = resultSet.getString(5);
                provincia = resultSet.getString(6);
                pais = resultSet.getString(7);
                cif = resultSet.getString(8);
                telefono = resultSet.getString(9);
                fax = resultSet.getString(10);
                email = resultSet.getString(11);
                web = resultSet.getString(12);
                lopd = resultSet.getString(13);
                registroMercantil = resultSet.getString(14);
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
        return queryManager.insertOrUpdate("insert into Empresas(id, nombre, direccion, localidad, cp, provincia, pais, cif, telefono, fax, email, web, lopd, registro_mercantil) values(" + id + ", '" + nombre + "', '" + direccion + "', '" + localidad + "', '" + cp + "', '" + provincia + "', '" + pais + "', '" + cif + "', '" + telefono + "', '" + fax + "', '" + email + "', '" + web + "', '" + lopd + "', '" + registroMercantil + "' );");
    }
    
    public boolean update() {
        queryManager = new QueryManager();
        return queryManager.insertOrUpdate("update Empresas set nombre = '" + nombre + "', direccion = '" + direccion + "', localidad = '" + localidad + "', cp = '" + cp + "', provincia = '" + provincia + "', pais = '" + pais + "', cif = '" + cif + "', telefono = '" + telefono + "', fax = '" + fax + "', email = '" + email + "', web = '" + web + "', lopd = '" + lopd + "', registro_mercantil = '" + registroMercantil + "' where id = " + id + ";");
    }
    
    public boolean delete() {
        return true;
    }
    
    public int getNextId() {
        int nextId = 0;
        
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select max(id) as maxId from Empresas;");
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
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

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getLopd() {
        return lopd;
    }

    public void setLopd(String lopd) {
        this.lopd = lopd;
    }

    public String getRegistroMercantil() {
        return registroMercantil;
    }

    public void setRegistroMercantil(String registroMercantil) {
        this.registroMercantil = registroMercantil;
    }
    
    private QueryManager queryManager;
    private int id;
    private String nombre;
    private String direccion;
    private String localidad;
    private String cp;
    private String provincia;
    private String pais;
    private String cif;
    private String telefono;
    private String fax;
    private String email;
    private String web;
    private String lopd;
    private String registroMercantil;
}
