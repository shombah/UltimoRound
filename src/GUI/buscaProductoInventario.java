/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static GUI.frameInventarioActual.createImageIcon;
import ObjetosDB.Kitproductos;
import ObjetosDB.Productos;
import ObjetosDB.metodosDB;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

/**
 *
 * @author tars
 */
public class buscaProductoInventario extends javax.swing.JFrame {
    private final ArrayList<ObjetosDB.Productos> carroProductosLocal = new ArrayList<Productos>();
    private ArrayList<Productos> carroProductos;
    
    DefaultTableModel modelo0 = new DefaultTableModel();
    DefaultTableModel modelo1 = new DefaultTableModel(); // Tabla Productos
    private javax.swing.JTable jTable0;
    /**
     * Creates new form buscaProductoInventario
     */
    public buscaProductoInventario(ArrayList<Productos> carroProductos, DefaultTableModel modelo0,javax.swing.JTable jTable1) {
        initComponents();
        this.carroProductos = carroProductos;
        this.modelo0 = modelo0;
        this.jTable0 =jTable1;
        
        try {
            iniciar();
        } catch (SQLException ex) {
            Logger.getLogger(buscaProductoInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private buscaProductoInventario() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Productos al Carro");
        setPreferredSize(new java.awt.Dimension(1000, 618));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos Disponibles"));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setAutoscrolls(true);

        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripción Producto"));

        jLabel1.setText("Nombre: ");

        jLabel2.setText("Tipo :");

        jLabel3.setText("Marca :");

        jLabel4.setText("Precio :");

        jLabel5.setText("Proveedor :");

        jLabel6.setText("Cantidad Actual: ");

        jLabel7.setText("Imagen de Referencia");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Add.png"))); // NOI18N
        jButton2.setText("Agregar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("-");

        jLabel9.setText("-");

        jLabel10.setText("-");

        jLabel11.setText("-");

        jLabel12.setText("-");

        jLabel13.setText("-");

        jLabel14.setText("-");

        jLabel15.setText("-");

        jLabel16.setText("Talla:");

        jLabel17.setText("Color:");

        jLabel18.setMaximumSize(new java.awt.Dimension(100, 100));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel17)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel16)
                            .addComponent(jLabel1))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12)
                            .addComponent(jLabel11)
                            .addComponent(jLabel9)
                            .addComponent(jLabel8)
                            .addComponent(jLabel15)
                            .addComponent(jLabel10))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jLabel7))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(97, 97, 97))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13))
                        .addGap(22, 22, 22))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Productos a Agregar"));

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
        jScrollPane1.setViewportView(jTable1);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Remove.png"))); // NOI18N
        jButton5.setText("Quitar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTextField1.setText("Buscar...");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/zoom.png"))); // NOI18N
        jButton1.setText("BUSCAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow_undo.png"))); // NOI18N
        jButton3.setText("Volver");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Clear Green Button.png"))); // NOI18N
        jButton4.setText("Agregar todo y Finalizar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/arrow_refresh.png"))); // NOI18N
        jButton6.setText("MOSTRAR TODOS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(3, 3, 3)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        final int en =1;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           jTree1.getLastSelectedPathComponent();
        /* if nothing is selected */ 
        if (node == null) return;
        Object nodeInfo = node.getUserObject();
        Object[] object = new Object[10];
        
        try {
            Productos producto = new metodosDB().getProductoByNombre((String) nodeInfo);
            object[0]  = producto.getId_producto();
            object[1] = producto.getNombre();
            object[2] = producto.getTalla();
            object[3] = producto.getMarca();
            object[4] = producto.getTipo();
            object[5] = producto.getProveedor();
            object[6] = producto.getPrecioCompra();
            object[7] = producto.getPrecioVenta();
            object[8] = producto.getCodigo_barra();
            modelo1.addRow(object);
            carroProductosLocal.add(producto);

        } catch (SQLException ex) {System.out.println("Error agregando a la tabla"+ex);}



    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked
        final int en =1;
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           jTree1.getLastSelectedPathComponent();
        /* if nothing is selected */ 
        if (node == null) return;

        Productos producto = new Productos();
        Object nodeInfo = node.getUserObject();
        System.out.println("seleccionado : "+(String) nodeInfo);
        try {
            producto = new metodosDB().getProductoByNombre((String) nodeInfo);
            jLabel8.setText(producto.getNombre().trim());
            jLabel9.setText(producto.getTipo().trim());
            jLabel10.setText(producto.getMarca().trim());
            jLabel11.setText((producto.getPrecioVenta()).toString().trim());
            jLabel12.setText(producto.getProveedor().trim());
            jLabel13.setText((producto.getCantidadActual()).toString().trim());
            jLabel14.setText(producto.getTalla().trim());
            jLabel15.setText(producto.getColor().trim());
            String path = "/Imagenes/"+producto.getImagen();
            ImageIcon iconLogo = createImageIcon2(path);
            jLabel18.setIcon(iconLogo);
        } catch (SQLException ex) { System.out.println("Error de producto desde arbol a tabla"+ex); }
        




    }//GEN-LAST:event_jTree1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        int borrar = jTable1.getSelectedRow();
        try{
            carroProductosLocal.remove(borrar);
        }catch(ArrayIndexOutOfBoundsException a){System.out.println("No hay nada seleccionado");}
        modelo1.removeRow(borrar);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
            Object[] object = new Object[10];

            for(int i=0;i<carroProductosLocal.size();i++)
            {
                System.out.println("Producto a Agregar : "+ carroProductosLocal.get(i).getNombre()+"\n");
                Productos producto = carroProductosLocal.get(i);
                object[0]  = producto.getId_producto();
                object[1] = producto.getNombre();
                object[2] = producto.getTalla();
                object[3] = producto.getMarca();
                object[4] = producto.getTipo();
                object[5] = producto.getProveedor();
                object[6] = producto.getPrecioCompra();
                object[7] = producto.getPrecioVenta();
                object[8] = producto.getCodigo_barra();
                modelo0.addRow(object);
                jTable0.setModel(modelo0);
            }
            carroProductos.addAll(carroProductosLocal);
            this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
ArrayList<Productos> aux2a = null;
String nombre = jTextField1.getText();

        try {
            aux2a = new metodosDB().getProductos();
        } catch (SQLException ex) {
            Logger.getLogger(buscaProductoInventario.class.getName()).log(Level.SEVERE, null, ex);
        }
if(nombre.equals("")||nombre.equals("Buscar...")){
    
        int i = 0;
        Object[] object = new Object[5];
        System.out.println("aux2a = "+aux2a.size()+"asd i"+i);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode( "Productos Disponibles" );

        while(aux2a.size()>i)
        {
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(aux2a.get(i).getNombre());
            root.add(nodo);
            i++;
        }
        
       
        DefaultTreeModel modeloarbol = new DefaultTreeModel(root);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jTree1.getCellRenderer();
        ImageIcon leafIcon = createImageIcon("/Iconos/emblem-sales.png");
        Icon closedIcon = createImageIcon("/Iconos/boxing_gloves_red.png");
        Icon openIcon = createImageIcon("/Iconos/boxing_gloves_red.png");
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);
        
        jTree1.setModel(modeloarbol);
        traverse(jTree1);
        
        String t[] = {"ID","PRODUCTO","TALLA","MARCA","TIPO", "PROVEEDOR", "COSTO","PRECIO VENTA","COD."};
        modelo1.setColumnIdentifiers(t);
        jTable1.setModel(modelo1);
    
}else{
        String nombreproducto="";
        int i = 0;
        Object[] object = new Object[5];
        System.out.println("aux2a = "+aux2a.size()+"asd i"+i);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode( "Productos Disponibles" );

        while(aux2a.size()>i)
        {   System.out.println("NOMBRE A BUSCAR: "+nombre);
            nombreproducto="";
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(aux2a.get(i).getNombre());
            nombreproducto=aux2a.get(i).getNombre();
            nombreproducto=nombreproducto.toUpperCase();
            System.out.println("SIN FORMATO: "+nombreproducto);
            nombreproducto=nombreproducto.replace(" ","");
            nombreproducto = nombreproducto.toUpperCase();
             System.out.println("CON FORMATO: "+nombreproducto);
            if((nombreproducto.indexOf(nombre.toUpperCase()))!=-1){
                 System.out.println("ENCONTRO: "+nombreproducto);
                root.add(nodo);}
            i++;
        }
        
       
        DefaultTreeModel modeloarbol = new DefaultTreeModel(root);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jTree1.getCellRenderer();
        ImageIcon leafIcon = createImageIcon("/Iconos/emblem-sales.png");
        Icon closedIcon = createImageIcon("/Iconos/boxing_gloves_red.png");
        Icon openIcon = createImageIcon("/Iconos/boxing_gloves_red.png");
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);
        
        jTree1.setModel(modeloarbol);
        traverse(jTree1);
        
        String t[] = {"ID","PRODUCTO","TALLA","MARCA","TIPO", "PROVEEDOR", "COSTO","PRECIO VENTA","COD."};
        modelo1.setColumnIdentifiers(t);
        jTable1.setModel(modelo1);       // TODO add your handling code here:
}
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       ArrayList<Productos> aux2a = null;
String nombre = jTextField1.getText();

        try {
            aux2a = new metodosDB().getProductos();
        } catch (SQLException ex) {
            Logger.getLogger(buscaProductoInventario.class.getName()).log(Level.SEVERE, null, ex);
        }

    
        int i = 0;
        Object[] object = new Object[5];
        System.out.println("aux2a = "+aux2a.size()+"asd i"+i);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode( "Productos Disponibles" );

        while(aux2a.size()>i)
        {
            DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(aux2a.get(i).getNombre());
            root.add(nodo);
            i++;
        }
        
       
        DefaultTreeModel modeloarbol = new DefaultTreeModel(root);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jTree1.getCellRenderer();
        ImageIcon leafIcon = createImageIcon("/Iconos/emblem-sales.png");
        Icon closedIcon = createImageIcon("/Iconos/boxing_gloves_red.png");
        Icon openIcon = createImageIcon("/Iconos/boxing_gloves_red.png");
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);
        
        jTree1.setModel(modeloarbol);
        traverse(jTree1);
        
        String t[] = {"ID","PRODUCTO","TALLA","MARCA","TIPO", "PROVEEDOR", "COSTO","PRECIO VENTA","COD."};
        modelo1.setColumnIdentifiers(t);
        jTable1.setModel(modelo1);
    
 // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void iniciar() throws SQLException
        {
            ArrayList<Productos> aux2a;
        aux2a = new metodosDB().getProductos();
        int i = 0;
        Object[] object = new Object[5];
        System.out.println("aux2a = "+aux2a.size()+"asd i"+i);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode( "Productos Disponibles" );

        while(aux2a.size()>i)
        {
            Productos p = aux2a.get(i);
            if(p.getCantidadActual()>0)
            {
                DefaultMutableTreeNode nodo = new DefaultMutableTreeNode(p.getNombre());
                root.add(nodo);
            }
                            i++;

        }
        
       
        DefaultTreeModel modeloarbol = new DefaultTreeModel(root);
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jTree1.getCellRenderer();
        ImageIcon leafIcon = createImageIcon("/Iconos/emblem-sales.png");
        Icon closedIcon = createImageIcon("/Iconos/boxing_gloves_red.png");
        Icon openIcon = createImageIcon("/Iconos/boxing_gloves_red.png");
        renderer.setClosedIcon(closedIcon);
        renderer.setOpenIcon(openIcon);
        renderer.setLeafIcon(leafIcon);
        
        jTree1.setModel(modeloarbol);
        traverse(jTree1);
        
        String t[] = {"ID","PRODUCTO","TALLA","MARCA","TIPO", "PROVEEDOR", "COSTO","PRECIO VENTA","COD."};
        modelo1.setColumnIdentifiers(t);
        jTable1.setModel(modelo1);
        
        
        
        }
    
    
    
     public void traverse(JTree tree) { 
        TreeModel model = jTree1.getModel(); 
        if (model != null) { 
            Object root = model.getRoot(); 
            System.out.println(root.toString()); 
            walk(model,root); 
        } 
        else
            System.out.println("Tree is empty."); 
    } 
 
    protected void walk(TreeModel model, Object o){ 
        int cc; 
        cc = model.getChildCount(o); 
        for( int i=0; i < cc; i++) { 
            Object child = model.getChild(o, i ); 
            if (model.isLeaf(child)) 
                System.out.println("Leaf: "+i+" "+child.toString()); 
            else { 
                System.out.print("\n\rParent: "+i+" "+child.toString()+"\n\r"); 
                walk(model,child ); 
            } 
        } 
    }
    protected static ImageIcon createImageIcon2(String path) {
    java.net.URL imgURL = frameInventarioActual.class.getResource(path);
    if (imgURL != null) {
        ImageIcon imageIcon =new ImageIcon(imgURL);
        Image image = imageIcon.getImage();
        Image nueva = image.getScaledInstance(200,200,java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(nueva);
        
      return imageIcon;
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
    
  }
    protected static ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = frameInventarioActual.class.getResource(path);
    if (imgURL != null) {
      return new ImageIcon(imgURL);
    } else {
      System.err.println("Couldn't find file: " + path);
      return null;
    }
  }
    /**
     * @param args the command line arguments
     */
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
