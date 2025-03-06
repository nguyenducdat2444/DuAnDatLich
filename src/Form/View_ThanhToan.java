/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import response.HDCTResponse;
import response.LichHenResponse;
import repository.HDCTRepository;
import repository.HoaDonRepository;
import repository.LichHenRepository;
import response.HoaDonRespone;

/**
 *
 * @author Admin
 */
public class View_ThanhToan extends javax.swing.JPanel {

    private LichHenRepository lhRepo = new LichHenRepository();
    private HDCTRepository hdctRepo = new HDCTRepository();
    private HoaDonRepository hdrepo = new HoaDonRepository();

    DefaultTableModel dtmLichHen = new DefaultTableModel();
    DefaultTableModel dtmHDCT = new DefaultTableModel();

    int viTri;

    /**
     * Creates new form View_ThanhToan
     */
    public View_ThanhToan() {
        initComponents();
        dtmLichHen = (DefaultTableModel) tblKhachHang.getModel();
        dtmHDCT = (DefaultTableModel) tblHDCT.getModel();
        this.showTableLichHen(lhRepo.lichHenThanhToan());
        if (lhRepo.lichHenThanhToan().size() > 0) {
            viTri = lhRepo.lichHenThanhToan().size() - 1;
            ShowDetail();
        }

        txtTienDua.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                validate1();
            }
        });

        txtTienDua.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTraLai();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTraLai();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTraLai();
            }
        });
    }

    private void updateTraLai() {
        try {
            // Chuyển đổi giá trị từ các ô nhập liệu
            float tongTienDV = Float.parseFloat(txtTongTienDV.getText().trim());
            float tienCoc = Float.parseFloat(txtTienCoc.getText().trim());
            float tienDua = Float.parseFloat(txtTienDua.getText().trim());

            // Tính toán lblTong và txtTraLai
            float tong = tongTienDV - tienCoc;
            lblTong.setText(String.valueOf(tong));
            float traLai = tienDua - tong;
            txtTraLai.setText(String.valueOf(traLai));
        } catch (NumberFormatException e) {
            // Xử lý lỗi nếu có vấn đề với việc chuyển đổi số
            txtTraLai.setText(""); // Xóa giá trị tiền trả lại nếu có lỗi
        }
    }

    public void validate1() {
        if (txtTienDua.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Tiền khách đưa");
            txtTienDua.requestFocus();
            return;
        }
        try {
            int txt = Integer.valueOf(txtTienDua.getText());
            if (txt <= 0) {
                JOptionPane.showMessageDialog(this, "Tiền Đưa phải lớn hơn 0 !!");
                txtTienDua.requestFocus();
            } else if (txt < Float.valueOf(lblTong.getText())) {
                JOptionPane.showMessageDialog(this, "Tiền Đưa phải tối thiểu phải bằng tổng tiền!!");
                txtTienDua.requestFocus();
            } else if (txt > 10000000) {
                JOptionPane.showMessageDialog(this, "Mày cắt bộ tóc thôi mà đưa đéo gì lắm tiền thế");
                txtTienDua.requestFocus();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tiền đưa chưa có chức năng nhập chữ!!");
            txtTienDua.requestFocus();
        }

    }

    public void showTableLichHen(ArrayList<LichHenResponse> lists) {
        dtmLichHen.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmLichHen.addRow(new Object[]{
            index.getAndIncrement(),
            //s.getIdHD(), 
            s.getMaHD(),
            s.getTenKh(),
            s.getSdt_KH(),
            s.getGioHen(),
            s.getNgayHen(),
            s.getTrangThaiLH() == 0 ? "Đã xong" : "Đang chờ"
        }));
    }

    public void showTableHDCT(ArrayList<HDCTResponse> lists) {
        dtmHDCT.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> {
            dtmHDCT.addRow(new Object[]{
                index.getAndIncrement(), s.getTenDV(), s.getThoiGianDV(), s.getSoLuong(), s.getDonGia() + "đ", (s.getSoLuong() * s.getDonGia())
            });
            hdctRepo.UpdateTien(s.getSoLuong(), s.getDonGia(), s.getIdHDCT());

        });
        hdctRepo.updateTongTien();
        showTableLichHen(lhRepo.lichHenThanhToan());
    }

    public void ShowDetail() {
        LichHenResponse lh = lhRepo.lichHenThanhToan().get(viTri);
        txtTenKh.setText(lh.getTenKh());
        txtSDT.setText(lh.getSdt_KH());
        txtNgayDat.setText(lh.getNgayHen() + "");
        txtThoiGian.setText(lh.getGioHen() + "");
        txtTienCoc.setText(lh.getTienCoc() + "");
        txtTongTienDV.setText(lh.getTongTien() + "");
        float tongTienDV = Float.parseFloat(txtTongTienDV.getText().trim());
        float tienCoc = Float.parseFloat(txtTienCoc.getText().trim());
        float tienDua = Float.parseFloat(txtTienDua.getText().trim());

        float tong = tongTienDV - tienCoc;
        lblTong.setText(String.valueOf(tong));

        tblKhachHang.setRowSelectionInterval(viTri, viTri);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtTenKh = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtNgayDat = new javax.swing.JTextField();
        txtThoiGian = new javax.swing.JTextField();
        txtTienCoc = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        txtTongTienDV = new javax.swing.JTextField();
        txtTienDua = new javax.swing.JTextField();
        txtTraLai = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        lblTong = new javax.swing.JLabel();
        btnThanhTona = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnThemDV = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHDCT = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lịch Hẹn Trong Ngày", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Hóa Đơn", "Tên Khách Hàng", "Số Điện Thoại", "Thời Gian", "Ngày Đặt", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKhachHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKhachHang);

        jButton1.setBackground(new java.awt.Color(51, 255, 51));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        jButton1.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setText("Tên Khách Hàng:");

        jLabel2.setText("Số Điện Thoại:");

        jLabel3.setText("Ngày Đặt:");

        jLabel4.setText("Thời Gian:");

        jLabel5.setText("Tiền Cọc:");

        jLabel6.setText("Tiền Dịch Vụ:");

        jLabel7.setText("Tiền Khách Đưa:");

        jLabel8.setText("Tiền Trả Lại:");

        txtTenKh.setEditable(false);
        txtTenKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenKhActionPerformed(evt);
            }
        });

        txtSDT.setEditable(false);

        txtNgayDat.setEditable(false);

        txtThoiGian.setEditable(false);

        txtTienCoc.setEditable(false);

        jSeparator1.setForeground(new java.awt.Color(51, 0, 0));

        txtTongTienDV.setEditable(false);

        txtTienDua.setText("0");
        txtTienDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienDuaActionPerformed(evt);
            }
        });

        txtTraLai.setEditable(false);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thanh Toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Tổng Tiền :");

        lblTong.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTong.setText("0");

        btnThanhTona.setBackground(new java.awt.Color(0, 153, 255));
        btnThanhTona.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThanhTona.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhTona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/payment.png"))); // NOI18N
        btnThanhTona.setText("Thanh Toán");
        btnThanhTona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhTonaActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("VND");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(99, 99, 99)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
                .addComponent(lblTong, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(65, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThanhTona)
                .addGap(136, 136, 136))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblTong)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnThanhTona, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addGap(29, 29, 29)
                            .addComponent(txtTraLai))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTienDua))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTongTienDV, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTenKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtThoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtTongTienDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtTienDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtTraLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm Dịch Vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnThemDV.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnThemDV.setText("+");
        btnThemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVActionPerformed(evt);
            }
        });

        tblHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên dịch vụ", "Thời gian", "số lượng", "Đơn Giá", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblHDCT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemDV)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 795, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(btnThemDV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                .addGap(12, 12, 12))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 102, 255));
        jLabel10.setText("Bảng Thanh Toán");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(489, 489, 489))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKhActionPerformed

    private void tblKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKhachHangMouseClicked
        // TODO add your handling code here:
        viTri = tblKhachHang.getSelectedRow();
        int idHd = lhRepo.lichHenThanhToan().get(viTri).getIdHD();
        showTableHDCT(hdctRepo.getAllHDCTTT(idHd));

        if (viTri == -1) {
            return;
        } else {
            ShowDetail();
        }
    }//GEN-LAST:event_tblKhachHangMouseClicked

    public int SelectedIdHD() {
        viTri = tblKhachHang.getSelectedRow();
        int idHD = lhRepo.lichHenThanhToan().get(viTri).getIdHD();
        if (idHD != -1) {
            return idHD;
        }
        return -1;
    }
    private void btnThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVActionPerformed
        // TODO add your handling code here:
        new AddDV(this, true).setVisible(true);
    }//GEN-LAST:event_btnThemDVActionPerformed

    private void txtTienDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienDuaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTienDuaActionPerformed

    private void btnThanhTonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhTonaActionPerformed
        // TODO add your handling code here:
        viTri = tblKhachHang.getSelectedRow();
        if (viTri == -1) {
            JOptionPane.showMessageDialog(this, "Có vẻ như chưa có hóa đơn nào được chọn để thanh toán");
            return;
        }

        int idHD = lhRepo.lichHenThanhToan().get(viTri).getIdHD();
        int idLH = lhRepo.lichHenThanhToan().get(viTri).getIdLH();
        String MaHD = lhRepo.lichHenThanhToan().get(viTri).getMaHD();

        int i = JOptionPane.showConfirmDialog(this, "Bạn có muốn in hóa đơn không ?");
        if (i == JOptionPane.YES_OPTION) {
            //Xuất PDF
            String path = "";
            JFileChooser j = new JFileChooser();
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int x = j.showSaveDialog(this);
            if (x == JFileChooser.APPROVE_OPTION) {
                path = j.getSelectedFile().getPath();
            }

            if (!path.endsWith("/")) {
                path += "/";
            }

            Document doc = new Document();
            try {
                PdfWriter.getInstance(doc, new FileOutputStream(path + "HDTT.pdf"));
                doc.open();

                Font titleForn = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
                Font contentFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

                //Thông tin lịch hẹn
                doc.add(new Paragraph("LICH HEN", titleForn));
                doc.add(new Paragraph("MA HOA DON:" + getValueAt(tblKhachHang, viTri, 2)));
                doc.add(new Paragraph("TEN KHACH HANG: " + getValueAt(tblKhachHang, viTri, 3)));
                doc.add(new Paragraph("SDT: " + getValueAt(tblKhachHang, viTri, 4)));
                doc.add(new Paragraph("THOI GIAN: " + getValueAt(tblKhachHang, viTri, 5)));
                doc.add(new Paragraph("NGAY DAT: " + getValueAt(tblKhachHang, viTri, 6)));

                doc.add(new Paragraph(""));

                doc.add(new Paragraph("DICH VU DA DAT", titleForn));
                for (int a = 0; a < this.tblHDCT.getRowCount(); a++) {
                    doc.add(new Paragraph("TEN DICH VU" + getValueAt(tblHDCT, a, 1)));
                    doc.add(new Paragraph("SO LUONG" + getValueAt(tblHDCT, a, 3)));
                    doc.add(new Paragraph("DON GIA" + getValueAt(tblHDCT, a, 4)));
                    doc.add(new Paragraph("THANH TIEN" + getValueAt(tblHDCT, a, 5)));
                    doc.add(new Paragraph(""));
                    doc.add(new Paragraph(a + 1));
                    doc.add(new Paragraph(""));
                }

                doc.add(new Paragraph(""));
                doc.add(new Paragraph("TIEN DICH VU" + txtTongTienDV.getText()));
                doc.add(new Paragraph("-"));
                doc.add(new Paragraph("TIEN COC:" + txtTienCoc.getText()));
                doc.add(new Paragraph("____________________"));
                doc.add(new Paragraph("THANH TIEN:" + lblTong.getText()));

                doc.add(new Paragraph("TIEN KHACH DUA:" + txtTienDua.getText()));
                doc.add(new Paragraph(""));
                doc.add(new Paragraph("TIEN TRA LAI" + txtTraLai.getText()));

                doc.add(new Paragraph(""));

                doc.add(new Paragraph("__________________________________"));

                doc.add(new Paragraph("NICE TO MEET YOU"));

                doc.add(new Paragraph(""));

                String qrCodeText = String.valueOf(MaHD);
                String qrCodePath = path + "QRCode.png";
                createQRCode(qrCodeText, qrCodePath, 150, 150);
                Image qrCodeImage = Image.getInstance(qrCodePath);
                qrCodeImage.setAlignment(Element.ALIGN_CENTER);
                doc.add(qrCodeImage);

                hdrepo.updateTrangThaiHD(idHD, idLH);
                this.showTableLichHen(lhRepo.lichHenThanhToan());

                JOptionPane.showMessageDialog(this, "In Hóa Đơn Và Thanh Toán thành công");

            } catch (Exception e) {
                e.printStackTrace(System.out);
                JOptionPane.showMessageDialog(this, "Có một số lỗi không xác định");
            } finally {
                if (doc.isOpen()) {
                    doc.close();
                }
            }

        } else {
            hdrepo.updateTrangThaiHD(idHD, idLH);
            this.showTableLichHen(lhRepo.lichHenThanhToan());
            JOptionPane.showMessageDialog(this, "Đã thanh toán");
        }
    }//GEN-LAST:event_btnThanhTonaActionPerformed

    private String getValueAt(JTable table, int row, int column) {
        Object value = table.getValueAt(row, column);
        return value == null ? "" : value.toString();
    }

    private void createQRCode(String text, String filePath, int width, int height) throws WriterException, java.io.IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
        ImageIO.write(qrImage, "PNG", new File(filePath));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhTona;
    private javax.swing.JButton btnThemDV;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel lblTong;
    private javax.swing.JTable tblHDCT;
    private javax.swing.JTable tblKhachHang;
    private javax.swing.JTextField txtNgayDat;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKh;
    private javax.swing.JTextField txtThoiGian;
    private javax.swing.JTextField txtTienCoc;
    private javax.swing.JTextField txtTienDua;
    private javax.swing.JTextField txtTongTienDV;
    private javax.swing.JTextField txtTraLai;
    // End of variables declaration//GEN-END:variables
}
