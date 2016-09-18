package jmb.pentahotest.frontend.gui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import jmb.pentahotest.backend.controller.AbstractReportGenerator;
import jmb.pentahotest.backend.controller.RenderReport;
import jmb.pentahotest.backend.model.QueryManager;
import jmb.pentahotest.frontend.gui.tables.DocumentosView;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;

/**
 *
 * @author jmbalbas
 */
public class PentahoTestView extends javax.swing.JFrame {

    /**
     * Creates new form PentahoTestView
     */
    public PentahoTestView() {
        initComponents();
        setTitle("Pentaho Test - [0.1]");
        setLocationRelativeTo(null);
        
        // Modelo de la tabla documentos
        modeloDocumentos = (DefaultTableModel) jTableDocumentos.getModel();
        
        // Nuevo gestor de consultas
        QueryManager queryManager = new QueryManager();
        
        // Rellenamos combo Clientes
        jComboBoxClientes.removeAllItems();
        jComboBoxClientes.addItem("Seleccione cliente...");
        resultSet = queryManager.execute("select id, nombre, apellidos from Clientes order by id asc;");
        try {
            while (resultSet.next()) {
                jComboBoxClientes.addItem(String.valueOf(resultSet.getInt(1)) + "-" + resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PentahoTestView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Rellenamos combo Tipos
        jComboBoxTipos.removeAllItems();
        jComboBoxTipos.addItem("Seleccione tipo...");
        resultSet = queryManager.execute("select id, descripcion from Tipos order by id asc;");
        try {
            while (resultSet.next()){
                jComboBoxTipos.addItem(String.valueOf(resultSet.getInt(1)) + "-" + resultSet.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PentahoTestView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Rellenamos combo Empresas
        jComboBoxEmpresas.removeAllItems();
        jComboBoxEmpresas.addItem("Seleccione empresa...");
        resultSet = queryManager.execute("select id, nombre from Empresas order by id asc;");
        try {
            while (resultSet.next()){
                jComboBoxEmpresas.addItem(String.valueOf(resultSet.getInt(1)) + "-" + resultSet.getString(2));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PentahoTestView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Cerramos gestor de consultas
        queryManager.statementClose();
        queryManager.connectionClose();
        
        try {
            // Asignamos formatos para los campos Fecha inicio y Fecha fin (dd/MM/yyyy)
            jFormattedTextFieldFechaInicio.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
            jFormattedTextFieldFechaFin.setFormatterFactory(new DefaultFormatterFactory(new MaskFormatter("##/##/####")));
        } catch (ParseException ex) {
            Logger.getLogger(PentahoTestView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Añadimos MouseListener a la tabla para controlar la selección al hacer doble click (evento mousePressed)
        jTableDocumentos.addMouseListener(new MouseListener() {
            @Override
            public void mouseExited(MouseEvent evento) {}
            
            @Override
            public void mouseClicked(MouseEvent e) {}
            
            @Override
            public void mousePressed(MouseEvent e) {
                JTable target = (JTable) e.getSource();
                if (e.getClickCount() == 2) {
                    target.setRowSelectionAllowed(true);
                    jTextFieldSeleccion.setText((String) jTableDocumentos.getValueAt(jTableDocumentos.rowAtPoint(e.getPoint()), 0));
                    jButtonEjecutarReport.setEnabled(true);
                    jButtonEditarDocumento.setEnabled(true);
                } else {
                    target.setRowSelectionAllowed(false);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
        });
        
        // Creamos nuevo render para alinear a la derecha
        TableCellRenderer render = new DefaultTableCellRenderer() { 
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
		JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		
		return label;
            }
        };
        
        jTableDocumentos.getColumnModel().getColumn(5).setCellRenderer(render);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxClientes = new javax.swing.JComboBox();
        jComboBoxTipos = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextFieldFechaFin = new javax.swing.JFormattedTextField();
        jFormattedTextFieldFechaInicio = new javax.swing.JFormattedTextField();
        jButtonEjecutarSeleccion = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jComboBoxEmpresas = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDocumentos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldSeleccion = new javax.swing.JTextField();
        jButtonEjecutarReport = new javax.swing.JButton();
        jButtonEditarDocumento = new javax.swing.JButton();
        jButtonConfiguracion = new javax.swing.JButton();
        jButtonNuevoDocumento = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemConfiguracion = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItemSalir = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemDocumentos = new javax.swing.JMenuItem();
        jMenuItemClientes = new javax.swing.JMenuItem();
        jMenuItemEmpresas = new javax.swing.JMenuItem();
        jMenuItemTipos = new javax.swing.JMenuItem();
        jMenuItemReports = new javax.swing.JMenuItem();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Selección", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.blue));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Cliente:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tipo:");

        jComboBoxClientes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxTipos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Fecha fin:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Fecha inicio:");

        jButtonEjecutarSeleccion.setText("Ejecutar selección");
        jButtonEjecutarSeleccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjecutarSeleccionActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Empresa:");

        jComboBoxEmpresas.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxTipos, 0, 300, Short.MAX_VALUE)
                    .addComponent(jComboBoxClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jFormattedTextFieldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(10, 10, 10)
                        .addComponent(jFormattedTextFieldFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jComboBoxEmpresas, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonEjecutarSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEjecutarSeleccion)
                    .addComponent(jLabel6)
                    .addComponent(jComboBoxEmpresas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jFormattedTextFieldFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jComboBoxTipos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jFormattedTextFieldFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jTableDocumentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Tipo", "Numeración", "Fecha", "Cliente", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDocumentos.setRowHeight(20);
        jScrollPane1.setViewportView(jTableDocumentos);
        if (jTableDocumentos.getColumnModel().getColumnCount() > 0) {
            jTableDocumentos.getColumnModel().getColumn(0).setMinWidth(50);
            jTableDocumentos.getColumnModel().getColumn(0).setMaxWidth(50);
            jTableDocumentos.getColumnModel().getColumn(1).setMinWidth(150);
            jTableDocumentos.getColumnModel().getColumn(1).setMaxWidth(150);
            jTableDocumentos.getColumnModel().getColumn(2).setMinWidth(100);
            jTableDocumentos.getColumnModel().getColumn(2).setMaxWidth(100);
            jTableDocumentos.getColumnModel().getColumn(3).setMinWidth(100);
            jTableDocumentos.getColumnModel().getColumn(3).setMaxWidth(100);
            jTableDocumentos.getColumnModel().getColumn(4).setMinWidth(450);
            jTableDocumentos.getColumnModel().getColumn(4).setMaxWidth(450);
        }

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acciones", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, java.awt.Color.blue));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Selección:");

        jTextFieldSeleccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldSeleccionFocusLost(evt);
            }
        });

        jButtonEjecutarReport.setText("Ejecutar report");
        jButtonEjecutarReport.setEnabled(false);
        jButtonEjecutarReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEjecutarReportActionPerformed(evt);
            }
        });

        jButtonEditarDocumento.setText("Editar documento");
        jButtonEditarDocumento.setEnabled(false);
        jButtonEditarDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEditarDocumentoActionPerformed(evt);
            }
        });

        jButtonConfiguracion.setText("Configuración");
        jButtonConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfiguracionActionPerformed(evt);
            }
        });

        jButtonNuevoDocumento.setText("Nuevo documento");
        jButtonNuevoDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNuevoDocumentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButtonEjecutarReport, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonEditarDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonNuevoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addComponent(jButtonConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldSeleccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonEjecutarReport)
                    .addComponent(jButtonEditarDocumento)
                    .addComponent(jButtonConfiguracion)
                    .addComponent(jButtonNuevoDocumento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 386, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jMenu1.setText("Archivo");

        jMenuItemConfiguracion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemConfiguracion.setText("Configuración");
        jMenuItemConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemConfiguracionActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemConfiguracion);
        jMenu1.add(jSeparator1);

        jMenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItemSalir.setText("Salir");
        jMenu1.add(jMenuItemSalir);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Tablas");

        jMenuItemDocumentos.setText("Documentos");
        jMenuItemDocumentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDocumentosActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemDocumentos);

        jMenuItemClientes.setText("Clientes");
        jMenuItemClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClientesActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemClientes);

        jMenuItemEmpresas.setText("Empresas");
        jMenuItemEmpresas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEmpresasActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemEmpresas);

        jMenuItemTipos.setText("Tipos");
        jMenuItemTipos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTiposActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemTipos);

        jMenuItemReports.setText("Reports");
        jMenuItemReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReportsActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemReports);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void jButtonConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfiguracionActionPerformed
        new ConfigView(this, true).setVisible(true);
    }//GEN-LAST:event_jButtonConfiguracionActionPerformed

    private void jButtonEjecutarSeleccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjecutarSeleccionActionPerformed
        // Vaciamos la tabla
        modeloDocumentos.setRowCount(0);
        
        query = "select Documentos.id as docId, Tipos.id as tipoId, Tipos.descripcion as tipoDesc, Documentos.numeracion as docNumeracion, Documentos.fecha as docFecha, Clientes.id as clienteId, Clientes.nombre as clienteNombre, Clientes.apellidos as clienteApellidos, Documentos.importe_total as docImporteTotal from Documentos, Tipos, Clientes, Empresas where ";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat dateFormatSqlite = new SimpleDateFormat("yyyyMMdd");
        Date date;
        
        // Añadimos el cliente a la query
        if (!jComboBoxClientes.getSelectedItem().toString().equals("Seleccione cliente...")) {
            String cliente = jComboBoxClientes.getSelectedItem().toString();
            String[] idCliente = cliente.split("-");
            query += "Documentos.id_cliente = '" + idCliente[0] + "' and ";
        }
        
        // Añadimos el tipo a la query
        if (!jComboBoxTipos.getSelectedItem().toString().equals("Seleccione tipo...")) {
            String tipo = jComboBoxTipos.getSelectedItem().toString();
            String[] idTipo = tipo.split("-");
            query += "Documentos.id_tipo = '" + idTipo[0] + "' and ";
        }
        
        // Añadimos la empresa a la query
        if (!jComboBoxEmpresas.getSelectedItem().toString().equals("Seleccione empresa...")) {
            String empresa = jComboBoxEmpresas.getSelectedItem().toString();
            String[] idEmpresa = empresa.split("-");
            query += "Documentos.id_empresa = '" + idEmpresa[0] + "' and ";
        }

        // Añadimos la fecha de inicio a la query
        if (!jFormattedTextFieldFechaInicio.getText().equals("  /  /    ")) {
            String fechaInicio = jFormattedTextFieldFechaInicio.getText();            
            date = null;
            try {
                date = dateFormat.parse(fechaInicio);
            } catch (ParseException ex) {
                Logger.getLogger(PentahoTestView.class.getName()).log(Level.SEVERE, null, ex);
            }
            fechaInicio = dateFormatSqlite.format(date);
            query += "Documentos.fecha >= " + Integer.valueOf(fechaInicio) + " and ";
        }
        
        // Añadimos la fecha de fin a la query
        if (!jFormattedTextFieldFechaFin.getText().equals("  /  /    ")) {
            String fechaFin = jFormattedTextFieldFechaFin.getText();            
            date = null;
            try {
                date = dateFormat.parse(fechaFin);
            } catch (ParseException ex) {
                Logger.getLogger(PentahoTestView.class.getName()).log(Level.SEVERE, null, ex);
            }
            fechaFin = dateFormatSqlite.format(date);
            query += "Documentos.fecha <= " + Integer.valueOf(fechaFin) + " and ";
        }
        
        
        query += "Documentos.id_tipo = Tipos.id and Documentos.id_cliente = Clientes.id and Documentos.id_empresa = Empresas.id order by Documentos.id desc;";
        QueryManager queryManager = new QueryManager();
        resultSet = queryManager.execute(query);
        String[] fila = new String[6];
        
        try {
            while (resultSet.next()) {
                fila[0] = String.valueOf(resultSet.getInt("docId"));
                fila[1] = resultSet.getString("tipoId") + "-" + resultSet.getString("tipoDesc");
                fila[2] = resultSet.getString("docNumeracion");
                fila[3] = String.valueOf(resultSet.getInt("docFecha")).substring(6, 8) + "/" + String.valueOf(resultSet.getInt("docFecha")).substring(4, 6) + "/" + String.valueOf(resultSet.getInt("docFecha")).substring(0, 4);
                fila[4] = resultSet.getString("clienteId") + "-" + resultSet.getString("clienteNombre") + " " + resultSet.getString("clienteApellidos");
                fila[5] = new DecimalFormat("#0.00").format(resultSet.getDouble("docImporteTotal"));

                modeloDocumentos.addRow(fila);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PentahoTestView.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            queryManager.statementClose();
            queryManager.connectionClose();
        }
    }//GEN-LAST:event_jButtonEjecutarSeleccionActionPerformed

    private void jButtonEjecutarReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEjecutarReportActionPerformed
        // Cambiamos cursor
        setCursor(new Cursor(Cursor.WAIT_CURSOR));
        
        // Fecha y hora del momento de la impresión
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
        
        // Create an output filename
        final File outputFilename = new File("out" + File.separator + "Report_" + jTextFieldSeleccion.getText() + "_" + dateFormat.format(date) + ".pdf");

        try {
            // Generate the report
            new RenderReport(Integer.valueOf(jTextFieldSeleccion.getText())).generateReport(AbstractReportGenerator.OutputType.PDF, outputFilename);
            Desktop.getDesktop().open(outputFilename);
            JOptionPane.showMessageDialog(this, "Impreso generado correctamente", "Pentaho Test", 1);
        } catch (IllegalArgumentException | IOException | ReportProcessingException ex) {
            Logger.getLogger(PentahoTestView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error al generar el impreso", "Error", 0);
        } finally {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_jButtonEjecutarReportActionPerformed

    private void jMenuItemConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemConfiguracionActionPerformed
        new ConfigView(this, true).setVisible(true);
    }//GEN-LAST:event_jMenuItemConfiguracionActionPerformed

    private void jMenuItemClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClientesActionPerformed
        new TableView(this, true, "Clientes", "select * from Clientes order by id asc;", new Object[] { "Id", "Nombre", "Apellidos", "Dirección", "Cod.Postal", "Localidad", "Provincia", "País", "DNI/CIF", "Tel.Fijo", "Tel.Móvil", "Fax", "Email", "Web" }).setVisible(true);
    }//GEN-LAST:event_jMenuItemClientesActionPerformed

    private void jMenuItemDocumentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDocumentosActionPerformed
        new TableView(this, true, "Documentos", "select * from Documentos order by id asc;", new Object[] { "Id", "Numeración", "Fecha", "Hora", "Observaciones", "Imp.Base", "Porc.IVA", "Imp.Cuota", "Imp.Total", "Forma Pago", "Id Tipo", "Id Empresa", "Id Cliente" }).setVisible(true);
    }//GEN-LAST:event_jMenuItemDocumentosActionPerformed

    private void jMenuItemTiposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTiposActionPerformed
        new TableView(this, true, "Tipos", "select * from Tipos order by id asc;", new Object[] { "Id", "Descripción", "Id Report" }).setVisible(true);
    }//GEN-LAST:event_jMenuItemTiposActionPerformed

    private void jMenuItemReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReportsActionPerformed
        new TableView(this, true, "Reports", "select * from Reports order by id asc;", new Object[] { "Id", "Nombre", "Descripción", "Nro.Copias" }).setVisible(true);
    }//GEN-LAST:event_jMenuItemReportsActionPerformed

    private void jMenuItemEmpresasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEmpresasActionPerformed
        new TableView(this, true, "Empresas", "select * from Empresas order by id asc;", new Object[] { "Id", "Nombre", "Dirección", "Localidad", "Cod.Postal", "Provincia", "País", "CIF", "Teléfono", "Fax", "Email", "Web", "LOPD", "Reg.Mercantil" }).setVisible(true);
    }//GEN-LAST:event_jMenuItemEmpresasActionPerformed

    private void jButtonNuevoDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNuevoDocumentoActionPerformed
        new DocumentosView(this, true, "").setVisible(true);
    }//GEN-LAST:event_jButtonNuevoDocumentoActionPerformed

    private void jTextFieldSeleccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldSeleccionFocusLost
        if (jTextFieldSeleccion.getText().equals("")) {
            jButtonEjecutarReport.setEnabled(false);
            jButtonEditarDocumento.setEnabled(false);
        } else {
            jButtonEjecutarReport.setEnabled(true);
            jButtonEditarDocumento.setEnabled(true);
        }
    }//GEN-LAST:event_jTextFieldSeleccionFocusLost

    private void jButtonEditarDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEditarDocumentoActionPerformed
        new DocumentosView(this, true, jTextFieldSeleccion.getText()).setVisible(true);
    }//GEN-LAST:event_jButtonEditarDocumentoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfiguracion;
    private javax.swing.JButton jButtonEditarDocumento;
    private javax.swing.JButton jButtonEjecutarReport;
    private javax.swing.JButton jButtonEjecutarSeleccion;
    private javax.swing.JButton jButtonNuevoDocumento;
    private javax.swing.JComboBox jComboBoxClientes;
    private javax.swing.JComboBox jComboBoxEmpresas;
    private javax.swing.JComboBox jComboBoxTipos;
    private javax.swing.JFormattedTextField jFormattedTextFieldFechaFin;
    private javax.swing.JFormattedTextField jFormattedTextFieldFechaInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemClientes;
    private javax.swing.JMenuItem jMenuItemConfiguracion;
    private javax.swing.JMenuItem jMenuItemDocumentos;
    private javax.swing.JMenuItem jMenuItemEmpresas;
    private javax.swing.JMenuItem jMenuItemReports;
    private javax.swing.JMenuItem jMenuItemSalir;
    private javax.swing.JMenuItem jMenuItemTipos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTableDocumentos;
    private javax.swing.JTextField jTextFieldSeleccion;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel modeloDocumentos;
    private ResultSet resultSet;
    private String query;
}
