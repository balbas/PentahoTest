package jmb.pentahotest.backend.controller;

import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import jmb.pentahotest.backend.controller.objects.Report;
import org.pentaho.reporting.engine.classic.core.DataFactory;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.DriverConnectionProvider;
import org.pentaho.reporting.engine.classic.core.modules.misc.datafactory.sql.SQLReportDataFactory;
import org.pentaho.reporting.engine.classic.core.wizard.RelationalAutoGeneratorPreProcessor;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;

/**
 *
 * @author jmbalbas
 */
public class RenderReport extends AbstractReportGenerator {
    
    public RenderReport(int referencia) {
        this.referencia = referencia;
    }
    
    /**
     * Returns the report definition which will be used to generate the report. In this case, the report will be
     * loaded and parsed from a file contained in this package.
     *
     * @return the loaded and parsed report definition to be used in report generation.
     */
    @Override
    public MasterReport getReportDefinition() {
        try {
            // Using the classloader, get the URL to the reportDefinition file
            final ClassLoader classloader = this.getClass().getClassLoader();
            final URL reportDefinitionURL = classloader.getResource("jmb/pentahotest/backend/controller/resources/PRFACTURA.prpt");

            // Parse the report file
            final ResourceManager resourceManager = new ResourceManager();
            final Resource directly = resourceManager.createDirectly(reportDefinitionURL, MasterReport.class);
            final MasterReport report = (MasterReport) directly.getResource();
            
            // Set query
            report.setQuery(QUERY_NAME);
            report.addPreProcessor(new RelationalAutoGeneratorPreProcessor());
            return report;
        } catch (ResourceException ex) {
            Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Returns the data factory which will be used to generate the data used during report generation. In this example,
     * we will return null since the data factory has been defined in the report definition.
     *
     * @return the data factory used with the report generator
     */
    @Override
    public DataFactory getDataFactory() {
        final DriverConnectionProvider sampleDriverConnectionProvider = new DriverConnectionProvider();
        sampleDriverConnectionProvider.setDriver("org.sqlite.JDBC");
        sampleDriverConnectionProvider.setUrl("jdbc:sqlite:" + PropertiesFileManager.getInstance().getProperty(PropertiesFileManager.BBDD_URL));
        sampleDriverConnectionProvider.setProperty("user", "");
        sampleDriverConnectionProvider.setProperty("password", "");

        final SQLReportDataFactory dataFactory = new SQLReportDataFactory(sampleDriverConnectionProvider);
        dataFactory.setQuery(QUERY_NAME, "SELECT Reports.id AS RPT_ID, Reports.nombre AS RPT_NOMBRE, Reports.descripcion as RPT_DESCRIPCION, Reports.numero_copias as RPT_NRO_COPIAS, Documentos.id AS DOC_ID, Documentos.numeracion AS DOC_NUMERACION, Documentos.fecha AS DOC_FECHA, Documentos.hora AS DOC_HORA, Documentos.observaciones AS DOC_OBSERVACIONES, Documentos.importe_base AS DOC_IMP_BASE, Documentos.porcentaje_iva AS DOC_PORC_IVA, Documentos.importe_cuota AS DOC_IMP_CUOTA, Documentos.importe_total AS DOC_IMP_TOTAL, Documentos.forma_pago AS DOC_FORMA_PAGO, Tipos.id AS TIPO_ID, Tipos.descripcion AS TIPO_DESCRIPCION, Clientes.id AS CLI_ID, Clientes.nombre AS CLI_NOMBRE, Clientes.apellidos AS CLI_APELLIDOS, Clientes.direccion AS CLI_DIRECCION, Clientes.cp AS CLI_COD_POSTAL, Clientes.localidad AS CLI_LOCALIDAD, Clientes.provincia AS CLI_PROVINCIA, Clientes.pais AS CLI_PAIS, Clientes.documento AS CLI_DOCUMENTO, Clientes.telefono AS CLI_TEL_FIJO, Clientes.movil AS CLI_TEL_MOVIL, Clientes.fax AS CLI_FAX, Clientes.email AS CLI_EMAIL, Clientes.web AS CLI_WEB, Empresas.id AS EMP_ID, Empresas.nombre AS EMP_NOMBRE, Empresas.direccion AS EMP_DIRECCION, Empresas.cp AS EMP_COD_POSTAL, Empresas.localidad AS EMP_LOCALIDAD, Empresas.provincia AS EMP_PROVINCIA, Empresas.pais AS EMP_PAIS, Empresas.cif AS EMP_CIF, Empresas.telefono AS EMP_TELEFONO, Empresas.fax AS EMP_FAX, Empresas.email AS EMP_EMAIL, Empresas.web AS EMP_WEB, Empresas.lopd AS EMP_LOPD, Empresas.registro_mercantil AS EMP_REGISTRO_MERCANTIL, Lineas.id AS LIN_ID, Lineas.contador AS LIN_CONTADOR, Lineas.referencia AS LIN_REFERENCIA, Lineas.descripcion AS LIN_DESCRIPCION, Lineas.cantidad AS LIN_CANTIDAD, Lineas.precio_unitario AS LIN_PRECIO_UNITARIO, Lineas.porcentaje_descuento AS LIN_PORC_DESCUENTO, Lineas.importe_bruto AS LIN_IMP_BRUTO, Lineas.importe_descuento AS LIN_IMP_DESCUENTO, Lineas.importe_neto AS LIN_IMP_NETO FROM Clientes, Empresas, Tipos, Reports, Documentos INNER JOIN Lineas ON Documentos.id = Lineas.id_documento WHERE Documentos.id = " + referencia + " AND Documentos.id_tipo = Tipos.id AND Documentos.id_cliente = Clientes.id AND Documentos.id_empresa = Empresas.id AND Tipos.id_report = Reports.id ORDER BY Documentos.id DESC;");

        return dataFactory;
    }

    /**
     * Returns the set of runtime report parameters. This sample report does not use report parameters, so the
     * method will return <code>null</code>
     *
     * @return <code>null</code> indicating the report generator does not use any report parameters
     */
    @Override
    public Map<String, Object> getReportParameters() {
        return null;
    }
    
    private static final String QUERY_NAME = "ReportQuery";
    private final int referencia;
}
