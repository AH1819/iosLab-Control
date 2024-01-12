package Model.Views;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Hitler
 */
public class TableActionCellRenderEQ extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object o, boolean isSelected, boolean hasFocus, int row, int column) {
        Component com = super.getTableCellRendererComponent(table, o, isSelected, hasFocus, row, column);
        PanelActionEQ action = null;
        action = new PanelActionEQ();
        if (!isSelected && row % 2 == 0) {
            action.setBackground(com.getBackground());
        } else {
            action.setBackground(com.getBackground());
        }

        return action;
    }
}
