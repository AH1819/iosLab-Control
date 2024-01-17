package View;

import Controller.NotasController;
import Model.Database.Notas;
import Model.Views.TableActionCellEditorEQ;
import Model.Views.TableActionCellRenderEQ;
import Model.Views.TableActionEvent;
import Model.Views.ThemesChange;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hitler
 */
public class Notas_Globales extends javax.swing.JDialog implements TableActionEvent {

    DefaultTableModel modelo;
    NotasController ec = new NotasController();
    ArrayList<Notas> nt;

    public Notas_Globales(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        modelo = (DefaultTableModel) NotasTable.getModel();
        init(NotasTable, 3, 3);
        CargarNotasGB();
    }

    private void init(JTable table, int columna, int numcell) {
        cmdNotas.setIcon(new FlatSVGIcon("Recursos/Icons/notesAdd.svg", 0.45f));
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

    public void CargarNotasGB() {
        nt = ec.GetAllNotasGlobal();
        if (!nt.isEmpty()) {
            modelo.setRowCount(0);
            for (Notas nota : nt) {
                Object[] fila = {
                    nota.getNumero(),
                    nota.getFecha(),
                    nota.getDescripcion()
                };
                modelo.addRow(fila);
            }
            NotasTable.setModel(modelo);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        cmdNotas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        NotasTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap,fill",
            "[fill]",
            "[fill]",
            new String[]{
                "split 2",
                "",
                ""
            }
        ));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Notas Globales");
        crazyPanel1.add(jLabel1);

        cmdNotas.setText("Agregar");
        cmdNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdNotasActionPerformed(evt);
            }
        });
        crazyPanel1.add(cmdNotas);

        NotasTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "N°", "Fecha", "Descripcion", "Acciones"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(NotasTable);

        crazyPanel1.add(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmdNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdNotasActionPerformed
        NotaAdd_Update nau = new NotaAdd_Update(this, true);
        nau.setVisible(true);
    }//GEN-LAST:event_cmdNotasActionPerformed

    @Override
    public void onEdit(int row) {
        NotaAdd_Update nau = new NotaAdd_Update(this, true, NotasTable.getValueAt(row, 0).toString(), "N/E", NotasTable.getValueAt(row, 2).toString());
        nau.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                CargarNotasGB();
            }
        });
        nau.setVisible(true);
    }

    @Override
    public void onDelete(int row) {
        String[] arreglo = {"Si", "No"};
        int opcionp = JOptionPane.showOptionDialog(this, "¿Esta seguro de eliminar este registro? \nSe borraran todos los datos relacionados", "Eliminar", 0, JOptionPane.ERROR_MESSAGE, null, arreglo, "No");
        if (arreglo[opcionp].equals("Si")) {
            int id_rg = Integer.parseInt(NotasTable.getValueAt(row, 0).toString());
            ec.DeleteNotas(id_rg);
            CargarNotasGB();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable NotasTable;
    private javax.swing.JButton cmdNotas;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
