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
import GUI.jframe1;
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

public class modificar_producto extends javax.swing.JDialog implements KeyListener {
    Productos mainProduct=new Productos();
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

    public modificar_producto(java.awt.Frame parent, boolean modal,int id_producto) throws SQLException {
        super(parent, modal);
        initComponents();
        iniciar(id_producto);

    }

    modificar_producto(int id_producto) throws SQLException {
        initComponents();
        iniciar(id_producto);
    }

    modificar_producto(int u, int idliq, int rut_emp, int p, String nom_emp, String nom_benef, int id_producto) throws SQLException { // constructor que viene de el listado de solicitudes de seguro
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
    
        //jTextField16.setText(p + " %");
        iniciar(id_producto);
        //jTextField1.setText("0");
    }

    public void iniciar(int id_producto) throws SQLException {
        Productos producto = new Productos();
        
        metodosDB metodos= new metodosDB();
        producto=metodos.getProductoById(id_producto);
        mainProduct=producto;
          jTextField22.setText(producto.getNombre());
        jTextField23.setText(producto.getMarca()); 
        jTextField24.setText(producto.getCantidadActual().toString()); 
        jTextField25.setText(producto.getPrecioCompra().toString()); 
        jTextField26.setText(producto.getPrecioVenta().toString());
        jTextField2.setText(producto.getColor().toString());
        jTextField1.setText(producto.getCodigo_barra().toString());
      System.out.println(producto.getTipo());
 
        if(producto.getTipo().equals("SELECCIONAR ITEM")){
                 jComboBox1.setSelectedIndex(0);  
        }
         if(producto.getTipo().equals("POLERA")){
                 jComboBox1.setSelectedIndex(1);  
        }
          if(producto.getTipo().equals("SHORT")){
                 jComboBox1.setSelectedIndex(2);  
        }
           if(producto.getTipo().equals("POLERON")){
                 jComboBox1.setSelectedIndex(3);  
        }
            if(producto.getTipo().equals("JOCKEY")){
                 jComboBox1.setSelectedIndex(4);  
        }
                 if(producto.getTipo().equals("ESCUDO")){
                 jComboBox1.setSelectedIndex(5);  
        }
             if(producto.getTipo().equals("PADS")){
                 jComboBox1.setSelectedIndex(6);  
        }
              if(producto.getTipo().equals("FOCOS")){
                 jComboBox1.setSelectedIndex(7);  
        }
               if(producto.getTipo().equals("GUANTILLAS")){
                 jComboBox1.setSelectedIndex(8);  
        }
                if(producto.getTipo().equals("GUANTES DE BOXEO")){
                 jComboBox1.setSelectedIndex(9);  
        }
             if(producto.getTipo().equals("CANILLERAS ENTRENAMIENTO")){
                 jComboBox1.setSelectedIndex(10);  
        }    if(producto.getTipo().equals("GI JIUJITSU")){
                 jComboBox1.setSelectedIndex(11);  
        }    if(producto.getTipo().equals("CINTURONES JIUJITSU")){
                 jComboBox1.setSelectedIndex(12);  
        }    if(producto.getTipo().equals("TIMER")){
                 jComboBox1.setSelectedIndex(13);  
        }    if(producto.getTipo().equals("ZAPATILLAS DE BOXEO")){
                 jComboBox1.setSelectedIndex(14);  
        }    if(producto.getTipo().equals("BUCALES")){
                 jComboBox1.setSelectedIndex(15);  
        } if(producto.getTipo().equals("VENDAS")){
                 jComboBox1.setSelectedIndex(16);  
        } if(producto.getTipo().equals("ELEVATION TRAINING MASK ")){
                 jComboBox1.setSelectedIndex(17);  
        } if(producto.getTipo().equals("ACCESORIOS")){
                 jComboBox1.setSelectedIndex(18);  
        }
          if(producto.getTipo().equals("SPATS")){
                 jComboBox1.setSelectedIndex(19);  
        }     
        
       
        jTextField27.setText(producto.getProveedor()); 
        jTextField20.setText(producto.getTalla()); 
      //  jTextField8.setEditable(false);
        //  jTextField10.setEditable(false);
        //  jTextField7.setEditable(false);
        //  jTextField2.setEditable(false);
        //   jTextField9.setEditable(false);
        //   jTextField6.setEditable(false);
      //  jDateChooser1.setDate(c.getTime());
        //   jDateChooser1.addKeyListener(this);
    
//        jTable1.getColumn("NIVEL").setPreferredWidth(1);
        // jTable1.getColumn("# VECES").setPreferredWidth(8);
        // jTable1.getColumn("BENEFICIO").setPreferredWidth(15);
        // jTable1.getColumn("COD.").setPreferredWidth(50);
        // jTable1.getColumn("COPAGO").setPreferredWidth(39);
        //// String[] aux = this.getEstadoDetSol().split("=");
 
		
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
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MODIFICAR PRODUCTO");
        setModal(true);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/accept.png"))); // NOI18N
        jButton1.setText("GUARDAR PRODUCTO(S)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete.png"))); // NOI18N
        jButton2.setText("CERRAR");
        jButton2.setFocusCycleRoot(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Backup Green Button.png"))); // NOI18N
        jButton4.setText("ATRAS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 255, 102));
        jButton3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/emblem-sales.png"))); // NOI18N
        jButton3.setText("ESCANEAR CODIGO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel10.setText("COSTO");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setText("TIPO");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("MARCA");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("NOMBRE  PRODUCTO");

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

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel11.setText("PRECIO VENTA");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel12.setText("PROVEEDOR");

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

        jTextField25.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField25.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField25ActionPerformed(evt);
            }
        });

        jTextField26.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField26.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField26ActionPerformed(evt);
            }
        });

        jTextField27.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField27.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField27ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONAR ITEM", "POLERA", "SHORT", "POLERON ", "JOCKEY ", "ESCUDO ", "PADS", "FOCOS ", "GUANTILLAS ", "GUANTES DE BOXEO", "CANILLERAS ENTRENAMIENTO", "GI JIUJITSU", "CINTURONES JIUJITSU", "TIMER ", "ZAPATILLAS DE BOXEO", "BUCALES ", "VENDAS ", "ELEVATION TRAINING MASK ", "ACCESORIOS", "SPATS" }));
        jComboBox1.setSelectedItem(jComboBox1);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel7.setText("COLOR");

        jTextField2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField20)
                                            .addComponent(jTextField22)
                                            .addComponent(jTextField23)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField24)
                                            .addComponent(jTextField25)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jTextField26))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField27)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jTextField2)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(257, 257, 257)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)))
                        .addGap(0, 2, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextField2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
metodosDB metodos= new metodosDB();
if(jTextField24.getText().equals("")||jTextField1.getText().equals("") 
        ||jTextField1.getText().equals("")||jTextField2.getText().equals("")
         ||jTextField23.getText().equals("")||jTextField22.getText().equals("")
         ||jTextField25.getText().equals("")||jTextField26.getText().equals("")
         ||jTextField27.getText().equals("")||jTextField20.getText().equals("")){
       jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "FALTA LLENAR UN CAMPO");
   }else{
    
     if(jComboBox1.getSelectedIndex()==0){
           jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "NO SELECCIONO TIPO");
    }else{
    mainProduct.setCantidadActual(Integer.parseInt(jTextField24.getText()));
            mainProduct.setCodigo_barra(jTextField1.getText());
            mainProduct.setColor(jTextField2.getText()); // falt hacer el del COLOR))
                    mainProduct.setMarca(jTextField23.getText());
                            mainProduct.setNombre(jTextField22.getText());
                            mainProduct.setPrecioCompra(Integer.parseInt(jTextField25.getText()));
                                    mainProduct.setPrecioVenta(Integer.parseInt(jTextField26.getText()));
                                    mainProduct.setProveedor(jTextField27.getText());
                                    mainProduct.setTalla(jTextField20.getText());
                                            mainProduct.setTipo(jComboBox1.getSelectedItem().toString());
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     
        try {
            metodos.updateProductos(mainProduct);
            jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "PRODUCTO MODIFICADO CON EXITO");
            this.dispose();
        } catch (SQLException ex) {
            Logger.getLogger(modificar_producto.class.getName()).log(Level.SEVERE, null, ex);
        }}
}
    
       

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new Metodos_objetos().testProductos(new metodosDB().getProductos());
        } catch (SQLException ex) {
            Logger.getLogger(modificar_producto.class.getName()).log(Level.SEVERE, null, ex);
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
jTextField1.setText("");
        jTextField1.requestFocus();
        // jTextField9ActionPerformed(evt);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jTextField22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField22ActionPerformed
        // TODO add your handling code here:safsd
       
    }//GEN-LAST:event_jTextField22ActionPerformed

    private void jTextField23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField23ActionPerformed

    private void jTextField24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField24ActionPerformed

    private void jTextField25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField25ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField25ActionPerformed

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed
 private void limpiar(){
        jTextField22.setText("");
        jTextField23.setText(""); 
        jTextField24.setText(""); 
        jTextField25.setText(""); 
        jTextField26.setText(""); 
        jComboBox1.setSelectedIndex(0); 
        jTextField27.setText(""); 
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
            java.util.logging.Logger.getLogger(modificar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(modificar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(modificar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(modificar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

      
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
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
