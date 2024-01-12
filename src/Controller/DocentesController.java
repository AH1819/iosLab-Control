package Controller;

import Dao.DocenteDao;
import Model.Database.Docente;

/**
 *
 * @author Hitler
 */
public class DocentesController {

    DocenteDao DD = new DocenteDao();

    public boolean InsertDocente(Docente docente) {
        return DD.InsertDocente(docente);
    }

    public boolean UpdateAlumno(Docente alumno) {
        return DD.UpdateDocente(alumno);
    }

    public boolean Baja_Alta(String plaza, String status) {
        return DD.Baja_Alta(plaza, status);
    }
}
