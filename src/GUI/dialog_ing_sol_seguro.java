/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.funciones;
import com.mxrck.autocompleter.TextAutoCompleter;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class dialog_ing_sol_seguro extends javax.swing.JDialog implements KeyListener {

    int usuario, a = 1, mnsj = 0, global_update, global_suma = 0, op = 0, id_liq = 0;
    Date fecha = new Date();
    String x, aux_1 = null;
    float pro;
    int i = -1, flag = 0, aux2, id_sol = 0, cuenta = 0, monto = 0;
    String aux = null, arancel = null, aux4;
    boolean nuevo = true;
    String[][] cuentas;
    String[] aux3 = null, aux5;
    DefaultComboBoxModel modelo2 = new DefaultComboBoxModel();
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    TextAutoCompleter autocompleter;
    DefaultTableModel modelo = new DefaultTableModel() {

        @Override
        public boolean isCellEditable(int row, int column) { // sobreescribe el metodo para convertir la celda ingresada en NO editable
            return false;
        }
    };

    public dialog_ing_sol_seguro(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        iniciar();

    }

    dialog_ing_sol_seguro() {
        initComponents();
        iniciar();
    }

    dialog_ing_sol_seguro(int u, int idliq, int rut_emp, int p, String nom_emp, String nom_benef, int rut_benef) { // constructor que viene de el listado de solicitudes de seguro
        initComponents();
        String rut = String.valueOf(rut_emp);
        usuario = u;
        funciones f = new funciones();
   //    jTextField1.setEditable(false);
    //    jTextField18.setEditable(false);
     //   jTextField22.setEditable(false);
        jTextField12.setText(nom_emp);
        jTextField20.setText(nom_benef);
      //  jTextField18.setText(rut_benef + "");
      //  jTextField19.setText(f.validar_rut(rut_benef + ""));
       // jTextField8.setText(rut);
      //  jTextField11.setText(f.validar_rut(rut));
        pro = Float.parseFloat(p + "") / 100;
        id_liq = idliq;
        //jTextField16.setText(p + " %");
        iniciar();
        //jTextField1.setText("0");
        jTextField4.requestFocus();
    }

    public void iniciar() {
        jTextField12.setEditable(false);
        jTextField15.setEditable(false);
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
        jComboBox1.setEnabled(false);
        String t[] = {"NOMBRE PRODUCTO", "COD.", "MARCA", "TIPO", "PRECIO", "COSTO", "VENTA", "CANTIDAD"};
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
        for (int j = 0; j < 3; j++) {
          //  modelo2.addElement(aux[j]);
        }
        jComboBox1.setModel(modelo2);
        a = 0;
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
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField15 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();

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
        jButton3.setText("ESCANEAR CODIGO");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton8.setText("AGREGAR");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel10.setText("COSTO");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel5.setText("TIPO");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel6.setText("MARCA");

        jTextField4.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel2.setText("NOMBRE NUEVO PRODUCTO");

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel23.setText("TALLA");

        jTextField20.setEditable(false);
        jTextField20.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        jTextField21.setEditable(false);
        jTextField21.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField21.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField21ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel18.setText("CANTIDAD");

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel11.setText("PRECIO VENTA");

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel12.setText("PROVEEDOR");

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 721, Short.MAX_VALUE)
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
                                            .addComponent(jTextField12)
                                            .addComponent(jTextField20)
                                            .addComponent(jTextField4)
                                            .addComponent(jTextField21)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField15)
                                    .addComponent(jTextField16)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jTextField17)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
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
        String arancel = "00-00-000";
        String nivel = "0";
        funciones f = new funciones();
        String estado = "RECHAZADA";
        for (int j = 0; j < modelo.getRowCount(); j++) {
            if (modelo.getValueAt(j, 6).toString().equals("ACEPTADO")) {
                estado = "PENDIENTE";
            }
        }
        String datos = null;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            if (!modelo.getValueAt(i, 3).toString().equals("-")) {
                arancel = modelo.getValueAt(i, 3).toString();
                nivel = modelo.getValueAt(i, 2).toString();
            } else {
                arancel = "00-00-000";
                nivel = "0";
            }
            if (datos == null) {
                datos = modelo.getValueAt(i, 1).toString() + ";" + arancel + ";" + nivel + ";" + modelo.getValueAt(i, 4).toString() + ";" + modelo.getValueAt(i, 5).toString() + ";" + modelo.getValueAt(i, 6).toString() + ";" + modelo.getValueAt(i, 7).toString() + ";" + modelo.getValueAt(i, 0).toString().split("-")[0] + "=";
            } else {
                datos = datos + modelo.getValueAt(i, 1).toString() + ";" + arancel + ";" + nivel + ";" + modelo.getValueAt(i, 4).toString() + ";" + modelo.getValueAt(i, 5).toString() + ";" + modelo.getValueAt(i, 6).toString() + ";" + modelo.getValueAt(i, 7).toString() + ";" + modelo.getValueAt(i, 0).toString().split("-")[0] + "=";
            }
        }
        String det = null;
      /*  if (jTextField17.getText().trim().isEmpty() | jTextField17.getText().trim() == null) {
            det = "-";
        } else {
            det = jTextField17.getText().trim().substring(0, 49).toUpperCase();
        }*/
        try {
       //     aux4 = this.guardarSol(datos, 0, f.getFechaTxt(jDateChooser1.getDate()), Integer.valueOf(jTextField13.getText()), Integer.valueOf(jTextField14.getText()), estado, Integer.valueOf(jTextField8.getText().replace(".", "")), id_liq, usuario, det);
            this.setCursor(Cursor.getDefaultCursor());
            if (aux4.equals("creado")) {
                mnsj = JOptionPane.showConfirmDialog(null, correcto, "INFORMACIÓN", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                JFrame frame = null;
             //   dialog_sol_seg a = new dialog_sol_seg(frame, usuario);
           //     a.setLocationRelativeTo(null);
                this.dispose();
           //     a.setVisible(true);

            } else {
                mnsj = JOptionPane.showConfirmDialog(null, error + aux4, "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception ex) {
            this.setCursor(Cursor.getDefaultCursor());
            mnsj = JOptionPane.showConfirmDialog(null, error + aux4, "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            return;
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed

        funciones f = new funciones();
        x = jTextField4.getText().trim();
        int cta = buscar_cta(x);
       // jTextField2.setText("");
      //  jTextField6.setText("");
      //  jTextField7.setText("");
      //  jTextField6.setEditable(false);
      //  jTextField2.setEditable(false);
      //  jTextField7.setEditable(false);
        if (cta == -1) {  // cuenta no valida
            mnsj = JOptionPane.showConfirmDialog(null, num_no_valido + "CUENTA", "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
            jTextField4.setText("");
            jTextField4.requestFocus();
            return;
        }
        if (!cuentas[5][cta].equals("0") && !cuentas[5][cta].equals("1")) {
          //  jTextField2.setEditable(true);
            jTextField21.setText(cuentas[1][cta]);
          //  jTextField2.requestFocus();
          //  jTextField2.setText("3");
            return;
        }
        if (cuentas[5][cta].equals("1")) {
          //  jTextField7.setEditable(true);
            jTextField21.setText(cuentas[1][cta]);
          //  jTextField7.requestFocus();
            String aux4 = null;
            if (x.equals("6033") || x.equals("6038")) {
                String aux2 ="hola";// this.validarCtaBenef(Integer.valueOf(x), Integer.valueOf(jTextField8.getText()));
                if (aux2.equals("vacio")) {
                    return;
                }
                aux3 = aux2.split("=");
                for (int j = 5; j < aux3.length; j++) {
                    if (x.equals("6038")) {
                        cuenta = 6038;
                        monto = Integer.valueOf(aux5[1]);
                    }
                    String[] aux5 = aux3[j].split(";");
                    if (aux4 == null) {
                        aux4 = "ID COMP: " + aux5[0] + "   MONTO: " + f.getNumcPto(Integer.valueOf(aux5[1])).replace(",", ".") + "   FECHA: " + aux5[2] + "   DETALLE: " + aux5[3] + "\n";
                    } else {
                        aux4 = aux4 + "ID COMP: " + aux5[0] + "   MONTO: " + f.getNumcPto(Integer.valueOf(aux5[1])).replace(",", ".") + "   FECHA: " + aux5[2] + "   DETALLE: " + aux5[3] + "\n";
                    }
                }
                mnsj = JOptionPane.showConfirmDialog(null, "EN LA CUENTA " + x + " SE HA BENEFICIADO LO SIGUIENTE AL SOCIO:\n\n" + aux4, "INFORMACIÓN", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
            }
            return;
        }
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        try {
            int aux = Integer.valueOf(jTextField4.getText().trim());
        } catch (Exception e) {
            jTextField4.setText("");
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField20ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField21ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        String aux3[];
        int linea = jTable1.getSelectedRow();
        if (evt.getClickCount() == 2 & linea != -1) {
            jTextField4.setText(modelo.getValueAt(linea, 1).toString());
            jTextField4.requestFocus();
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
            int porciento =0;// Integer.valueOf(jTextField16.getText().replace("%", "").trim());
            int monto = Integer.valueOf(modelo.getValueAt(linea, 4).toString());
          //  jTextField7.setText((monto * 100 / porciento) + "");
          //  jTextField22.setText(modelo.getValueAt(linea, 4).toString());
          //  jTextField9.setText(modelo.getValueAt(linea, 5).toString());
          //  jTextField10.setText(modelo.getValueAt(linea, 6).toString());
            jComboBox1.setSelectedItem(modelo.getValueAt(linea, 7).toString());
        }
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(dialog_ing_sol_seguro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dialog_ing_sol_seguro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dialog_ing_sol_seguro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dialog_ing_sol_seguro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                dialog_ing_sol_seguro dialog = new dialog_ing_sol_seguro(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField4;
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
