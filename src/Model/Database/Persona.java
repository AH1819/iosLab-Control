package Model.Database;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hitler
 */
@Setter
@Getter
public class Persona {
    
    int id_persona;
    String nombre;
    String apellido_p;
    String apellido_m;
    int edad;
    String sexo;
}
