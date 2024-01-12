package Model.Database;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hitler
 */
@Setter
@Getter
public class Alumno extends Persona {

    String matricula;
    String procedencia;
    String semestre;
    String grupo;
    String correo;
    String status;
}
