/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.Metodos_objetos;
import Clases.funciones;
import static GUI.buscaProductoInventario.createImageIcon2;
import ObjetosDB.Productos;
import ObjetosDB.metodosDB;
import com.mxrck.autocompleter.TextAutoCompleter;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import jbarcodebean.JBarcodeBean;
import net.sourceforge.jbarcodebean.model.Interleaved25;
import javax.swing.JFileChooser;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;

public class Newproduct extends javax.swing.JDialog implements KeyListener {

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
            String nombreArchivo = "";

    DefaultTableModel modelo = new DefaultTableModel() {
private JComboBox combo1;
        @Override
        public boolean isCellEditable(int row, int column) { // sobreescribe el metodo para convertir la celda ingresada en NO editable
            return false;
        }
    };

    public Newproduct(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        iniciar();

    }

    Newproduct() {
        initComponents();
        iniciar();
    }

    Newproduct(int u, int idliq, int rut_emp, int p, String nom_emp, String nom_benef, int rut_benef) { // constructor que viene de el listado de solicitudes de seguro
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

    public void iniciar() {

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
        String t[] = {"NOMBRE PRODUCTO","TALLA","MARCA","COLOR", "TIPO", "PRECIO VENTA","CANTIDAD","PROVEEDOR","COD.","FOTO"};
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
 
		
		// Accion a realizar cuando el JComboBox cambia de item seleccionado.
         //Iniciamos la wea sin nada
        
         String path = "/fotosProductos/default.png";
         ImageIcon iconLogo = createImageIcon2(path);
         jLabel19.setIcon(iconLogo);
         
         
         
         
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
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("INGRESAR NUEVO PRODUCTO");
        setModal(true);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(41, 91, 134));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/disk.png"))); // NOI18N
        jButton1.setText("GUARDAR PRODUCTO(S)");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(206, 12, 12));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/delete.png"))); // NOI18N
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

        jButton4.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow_rotate_clockwise.png"))); // NOI18N
        jButton4.setText("ATRAS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/stock_id.png"))); // NOI18N
        jButton3.setText("Escanear Código de Barra");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton8.setForeground(new java.awt.Color(206, 12, 12));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Remove.png"))); // NOI18N
        jButton8.setText("Eliminar Producto");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel5.setText("TIPO");

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel6.setText("MARCA");

        jLabel2.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel2.setText("NOMBRE ");

        jLabel23.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel23.setText("TALLA");

        jTextField20.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField20.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField20ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel18.setText("CANTIDAD");

        jLabel11.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel11.setText("PRECIO VENTA");

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
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

        jButton9.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton9.setForeground(new java.awt.Color(61, 182, 70));
        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Add.png"))); // NOI18N
        jButton9.setText("Agregar Producto");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "SELECCIONAR ITEM", "POLERA", "SHORT", "POLERON ", "JOCKEY ", "ESCUDO ", "PADS", "FOCOS ", "GUANTILLAS ", "GUANTES DE BOXEO", "CANILLERAS ENTRENAMIENTO", "GI JIUJITSU", "CINTURONES JIUJITSU", "TIMER ", "ZAPATILLAS DE BOXEO", "BUCALES ", "VENDAS ", "ELEVATION TRAINING MASK ", "ACCESORIOS", "SPATS " }));
        jComboBox1.setSelectedItem(jComboBox1);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.setToolTipText("CODIGO");
        jTextField1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jTextField1.setCaretColor(new java.awt.Color(204, 0, 0));

        jLabel7.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jLabel7.setText("COLOR");

        jTextField28.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField28.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTextField28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField28ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("DejaVu Sans", 1, 16)); // NOI18N
        jButton10.setForeground(new java.awt.Color(61, 182, 70));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/applets-screenshooter.png"))); // NOI18N
        jButton10.setText("Seleccionar Foto");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel19.setMaximumSize(new java.awt.Dimension(250, 250));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel18)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7))
                                    .addGap(47, 47, 47)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField23, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addComponent(jTextField20, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addComponent(jTextField28, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addComponent(jTextField24, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                        .addComponent(jComboBox1, 0, 195, Short.MAX_VALUE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            boolean correcto=false;
            boolean correcto2=false;
        System.out.println("q wea pasa"+modelo.getRowCount());
        for (int j = 0; j < modelo.getRowCount(); j++) {
           
            if(modelo.getRowCount()!=0){
            ArrayList<Productos> productos = new ArrayList();
 //Productos(String nombre, String marca, String talla, String color, int precioCompra, int precioVenta, String proveedor, int cantidadActual) { //Constructor
            String color = ""; //deberia ser modelo.getColor
            String tipo = "";
            String codigo_barra = "";
   Object[] object = new Object[10];
            Productos producto = new Productos(0,modelo.getValueAt(j, 0).toString(),modelo.getValueAt(j, 2).toString(),modelo.getValueAt(j, 1).toString()
            ,modelo.getValueAt(j, 3).toString(),0,(Integer) modelo.getValueAt(j, 5),modelo.getValueAt(j, 7).toString(),(Integer) modelo.getValueAt(j, 6)
 ,modelo.getValueAt(j, 4).toString(),modelo.getValueAt(j, 8).toString());
           //falta agregar en tabla color
            
               producto.setImagen(modelo.getValueAt(j, 9).toString());
            //falta para TIPO y para CODIGO
            
            
            metodosDB metodos= new metodosDB();
            try {
                
                correcto=metodos.addProductos(producto);
            correcto2=metodos.addProductos(producto);
          
            this.dispose();
            } catch (SQLException ex) {
                Logger.getLogger(Newproduct.class.getName()).log(Level.SEVERE, null, ex);
            }
           
         
                      
                      //validar campos
                      //guardar en la base de datos si es consistente
                         
                      this.dispose(); //dispose para salir de la ventana
        }else{
               
              JOptionPane.showMessageDialog(rootPane,  "NO HA AGREGADO NINGUN PRODUCTO".toUpperCase());
                
                }}     if(correcto2==false)
    if(correcto==false){
          jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "ERROR: PRODUCTO CON MISMO CODIGO DE BARRA ALMACENADO");
            this.dispose();
       
        } else{
        jframe1 a = new jframe1();
            JOptionPane.showMessageDialog(a, "PRODUCTO(S) AGREGADOS CON EXITO");
    }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new Metodos_objetos().testProductos(new metodosDB().getProductos());
        } catch (SQLException ex) {
            Logger.getLogger(Newproduct.class.getName()).log(Level.SEVERE, null, ex);
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
            
           jTextField1.requestFocus();
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

    private void jTextField26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField26ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField26ActionPerformed

    private void jTextField27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField27ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField27ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

    if(validaCampos())  
    {
        //DATOS DE LA TABLA
        Object[] object = new Object[10];
        object[0] = jTextField22.getText();
        object[1] = jTextField20.getText();
        object[2] = jTextField23.getText();
        object[5] = Integer.parseInt(jTextField26.getText());
        object[4] = jComboBox1.getSelectedItem().toString();
        object[7] = jTextField27.getText();
     
        object[6] = Integer.parseInt(jTextField24.getText());
        object[8] = jTextField1.getText();
        object[3] = jTextField28.getText();
        object[9] = nombreArchivo+".png";
        
        modelo.addRow(object);
            //TERMINO DATOS TABLA
        limpiar();
    }
  

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField28ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        boolean error = false;
        nombreArchivo = jTextField1.getText().trim();
        if(nombreArchivo.isEmpty())
        {
            JOptionPane.showMessageDialog(rootPane,"DEBE INGRESAR CODIGO DE BARRA PRIMERO");
            error = true;
        }
        if(error == false)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) 
            {
                funciones f= new funciones();
            File selectedFile = fileChooser.getSelectedFile();
            String pathInicio = selectedFile.getAbsolutePath();
            String pathDestino = f.getRutaFotoProducto()+nombreArchivo+".png"; //cambiar por new f.getRutaFotosProducto()
            long start = System.nanoTime();
            File source = new File(pathInicio);
            File dest = new File(pathDestino);
                try {
                    copyFile(source,dest);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(rootPane,"ERROR ALMACENANDO FOTO");
                }
            }
            recargaImagen(nombreArchivo);
recargaImagen(nombreArchivo);
        }
        
    }//GEN-LAST:event_jButton10ActionPerformed
    private void recargaImagen(String nombreArchivo)
    {   funciones f= new funciones();
        String path = f.getRutaFotoProducto()+nombreArchivo.trim()+".png";
        ImageIcon iconLogo = createImageIcon2(path);
        jLabel19.setIcon(iconLogo);
    }
    private void limpiar(){
        jTextField22.setText("");
        jTextField23.setText(""); 
        jTextField24.setText(""); 
        jTextField26.setText(""); 
        jComboBox1.setSelectedIndex(0); 
        jTextField27.setText(""); 
         jTextField28.setText(""); 
        jTextField20.setText(""); 
        jTextField1.setText("");
        recargaImagen("default");
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
            java.util.logging.Logger.getLogger(Newproduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Newproduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Newproduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Newproduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                Newproduct dialog = new Newproduct(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
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
    public boolean validaCampos()
    {   
        
        //jtextfield22 == nombre
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField22.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "nombre no puede estar vacío".toUpperCase());
                return false;
                }
        //jtexfield 20 = talla
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField20.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "talla no puede estar vacío".toUpperCase());
                return false;
                }
        //jtexfield 23 = marca
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField23.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "marca no puede estar vacío".toUpperCase());
                return false;
                }
        
        //jtexfield 28 = color
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField28.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "color no puede estar vacío".toUpperCase());
                return false;
                }
    
         //jtexfield 24 = cantidad
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField24.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "cantidad no puede estar vacío".toUpperCase());
                return false;
                }
        
         try {
            // Check whether priceField.getText()'s length equals 0
            Integer.parseInt(jTextField24.getText().trim());
               
            } catch(NumberFormatException e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "cantidad debe ser un número".toUpperCase());
                return false;
                }
         
         if (jComboBox1.getSelectedIndex()==0)
         {
                JOptionPane.showMessageDialog(rootPane,  "Debe seleccionar un tipo".toUpperCase());
                return false;
         }
          //jtexfield 25 = costo
        //Check whether priceField.getText()'s length equals 0
           
         
          //jtexfield 26 precio venta
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField26.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "precio venta no puede estar vacío".toUpperCase());
                return false;
                }
        
         try {
            // Check whether priceField.getText()'s length equals 0
            Integer.parseInt(jTextField26.getText().trim());
               
            } catch(NumberFormatException e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "precio venta debe ser un número".toUpperCase());
                return false;
                }
         
           //jtexfield 27 = proveedor
        try {
            // Check whether priceField.getText()'s length equals 0
            if(jTextField27.getText().trim().length() == 0) 
                {
                throw new Exception();
                }
            } catch(Exception e) 
                {
                JOptionPane.showMessageDialog(rootPane,  "proveedor no puede estar vacío".toUpperCase());
                return false;
                }
        
        return true;
    }
     protected static ImageIcon createImageIcon2(String path) {
         System.out.println(path);
     

    if (path != null) {
        ImageIcon imageIcon =new ImageIcon(path);
        Image image = imageIcon.getImage();
        Image nueva = image.getScaledInstance(250,200,java.awt.Image.SCALE_FAST);
        imageIcon = new ImageIcon(nueva);
        
      return imageIcon;
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
    
  }
   public static void copyFile(File sourceFile, File destFile) throws IOException {
    if(!destFile.exists()) {
        destFile.createNewFile();
    }

    FileChannel source = null;
    FileChannel destination = null;

    try {
        source = new FileInputStream(sourceFile).getChannel();
        destination = new FileOutputStream(destFile).getChannel();
        destination.transferFrom(source, 0, source.size());
    }
    finally {
        if(source != null) {
            source.close();
        }
        if(destination != null) {
            destination.close();
        }

    }
    jframe1 a = new jframe1();
    JOptionPane.showMessageDialog(a, "IMAGEN ALMACENADA CORRECTAMENTE");
    

}
      
}
