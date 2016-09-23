package jmb.pentahotest.backend.controller.objects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmb.pentahotest.backend.model.QueryManager;

/**
 *
 * @author jmbalbas
 */
public class Lineas {
    
    public Lineas() {
        id = new ArrayList();
        contador = new ArrayList();
        referencia = new ArrayList();
        descripcion = new ArrayList();
        cantidad = new ArrayList();
        precioUnitario = new ArrayList();
        porcentajeDto = new ArrayList();
        importeBruto = new ArrayList();
        importeDto = new ArrayList();
        importeNeto = new ArrayList();
    }
    
    public Lineas select() {
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select * from Lineas where id_documento = " + documento + " order by contador asc;");
        try {
            while (resultSet.next()) {
                id.add(resultSet.getInt(1));
                contador.add(resultSet.getInt(2));
                referencia.add(resultSet.getString(3));
                descripcion.add(resultSet.getString(4));
                cantidad.add(resultSet.getDouble(5));
                precioUnitario.add(resultSet.getDouble(6));
                porcentajeDto.add(resultSet.getDouble(7));
                importeBruto.add(resultSet.getDouble(8));
                importeDto.add(resultSet.getDouble(9));
                importeNeto.add(resultSet.getDouble(10));
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
        boolean result = false;
        queryManager = new QueryManager();
        for (int i = 0; i < id.size(); i++){
            result = queryManager.insertOrUpdateOrDelete("insert into Lineas(id, contador, referencia, descripcion, cantidad, precio_unitario, porcentaje_descuento, importe_bruto, importe_descuento, importe_neto, id_documento) values(" + id.get(i) + ", " + contador.get(i) + ", '" + referencia.get(i) + "', '" + descripcion.get(i) + "', " + cantidad.get(i) + ", " + precioUnitario.get(i) + ", " + porcentajeDto.get(i) + ", " + importeBruto.get(i) + ", " + importeDto.get(i) + ", " + importeNeto.get(i) + ", " + documento + ");");
            if (!result) break;
        }
        
        return result;
    }
    
    public boolean update() {
        boolean result = false;
        queryManager = new QueryManager();
        for (int i = 0; i < id.size(); i++){
            result = queryManager.insertOrUpdateOrDelete("update Lineas set contador = " + contador.get(i) + ", referencia = '" + referencia.get(i) + "', descripcion = '" + descripcion.get(i) + "', cantidad = " + cantidad.get(i) + ", precio_unitario = " + precioUnitario.get(i) + ", porcentaje_descuento = " + porcentajeDto.get(i) + ", importe_bruto = " + importeBruto.get(i) + ", importe_descuento = " + importeDto.get(i) + ", importe_neto = " + importeNeto.get(i) + " where id_documento = " + documento + ";");
            if (!result) break;
        }
        
        return result;
    }
    
    public boolean delete() {
        queryManager = new QueryManager();
        return queryManager.insertOrUpdateOrDelete("delete from Lineas where id_documento = " + documento + ";");
    }
    
    public int getNextId() {
        int nextId = 0;
        
        queryManager = new QueryManager();
        ResultSet resultSet = queryManager.execute("select max(id) as maxId from Lineas;");
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

    public ArrayList<Integer> getId() {
        return id;
    }

    public void setId(ArrayList<Integer> id) {
        this.id = id;
    }

    public ArrayList<Integer> getContador() {
        return contador;
    }

    public void setContador(ArrayList<Integer> contador) {
        this.contador = contador;
    }
    
    public ArrayList<String> getReferencia() {
        return referencia;
    }

    public void setReferencia(ArrayList<String> referencia) {
        this.referencia = referencia;
    }

    public ArrayList<String> getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(ArrayList<String> descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Double> getCantidad() {
        return cantidad;
    }

    public void setCantidad(ArrayList<Double> cantidad) {
        this.cantidad = cantidad;
    }

    public ArrayList<Double> getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(ArrayList<Double> precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public ArrayList<Double> getPorcentajeDto() {
        return porcentajeDto;
    }

    public void setPorcentajeDto(ArrayList<Double> porcentajeDto) {
        this.porcentajeDto = porcentajeDto;
    }

    public ArrayList<Double> getImporteBruto() {
        return importeBruto;
    }

    public void setImporteBruto(ArrayList<Double> importeBruto) {
        this.importeBruto = importeBruto;
    }

    public ArrayList<Double> getImporteDto() {
        return importeDto;
    }

    public void setImporteDto(ArrayList<Double> importeDto) {
        this.importeDto = importeDto;
    }

    public ArrayList<Double> getImporteNeto() {
        return importeNeto;
    }

    public void setImporteNeto(ArrayList<Double> importeNeto) {
        this.importeNeto = importeNeto;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }
    
    private QueryManager queryManager;
    private ArrayList<Integer> id;
    private ArrayList<Integer> contador;
    private ArrayList<String> referencia;
    private ArrayList<String> descripcion;
    private ArrayList<Double> cantidad;
    private ArrayList<Double> precioUnitario;
    private ArrayList<Double> porcentajeDto;
    private ArrayList<Double> importeBruto;
    private ArrayList<Double> importeDto;
    private ArrayList<Double> importeNeto;
    private int documento;
}
