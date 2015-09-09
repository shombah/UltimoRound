package GUI;

import Clases.funciones;
import ObjetosDB.OrdenDeVenta;
import ObjetosDB.metodosDB;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import metodos2.PrestamosObp;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class libro_diario extends javax.swing.JDialog implements MouseListener {

    int usuario, mnsj, num_celdas = 45;
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    DefaultTableModel modelo = new DefaultTableModel() {

        public boolean isCellEditable(int row, int column) { // sobreescribe el metodo para convertir la celda ingresada en NO editable
            return false;
        }
    };

    public libro_diario(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        iniciar();
    }

    libro_diario(java.awt.Frame parent, int rut) throws SQLException {
        super(parent, true);
        initComponents();
        this.usuario = rut;
        iniciar();
    }

    private void iniciar() throws SQLException { 
        String titulos[] = {"ID VENTA", "FECHA", "MONTO TOTAL", "NUMERO BOLETA", "MEDIO PAGO", "ESTADO PRESUPUESTO", "RUT CLIENTE"};
        modelo.setColumnIdentifiers(titulos);
        modelo.setNumRows(0);
        jTable1.setModel(modelo);
        int suma=0;
        jTextField3.setText("0");
        jTextField3.setEditable(false);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr2);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jButton1.addMouseListener(this);
        jButton2.addMouseListener(this);
        jProgressBar1.setVisible(false);
        metodosDB metodos = new metodosDB();
       
       
        modelo.setNumRows(0);
         ArrayList<OrdenDeVenta> ordenesDia = new metodosDB().getVentasDia(getFecha());
     int i=0;
            for(OrdenDeVenta orden: ordenesDia)
            {
            Object[] object = new Object[6];
            object[0] = orden.getIdOrdenVenta();
            object[1] = orden.getNumeroBoleta();
            object[2] = orden.getMontoTotal();
            object[3] = orden.getFecha();
            object[4] = orden.getMedioPago();
            object[5] = orden.getCliente().getNombre();
            modelo.addRow(object);
            suma = suma + Integer.parseInt(orden.getMontoTotal());
        i++;

            
            }  jTextField3.setText(Integer.toString(i));
        //Funcion que debe cargar todos los prestamos desde la BD y agregarlos a la tabla
    }

    public void ListarCompPend() {
        jProgressBar1.setVisible(true);
        final SwingWorker worker = new SwingWorker() {

            @Override
            protected Void doInBackground() throws Exception {
                try {
                    jProgressBar1.setString("ESPERE UN MOMENTO.");
                    jProgressBar1.setStringPainted(true);
                    jProgressBar1.setIndeterminate(true);
                    //String r1 = getListadoCompPend();//DEBE IR EL SERVICIO DE PRESTAMOS IMPAGOS
                   String  r1=null;
                    String[] r2 = null;
                    if (modelo.getRowCount()==0) {
                        setCursor(Cursor.getDefaultCursor());
                        jProgressBar1.setVisible(false);
                        mnsj = JOptionPane.showConfirmDialog(null, "NO SE REGISTRAN PRESTAMOS PARA EXPORTAR.", "INFORMACIÓN", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                        return null;
                    } else {
                         XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet1 = workbook.createSheet("PRESTAMOS");
      
        funciones f = new funciones();
        int sel=0;
        int i=0;
        //objetos de la primera hoja "seg cat titulares"
        Map<String, Object[]> data = new TreeMap<String, Object[]>();
               data.put(Integer.toString(sel), new Object[]{"NUMERO", "RUT", "COD", "OBSERVACIONES", "MONTO", "INTERES", "CUOTA", "FECHA INICIO", "FECHA TERMINO", "MONTO PAGADO", "ID_PRESTAMO"});
  ArrayList<PrestamosObp> lista= getPrestamosTodos();
         while(modelo.getRowCount()>i){sel++;
         System.out.println(lista.get(i).getInteres());
         String interes = Float.toString(lista.get(i).getInteres());
       data.put(Integer.toString(sel), new Object[]{Integer.toString(i), Integer.toString(lista.get(i).getRut()),lista.get(i).getCodigo(),lista.get(i).getObservaciones().toString(),lista.get(i).getMonto()
               ,interes,lista.get(i).getCuotas(),lista.get(i).getFechaInicio(), lista.get(i).getFechaTermino(), lista.get(i).getMontoPagado(), lista.get(i).getIdprestamo()});
i++;} 
       

        //Iterate over data and write to sheet
        Set<String> keyset = data.keySet();
        int rownum = 0;
        for (String key : keyset) {
            XSSFRow row = sheet1.createRow(rownum++);
            Object[] objArr = data.get(key);
            int cellnum = 0;
            sheet1.setColumnWidth(2, 256 * 12);
            sheet1.setColumnWidth(3, 256 * 5);
            sheet1.setColumnWidth(7, 256 * 30);
            sheet1.setColumnWidth(8, 256 * 13);
            sheet1.setColumnWidth(9, 256 * 12);
            sheet1.setColumnWidth(13, 256 * 7);
            for (Object obj : objArr) {
                XSSFCell cell = row.createCell(cellnum);
                if (obj instanceof String) {
                    if (row.getRowNum() == 0 | cellnum == 1 | cellnum == 3 | cellnum == 5) {
                        CellStyle cellStyle = workbook.createCellStyle();
                        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
                        cell.setCellStyle(cellStyle);
                    }
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Integer) {
                    if (row.getRowNum() == 0 | cellnum == 1 | cellnum == 3 | cellnum == 5) {
                        CellStyle cellStyle = workbook.createCellStyle();
                        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
                        cell.setCellStyle(cellStyle);
                    }
                    cell.setCellValue((Integer) obj);
                }
                cellnum++;
            }
        }

        

        File file = new File(f.getRuta_formatos() + "PRESTAMOS.xlsx");
        try {
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            if (Desktop.isDesktopSupported() == true) {
                Desktop desktop = Desktop.getDesktop();
                desktop.open(file);
            }
        } catch (Exception e) {
            mnsj = JOptionPane.showConfirmDialog(null, error + e.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            setCursor(Cursor.getDefaultCursor());
            return null;
        }

                    }

                } catch (Exception ex) {
                    jProgressBar1.setVisible(false);
                    mnsj = JOptionPane.showConfirmDialog(null, error + ex.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                }
                setCursor(Cursor.getDefaultCursor());
                return null;
            }
        };
        worker.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jTextField3 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUSCAR COMPROBANTE");
        setModal(true);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/zoom.png"))); // NOI18N
        jButton1.setText("BUSCAR");
        jButton1.setPreferredSize(new java.awt.Dimension(137, 35));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Prestamos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/cancel.png"))); // NOI18N
        jButton2.setText("CERRAR");
        jButton2.setPreferredSize(new java.awt.Dimension(135, 35));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/book_error.png"))); // NOI18N
        jButton4.setText("PENDIENTES");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/script_add.png"))); // NOI18N
        jButton5.setText("ARCHIVO");
        jButton5.setToolTipText("");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/delete.png"))); // NOI18N
        jButton6.setText("RECHAZAR");
        jButton6.setToolTipText("");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jProgressBar1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jTextField3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText("0");
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("REGISTRO(S)");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("BUSCAR POR RUT");
        jLabel3.setToolTipText("");

        jTextField6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    private String getFecha()
 {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        String FechaHoy = dateFormat.format(cal.getTime());
        return FechaHoy;
 }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String r = null;
        String[] aux2, aux3;
        int aux = 0;
        modelo.setNumRows(0);
        if (jTextField6.getText().trim().isEmpty() | jTextField6.getText().trim() == null) {
            mnsj = JOptionPane.showConfirmDialog(null, "DEBE INGRESAR UN DATO PARA INICIAR LA BUSQUEDA.", "INFORMACIÓN", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //r funcion que debe buscar prestamos con las variables correspondientes
       ArrayList<PrestamosObp> lista= getPrestamosByRut(Integer.parseInt(jTextField6.getText()));
        modelo.setNumRows(0);
         Object[] object = new Object[9];
                    int i=0;
                    while(lista.size()>i){
       
        object[0]  = lista.get(i).getRut();
        object[1] = lista.get(i).getObservaciones();
        object[2] = lista.get(i).getCodigo();
        object[3] = lista.get(i).getMonto();
        object[4] = lista.get(i).getInteres();
        object[5] = lista.get(i).getCuotas();
        object[6] = lista.get(i).getFechaInicio();
        object[7] = lista.get(i).getFechaTermino();
        object[8] = lista.get(i).getMontoPagado();
        
         modelo.addRow(object);i++;}
                    jTextField3.setText(Integer.toString(i));
        //Funcion que debe cargar todos los prestamos desde la BD y agregarlos a la tabla


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int i = jTable1.getSelectedRow();
        if (evt.getClickCount() == 2 & i != -1) {
            int flag = 1;
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            if (modelo.getValueAt(i, 2).toString().equals("PENDIENTE") || modelo.getValueAt(i, 2).toString().equals("ACEPTADO")) {
                flag = 0;
            }
            dialog_ing_comp a = new dialog_ing_comp(usuario, Integer.valueOf(modelo.getValueAt(i, 0).toString()), Integer.valueOf(modelo.getValueAt(i, 3).toString().replace(".", "")), modelo.getValueAt(i, 1).toString(), flag, modelo.getValueAt(i, 4).toString(), modelo.getValueAt(i, 5).toString());
            a.setLocationRelativeTo(null);
            this.setVisible(false);
            this.dispose();
            setCursor(Cursor.getDefaultCursor());
            a.setVisible(true);
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        String r = null;
        String[] aux2, aux3;
        int aux = 0;
        modelo.setNumRows(0);
     
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        //r funcion que debe buscar prestamos con las variables correspondientes
       ArrayList<PrestamosObp> lista= getPrestamosImpagos();
         if (lista.isEmpty()) {
            mnsj = JOptionPane.showConfirmDialog(null, "NO SE REGISTRAN PRESTAMOS IMPAGOS.", "INFORMACIÓN", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            return;
        }
       modelo.setNumRows(0);
         Object[] object = new Object[9];
                    int i=0;
                    while(lista.size()>i){
       
        object[0]  = lista.get(i).getRut();
        object[1] = lista.get(i).getObservaciones();
        object[2] = lista.get(i).getCodigo();
        object[3] = lista.get(i).getMonto();
        object[4] = lista.get(i).getInteres();
        object[5] = lista.get(i).getCuotas();
        object[6] = lista.get(i).getFechaInicio();
        object[7] = lista.get(i).getFechaTermino();
        object[8] = lista.get(i).getMontoPagado();
        
         modelo.addRow(object);i++;}   jTextField3.setText(Integer.toString(i));
        //Funcion que debe cargar todos los prestamos desde la BD y agregarlos a la tabla

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        this.ListarCompPend();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        int i = jTable1.getSelectedRow();
        if (i != -1) {
            if (!modelo.getValueAt(i, 2).toString().equals("CERRADO")) {
                this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                String aux = null;//this.rechazarComp(Integer.valueOf(modelo.getValueAt(i, 0).toString()));
                if (aux.equals("creado")) {
                    this.setCursor(Cursor.getDefaultCursor());
                    mnsj = JOptionPane.showConfirmDialog(null, correcto + "\nEL COMPROBANTE SE HA RECHAZADO DEL SISTEMA.", "INFORMACIÓN", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                    funciones f = new funciones();
                    f.limpiar_tabla(modelo);
                    return;
                } else {
                    this.setCursor(Cursor.getDefaultCursor());
                    mnsj = JOptionPane.showConfirmDialog(null, error + aux, "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed

        jButton1ActionPerformed(evt);

    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6KeyPressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(libro_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(libro_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(libro_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(libro_diario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                libro_diario dialog = new libro_diario(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {

                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

    private static String buscarComp(int rut, int cuenta, int idComp, int idSol) {
        metodos.Servicio_Service service = new metodos.Servicio_Service();
        metodos.Servicio port = service.getServicioPort();
        return port.buscarComp(rut, cuenta, idComp, idSol);
    }

    private static String getCompPendientes() {
        metodos.Servicio_Service service = new metodos.Servicio_Service();
        metodos.Servicio port = service.getServicioPort();
        return port.getCompPendientes();
    }

    private static String buscarcompxfolio(int folio) {
        metodos.Servicio_Service service = new metodos.Servicio_Service();
        metodos.Servicio port = service.getServicioPort();
        return port.buscarcompxfolio(folio);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private static ArrayList<PrestamosObp> getPrestamosTodos() {
        metodos2.Servicio2_Service service = new metodos2.Servicio2_Service();
        metodos2.Servicio2 port = service.getServicio2Port();
        return (ArrayList<PrestamosObp>) port.getPrestamosTodos();
    }
     private static ArrayList<PrestamosObp> getPrestamosImpagos() {
        metodos2.Servicio2_Service service = new metodos2.Servicio2_Service();
        metodos2.Servicio2 port = service.getServicio2Port();
        return (ArrayList<PrestamosObp>) port.getPrestamosImpagos();
    }
     private static ArrayList<PrestamosObp> getPrestamosByRut(int rut) {
        metodos2.Servicio2_Service service = new metodos2.Servicio2_Service();
        metodos2.Servicio2 port = service.getServicio2Port();
        return (ArrayList<PrestamosObp>) port.getPrestamosByRut(rut);
    }
}
