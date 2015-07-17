/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Clases.funciones;
import ObjetosDB.Productos;
import ObjetosDB.metodosDB;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alejandro
 */
public class buscar_producto extends javax.swing.JDialog implements KeyListener, ActionListener, MouseListener {

    int usuario, a = 1, mnsj, num_celdas = 44;
    String vacio = "NO SE OBTUVO RESULTADO CON LOS PARAMETROS INGRESADOS.";
    String correcto = "OPERACIÓN REALIZADA CON ÉXITO.", error = "HA OCURRIDO EL SIGUIENTE ERROR:\n";
    String no_empleado = "NO SE REGISTRA EMPLEADO CON EL RUT INGRESADO.";
    String no_opcion = "DEBE SELECCIONAR UNA DE LAS OPCIONES DEL SISTEMA.";
    String no_numero = "DEBE INGRESAR UN DATO NUMÉRICO EN EL CAMPO ";
    String num_no_valido = "DEBE INGRESAR UN NÚMERO VÁLIDO EN EL CAMPO ";
    String campo_vacio = "DEBE COMPLETAR EL SIGUIENTE CAMPO:\n";
    DefaultTableModel modelo = new DefaultTableModel() {

        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public buscar_producto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        iniciar();
    }

    buscar_producto(int usuario) {
        initComponents();
        this.usuario = usuario;
        iniciar();
    }

    buscar_producto(jframeUsuario aThis, boolean b, int u) {
        super(aThis, b);
        initComponents();
        this.usuario = u;
        iniciar();
    }

    private void iniciar() {
String t[] = {"ID", "NOMBRE", "TALLA", "MARCA", "CANTIDAD ACTUAL", "TIPO", "PROVEEDOR","PRECIO COMPRA", "PRECIO VENTA","CODIGO"};
        modelo.setColumnIdentifiers(t);
        jTable1.setModel(modelo);
        modelo.setNumRows(0);
        jProgressBar1.setVisible(false);
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr2.setHorizontalAlignment(SwingConstants.CENTER);
        tcr.setHorizontalAlignment(SwingConstants.RIGHT);
        jTable1.getColumn("ID").setPreferredWidth(8);
        jTable1.getColumn("NOMBRE").setPreferredWidth(30);
        jTable1.getColumn("MARCA").setPreferredWidth(30);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(tcr2);
        jTable1.getColumnModel().getColumn(3).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(4).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(5).setCellRenderer(tcr);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(tcr2);
        ((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        jButton2.addKeyListener(this);
        jButton5.addKeyListener(this);
        jTable1.addKeyListener(this);
        jTable1.setRowHeight(22);
        jTable1.setCellSelectionEnabled(false);
        jTable1.setRowSelectionAllowed(true);

    }

    /*public void ListaSolPend(final String date) {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        jProgressBar1.setVisible(true);
        final SwingWorker worker = new SwingWorker() {

            @Override
            protected Void doInBackground() throws Exception {
                try {
                    funciones f = new funciones();
                    String r1 = getListadoSolPend(date);
                    String[] r2;
                    if (r1.equals("vacio")) {
                        jProgressBar1.setVisible(false);
                        setCursor(Cursor.getDefaultCursor());
                        mnsj = JOptionPane.showConfirmDialog(null, "NO SE REGISTRAN SOLICITUDES PENDIENTES.", "INFORMACIÓN", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE);
                        return null;
                    } else {
                        jProgressBar1.setString("ESPERE UN MOMENTO.");
                        jProgressBar1.setStringPainted(true);
                        jProgressBar1.setIndeterminate(true);
                        String nom=f.getFechaTxtInv(Calendar.getInstance().getTime())+" SOLICITUDES PROCESADAS";
                        try {
                            String[] detsol = null;
                            int j = 0, benef = 0, gasto = 0, total_gasto = 0, total_benef = 0;
                            File file = new File(f.getRuta_reportes() + nom + ".pdf");
                            FileOutputStream archivo = new FileOutputStream(f.getRuta_reportes() + nom + ".pdf");
                            Document documento = new Document(PageSize.LETTER);
                            PdfWriter.getInstance(documento, archivo);
                            documento.open();
                            float[] colsWidth = {1f, 2.7f, 1.8f, 1.6f, 2f, 1.2f, 1.7f, 1.7f, 1.9f, 3f};
                            PdfPTable tabla = new PdfPTable(10);
                            tabla.setWidths(colsWidth);
                            tabla.setWidthPercentage(100);
                            PdfPCell celda;
                            r2 = r1.split("_");
                            int num_sol = r1.split("_").length, det = r1.replace("_", "=").split("=").length;
                            r2 = r1.replace("_", "=").split("=");
                            int nro_hoja = ((num_sol + det) / num_celdas) + 1;
                            int hoja = 1;
                            int sol = Integer.valueOf(r2[0].split(";")[0]);
                            for (int i = 0; i < r2.length; i++) { // i representa cada detalle de solicitud                                 
                                detsol = r2[i].split(";");
                                if (j % num_celdas == 0) {
                                    if (j != 0) {
                                        documento.add(tabla);
                                        documento.newPage();
                                        hoja++;
                                    }
                                    tabla = new PdfPTable(10);
                                    tabla.setWidths(colsWidth);
                                    tabla.setWidthPercentage(100);
                                    Paragraph p = new Paragraph("DEPARTAMENTO ADMINISTRADOR DE BENEFICIOS P.U.C.V", FontFactory.getFont("times new roman", 10, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK));
                                    p.setAlignment(Paragraph.ALIGN_LEFT);
                                    documento.add(p);
                                    p = new Paragraph("SISTEMA ADMINISTRADOR DE BENEFICIOS PARA LOS EMPLEADOS", FontFactory.getFont("times new roman", 10, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK));
                                    p.setAlignment(Paragraph.ALIGN_LEFT);
                                    documento.add(p);
                                    p = new Paragraph("PÁGINA: " + hoja + " DE " + nro_hoja, FontFactory.getFont("times new roman", 10, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK));
                                    p.setAlignment(Paragraph.ALIGN_RIGHT);
                                    documento.add(p);
                                    p = new Paragraph("SOLICITUDES PROCESADAS", FontFactory.getFont("times new roman", 9, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK));
                                    p.setAlignment(Element.ALIGN_CENTER);
                                    documento.add(p);
                                    p = new Paragraph("FECHA LISTADO: " + nom.split("-")[0].substring(6) + "/" + nom.split("-")[0].substring(4, 6) + "/" + nom.split("-")[0].substring(2, 4), FontFactory.getFont("times new roman", 9, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK));
                                    p.setAlignment(Element.ALIGN_LEFT);
                                    documento.add(p);
                                    documento.add(new Paragraph(" "));
                                    tabla = new PdfPTable(10);
                                    tabla.setWidths(colsWidth);
                                    tabla.setWidthPercentage(100);
                                    celda = new PdfPCell(new Paragraph("COD", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("CUENTA", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("ARANCEL", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("FECHA", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("RUT SOCIO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("FOLIO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("GASTO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("BENEFICIO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("ESTADO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("NOM SOCIO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                }
                                if (sol != Integer.valueOf(detsol[0])) {
                                    sol = Integer.valueOf(detsol[0]);
                                    documento.add(tabla);
                                    tabla = new PdfPTable(10);
                                    tabla.setWidths(colsWidth);
                                    tabla.setWidthPercentage(100);
                                    celda = new PdfPCell(new Paragraph("", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("           TOTAL SOLICITUD", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    celda.setColspan(5);
                                    tabla.addCell(celda);
                                    total_gasto = total_gasto + gasto;
                                    total_benef = total_benef + benef;
                                    celda = new PdfPCell(new Paragraph(f.getNumcPto(gasto).replace(",", "."), FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph(f.getNumcPto(benef).replace(",", "."), FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                    tabla.addCell(celda);
                                    celda = new PdfPCell(new Paragraph("", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    celda.setColspan(2);
                                    tabla.addCell(celda);
                                    gasto = benef = 0;
                                    j++;
                                    if (j % num_celdas == 0) {
                                        if (j != 0) {
                                            documento.add(tabla);
                                            documento.newPage();
                                            hoja++;
                                        }
                                        tabla = new PdfPTable(10);
                                        tabla.setWidths(colsWidth);
                                        tabla.setWidthPercentage(100);
                                        Paragraph p = new Paragraph("DEPARTAMENTO ADMINISTRADOR DE BENEFICIOS P.U.C.V", FontFactory.getFont("times new roman", 10, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK));
                                        p.setAlignment(Paragraph.ALIGN_LEFT);
                                        documento.add(p);
                                        p = new Paragraph("SISTEMA ADMINISTRADOR DE BENEFICIOS PARA LOS EMPLEADOS", FontFactory.getFont("times new roman", 10, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK));
                                        p.setAlignment(Paragraph.ALIGN_LEFT);
                                        documento.add(p);
                                        p = new Paragraph("PÁGINA: " + hoja + " DE " + nro_hoja, FontFactory.getFont("times new roman", 10, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK));
                                        p.setAlignment(Paragraph.ALIGN_RIGHT);
                                        documento.add(p);
                                        p = new Paragraph("SOLICITUDES PROCESADAS", FontFactory.getFont("times new roman", 9, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK));
                                        p.setAlignment(Element.ALIGN_CENTER);
                                        documento.add(p);
                                        p = new Paragraph("FECHA LISTADO: " + nom.split("-")[0].substring(6) + "/" + nom.split("-")[0].substring(4, 6) + "/" + nom.split("-")[0].substring(2, 4), FontFactory.getFont("times new roman", 9, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK));
                                        p.setAlignment(Element.ALIGN_LEFT);
                                        documento.add(p);
                                        documento.add(new Paragraph(" "));
                                        tabla = new PdfPTable(10);
                                        tabla.setWidths(colsWidth);
                                        tabla.setWidthPercentage(100);
                                        celda = new PdfPCell(new Paragraph("COD", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(celda);
                                        celda = new PdfPCell(new Paragraph("CUENTA", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(celda);
                                        celda = new PdfPCell(new Paragraph("ARANCEL", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(celda);
                                        celda = new PdfPCell(new Paragraph("FECHA", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(celda);
                                        celda = new PdfPCell(new Paragraph("RUT SOCIO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(celda);
                                        celda = new PdfPCell(new Paragraph("FOLIO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(celda);
                                        celda = new PdfPCell(new Paragraph("GASTO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(celda);
                                        celda = new PdfPCell(new Paragraph("BENEFICIO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(celda);
                                        celda = new PdfPCell(new Paragraph("ESTADO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(celda);
                                        celda = new PdfPCell(new Paragraph("NOM SOCIO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                        celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                        tabla.addCell(celda);
                                    }
                                }
                                celda = new PdfPCell(new Paragraph(detsol[1], FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                tabla.addCell(celda);
                                celda = new PdfPCell(new Paragraph(detsol[2], FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                                tabla.addCell(celda);
                                if (detsol[3].equals("00-00-000")) {
                                    celda = new PdfPCell(new Paragraph(" ", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                } else {
                                    celda = new PdfPCell(new Paragraph(detsol[3], FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                    celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                    tabla.addCell(celda);
                                }
                                celda = new PdfPCell(new Paragraph(detsol[4], FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                tabla.addCell(celda);
                                celda = new PdfPCell(new Paragraph(f.getNumcPto(Integer.valueOf(detsol[5])).replace(",", ".") + "-" + f.validar_rut(detsol[5]), FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                tabla.addCell(celda);
                                celda = new PdfPCell(new Paragraph(detsol[6], FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                tabla.addCell(celda);
                                gasto = gasto + Integer.valueOf(detsol[7]);
                                celda = new PdfPCell(new Paragraph(f.getNumcPto(Integer.valueOf(detsol[7])).replace(",", "."), FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                tabla.addCell(celda);
                                if (detsol[9].equals("ACEPTADO")) {
                                    benef = benef + Integer.valueOf(detsol[8]);
                                }
                                celda = new PdfPCell(new Paragraph(f.getNumcPto(Integer.valueOf(detsol[8])).replace(",", "."), FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                                tabla.addCell(celda);
                                celda = new PdfPCell(new Paragraph(detsol[9], FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                                tabla.addCell(celda);
                                celda = new PdfPCell(new Paragraph(detsol[10], FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                                celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                                tabla.addCell(celda);
                                j++;
                            }
                            documento.add(tabla);
                            tabla = new PdfPTable(10);
                            tabla.setWidths(colsWidth);
                            tabla.setWidthPercentage(100);
                            celda = new PdfPCell(new Paragraph("", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla.addCell(celda);
                            celda = new PdfPCell(new Paragraph("           TOTAL SOLICITUD", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                            celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                            celda.setColspan(5);
                            tabla.addCell(celda);
                            total_gasto = total_gasto + gasto;
                            total_benef = total_benef + benef;
                            celda = new PdfPCell(new Paragraph(f.getNumcPto(gasto).replace(",", "."), FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                            celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            tabla.addCell(celda);
                            celda = new PdfPCell(new Paragraph(f.getNumcPto(benef).replace(",", "."), FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                            celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            tabla.addCell(celda);
                            celda = new PdfPCell(new Paragraph("", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda.setColspan(2);
                            tabla.addCell(celda);
                            documento.add(tabla);
                            tabla = new PdfPTable(10);
                            tabla.setWidths(colsWidth);
                            tabla.setWidthPercentage(100);
                            celda = new PdfPCell(new Paragraph("", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                            tabla.addCell(celda);
                            celda = new PdfPCell(new Paragraph("           TOTAL LISTADO", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                            celda.setHorizontalAlignment(Element.ALIGN_LEFT);
                            celda.setColspan(5);
                            tabla.addCell(celda);
                            celda = new PdfPCell(new Paragraph(f.getNumcPto(total_gasto).replace(",", "."), FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                            celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            tabla.addCell(celda);
                            celda = new PdfPCell(new Paragraph(f.getNumcPto(total_benef).replace(",", "."), FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                            celda.setHorizontalAlignment(Element.ALIGN_RIGHT);
                            tabla.addCell(celda);
                            celda = new PdfPCell(new Paragraph("", FontFactory.getFont("times new roman", 8, java.awt.Font.ROMAN_BASELINE, BaseColor.BLACK)));
                            celda.setHorizontalAlignment(Element.ALIGN_CENTER);
                            celda.setColspan(2);
                            tabla.addCell(celda);
                            documento.add(tabla);
                            documento.close();
                            jProgressBar1.setVisible(false);
                            if (Desktop.isDesktopSupported() == true) {
                                Desktop desktop = Desktop.getDesktop();
                                try {
                                    desktop.open(file);
                                    setCursor(Cursor.getDefaultCursor());
                                    return null;
                                } catch (Exception e) {
                                    jProgressBar1.setVisible(false);
                                    setCursor(Cursor.getDefaultCursor());
                                    mnsj = JOptionPane.showConfirmDialog(null, error + e.getMessage(), "INFORMACIÓN", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                                    return null;
                                }
                            }
                        } catch (Exception ex) {
                            jProgressBar1.setVisible(false);
                            setCursor(Cursor.getDefaultCursor());
                            mnsj = JOptionPane.showConfirmDialog(null, error + ex.getMessage(), "INFORMACIÓN", JOptionPane.PLAIN_MESSAGE, JOptionPane.ERROR_MESSAGE);
                            return null;
                        }
                    }

                } catch (Exception ex) {
                }
                setCursor(Cursor.getDefaultCursor());
                return null;
            }
        };
        worker.execute();
    }

    public void buscar_sol() {
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        final SwingWorker worker = new SwingWorker() {

            @Override
            protected Void doInBackground() throws Exception {
                try {
                } catch (Exception ex) {
                }
                setCursor(Cursor.getDefaultCursor());
                return null;
            }
        };
        worker.execute();
    }*/

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BUSCAR SOLICITUD");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton2.setText("BUSCAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jButton5.setText("CERRAR");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jTable1.setToolTipText("");
        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jProgressBar1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jLabel3.setText("BUSCAR");

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NOMBRE", "ID PRODUCTO", "MARCA" }));

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(123, 450, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
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
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
    }//GEN-LAST:event_formWindowOpened

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        dispose();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
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
            }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        jButton2ActionPerformed(evt);
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(buscar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(buscar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(buscar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(buscar_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>    
        //</editor-fold>    

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                buscar_producto dialog = new buscar_producto(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

   

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

  
}
