package Controller;

import Dao.AlumnoDao;
import Model.Database.Alumno;

/**
 *
 * @author Hitler
 */
public class AlumnosController {

    AlumnoDao AD = new AlumnoDao();

    public boolean InsertAlumno(Alumno alumno) {
        return AD.InsertAlumno(alumno);
    }

    public boolean UpdateAlumno(Alumno alumno) {
        return AD.UpdateAlumno(alumno);
    }

    public boolean Baja_Alta(String matricula, String status) {
        return AD.Baja_Alta(matricula, status);
    }

}
