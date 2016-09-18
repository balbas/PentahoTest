package jmb.pentahotest.backend.controller.objects;

/**
 *
 * @author jmbalbas
 */
public class Empresa {

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
