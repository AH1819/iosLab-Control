package Controller;

import Dao.IpadDao;
import Dao.MacDao;
import Model.Database.Datos_Cargados;
import Model.Database.Ipads;
import Model.Database.Macs;
import java.util.ArrayList;

/**
 *
 * @author Hitler
 */
public class EquiposController {

    IpadDao ID = new IpadDao();
    MacDao MD = new MacDao();

    public void CargarIpad() {
        ArrayList<Ipads> ipads = ID.GetIpads();
        Datos_Cargados.setIpads_guardadas(ipads);
    }

    public ArrayList<Ipads> CargarIpadsAvailable() {
        return ID.GetIpadsAvailable();
    }

    public boolean InsertIpad(Ipads ipd) {
        return ID.InsertIpad(ipd);
    }

    public boolean UpdateIpad(Ipads ip) {
        return ID.UpdateIpad(ip);
    }

    public void CargarMacs() {
        ArrayList<Macs> macs = MD.GetMacs();
        Datos_Cargados.setMacs_guardadas(macs);
    }

    public ArrayList<Macs> CargarMacsAvailable() {
        return MD.GetMacsAvailable();
    }

    public boolean InsertMac(Macs mc) {
        return MD.InsertMac(mc);
    }

    public boolean UpdateMac(Macs mc) {
        return MD.UpdateMac(mc);
    }
}
