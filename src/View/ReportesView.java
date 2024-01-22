package View;

import Controller.PrestamosController;
import Model.Database.Datos_Cargados;
import Model.Database.Prestamos;
import PrintReport.Jasper;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserAdapter;
import java.util.ArrayList;
import java.sql.Date;
import java.util.stream.Collectors;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hitler
 */
public class ReportesView extends javax.swing.JPanel {

    private ArrayList<Prestamos> filtro;
    private final DefaultTableModel modelo;
    private final DateChooser dch = new DateChooser();
    private Date Inicio;
    private Date Fin;
    private boolean status = true;

    public ReportesView() {
        initComponents();
        CargarImprimir.setVisible(false);
        modelo = (DefaultTableModel) jTable1.getModel();
        init(jTable1);
        new Thread() {
            @Override
            public void run() {
                CargarPrestamos();
            }
        }.start();
        dch.setTextField(Rango);
        dch.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        dch.addActionDateChooserListener(new DateChooserAdapter() {
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
                Date inicio = new Date(date.getFromDate().getTime());
                Date fin = new Date(date.getToDate().getTime());
                FiltrarFechas(inicio, fin);
            }
        });
    }

    private void init(JTable table) {
        imprimir.setIcon(new FlatSVGIcon("Recursos/Icons/print.svg", 0.45f));
        //  Change scroll style
        JScrollPane scroll = (JScrollPane) table.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 6; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        Rango = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        CargarSearch = new javax.swing.JLabel();
        imprimir = new javax.swing.JButton();
        CargarImprimir = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fillx,insets 15",
            "[fill]",
            "[fill]",
            null
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "",
                "background:lighten(@background,8%);borderWidth:1",
                "",
                "background:lighten(@background,8%);borderWidth:1",
                ""
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[][][]push[][]",
            "",
            new String[]{
                "width 300",
                "",
                "",
                ""
            }
        ));
        crazyPanel2.add(Rango);

        jButton1.setText("Todo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton1);

        CargarSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/cargando.gif"))); // NOI18N
        crazyPanel2.add(CargarSearch);

        imprimir.setText("Imprimir");
        imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirActionPerformed(evt);
            }
        });
        crazyPanel2.add(imprimir);

        CargarImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/cargando.gif"))); // NOI18N
        crazyPanel2.add(CargarImprimir);

        crazyPanel1.add(crazyPanel2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Matricula/Plaza", "Nombre", "Mac", "Ipad", "Fecha"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        crazyPanel1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirActionPerformed
        new Thread() {
            @Override
            public void run() {
                new Jasper().Generar_Reporte(Inicio, Fin, status);
            }
        }.start();
    }//GEN-LAST:event_imprimirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CargarPrestamos();
        status = true;
    }//GEN-LAST:event_jButton1ActionPerformed

    public void CargarPrestamos() {
        PrestamosController pdc = new PrestamosController();
        pdc.GetPrestaciones();
        filtro = Datos_Cargados.getPrestamos_guardados();
        if (!filtro.isEmpty() && filtro.get(0).getNombre() != null) {
            DataTable();
        } else {
            imprimir.setVisible(false);
            JOptionPane.showMessageDialog(this, "No hay registros para mostrar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        CargarSearch.setVisible(false);
    }

    private void DataTable() {
        modelo.setRowCount(0);
        for (Prestamos pr : filtro) {
            Object[] fila = {
                pr.getIdentificador(),
                pr.getId_usuario(),
                pr.getNombre(),
                pr.getId_mac(),
                pr.getId_ipad(),
                pr.getFecha()
            };
            modelo.addRow(fila);
        }
        jTable1.setModel(modelo);
    }

    private void FiltrarFechas(Date inicio, Date fin) {
        Inicio = inicio;
        Fin = fin;
        status = false;
        CargarSearch.setVisible(true);
        filtro = (ArrayList<Prestamos>) Datos_Cargados.getPrestamos_guardados().stream().filter((fecha) -> fecha.getFecha().compareTo(inicio) >= 0 && fecha.getFecha().compareTo(fin) <= 0).collect(Collectors.toList());
        if (!filtro.isEmpty()) {
            modelo.setRowCount(0);
            imprimir.setVisible(true);
            for (Prestamos pr : filtro) {
                Object[] fila = {
                    pr.getIdentificador(),
                    pr.getId_usuario(),
                    pr.getNombre(),
                    pr.getId_mac(),
                    pr.getId_ipad(),
                    pr.getFecha(),
                    pr.getStatus()
                };
                modelo.addRow(fila);
            }
            jTable1.setModel(modelo);
        }
        CargarSearch.setVisible(false);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CargarImprimir;
    private javax.swing.JLabel CargarSearch;
    private javax.swing.JTextField Rango;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JButton imprimir;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
