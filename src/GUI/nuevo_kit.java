/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.Metodos_objetos;
import Clases.funciones;
import ObjetosDB.Productos;
import ObjetosDB.metodosDB;
import com.mxrck.autocompleter.TextAutoCompleter;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class nuevo_kit extends javax.swing.JDialog implements KeyListener {

    int usuario, a = 1, mnsj = 0, global_update, global_suma = 0, op = 0, id_liq = 0;
    Date fecha = new Date();
    String x, aux_1 = null;
    float pro;
    private JTextField tf;
    int i = -1, flag = 0, aux2, id_sol = 0, cuenta = 0, monto = 0;
    String aux = null, arancel = null, aux4;
    boolean nuevo = true;
    String[][] cuentas;
    String[] aux3 = null, aux5;
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    TextAutoCompleter autocompleter;
    DefaultTableModel modelo = new DefaultTableModel() {
private JComboBox combo1;
        @Override
        public boolean isCellEditable(int row, int column) { // sobreescribe el metodo para convertir la celda ingresada en NO editable
            return false;
        }
    };

    public nuevo_kit(java.awt.Frame parent, boolean modal) throws SQLException {
        super(parent, modal);
        initComponents();
        iniciar();

    }

    nuevo_kit() throws SQLException {
        initComponents();
        iniciar();
    }

    nuevo_kit(int u, int idliq, int rut_emp, int p, String nom_emp, String nom_benef, int rut_benef) throws SQLException { // constructor que viene de el listado de solicitudes de seguro
        initComponents();
        String rut = String.valueOf(rut_emp);
        usuario = u;
        funciones f = new funciones();
   //    jTextField1.setEditable(false);
        //    jTextField18.setEditable(false);
        //   jTextField22.setEditable(false);

      //  jTextField18.setText(rut_benef + "");
        //  jTextField19.setText(f.validar_rut(rut_benef + ""));
        // jTextField8.setText(rut);
        //  jTextField11.setText(f.validar_rut(rut));
        pro = Float.parseFloat(p + "") / 100;
        id_liq = idliq;
        //jTextField16.setText(p + " %");
        iniciar();
        //jTextField1.setText("0");
    }

    public void iniciar() throws SQLException {

      //  jTextField8.setEditable(false);
        //  jTextField10.setEditable(false);
        //  jTextField7.setEditable(false);
        //  jTextField2.setEditable(false);
        //   jTextField9.setEditable(false);
        //   jTextField6.setEditable(false);
        Calendar c = Calendar.getInstance();
      //  jDateChooser1.setDate(c.getTime());
        //   jDateChooser1.addKeyListener(this);
        jTable1.addKeyListener(this);
        String t[] = {"NOMBRE PRODUCTO","TALLA","MARCA", "TIPO", "PRECIO VENTA", "COSTO", "CANTIDAD","PROVEEDOR","COD."};
        modelo.setColumnIdentifiers(t);
        jTable1.setRowHeight(22);
        jTable1.setModel(modelo);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(SwingConstants.RIGHT);
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr2);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr2);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr2);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tcr2);
//        jTable1.getColumn("NIVEL").setPreferredWidth(1);
        // jTable1.getColumn("# VECES").setPreferredWidth(8);
        // jTable1.getColumn("BENEFICIO").setPreferredWidth(15);
        // jTable1.getColumn("COD.").setPreferredWidth(50);
        // jTable1.getColumn("COPAGO").setPreferredWidth(39);
        //// String[] aux = this.getEstadoDetSol().split("=");
       metodosDB f = new metodosDB();
        modelo.setNumRows(0);
        int aux3;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            ArrayList<Productos> aux2 = new ArrayList<Productos>();
		         aux2 = f.getProductos();//Cargar resultados , debe ser por nombres
                    Object[] object = new Object[10];
                    int i=0;
                    while(aux2.size()>i){
        object[0]  = aux2.get(i).getId_producto();
        object[1] = aux2.get(i).getNombre();
        object[2] = aux2.get(i).getTalla();
        object[3] = aux2.get(i).getMarca();
        object[4] = aux2.get(i).getCantidadActual();
        object[5] = aux2.get(i).getTipo();
        object[6] = aux2.get(i).getProveedor();
        object[7] = aux2.get(i).getPrecioCompra();
        object[8] = aux2.get(i).getPrecioVenta();
        object[9] = aux2.get(i).getCodigo_barra();
         modelo.addRow(object);i++;}
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
		
        cargar();
    }

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            evt.setKeyCode(java.awt.event.KeyEvent.VK_TAB);
            //este codigo lo que hace es convertir el enter en tab            
        }

    }

    public void cargar() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        final SwingWorker worker = new SwingWorker() {

            @Override
            protected Void doInBackground() throws Exception {
                try {/*
                     String[] ctas = getcuentas().split("=");
                     cuentas = new String[ctas[0].split(";").length][ctas.length];
                     for (int j = 0; j < ctas.length; j++) {
                     String[] aux = ctas[j].split(";");
                     cuentas[0][j] = aux[0];
                     cuentas[1][j] = aux[1];
                     cuentas[2][j] = aux[2];
                     cuentas[3][j] = aux[3];
                     cuentas[4][j] = aux[4];
                     cuentas[5][j] = aux[5];
                     }*/

                } catch (Exception ex) {
                }
                setCursor(Cursor.getDefaultCursor());
                return null;
            }
        };
        worker.execute();
    }

    int buscar_cta(String nro) {
        for (int j = 0; j < cuentas[0].length; j++) {
            if (cuentas[0][j].equals(nro)) {
                return j;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESAR SOLICITUD DEL SEGURO CATASTROFICO");
        setModal(true);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton1.setText("GUARDAR PRODUCTO(S)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton2.setText("CERRAR");
        jButton2.setFocusCycleRoot(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setAutoCreateRowSorter(true);
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
        jTable1.setSurrendersFocusOnKeystroke(true);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton4.setText("ATRAS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 0, 0));
        jButton3.setText("ESCANEAR CODIGO KIT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton8.setText("ELIMINAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("MARCA");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("NOMBRE NUEVO PRODUCTO");

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel23.setText("TALLA");

        jTextField20.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel18.setText("CANTIDAD");

        jTextField22.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField22.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField22ActionPerformed(evt);
            }
        });

        jTextField23.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField23.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField23ActionPerformed(evt);
            }
        });

        jTextField24.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField24.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField24ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton9.setText("AGREGAR");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton5.setText("BUSCAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NOMBRE", "ID PRODUCTO", "MARCA" }));

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("BUSCAR");

        jTable2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable2.setToolTipText("");
        jTable2.setColumnSelectionAllowed(true);
        jTable2.getTableHeader().setReorderingAllowed(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 506, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(283, 283, 283)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField20)
                                    .addComponent(jTextField23)
                                    .addComponent(jTextField24)
                                    .addComponent(jTextField22)))
                            .addComponent(jScrollPane1))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        for (int j = 0; j < modelo.getRowCount(); j++) {
            ArrayList<Productos> productos = new ArrayList();
 //Productos(String nombre, String marca, String talla, String color, int precioCompra, int precioVenta, String proveedor, int cantidadActual) { //Constructor
            String color = ""; //deberia ser modelo.getColor
            String tipo = "";
            String codigo_barra = "";
            Productos producto = new Productos(0,modelo.getValueAt(j, 0).toString(),modelo.getValueAt(j, 2).toString(),modelo.getValueAt(j, 1).toString(),color,(Integer) modelo.getValueAt(j, 5),(Integer) modelo.getValueAt(j, 4),modelo.getValueAt(j, 7).toString(),(Integer) modelo.getValueAt(j, 6),tipo,codigo_barra,true);
           
            //falta para TIPO y para CODIGO
            productos.add(producto);
             System.out.println("Nombre Producto:"+producto.getNombre());
              System.out.println("Nombre Marca:"+producto.getMarca());
                System.out.println("Talla:"+producto.getTalla());
                  System.out.println("Nombre Proveedor:"+producto.getProveedor());
                    System.out.println("Precio Compra:"+producto.getPrecioCompra());
                      System.out.println("Precio Venta:"+producto.getPrecioVenta());
                      
                      
                      //validar campos
                      //guardar en la base de datos si es consistente
                     
                      this.dispose(); //dispose para salir de la ventana
        }
    
       

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new Metodos_objetos().testProductos(new metodosDB().getProductos());
        } catch (SQLException ex) {
            Logger.getLogger(nuevo_kit.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        JFrame frame = null;
      //  dialog_sol_seg a = new dialog_sol_seg(frame, usuario);
        // a.setLocationRelativeTo(null);
        this.dispose();
        // a.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        // jTextField9ActionPerformed(evt);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        funciones f = new funciones();
        int linea = jTable1.getSelectedRow();
        if (linea != -1) {
            modelo.removeRow(linea);
            int p = 0, b2 = 0;
            for (int j = 0; j < jTable1.getRowCount(); j++) {
                p = p + (Integer.valueOf(modelo.getValueAt(j, 4).toString()) * Integer.valueOf(modelo.getValueAt(j, 5).toString()));
                b2 = b2 + Integer.valueOf(modelo.getValueAt(j, 6).toString());
            }
          //  jTextField13.setText(f.getNumcPto(p));
            //    jTextField14.setText(f.getNumcPto(b2));
            //    jTextField18.requestFocus();
            i = -1;
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String aux3[];
        int linea = jTable1.getSelectedRow();
        if (evt.getClickCount() == 2 & linea != -1) {

            if (!modelo.getValueAt(linea, 3).toString().equals("-")) { // cuenta con arancel
                //      jTextField2.setEnabled(true);
                //      jTextField6.setEnabled(true);
                //      jTextField2.setText(modelo.getValueAt(linea, 2).toString());
                //      jTextField6.setText(modelo.getValueAt(linea, 3).toString().replace("-", ""));
            } else {
              //  jTextField2.setEnabled(false);
                //  jTextField6.setEnabled(false);
            }
            i = linea;
            jComboBox1.setEnabled(true);
           // jTextField7.setEnabled(true);
            // jTextField9.setEnabled(true);
            int porciento = 0;// Integer.valueOf(jTextField16.getText().replace("%", "").trim());
            int monto = Integer.valueOf(modelo.getValueAt(linea, 4).toString());
          //  jTextField7.setText((monto * 100 / porciento) + "");
            //  jTextField22.setText(modelo.getValueAt(linea, 4).toString());
            //  jTextField9.setText(modelo.getValueAt(linea, 5).toString());
            //  jTextField10.setText(modelo.getValueAt(linea, 6).toString());
            jComboBox1.setSelectedItem(modelo.getValueAt(linea, 7).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:safsd
       
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

        

        //DATOS DE LA TABLA
        Object[] object = new Object[10];
        object[0] = jTextField22.getText();
        object[1] = jTextField20.getText();
        object[2] = jTextField23.getText();
     
        object[3] = (String)jComboBox1.getSelectedItem();
  
        object[6] = Integer.parseInt(jTextField24.getText());
        object[8] = "Codigo";
        
        modelo.addRow(object);
            //TERMINO DATOS TABLA
  limpiar();

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        metodosDB f = new metodosDB();
        modelo.setNumRows(0);
        int aux3;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Productos aux2 = new Productos();
        if (jComboBox1.getSelectedIndex()==0) { // si quiere buscar por solicitud
            if (jTextField1.getText().isEmpty() | jTextField1.getText().equals("")) {  // si el campo esta vacio
                this.setCursor(Cursor.getDefaultCursor());
                mnsj = JOptionPane.showConfirmDialog(null, "DEBE INGRESAR UN NOMBRE", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                try {
                    aux2 = f.getProductoById(Integer.parseInt(jTextField1.getText()));//Cargar resultados , debe ser por nombres
                    Object[] object = new Object[10];
                    object[0]  = aux2.getId_producto();
                    object[1] = aux2.getNombre();
                    object[2] = aux2.getTalla();
                    object[3] = aux2.getMarca();
                    object[4] = aux2.getCantidadActual();
                    object[5] = aux2.getTipo();
                    object[6] = aux2.getProveedor();
                    object[7] = aux2.getPrecioCompra();
                    object[8] = aux2.getPrecioVenta();
                    object[9] = aux2.getCodigo_barra();
                    modelo.addRow(object);
                } catch (SQLException ex) {
                    Logger.getLogger(buscar_producto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        if (jComboBox1.getSelectedIndex()==1) { // si quiere buscar por rut
            if (jTextField1.getText().isEmpty() | jTextField1.getText().equals("")) {  // si el campo esta vacio
                this.setCursor(Cursor.getDefaultCursor());
                mnsj = JOptionPane.showConfirmDialog(null, "DEBE INGRESAR UN ID DE PRODUCTO", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                try {
                    aux2 = f.getProductoById(Integer.parseInt(jTextField1.getText()));//Cargar resultados , debe ser por nombres
                    Object[] object = new Object[10];
                    object[0]  = aux2.getId_producto();
                    object[1] = aux2.getNombre();
                    object[2] = aux2.getTalla();
                    object[3] = aux2.getMarca();
                    object[4] = aux2.getCantidadActual();
                    object[5] = aux2.getTipo();
                    object[6] = aux2.getProveedor();
                    object[7] = aux2.getPrecioCompra();
                    object[8] = aux2.getPrecioVenta();
                    object[9] = aux2.getCodigo_barra();
                    modelo.addRow(object);
                } catch (SQLException ex) {
                    Logger.getLogger(buscar_producto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
        if (jComboBox1.getSelectedIndex()==2) { // si quiere buscar por rut
            if (jTextField1.getText().isEmpty() | jTextField1.getText().equals("")) {  // si el campo esta vacio
                this.setCursor(Cursor.getDefaultCursor());
                mnsj = JOptionPane.showConfirmDialog(null, "DEBE INGRESAR UNA MARCA", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }else{
                try {
                    aux2 = f.getProductoById(Integer.parseInt(jTextField1.getText()));//Cargar resultados , debe ser por nombres
                    Object[] object = new Object[10];
                    object[0]  = aux2.getId_producto();
                    object[1] = aux2.getNombre();
                    object[2] = aux2.getTalla();
                    object[3] = aux2.getMarca();
                    object[4] = aux2.getCantidadActual();
                    object[5] = aux2.getTipo();
                    object[6] = aux2.getProveedor();
                    object[7] = aux2.getPrecioCompra();
                    object[8] = aux2.getPrecioVenta();
                    object[9] = aux2.getCodigo_barra();
                    modelo.addRow(object);
                } catch (SQLException ex) {
                    Logger.getLogger(buscar_producto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        jButton2ActionPerformed(evt);

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int i = jTable1.getSelectedRow();
        Productos op = new Productos();
        if (evt.getClickCount() == 2 && i != -1) {
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            op.setId_producto(Integer.parseInt(modelo.getValueAt(i, 0).toString()));
            op.setNombre(modelo.getValueAt(i, 1).toString());
            op.setTalla(modelo.getValueAt(i, 2).toString());
            op.setMarca(modelo.getValueAt(i, 3).toString());
            op.setCantidadActual(Integer.parseInt(modelo.getValueAt(i, 4).toString()));
            op.setTipo(modelo.getValueAt(i, 5).toString());
            op.setProveedor(modelo.getValueAt(i, 6).toString());
            op.setPrecioCompra(Integer.parseInt(modelo.getValueAt(i, 7).toString()));
            op.setPrecioVenta(Integer.parseInt(modelo.getValueAt(i, 8).toString()));
            op.setCodigo_barra(modelo.getValueAt(i, 9).toString());

            modificar_producto a = null;
            try {
                a = new modificar_producto(op.getId_producto());
            } catch (SQLException ex) {
                Logger.getLogger(buscar_producto.class.getName()).log(Level.SEVERE, null, ex);
            }
            a.setLocationRelativeTo(null);
            this.setVisible(false);
            this.setCursor(Cursor.getDefaultCursor());
            a.setVisible(true);
        }
    }//GEN-LAST:event_jTable2MouseClicked
 private void limpiar(){
        jTextField22.setText("");
        jTextField23.setText(""); 
        jTextField24.setText(""); 
     
        jComboBox1.setSelectedIndex(0); 
  
        jTextField20.setText(""); 
    }
    public static void main(String args[]) {

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
            java.util.logging.Logger.getLogger(nuevo_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nuevo_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nuevo_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nuevo_kit.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                nuevo_kit dialog=null;
                try {
                    dialog = new nuevo_kit(new javax.swing.JFrame(), true);
                } catch (SQLException ex) {
                    Logger.getLogger(nuevo_kit.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyPressed(KeyEvent e) {
        int op = 0, mnsj;
        ActionEvent ex = null;
        //if (jButton2.hasFocus()) {
        if (e.getKeyCode() == 116) { // tecla F5
            //  jTextField8.setText("");
            this.jButton1ActionPerformed(ex);

        }

        if (e.getKeyCode() == 27) { // tecla ESC
            if (e.getKeyCode() == 27) {
                dispose();
            }
        }
        /*  if (e.getKeyCode() == 10 & jTextField11.hasFocus()) { // tecla enter
         // this.rutSocioActionPerformed(ex);
         }*/

    }

    @Override
    public void keyReleased(KeyEvent e) {
        ActionEvent ex = null;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        ActionEvent ex = null;

    }

    public boolean validarFecha(int dia, int mes, int anio) {
        if (0 < mes & mes > 13) {
            return false;
        } else {
            if (0 < dia & dia > 32) {
                return false;
            } else {
                if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia > 30)) {
                    return false;
                } else {
                    if (mes == 2 && bisiesto(anio) && dia > 29) {
                        return false;
                    } else if (mes == 2 && !bisiesto(anio) && dia > 28) {
                        return false;
                    } else {
                        if (anio + 1 < Calendar.getInstance().getTime().getYear() + 1900) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }
    }

    public boolean bisiesto(int anio) {
        if (anio % 400 == 0) {
            return true;
        } else {
            if (anio % 4 == 0 && anio % 100 != 0) {
                return true;
            } else {
                return false;
            }
        }
    }

}
