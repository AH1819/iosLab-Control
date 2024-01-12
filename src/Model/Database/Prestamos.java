package Model.Database;

import java.sql.Date;
import java.sql.Time;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hitler
 */
@Getter
@Setter
public class Prestamos {

    int identificador;
    String id_usuario;
    String nombre;
    String id_mac;
    String id_ipad;
    boolean c_mac;
    boolean c_ipad;
    boolean credencial;
    Date fecha;
    Time hora_entrada;
    Time hora_salida;
    String status;
}
