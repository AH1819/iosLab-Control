package View;

import java.awt.event.MouseEvent;
import javax.swing.JFormattedTextField;
import raven.calendar.model.ModelDate;

/**
 *
 * @author Hitler
 */
public class CalendarModal extends javax.swing.JDialog {
    
    public CalendarModal(java.awt.Frame parent, boolean modal, JFormattedTextField fecha) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(this);
        if (!fecha.getText().isEmpty()) {
            //
        }
        calendar.addCalendarSelectedListener((MouseEvent evt, ModelDate date) -> {
            java.sql.Date sqlDate = new java.sql.Date(date.toDate().getTime());
            fecha.setText(sqlDate.toString());
            this.dispose();
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        calendar = new raven.calendar.Calendar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Selecciona la fecha");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.calendar.Calendar calendar;
    // End of variables declaration//GEN-END:variables

}
