package View;

import Model.Database.ConexionBD;
import Model.Database.ErrorsAndSuccesses;
import Model.Database.Logeo;
import Model.Database.PersistenciaGetSet;
import Model.Database.PersistenciaLogin;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Hitler
 */
public class DBConfig extends javax.swing.JFrame {

    ConexionBD cn;
    PersistenciaGetSet pgs;
    Logeo test;
    boolean status_conexion;
    JFrame vista;

    public DBConfig(JFrame vista, String usuario, String password) {
        initComponents();
        this.vista = vista;
        txtUser.setText(usuario);
        txtPassword.setText(password);
        this.setLocationRelativeTo(this);
        txtPassword.putClientProperty(FlatClientProperties.STYLE, ""
                + "showRevealButton:true;"
                + "showCapsLock:true;");
        Status.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999");
        Messages.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:20");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crazyPanel1 = new raven.crazypanel.CrazyPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        txtHost = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtPuerto = new javax.swing.JTextField();
        Test = new javax.swing.JButton();
        Status = new javax.swing.JPanel();
        Guardar = new javax.swing.JButton();
        Messages = new javax.swing.JPanel();
        MessageError = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        crazyPanel1.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20;[light]background:shade(@background,5%);[dark]background:tint(@background,5%)",
            new String[]{
                "font:bold +10",
                "font:bold",
                "showClearButton:true;JTextField.placeholderText=Usuario",
                "showClearButton:true;JTextField.placeholderText=Contraseña",
                "font:bold",
                "showClearButton:true;JTextField.placeholderText=Host",
                "font:bold",
                "showClearButton:true;JTextField.placeholderText=Nombre",
                "font:bold",
                "showClearButton:true;JTextField.placeholderText=Puerto",
                "font:plain",
                "",
                "font:plain"
            }
        ));
        crazyPanel1.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap 2,fillx,insets 20",
            "[grow 0,trail]15[fill]",
            "[grow 0]20",
            new String[]{
                "wrap,al lead",
                "",
                "split 2",
                "",
                "",
                "",
                "",
                "",
                "",
                "width 100,split 3",
                "width 100",
                "width 35,height 35",
                "span 2,al center"
            }
        ));

        jLabel1.setText("Detalles de la BD");
        crazyPanel1.add(jLabel1);

        jLabel4.setText("Credenciales");
        crazyPanel1.add(jLabel4);

        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserKeyReleased(evt);
            }
        });
        crazyPanel1.add(txtUser);

        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPasswordKeyReleased(evt);
            }
        });
        crazyPanel1.add(txtPassword);

        jLabel5.setText("Host del servidor");
        crazyPanel1.add(jLabel5);

        txtHost.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtHostKeyReleased(evt);
            }
        });
        crazyPanel1.add(txtHost);

        jLabel6.setText("Nombre de la base de datos");
        crazyPanel1.add(jLabel6);

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNombreKeyReleased(evt);
            }
        });
        crazyPanel1.add(txtNombre);

        jLabel9.setText("Puerto");
        crazyPanel1.add(jLabel9);

        txtPuerto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPuertoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPuertoKeyTyped(evt);
            }
        });
        crazyPanel1.add(txtPuerto);

        Test.setText("Probar Conexion");
        Test.setEnabled(false);
        Test.setPreferredSize(new java.awt.Dimension(125, 24));
        Test.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TestActionPerformed(evt);
            }
        });
        crazyPanel1.add(Test);

        javax.swing.GroupLayout StatusLayout = new javax.swing.GroupLayout(Status);
        Status.setLayout(StatusLayout);
        StatusLayout.setHorizontalGroup(
            StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 79, Short.MAX_VALUE)
        );
        StatusLayout.setVerticalGroup(
            StatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );

        crazyPanel1.add(Status);

        Guardar.setText("Guardar configuracion");
        Guardar.setEnabled(false);
        Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarActionPerformed(evt);
            }
        });
        crazyPanel1.add(Guardar);

        Messages.setBorder(javax.swing.BorderFactory.createTitledBorder("Mensajes de la conexion"));

        MessageError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MessageError.setText("Llena todos los campos para iniciar el test");

        javax.swing.GroupLayout MessagesLayout = new javax.swing.GroupLayout(Messages);
        Messages.setLayout(MessagesLayout);
        MessagesLayout.setHorizontalGroup(
            MessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MessagesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MessageError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        MessagesLayout.setVerticalGroup(
            MessagesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MessagesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(MessageError, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(crazyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Messages, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(crazyPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Messages, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TestActionPerformed
        TestearConexion();
    }//GEN-LAST:event_TestActionPerformed

    private void txtUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyReleased
        ValidarTest();
    }//GEN-LAST:event_txtUserKeyReleased

    private void txtPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyReleased
        ValidarTest();
    }//GEN-LAST:event_txtPasswordKeyReleased

    private void txtHostKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtHostKeyReleased
        ValidarTest();
    }//GEN-LAST:event_txtHostKeyReleased

    private void txtNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyReleased
        ValidarTest();
    }//GEN-LAST:event_txtNombreKeyReleased

    private void txtPuertoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPuertoKeyReleased
        ValidarTest();
    }//GEN-LAST:event_txtPuertoKeyReleased

    private void txtPuertoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPuertoKeyTyped
        int key = evt.getKeyChar();

        boolean numeros = key >= 48 && key <= 57;

        if (!numeros) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPuertoKeyTyped

    private void GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarActionPerformed
        GuardarConexion();
    }//GEN-LAST:event_GuardarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        vista.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void ValidarTest() {
        Guardar.setEnabled(false);
        Test.setEnabled(
                !txtUser.getText().isEmpty()
                && !String.valueOf(txtPassword.getPassword()).isEmpty()
                && !txtHost.getText().isEmpty()
                && !txtNombre.getText().isEmpty()
                && !txtPuerto.getText().isEmpty()
        );
    }

    private void TestearConexion() {
        test = new Logeo();
        test.setUsuario(txtUser.getText());
        test.setPassword(new String(txtPassword.getPassword()));
        test.setPuerto(txtPuerto.getText());
        test.setIp(txtHost.getText());
        test.setBase(txtNombre.getText());

        cn = ConexionBD.getInstance();
        cn.conectar();
        if (!ErrorsAndSuccesses.isConexion()) {
            status_conexion = false;
            MessageError.setText("<html>" + ErrorsAndSuccesses.getCadenaErrorBD() + "</html>");
            Status.setBackground(Color.red);
        } else {
            status_conexion = true;
            MessageError.setText("Conexion establecida sin ningun error");
            Status.setBackground(Color.green);
        }
        Guardar.setEnabled(status_conexion);
    }

    private void GuardarConexion() {
        PersistenciaLogin p = new PersistenciaLogin();
        p.setHost(test.getIp());
        p.setBase(test.getBase());
        p.setPuerto(test.getPuerto());
        pgs = new PersistenciaGetSet();
        if (pgs.Guardar(p)) {
            MessageError.setText("Guardado con exito");
            MessageError.setForeground(Color.GREEN);
        }
        Guardar.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Guardar;
    private javax.swing.JLabel MessageError;
    private javax.swing.JPanel Messages;
    private javax.swing.JPanel Status;
    private javax.swing.JButton Test;
    private raven.crazypanel.CrazyPanel crazyPanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtHost;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPuerto;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
