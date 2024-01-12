package Model.Database;

/**
 *
 * @author Hitler
 */
public class Logeo {

    private static String usuario = "";
    private static String password = "";
    private static String puerto = "5432";
    private static String ip = "localhost";
    private static String base = "iosLab";

    public Logeo() {
    }

    public Logeo(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    public Logeo(String puerto, String ip, String base) {
        this.puerto = puerto;
        this.ip = ip;
        this.base = base;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }
}
