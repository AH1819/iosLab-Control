package Model.Views;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Raven
 */
public class ThemesChange extends JPanel {

    private static final String NOMBRE_ARCHIVO = "config.properties";

    public ThemesChange() {
        init();
    }

    private Icon createIcon(String path) {
        FlatSVGIcon icon = new FlatSVGIcon(path, 0.7f);
        FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
        colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
        icon.setColorFilter(colorFilter);
        return icon;
    }

    private void init() {
        putClientProperty(FlatClientProperties.STYLE, ""
                + "background:null");
        setLayout(new MigLayout("al center", "[fill,200]", "fill"));
        JPanel panel = new JPanel(new MigLayout("fill", "[fill]10[fill]", "fill"));
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "background:darken($Drawer.background,5%)");
        JButton buttonLight = new JButton(createIcon("Recursos/Icons/light.svg"));
        JButton buttonDark = new JButton(createIcon("Recursos/Icons/dark.svg"));
        buttonLight.addActionListener(e -> changeMode(false));
        buttonDark.addActionListener(e -> changeMode(true));
        buttonLight.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "[dark]background:null;"
                + "[light]background:$Drawer.background;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:3,5,3,5");

        buttonDark.putClientProperty(FlatClientProperties.STYLE, ""
                + "arc:999;"
                + "[dark]background:$Drawer.background;"
                + "[light]background:null;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0;"
                + "margin:3,5,3,5");
        panel.add(buttonDark);
        panel.add(buttonLight);
        add(panel);
    }

    private void changeMode(boolean dark) {
        if (dark != FlatLaf.isLafDark()) {
            if (dark) {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacDarkLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            } else {
                EventQueue.invokeLater(() -> {
                    FlatAnimatedLafChange.showSnapshot();
                    FlatMacLightLaf.setup();
                    FlatLaf.updateUI();
                    FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
            }
        }
        saveMode(dark);
    }

    public static void saveMode(boolean dark) {
        try (FileOutputStream archivoOutput = new FileOutputStream(NOMBRE_ARCHIVO)) {
            Properties propiedades = new Properties();
            propiedades.setProperty("Modo", String.valueOf(dark));
            propiedades.store(archivoOutput, null);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean getMode(String clave) {
        Properties propiedades = new Properties();
        try (FileInputStream archivoInput = new FileInputStream(NOMBRE_ARCHIVO)) {
            propiedades.load(archivoInput);
            return Boolean.parseBoolean(propiedades.getProperty(clave));
        } catch (IOException e) {
            return true;
        }
    }

}
