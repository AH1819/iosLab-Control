package View;

import Controller.EquiposController;
import Controller.PrestamosController;
import Model.Database.Alumno;
import Model.Database.Docente;
import Model.Database.Ipads;
import Model.Database.Macs;
import Model.Database.Prestamos;
import Model.Views.FormManager;
import Model.Views.SimpleForm;
import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

/**
 *
 * @author Hitler
 */
public class Prestamos_New_or_Update extends SimpleForm {

    Application app;
    PrestamosView pv;
    Alumno alm;
    Docente dct;
    Prestamos pr;
    String id_usuario = "";

    public Prestamos_New_or_Update(Application app, PrestamosView pv, Alumno alm, Docente dct) {
        initComponents();
        this.app = app;
        this.pv = pv;
        this.alm = alm;
        this.dct = dct;
        CargarEquipos();
        CargarDatos();
    }

    public Prestamos_New_or_Update(Application app, PrestamosView pv, Prestamos pr) {
        initComponents();
        this.app = app;
        this.pv = pv;
        this.pr = pr;
        CargarEquipos();
        CargarDatos();
    }

    private void CargarHoras(JFormattedTextField campo, java.sql.Time hora) {
        String horaString = "";
        if (hora != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime horaLocalTime = hora.toLocalTime();
            horaString = formatter.format(horaLocalTime);
        }
        campo.setText(horaString);
    }

    private void CargarDatos() {
        if (pr != null) {
            Nombre.setText(pr.getNombre());
            Identificador.setText(pr.getId_usuario());
            if (!pr.getId_mac().equals("N/E")) {
                Macs.addItem(pr.getId_mac());
                Macs.setSelectedItem(pr.getId_mac());
            }
            if (!pr.getId_ipad().equals("N/E")) {
                Ipads.addItem(pr.getId_ipad());
                Ipads.setSelectedItem(pr.getId_ipad());
            }
            Fecha.setText(pr.getFecha().toString());
            C_Mac.setSelected(pr.isC_mac());
            C_Ipad.setSelected(pr.isC_ipad());
            Credencial.setSelected(pr.isCredencial());
            CargarHoras(Entrada, pr.getHora_entrada());
            CargarHoras(Salida, pr.getHora_salida());
            ValidarCampos();
        } else {
            if (alm != null) {
                id_usuario = String.valueOf(alm.getId_persona());
                Nombre.setText(alm.getNombre() + " " + alm.getApellido_p() + " " + alm.getApellido_m());
                Identificador.setText(alm.getMatricula());
            }
            if (dct != null) {
                id_usuario = String.valueOf(dct.getId_persona());
                Nombre.setText(dct.getNombre() + " " + dct.getApellido_p() + " " + dct.getApellido_m());
                Identificador.setText(dct.getNumero_plaza());
            }
        }
    }

    private void CargarEquipos() {
        ArrayList<Ipads> ipds = new EquiposController().CargarIpadsAvailable();
        ArrayList<Macs> mcs = new EquiposController().CargarMacsAvailable();
        if (!mcs.isEmpty() && mcs.get(0).getNumero() != null) {
            mcs.forEach((mc) -> {
                Macs.addItem(mc.getNumero());
            });
        } else {
            Macs.setEnabled(false);
        }
        if (!ipds.isEmpty() && ipds.get(0).getNumero() != null) {
            ipds.forEach((ipd) -> {
                Ipads.addItem(ipd.getNumero());
            });
        } else {
            Ipads.setEnabled(false);
        }
    }

    private void ActualizarPrestamo() {
        System.out.println("Actualizar");
        pr.setId_mac(Macs.getSelectedIndex() > 0 ? Macs.getSelectedItem().toString() : null);
        pr.setId_ipad(Ipads.getSelectedIndex() > 0 ? Ipads.getSelectedItem().toString() : null);
        pr.setFecha(convertirStringADate(Fecha.getText()));
        pr.setC_mac(C_Mac.isSelected());
        pr.setC_ipad(C_Ipad.isSelected());
        pr.setCredencial(Credencial.isSelected());
        pr.setHora_entrada(convertirStringATime(Entrada.getText()));
        pr.setHora_salida(convertirStringATime(Salida.getText()));

        PrestamosController pc = new PrestamosController();
        if (pc.ActualizarPrestamo(pr)) {
            JOptionPane.showMessageDialog(app, "Se actualizo el prestamo", "Exito", JOptionPane.INFORMATION_MESSAGE);
            pv.CargarPrestamos();
            FormManager.CambiarTexto("  Prestamos");
            FormManager.showForm(pv);
        } else {
            JOptionPane.showMessageDialog(app, "Hubo un error en el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void InsertarPrestamo() {
        System.out.println("Insertar");
        Prestamos presta = new Prestamos();
        presta.setId_usuario(id_usuario);
        presta.setId_mac(Macs.getSelectedIndex() > 0 ? Macs.getSelectedItem().toString() : null);
        presta.setId_ipad(Ipads.getSelectedIndex() > 0 ? Ipads.getSelectedItem().toString() : null);
        presta.setFecha(convertirStringADate(Fecha.getText()));
        presta.setC_mac(C_Mac.isSelected());
        presta.setC_ipad(C_Ipad.isSelected());
        presta.setCredencial(Credencial.isSelected());
        presta.setHora_entrada(convertirStringATime(Entrada.getText()));
        presta.setHora_salida(convertirStringATime(Salida.getText()));

        PrestamosController pc = new PrestamosController();
        if (pc.InsertPrestamo(presta)) {
            JOptionPane.showMessageDialog(app, "Se registro el prestamo", "Exito", JOptionPane.INFORMATION_MESSAGE);
            pv.CargarPrestamos();
            FormManager.CambiarTexto("  Prestamos");
            FormManager.showForm(pv);
        } else {
            JOptionPane.showMessageDialog(app, "Hubo un error en el proceso", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Guardar = new javax.swing.JButton();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        label = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        Identificador = new javax.swing.JTextField();
        Macs = new javax.swing.JComboBox<>();
        Ipads = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Fecha = new javax.swing.JFormattedTextField();
        C_Mac = new javax.swing.JCheckBox();
        C_Ipad = new javax.swing.JCheckBox();
        Credencial = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Entrada = new javax.swing.JFormattedTextField();
        Salida = new javax.swing.JFormattedTextField();

        Guardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/guardar.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.setEnabled(false);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            new String[]{
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "JTextField.placeholderText=yyyy-mm-dd",
                "",
                "",
                "",
                "",
                "",
                "showClearButton:true;JTextField.placeholderText=hh:mm",
                "showClearButton:true;JTextField.placeholderText=hh:mm"
            }
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap 4,fillx,insets 30",
            "[fill]25[fill]",
            "[grow 0]20[fill]",
            new String[]{
                "",
                "",
                "",
                "",
                "height 30",
                "height 30",
                "height 30",
                "height 30",
                "",
                "",
                "wrap",
                "height 30",
                "height 30,split 2",
                "height 30",
                "height 30,wrap",
                "",
                "wrap",
                "height 30",
                "height 30"
            }
        ));

        label.setText("Nombre:");
        crazyPanel1.add(label);

        jLabel2.setText("Identificador:");
        crazyPanel1.add(jLabel2);

        jLabel3.setText("Mac:");
        crazyPanel1.add(jLabel3);

        jLabel4.setText("Ipad:");
        crazyPanel1.add(jLabel4);

        Nombre.setEditable(false);
        crazyPanel1.add(Nombre);

        Identificador.setEditable(false);
        crazyPanel1.add(Identificador);

        Macs.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        Macs.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                MacsItemStateChanged(evt);
            }
        });
        crazyPanel1.add(Macs);

        Ipads.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona una opcion" }));
        Ipads.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                IpadsItemStateChanged(evt);
            }
        });
        crazyPanel1.add(Ipads);

        jLabel5.setText("Fecha:");
        crazyPanel1.add(jLabel5);

        jLabel6.setText("Cargadores:");
        crazyPanel1.add(jLabel6);

        jLabel7.setText("Credencial:");
        crazyPanel1.add(jLabel7);

        Fecha.setEditable(false);
        Fecha.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));
        Fecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                FechaFocusGained(evt);
            }
        });
        Fecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FechaMousePressed(evt);
            }
        });
        Fecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                FechaPropertyChange(evt);
            }
        });
        crazyPanel1.add(Fecha);

        C_Mac.setText("Mac");
        C_Mac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_MacActionPerformed(evt);
            }
        });
        crazyPanel1.add(C_Mac);

        C_Ipad.setText("Ipad");
        C_Ipad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                C_IpadActionPerformed(evt);
            }
        });
        crazyPanel1.add(C_Ipad);

        Credencial.setText("No");
        Credencial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CredencialActionPerformed(evt);
            }
        });
        crazyPanel1.add(Credencial);

        jLabel8.setText("Hora de entrada:");
        crazyPanel1.add(jLabel8);

        jLabel9.setText("Hora de salida:");
        crazyPanel1.add(jLabel9);

        Entrada.setEditable(false);
        Entrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("hh:mm"))));
        Entrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Entrada.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                EntradaMousePressed(evt);
            }
        });
        Entrada.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                EntradaPropertyChange(evt);
            }
        });
        crazyPanel1.add(Entrada);

        Salida.setEditable(false);
        Salida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("hh:mm"))));
        Salida.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        Salida.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SalidaMousePressed(evt);
            }
        });
        Salida.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                SalidaPropertyChange(evt);
            }
        });
        crazyPanel1.add(Salida);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(Guardar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Guardar)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CredencialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CredencialActionPerformed
        Credencial.setText(Credencial.isSelected() ? "Si" : "No");
        ValidarCampos();
    }//GEN-LAST:event_CredencialActionPerformed

    private void FechaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_FechaFocusGained

    }//GEN-LAST:event_FechaFocusGained

    private void FechaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FechaMousePressed
        CalendarModal cm = new CalendarModal(app, true, Fecha);
        cm.setVisible(true);
    }//GEN-LAST:event_FechaMousePressed

    private void EntradaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EntradaMousePressed
        TimeModal tm = new TimeModal(app, true, Entrada);
        tm.setVisible(true);
    }//GEN-LAST:event_EntradaMousePressed

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        if (pr != null) {
            //Actualizar
            ActualizarPrestamo();
        } else {
            //Insertar
            InsertarPrestamo();
        }
    }//GEN-LAST:event_GuardarActionPerformed

    private void SalidaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SalidaMousePressed
        TimeModal tm = new TimeModal(app, true, Salida);
        tm.setVisible(true);
    }//GEN-LAST:event_SalidaMousePressed

    private void MacsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_MacsItemStateChanged
        ValidarCampos();
    }//GEN-LAST:event_MacsItemStateChanged

    private void IpadsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_IpadsItemStateChanged
        ValidarCampos();
    }//GEN-LAST:event_IpadsItemStateChanged

    private void FechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_FechaPropertyChange
        ValidarCampos();
    }//GEN-LAST:event_FechaPropertyChange

    private void EntradaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_EntradaPropertyChange
        ValidarHora();
    }//GEN-LAST:event_EntradaPropertyChange

    private void SalidaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_SalidaPropertyChange
        ValidarHora();
    }//GEN-LAST:event_SalidaPropertyChange

    private void C_MacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_MacActionPerformed
        ValidarCampos();
    }//GEN-LAST:event_C_MacActionPerformed

    private void C_IpadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_C_IpadActionPerformed
        ValidarCampos();
    }//GEN-LAST:event_C_IpadActionPerformed

    private void ValidarHora() {
        if (!Entrada.getText().isEmpty() && !Entrada.getText().equals("--:-- --") && !Entrada.getText().equals("--:--") && !Salida.getText().isEmpty() && !Salida.getText().equals("--:-- --") && !Salida.getText().equals("--:--")) {
            Time hora1 = convertirStringATime(Entrada.getText());
            Time hora2 = convertirStringATime(Salida.getText());
            if (hora1.after(hora2)) {
                JOptionPane.showMessageDialog(app, "La hora de salida debe ser posterior a la de entrada", "Aviso", JOptionPane.WARNING_MESSAGE);
                Salida.setText("");
            } else {
                ValidarCampos();
            }
        } else {
            if (!Entrada.getText().isEmpty() && !Entrada.getText().equals("--:-- --")) {
                ValidarCampos();
            }
        }
    }

    private static Time convertirStringATime(String horaString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            java.util.Date date = sdf.parse(horaString);
            return new Time(date.getTime());
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    private static Date convertirStringADate(String fecha) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(fecha);
            return new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            System.out.println(e);
        }
        return null;
    }

    private String ValidarHoras(java.sql.Time hora) {
        String horaString = "";
        if (hora != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime horaLocalTime = hora.toLocalTime();
            horaString = formatter.format(horaLocalTime);
        }
        return horaString;
    }

    private void ValidarCampos() {
        if (pr != null) {
            //Actualizar. Los nuevos datos no deben ser igual a los anteriores
            Guardar.setEnabled(
                    Macs.getSelectedIndex() > 0 && !Macs.getSelectedItem().equals(pr.getId_mac())
                    || Ipads.getSelectedIndex() > 0 && !Ipads.getSelectedItem().equals(pr.getId_ipad())
                    || !Fecha.getText().equals(pr.getFecha().toString()) || C_Mac.isSelected() != pr.isC_mac()
                    || C_Ipad.isSelected() != pr.isC_ipad()
                    || Credencial.isSelected() != pr.isCredencial()
                    || (!Entrada.getText().equals(ValidarHoras(pr.getHora_entrada())) && !Entrada.getText().equals("--:-- --"))
                    || (!Salida.getText().equals(ValidarHoras(pr.getHora_salida())) && !Salida.getText().equals("--:-- --"))
            );
        } else {
            //Insertar. No todos los campos son obligatorios, puede pedir una mac o ipad, 
            //y solo tener la hora de entrada para despues actualizar el prestamo y añadir la de salida
            Guardar.setEnabled(
                    (Macs.getSelectedIndex() > 0 || Ipads.getSelectedIndex() > 0)
                    && !Fecha.getText().isEmpty() && !Entrada.getText().isEmpty()
            );
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox C_Ipad;
    private javax.swing.JCheckBox C_Mac;
    private javax.swing.JCheckBox Credencial;
    private javax.swing.JFormattedTextField Entrada;
    private javax.swing.JFormattedTextField Fecha;
    private javax.swing.JButton Guardar;
    private javax.swing.JTextField Identificador;
    private javax.swing.JComboBox<String> Ipads;
    private javax.swing.JComboBox<String> Macs;
    private javax.swing.JTextField Nombre;
    private javax.swing.JFormattedTextField Salida;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel label;
    // End of variables declaration//GEN-END:variables
}
