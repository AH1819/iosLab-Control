package Model.Database;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hitler
 */
@Setter
@Getter
public class Docente extends Persona {

    String numero_plaza;
    String institucion;
    String correo;
    String status;
}
