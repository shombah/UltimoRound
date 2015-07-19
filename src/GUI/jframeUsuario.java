/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;

public class jframeUsuario extends javax.swing.JFrame implements KeyListener {

    int usuario = 0, mnsj;
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    String sin_conexion = "NO SE HA PODIDO ESTABLECER LA CONEXIÓN PARA OBTENER LA INFORMACIÓN.";
    String fecha_no_valida = "DEBE INGRESAR EN EL CAMPO FECHA UN DATO VALIDO";

    public jframeUsuario() {
        initComponents();
        iniciar();

    }

    private void modulosDefault() {
//        modulo1.setEnabled(false);
//        modulo2.setEnabled(false);
//        modulo3.setEnabled(false);
//        modulo4.setEnabled(false);
//        modulo5.setEnabled(false);
//        modulo6.setEnabled(false);
//        modulo7.setEnabled(false);
//        modulo8.setEnabled(false);
//        modulo9.setEnabled(false);
//        modulo10.setEnabled(false);
//        modulo12.setEnabled(false);
//        modulo13.setEnabled(false);
//        modulo14.setEnabled(false);
//        modulo15.setEnabled(false);
//        modulo16.setEnabled(false);
//        modulo17.setEnabled(false);
//        modulo18.setEnabled(false);
//        modulo19.setEnabled(false);
//        modulo20.setEnabled(false);
//        modulo21.setEnabled(false);
    }

    private void modulosPrivilegios(String m) {
        String[] p = m.split("-");
        int i = 0, aux;
        /*while (i < p.length) {*/
            aux = Integer.valueOf(p[i]);
            switch (aux) {
                case 1:
                    modulo1.setEnabled(true);
                    break;
                case 2:
                    modulo2.setEnabled(true);
                    break;
                case 3:
                    modulo3.setEnabled(true);
                    break;
                case 4:
                    modulo4.setEnabled(true);
                    break;
                case 5:
                    modulo5.setEnabled(true);
                    break;
                case 6:
                    modulo6.setEnabled(true);
                    break;
                case 7:
                    modulo7.setEnabled(true);
                    break;
                case 8:
                    modulo8.setEnabled(true);
                    break;
                case 14:
                    modulo14.setEnabled(true);
                    break;
                case 15:
                    modulo15.setEnabled(true);
                    break;
                case 16:
                    modulo16.setEnabled(true);
                    break;
                case 17:
                    modulo17.setEnabled(true);
                    break;
                case 18:
                    modulo18.setEnabled(true);
                    break;
                case 19:
                    modulo19.setEnabled(true);
                    break;
                
            }
            i++;
       // }
    }

    public jframeUsuario(String rut_usuario) {
        initComponents();
        usuario = Integer.valueOf(rut_usuario);
        modulosDefault();
        String a = getModulos(rut_usuario);
      //  modulosPrivilegios(a);
        iniciar();

    }

    public void iniciar() {
        jpanel2 pane = new jpanel2();
        this.add(pane, BorderLayout.CENTER);
        this.pack();
        this.addKeyListener(this);
        jMenuBar1.addKeyListener(this);


    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        solicitud = new javax.swing.JMenu();
        modulo1 = new javax.swing.JMenuItem();
        modulo2 = new javax.swing.JMenuItem();
        modulo3 = new javax.swing.JMenuItem();
        empleado = new javax.swing.JMenu();
        modulo4 = new javax.swing.JMenuItem();
        modulo5 = new javax.swing.JMenuItem();
        modulo11 = new javax.swing.JMenuItem();
        comprobante = new javax.swing.JMenu();
        modulo6 = new javax.swing.JMenuItem();
        modulo7 = new javax.swing.JMenuItem();
        modulo8 = new javax.swing.JMenuItem();
        contabilidad = new javax.swing.JMenu();
        modulo15 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        modulo16 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        gestion = new javax.swing.JMenu();
        modulo14 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        modulo17 = new javax.swing.JMenuItem();
        administrador = new javax.swing.JMenu();
        modulo18 = new javax.swing.JMenuItem();
        modulo19 = new javax.swing.JMenuItem();
        opciones = new javax.swing.JMenu();
        cambiarPass = new javax.swing.JMenuItem();
        CerrarSesion = new javax.swing.JMenuItem();
        salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bienvenido");
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);

        jButton1.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 102));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/gnome-logout.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.setSelected(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 102));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/stock_lock.png"))); // NOI18N
        jButton2.setText("Cerrar Sesión");
        jButton2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jButton2.setSelected(true);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        solicitud.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/emblem-sales.png"))); // NOI18N
        solicitud.setText("Productos");
        solicitud.setIconTextGap(1);
        solicitud.setMaximumSize(new java.awt.Dimension(100, 32767));
        solicitud.setPreferredSize(new java.awt.Dimension(100, 29));

        modulo1.setText("Crear Producto");
        modulo1.setToolTipText("");
        modulo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo1ActionPerformed(evt);
            }
        });
        solicitud.add(modulo1);

        modulo2.setText("Modificar Producto");
        modulo2.setToolTipText("");
        modulo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo2ActionPerformed(evt);
            }
        });
        solicitud.add(modulo2);

        modulo3.setText("Eliminar Producto");
        modulo3.setToolTipText("");
        modulo3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo3ActionPerformed(evt);
            }
        });
        solicitud.add(modulo3);

        jMenuBar1.add(solicitud);

        empleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cart.png"))); // NOI18N
        empleado.setText("Kit Productos");
        empleado.setMaximumSize(new java.awt.Dimension(110, 32767));
        empleado.setPreferredSize(new java.awt.Dimension(130, 21));

        modulo4.setText("Crear Kit de Productos");
        modulo4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo4ActionPerformed(evt);
            }
        });
        empleado.add(modulo4);

        modulo5.setText("Modificar Kit De Productos");
        modulo5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo5ActionPerformed(evt);
            }
        });
        empleado.add(modulo5);

        modulo11.setText("Eliminar Kit De Productos");
        modulo11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo11ActionPerformed(evt);
            }
        });
        empleado.add(modulo11);

        jMenuBar1.add(empleado);

        comprobante.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/chart_curve.png"))); // NOI18N
        comprobante.setText("Inventario");
        comprobante.setMaximumSize(new java.awt.Dimension(110, 32767));
        comprobante.setPreferredSize(new java.awt.Dimension(100, 21));

        modulo6.setText("Inventario por Producto");
        modulo6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo6ActionPerformed(evt);
            }
        });
        comprobante.add(modulo6);

        modulo7.setText("Inventario General");
        modulo7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modulo7MouseClicked(evt);
            }
        });
        modulo7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo7ActionPerformed(evt);
            }
        });
        comprobante.add(modulo7);

        modulo8.setText("Inventario Critico");
        modulo8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo8ActionPerformed(evt);
            }
        });
        comprobante.add(modulo8);

        jMenuBar1.add(comprobante);

        contabilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/coins.png"))); // NOI18N
        contabilidad.setText("Ventas");
        contabilidad.setMaximumSize(new java.awt.Dimension(90, 32767));

        modulo15.setText("Vender Producto");
        modulo15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo15ActionPerformed(evt);
            }
        });
        contabilidad.add(modulo15);

        jMenuBar1.add(contabilidad);

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/chart.png"))); // NOI18N
        jMenu1.setText("Estadisticas");
        jMenu1.setMaximumSize(new java.awt.Dimension(105, 32767));

        jMenuItem3.setText("Productos Mas Vendidos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        modulo16.setText("Productos Menos Vendidos");
        modulo16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo16ActionPerformed(evt);
            }
        });
        jMenu1.add(modulo16);

        jMenuItem5.setText("Libro de Ventas");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuBar1.add(jMenu1);

        gestion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/basket.png"))); // NOI18N
        gestion.setText("Promociones");
        gestion.setMaximumSize(new java.awt.Dimension(125, 32767));

        modulo14.setText("Nueva Promocion");
        modulo14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo14ActionPerformed(evt);
            }
        });
        gestion.add(modulo14);

        jMenuItem7.setText("Modificiar Promocion");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        gestion.add(jMenuItem7);

        modulo17.setText("Eliminar Promocion");
        modulo17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo17ActionPerformed(evt);
            }
        });
        gestion.add(modulo17);

        jMenuBar1.add(gestion);

        administrador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/user_gray.png"))); // NOI18N
        administrador.setText("Administrador");
        administrador.setActionCommand("Administrador     ");

        modulo18.setText("Adm. Usuarios");
        modulo18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo18ActionPerformed(evt);
            }
        });
        administrador.add(modulo18);

        modulo19.setText("Adm. Perfiles");
        modulo19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modulo19ActionPerformed(evt);
            }
        });
        administrador.add(modulo19);

        jMenuBar1.add(administrador);

        opciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/computer_key.png"))); // NOI18N
        opciones.setText("Opciones");
        opciones.setIconTextGap(1);
        opciones.setMaximumSize(new java.awt.Dimension(80, 32767));

        cambiarPass.setText("Cambiar Contraseña");
        cambiarPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cambiarPassActionPerformed(evt);
            }
        });
        opciones.add(cambiarPass);

        CerrarSesion.setText("Cerrar Sesión");
        CerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarSesionActionPerformed(evt);
            }
        });
        opciones.add(CerrarSesion);

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        opciones.add(salir);

        jMenuBar1.add(opciones);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 654, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(323, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modulo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       buscar_producto p = new buscar_producto(this, true, usuario);
       p.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
      p.setVisible(true);

    }//GEN-LAST:event_modulo2ActionPerformed

    private void modulo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo1ActionPerformed
     this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Newproduct ingresar = new Newproduct();
       
        this.setCursor(Cursor.getDefaultCursor());
        ingresar.setVisible(true);

    }//GEN-LAST:event_modulo1ActionPerformed

    private void modulo6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo6ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     //   dialog_ing_comp a = new dialog_ing_comp(this, true, usuario);
     //   a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
      //  a.setVisible(true);
    }//GEN-LAST:event_modulo6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        jframe1 login = new jframe1();
        dispose();
        this.setCursor(Cursor.getDefaultCursor());
        login.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed

        System.exit(0);
    }//GEN-LAST:event_salirActionPerformed

    private void CerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarSesionActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        jframe1 login = new jframe1();
        dispose();
        this.setCursor(Cursor.getDefaultCursor());
        login.setVisible(true);

    }//GEN-LAST:event_CerrarSesionActionPerformed

    private void cambiarPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cambiarPassActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    //    dialog_cambiar_pass p = new dialog_cambiar_pass(this, true, usuario);
      //  p.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
      //  p.setVisible(true);

    }//GEN-LAST:event_cambiarPassActionPerformed

    private void modulo7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo7ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            frameInventarioActual frameInventario = new frameInventarioActual();
      
            frameInventario.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_modulo7ActionPerformed

    private void modulo4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo4ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        try {
            nuevo_kit p = new nuevo_kit();
            p.setVisible(true);
            this.setCursor(Cursor.getDefaultCursor());
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      //  p.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
      //  p.setVisible(true);
    }//GEN-LAST:event_modulo4ActionPerformed

    private void modulo5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo5ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     //   dialog_buscar_carga p = new dialog_buscar_carga(usuario);
     //   p.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     //   p.setVisible(true);
    }//GEN-LAST:event_modulo5ActionPerformed

    private void modulo18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo18ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      //  dialog_mostrar_usuarios a = new dialog_mostrar_usuarios(this, usuario);
     //   a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     //   a.setVisible(true);

    }//GEN-LAST:event_modulo18ActionPerformed

    private void modulo8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo8ActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     //   dialog_generar_comp a = new dialog_generar_comp(this, usuario);
     //   a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     //   a.setVisible(true);

    }//GEN-LAST:event_modulo8ActionPerformed

    private void modulo19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo19ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    //    dialog_control_perfil a = new dialog_control_perfil(this, usuario);
    //    a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     //   a.setVisible(true);

    }//GEN-LAST:event_modulo19ActionPerformed

    private void modulo16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo16ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      //  dialog_buscar_seguro a = new dialog_buscar_seguro(usuario);
      //  a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
      //  a.setVisible(true);
    }//GEN-LAST:event_modulo16ActionPerformed

    private void modulo3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo3ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     buscar_eliminar_producto a = new buscar_eliminar_producto(usuario);
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);
    }//GEN-LAST:event_modulo3ActionPerformed

    private void modulo15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo15ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     //   dialog_buscar_arancel a = new dialog_buscar_arancel(this, usuario);
     //   a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     //   a.setVisible(true);

    }//GEN-LAST:event_modulo15ActionPerformed

    private void modulo17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo17ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    //    dialog_prestamo_sim a = new dialog_prestamo_sim(this, usuario);
     //   a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     //   a.setVisible(true);

    }//GEN-LAST:event_modulo17ActionPerformed

    private void modulo14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo14ActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       // dialog_reportes2 a = new dialog_reportes2(usuario);
       // a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
      //  a.setVisible(true);

    }//GEN-LAST:event_modulo14ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
      //  dialog_carga_seguro a = new dialog_carga_seguro(usuario);
      //  a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
      //  a.setVisible(true);

    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed

        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     //   dialog_buscar_atencion_social a = new dialog_buscar_atencion_social(usuario);
     //   a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     //   a.setVisible(true);

    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     //   dialog_reporte_sostenibilidad a = new dialog_reporte_sostenibilidad(usuario);
      //  a.setLocationRelativeTo(null);
        setCursor(Cursor.getDefaultCursor());
       // a.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void modulo11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modulo11ActionPerformed
             this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
     buscar_eliminar_kit a = null;
        try {
            a = new buscar_eliminar_kit(this.usuario);
        } catch (SQLException ex) {
            Logger.getLogger(jframeUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
      a.setLocationRelativeTo(null);
        this.setCursor(Cursor.getDefaultCursor());
     a.setVisible(true);
    }//GEN-LAST:event_modulo11ActionPerformed
    private void modulo7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modulo7MouseClicked
      
    }//GEN-LAST:event_modulo7MouseClicked

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
            java.util.logging.Logger.getLogger(jframeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jframeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jframeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jframeUsuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new jframeUsuario().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CerrarSesion;
    private javax.swing.JMenu administrador;
    private javax.swing.JMenuItem cambiarPass;
    private javax.swing.JMenu comprobante;
    private javax.swing.JMenu contabilidad;
    private javax.swing.JMenu empleado;
    private javax.swing.JMenu gestion;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem modulo1;
    private javax.swing.JMenuItem modulo11;
    private javax.swing.JMenuItem modulo14;
    private javax.swing.JMenuItem modulo15;
    private javax.swing.JMenuItem modulo16;
    private javax.swing.JMenuItem modulo17;
    private javax.swing.JMenuItem modulo18;
    private javax.swing.JMenuItem modulo19;
    private javax.swing.JMenuItem modulo2;
    private javax.swing.JMenuItem modulo3;
    private javax.swing.JMenuItem modulo4;
    private javax.swing.JMenuItem modulo5;
    private javax.swing.JMenuItem modulo6;
    private javax.swing.JMenuItem modulo7;
    private javax.swing.JMenuItem modulo8;
    private javax.swing.JMenu opciones;
    private javax.swing.JMenuItem salir;
    private javax.swing.JMenu solicitud;
    // End of variables declaration//GEN-END:variables

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 18) {
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private static String getModulos(java.lang.String rut) {
     //   metodos.Servicio_Service service = new metodos.Servicio_Service();
       // metodos.Servicio port = service.getServicioPort();
     //   return port.getModulos(rut);
        return null;
    }
}
