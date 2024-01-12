package Model.Database;

import View.DBConfig;
import View.Login;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hitler
 */
public class PersistenciaGetSet {

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";

    private static final String SECRET_KEY = "+F7JKCYEiSXzDMx6ACsdTA==";

    public boolean Guardar(Object logeo) {
        try (ObjectOutputStream objg = new ObjectOutputStream(new CipherOutputStream(new FileOutputStream("Logeo.dat"), obtenerCipher(Cipher.ENCRYPT_MODE)))) {
            objg.writeObject(logeo);
            return true;
        } catch (IOException | GeneralSecurityException e) {
            JOptionPane.showMessageDialog(null, "Error -->\n" + e, "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
    }

    public boolean Leer() {
        //Lectura
        FileInputStream fichero = null;

        try {
            fichero = new FileInputStream("Logeo.dat");
            try (ObjectInputStream obji = new ObjectInputStream(new CipherInputStream(new FileInputStream("Logeo.dat"), obtenerCipher(Cipher.DECRYPT_MODE)))) {
                PersistenciaLogin l = (PersistenciaLogin) obji.readObject();
                Logeo p = new Logeo(l.getPuerto(), l.getHost(), l.getBase());
            } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException ex) {
                Logger.getLogger(PersistenciaGetSet.class.getName()).log(Level.SEVERE, null, ex);
            }
            fichero.close();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Se debe configurar la conexion\n a la base de datos", "Aviso", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    private Cipher obtenerCipher(int modo) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        SecretKey secretKey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        cipher.init(modo, secretKey);
        return cipher;
    }

}
