package Model.Views;

import Model.Database.Datos_Cargados;
import View.AcercaDe;
import View.AlumnosView;
import View.Application;
import View.DocentesView;
import View.EquiposView;
import View.Login;
import View.PrestamosView;
import View.ReportesView;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import raven.drawer.component.DrawerPanel;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.header.SimpleHeaderStyle;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.drawer.component.menu.SimpleMenuStyle;
import raven.swing.AvatarIcon;

/**
 *
 * @author Raven
 */
public class MyDrawerBuilder extends SimpleDrawerBuilder {

    private final ThemesChange themesChange;
    private final Application app;

    public MyDrawerBuilder(Application app) {
        themesChange = new ThemesChange();
        this.app = app;
    }

    @Override
    public Component getFooter() {
        return themesChange;
    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        AvatarIcon icon = new AvatarIcon(getClass().getResource("/Recursos/Images/unach.png"), 100, 100, 999);
        icon.setBorder(2);
        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle("Facultad de negocios")
                .setDescription("Campus IV")
                .setHeaderStyle(new SimpleHeaderStyle() {

                    @Override
                    public void styleTitle(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#FAFAFA");
                    }

                    @Override
                    public void styleDescription(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#E1E1E1");
                    }
                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData();
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {

        String menus[][] = {
            {"~PRINCIPAL~"},
            {"Prestamos"},
            {"Alumnos"},
            {"Docentes"},
            {"Equipos"},
            {"~UTILIDADES~"},
            {"Reportes"},
            {"~OTROS~"},
            {"Acerca de"},
            {"Cerrar Sesion"}};

        String icons[] = {
            "loan.svg",
            "student.svg",
            "teacher.svg",
            "device.svg",
            "document.svg",
            "about.svg",
            "logout.svg",};

        SimpleMenuOption simpleMenuOption = new SimpleMenuOption() {
            @Override
            public Icon buildMenuIcon(String path, float scale) {
                FlatSVGIcon icon = new FlatSVGIcon(path, scale);
                FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
                colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
                icon.setColorFilter(colorFilter);
                return icon;
            }
        };

        simpleMenuOption.setMenuStyle(new SimpleMenuStyle() {
            @Override
            public void styleMenuItem(JButton menu, int index) {
                menu.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:#FAFAFA;"
                        + "arc:10");
            }

            @Override
            public void styleSubMenuItem(JButton subMenu, int index, int subIndex) {
                subMenu.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:#FAFAFA;"
                        + "arc:10");
            }

            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, ""
                        + "background:$Drawer.background");
            }

            @Override
            public void styleLabel(JLabel label) {
                label.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:darken(#FAFAFA,15%);"
                        + "[dark]foreground:darken($Label.foreground,30%)");
            }
        });
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int index, int subIndex) {

                switch (index) {
                    case 0:
                        FormManager.CambiarTexto("  Prestamo");
                        FormManager.showForm(new PrestamosView(app));
                        break;
                    case 1:
                        FormManager.CambiarTexto("  Alumnos");
                        FormManager.showForm(new AlumnosView(app));
                        break;
                    case 2:
                        FormManager.CambiarTexto("  Docentes");
                        FormManager.showForm(new DocentesView(app));
                        break;
                    case 3:
                        FormManager.CambiarTexto("  Equipos");
                        FormManager.showForm(new EquiposView(app));
                        break;
                    case 4:
                        FormManager.CambiarTexto("  Reportes");
                        FormManager.showForm(new ReportesView());
                        break;
                    case 5:
                        FormManager.CambiarTexto("  Acerca de");
                        FormManager.showForm(new AcercaDe());
                        break;
                    case 6:
                        Login log = new Login();
                        log.setVisible(true);
                        Datos_Cargados.setAlumnos_guardados(null);
                        Datos_Cargados.setDocentes_guardados(null);
                        Datos_Cargados.setIpads_guardadas(null);
                        Datos_Cargados.setMacs_guardadas(null);
                        Datos_Cargados.setPrestamos_guardados(null);
                        app.dispose();
                        break;
                }
            }
        });

        simpleMenuOption.setMenus(menus)
                .setIcons(icons)
                .setBaseIconPath("Recursos/Icons")
                .setIconScale(0.45f);
        return simpleMenuOption;
    }

    @Override
    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Drawer.background");
    }

    @Override
    public int getDrawerWidth() {
        return 160;
    }

}
