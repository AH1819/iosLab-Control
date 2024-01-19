package PrintReport;

import Model.Database.ConexionBD;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Jasper {

    private final ConexionBD conexion = ConexionBD.getInstance();

    public void Generar_Reporte(Date inicio, Date fin, boolean status) {
        Map parametros = new HashMap<String, Date>();
        parametros.put("Inicio", inicio);
        parametros.put("Fin", fin);
        JasperPrint cargar;
        try {
            if (status) {
                JasperReport js = (JasperReport) JRLoader.loadObject(Jasper.class.getResource("/Recursos/Plantillas/Prestamos.jasper"));

                cargar = JasperFillManager.fillReport(js, null, conexion.conectar());
            } else {
                JasperReport js = (JasperReport) JRLoader.loadObject(Jasper.class.getResource("/Recursos/Plantillas/PrestamosRango.jasper"));

                cargar = JasperFillManager.fillReport(js, parametros, conexion.conectar());
            }
            JasperViewer jv = new JasperViewer(cargar, true);

            jv.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(Jasper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
