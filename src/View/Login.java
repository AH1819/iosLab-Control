package View;

import Model.Database.ConexionBD;
import Model.Database.ErrorsAndSuccesses;
import Model.Database.Logeo;
import Model.Database.PersistenciaGetSet;
import Model.Views.ThemesChange;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Font;
import javax.swing.UIManager;
import raven.alerts.MessageAlerts;
import raven.popup.GlassPanePopup;

/**
 *
 * @author Hitler
 */
public class Login extends javax.swing.JFrame {

    private static Logeo logeo;

    public Login() {
        initComponents();
        this.setLocationRelativeTo(this);
        GlassPanePopup.install(this);
        login.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Login.background;"
                + "arc:50");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new raven.crazypanel.CrazyPanel();
        lbTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        cmdLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        login.setFlatLafStyleComponent(new raven.crazypanel.FlatLafStyleComponent(
            "[light]border:0,0,0,0,shade(@background,5%),,20;[dark]border:0,0,0,0,tint(@background,5%),,20;[light]background:shade(@background,5%);[dark]background:tint(@background,5%)",
            new String[]{
                "font:bold +10",
                "font:bold",
                "margin:5,10,5,10;focusWidth:1;innerFocusWidth:0;showClearButton:true;JTextField.placeholderText=Usuario",
                "font:bold",
                "margin:5,10,5,10;focusWidth:1;innerFocusWidth:0;showRevealButton:true;showClearButton:true;JTextField.placeholderText=Contraseña",
                "background:$Component.accentColor;foreground:rgb(250,250,250);borderWidth:0;focusWidth:0;innerFocusWidth:0"
            }
        ));
        login.setMigLayoutConstraints(new raven.crazypanel.MigLayoutConstraints(
            "wrap 1,fillx,insets 45 45 50 45",
            "[grow 1,center]15[fill]",
            "",
            new String[]{
                "wrap 38",
                "span,grow",
                "span,grow,wrap 20",
                "span,grow",
                "span,grow,wrap 40",
                "span,grow"
            }
        ));

        lbTitle.setText("Login");
        login.add(lbTitle);

        jLabel2.setText("Usuario:");
        login.add(jLabel2);

        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });
        txtUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUserKeyReleased(evt);
            }
        });
        login.add(txtUser);

        jLabel3.setText("Contraseña:");
        login.add(jLabel3);

        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPassKeyReleased(evt);
            }
        });
        login.add(txtPass);

        cmdLogin.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        cmdLogin.setText("Login");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });
        login.add(cmdLogin);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserActionPerformed

    private void txtUserKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUserKeyReleased
        if (evt.getKeyCode() == 115) {
            DBConfig cb = new DBConfig(this, txtUser.getText(), new String(txtPass.getPassword()));
            cb.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_txtUserKeyReleased

    private void txtPassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyReleased
        if (evt.getKeyCode() == 115) {
            DBConfig cb = new DBConfig(this, txtUser.getText(), new String(txtPass.getPassword()));
            cb.setVisible(true);
            this.setVisible(false);
        }
        if (evt.getKeyCode() == 10) {
            Validar();
        }
    }//GEN-LAST:event_txtPassKeyReleased

    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed
        Validar();
    }//GEN-LAST:event_cmdLoginActionPerformed

    private void Validar() {
        if (txtUser.getText().isEmpty()) {
            txtUser.requestFocus();
        } else if (String.valueOf(txtPass.getPassword()).isEmpty()) {
            txtPass.requestFocus();
        } else {
            new Thread(() -> Autentificacion(txtUser.getText(), new String(txtPass.getPassword()))).start();
        }
    }

    private void Autentificacion(String user, String password) {
        PersistenciaGetSet pgs = new PersistenciaGetSet();
        if (pgs.Leer()) {
            ConexionBD con = ConexionBD.getInstance();
            logeo = new Logeo(user, password);
            con.conectar();
            if (ErrorsAndSuccesses.isConexion()) {
                Application app = new Application();
                app.setVisible(true);
                this.dispose();
            } else {
                MessageAlerts.getInstance().showMessage("Credenciales", ErrorsAndSuccesses.getCadenaErrorBD(), MessageAlerts.MessageType.ERROR);
            }
        } else {
            DBConfig dbc = new DBConfig(this, user, password);
            dbc.setVisible(true);
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        FlatLaf.registerCustomDefaultsSource("com.raven.datechooser.demo");
        String listMonth[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        String listDays[] = {"Lu.", "Ma.", "Mi.", "Ju.","Vi.", "Sá.", "Do."};
        UIManager.put("DateChooser.listMonth", listMonth);
        UIManager.put("DateChooser.listDay", listDays);
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("Recursos.Themes");
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        ThemesChange tch = new ThemesChange();
        if (tch.getMode("Modo")) {
            FlatMacDarkLaf.setup();
        } else {
            FlatMacLightLaf.setup();
        }
        java.awt.EventQueue.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdLogin;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbTitle;
    private raven.crazypanel.CrazyPanel login;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
