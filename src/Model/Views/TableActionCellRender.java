package Model.Views;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRender extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
        PanelAction action = null;
        if (column != 6 && column != 4) {
            action = new PanelAction();
            if (!isSelected && row % 2 == 0) {
                action.setBackground(com.getBackground());
            } else {
                action.setBackground(com.getBackground());
            }
        }

        if (column == 6) {
            // Verificar el valor de la celda en la columna 6
            String cellValue = (String) o;
            if (null == cellValue) {
                // Otros valores: restaurar colores predeterminados
                com.setBackground(table.getBackground());
                com.setForeground(table.getForeground());
            } else {
                switch (cellValue) {
                    case "En uso":
                        // Celda "En uso": fondo rojo, letras blancas
                        com.setBackground(Color.RED);
                        com.setForeground(Color.WHITE);
                        break;
                    case "Ok":
                        // Celda "Ok": fondo verde, letras negras
                        com.setBackground(Color.GREEN);
                        com.setForeground(Color.BLACK);
                        break;
                    default:
                        // Otros valores: restaurar colores predeterminados
                        com.setBackground(table.getBackground());
                        com.setForeground(table.getForeground());
                        break;
                }
            }
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        if (column == 4) {
            // Verificar el valor de la celda en la columna 6
            String cellValue = (String) o;
            if (null == cellValue) {
                // Otros valores: restaurar colores predeterminados
                com.setBackground(table.getBackground());
                com.setForeground(table.getForeground());
            } else {
                switch (cellValue) {
                    case "Inactivo":
                        // Celda "En uso": fondo rojo, letras blancas
                        com.setBackground(Color.RED);
                        com.setForeground(Color.WHITE);
                        break;
                    case "Activo":
                        // Celda "Ok": fondo verde, letras negras
                        com.setBackground(Color.GREEN);
                        com.setForeground(Color.BLACK);
                        break;
                    default:
                        // Otros valores: restaurar colores predeterminados
                        com.setBackground(table.getBackground());
                        com.setForeground(table.getForeground());
                        break;
                }
            }
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        return (column == 6 || column == 4) ? com : action;
    }
}
