package Controller;

import Dao.PrestamosDao;
import Model.Database.Datos_Cargados;
import Model.Database.Prestamos;

/**
 *
 * @author Hitler
 */
public class PrestamosController {

    PrestamosDao pd = new PrestamosDao();

    public void GetPrestaciones() {
        Datos_Cargados.setPrestamos_guardados(pd.GetPrestamos());
    }

    public boolean InsertPrestamo(Prestamos presta) {
        return pd.InsertarPrestamo(presta);
    }

    public boolean ActualizarPrestamo(Prestamos presta) {
        return pd.ActualizarPrestamo(presta);
    }

    public boolean DeletePrestamo(int id) {
        return pd.DeletePrestamo(id);
    }

}
