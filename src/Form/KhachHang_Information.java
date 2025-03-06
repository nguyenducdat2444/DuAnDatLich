/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import entity.Khach_Hang;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import repository.KhachHangRepository;
import response.KhachHangResponse;

/**
 *
 * @author default
 */
public class KhachHang_Information extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();

    private KhachHangRepository repo = new KhachHangRepository();

    private ArrayList<Khach_Hang> lists = new ArrayList<>();

    private int page = 1;
    private int limit = 6;

    /**
     * Creates new form khachHang1
     */
    public KhachHang_Information() {
        initComponents();

        lblpage.setText(this.page + "");
        //this.list = this.repo.getAll();
        FillTable(repo.getPhanTrang(page, limit));
        //FillTable2(repo.LichSuDatLich());
        createDay.setDate(new Date());

    }

    public void FillTable(ArrayList<Khach_Hang> listKH) {
        dtm = (DefaultTableModel) tblKhachHang.getModel();
        dtm.setRowCount(0);

        AtomicInteger index = new AtomicInteger(1);

        // Đảo ngược danh sách nếu muốn thêm mới nhất lên đầu bảng
        //Collections.reverse(listKH);
        listKH.forEach(s -> dtm.addRow(new Object[]{
            index.getAndIncrement(),
            s.getMa_KH(),
            s.getTen_KH(),
            (s.getGioiTinh() == 1) ? "Nam" : "Nữ",
            s.getNgaySinh(),
            s.getSdt_KH(),
            s.getDiaChi(),
            s.getEmail(),
            s.getNgayTao()
        }));
    }

    public void FillTabletoForm(int i) {
        //txtOrdinalNumber.setText(tblKhachhang.getValueAt(i, 0).toString());
        txtMa.setText(tblKhachHang.getValueAt(i, 1).toString());
        txtName.setText(tblKhachHang.getValueAt(i, 2).toString());
        String gioiTinh = tblKhachHang.getValueAt(i, 3).toString();
        if (gioiTinh.equalsIgnoreCase("Nam")) {
            rdoNam.setSelected(true);
        } else {
            rdoNu.setSelected(true);
        }
        dateChooser.setDate((Date) tblKhachHang.getValueAt(i, 4));
        txtPhoneNumber.setText(tblKhachHang.getValueAt(i, 5).toString());
        txtAddress.setText(tblKhachHang.getValueAt(i, 6).toString());
        txtEmail.setText(tblKhachHang.getValueAt(i, 7).toString());
        createDay.setDate((Date) tblKhachHang.getValueAt(i, 8));
    }

    private Khach_Hang ReadForm() {

        String maKH = txtMa.getText().trim().toString();
        String tenKH = txtName.getText().trim().toString();
        Integer gioiTinh = rdoNam.isSelected() ? 1 : 0;
        Date ngaySinh = dateChooser.getDate();
        String soDienThoai = txtPhoneNumber.getText().trim().toString();
        String diaChi = txtAddress.getText().trim().toString();
        String email = txtEmail.getText().trim().toString();
        Date ngayTao = new Date();

        return Khach_Hang.builder()
                .ma_KH("KH" + UUID.randomUUID().toString().substring(0, 7))
                .ten_KH(tenKH)
                .gioiTinh(gioiTinh)
                .ngaySinh(ngaySinh)
                .diaChi(diaChi)
                .sdt_KH(soDienThoai)
                .email(email)
                .ngayTao(ngayTao)
                .build();

    }

//    boolean checkTrung(String maKH) {
//        ArrayList<Khach_Hang> lists = repo.getAll();
//        for (Khach_Hang kh : lists) {
//            if (kh.getMa_KH().equals(maKH)) {
//                return true;
//            }
//        }
//        return false;
//    }
    boolean checkTrong() {
        if (txtName.getText().trim().isEmpty() || txtPhoneNumber.getText().trim().isEmpty() || txtEmail.getText().trim().isEmpty() || txtAddress.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không được để trống");
            return false;
        }
        return true;
    }

//    public void FillTable2(ArrayList<KhachHangResponse> listkh) {
//        dtm = (DefaultTableModel) tblLichSuDat.getModel();
//        dtm.setRowCount(0);
//
//        AtomicInteger index = new AtomicInteger(1);
//
//        listkh.forEach(s -> dtm.addRow(new Object[]{
//            index.getAndIncrement(),
//            s.getTen_KH(),
//            s.getTen_DV(),
//            s.getNgayHen(),
//            s.getGioHen(),
//            s.getTien_Coc(),
//            (s.getTrangThai() == 1) ? "Đang chờ"
//            : (s.getTrangThai() == 2) ? "Đã hủy" : "Đã hoàn thành",
//            s.getGhiChu()
//
//        }));
//    }
    public int calculateAge(Date birthDate) {
        if (birthDate == null) {
            throw new IllegalArgumentException("Ngày sinh không thể là null");
        }

        Calendar birth = Calendar.getInstance();
        birth.setTime(birthDate);

        Calendar today = Calendar.getInstance();

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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtMa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        btnAdd = new javax.swing.JButton();
        btnRemove = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnXuat = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        createDay = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        txtSearch = new javax.swing.JTextField();
        btnSearch = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        cboGioiTinh = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtMinAge = new javax.swing.JTextField();
        txtMaxAge = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblpage = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1500, 900));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("QUẢN LÝ KHÁCH HÀNG");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtMa.setEditable(false);
        txtMa.setText("#########");

        jLabel1.setText("Mã");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên");

        jLabel8.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setSelected(true);
        rdoNam.setText("Nam");
        rdoNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoNamActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel5.setText("Địa chỉ");

        jLabel4.setText("Số điện thoại");

        jLabel9.setText("Ngày sinh");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane3.setViewportView(txtAddress);

        jLabel6.setText("Email");

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add3.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xoa2.png"))); // NOI18N
        btnRemove.setText("Xóa");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/update3.png"))); // NOI18N
        btnUpdate.setText("Cập nhật");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnClear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/circular.png"))); // NOI18N
        btnClear.setText("Làm mới");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xls.png"))); // NOI18N
        btnXuat.setText("Xuất Excel");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        jLabel13.setText("Ngày tạo");

        createDay.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(createDay, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdoNam)
                        .addGap(69, 69, 69)
                        .addComponent(rdoNu))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXuat)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(createDay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdoNam)
                    .addComponent(rdoNu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemove, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm theo tên, số điện thoại", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Source Sans Pro Semibold", 0, 18))); // NOI18N
        jPanel2.setToolTipText("");
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtSearch.setName("Mời nhập tên cần tìm"); // NOI18N
        txtSearch.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSearchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSearchFocusLost(evt);
            }
        });
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(204, 204, 204));
        btnSearch.setForeground(new java.awt.Color(255, 102, 102));
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnSearch.setText("Tìm kiếm");
        btnSearch.setToolTipText("");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Lọc theo giới tính");

        cboGioiTinh.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));
        cboGioiTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGioiTinhActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Lọc theo khoảng tuổi");

        txtMinAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMinAgeActionPerformed(evt);
            }
        });

        jLabel11.setText("Tuổi nhỏ nhất");

        jLabel12.setText("Tuổi lớn nhất");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtMinAge, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(24, 24, 24)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(txtMaxAge, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtSearch))
                        .addGap(47, 47, 47))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(69, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSearch)
                        .addGap(103, 103, 103))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaxAge, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinAge, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Giới tính", "Ngày sinh", "Số điện thoại", "Địa chỉ", "Email", "Ngày tạo"
            }
        ));
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1184, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1))
        );

        jTabbedPane1.addTab("Thông tin khách hàng", jPanel4);

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBack.setText("<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lblpage.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblpage.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(587, 587, 587))
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblpage, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(247, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblpage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // Kiểm tra xem các trường có rỗng không
        if (!checkTrong()) {
            return; // Dừng nếu có trường rỗng
        }

        // Đọc thông tin khách hàng từ form
        Khach_Hang kh = this.ReadForm();

        // Kiểm tra nếu thông tin khách hàng không null
        if (kh != null) {
            Date birthDate = dateChooser.getDate();
            int age = calculateAge(birthDate);// Tính tuổi
            // Kiểm tra nếu tuổi nhỏ hơn 16
            if (age < 16) {
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
            if (!isPhoneNumberValid) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Phải có 10 số và bắt đầu bằng số 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!ckEmail) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

//            if (ckEmail) {
//                JOptionPane.showMessageDialog(this, "Email hợp lệ.", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
//            if (isPhoneNumberValid) {
//                JOptionPane.showMessageDialog(this, "Số điện thoại hợp lệ.", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
            // Thêm khách hàng vào repository
            if (repo.Add(kh) > 0) {
                JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                // Làm mới bảng sau khi thêm thành công
                this.FillTable(repo.getPhanTrang(page, limit));
            } else {
                JOptionPane.showMessageDialog(this, "Thêm không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
//            } else {
//                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Phải có 10 số và bắt đầu bằng số 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
//            } else {
//               JOptionPane.showMessageDialog(this, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
//            }
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (!checkTrong()) {
            return;
        }

        Khach_Hang kh = this.ReadForm();
        int index = tblKhachHang.getSelectedRow();
        Khach_Hang existingKh = repo.getAll().get(index);

        // Log thông tin để kiểm tra
        System.out.println("Selected Row Index: " + index);
        System.out.println("Selected Khach_Hang ID: " + existingKh.getId());
        System.out.println("New Khach_Hang Info: " + kh.getTen_KH() + ", " + kh.getGioiTinh() + ", " + kh.getNgaySinh());

        // Kiểm tra nếu thông tin khách hàng không null
        if (kh != null) {

            // Lấy số điện thoại từ text field
            String phoneNumber = txtPhoneNumber.getText().trim();
            System.out.println("Số điện thoại nhập vào: " + phoneNumber);

            // Kiểm tra số điện thoại hợp lệ
            boolean isPhoneNumberValid = checkPhoneNumbers(phoneNumber);
            System.out.println("Kết quả kiểm tra số điện thoại: " + isPhoneNumberValid);

            // Lấy email từ text field và kiểm tra
            String email = txtEmail.getText().trim();
            boolean ckEmail = checkEmail(email);
            System.out.println("Kết quả kiểm tra email: " + ckEmail);

            // Nếu số điện thoại không hợp lệ
            if (!isPhoneNumberValid) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ. Phải có 10 số và bắt đầu bằng số 0.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Nếu email không hợp lệ
            if (!ckEmail) {
                JOptionPane.showMessageDialog(this, "Email không hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Nếu cả số điện thoại và email đều hợp lệ
            if (repo.Update(kh, existingKh.getId()) > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                // Làm mới bảng sau khi thêm thành công
                this.FillTable(repo.getPhanTrang(page, limit));
            } else {
                JOptionPane.showMessageDialog(this, "Sửa không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        //        int index = tblKhachhang.getSelectedRow();
        //
        //        KhachHang kh = repo.getAll().get(index);
        //        repo.Remove(kh.getId());
        //
        //        showDataTable(repo.getAll());

        int index = tblKhachHang.getSelectedRow();
        Khach_Hang existingKh = repo.getAll().get(index);
        if (this.ReadForm() != null) {
            if (repo.Remove(existingKh.getId()) > 0) {

                JOptionPane.showMessageDialog(this, "Remove thành công");
                this.FillTable(repo.getPhanTrang(page, limit));

            } else {
                JOptionPane.showMessageDialog(this, "Remove không thành công");
            }
        }
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void rdoNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoNamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoNamActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        int i = tblKhachHang.getSelectedRow();

        this.FillTabletoForm(i);

//        tblKhachHang.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 1) {
//                    int row = tblKhachHang.getSelectedRow();
//                    showThongTinChiTiet(row);
//                }
//            }
//        });
//
//        TableModel model = tblKhachHang.getModel();
//        String maKH = model.getValueAt(i, 1).toString();
//        String tenKH = model.getValueAt(i, 2).toString();
//        String sdtKH = model.getValueAt(i, 3).toString();
////        Date ngayHen = model.get
////        Time gioHen 
//
//        jtRowData.setVisible(true);
//        jtRowData.pack();
//        jtRowData.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        jtRowData.txtMaKH.setText(maKH);
//        jtRowData.txtTenKH.setText(tenKH);

    }//GEN-LAST:event_tblKhachHangMouseClicked

//    private void showThongTinChiTiet(int row) {
//        if (row >= 0) {
//            if (row < repo.getAll().size()) {
//                Khach_Hang khachHang = repo.getAll().get(row);
//                DefaultTableModel model = (DefaultTableModel) tblLichSuDat.getModel();
//                model.setRowCount(0);
////                model.addRow(new Object[]{"Mã", khachHang.getMa()});
////                model.addRow(new Object[]{"Tên", khachHang.getTen()});
//                // ... thêm các dòng khác cho thông tin chi tiết
//            }
//        }
//    }

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        try {
            String selectedGender = (String) cboGioiTinh.getSelectedItem();
            Integer gioiTinh;
            if ("Nam".equals(selectedGender)) {
                gioiTinh = 1; // 1 cho Nam
            } else if ("Nữ".equals(selectedGender)) {
                gioiTinh = 0; // 0 cho Nữ
            } else {
                gioiTinh = null; // Hoặc bạn có thể để một giá trị mặc định nếu không có lựa chọn
            }

            int tuoiBatDau = 0;
            int tuoiKetThuc = 0;
            boolean hasAgeFilter = false;
            // Kiểm tra và phân tích tuổi nếu ô không trống
            String minAgeText = txtMinAge.getText().trim();
            String maxAgeText = txtMaxAge.getText().trim();
            if (!minAgeText.isEmpty() && !maxAgeText.isEmpty()) {
                try {
                    tuoiBatDau = Integer.parseInt(minAgeText);
                    tuoiKetThuc = Integer.parseInt(maxAgeText);

                    // Kiểm tra nếu tuổi kết thúc nhỏ hơn tuổi bắt đầu
                    if (tuoiKetThuc < tuoiBatDau) {
                        tuoiKetThuc = tuoiBatDau;
                        txtMaxAge.setText(String.valueOf(tuoiKetThuc));
                    }

                    // Kiểm tra tuổi kết thúc không thể nhỏ hơn tuổi bắt đầu
                    if (tuoiKetThuc < tuoiBatDau) {
                        JOptionPane.showMessageDialog(this, "Tuổi kết thúc không thể nhỏ hơn tuổi bắt đầu.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                        return; // Dừng thực hiện nếu có lỗi
                    }

                    hasAgeFilter = true; // Đã có bộ lọc độ tuổi
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Vui lòng nhập tuổi hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                    return; // Dừng thực hiện nếu có lỗi
                }
            }

            ArrayList<Khach_Hang> list = repo.findCustomerByAll(tuoiBatDau, tuoiKetThuc, gioiTinh, txtSearch.getText());
            FillTable(list);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi. Vui lòng thử lại sau.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        txtAddress.setText("");
        txtEmail.setText("");
        txtMa.setText("");
        txtName.setText("");
        txtPhoneNumber.setText("");
    }//GEN-LAST:event_btnClearActionPerformed

    private void cboGioiTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGioiTinhActionPerformed
//        // TODO add your handling code here:
//        String gioiTinh = (String) cboGioiTinh.getSelectedItem();
//        Boolean isMale = gioiTinh.equals("Nam"); // Giả sử `true` là Nam, `false` là Nữ
//        ArrayList<Khach_Hang> list = repo.findCustomerBySex(isMale);
//        FillTable(list);
    }//GEN-LAST:event_cboGioiTinhActionPerformed

    private void txtMinAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMinAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMinAgeActionPerformed

    private void txtSearchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusGained

    }//GEN-LAST:event_txtSearchFocusGained

    private void txtSearchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSearchFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchFocusLost

    public void openFile(String flie) {
        try {
            File path = new File(flie);
            Desktop.getDesktop().open(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btnXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXuatActionPerformed
        try {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            File saveFile = jFileChooser.getSelectedFile();
            if (saveFile != null) {
                saveFile = new File(saveFile.toString() + ".xlsx");
                Workbook wb = new XSSFWorkbook();
                Sheet sheet = (Sheet) wb.createSheet("customer");
                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblKhachHang.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblKhachHang.getColumnName(i));
                }

                for (int i = 0; i < tblKhachHang.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < tblKhachHang.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        if (tblKhachHang.getValueAt(i, j) != null) {
                            cell.setCellValue(tblKhachHang.getValueAt(i, j).toString());
                        }
                    }
                }

                FileOutputStream out = new FileOutputStream(new File(saveFile.toString()));
                wb.write(out);
                wb.close();
                out.close();
                openFile(saveFile.toString());

            } else {
                JOptionPane.showMessageDialog(null, " Xuất file exle thất bại");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }//GEN-LAST:event_btnXuatActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        this.page++;
        this.lblpage.setText(this.page + "");
        FillTable(repo.getPhanTrang(page, limit));
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.page--;
        this.lblpage.setText(this.page + "");
        if (this.page == 1) {
            this.btnBack.setEnabled(false);
        } else {
            this.btnBack.setEnabled(true);
        }
        FillTable(repo.getPhanTrang(page, limit));

    }//GEN-LAST:event_btnBackActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnRemove;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnXuat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboGioiTinh;
    private com.toedter.calendar.JDateChooser createDay;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblpage;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaxAge;
    private javax.swing.JTextField txtMinAge;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
