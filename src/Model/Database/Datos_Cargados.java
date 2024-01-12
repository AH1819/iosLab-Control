package Model.Database;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Hitler
 */
public class Datos_Cargados {

    @Setter
    @Getter
    private static ArrayList<Macs> macs_guardadas;
    @Setter
    @Getter
    private static ArrayList<Ipads> ipads_guardadas;
    @Setter
    @Getter
    private static ArrayList<Docente> docentes_guardados;
    @Setter
    @Getter
    private static ArrayList<Alumno> alumnos_guardados;
    @Getter
    @Setter
    private static ArrayList<Prestamos> prestamos_guardados;
}
