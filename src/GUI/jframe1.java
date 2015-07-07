package GUI;

//import Clases.funciones;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

public class jframe1 extends javax.swing.JFrame implements ActionListener {

    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    String sin_conexion = "NO SE HA PODIDO ESTABLECER LA CONEXIÓN PARA OBTENER LA INFORMACIÓN.";
    int mnsj;

    public jframe1() {
        initComponents();
        centrarVentana();
        jpanel1 panel = new jpanel1();
        this.add(panel, BorderLayout.CENTER);
        
        this.pack();
        jTextField1.addActionListener(this);
        jPasswordField1.addActionListener(this);
       // funciones f = new funciones();
        String dia=(Calendar.getInstance().getTime().getDate()<10)?"0"+Calendar.getInstance().getTime().getDate():Calendar.getInstance().getTime().getDate()+"";
       // String mes=f.get_mesMay((Calendar.getInstance().getTime().getMonth()+1));
        String anio=(Calendar.getInstance().getTime().getYear()+1900)+"";
      //  jLabel5.setText(dia+" DE "+mes+" DEL "+anio);
    }

    


    
    public void centrarVentana() {
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        // Se obtienen las dimensiones en pixels de la ventana.
        Dimension ventana = getSize();
        // Una cuenta para situar la ventana en el centro de la pantalla.
        setLocation((pantalla.width - ventana.width) / 2,
                (pantalla.height - ventana.height) / 2);
    }

    private void verificar_usuario() {
        String aux;
        boolean nu = jTextField1.getText().isEmpty();
        boolean np = jPasswordField1.getText().isEmpty();
        if (nu & np) {
            jLabel6.setText("DEBE COMPLETAR LOS CAMPOS");
            this.setCursor(Cursor.getDefaultCursor());
            return;
        }
        if (nu) {
            jLabel6.setText("DEBE INGRESAR UN USUARIO");
            this.setCursor(Cursor.getDefaultCursor());
            return;
        }
        if (np) {
            jLabel6.setText("DEBE INGRESAR UNA CONTRASEÑA");
            this.setCursor(Cursor.getDefaultCursor());
            return;
        }
        String plaintext = jPasswordField1.getText();
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(jframe1.class.getName()).log(Level.SEVERE, null, ex);
        }
        m.reset();
        m.update(plaintext.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1, digest);
        String hashtext = bigInt.toString(16);
        if (nu == false & np == false) {
            setCursor(Cursor.WAIT_CURSOR);
            aux = validarUsuario(jTextField1.getText(), hashtext);
            if (aux.equals("-1")) {
                this.setCursor(Cursor.getDefaultCursor());
                jLabel6.setText("CONTRASEÑA INCORRECTA");
            } else {
                if (aux.equals("-2")) {
                    this.setCursor(Cursor.getDefaultCursor());
                    jLabel6.setText("RUT INCORRECTO");
                } else {
                    try {
                        jframeUsuario u = new jframeUsuario(jTextField1.getText());
                        u.setLocationRelativeTo(null);
                        this.setCursor(Cursor.getDefaultCursor());
                        dispose();
                        u.setVisible(true);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public void siguiente() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        final SwingWorker worker = new SwingWorker() {

            @Override
            protected Void doInBackground() throws Exception {
                try {
                    String aux;
                    boolean nu = jTextField1.getText().isEmpty();
                    boolean np = jPasswordField1.getText().isEmpty();
                    if (nu & np) {
                        jLabel6.setText("DEBE COMPLETAR LOS CAMPOS");
                        setCursor(Cursor.getDefaultCursor());
                        return null;
                    }
                    if (nu) {
                        jLabel6.setText("DEBE INGRESAR UN USUARIO");
                        setCursor(Cursor.getDefaultCursor());
                        return null;
                    }
                    if (np) {
                        jLabel6.setText("DEBE INGRESAR UNA CONTRASEÑA");
                        setCursor(Cursor.getDefaultCursor());
                        return null;
                    }
                    String plaintext = jPasswordField1.getText();
                    MessageDigest m = null;
                    try {
                        m = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(jframe1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    m.reset();
                    m.update(plaintext.getBytes());
                    byte[] digest = m.digest();
                    BigInteger bigInt = new BigInteger(1, digest);
                    String hashtext = bigInt.toString(16);
                    if (nu == false & np == false) {
                        setCursor(Cursor.WAIT_CURSOR);
                        aux = "1";//validarUsuario(jTextField1.getText(), hashtext);
                        if (aux.equals("-1")) {
                            setCursor(Cursor.getDefaultCursor());
                            jLabel6.setText("CONTRASEÑA INCORRECTA");
                        } else {
                            if (aux.equals("-2")) {
                                setCursor(Cursor.getDefaultCursor());
                                jLabel6.setText("RUT INCORRECTO");
                            } else {
                                try {
                                    jframeUsuario u = new jframeUsuario("168141211");
                                    u.setLocationRelativeTo(null);
                                    setCursor(Cursor.getDefaultCursor());
                                    dispose();
                                    u.setVisible(true);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                    }

                } catch (Exception ex) {
                    setCursor(Cursor.getDefaultCursor());
                    mnsj = JOptionPane.showConfirmDialog(null, error + ex.getMessage(), "ERROR", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        };
        worker.execute();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ULTIMO ROUND 2015");
        setPreferredSize(new java.awt.Dimension(550, 470));
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Usuario");
        jLabel2.setAlignmentX(0.5F);

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Contraseña");
        jLabel3.setAlignmentX(0.5F);

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jPasswordField1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        jPasswordField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordField1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/iniciar-sesion 32x32.png"))); // NOI18N
        jButton1.setText("ENTRAR");
        jButton1.setAlignmentX(0.5F);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir 32x32.png"))); // NOI18N
        jButton2.setText("SALIR");
        jButton2.setAlignmentX(0.5F);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 10)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("IDP SOFTWARE 2015");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("jLabel5");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(204, 0, 51));
        jLabel6.setAlignmentX(0.5F);

        jLabel1.setBackground(new java.awt.Color(102, 102, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logo.png"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setAlignmentX(0.5F);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                    .addComponent(jPasswordField1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel1)))))
                        .addGap(104, 104, 104))))
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        this.siguiente();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        System.exit(0);

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        jPasswordField1.requestFocus();

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordField1ActionPerformed

        this.verificar_usuario();

    }//GEN-LAST:event_jPasswordField1ActionPerformed

    public static void main(String args[]) {



        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            // for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
            //   if ("Nimbus".equals(info.getName())) {
            //     javax.swing.UIManager.setLookAndFeel(info.getClassName());
            //   break;
            //}
            //}
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jframe1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new jframe1().setVisible(true);

            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    private String validarUsuario(java.lang.String rut, java.lang.String pass) {
       // metodos.Servicio_Service service = new metodos.Servicio_Service();
      //  metodos.Servicio port = service.getServicioPort();
     //   return port.validarUsuario(rut, pass);
        return null;
    }
}
