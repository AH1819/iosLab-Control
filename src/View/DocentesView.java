package View;

import Controller.DocentesController;
import Controller.PersonasController;
import Model.Database.Datos_Cargados;
import Model.Database.Docente;
import Model.Views.FormManager;
import Model.Views.SimpleForm;
import Model.Views.TableActionCellRender;
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
public class DocentesView extends SimpleForm {

    ArrayList<Docente> docentes;
    Docente docenteSelected;
    DefaultTableModel modelo;
    PersonasController pc = new PersonasController();
    Application app;

    public DocentesView(Application app) {
        initComponents();
        this.app = app;
        init(DocentesTable, 4);
        modelo = (DefaultTableModel) DocentesTable.getModel();
        CargarDocentes();
    }

    public void CargarDocentes() {
        pc.CargarDocentes();
        docentes = Datos_Cargados.getDocentes_guardados();
        if (!docentes.isEmpty() && docentes.get(0).getNumero_plaza() != null) {
            DataTable();
            txtSearch.setEnabled(true);
        } else {
            txtSearch.setEnabled(false);
            JOptionPane.showMessageDialog(app, "No hay registros para mostrar", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        Cargando.setVisible(false);
    }

    private void DataTable() {
        modelo.setRowCount(0);
        for (Docente docente : docentes) {
            Object[] fila = {
                docente.getNumero_plaza(),
                docente.getNombre() + " " + docente.getApellido_p() + " " + docente.getApellido_m(),
                docente.getInstitucion(),
                docente.getCorreo(),
                docente.getStatus()
            };
            modelo.addRow(fila);
        }
        DocentesTable.setModel(modelo);
        DocentesTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
    }

    private void init(JTable table, int numcell) {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < numcell; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        cmdAdd.setIcon(new FlatSVGIcon("Recursos/Icons/add.svg", 0.35f));
        Actualizar.setIcon(new FlatSVGIcon("Recursos/Icons/edit.svg", 0.35f));

        txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_TRAILING_ICON, new FlatSVGIcon("Recursos/Icons/search.svg", 0.35f));
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
        txtSearch = new javax.swing.JTextField();
        Cargando = new javax.swing.JLabel();
        cmdAdd = new javax.swing.JButton();
        Actualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        DocentesTable = new javax.swing.JTable();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill,insets 15",
            "[fill]",
            "[grow 0][fill]",
            null
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "JTextField.placeholderText=Search;background:@background",
                "",
                "background:lighten(@background,8%);borderWidth:1",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[][]push[][]",
            "",
            new String[]{
                "width 200"
            }
        ));
        crazyPanel2.setPreferredSize(new java.awt.Dimension(444, 46));

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        crazyPanel2.add(txtSearch);

        Cargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/cargando.gif"))); // NOI18N
        crazyPanel2.add(Cargando);

        cmdAdd.setText("Agregar");
        cmdAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdAddActionPerformed(evt);
            }
        });
        crazyPanel2.add(cmdAdd);

        Actualizar.setText("Modificar");
        Actualizar.setEnabled(false);
        Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ActualizarActionPerformed(evt);
            }
        });
        crazyPanel2.add(Actualizar);

        crazyPanel1.add(crazyPanel2);

        DocentesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Plaza", "Nombre", "Institucion", "Correo", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        DocentesTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                DocentesTableFocusLost(evt);
            }
        });
        DocentesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DocentesTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DocentesTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(DocentesTable);
        if (DocentesTable.getColumnModel().getColumnCount() > 0) {
            DocentesTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            DocentesTable.getColumnModel().getColumn(2).setPreferredWidth(300);
            DocentesTable.getColumnModel().getColumn(3).setPreferredWidth(200);
            DocentesTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DocentesTableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_DocentesTableFocusLost

    }//GEN-LAST:event_DocentesTableFocusLost

    private void DocentesTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DocentesTableMousePressed

    }//GEN-LAST:event_DocentesTableMousePressed

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        FormManager.CambiarTexto("  Agregar un Docente");
        FormManager.showForm(new Docente_New_or_Update(app, this, null));
    }//GEN-LAST:event_cmdAddActionPerformed

    private void DocentesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DocentesTableMouseClicked
        if (DocentesTable.getSelectedRow() != -1) {
            for (Docente docente : docentes) {
                if (docente.getNumero_plaza().equals(DocentesTable.getValueAt(DocentesTable.getSelectedRow(), 0))) {
                    docenteSelected = docente;
                    Actualizar.setEnabled(true);
                }
            }
            if (evt.getClickCount() == 2 && DocentesTable.getSelectedColumn() == 4) {
                Baja_Alta();
            }
        }
    }//GEN-LAST:event_DocentesTableMouseClicked

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        FormManager.CambiarTexto("  Editar un Docente");
        FormManager.showForm(new Docente_New_or_Update(app, this, docenteSelected));
    }//GEN-LAST:event_ActualizarActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (!txtSearch.getText().isEmpty()) {
            Cargando.setVisible(true);
            if (txtSearch.getText().matches(".*\\d.*")) {
                FiltroTabla(txtSearch.getText(), 2);
            } else {
                FiltroTabla(txtSearch.getText(), 1);
            }
        } else {
            DataTable();
        }
        Cargando.setVisible(false);
    }//GEN-LAST:event_txtSearchKeyReleased

    private void Baja_Alta() {
        DocentesController dc = new DocentesController();

        if (dc.Baja_Alta(docenteSelected.getNumero_plaza(), docenteSelected.getStatus().equals("Activo") ? "Inactivo" : "Activo")) {
            JOptionPane.showMessageDialog(app, "Docente dado de " + (docenteSelected.getStatus().equals("Activo") ? "baja" : "alta") + " con exito", "Actualizacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(app, "Hubo un error al momento de dar de " + (docenteSelected.getStatus().equals("Activo") ? "baja" : "alta"), "Actualizacion", JOptionPane.ERROR_MESSAGE);
        }
        CargarDocentes();
    }

    public boolean contieneNumero(String numeroOriginal, String numeroObjetivo) {
        String numOriginalStr = String.valueOf(numeroOriginal);
        String numObjetivoStr = String.valueOf(numeroObjetivo);

        return numOriginalStr.contains(numObjetivoStr);
    }

    private void FiltroTabla(String busqueda, int opcion) {
        boolean Status = false;

        modelo.setRowCount(0);

        switch (opcion) {
            case 1:
                for (Docente docente : docentes) {
                    if ((docente.getNombre() + " " + docente.getApellido_p() + " " + docente.getApellido_m()).toLowerCase().contains(busqueda.toLowerCase())) {
                        Object[] fila = {
                            docente.getNumero_plaza(),
                            docente.getNombre() + " " + docente.getApellido_p() + " " + docente.getApellido_m(),
                            docente.getInstitucion(),
                            docente.getCorreo(),
                            docente.getStatus()
                        };
                        modelo.addRow(fila);
                        Status = true;
                    }
                }
                break;
            case 2:
                for (Docente docente : docentes) {
                    if (contieneNumero(docente.getNumero_plaza(), busqueda.trim())) {
                        Object[] fila = {
                            docente.getNumero_plaza(),
                            docente.getNombre() + " " + docente.getApellido_p() + " " + docente.getApellido_m(),
                            docente.getInstitucion(),
                            docente.getCorreo(),
                            docente.getStatus()
                        };
                        modelo.addRow(fila);
                        Status = true;
                    }
                }
                break;
        }

        if (!Status) {
            DataTable();
            return;
        }
        DocentesTable.setModel(modelo);
        Cargando.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JLabel Cargando;
    private javax.swing.JTable DocentesTable;
    private javax.swing.JButton cmdAdd;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
