package jmb.pentahotest.backend.controller.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmb.pentahotest.backend.model.QueryManager;
import jmb.pentahotest.frontend.gui.PentahoTestView;

/**
 *
 * @author jmbalbas
 */
public class Documento {
    
    public Documento() {}
    
    public Documento select() {
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select * from Documentos where id = " + id + ";");
        try {
            while (resultSet.next()) {
                numeracion = resultSet.getString(2);
                fecha = resultSet.getInt(3);
                hora = resultSet.getString(4);
                observaciones = resultSet.getString(5);
                importeBase = resultSet.getDouble(6);
                importeDescuento = resultSet.getDouble(7);
                porcentajeIva = resultSet.getDouble(8);
                importeCuota = resultSet.getDouble(9);
                importeTotal = resultSet.getDouble(10);
                formaPago = resultSet.getString(11);
                tipo = resultSet.getInt(12);
                empresa = resultSet.getInt(13);
                cliente = resultSet.getInt(14);
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
        return queryManager.insertOrUpdate("insert into Documentos(id, numeracion, fecha, hora, observaciones, importe_base, importe_descuento, porcentaje_iva, importe_cuota, importe_total, forma_pago, id_tipo, id_empresa, id_cliente) values(" + id + ", '" + numeracion + "', " + fecha + ", '" + hora + "', '" + observaciones + "', " + importeBase + ", " + importeDescuento + ", " + porcentajeIva + ", " + importeCuota + ", " + importeTotal + ", '" + formaPago + "', " + tipo + ", " + empresa + ", " + cliente + ");");
    }
    
    public boolean update() {
        queryManager = new QueryManager();
        return queryManager.insertOrUpdate("update Documentos set numeracion = '" + numeracion + "', fecha = " + fecha + ", hora = '" + hora + "', observaciones = '" + observaciones + "', importe_base = " + importeBase + ", importe_descuento = " + importeDescuento + "porcentaje_iva = " + porcentajeIva + ", importe_cuota = " + importeCuota + ", importe_total = " + importeTotal + ", forma_pago = '" + formaPago + "', id_tipo = " + tipo + ", id_empresa = " + empresa + ", id_cliente = " + cliente + " where id = " + id + ";");
    }
    
    public boolean delete() {
        return true;
    }
    
    public int getNextId() {
        int nextId = 0;
        
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select max(id) as maxId from Documentos;");
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

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public int getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        date = null;
        try {
            date = dateFormat.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(PentahoTestView.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.fecha = Integer.valueOf(dateFormatSqlite.format(date));
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getImporteBase() {
        return importeBase;
    }

    public void setImporteBase(double importeBase) {
        this.importeBase = importeBase;
    }
    
    public double getImporteDescuento() {
        return importeDescuento;
    }

    public void setImporteDescuento(double importeDescuento) {
        this.importeDescuento = importeDescuento;
    }

    public double getPorcentajeIva() {
        return porcentajeIva;
    }

    public void setPorcentajeIva(double porcentajeIva) {
        this.porcentajeIva = porcentajeIva;
    }

    public double getImporteCuota() {
        return importeCuota;
    }

    public void setImporteCuota(double importeCuota) {
        this.importeCuota = importeCuota;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getEmpresa() {
        return empresa;
    }

    public void setEmpresa(int empresa) {
        this.empresa = empresa;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }
    
    private QueryManager queryManager;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final DateFormat dateFormatSqlite = new SimpleDateFormat("yyyyMMdd");
    private Date date;
    private int id;
    private String numeracion;
    private int fecha;
    private String hora;
    private String observaciones;
    private double importeBase;
    private double importeDescuento;
    private double porcentajeIva;
    private double importeCuota;
    private double importeTotal;
    private String formaPago;
    private int tipo;
    private int empresa;
    private int cliente;
}
