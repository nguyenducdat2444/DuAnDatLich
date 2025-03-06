package Form;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;

public class QuanLyLich_Form extends JPanel {

    private GranttChartPanel granttChartPanel;
    //private com.toedter.calendar.JDateChooser jDateChooser1;

    public QuanLyLich_Form() {
        initComponents();
        granttChartPanel = new GranttChartPanel();
        
        // Tạo JScrollPane và bọc GranttChartPanel
        JScrollPane scrollPane = new JScrollPane(granttChartPanel);
        
        setLayout(new BorderLayout());
        add(createTopPanel(), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Đặt ngày hiện tại và cập nhật biểu đồ
        Date today = new Date();
        jDateChooser1.setDate(today);
        granttChartPanel.setReservationDate(today);
    }

    private JPanel createTopPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel jLabel1 = new JLabel("Ngày đặt lịch:");
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser1.setPreferredSize(new Dimension(150, 30)); // Điều chỉnh kích thước theo ý muốn

        jDateChooser1.addPropertyChangeListener(evt -> {
            if ("date".equals(evt.getPropertyName())) {
                Date selectedDate = jDateChooser1.getDate();
                if (selectedDate != null) {
                    granttChartPanel.setReservationDate(selectedDate);
                }
            }
        });

        topPanel.add(jLabel1);
        topPanel.add(jDateChooser1);
        return topPanel;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        jLabel1.setText("Ngày đặt bàn");

        jDateChooser1.setMinimumSize(new java.awt.Dimension(100, 30));
        jDateChooser1.setPreferredSize(new java.awt.Dimension(100, 22));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(409, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                .addContainerGap(404, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
