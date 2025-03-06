/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import entity.Dich_Vu;
import entity.loai_DV;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import repository.Loai_DV_Responsory;
import repository.dichVuTietResponsory;
import response.DichVuRespon;


/**
 *
 * @author default
 */
public class DichVuShow extends javax.swing.JFrame {

    private dichVuTietResponsory dvRes;
    String strHinhAnh = null;
    private Loai_DV_Responsory dvbxres;

    private DefaultComboBoxModel modelCombox = new DefaultComboBoxModel();
    

    public DichVuShow(int index, DefaultTableModel model) {
        initComponents();

        dvRes = new dichVuTietResponsory();
        dvbxres = new Loai_DV_Responsory();
        modelCombox = (DefaultComboBoxModel) cboLoaiDV.getModel();
        // ĐOạn này lấy ra 
        int indexSelected = Integer.parseInt(dvRes.getAll().get(index).getId_LoaiDV().toString()) ;
        
//      cboLoaiDV.setSelectedItem(model.getValueAt(index, 1).toString());
        txtMa.setText(model.getValueAt(index, 2) == null ? "" : model.getValueAt(index, 2).toString());
        txtTen.setText(model.getValueAt(index, 3) == null ? "" : model.getValueAt(index, 3).toString());
        txtGia.setText(model.getValueAt(index, 4) == null ? "" : model.getValueAt(index, 4).toString());
        txtThoiGian.setText(model.getValueAt(index, 5) == null ? "" : model.getValueAt(index, 5).toString());
        txtMoTa.setText(model.getValueAt(index, 6) == null ? "" : model.getValueAt(index, 6).toString());
        String v = model.getValueAt(index, 7) == null ? "" : model.getValueAt(index, 7).toString();
        if (v.equalsIgnoreCase("No Avata")) {
            lblAnh.setText("No Avata");
            lblAnh.setIcon(null);
        } else {
            lblAnh.setText("");
            lblAnh.setIcon(new javax.swing.ImageIcon("D:\\Document\\BT_DuAn1\\ChonLichCatToc\\src\\icon\\" + v));
        }
        showcombox();
        cboLoaiDV.setSelectedIndex(indexSelected-1);
    }

//    public void deailTable(int index) {
//        DichVuRespon w = dvRes.getAll().get(index);
//        cboLoaiDV.setSelectedItem(w.getTen_LoaiDV());
//        txtMa.setText(w.getMa_DV());
//        txtGia.setText(String.valueOf(w.getGia_Tien()));
//        txtTen.setText(w.getTen_DV());
//        txtMoTa.setText(w.getMoTa());
//        txtThoiGian.setText(w.getThoiGianDV() + "");
//        lblAnh.setText(w.getAnh());
//
//    }

    public void showcombox() {
        modelCombox.removeAllElements();
        dvbxres.getAll().forEach(cv -> modelCombox.addElement(cv.getTenDV()));
    }
    private DefaultTableModel model;

    public void showDataTableDVCT(List<DichVuRespon> list, DefaultTableModel model) {
        this.model = model;
        this.model.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);//Khoi tao 1 gia tri bat dau bang 1 de tu dong tang
        for (DichVuRespon s : list) {
            String trangThai;

            if (s.getTrangThai() == 1) {
                trangThai = "Hoàn Thành";
            } else {
                trangThai = "Thất Bại";
            }
            model.addRow(new Object[]{index.getAndIncrement(), s.getTen_LoaiDV(), s.getMa_DV(), s.getTen_DV(), s.getGia_Tien(), s.getThoiGianDV(), s.getMoTa(), s.getAnh(), trangThai});
        }
    }

    String linkAnh = "D:\\Document\\BT_DuAn1\\ChonLichCatToc\\src\\icon\\";

    private void taiAnh() {
        ImageIcon icon = new ImageIcon();
        int with = lblAnh.getWidth();
        int hight = lblAnh.getHeight();
        Image anh = icon.getImage().getScaledInstance(with, hight, 0);
        lblAnh.setIcon(new ImageIcon(anh));
    }

    public DichVuRespon getFormData() {
        DichVuRespon dv = new DichVuRespon();
        dv.setMa_DV(txtMa.getText());
        dv.setMoTa(txtMoTa.getText());
        dv.setTen_DV(txtTen.getText());
        dv.setThoiGianDV(Time.valueOf(txtThoiGian.getText()));
        dv.setGia_Tien(Double.parseDouble(txtGia.getText()));
        dv.setAnh(strHinhAnh);
        dv.setTen_LoaiDV(cboLoaiDV.getSelectedItem().toString());
        return dv;
    }

    private Dich_Vu conver(DichVuRespon pon) {
        loai_DV cv = dvbxres.getChuVu(pon.getTen_LoaiDV());
        return Dich_Vu.builder()
                .ma_DV(pon.getMa_DV())
                .ten_DV(pon.getTen_DV())
                .ThoiGianDV(pon.getThoiGianDV())
                .anh(pon.getAnh())
                .moTa(pon.getMoTa())
                .gia_Tien(pon.getGia_Tien())
                .id_LoaiDV(cv.getId())//lấy ra id của chức vụ dựa vào chức vụ
                .build();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtMa = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtGia = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        txtThoiGian = new javax.swing.JTextField();
        cboLoaiDV = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel5.setText("Giá Dịch Vụ");

        jLabel4.setText("Tên Dịch Vụ");

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel7.setText("Mô Tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane1.setViewportView(txtMoTa);

        jLabel3.setText("Chọn Loại Dịch Vụ");

        jLabel6.setText("Thời Gian Của Dịch Vụ");

        jLabel8.setText("Ảnh Dịch Vụ");

        jLabel2.setText("Mã Dịch Vụ");

        lblAnh.setText("Anh");
        lblAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });

        cboLoaiDV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboLoaiDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiDVActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 102, 255));
        jLabel9.setText("Xem Dịch Vụ");
        jLabel9.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboLoaiDV, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel8)
                        .addContainerGap(138, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(160, 160, 160))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel8)
                    .addComponent(cboLoaiDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAnhMouseClicked

        try {
            // TODO add your handling code here:
            JFileChooser com = new JFileChooser("D:\\Document\\BT_DuAn1\\ChonLichCatToc\\src\\icon\\");
            com.showOpenDialog(null);
            File file = com.getSelectedFile();
            Image img = ImageIO.read(file);
            strHinhAnh = file.getName();
            lblAnh.setText("");
            int width = lblAnh.getWidth();
            int height = lblAnh.getHeight();
            lblAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (IOException ex) {
            System.out.println("Error:" + ex.toString());
        }
    }//GEN-LAST:event_lblAnhMouseClicked
    public boolean validateForm() {
        if (txtMa.getText().isEmpty() || txtGia.getText().isEmpty() || txtMoTa.getText().isEmpty() || txtThoiGian.getText().isEmpty() || txtTen.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    private void cboLoaiDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiDVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiDVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboLoaiDV;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JTextField txtGia;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtThoiGian;
    // End of variables declaration//GEN-END:variables
}
