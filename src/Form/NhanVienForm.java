/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form;

import entity.Ca_Lam;
import entity.Giao_Ca;
import entity.nhanVien;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import response.CaLamResponse;
import response.nhanVienResponse;
import repository.CaLamRespository;
import repository.NhanVienRepository;
import repository.TenCongViecResponsory;

public class NhanVienForm extends javax.swing.JPanel {

    private DefaultTableModel model = new DefaultTableModel();
    private DefaultTableModel modelCaLam = new DefaultTableModel();
    private DefaultComboBoxModel modelComboxCaLam = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelComboxTenNhanVien = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelCombox = new DefaultComboBoxModel();
    private TenCongViecResponsory TenCongViecResponsory = new TenCongViecResponsory();
    private CaLamRespository CaLamRespository = new CaLamRespository();
    private NhanVienRepository NhanVienRepository = new NhanVienRepository();
    private int page = 1;
    private int limit = 3;

    public NhanVienForm() {
        initComponents();
        model = (DefaultTableModel) tblNhanVien.getModel();
        modelCaLam = (DefaultTableModel) tblCaLam.getModel();
        this.lblPage.setText(this.page + "");
        showDataTableNV(NhanVienRepository.PhanTrang(page, limit));
        modelCombox = (DefaultComboBoxModel) cboNhanVien.getModel();
        modelComboxTenNhanVien = (DefaultComboBoxModel) cboTenNV.getModel();
        modelComboxCaLam = (DefaultComboBoxModel) cboCalam.getModel();
        showcombox();
        showcomboxNV();
        showcomboxCaLam();
        showDataTableCalam(CaLamRespository.getAll());
    }

    public void showcombox() {
        modelCombox.removeAllElements();
        TenCongViecResponsory.getAll().forEach(cv -> modelCombox.addElement(cv.getTenCV()));
    }

    public void showcomboxNV() {
        modelComboxTenNhanVien.removeAllElements();
        NhanVienRepository.getNhanVien().forEach(cv -> modelComboxTenNhanVien.addElement(cv.getTen_NV()));
    }

    public void showcomboxCaLam() {
        modelComboxCaLam.removeAllElements();
        CaLamRespository.getCaLam().forEach(cv -> modelComboxCaLam.addElement(cv.getTenCaLam()));
    }

        private void showDataTableNV(List<nhanVienResponse> list) {
            model.setRowCount(0);
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

    private void showDataTableCalam(List<CaLamResponse> list) {
        modelCaLam.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);//Khoi tao 1 gia tri bat dau bang 1 de tu dong tang
        for (CaLamResponse v : list) {

            modelCaLam.addRow(new Object[]{index.getAndIncrement(), v.getTen_NV(), v.getTenCaLam(), v.getNgayCaLam(), v.getTien_Ca_Lam(), v.getGhiChu()});
        }
    }

    public void showDeil(int index) {
        cboTenNV.setSelectedItem(modelCaLam.getValueAt(index, 1).toString());
        cboCalam.setSelectedItem(modelCaLam.getValueAt(index, 2).toString());
        txtNgayCaLam.setDate((Date) modelCaLam.getValueAt(index, 3));
        //txtTienCaLam.setText(modelCaLam.getValueAt(index, 4).toString());
        txtGhiChu.setText(modelCaLam.getValueAt(index, 5).toString());

    }

    public CaLamResponse getFormData() {
        CaLamResponse dv = new CaLamResponse();
        dv.setTen_NV(cboTenNV.getSelectedItem().toString());
        dv.setTenCaLam(cboCalam.getSelectedItem().toString());
        dv.setNgayCaLam(txtNgayCaLam.getDate());
        dv.setGhiChu(txtGhiChu.getText());
        //dv.setTien_Ca_Lam(Double.parseDouble(txtTienCaLam.getText()));
        return dv;
    }

    private Giao_Ca conver(CaLamResponse pon) {
        nhanVien nv = NhanVienRepository.getChuVuNhanVien(pon.getTen_NV());
        Ca_Lam cl = CaLamRespository.getChuVu(pon.getTenCaLam());
        return Giao_Ca.builder()
                .id_NV_Nhan(nv.getId())
                .id_CaLam(cl.getId())
                .ngayCaLam(pon.getNgayCaLam())
                //.tien_Ca_Lam(pon.getTien_Ca_Lam())
                .ghiChu(pon.getGhiChu())
                .build();
    }

//    public boolean validateForm() {
//        if (txtGhiChu.getText().isEmpty() || txtTienCaLam.getText().isEmpty()) {
//            return false;
//        } else {
//
//            try {
//                double av = Double.parseDouble(txtTienCaLam.getText());
//                if (av < 0) {
//                    JOptionPane.showMessageDialog(this, "Bạn không được nhập giá âm");
//                    return false;
//                }
//            } catch (Exception e) {
//                return false;
//            }
//
//        }
//        return true;
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnXem = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnXuat = new javax.swing.JButton();
        panel1 = new java.awt.Panel();
        btnSearch = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        cboNhanVien = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMinAge = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtMaxAge = new javax.swing.JTextField();
        btnLoadTable = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        lblPage = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCaLam = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboTenNV = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        cboCalam = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNgayCaLam = new com.toedter.calendar.JDateChooser();
        btnNewCaLam = new javax.swing.JToggleButton();
        btnSuaCaLam = new javax.swing.JToggleButton();
        btnThemCaLam = new javax.swing.JToggleButton();
        btnXoaCaLam = new javax.swing.JToggleButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtNgayCaLam1 = new com.toedter.calendar.JDateChooser();
        txtNgayCaLam2 = new com.toedter.calendar.JDateChooser();
        btnTim = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Email", "Địa Chỉ", "Số Điện Thoại", "Ngày Sinh", "Tên Công Việc", "Trạng Thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/update3.png"))); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xoa2.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnXem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/view-files.png"))); // NOI18N
        btnXem.setText("Xem");
        btnXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemActionPerformed(evt);
            }
        });

        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add3.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xls.png"))); // NOI18N
        btnXuat.setText("Xuất Excel");
        btnXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXuatActionPerformed(evt);
            }
        });

        panel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnSearch.setBackground(new java.awt.Color(204, 204, 204));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        jLabel7.setText("Tìm kiếm( Mã NV, Tên NV, SĐT, Email):");

        cboNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel14.setText("Theo tên công việc:");

        jLabel8.setText("Tìm theo tuổi:");

        jLabel9.setText("Tuổi nhỏ nhất");

        jLabel10.setText("Tuổi lớn nhất");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cboNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtSearch)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMinAge, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaxAge, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addComponent(btnSearch)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaxAge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinAge, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSearch)
                .addGap(25, 25, 25))
        );

        btnLoadTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnLoadTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/spreadsheet.png"))); // NOI18N
        btnLoadTable.setText("Bảng mặc định");
        btnLoadTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(84, 84, 84)
                        .addComponent(btnXuat)
                        .addGap(250, 250, 250))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(btnXem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)
                                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(279, 279, 279)
                                .addComponent(btnLoadTable)))
                        .addGap(39, 39, 39)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 164, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnXem, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(btnLoadTable, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))))
        );

        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack.setText("<");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        lblPage.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPage.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPage)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack)
                    .addComponent(lblPage, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản Lí Nhân Viên", jPanel1);

        tblCaLam.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên Nhân Viên", "Ca Làm", "Ngày Ca Làm", "Tiền Ca Làm", "Ghi Chú"
            }
        ));
        tblCaLam.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCaLamMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblCaLam);

        jPanel4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Tên Nhân Viên:");

        cboTenNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Ca Làm:");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        cboCalam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Ghi Chú:");

        jLabel6.setText("Ngày Ca Làm:");

        txtNgayCaLam.setDateFormatString("yyyy-MM-dd");

        btnNewCaLam.setBackground(new java.awt.Color(204, 204, 204));
        btnNewCaLam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/circular.png"))); // NOI18N
        btnNewCaLam.setText("Làm mới form");
        btnNewCaLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCaLamActionPerformed(evt);
            }
        });

        btnSuaCaLam.setBackground(new java.awt.Color(204, 204, 204));
        btnSuaCaLam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/update3.png"))); // NOI18N
        btnSuaCaLam.setText("Cập nhật");
        btnSuaCaLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaCaLamActionPerformed(evt);
            }
        });

        btnThemCaLam.setBackground(new java.awt.Color(204, 204, 204));
        btnThemCaLam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add3.png"))); // NOI18N
        btnThemCaLam.setText("Thêm");
        btnThemCaLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemCaLamActionPerformed(evt);
            }
        });

        btnXoaCaLam.setBackground(new java.awt.Color(204, 204, 204));
        btnXoaCaLam.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xoa2.png"))); // NOI18N
        btnXoaCaLam.setText("Xóa");
        btnXoaCaLam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaCaLamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboCalam, 0, 300, Short.MAX_VALUE)
                    .addComponent(txtGhiChu))
                .addGap(53, 53, 53)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThemCaLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnNewCaLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSuaCaLam, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(btnXoaCaLam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(125, 125, 125))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cboTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(cboCalam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtNgayCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNewCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSuaCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThemCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoaCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel11.setText("Tìm Kiếm Tên Nhân Viên:");

        jLabel12.setText("Ngày Ca Làm:");

        txtNgayCaLam1.setDateFormatString("yyyy-MM-dd");

        txtNgayCaLam2.setDateFormatString("yyyy-MM-dd");

        btnTim.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        btnTim.setText("Tìm kiếm");
        btnTim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtNgayCaLam1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNgayCaLam2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 246, Short.MAX_VALUE)
                .addComponent(btnTim, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnTim)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayCaLam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayCaLam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phân Công", jPanel2);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 255));
        jLabel1.setText("Quản Lý Nhân Viên");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(548, 548, 548))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemActionPerformed
        // TODO add your handling code here:
        int id = tblNhanVien.getSelectedRow();
        if (id < 0) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn dòng để xem thông tin");
        } else {
            NhanVienFormShow v = new NhanVienFormShow(id, model);
            v.setVisible(true);
            v.pack();
            v.setLocationRelativeTo(null);
            v.showDataTableNVN(NhanVienRepository.PhanTrang(page, limit), model);
            v.setDefaultCloseOperation(v.DISPOSE_ON_CLOSE);
        }


    }//GEN-LAST:event_btnXemActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        NhanVienFormThem g = new NhanVienFormThem();
        g.setVisible(true);
        g.pack();
        g.setLocationRelativeTo(null);
        g.showDataTableNVN(NhanVienRepository.PhanTrang(page, limit), model);
        g.setDefaultCloseOperation(g.DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // Get the index of the selected row
        int index = tblNhanVien.getSelectedRow();

        // Check if a valid row is selected
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn dòng cần xóa");
            return; // Exit if no row is selected
        }

        // Retrieve the selected employee
        nhanVienResponse selectedNhanVien = NhanVienRepository.getAll().get(index);

        // Confirm the deletion action
        int confirmation = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa hay không");

        // Proceed with deletion if confirmed
        if (confirmation == JOptionPane.YES_OPTION) {
            NhanVienRepository.xoa(selectedNhanVien.getId());
            showDataTableNV(NhanVienRepository.PhanTrang(page, limit));
            JOptionPane.showMessageDialog(this, "Đã xóa thành công");
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int id = tblNhanVien.getSelectedRow();
        if (id < 0) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn dòng cần sửa");
        } else {
            NhanVienFormUpdate v = new NhanVienFormUpdate(id, model);
            v.setVisible(true);
            v.pack();
            v.setLocationRelativeTo(null);
            v.showDataTableNVN(NhanVienRepository.PhanTrang(page, limit), model);
            v.setDefaultCloseOperation(v.DISPOSE_ON_CLOSE);
        }


    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThemCaLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemCaLamActionPerformed
        //if (validateForm()) {

        if (conver(getFormData()) != null) {

            CaLamRespository.add(conver(getFormData()));
            JOptionPane.showMessageDialog(this, "them thanh cong");

            showDataTableCalam(CaLamRespository.getAll());

        } else {
            JOptionPane.showMessageDialog(this, "them thất bại");
            return;
        }

        //} else {
        //   JOptionPane.showMessageDialog(this, "Bạn chưa nhập đủ thông tin");
        //}

    }//GEN-LAST:event_btnThemCaLamActionPerformed

    private void tblCaLamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCaLamMouseClicked
        // TODO add your handling code here:
        int id = tblCaLam.getSelectedRow();
        showDeil(id);
    }//GEN-LAST:event_tblCaLamMouseClicked

    private void btnSuaCaLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaCaLamActionPerformed
        // TODO add your handling code here:
        int index = tblCaLam.getSelectedRow();
        CaLamResponse cl = CaLamRespository.getAll().get(index);

        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn dòng cần sửa");
        } else {
            if (conver(getFormData()) != null) {

                CaLamRespository.update(cl.getId(), conver(getFormData()));
                JOptionPane.showMessageDialog(this, "Sửa Thành  Công");

                showDataTableCalam(CaLamRespository.getAll());

            } else {
                JOptionPane.showMessageDialog(this, "Sửa Thất Bại");
                return;
            }

        }
    }//GEN-LAST:event_btnSuaCaLamActionPerformed

    private void btnXoaCaLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaCaLamActionPerformed
        // TODO add your handling code here:
        int index = tblCaLam.getSelectedRow();
        CaLamResponse cl = CaLamRespository.getAll().get(index);
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn dòng cần xóa");
        } else {
            int hoi = JOptionPane.showConfirmDialog(this, " bạn có muốn xóa hay không");
            if (hoi != JOptionPane.YES_OPTION) {
                return;
            } else {
                CaLamRespository.xoa(cl.getId());//updte trang thai => pk
                showDataTableCalam(CaLamRespository.getAll());
                JOptionPane.showMessageDialog(this, "đã xóa thành công");
            }
        }
    }//GEN-LAST:event_btnXoaCaLamActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.page--;
        this.lblPage.setText(this.page + "");
        showDataTableNV(NhanVienRepository.PhanTrang(page, limit));
        if (this.page == 1) {
            this.btnBack.setEnabled(false);
        } else {
            this.btnBack.setEnabled(true);
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        this.page++;
        this.lblPage.setText(this.page + "");
        showDataTableNV(NhanVienRepository.PhanTrang(page, limit));

    }//GEN-LAST:event_btnNextActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed

        try {
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

            //Gọi phương thức
            ArrayList<nhanVienResponse> listsNV = (ArrayList<nhanVienResponse>) NhanVienRepository.Search(cboNhanVien.getSelectedItem().toString(), tuoiBatDau, tuoiKetThuc, txtSearch.getText());
            showDataTableNV(listsNV);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi. Vui lòng thử lại sau.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnSearchActionPerformed
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
                Sheet sheet = wb.createSheet("customer");
                Row rowCol = sheet.createRow(0);
                for (int i = 0; i < tblNhanVien.getColumnCount(); i++) {
                    Cell cell = rowCol.createCell(i);
                    cell.setCellValue(tblNhanVien.getColumnName(i));
                }

                for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
                    Row row = sheet.createRow(i + 1);
                    for (int j = 0; j < tblNhanVien.getColumnCount(); j++) {
                        Cell cell = row.createCell(j);
                        if (tblNhanVien.getValueAt(i, j) != null) {
                            cell.setCellValue(tblNhanVien.getValueAt(i, j).toString());
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

    private void btnNewCaLamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCaLamActionPerformed
        // TODO add your handling code here:
        txtGhiChu.setText("");
        //txtTienCaLam.setText("");
    }//GEN-LAST:event_btnNewCaLamActionPerformed

    private void btnTimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimActionPerformed
        // TODO add your handling code here:
        showDataTableCalam(CaLamRespository.Search(txtTenNhanVien.getText(), txtNgayCaLam1.getDate(), txtNgayCaLam2.getDate()));
    }//GEN-LAST:event_btnTimActionPerformed

    private void btnLoadTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadTableActionPerformed
        // TODO add your handling code here:
        showDataTableNV(NhanVienRepository.getAll());
    }//GEN-LAST:event_btnLoadTableActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnLoadTable;
    private javax.swing.JToggleButton btnNewCaLam;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JToggleButton btnSuaCaLam;
    private javax.swing.JButton btnThem;
    private javax.swing.JToggleButton btnThemCaLam;
    private javax.swing.JButton btnTim;
    private javax.swing.JButton btnXem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JToggleButton btnXoaCaLam;
    private javax.swing.JButton btnXuat;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboCalam;
    private javax.swing.JComboBox<String> cboNhanVien;
    private javax.swing.JComboBox<String> cboTenNV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblPage;
    private java.awt.Panel panel1;
    private javax.swing.JTable tblCaLam;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtGhiChu;
    private javax.swing.JTextField txtMaxAge;
    private javax.swing.JTextField txtMinAge;
    private com.toedter.calendar.JDateChooser txtNgayCaLam;
    private com.toedter.calendar.JDateChooser txtNgayCaLam1;
    private com.toedter.calendar.JDateChooser txtNgayCaLam2;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables
}
