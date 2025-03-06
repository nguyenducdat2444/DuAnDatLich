/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package Form;

import config.DBConnect;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import entity.Khach_Hang;
import java.lang.reflect.Array;
import repository.LichHenRepository;
import entity.lichHen;
import response.KhachHangResponse;

import entity.Dich_Vu;
import entity.nhanVien;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import repository.KhachHangRepository;
import repository.Loai_DV_Responsory;
import repository.NhanVienRepository;
import repository.dichVuTietResponsory;
import response.DichVuRespon;
import response.LichHenResponse;
import response.nhanVienResponse;
import java.sql.Time;
import Form.AddDV;
import Form.AddDVTam;
import dichVuTam.DVTamRepository;
import dichVuTam.DvTam;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.*;

/**
 *
 * @author default
 */
public class DatLich1 extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultTableModel dtmDVTam = new DefaultTableModel();

    private LichHenRepository repo = new LichHenRepository();

    private ArrayList<Khach_Hang> lists = new ArrayList<>();
    private dichVuTietResponsory dichVuTietResponsory = new dichVuTietResponsory();
    private KhachHangRepository KhachHangRepository = new KhachHangRepository();
    private NhanVienRepository NhanVienRepository = new NhanVienRepository();
    private DVTamRepository dvTamRepo = new DVTamRepository();

    private Map<String, Khach_Hang> phoneToKhachHangMap; // Map số điện thoại tới đối tượng khách hàng

    /**
     * Creates new form khachHang1
     */
    public DatLich1() {
        initComponents();

        // Khởi tạo phoneToKhachHangMap
        //phoneToKhachHangMap = new HashMap<>();
        //FillTable(repo.LichSuDatLich());
        dtmDVTam = (DefaultTableModel) tblDichVuAdd.getModel();

        loadComboBoxDataKH();
        loadComboBoxDataNV();
        loadComboBoxDataDV();
        loadComboBoxTime();

    }

    public void showTableAddDv(ArrayList<DvTam> lists) {
        dtmDVTam.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        lists.forEach(s -> dtmDVTam.addRow(new Object[]{
            index.getAndIncrement(),
            s.getTenDV(),
            s.getThoiwGianDV(),
            s.getGiaTien() + "đ",
            s.getId(),
            s.getMaDV()
        }));
    }

//    public void FillTable(ArrayList<LichHenResponse> listkh) {
//        dtm = (DefaultTableModel) tblLichHen.getModel();
//        dtm.setRowCount(0);
//
//        AtomicInteger index = new AtomicInteger(1);
//
//        listkh.forEach(s -> dtm.addRow(new Object[]{
//            index.getAndIncrement(),
//            s.getTen_KH(),
//            s.getNgayHen(),
//            s.getGioHen(),
//            s.getTen_DV(),
//            s.getTen_NV(),
//            s.getSdt_KH(),
//            s.getGhiChu(),  
//            (s.getTrangThai() == 1) ? "Đang chờ"
//            : (s.getTrangThai() == 2) ? "Đã hủy" : "Đã hoàn thành",}));
//    }
    private LichHenResponse readForm() {
        LichHenResponse lh = new LichHenResponse();
        lh.setNgayHen(JDateChoose.getDate());
        // Lấy giờ và chuyển đổi sang java.sql.Time
        String gioHenStr = cboTime.getSelectedItem() != null ? cboTime.getSelectedItem().toString() : "";
        try {
            // Chuyển đổi chuỗi thời gian thành java.sql.Time
            java.sql.Time gioHen = java.sql.Time.valueOf(gioHenStr + ":00"); // Thêm :00 nếu cần
            lh.setGioHen(gioHen);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            // Xử lý lỗi chuyển đổi nếu cần
        }
        lh.setTen_DV(cboDichVu.getSelectedItem().toString());
        lh.setTen_NV(cboNhanVien.getSelectedItem().toString());
        lh.setTen_KH(cboKhachHang.getSelectedItem().toString());
        lh.setGhiChu(txtGhiChu.getText());
        lh.setSdt_KH(cboSdtKH.getSelectedItem().toString());

        return lh;
    }

    private lichHen Conver(LichHenResponse pon) {
        // Lấy thông tin khách hàng từ repository
        Khach_Hang kh = KhachHangRepository.chucVu(pon.getTen_KH(), pon.getSdt_KH());
        // Lấy thông tin dịch vụ từ repository
        Dich_Vu dv = dichVuTietResponsory.chucVu(pon.getTen_DV());
        // Lấy thông tin nhân viên từ repository
        nhanVien nv = NhanVienRepository.chucVu(pon.getTen_NV()); // Chỉnh sửa tham số cho đúng với nhân viên
        // Kiểm tra null cho các đối tượng và xây dựng lichHen
        return lichHen.builder()
                .ngayHen(pon.getNgayHen())
                .gioHen(pon.getGioHen())
                .ghiChu(pon.getGhiChu())
                .id_KH(kh != null ? kh.getId() : null) // Kiểm tra null và gán giá trị
                .id_DV(dv != null ? dv.getId() : null) // Kiểm tra null và gán giá trị
                .id_NV(nv != null ? nv.getId() : null) // Kiểm tra null và gán giá trị
                .build();

    }
    private Integer selectedKhachHangId;
    
    private boolean isUpdating = false;

    private void loadComboBoxDataKH() {
        try {
            // Khởi tạo phoneToKhachHangMap nếu chưa khởi tạo
            if (phoneToKhachHangMap == null) {
                phoneToKhachHangMap = new HashMap<>();
            } else {
                // Xóa dữ liệu cũ nếu đã khởi tạo
                phoneToKhachHangMap.clear();
            }

            // Xóa các mục hiện tại nếu có
            cboKhachHang.removeAllItems();
            cboSdtKH.removeAllItems();
            cboEmail.removeAllItems();

            // Lấy danh sách khách hàng từ repository
            ArrayList<Khach_Hang> khachHangList = repo.getComboBoxKH();
            // Sử dụng Map để lưu trữ ID và đối tượng khách hàng
            Map<Integer, Khach_Hang> idToKhachHangMap = new HashMap<>();

            // Thêm các khách hàng vào ComboBox và lưu trữ ID -> Khach_Hang mapping
            for (Khach_Hang kh : khachHangList) {
                Integer id_KH = kh.getId();
                String ten_KH = kh.getTen_KH();
                String sdt_KH = kh.getSdt_KH();
                String email = kh.getEmail();

                // Lưu trữ số điện thoại và đối tượng khách hàng
                phoneToKhachHangMap.put(sdt_KH.trim(), kh);
                // Lưu trữ ID và đối tượng khách hàng
                idToKhachHangMap.put(id_KH, kh);

                // Thêm tên khách hàng vào ComboBox KhachHang
                cboKhachHang.addItem(ten_KH);
                // Thêm số điện thoại vào ComboBox SdtKH
                cboSdtKH.addItem(sdt_KH);
                // Thêm email vào ComboBox Email
                cboEmail.addItem(email);
            }

            // Xử lý sự kiện chọn khách hàng trong ComboBox KhachHang
            cboKhachHang.addActionListener(e -> {
                if (isUpdating) {
                    return;
                }
                isUpdating = true;

                String selectedName = (String) cboKhachHang.getSelectedItem();
                boolean found = false;

                for (Khach_Hang kh : idToKhachHangMap.values()) {
                    if (kh.getTen_KH().equals(selectedName)) {
                        Integer id = kh.getId();
                        selectedKhachHangId = id; // Lưu ID
                        cboSdtKH.setSelectedItem(kh.getSdt_KH());
                        cboEmail.setSelectedItem(kh.getEmail());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Khách hàng không tìm thấy trong sự kiện chọn tên.");
                    JOptionPane.showMessageDialog(this, "Khách hàng không tìm thấy trong sự kiện chọn tên." );
                }

                isUpdating = false;
            });

            // Xử lý sự kiện chọn số điện thoại trong ComboBox SdtKH
            cboSdtKH.addActionListener(e -> {
                if (isUpdating) {
                    return;
                }
                isUpdating = true;

                String selectedSdt = (String) cboSdtKH.getSelectedItem();
                boolean found = false;

                for (Khach_Hang kh : idToKhachHangMap.values()) {
                    if (kh.getSdt_KH().equals(selectedSdt)) {
                        Integer id = kh.getId();
                        cboKhachHang.setSelectedItem(kh.getTen_KH());
                        cboEmail.setSelectedItem(kh.getEmail());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Khách hàng không tìm thấy trong sự kiện chọn số điện thoại.");
                    JOptionPane.showMessageDialog(this, "Khách hàng không tìm thấy trong sự kiện chọn SDT." );
                }

                isUpdating = false;
            });

            // Xử lý sự kiện chọn email trong ComboBox Email
            cboEmail.addActionListener(e -> {
                if (isUpdating) {
                    return;
                }
                isUpdating = true;

                String selectedEmail = (String) cboEmail.getSelectedItem();
                boolean found = false;

                for (Khach_Hang kh : idToKhachHangMap.values()) {
                    if (kh.getEmail().equals(selectedEmail)) {
                        Integer id = kh.getId();
                        cboKhachHang.setSelectedItem(kh.getTen_KH());
                        cboSdtKH.setSelectedItem(kh.getSdt_KH());
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Khách hàng không tìm thấy trong sự kiện chọn email.");
                    JOptionPane.showMessageDialog(this, "Khách hàng không tìm thấy trong sự kiện chọn Email." );
                }

                isUpdating = false;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void searchCustomer() {
        String phoneNumber = searchField.getText().trim(); // Loại bỏ khoảng trắng đầu và cuối
        if (phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại.");
            return;
        }

        Khach_Hang kh = phoneToKhachHangMap.get(phoneNumber);
        if (kh != null) {

            cboKhachHang.setSelectedItem(kh.getTen_KH());
            cboSdtKH.setSelectedItem(kh.getSdt_KH());
            cboEmail.setSelectedItem(kh.getEmail());
        } else {
            JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng với số điện thoại: " + phoneNumber);
            System.out.println("Số điện thoại tìm kiếm: " + phoneNumber);
            phoneToKhachHangMap.forEach((key, value) -> {
                System.out.println("Số điện thoại: " + key + ", Khách hàng: " + value.getTen_KH());
            });
        }
    }
    private Integer selectedNhanVienId;

    private void loadComboBoxDataNV() {

        cboNhanVien.removeAllItems();

        ArrayList<nhanVien> nhanVienList = repo.getComboBoxNV(); //Lấy nhân viên từ sql

        // Sử dụng Map để lưu trữ ID và đối tượng khách hàng
        Map<Integer, nhanVien> idToNhanVienMap = new HashMap<>();

        // Thêm các khách hàng vào ComboBox và lưu trữ ID -> Khach_Hang mapping
        for (nhanVien nv : nhanVienList) {
            Integer id = nv.getId();
            String ten_NV = nv.getTen_NV();

            // Lưu trữ ID và đối tượng khách hàng
            idToNhanVienMap.put(id, nv);

            // Thêm tên khách hàng vào ComboBox KhachHang
            cboNhanVien.addItem(ten_NV);
        }

        // Xử lý sự kiện chọn khách hàng trong ComboBox KhachHang
        cboNhanVien.addActionListener(e -> {
            // Lấy tên khách hàng được chọn
            String selectedName = (String) cboNhanVien.getSelectedItem();

            // Tìm ID khách hàng từ tên
            for (nhanVien nv : idToNhanVienMap.values()) {
                if (nv.getTen_NV().equals(selectedName)) {
                    Integer id = nv.getId();
                    selectedNhanVienId = nv.getId();
                    System.out.println("Selected ID: " + id);
                    // Cập nhật tên tương ứng trong cboNhanVien
                    cboNhanVien.setSelectedItem(nv.getTen_NV());
                    break;
                }
            }
        });
    }

    private void loadComboBoxDataDV() {

        cboDichVu.removeAllItems();
        //dichVuTietResponsory.getAll().forEach(nv -> cboDichVu.addItem(nv.getTen_DV()));
        ArrayList<Dich_Vu> dichVuList = repo.getComboBoxDV();

        // Sử dụng Map để lưu trữ ID và đối tượng khách hàng
        Map<Integer, Dich_Vu> idToDichVuMap = new HashMap<>();

        // Thêm các khách hàng vào ComboBox và lưu trữ ID -> Khach_Hang mapping
        for (Dich_Vu dv : dichVuList) {
            Integer id = dv.getId();
            String ten_DV = dv.getTen_DV();

            // Lưu trữ ID và đối tượng khách hàng
            idToDichVuMap.put(id, dv);

            // Thêm tên khách hàng vào ComboBox KhachHang
            cboDichVu.addItem(ten_DV);
        }

        // Xử lý sự kiện chọn khách hàng trong ComboBox KhachHang
        cboDichVu.addActionListener(e -> {
            // Lấy tên khách hàng được chọn
            String selectedName = (String) cboDichVu.getSelectedItem();

            // Tìm ID khách hàng từ tên
            for (Dich_Vu dv : idToDichVuMap.values()) {
                if (dv.getTen_DV().equals(selectedName)) {
                    Integer id = dv.getId();
                    System.out.println("Selected ID: " + id);
                    // Cập nhật tên tương ứng trong cboNhanVien
                    cboDichVu.setSelectedItem(dv.getTen_DV());
                    break;
                }
            }
        });
    }

    public static String[] generateTimeSlots() {
        ArrayList<String> timeSlots = new ArrayList<>();
        int startHour = 8;
        int endHour = 20;
        int interval = 30;

        for (int hour = startHour; hour <= endHour; hour++) {
            for (int minute = 0; minute < 60; minute += interval) {
                String timeSlot = String.format("%02d:%02d", hour, minute);
                timeSlots.add(timeSlot);
            }
        }

        return timeSlots.toArray(new String[0]);
    }

    private void loadComboBoxTime() {
        // Nạp danh sách thời gian vào combo box
        cboTime.setModel(new DefaultComboBoxModel<>(generateTimeSlots()));
    }

    public boolean checkDay(Date appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Ngày hẹn không thể là null");
        }

        Calendar tomorrow = Calendar.getInstance();
        tomorrow.setTime(appointment);

        Calendar today = Calendar.getInstance();

        int day = today.get(Calendar.YEAR) - tomorrow.get(Calendar.YEAR);

        if (today.get(Calendar.YEAR) == tomorrow.get(Calendar.YEAR)
                && today.get(Calendar.DAY_OF_YEAR) > tomorrow.get(Calendar.DAY_OF_YEAR)) {
            return true;
        }

        return false;
    }

    private void sendEmail(String to, String subject, String body) {
        final String from = "datdz2442005@gmail.com";
        final String password = "ukiu iokt ktun zzzv"; // Mật khẩu ứng dụng nếu sử dụng 2FA

        // Cấu hình thuộc tính email
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2"); // Cài đặt TLS 1.2

        // Tạo Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        // Tạo phiên làm việc
        Session session = Session.getInstance(props, auth);

        try {
            // Tạo tin nhắn
            Message message = new MimeMessage(session);

            // Kiểu nội dung
            message.setHeader("Content-Type", "text/HTML; charset=UTF-8");

            // Người gửi
            message.setFrom(new InternetAddress(from));

            // Người nhận
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // Tiêu đề email
            message.setSubject(subject);

            // Ngày gửi
            message.setSentDate(new Date());

            // Email nhận phản hồi
            message.setReplyTo(InternetAddress.parse(from, true));

            // Nội dung email
            message.setText(body);

            // Gửi email
            Transport.send(message);

            System.out.println("Email sent successfully to " + to + "!");
        } catch (MessagingException e) {
            e.printStackTrace(); // In chi tiết lỗi nếu có
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel28 = new javax.swing.JLabel();
        cboDichVu = new javax.swing.JComboBox<>();
        btnDatLich = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        JDateChoose = new com.toedter.calendar.JDateChooser();
        jLabel23 = new javax.swing.JLabel();
        cboTime = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        cboNhanVien = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        txtGhiChu = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDichVuAdd = new javax.swing.JTable();
        btnThemDv = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        cboKhachHang = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        cboSdtKH = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        cboEmail = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1500, 900));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 51, 255));
        jLabel28.setText("ĐẶT LỊCH HẸN CẮT TÓC");

        cboDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnDatLich.setBackground(new java.awt.Color(204, 0, 0));
        btnDatLich.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnDatLich.setForeground(new java.awt.Color(255, 255, 255));
        btnDatLich.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/clipboard.png"))); // NOI18N
        btnDatLich.setText("Đặt lịch ngay");
        btnDatLich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatLichActionPerformed(evt);
            }
        });

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin lịch hẹn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18)))); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setText("Ngày hẹn:");

        JDateChoose.setDateFormatString("yyyy-MM-dd");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("Giờ hẹn:");

        cboTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "13:30", "14:00", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00" }));
        cboTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTimeActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setText("Nhân viên:");

        cboNhanVien.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNhanVienActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel31.setText("Ghi chú:");

        txtGhiChu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGhiChuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboNhanVien, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtGhiChu)
                    .addComponent(JDateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTime, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(59, 59, 59))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JDateChoose, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTime, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGhiChu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(188, 188, 188))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thêm Dịch Vụ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblDichVuAdd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Tên Dịch vụ", "Thời Gian", "Giá Tiền "
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tblDichVuAdd);

        btnThemDv.setText("Thêm");
        btnThemDv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDvActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnThemDv)
                .addGap(27, 27, 27)
                .addComponent(btnXoa)
                .addContainerGap(1009, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemDv)
                    .addComponent(btnXoa))
                .addContainerGap(226, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(39, 39, 39)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 18))); // NOI18N
        jPanel6.setPreferredSize(new java.awt.Dimension(1350, 320));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Số điện thoại:");

        cboKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhachHangActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("Tên khách hàng:");

        cboSdtKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("Email:");

        cboEmail.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Mời nhập số điện thoại để lấy thông tin:");

        searchField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

        searchButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        searchButton.setForeground(new java.awt.Color(255, 153, 153));
        searchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Search.png"))); // NOI18N
        searchButton.setText("Tìm kiếm");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboSdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 9, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 12, Short.MAX_VALUE))
                    .addComponent(searchField))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSdtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 219, Short.MAX_VALUE)
                        .addComponent(cboDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(504, 504, 504)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(517, 517, 517)
                                .addComponent(btnDatLich, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 272, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDatLich, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cboDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThemDvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDvActionPerformed
        new AddDVTam(this, true).setVisible(true);
    }//GEN-LAST:event_btnThemDvActionPerformed

    private void txtGhiChuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGhiChuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGhiChuActionPerformed

    private void cboNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNhanVienActionPerformed

    private void cboTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboTimeActionPerformed

    private void btnDatLichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatLichActionPerformed

        Date appointment = JDateChoose.getDate();
        boolean isPassed = checkDay(appointment);
        if (isPassed) {
            JOptionPane.showMessageDialog(this, "Ngày hẹn đã qua");
        } else {
            int ask = JOptionPane.showConfirmDialog(this, " Bạn có muốn Đặt Lịch Không");
            if (ask != JOptionPane.YES_OPTION) {
                return;
            } else {
                int result = repo.Add(Conver(readForm()));
                if (result > 0) {
                    // Thêm thành công
                    JOptionPane.showMessageDialog(this, "Thêm thành công", "Thông Báo", JOptionPane.INFORMATION_MESSAGE);
                    // Cập nhật bảng dữ liệu
                    //this.FillTable(repo.LichSuDatLich());
                    repo.XoaDVTam();
                    showTableAddDv(dvTamRepo.getAll());

                    // Lấy thông tin khách hàng
                    String selectedEmail = (String) cboEmail.getSelectedItem();
                    String customerName = (String) cboKhachHang.getSelectedItem();
                    String customerPhone = (String) cboSdtKH.getSelectedItem();
                    String hour = (String) cboTime.getSelectedItem();
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    java.util.Date parsedDate = null;
                    try {
                        parsedDate = format.parse(hour);
                    } catch (java.text.ParseException ex) {
                        Logger.getLogger(DatLich1.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String appointmentDate = new SimpleDateFormat("dd/MM/yyyy").format(appointment);
                    Time appointmentHour = new Time(parsedDate.getTime());
                    String note = txtGhiChu.getText();

                    // Tạo nội dung email
                    String emailBody = String.format(
                            "Thông Tin Đặt Lịch:\n\n"
                            + "Tên Khách Hàng: %s\n"
                            + "Số Điện Thoại: %s\n"
                            + "Ngày Hẹn: %s\n"
                            + "Giờ Hẹn: %s\n"
                            + "Note: %s\n"
                            + "Thông Tin Đặt Lịch đã được cập nhật thành công.",
                            customerName, customerPhone, appointmentDate, appointmentHour, note
                    );

                    // Gửi email
                    sendEmail(selectedEmail, "Thông tin khách hàng đặt lịch !", emailBody);

                } else {
                    // Thêm không thành công
                    JOptionPane.showMessageDialog(this, "Thêm không thành công", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDatLichActionPerformed

    private void cboKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboKhachHangActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        searchCustomer();
    }//GEN-LAST:event_searchButtonActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        viTri = tblDichVuAdd.getSelectedRow();
        int id = dvTamRepo.getAll().get(viTri).getId();
        if (viTri == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dịch vụ cần xóa !!");
            return;
        }
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không ?");
        if (chon == JOptionPane.YES_OPTION) {
            dvTamRepo.remove(id);
            this.showTableAddDv(dvTamRepo.getAll());
            JOptionPane.showMessageDialog(this, "Xóa Ok");
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không xóa nữa à");
            return;
        }


    }//GEN-LAST:event_btnXoaActionPerformed
//    public ArrayList<DvTam>detail(){
//        ArrayList<Integer>lists = new ArrayList<>();
//        for(int i =0; i<tblDichVuAdd.getRowCount();i++){
//            int idDv = Integer.valueOf(tblDichVuAdd.getValueAt(i, 1).toString());
//            lists.add(idDv);
//            System.out.println(idDv);
//        }
//        return lists;
//    }

    int viTri;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JDateChoose;
    private javax.swing.JButton btnDatLich;
    private javax.swing.JButton btnThemDv;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboDichVu;
    private javax.swing.JComboBox<String> cboEmail;
    private javax.swing.JComboBox<String> cboKhachHang;
    private javax.swing.JComboBox<String> cboNhanVien;
    private javax.swing.JComboBox<String> cboSdtKH;
    private javax.swing.JComboBox<String> cboTime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JTable tblDichVuAdd;
    private javax.swing.JTextField txtGhiChu;
    // End of variables declaration//GEN-END:variables

}
