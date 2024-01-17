package Model.Views;

import View.Application;
import View.Notas_Globales;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Raven
 */
public class MainForm extends JPanel {

    private final javax.swing.JLabel jLabel1 = new javax.swing.JLabel();
    private Application app;

    public MainForm(Application app) {
        init();
        this.app = app;
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5;"
                + "arc:30");
        setLayout(new MigLayout("wrap,fillx", "[fill]", "[fill]"));
        header = createHeader();
        panelSlider = new PanelSlider();
        JScrollPane scroll = new JScrollPane(panelSlider);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0");
        scroll.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, ""
                + "trackArc:999;"
                + "width:10");
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        add(header);
        add(scroll, "span,growy");
    }

    private JPanel createHeader() {
        JPanel panel = new JPanel(new MigLayout("fill,insets 3", "[][grow]push[]"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:null;"
                + "border:5,5,5,5,$Component.borderColor,,20");

        JButton cmdMenu = createButton(new FlatSVGIcon("Recursos/Icons/menu.svg"));
        cmdMenu.addActionListener(e -> {
            FormManager.showMenu();
        });

        jLabel1.setText("  Prestamos");
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 20));
        JButton cmdNotas = createButton(new FlatSVGIcon("Recursos/Icons/notemn.svg"));
        cmdNotas.addActionListener(e -> {
            Notas_Globales ng = new Notas_Globales(app, true);
            ng.setVisible(true);
        });
        cmdMenu.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "[dark]background:$Drawer.background;"
                + "[light]foreground:$Drawer.background;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:3,5,3,5");
        panel.add(cmdMenu);
        panel.add(jLabel1);
        panel.add(cmdNotas);

        return panel;
    }

    public void ActualizarTexto(String texto) {
        jLabel1.setText(texto);
    }

    private JButton createButton(Icon icon) {
        JButton button = new JButton(icon);
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Button.toolbar.background;"
                + "arc:10;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        return button;
    }

    public void showForm(Component component) {
        panelSlider.addSlide(component, SimpleTransition.getDefaultTransition(false));
        revalidate();
    }

    public void setForm(Component component) {
        panelSlider.addSlide(component, null);
    }

    private JPanel header;
    private PanelSlider panelSlider;
}
