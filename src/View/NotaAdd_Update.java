package View;

import Controller.NotasController;
import Model.Database.Notas;
import javax.swing.JOptionPane;

/**
 *
 * @author Hitler
 */
public class NotaAdd_Update extends javax.swing.JDialog {

    String identificador;
    String dispo;
    NotasController EC = new NotasController();
    NotasModalView ntv;
    Notas_Globales ng;
    boolean status;
    EquiposView ev;
    PrestamosView pv;

    //Constructor para registro de prestaciones
    public NotaAdd_Update(java.awt.Frame parent, boolean modal, String id, PrestamosView ev) {
        super(parent, modal);
        initComponents();
        jLabel2.setText("Registro:");
        this.setLocationRelativeTo(this);
        this.pv = ev;
        this.id.setText(id);
        this.identificador = id;
    }

    //Constructor para equipos
    public NotaAdd_Update(java.awt.Frame parent, boolean modal, String id, String dispo, EquiposView ev) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.ev = ev;
        this.id.setText(id);
        this.dispo = dispo;
        this.identificador = id;
    }

    //Constructor para Notas Globales
    public NotaAdd_Update(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.ng = (Notas_Globales) parent;
        status = false;
        this.setLocationRelativeTo(this);
        this.jLabel2.setText("Global");
    }

    //Construtor para editar Nota
    public NotaAdd_Update(java.awt.Dialog parent, boolean modal, String referencia, String id, String nota) {
        super(parent, modal);
        initComponents();
        try {
            this.ng = (Notas_Globales) parent;
        } catch (Exception e) {
            this.ntv = (NotasModalView) parent;
        }
        this.jLabel1.setText("Editar Nota");
        if (id.contains("M") || id.contains("I")) {
            jLabel2.setText("Equipo:");
        } else if (id.contains("N/E")) {
            jLabel2.setText("Global:");
            id = referencia;
            status = true;
        } else {
            jLabel2.setText("Prestacion:");
        }
        this.setLocationRelativeTo(this);
        this.id.setText(id);
        this.texto.setText(nota);
        this.identificador = referencia;
    }

    //Insertar notas equipo
    private void InsertarNotaEQ(String id, String dispo) {
        Notas nt = new Notas();
        nt.setEquipo(dispo);
        nt.setTipo((id.contains("M")) ? "M" : "I");
        nt.setDescripcion(texto.getText());
        if (EC.AddNotaEQ(nt)) {
            JOptionPane.showMessageDialog(this, "Nota guardada", "Exito", JOptionPane.INFORMATION_MESSAGE);
            ev.CargarEquipos();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Insertar notas registro de prestaciones
    private void InsertarNotaPR(Integer id) {
        Notas nt = new Notas();
        nt.setPrestacion(id);
        nt.setTipo("P");
        nt.setDescripcion(texto.getText());
        if (EC.AddNotaPR(nt)) {
            JOptionPane.showMessageDialog(this, "Nota guardada", "Exito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Insertar nota global
    private void InsertarNotaGB() {
        Notas nt = new Notas();
        nt.setTipo("G");
        nt.setDescripcion(texto.getText());
        if (EC.AddNotaGB(nt)) {
            JOptionPane.showMessageDialog(this, "Nota guardada", "Exito", JOptionPane.INFORMATION_MESSAGE);
            ng.CargarNotasGB();
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Editar notas
    private void EditarNota(Integer id) {
        System.out.println(id);
        Notas nt = new Notas();
        nt.setNumero(id);
        nt.setDescripcion(texto.getText());
        if (EC.UpdateNotas(nt)) {
            JOptionPane.showMessageDialog(this, "Nota actualizada", "Exito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo actualizar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        id = new javax.swing.JLabel();
        Guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(450, 350));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Agregar Nota");

        texto.setColumns(20);
        texto.setLineWrap(true);
        texto.setRows(5);
        texto.setWrapStyleWord(true);
        texto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(texto);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Equipo:");

        id.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/notas.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.setEnabled(false);
        Guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Guardar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        Guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Guardar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoKeyReleased
        if (!texto.getText().isEmpty()) {
            Guardar.setEnabled(true);
        } else {
            Guardar.setEnabled(false);
        }
    }//GEN-LAST:event_textoKeyReleased

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        if (ev != null) {
            InsertarNotaEQ(identificador, dispo);
        }
        if (pv != null) {
            InsertarNotaPR(Integer.valueOf(identificador));
        }
        if (ntv != null || (ng != null && jLabel1.getText().equals("Editar Nota"))) {
            EditarNota(Integer.valueOf(identificador));
        }
        if (ng != null && jLabel1.getText().equals("Agregar Nota")) {
            InsertarNotaGB();
        }
    }//GEN-LAST:event_GuardarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea texto;
    // End of variables declaration//GEN-END:variables
}
