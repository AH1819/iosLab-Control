package Controller;

import Dao.NotasDao;
import Model.Database.Notas;
import java.util.ArrayList;

/**
 *
 * @author Hitler
 */
public class NotasController {

    NotasDao ND = new NotasDao();

    public ArrayList<Notas> GetAll() {
        return ND.GetAll();
    }

    public ArrayList<Notas> GetAllNotasEQ(String tipo, String id) {
        return ND.GetAllEQ(tipo, id);
    }

    public ArrayList<Notas> GetAllNotasPR() {
        return ND.GetAllPR();
    }

    public boolean AddNotaEQ(Notas nota) {
        return ND.InsertNotaEQ(nota);
    }

    public boolean AddNotaPR(Notas nota) {
        return ND.InsertNotaPR(nota);
    }

    public boolean AddNotaGB(Notas nota) {
        return ND.InsertNotaGB(nota);
    }

    public boolean UpdateNotas(Notas nota) {
        return ND.UpdateNotas(nota);
    }

    public boolean DeleteNotas(int id) {
        return ND.DeleteNotas(id);
    }

}
