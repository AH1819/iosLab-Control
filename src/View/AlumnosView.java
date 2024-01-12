package View;

import Controller.AlumnosController;
import Controller.PersonasController;
import Model.Database.Alumno;
import Model.Database.Datos_Cargados;
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
public class AlumnosView extends SimpleForm {

    ArrayList<Alumno> alumnos;
    Alumno alumnoSelected;
    DefaultTableModel modelo;
    PersonasController pc = new PersonasController();
    Application app;

    public AlumnosView(Application app) {
        initComponents();
        this.app = app;
        init(AlumnosTable, 4);
        modelo = (DefaultTableModel) AlumnosTable.getModel();
        new Thread() {
            @Override
            public void run() {
                CargarAlumnos();
            }
        }.start();
    }

    public void CargarAlumnos() {
        pc.CargarAlumnos();
        alumnos = Datos_Cargados.getAlumnos_guardados();
        if (!alumnos.isEmpty() && alumnos.get(0).getMatricula() != null) {
            DataTable();
            txtSearch.setEnabled(true);
        } else {
            txtSearch.setEnabled(false);
            JOptionPane.showMessageDialog(app, "No hay registros para mostrar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        Cargando.setVisible(false);
    }

    private void DataTable() {
        modelo.setRowCount(0);
        for (Alumno alumno : alumnos) {
            Object[] fila = {
                alumno.getMatricula(),
                alumno.getNombre() + " " + alumno.getApellido_p() + " " + alumno.getApellido_m(),
                alumno.getProcedencia(),
                alumno.getSemestre() + " " + alumno.getGrupo(),
                alumno.getStatus()
            };
            modelo.addRow(fila);
        }
        AlumnosTable.setModel(modelo);
        AlumnosTable.getColumnModel().getColumn(4).setCellRenderer(new TableActionCellRender());
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
        AlumnosTable = new javax.swing.JTable();

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

        AlumnosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Matricula", "Nombre", "Procedencia", "Semestre y grupo", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        AlumnosTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        AlumnosTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                AlumnosTableFocusLost(evt);
            }
        });
        AlumnosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AlumnosTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                AlumnosTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(AlumnosTable);
        if (AlumnosTable.getColumnModel().getColumnCount() > 0) {
            AlumnosTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            AlumnosTable.getColumnModel().getColumn(2).setPreferredWidth(300);
            AlumnosTable.getColumnModel().getColumn(4).setPreferredWidth(50);
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

    private void AlumnosTableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_AlumnosTableFocusLost

    }//GEN-LAST:event_AlumnosTableFocusLost

    private void AlumnosTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlumnosTableMousePressed

    }//GEN-LAST:event_AlumnosTableMousePressed

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

    private void cmdAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdAddActionPerformed
        FormManager.CambiarTexto("  Agregar un Alumno");
        FormManager.showForm(new Alumno_New_or_Update(app, this, null));
    }//GEN-LAST:event_cmdAddActionPerformed

    private void ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ActualizarActionPerformed
        FormManager.CambiarTexto("  Editar un Alumno");
        FormManager.showForm(new Alumno_New_or_Update(app, this, alumnoSelected));
    }//GEN-LAST:event_ActualizarActionPerformed

    private void AlumnosTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AlumnosTableMouseClicked
        if (AlumnosTable.getSelectedRow() != -1) {
            for (Alumno alumno : alumnos) {
                if (alumno.getMatricula().equals(AlumnosTable.getValueAt(AlumnosTable.getSelectedRow(), 0))) {
                    alumnoSelected = alumno;
                    Actualizar.setEnabled(true);
                }
            }
            if (evt.getClickCount() == 2 && AlumnosTable.getSelectedColumn() == 4) {
                Baja_Alta();
            }
        }
    }//GEN-LAST:event_AlumnosTableMouseClicked

    private void Baja_Alta() {
        AlumnosController ac = new AlumnosController();

        if (ac.Baja_Alta(alumnoSelected.getMatricula(), alumnoSelected.getStatus().equals("Activo") ? "Inactivo" : "Activo")) {
            JOptionPane.showMessageDialog(app, "Alumno dado de " + (alumnoSelected.getStatus().equals("Activo") ? "baja" : "alta") + " con exito", "Actualizacion", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(app, "Hubo un error al momento de dar de " + (alumnoSelected.getStatus().equals("Activo") ? "baja" : "alta"), "Actualizacion", JOptionPane.ERROR_MESSAGE);
        }
        CargarAlumnos();
    }

    private void FiltroTabla(String busqueda, int opcion) {
        boolean Status = false;

        modelo.setRowCount(0);

        switch (opcion) {
            case 1:
                for (Alumno alumno : alumnos) {
                    if ((alumno.getNombre().toLowerCase() + " " + alumno.getApellido_p().toLowerCase() + " " + alumno.getApellido_m().toLowerCase()).contains(busqueda.toLowerCase())) {
                        Object[] arr = {
                            alumno.getMatricula(),
                            alumno.getNombre() + " " + alumno.getApellido_p() + " " + alumno.getApellido_m(),
                            alumno.getProcedencia(),
                            alumno.getSemestre(),
                            alumno.getGrupo(),
                            alumno.getStatus()
                        };
                        modelo.addRow(arr);
                        Status = true;
                    }
                }
                break;
            case 2:
                for (Alumno alumno : alumnos) {
                    if (alumno.getMatricula().toLowerCase().contains(busqueda.toLowerCase())) {
                        Object[] arr = {
                            alumno.getMatricula(),
                            alumno.getNombre() + " " + alumno.getApellido_p() + " " + alumno.getApellido_m(),
                            alumno.getProcedencia(),
                            alumno.getSemestre(),
                            alumno.getGrupo(),
                            alumno.getStatus()
                        };
                        modelo.addRow(arr);
                        Status = true;
                    }
                }
                break;
        }

        if (!Status) {
            DataTable();
            return;
        }
        Cargando.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Actualizar;
    private javax.swing.JTable AlumnosTable;
    private javax.swing.JLabel Cargando;
    private javax.swing.JButton cmdAdd;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
