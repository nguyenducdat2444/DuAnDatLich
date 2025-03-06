/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import entity.CongViec;
import entity.Dich_Vu;
import entity.nhanVien;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import response.nhanVienResponse;
import repository.NhanVienRepository;
import repository.TenCongViecResponsory;

/**
 *
 * @author default
 */
public class NhanVienFormUpdate extends javax.swing.JFrame {

    private DefaultComboBoxModel modelCombox = new DefaultComboBoxModel();
    private TenCongViecResponsory TenCongViecResponsory = new TenCongViecResponsory();
    private NhanVienRepository NhanVienRepository = new NhanVienRepository();
    private int page = 1;
    private int limit = 3;
    /**
     * Creates new form NhanVienFormSgow
     */
    public NhanVienFormUpdate(int index, DefaultTableModel model) {
        initComponents();
        modelCombox = (DefaultComboBoxModel) cboTenCongViec.getModel();
        txtMa.setText(model.getValueAt(index, 1).toString());
        txtTen.setText(model.getValueAt(index, 2).toString());
        String gt = model.getValueAt(index, 3).toString();
        if (gt.equals("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        txtEmail.setText(model.getValueAt(index, 4).toString());
        txtDchi.setText(model.getValueAt(index, 5).toString());
        txtSDT.setText(model.getValueAt(index, 6).toString());
        txtNgay.setDate((Date) model.getValueAt(index, 7));
        int indexSelected = Integer.parseInt(NhanVienRepository.getAll().get(index).getId_CongViec().toString());
        showcombox();
        cboTenCongViec.setSelectedIndex(indexSelected - 1);

    }

    public void showcombox() {
        modelCombox.removeAllElements();
        TenCongViecResponsory.getAll().forEach(cv -> modelCombox.addElement(cv.getTenCV()));
    }
    private DefaultTableModel model;

    public void showDataTableNVN(List<nhanVienResponse> list, DefaultTableModel model) {
        this.model = model;
        this.model.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);//Khoi tao 1 gia tri bat dau bang 1 de tu dong tang
        for (nhanVienResponse s : list) {
            String trangThai;
            String GioiTinh;
            if (s.getGioiTinh() == 1) {
                GioiTinh = "Nam";
            } else {
                GioiTinh = "Nữ";
            }
            if (s.getTrangThai() == 1) {
                trangThai = "Đi Làm";
            } else {
                trangThai = "Nghỉ Việc";
            }
            model.addRow(new Object[]{index.getAndIncrement(), s.getMa_NV(), s.getTen_NV(), GioiTinh, s.getEmail(), s.getDiaChi(), s.getSdt_NV(), s.getNgaySinh(), s.getTenCV(), trangThai});
        }
    }

    public nhanVienResponse getFormData() {
        nhanVienResponse dv = new nhanVienResponse();
        dv.setMa_NV(txtMa.getText());
        dv.setTen_NV(txtTen.getText());
        dv.setEmail(txtEmail.getText());
        dv.setDiaChi(txtDchi.getText());
        dv.setSdt_NV(txtSDT.getText());
        dv.setGioiTinh(rdoNam.isSelected() ? 1 : 0);
        dv.setNgaySinh(txtNgay.getDate());
        dv.setTenCV(cboTenCongViec.getSelectedItem().toString());
        return dv;
    }

    private nhanVien conver(nhanVienResponse pon) {
        CongViec cv = TenCongViecResponsory.getChuVu(pon.getTenCV());
        return nhanVien.builder()
                .ma_NV(pon.getMa_NV())
                .ten_NV(pon.getTen_NV())
                .email(pon.getEmail())
                .diaChi(pon.getDiaChi())
                .ngaySinh(pon.getNgaySinh())
                .sdt_NV(pon.getSdt_NV())
                .gioiTinh(pon.getGioiTinh())
                .id_CongViec(cv.getId())
                .build();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel3 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDchi = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtNgay = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        cboTenCongViec = new javax.swing.JComboBox<>();
        btnSua = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Mã Nhân Viên:");

        txtMa.setEditable(false);

        jLabel2.setText("Tên Nhân Viên:");

        jLabel6.setText("Giới Tính:");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel4.setText("Email:");

        jLabel7.setText("Địa Chỉ :");

        jLabel5.setText("Số Điện Thoại:");

        jLabel8.setText("Ngày Sinh:");

        txtNgay.setDateFormatString("yyyy-MM-dd");

        jLabel9.setText("Tên Công Việc");

        cboTenCongViec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1.png"))); // NOI18N
        jLabel1.setText("        Cập nhật Nhân Viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSua)
                .addGap(204, 204, 204))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rdoNam)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNu))
                            .addComponent(txtTen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(txtDchi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(cboTenCongViec, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(7, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDchi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboTenCongViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public boolean validateForm() {
        if (txtMa.getText().isEmpty() || txtTen.getText().isEmpty() || txtEmail.getText().isEmpty() || txtDchi.getText().isEmpty() || txtSDT.getText().isEmpty()) {
            return false;
        }
        return true;
    }
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        nhanVien sv = conver(getFormData());
        if (validateForm()) {
            try {
                if (NhanVienRepository.up(sv) > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                    showDataTableNVN(NhanVienRepository.PhanTrang(page, limit), model);
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa Thất Bại");
                    showDataTableNVN(NhanVienRepository.PhanTrang(page, limit), model);
                }
            } catch (Exception e) {

            }

        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập đủ thông tin");
        }


    }//GEN-LAST:event_btnSuaActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTenCongViec;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTextField txtDchi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private com.toedter.calendar.JDateChooser txtNgay;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
