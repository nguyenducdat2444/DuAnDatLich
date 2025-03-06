/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import entity.CongViec;
import entity.nhanVien;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class NhanVienFormThem extends javax.swing.JFrame {

    private DefaultComboBoxModel modelCombox = new DefaultComboBoxModel();
    private TenCongViecResponsory TenCongViecResponsory = new TenCongViecResponsory();
    private NhanVienRepository NhanVienRepository = new NhanVienRepository();
    private int page = 1;
    private int limit = 3;

    /**
     * Creates new form NhanVienFormSgow
     */
    public NhanVienFormThem() {
        initComponents();
        modelCombox = (DefaultComboBoxModel) cboTenCongViec.getModel();

        showcombox();

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
        dv.setMa_NV("NV" + UUID.randomUUID().toString().substring(0, 7));
        dv.setTen_NV(txtTen.getText());
        dv.setEmail(txtEmail.getText());
        dv.setDiaChi(txtDchi.getText());
        dv.setSdt_NV(txtPhoneNumber.getText());
        dv.setGioiTinh(rdoNam.isSelected() ? 1 : 0);
        dv.setNgaySinh(dateChooser.getDate());
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

    public int calculateAge(Date birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Ngày sinh không thể là null");
        }

        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        Calendar today = Calendar.getInstance();

        // Check if birth date is in the future
        if (birth.after(today)) {
            throw new IllegalArgumentException("Ngày sinh không thể trong tương lai");
        }

        int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        // Kiểm tra nếu ngày sinh của khách hàng chưa trôi qua trong năm hiện tại
        if (today.get(Calendar.DAY_OF_YEAR) < birth.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        return age;
    }

    private boolean checkPhoneNumbers(String phoneNumber) {
        // Biểu thức chính quy để kiểm tra số điện thoại
        String regex = "^0\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean checkEmail(String email) {
        // Biểu thức chính quy cho email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean validateForm() {
        if (txtTen.getText().isEmpty() || txtEmail.getText().isEmpty() || txtDchi.getText().isEmpty() || txtPhoneNumber.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean checkGioiTinh() {
        // Return true if one of the radio buttons is selected
        return rdoNam.isSelected() || rdoNu.isSelected();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        cboTenCongViec = new javax.swing.JComboBox<>();
        btnThem = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tên nhân viên:");

        txtTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenActionPerformed(evt);
            }
        });

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Giới tính:");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Email:");

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Địa chỉ :");

        jLabel5.setText("Số Điện Thoại:");

        jLabel8.setText("Ngày Sinh:");

        dateChooser.setDateFormatString("yyyy-MM-dd");

        jLabel9.setText("Tên Công Việc");

        cboTenCongViec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1.png"))); // NOI18N
        jLabel1.setText("         Thêm Nhân Viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(btnThem)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTenCongViec, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rdoNam)
                        .addGap(18, 18, 18)
                        .addComponent(rdoNu))
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDchi, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDchi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cboTenCongViec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        if (validateForm()) {
            Date birthDate = dateChooser.getDate();
            if (birthDate == null) { // Đảm bảo ngày sinh không được null
                JOptionPane.showMessageDialog(this, "Ngày sinh không thể để trống.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Exit if birthDate is null
            }
            int age = calculateAge(birthDate);// Tính tuổi
            if (age < 16) { // Kiểm tra nếu tuổi nhỏ hơn 16
                JOptionPane.showMessageDialog(this, "Khách hàng phải ít nhất 16 tuổi.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Dừng nếu tuổi nhỏ hơn 16
            }

            // Lấy số điện thoại từ text field
            String phoneNumber = txtPhoneNumber.getText().trim();
            System.out.println("Số điện thoại nhập vào: " + phoneNumber);

            // Kiểm tra số điện thoại hợp lệ
            boolean isPhoneNumberValid = checkPhoneNumbers(phoneNumber);
            System.out.println("Kết quả kiểm tra số điện thoại: " + isPhoneNumberValid);

            String email = txtEmail.getText().trim();
            boolean ckEmail = checkEmail(email);
            System.out.println("Kết quả kiểm tra email: " + ckEmail);

            if (!checkGioiTinh()) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn giới tính.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!isPhoneNumberValid) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Phải có 10 số và bắt đầu bằng số 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;//Dừng nếu số điện thoại không hợp lệ
            }
            if (!ckEmail) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ.", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                return; //Dừng nếu Email không hợp lệ
            }
            if (conver(getFormData()) != null) {

                NhanVienRepository.add(conver(getFormData()));
                JOptionPane.showMessageDialog(this, "thêm thanh cong");
                showDataTableNVN(NhanVienRepository.PhanTrang(page, limit), model);

            } else {
                JOptionPane.showMessageDialog(this, "thêm thất bại");
                return;
            }

        } else {
            JOptionPane.showMessageDialog(this, "Bạn chưa nhập đủ thông tin");
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void txtTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboTenCongViec;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
