package Controller;

import Dao.AlumnoDao;
import Dao.DocenteDao;
import Model.Database.Alumno;
import Model.Database.Datos_Cargados;
import Model.Database.Docente;
import java.util.ArrayList;

/**
 *
 * @author Hitler
 */
public class PersonasController {

    DocenteDao PD = new DocenteDao();
    AlumnoDao AD = new AlumnoDao();

    public boolean CargarDocentes() {
        ArrayList<Docente> docentes = PD.GetAll();
        Datos_Cargados.setDocentes_guardados(docentes);
        return !docentes.isEmpty() && docentes.get(0).getNumero_plaza() != null;
    }

    public boolean CargarAlumnos() {
        ArrayList<Alumno> alumnos = AD.GetAll();
        Datos_Cargados.setAlumnos_guardados(alumnos);
        return !alumnos.isEmpty() && alumnos.get(0).getMatricula() != null;
    }
}
