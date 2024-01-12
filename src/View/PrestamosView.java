package View;

import Controller.PrestamosController;
import Model.Database.Alumno;
import Model.Database.Datos_Cargados;
import Model.Database.Docente;
import Model.Database.Persona;
import Model.Database.Prestamos;
import Model.Views.FormManager;
import Model.Views.SimpleForm;
import Model.Views.TableActionCellEditor;
import Model.Views.TableActionCellRender;
import Model.Views.TableActionEvent;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.ItemEvent;
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
public class PrestamosView extends SimpleForm implements TableActionEvent {

    ArrayList<Docente> docentes;
    ArrayList<Alumno> alumnos;
    DefaultTableModel modelo;
    ArrayList<Prestamos> prestamos;
    Application app;

    public PrestamosView(Application app) {
        initComponents();
        init(PrestamosTable);
        modelo = (DefaultTableModel) PrestamosTable.getModel();
        this.app = app;
        new Thread() {
            @Override
            public void run() {
                CargarPersonas();
                CargarPrestamos();
            }
        }.start();
    }

    private void CargarPersonas() {
        docentes = Datos_Cargados.getDocentes_guardados();
        alumnos = Datos_Cargados.getAlumnos_guardados();
        if (!docentes.isEmpty() && docentes.get(0).getNumero_plaza() != null) {
            Docentes.addItem("Selecciona una opcion");
            docentes.forEach((docente) -> {
                if (docente.getStatus().equals("Activo")) {
                    Docentes.addItem(docente.getNombre() + " " + docente.getApellido_p() + " " + docente.getApellido_m());
                }
            });
        } else {
            Docentes.setEnabled(false);
            JOptionPane.showMessageDialog(app, "No se encontraron registros de docentes", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        if (!alumnos.isEmpty() && alumnos.get(0).getMatricula() != null) {
            Alumnos.addItem("Selecciona una opcion");
            alumnos.forEach((alumno) -> {
                if (alumno.getStatus().equals("Activo")) {
                    Alumnos.addItem(alumno.getNombre() + " " + alumno.getApellido_p() + " " + alumno.getApellido_m());
                }
            });
        } else {
            Alumnos.setEnabled(false);
            JOptionPane.showMessageDialog(app, "No se encontraron registros de alumnos", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        Cargando.setVisible(false);
    }

    public void CargarPrestamos() {
        PrestamosController pdc = new PrestamosController();
        pdc.GetPrestaciones();
        prestamos = Datos_Cargados.getPrestamos_guardados();
        if (!prestamos.isEmpty() && prestamos.get(0).getNombre() != null) {
            DataTable();
        } else {
            JOptionPane.showMessageDialog(app, "No hay registros para mostrar", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void DataTable() {
        modelo.setRowCount(0);
        for (Prestamos pr : prestamos) {
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
        PrestamosTable.setModel(modelo);
        PrestamosTable.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
    }

    private void init(JTable table) {
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

        TableActionCellEditor eventEditor = new TableActionCellEditor(this);
        table.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(7).setCellEditor(eventEditor);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < 7; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        crazyPanel2 = new raven.crazypanel.CrazyPanel();
        jLabel2 = new javax.swing.JLabel();
        Docentes = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        Alumnos = new javax.swing.JComboBox<>();
        txtSearch = new javax.swing.JTextField();
        Cargando = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        PrestamosTable = new javax.swing.JTable();

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fillx,insets 15",
            "[fill]",
            "[fill]",
            new String[]{
                "",
                "grow"
            }
        ));

        crazyPanel2.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background",
            new String[]{
                "",
                "",
                "",
                "",
                "JTextField.placeholderText=Search;background:@background",
                "",
                "background:lighten(@background,8%);borderWidth:1"
            }
        ));
        crazyPanel2.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "",
            "[][][][][][]push[]",
            "",
            new String[]{
                "",
                "width 220",
                "",
                "width 220",
                "width 200",
                "",
                ""
            }
        ));
        crazyPanel2.setPreferredSize(new java.awt.Dimension(444, 46));

        jLabel2.setText("Docente:");
        crazyPanel2.add(jLabel2);

        Docentes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DocentesItemStateChanged(evt);
            }
        });
        Docentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DocentesActionPerformed(evt);
            }
        });
        crazyPanel2.add(Docentes);

        jLabel3.setText("Alumno:");
        crazyPanel2.add(jLabel3);

        Alumnos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                AlumnosItemStateChanged(evt);
            }
        });
        crazyPanel2.add(Alumnos);

        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });
        crazyPanel2.add(txtSearch);

        Cargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/cargando.gif"))); // NOI18N
        crazyPanel2.add(Cargando);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/nota-btn.png"))); // NOI18N
        jButton1.setText("Mostrar notas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        crazyPanel2.add(jButton1);

        crazyPanel1.add(crazyPanel2);

        PrestamosTable.setAutoCreateRowSorter(true);
        PrestamosTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Matricula/Plaza", "Nombre", "Mac", "Ipad", "Fecha", "Status", "Acciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PrestamosTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        PrestamosTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                PrestamosTableFocusLost(evt);
            }
        });
        PrestamosTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PrestamosTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PrestamosTableMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(PrestamosTable);
        if (PrestamosTable.getColumnModel().getColumnCount() > 0) {
            PrestamosTable.getColumnModel().getColumn(0).setPreferredWidth(10);
            PrestamosTable.getColumnModel().getColumn(2).setPreferredWidth(200);
            PrestamosTable.getColumnModel().getColumn(3).setPreferredWidth(10);
            PrestamosTable.getColumnModel().getColumn(4).setPreferredWidth(10);
            PrestamosTable.getColumnModel().getColumn(6).setPreferredWidth(10);
            PrestamosTable.getColumnModel().getColumn(7).setPreferredWidth(50);
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

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        if (evt.getKeyCode() == 10) {
            if (!txtSearch.getText().isEmpty()) {
                Cargando.setVisible(true);
                try {
                    int number = Integer.parseInt(txtSearch.getText());
                    Docente dct = (Docente) buscarPersonaId(docentes, null, String.valueOf(number), 1);
                    if (dct != null) {
                        if (!Repetido(String.valueOf(number))) {
                            FormManager.CambiarTexto("  Registrar Prestamo");
                            FormManager.showForm(new Prestamos_New_or_Update(app, this, null, dct));
                        }
                    } else {
                        JOptionPane.showMessageDialog(app, "No se encontro registro alguno", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    String matricula = txtSearch.getText().toUpperCase();
                    Alumno alm = (Alumno) buscarPersonaId(null, alumnos, matricula, 2);
                    if (alm != null) {
                        if (!Repetido(matricula)) {
                            FormManager.CambiarTexto("  Registrar Prestamo");
                            FormManager.showForm(new Prestamos_New_or_Update(app, this, alm, null));
                        }
                    } else {
                        JOptionPane.showMessageDialog(app, "No se encontraron registros de alumnos", "Aviso", JOptionPane.WARNING_MESSAGE);
                    }
                }
                Cargando.setVisible(false);
            }
            txtSearch.setText("");
        }
    }//GEN-LAST:event_txtSearchKeyReleased

    private void PrestamosTableFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_PrestamosTableFocusLost

    }//GEN-LAST:event_PrestamosTableFocusLost

    private void PrestamosTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrestamosTableMousePressed

    }//GEN-LAST:event_PrestamosTableMousePressed

    private void DocentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DocentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DocentesActionPerformed

    private void PrestamosTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PrestamosTableMouseClicked

    }//GEN-LAST:event_PrestamosTableMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        NotasModalView nv = new NotasModalView(app, true);
        nv.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void AlumnosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_AlumnosItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            for (Alumno alumno : alumnos) {
                if ((alumno.getNombre() + " " + alumno.getApellido_p() + " " + alumno.getApellido_m()).equals(Alumnos.getSelectedItem())) {
                    if (!Repetido(alumno.getMatricula())) {
                        FormManager.CambiarTexto("  Registrar Prestamo");
                        FormManager.showForm(new Prestamos_New_or_Update(app, this, alumno, null));
                    }
                }
            }
            Alumnos.setSelectedIndex(0);
        }
    }//GEN-LAST:event_AlumnosItemStateChanged

    private void DocentesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DocentesItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            for (Docente docente : docentes) {
                if ((docente.getNombre() + " " + docente.getApellido_p() + " " + docente.getApellido_m()).equals(Docentes.getSelectedItem())) {
                    if (!Repetido(docente.getNumero_plaza())) {
                        FormManager.CambiarTexto("  Registrar Prestamo");
                        FormManager.showForm(new Prestamos_New_or_Update(app, this, null, docente));
                    }
                }
            }
            Docentes.setSelectedIndex(0);
        }
    }//GEN-LAST:event_DocentesItemStateChanged

    private Persona buscarPersonaId(ArrayList<Docente> docentes, ArrayList<Alumno> alumnos, String id, int opcion) {
        for (Persona persona : opcion == 1 ? docentes : alumnos) {
            if (opcion == 1 && persona instanceof Docente) {
                Docente docente = (Docente) persona;
                if (docente.getNumero_plaza().equals(id)) {
                    return docente;
                }
            } else if (opcion == 2 && persona instanceof Alumno) {
                Alumno alumno = (Alumno) persona;
                if (alumno.getMatricula().equals(id)) {
                    return alumno;
                }
            }
        }
        return null;
    }

    private boolean Repetido(String id) {
        for (int i = 0; i < PrestamosTable.getRowCount(); i++) {
            String nombretable = (PrestamosTable.getValueAt(i, 1) != null) ? PrestamosTable.getValueAt(i, 1).toString() : "";
            boolean Status = PrestamosTable.getValueAt(i, 6).toString().equals("En uso");
            if (nombretable.equals(id) && Status) {
                JOptionPane.showMessageDialog(app, "El nombre seleccionado ya tiene un registro", "Aviso", JOptionPane.WARNING_MESSAGE);
                return true;
            }
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> Alumnos;
    private javax.swing.JLabel Cargando;
    private javax.swing.JComboBox<String> Docentes;
    private javax.swing.JTable PrestamosTable;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private raven.crazypanel.CrazyPanel crazyPanel2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    @Override
    public void onEdit(int row) {
        Prestamos pr = null;
        for (Prestamos prestamo : prestamos) {
            if (prestamo.getIdentificador() == Integer.parseInt(PrestamosTable.getValueAt(row, 0).toString())) {
                pr = prestamo;
            }
        }
        if (pr != null) {
            FormManager.CambiarTexto("  Editar Prestamo");
            FormManager.showForm(new Prestamos_New_or_Update(app, this, pr));
        }
    }

    @Override
    public void onComment(int row) {
        String id = PrestamosTable.getValueAt(row, 0).toString();
        NotaAdd_Update nt = new NotaAdd_Update(app, true, id, this);
        nt.setVisible(true);
    }
}
