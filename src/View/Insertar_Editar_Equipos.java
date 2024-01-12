package View;

import Controller.EquiposController;
import Model.Database.Datos_Cargados;
import Model.Database.Ipads;
import Model.Database.Macs;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Hitler
 */
public class Insertar_Editar_Equipos extends javax.swing.JDialog {

    boolean macEQ = false;
    boolean insertar = false;
    Macs mac = null;
    Ipads ipad = null;
    EquiposView eqv;

    //Actualizar
    public Insertar_Editar_Equipos(java.awt.Frame parent, boolean modal, String title, Macs mac, Ipads ipad, EquiposView eqv) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.titulo.setText(title);
        this.insertar = false;
        this.eqv = eqv;
        this.Cargando.setVisible(false);
        macEQ = title.contains("Mac");
        this.id.setToolTipText(macEQ ? "M seguido de un numero" : "I seguido de un numero");
        if (mac != null) {
            this.mac = mac;
            this.id.setText(mac.getNumero());
            this.cargador.setSelected(mac.getCargador().equals("si"));
            this.N_serie.setText(mac.getNumero_serie());
            this.pencil.setVisible(false);
            ValidarCampos();
        }

        if (ipad != null) {
            System.out.println(ipad.getApplepencil());
            this.ipad = ipad;
            this.id.setText(ipad.getNumero());
            this.cargador.setSelected(ipad.getCargador().equals("si"));
            this.pencil.setSelected(ipad.getApplepencil() != null && ipad.getApplepencil().equals("si"));
            this.N_serie.setText(ipad.getNumero_serie());
            this.pencil.setVisible(true);
            ValidarCampos();
        }
    }

    //Insertar
    public Insertar_Editar_Equipos(java.awt.Frame parent, boolean modal, String title, EquiposView eqv) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        this.titulo.setText(title);
        this.insertar = true;
        this.eqv = eqv;
        this.Cargando.setVisible(false);
        macEQ = title.contains("Mac");
        this.id.setToolTipText(macEQ ? "M seguido de un numero" : "I seguido de un numero");
        this.pencil.setVisible(!macEQ);
    }

    public void insertarEquipo() {
        if (macEQ) {
            if (validarCadena(id.getText().toUpperCase(), "M")) {
                if (validarMac(id.getText().toUpperCase(), N_serie.getText().toUpperCase())) {
                    id.setForeground(Color.white);
                    Macs mc = new Macs();
                    mc.setNumero(id.getText().toUpperCase());
                    mc.setNumero_serie(N_serie.getText());
                    mc.setCargador(cargador.isSelected() ? "si" : "no");
                    EquiposController ec = new EquiposController();
                    if (ec.InsertMac(mc)) {
                        JOptionPane.showMessageDialog(this, "Se inserto la Mac: " + mc.getNumero(), "Exito", JOptionPane.INFORMATION_MESSAGE);
                        eqv.CargarEquipos();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Hubo un error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                id.requestFocus();
                id.setForeground(Color.red);
            }
        } else {
            if (validarCadena(id.getText().toUpperCase(), "I")) {
                if (validarIpad(id.getText().toUpperCase(), N_serie.getText().toUpperCase())) {
                    id.setForeground(Color.white);
                    Ipads ip = new Ipads();
                    ip.setNumero(id.getText().toUpperCase());
                    ip.setNumero_serie(N_serie.getText());
                    ip.setCargador(cargador.isSelected() ? "si" : "no");
                    ip.setApplepencil(pencil.isSelected() ? "si" : "no");
                    EquiposController ec = new EquiposController();
                    if (ec.InsertIpad(ip)) {
                        JOptionPane.showMessageDialog(this, "Se inserto el Ipad: " + ip.getNumero(), "Exito", JOptionPane.INFORMATION_MESSAGE);
                        eqv.CargarEquipos();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Hubo un error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                id.requestFocus();
                id.setForeground(Color.red);
            }
        }
    }

    public void actualizarEquipo() {
        if (macEQ) {
            if (validarCadena(id.getText().toUpperCase(), "M")) {
                if (validarMac(id.getText().toUpperCase(), N_serie.getText().toUpperCase())) {
                    id.setForeground(Color.white);
                    Macs mc = new Macs();
                    mc.setNumero_anterior(mac.getNumero());
                    mc.setNumero(id.getText().toUpperCase());
                    mc.setNumero_serie(N_serie.getText());
                    mc.setCargador(cargador.isSelected() ? "si" : "no");
                    EquiposController ec = new EquiposController();
                    if (ec.UpdateMac(mc)) {
                        JOptionPane.showMessageDialog(this, "Se actualizo la Mac: " + mc.getNumero(), "Exito", JOptionPane.INFORMATION_MESSAGE);
                        eqv.CargarEquipos();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Hubo un error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                id.requestFocus();
                id.setForeground(Color.red);
            }
        } else {
            if (validarCadena(id.getText().toUpperCase(), "I")) {
                if (validarIpad(id.getText().toUpperCase(), N_serie.getText().toUpperCase())) {
                    id.setForeground(Color.white);
                    Ipads ip = new Ipads();
                    ip.setNumero_anterior(ipad.getNumero());
                    ip.setNumero(id.getText().toUpperCase());
                    ip.setNumero_serie(N_serie.getText());
                    ip.setCargador(cargador.isSelected() ? "si" : "no");
                    ip.setApplepencil(pencil.isSelected() ? "si" : "no");
                    EquiposController ec = new EquiposController();
                    if (ec.UpdateIpad(ip)) {
                        JOptionPane.showMessageDialog(this, "Se actualizo el Ipad: " + ip.getNumero(), "Exito", JOptionPane.INFORMATION_MESSAGE);
                        eqv.CargarEquipos();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "Hubo un error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                id.requestFocus();
                id.setForeground(Color.red);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titulo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        cargador = new javax.swing.JCheckBox();
        Guardar = new javax.swing.JButton();
        pencil = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        N_serie = new javax.swing.JTextField();
        Cargando = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(253, 264));
        setResizable(false);

        titulo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Title");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Identificador:");

        id.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        id.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                idPropertyChange(evt);
            }
        });
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idKeyReleased(evt);
            }
        });

        cargador.setText("Cargador");
        cargador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargadorActionPerformed(evt);
            }
        });
        cargador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cargadorKeyReleased(evt);
            }
        });

        Guardar.setText("Guardar");
        Guardar.setEnabled(false);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        pencil.setText("apple pencil");
        pencil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pencilActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Numero de serie:");

        N_serie.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        N_serie.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                N_seriePropertyChange(evt);
            }
        });
        N_serie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                N_serieKeyReleased(evt);
            }
        });

        Cargando.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Cargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/cargando.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(id)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(N_serie, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cargador, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pencil, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                            .addComponent(Cargando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(N_serie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cargador)
                    .addComponent(pencil))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Guardar)
                    .addComponent(Cargando))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyReleased
        ValidarCampos();
    }//GEN-LAST:event_idKeyReleased

    private void N_serieKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_N_serieKeyReleased
        ValidarCampos();
    }//GEN-LAST:event_N_serieKeyReleased

    private void cargadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cargadorKeyReleased

    }//GEN-LAST:event_cargadorKeyReleased

    private void cargadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargadorActionPerformed

    }//GEN-LAST:event_cargadorActionPerformed

    private void pencilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pencilActionPerformed

    }//GEN-LAST:event_pencilActionPerformed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        this.Cargando.setVisible(true);
        if (insertar) {
            //Insertar equipo
            insertarEquipo();
        } else {
            //Actualizar equipo
            actualizarEquipo();
        }
        this.Cargando.setVisible(false);
    }//GEN-LAST:event_GuardarActionPerformed

    private void idPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_idPropertyChange

    }//GEN-LAST:event_idPropertyChange

    private void N_seriePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_N_seriePropertyChange

    }//GEN-LAST:event_N_seriePropertyChange

    private void ValidarCampos() {
        Guardar.setEnabled(
                !id.getText().isEmpty()
                && !N_serie.getText().isEmpty()
        );
    }

    private void mostrarMensaje(String mensaje, JTextField textField) {
        textField.requestFocus();
        textField.setForeground(Color.red);
        JOptionPane.showMessageDialog(this, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }

    private boolean validarMac(String id, String serie) {
        if (Datos_Cargados.getMacs_guardadas().isEmpty()) {
            return true;
        }
        for (Macs macg : Datos_Cargados.getMacs_guardadas()) {
            if (mac != null) {
                //Actualizar
                if (!id.equals(mac.getNumero()) && id.equals(macg.getNumero())) {
                    mostrarMensaje("Numero de equipo repetido", this.id);
                    return false;
                }
                this.id.setForeground(Color.white);
                if (macg.getNumero_serie() != null) {
                    if (!macg.getNumero().equals(mac.getNumero()) && !serie.equals(mac.getNumero_serie()) && serie.equals(macg.getNumero_serie().toUpperCase())) {
                        mostrarMensaje("Numero de serie repetido", this.N_serie);
                        return false;
                    }
                }
                this.N_serie.setForeground(Color.white);
            } else {
                //Insertar
                if (id.equals(macg.getNumero())) {
                    mostrarMensaje("Numero de equipo repetido", this.id);
                    return false;
                }
                this.id.setForeground(Color.white);
                if (macg.getNumero_serie() != null) {
                    if (serie.equals(macg.getNumero_serie().toUpperCase())) {
                        mostrarMensaje("Numero de serie repetido", this.N_serie);
                        return false;
                    }
                }
                this.N_serie.setForeground(Color.white);
            }
        }
        return true;
    }

    private boolean validarIpad(String id, String serie) {
        if (Datos_Cargados.getIpads_guardadas().isEmpty()) {
            return true;
        }
        for (Ipads ipadg : Datos_Cargados.getIpads_guardadas()) {
            if (ipad != null) {
                //Actualizar
                if (!id.equals(ipad.getNumero()) && id.equals(ipadg.getNumero())) {
                    mostrarMensaje("Numero de equipo repetido", this.id);
                    return false;
                }
                this.id.setForeground(Color.white);
                if (ipadg.getNumero_serie() != null) {
                    if (!ipadg.getNumero().equals(ipad.getNumero()) && !serie.equals(ipad.getNumero_serie()) && serie.equals(ipadg.getNumero_serie().toUpperCase())) {
                        mostrarMensaje("Numero de serie repetido", this.N_serie);
                        return false;
                    }
                }
                this.N_serie.setForeground(Color.white);
            } else {
                //Insertar
                if (id.equals(ipadg.getNumero())) {
                    mostrarMensaje("Numero de equipo repetido", this.id);
                    return false;
                }
                this.id.setForeground(Color.white);
                if (ipadg.getNumero_serie() != null) {
                    if (serie.equals(ipadg.getNumero_serie().toUpperCase())) {
                        mostrarMensaje("Numero de serie repetido", this.N_serie);
                        return false;
                    }
                }
                this.N_serie.setForeground(Color.white);
            }
        }
        return true;
    }

    private static boolean validarCadena(String input, String letra) {
        // Utiliza la expresión regular para asegurarte de que solo haya una "M" o "I" en la cadena
        return input.matches("" + letra + "\\d*");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cargando;
    private javax.swing.JButton Guardar;
    private javax.swing.JTextField N_serie;
    private javax.swing.JCheckBox cargador;
    private javax.swing.JTextField id;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JCheckBox pencil;
    private javax.swing.JLabel titulo;
    // End of variables declaration//GEN-END:variables
}
