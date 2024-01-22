package View;

import Controller.AlumnosController;
import Model.Database.Alumno;
import Model.Database.Datos_Cargados;
import Model.Views.FormManager;
import Model.Views.SimpleForm;
import com.formdev.flatlaf.FlatClientProperties;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Hitler
 */
public class Alumno_New_or_Update extends SimpleForm {

    AlumnosView av;
    ArrayList<Alumno> alumnos;
    Application app;
    String matricula = "";
    Alumno alumno;

    public Alumno_New_or_Update(Application app, AlumnosView av, Alumno alumno) {
        initComponents();
        Guardar.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:lighten(@background,8%);"
                + "borderWidth:1");
        this.app = app;
        this.av = av;
        this.alumno = alumno;
        if (alumno != null) {
            RellenarInformacion();
        }
        alumnos = Datos_Cargados.getAlumnos_guardados();
    }

    private void RellenarInformacion() {
        Matricula.setText(alumno.getMatricula());
        matricula = alumno.getMatricula();
        Nombre.setText(alumno.getNombre());
        App.setText(alumno.getApellido_p());
        Apm.setText(alumno.getApellido_m());
        Edad.setText(String.valueOf(alumno.getEdad()));
        Sexo.setSelectedItem(alumno.getSexo());
        Procedencia.setText(alumno.getProcedencia());
        Semestre.setText(alumno.getSemestre());
        Grupo.setText(alumno.getGrupo());
        Email.setText(alumno.getCorreo());
        Guardar.setEnabled(false);
        Cargando.setVisible(false);
    }

    private void ComprobarMatricula(String matricula) {
        for (Alumno persona : alumnos) {
            if (!matricula.equals(this.matricula)) {
                if (matricula.equals(persona.getMatricula())) {
                    Matricula.requestFocus();
                    Matricula.putClientProperty(FlatClientProperties.STYLE, ""
                            + "foreground:#f00");
                    JOptionPane.showMessageDialog(app, "Matricula repetida", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    Matricula.putClientProperty(FlatClientProperties.STYLE, ""
                            + "foreground:@foreground");
                    ValidarContenido();
                }
            } else {
                Matricula.putClientProperty(FlatClientProperties.STYLE, ""
                        + "foreground:@foreground");
                ValidarContenido();
            }
        }
    }

    private void validarEmail(String email) {
        String regex
                = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.(com|mx))+";
        email = email.toLowerCase();
        Pattern patron = Pattern.compile(regex);
        if (!patron.matcher(email).matches()) {
            Email.putClientProperty(FlatClientProperties.STYLE, ""
                    + "foreground:#f00");
        } else {
            Email.putClientProperty(FlatClientProperties.STYLE, ""
                    + "foreground:@foreground");
            ValidarContenido();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Cargando = new javax.swing.JLabel();
        Guardar = new javax.swing.JButton();
        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        Matricula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sex = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        App = new javax.swing.JTextField();
        Apm = new javax.swing.JTextField();
        Edad = new javax.swing.JTextField();
        Sexo = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Procedencia = new javax.swing.JTextField();
        Semestre = new javax.swing.JTextField();
        Grupo = new javax.swing.JTextField();
        Email = new javax.swing.JTextField();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cargando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/cargando.gif"))); // NOI18N
        jPanel1.add(Cargando, new org.netbeans.lib.awtextra.AbsoluteConstraints(126, 5, -1, -1));

        Guardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Icons/guardar.png"))); // NOI18N
        Guardar.setText("Guardar");
        Guardar.setEnabled(false);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        jPanel1.add(Guardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "background:$Table.background;[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20",
            null
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap 4,fillx,insets 30",
            "[fill]25[fill]",
            "[grow 0]20[fill]",
            new String[]{
                "wrap",
                "height 35,wrap",
                "",
                "",
                "",
                "split 2",
                "",
                "height 35",
                "height 35",
                "height 35",
                "height 35,split 2",
                "height 35",
                "",
                "",
                "",
                "",
                "height 35",
                "height 35",
                "height 35",
                "height 35",
                "span,al center"
            }
        ));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Matricula:");
        crazyPanel1.add(jLabel1);

        Matricula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Matricula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                MatriculaKeyReleased(evt);
            }
        });
        crazyPanel1.add(Matricula);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nombre:");
        crazyPanel1.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Apellido paterno:");
        crazyPanel1.add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Apellido materno:");
        crazyPanel1.add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Edad:");
        crazyPanel1.add(jLabel5);

        sex.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sex.setText("Sexo:");
        crazyPanel1.add(sex);

        Nombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                NombreKeyReleased(evt);
            }
        });
        crazyPanel1.add(Nombre);

        App.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        App.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AppKeyReleased(evt);
            }
        });
        crazyPanel1.add(App);

        Apm.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Apm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ApmKeyReleased(evt);
            }
        });
        crazyPanel1.add(Apm);

        Edad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Edad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EdadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EdadKeyTyped(evt);
            }
        });
        crazyPanel1.add(Edad);

        Sexo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una opcion", "Masculino", "Femenino" }));
        Sexo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                SexoItemStateChanged(evt);
            }
        });
        crazyPanel1.add(Sexo);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Procedencia:");
        crazyPanel1.add(jLabel7);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Semestre:");
        crazyPanel1.add(jLabel8);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Grupo:");
        crazyPanel1.add(jLabel9);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Email:");
        crazyPanel1.add(jLabel10);

        Procedencia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Procedencia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ProcedenciaKeyReleased(evt);
            }
        });
        crazyPanel1.add(Procedencia);

        Semestre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Semestre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SemestreKeyReleased(evt);
            }
        });
        crazyPanel1.add(Semestre);

        Grupo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Grupo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                GrupoKeyReleased(evt);
            }
        });
        crazyPanel1.add(Grupo);

        Email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                EmailKeyReleased(evt);
            }
        });
        crazyPanel1.add(Email);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1085, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void MatriculaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MatriculaKeyReleased
        if (Matricula.getText().length() > 6) {
            ComprobarMatricula(Matricula.getText().toUpperCase());
        }
    }//GEN-LAST:event_MatriculaKeyReleased

    private void EmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EmailKeyReleased
        if (!Email.getText().isEmpty()) {
            validarEmail(Email.getText());
        }
    }//GEN-LAST:event_EmailKeyReleased

    private void NombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NombreKeyReleased
        ValidarContenido();
    }//GEN-LAST:event_NombreKeyReleased

    private void AppKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AppKeyReleased
        ValidarContenido();
    }//GEN-LAST:event_AppKeyReleased

    private void ApmKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ApmKeyReleased
        ValidarContenido();
    }//GEN-LAST:event_ApmKeyReleased

    private void EdadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdadKeyReleased
        ValidarContenido();
    }//GEN-LAST:event_EdadKeyReleased

    private void SexoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_SexoItemStateChanged
        ValidarContenido();
    }//GEN-LAST:event_SexoItemStateChanged

    private void ProcedenciaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ProcedenciaKeyReleased
        ValidarContenido();
    }//GEN-LAST:event_ProcedenciaKeyReleased

    private void SemestreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SemestreKeyReleased
        ValidarContenido();
    }//GEN-LAST:event_SemestreKeyReleased

    private void GrupoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_GrupoKeyReleased
        ValidarContenido();
    }//GEN-LAST:event_GrupoKeyReleased

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        Cargando.setVisible(true);
        Guardar.setEnabled(false);
        new Thread() {
            @Override
            public void run() {
                if (alumno != null) {
                    Actualizar();
                } else {
                    Insertar();
                }
            }
        }.start();
    }//GEN-LAST:event_GuardarActionPerformed

    private void EdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EdadKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_EdadKeyTyped
    private void ValidarContenido() {
        if (alumno != null) {
            Guardar.setEnabled(
                    !Matricula.getText().trim().isEmpty()
                    && !Matricula.getText().trim().equals(alumno.getMatricula())
                    || !Nombre.getText().trim().isEmpty()
                    && !Nombre.getText().trim().equals(alumno.getNombre())
                    || !App.getText().trim().isEmpty()
                    && !App.getText().trim().equals(alumno.getApellido_p())
                    || !Apm.getText().trim().isEmpty()
                    && !Apm.getText().trim().equals(alumno.getApellido_m())
                    || !Edad.getText().trim().isEmpty()
                    && !Edad.getText().trim().equals(String.valueOf(alumno.getEdad()))
                    || Sexo.getSelectedIndex() > 0
                    && !Sexo.getSelectedItem().equals(alumno.getSexo())
                    || !Procedencia.getText().trim().isEmpty()
                    && !Procedencia.getText().trim().equals(alumno.getProcedencia())
                    || !Semestre.getText().trim().isEmpty()
                    && !Semestre.getText().trim().equals(alumno.getSemestre())
                    || !Grupo.getText().trim().isEmpty()
                    && !Grupo.getText().trim().equals(alumno.getGrupo())
                    || !Email.getText().trim().isEmpty()
                    && !Email.getText().trim().equals(alumno.getCorreo())
            );
        } else {
            Guardar.setEnabled(
                    !Matricula.getText().isEmpty()
                    && !Nombre.getText().isEmpty()
                    && !App.getText().isEmpty()
                    && !Apm.getText().isEmpty()
                    && !Edad.getText().isEmpty()
                    && Sexo.getSelectedIndex() > 0
                    && !Procedencia.getText().isEmpty()
                    && !Semestre.getText().isEmpty()
                    && !Grupo.getText().isEmpty()
                    && !Email.getText().isEmpty()
            );
        }
    }

    private void Insertar() {
        alumno = new Alumno();
        alumno.setMatricula(Matricula.getText().toUpperCase());
        alumno.setNombre(Nombre.getText());
        alumno.setApellido_p(App.getText());
        alumno.setApellido_m(Apm.getText());
        alumno.setEdad(Integer.parseInt(Edad.getText()));
        alumno.setSexo(Sexo.getSelectedItem().toString());
        alumno.setProcedencia(Procedencia.getText());
        alumno.setSemestre(Semestre.getText());
        alumno.setGrupo(Grupo.getText());
        alumno.setCorreo(Email.getText());

        AlumnosController ac = new AlumnosController();

        if (ac.InsertAlumno(alumno)) {
            JOptionPane.showMessageDialog(app, "Alumno registrado con exito", "Registro", JOptionPane.INFORMATION_MESSAGE);
            new Thread() {
                @Override
                public void run() {
                    av.CargarAlumnos();
                    FormManager.CambiarTexto("  Alumnos");
                    FormManager.showForm(av);
                }
            }.start();
        } else {
            Cargando.setVisible(false);
            Guardar.setEnabled(true);
            JOptionPane.showMessageDialog(app, "Hubo un error al momento de registrar", "Registro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void Actualizar() {
        alumno.setMatricula(Matricula.getText().toUpperCase());
        alumno.setNombre(Nombre.getText());
        alumno.setApellido_p(App.getText());
        alumno.setApellido_m(Apm.getText());
        alumno.setEdad(Integer.parseInt(Edad.getText()));
        alumno.setSexo(Sexo.getSelectedItem().toString());
        alumno.setProcedencia(Procedencia.getText());
        alumno.setSemestre(Semestre.getText());
        alumno.setGrupo(Grupo.getText());
        alumno.setCorreo(Email.getText());

        AlumnosController ac = new AlumnosController();

        if (ac.UpdateAlumno(alumno)) {
            JOptionPane.showMessageDialog(app, "Alumno Actualizado con exito", "Actualizacion", JOptionPane.INFORMATION_MESSAGE);
            new Thread() {
                @Override
                public void run() {
                    av.CargarAlumnos();
                    FormManager.CambiarTexto("  Alumnos");
                    FormManager.showForm(av);
                }
            }.start();
        } else {
            Cargando.setVisible(false);
            Guardar.setEnabled(true);
            JOptionPane.showMessageDialog(app, "Hubo un error al momento de actualizar", "Actualizacion", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apm;
    private javax.swing.JTextField App;
    private javax.swing.JLabel Cargando;
    private javax.swing.JTextField Edad;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField Grupo;
    private javax.swing.JButton Guardar;
    private javax.swing.JTextField Matricula;
    private javax.swing.JTextField Nombre;
    private javax.swing.JTextField Procedencia;
    private javax.swing.JTextField Semestre;
    private javax.swing.JComboBox<String> Sexo;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel sex;
    // End of variables declaration//GEN-END:variables
}
