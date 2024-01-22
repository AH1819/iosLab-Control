package View;

import Controller.EquiposController;
import Model.Database.Datos_Cargados;
import Model.Database.Ipads;
import Model.Database.Macs;
import Model.Views.SimpleForm;
import Model.Views.TableActionEvent;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.util.ArrayList;
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
public class EquiposView extends SimpleForm implements TableActionEvent {

    EquiposController ec = new EquiposController();
    ArrayList<Macs> mc;
    Macs mac;
    ArrayList<Ipads> id;
    Ipads ipad;
    DefaultTableModel modelo_macs;
    DefaultTableModel modelo_ipads;
    Application app;

    public EquiposView(Application app) {
        initComponents();
        init(MacsTable, 4);
        init(IpadsTable, 5);
        modelo_macs = (DefaultTableModel) MacsTable.getModel();
        modelo_ipads = (DefaultTableModel) IpadsTable.getModel();
        new Thread() {
            @Override
            public void run() {
                CargarEquipos();
            }
        }.start();
    }

    public void CargarEquipos() {
        ec.CargarMacs();
        ec.CargarIpad();
        mc = Datos_Cargados.getMacs_guardadas();
        id = Datos_Cargados.getIpads_guardadas();

        if (!mc.isEmpty() && mc.get(0).getNumero() != null) {
            DataTable(modelo_macs, MacsTable, 0);
        }
        if (!id.isEmpty() && id.get(0).getNumero() != null) {
            DataTable(modelo_ipads, IpadsTable, 1);
        }
        cmdAddMac.setEnabled(true);
        cmdNotesMacs.setEnabled(true);
        cmdEditMac.setEnabled(false);
        cmdAddNoteMac.setEnabled(false);
        cmdAddIpad.setEnabled(true);
        cmdNotesIpads.setEnabled(true);
        cmdEditIpad.setEnabled(false);
        cmdAddNoteIpad.setEnabled(false);
        CargandoMacs.setVisible(false);
        CargandoIpads.setVisible(false);
    }

    private void DataTable(DefaultTableModel modelo, JTable tabla, int opcion) {
        modelo.setRowCount(0);
        if (opcion == 0) {
            for (Macs macf : mc) {
                Object[] fila = {
                    macf.getNumero(), macf.getNumero_serie() != null ? macf.getNumero_serie() : "N/R", macf.getCargador(), macf.getNotas()
                };
                modelo.addRow(fila);
            }
        } else {
            for (Ipads ipadf : id) {
                Object[] fila = {
                    ipadf.getNumero(), ipadf.getNumero_serie() != null ? ipadf.getNumero_serie() : "N/R", ipadf.getApplepencil(), ipadf.getCargador(), ipadf.getNotas()
                };
                modelo.addRow(fila);
            }
        }
        tabla.setModel(modelo);
    }

    private void init(JTable table, int numcell) {
        cmdAddMac.setIcon(new FlatSVGIcon("Recursos/Icons/add.svg", 0.35f));
        cmdAddIpad.setIcon(new FlatSVGIcon("Recursos/Icons/add.svg", 0.35f));
        cmdNotesMacs.setIcon(new FlatSVGIcon("Recursos/Icons/note.svg", 0.35f));
        cmdNotesIpads.setIcon(new FlatSVGIcon("Recursos/Icons/note.svg", 0.35f));
        cmdEditMac.setIcon(new FlatSVGIcon("Recursos/Icons/edit.svg", 0.35f));
        cmdEditIpad.setIcon(new FlatSVGIcon("Recursos/Icons/edit.svg", 0.35f));
        cmdAddNoteMac.setIcon(new FlatSVGIcon("Recursos/Icons/notesAdd.svg", 0.35f));
        cmdAddNoteIpad.setIcon(new FlatSVGIcon("Recursos/Icons/notesAdd.svg", 0.35f));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < numcell; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        //  Change scroll style
        JScrollPane scroll = (JScrollPane) table.getParent().getParent();
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Table.background;"
                + "track:$Table.background;"
                + "trackArc:999");

        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        crazyPanel3 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        CargandoMacs = new javax.swing.JLabel();
        cmdAddMac = new javax.swing.JButton();
        cmdNotesMacs = new javax.swing.JButton();
        cmdEditMac = new javax.swing.JButton();
        cmdAddNoteMac = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        MacsTable = new javax.swing.JTable();
        crazyPanel4 = new raven.crazypanel.CrazyPanel();
        crazyPanel5 = new raven.crazypanel.CrazyPanel();
        jLabel2 = new javax.swing.JLabel();
        CargandoIpads = new javax.swing.JLabel();
        cmdAddIpad = new javax.swing.JButton();
        cmdNotesIpads = new javax.swing.JButton();
        cmdEditIpad = new javax.swing.JButton();
        cmdAddNoteIpad = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        IpadsTable = new javax.swing.JTable();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap 2,fill",
            "[fill]10[fill]",
            "[fill]",
            null
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            new String[]{
                "",
                "span,grow"
            }
        ));

        crazyPanel3.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "",
                "",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel3.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "fill",
            "[grow0,center][fill]",
            "[grow 0,center]",
            new String[]{
                "split 2,span 4,grow",
                "wrap",
                "",
                "",
                ""
            }
        ));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Macs");
        crazyPanel3.add(jLabel1);

        CargandoMacs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/cargando.gif"))); // NOI18N
        crazyPanel3.add(CargandoMacs);

        cmdAddMac.setText("Agregar Mac");
        cmdAddMac.setEnabled(false);
        cmdAddMac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddMacActionPerformed(evt);
            }
        });
        crazyPanel3.add(cmdAddMac);

        cmdNotesMacs.setText("Mostrar las notas");
        cmdNotesMacs.setEnabled(false);
        cmdNotesMacs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNotesMacsActionPerformed(evt);
            }
        });
        crazyPanel3.add(cmdNotesMacs);

        cmdEditMac.setText("Editar");
        cmdEditMac.setEnabled(false);
        cmdEditMac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditMacActionPerformed(evt);
            }
        });
        crazyPanel3.add(cmdEditMac);

        cmdAddNoteMac.setText("Agregar nota");
        cmdAddNoteMac.setEnabled(false);
        cmdAddNoteMac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddNoteMacActionPerformed(evt);
            }
        });
        crazyPanel3.add(cmdAddNoteMac);

        crazyPanel2.add(crazyPanel3);

        MacsTable.setAutoCreateRowSorter(true);
        MacsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Numero de serie", "Cargador", "Notas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        MacsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MacsTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MacsTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(MacsTable);
        if (MacsTable.getColumnModel().getColumnCount() > 0) {
            MacsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            MacsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        }

        crazyPanel2.add(jScrollPane1);

        crazyPanel1.add(crazyPanel2);

        crazyPanel4.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel4.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        crazyPanel5.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "",
                "",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel5.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "fill",
            "[grow 0,center][fill]",
            "[grow 0,center]",
            new String[]{
                "split 2,span 4,grow",
                "wrap",
                "",
                "",
                ""
            }
        ));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Ipads");
        crazyPanel5.add(jLabel2);

        CargandoIpads.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/cargando.gif"))); // NOI18N
        crazyPanel5.add(CargandoIpads);

        cmdAddIpad.setText("Agregar Ipad");
        cmdAddIpad.setEnabled(false);
        cmdAddIpad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddIpadActionPerformed(evt);
            }
        });
        crazyPanel5.add(cmdAddIpad);

        cmdNotesIpads.setText("Mostrar las notas");
        cmdNotesIpads.setEnabled(false);
        cmdNotesIpads.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNotesIpadsActionPerformed(evt);
            }
        });
        crazyPanel5.add(cmdNotesIpads);

        cmdEditIpad.setText("Editar");
        cmdEditIpad.setEnabled(false);
        cmdEditIpad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdEditIpadActionPerformed(evt);
            }
        });
        crazyPanel5.add(cmdEditIpad);

        cmdAddNoteIpad.setText("Agregar nota");
        cmdAddNoteIpad.setEnabled(false);
        cmdAddNoteIpad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddNoteIpadActionPerformed(evt);
            }
        });
        crazyPanel5.add(cmdAddNoteIpad);

        crazyPanel4.add(crazyPanel5);

        IpadsTable.setAutoCreateRowSorter(true);
        IpadsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Numero de serie", "Lapiz", "Cargador", "Notas"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        IpadsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                IpadsTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(IpadsTable);
        if (IpadsTable.getColumnModel().getColumnCount() > 0) {
            IpadsTable.getColumnModel().getColumn(0).setPreferredWidth(30);
            IpadsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            IpadsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        crazyPanel4.add(jScrollPane2);

        crazyPanel1.add(crazyPanel4);

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
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdNotesMacsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNotesMacsActionPerformed
        NotasModalView nmv = new NotasModalView(app, true, "M", null, this);
        nmv.setVisible(true);
    }//GEN-LAST:event_cmdNotesMacsActionPerformed

    private void cmdNotesIpadsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNotesIpadsActionPerformed
        NotasModalView nmv = new NotasModalView(app, true, "I", null, this);
        nmv.setVisible(true);
    }//GEN-LAST:event_cmdNotesIpadsActionPerformed

    private void cmdEditMacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditMacActionPerformed
        Insertar_Editar_Equipos ee = new Insertar_Editar_Equipos(app, true, "Editar Mac", mac, null, this);
        ee.setVisible(true);
    }//GEN-LAST:event_cmdEditMacActionPerformed

    private void cmdAddNoteMacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddNoteMacActionPerformed
        NotaAdd_Update na = new NotaAdd_Update(app, true, mac.getNumero(), mac.getId_dispositivo(), this);
        na.setVisible(true);
    }//GEN-LAST:event_cmdAddNoteMacActionPerformed

    private void MacsTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MacsTableMousePressed

    }//GEN-LAST:event_MacsTableMousePressed

    private void MacsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MacsTableMouseClicked
        if (MacsTable.getSelectedRow() > -1) {
            for (Macs macs : mc) {
                if (macs.getNumero().equals(MacsTable.getValueAt(MacsTable.getSelectedRow(), 0))) {
                    cmdEditMac.setEnabled(true);
                    cmdAddNoteMac.setEnabled(true);
                    mac = macs;
                }
            }
            if (evt.getClickCount() == 2) {
                if (!MacsTable.getValueAt(MacsTable.getSelectedRow(), 3).equals("0")) {
                    NotasModalView nv = new NotasModalView(app, true, "M", mac.getId_dispositivo(), this);
                    nv.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(app, "No hay notas registradas", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            cmdAddNoteMac.setEnabled(false);
            cmdEditMac.setEnabled(false);
        }
    }//GEN-LAST:event_MacsTableMouseClicked

    private void IpadsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_IpadsTableMouseClicked
        if (IpadsTable.getSelectedRow() > -1) {
            for (Ipads ipads : id) {
                if (ipads.getNumero().equals(IpadsTable.getValueAt(IpadsTable.getSelectedRow(), 0))) {
                    cmdEditIpad.setEnabled(true);
                    cmdAddNoteIpad.setEnabled(true);
                    ipad = ipads;
                }
            }
            if (evt.getClickCount() == 2) {
                if (!IpadsTable.getValueAt(IpadsTable.getSelectedRow(), 4).equals("0")) {
                    NotasModalView nv = new NotasModalView(app, true, "I", ipad.getId_dispositivo(), this);
                    nv.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(app, "No hay notas registradas", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        } else {
            cmdAddNoteIpad.setEnabled(false);
            cmdEditIpad.setEnabled(false);
        }
    }//GEN-LAST:event_IpadsTableMouseClicked

    private void cmdEditIpadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdEditIpadActionPerformed
        Insertar_Editar_Equipos ee = new Insertar_Editar_Equipos(app, true, "Editar Ipad", null, ipad, this);
        ee.setVisible(true);
    }//GEN-LAST:event_cmdEditIpadActionPerformed

    private void cmdAddNoteIpadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddNoteIpadActionPerformed
        NotaAdd_Update na = new NotaAdd_Update(app, true, ipad.getNumero(), ipad.getId_dispositivo(), this);
        na.setVisible(true);
    }//GEN-LAST:event_cmdAddNoteIpadActionPerformed

    private void cmdAddMacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddMacActionPerformed
        Insertar_Editar_Equipos ee = new Insertar_Editar_Equipos(app, true, "Registrar Mac", this);
        ee.setVisible(true);
    }//GEN-LAST:event_cmdAddMacActionPerformed

    private void cmdAddIpadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddIpadActionPerformed
        Insertar_Editar_Equipos ee = new Insertar_Editar_Equipos(app, true, "Registrar Ipad", this);
        ee.setVisible(true);
    }//GEN-LAST:event_cmdAddIpadActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel CargandoIpads;
    private javax.swing.JLabel CargandoMacs;
    private javax.swing.JTable IpadsTable;
    private javax.swing.JTable MacsTable;
    private javax.swing.JButton cmdAddIpad;
    private javax.swing.JButton cmdAddMac;
    private javax.swing.JButton cmdAddNoteIpad;
    private javax.swing.JButton cmdAddNoteMac;
    private javax.swing.JButton cmdEditIpad;
    private javax.swing.JButton cmdEditMac;
    private javax.swing.JButton cmdNotesIpads;
    private javax.swing.JButton cmdNotesMacs;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private raven.crazypanel.CrazyPanel crazyPanel3;
    private raven.crazypanel.CrazyPanel crazyPanel4;
    private raven.crazypanel.CrazyPanel crazyPanel5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
