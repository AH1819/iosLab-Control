package View;

import Controller.EquiposController;
import Controller.PersonasController;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Dimension;
import javax.swing.JFrame;
import Model.Views.Background;
import Model.Views.FormManager;
import raven.popup.GlassPanePopup;

/**
 *
 * @author Raven
 */
public class Application extends JFrame {

    public Application() {
        CargarDatos();
        init();
    }

    private void init() {
        GlassPanePopup.install(this);
        setTitle("IosLab Control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(UIScale.scale(new Dimension(1280, 600)));
        setMinimumSize(new Dimension(1175, 600));
        setLocationRelativeTo(null);
        setContentPane(new Background());
        FormManager.install(this);
        FormManager.showForm(new PrestamosView(this));
    }

    private void CargarDatos() {
        PersonasController pc = new PersonasController();
        pc.CargarAlumnos();
        pc.CargarDocentes();
        EquiposController ec = new EquiposController();
        ec.CargarIpad();
        ec.CargarMacs();
    }

}
