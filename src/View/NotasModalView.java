package View;

import Controller.NotasController;
import Model.Database.Notas;
import Model.Views.TableActionCellEditorEQ;
import Model.Views.TableActionCellRenderEQ;
import Model.Views.TableActionEvent;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
public class NotasModalView extends javax.swing.JDialog implements TableActionEvent {

    String notaTipo;
    DefaultTableModel modelo;
    NotasController ec = new NotasController();
    ArrayList<Notas> nt;
    EquiposView eqv;
    String id;

    //Notas de Prestamos
    public NotasModalView(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        modelo = (DefaultTableModel) Table.getModel();
        this.notaTipo = "P";
        init(Table, 4, 4);
        new Thread() {
            @Override
            public void run() {
                CargarNotasPR();
            }
        }.start();
    }

    //Notas de Equipos
    public NotasModalView(java.awt.Frame parent, boolean modal, String tipo, String id, EquiposView eqv) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        modelo = (DefaultTableModel) Table.getModel();
        this.notaTipo = tipo;
        this.eqv = eqv;
        this.id = id;
        new Thread() {
            @Override
            public void run() {
                CargarNotasEQ(tipo, id);
            }
        }.start();
        init(Table, 4, 4);
    }

    private void init(JTable table, int columna, int numcell) {
        TableActionCellEditorEQ eventEditor = new TableActionCellEditorEQ(this);
        table.getColumnModel().getColumn(columna).setCellRenderer(new TableActionCellRenderEQ());
        table.getColumnModel().getColumn(columna).setCellEditor(eventEditor);

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
        jLabel1 = new javax.swing.JLabel();
        Cargando = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(400, 300));

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill",
            "[fill]",
            "[fill]",
            new String[]{
                "split 2,span 2,grow",
                "",
                ""
            }
        ));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Notas");
        crazyPanel1.add(jLabel1);

        Cargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/cargando.gif"))); // NOI18N
        crazyPanel1.add(Cargando);

        Table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Fecha", "Equipo", "Descripcion", "Acciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Table.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        Table.setRowHeight(45);
        jScrollPane1.setViewportView(Table);

        crazyPanel1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void CargarNotasEQ(String tipo, String id) {
        nt = ec.GetAllNotasEQ(tipo, id);
        if (!nt.isEmpty()) {
            modelo.setRowCount(0);
            for (Notas nota : nt) {
                Object[] fila = {
                    nota.getNumero(),
                    nota.getFecha(),
                    nota.getEquipo(),
                    nota.getDescripcion()
                };
                modelo.addRow(fila);
            }
            Table.setModel(modelo);
        }
        Cargando.setVisible(false);
    }

    public void CargarNotasPR() {
        nt = ec.GetAllNotasPR();
        if (!nt.isEmpty()) {
            modelo.setRowCount(0);
            for (Notas nota : nt) {
                Object[] fila = {
                    nota.getNumero(),
                    nota.getFecha(),
                    nota.getEquipo(),
                    nota.getDescripcion()
                };
                modelo.addRow(fila);
            }
            Table.setModel(modelo);
        }
        Table.getColumnModel().getColumn(2).setHeaderValue("Prestacion");
        Cargando.setVisible(false);
    }

    @Override
    public void onEdit(int row) {
        NotaAdd_Update nau = new NotaAdd_Update(this, true, Table.getValueAt(row, 0).toString(), Table.getValueAt(row, 2).toString(), Table.getValueAt(row, 3).toString());
        nau.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        if (notaTipo.contains("M") || notaTipo.contains("I")) {
                            CargarNotasEQ(notaTipo, id);
                        } else if (notaTipo.contains("P")) {
                            CargarNotasPR();
                        }
                    }
                }.start();
            }
        });
        nau.setVisible(true);
    }

    @Override
    public void onDelete(int row) {
        String[] arreglo = {"Si", "No"};
        int opcionp = JOptionPane.showOptionDialog(this, "¿Esta seguro de eliminar este registro? \nSe borraran todos los datos relacionados", "Eliminar", 0, JOptionPane.ERROR_MESSAGE, null, arreglo, "No");
        if (arreglo[opcionp].equals("Si")) {
            Cargando.setVisible(true);
            int id_rg = Integer.parseInt(Table.getValueAt(row, 0).toString());
            new Thread() {
                @Override
                public void run() {
                    ec.DeleteNotas(id_rg);
                    if (notaTipo.contains("P")) {
                        CargarNotasPR();
                    } else {
                        CargarNotasEQ(notaTipo, id);
                        eqv.CargarEquipos();
                    }
                }
            }.start();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cargando;
    private javax.swing.JTable Table;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
