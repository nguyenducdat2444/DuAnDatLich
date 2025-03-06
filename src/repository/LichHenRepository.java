/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import Form.DatLich1;
import config.DBConnect;
import dichVuTam.DvTam;
import entity.Dich_Vu;
import entity.Hoa_Don;
import entity.Khach_Hang;
import entity.lichHen;
import entity.nhanVien;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import response.KhachHangResponse;
import response.LichHenResponse;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class LichHenRepository {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public LichHenRepository() {
        con = DBConnect.getConnection();

    }

    public ArrayList<LichHenResponse> getAll(String maHD) {
        ArrayList<LichHenResponse> lists = new ArrayList<>();
        sql = """
             SELECT dbo.LichHen.ID, dbo.Hoa_Don.Ma_HD, dbo.LichHen.NgayHen, dbo.LichHen.GioHen, dbo.LichHen.TienCoc, dbo.LichHen.TrangThai
                          FROM     dbo.LichHen INNER JOIN
                                            dbo.Hoa_Don ON dbo.LichHen.ID = dbo.Hoa_Don.LichHenId where dbo.Hoa_Don.Ma_HD = ?
             """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            rs = ps.executeQuery();
            DateTimeFormatter fomat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            while (rs.next()) {
                LichHenResponse respon = LichHenResponse.builder()
                        .idLH(rs.getInt(1))
                        .maHD(rs.getString(2))
                        .ngayHen(rs.getDate(3))
                        .gioHen(rs.getTime(4))
                        .tienCoc(rs.getFloat(5))
                        .trangThai(rs.getInt(6))
                        .build();
                lists.add(respon);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<LichHenResponse> getAll2() {
        ArrayList<LichHenResponse> lists = new ArrayList<>();
        sql = """
             SELECT dbo.LichHen.ID, dbo.LichHen.NgayHen, dbo.LichHen.GioHen, dbo.LichHen.GhiChu, dbo.LichHen.ID_KH, dbo.Khach_Hang.Ten_KH, dbo.LichHen.ID_NV, dbo.Nhan_Vien.Ten_NV, dbo.LichHen.ID_DV, 
                          dbo.Dich_Vu.Ten_DV, dbo.Khach_Hang.SDT_KH
             FROM   dbo.LichHen INNER JOIN
                          dbo.Khach_Hang ON dbo.LichHen.ID_KH = dbo.Khach_Hang.ID INNER JOIN
                          dbo.Nhan_Vien ON dbo.LichHen.ID_NV = dbo.Nhan_Vien.ID INNER JOIN
                          dbo.Dich_Vu ON dbo.LichHen.ID_DV = dbo.Dich_Vu.ID
             """;
        try {
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            //DateTimeFormatter fomat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            while (rs.next()) {
                LichHenResponse respon = LichHenResponse.builder()
                        .idLH(rs.getInt(1))
                        .ngayHen(rs.getDate(2))
                        .gioHen(rs.getTime(3))
                        .ghiChu(rs.getString(4))
                        .id_KH(rs.getInt(5))
                        .ten_KH(rs.getString(6))
                        .id_NV(rs.getInt(7))
                        .ten_NV(rs.getString(8))
                        .id_DV(rs.getInt(9))
                        .ten_DV(rs.getString(10))
                        .trangThai(rs.getInt(10))
                        .build();
                lists.add(respon);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<LichHenResponse> LichSuDatLich() {
        ArrayList<LichHenResponse> lists = new ArrayList<>();
        sql = """
         SELECT 
                                             lh.ID,
                                             lh.NgayHen ,
                                             lh.GioHen ,
                                             lh.GhiChu,
                                             kh.Ten_KH ,
                                             nv.Ten_NV,
                                             kh.SDT_KH,
                                             STRING_AGG(dv.Ten_DV, ', ') AS Services, 
                                             SUM(hdct.Thanh_Tien) AS TotalAmount,
                                         	 lh.TrangThai
                                         FROM dbo.LichHen lh
                                         LEFT JOIN dbo.Khach_Hang kh ON lh.ID_KH = kh.ID 
                                         LEFT JOIN dbo.Nhan_Vien nv ON lh.ID_NV = nv.ID 
                                         LEFT JOIN dbo.Hoa_Don hd ON lh.ID = hd.LichHenId 
                                         LEFT JOIN dbo.HDCT hdct ON hd.ID = hdct.ID_HD 
                                         LEFT JOIN dbo.Dich_Vu dv ON hdct.ID_DV = dv.ID 
                                         GROUP BY 
                                             lh.ID, 
                                             lh.NgayHen, 
                                             lh.GioHen, 
                                             lh.GhiChu, 
                                             kh.Ten_KH, 
                                             nv.Ten_NV, 
                                             kh.SDT_KH,
                                         	lh.TrangThai
              
        """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //String ma_LH = rs.getString(1);
                int id = rs.getInt(1);
                String tenKH = rs.getString(5); // Đọc chuỗi
                Date ngayHen = rs.getDate(2);
                Time gioHen = rs.getTime(3); // Đọc Time
                String tenDV = rs.getString(8); // Đọc chuỗi
                String ten_NV = rs.getString(6);
                int trangThai = rs.getInt(10); // Đọc số nguyên               
                String ghiChu = rs.getString(4); // Đọc chuỗi
                String sdt_KH = rs.getString(7);
                double thanhTien = rs.getDouble(9);

                // Tạo đối tượng KhachHangResponse
                LichHenResponse dv = LichHenResponse.builder()
                        .idLH(id)
                        .ten_KH(tenKH)
                        .ngayHen(ngayHen)
                        .gioHen(gioHen)
                        .sdt_KH(sdt_KH)
                        .ten_DV(tenDV)
                        .ten_NV(ten_NV)
                        .trangThai(trangThai)
                        .ghiChu(ghiChu)
                        .thanhTien(thanhTien)
                        .build();

                lists.add(dv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public int CheckIn( Integer id) {
        sql = """
                UPDATE [dbo].[LichHen]
                   SET 
                		[TrangThai] = 0
                 WHERE ID = ?
                 """;
        try  {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return 0;
        }
    }

    public int Add(lichHen lh) {
        ArrayList<DvTam> listsDV = new ArrayList<>();
        String sql = """
                INSERT INTO [dbo].[LichHen]
                            ([ID_KH]
                            ,[ID_NV]
                            ,[ID_DV]                               
                            ,[NgayHen]
                            ,[GioHen]                              
                            ,[GhiChu]
                            ,[TrangThai])
                      VALUES
                            (?,?,?,?,?,?,1);
                
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, lh.getId_KH());
            ps.setInt(2, lh.getId_NV());
            ps.setInt(3, lh.getId_DV());
            // Chuyển đổi java.util.Date thành java.sql.Date
            java.util.Date utilDate = lh.getNgayHen();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            ps.setDate(4, sqlDate);
            ps.setTime(5, lh.getGioHen());
            ps.setString(6, lh.getGhiChu());

            int affectedRows = ps.executeUpdate(); // Thực hiện câu lệnh INSERT

            if (affectedRows == 0) {
                throw new SQLException("Creating reservation failed, no rows affected.");
            }

            // Lấy ID của bản ghi mới chèn
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int idLichHen = generatedKeys.getInt(1); // Lấy ID của bản ghi mới chèn
                    // Thực hiện thêm hóa đơn
                    AddHD(idLichHen, lh.getId_KH(), lh.getId_NV());
                    for (DvTam dv : getAllADDDV()) {
                        if (dv != null) {
                            AddDVLH(getByIdHD(idLichHen), dv.getId(), dv.getGiaTien());
                        } else {
                            System.out.println("iddv ko ton tai");
                        }

                    }
                    return idLichHen; // Trả về ID của lịch hẹn mới chèn
                } else {
                    throw new SQLException("Creating reservation failed, no ID obtained.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return -1; // Trả về -1 nếu có lỗi xảy ra
        }
    }

    public int XoaDVTam() {
        String sql = "DELETE FROM DVTam";
        try {
            ps = con.prepareStatement(sql);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected; // Trả về số hàng đã bị xóa
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Trả về -1 nếu có lỗi xảy ra
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<DvTam> getAllADDDV() {
        ArrayList<DvTam> lists = new ArrayList<>();
        sql = """
           SELECT [id]
                 ,[ten]
                 ,[thoiGian]
                 ,[giaTien]
             FROM [dbo].[DVTam]
           """;
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                DvTam dv = DvTam.builder()
                        .id(rs.getInt(1))
                        .tenDV(rs.getString(2))
                        .thoiwGianDV(rs.getString(3))
                        .giaTien(rs.getDouble(4))
                        .build();
                lists.add(dv);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int AddHD(Integer idLichHen, int idKH, int idNV) {

        String sql = """
                     INSERT INTO [dbo].[Hoa_Don]
                                ([Ma_HD]
                                ,[NgayTao]
                                ,[TrangThai]
                                ,[LichHenId]
                                ,[ID_NV]
                                ,[ID_KH]
                     )
                          VALUES
                                (?,?,1,?,?,?);
                     """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "HD" + UUID.randomUUID().toString().substring(0, 7));
            ps.setString(2, String.valueOf(LocalDate.now()));
            ps.setObject(3, idLichHen);
            ps.setObject(4, idNV);
            ps.setObject(5, idKH);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return 0;
        }

    }

    public int getByIdHD(int IdLH) {
        sql = """
        SELECT [ID]
        FROM [dbo].[Hoa_Don] 
        WHERE [LichHenId] = ?
    """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, IdLH);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) { // Kiểm tra nếu có dữ liệu
                return rs.getInt("ID"); // Lấy giá trị ID từ cột ID
            } else {
                // Không có dữ liệu phù hợp
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int AddDVLH(int IDHD, int IdDV, double thanhTien) {
        sql = """
                     INSERT INTO [dbo].[HDCT]
                                ([ID_HD]
                                ,[ID_DV]
                                ,[So_Luong]
                                ,[Thanh_Tien])
                          VALUES
                                (?,?,1,?)
                     """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, IDHD);
            ps.setObject(2, IdDV);

            ps.setObject(3, thanhTien);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return 0;
        }

    }

    public ArrayList<Khach_Hang> getComboBoxKH() {
        ArrayList<Khach_Hang> list = new ArrayList<>();
        sql = """
                 SELECT
                        [ID]
                       ,[Ten_KH]
                       ,[SDT_KH]
                       ,[Email]
                   FROM [dbo].[Khach_Hang]
                 WHERE TrangThai = 1
                 """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt(1); // Lấy ID từ cột thứ nhất
                String ten_KH = rs.getString(2); // Lấy tên khách hàng từ cột thứ hai
                String sdt_KH = rs.getString(3); // Lấy số điện thoại từ cột thứ ba
                String email = rs.getString(4);

                Khach_Hang kh = Khach_Hang.builder()
                        .id(id)
                        .ten_KH(ten_KH)
                        .sdt_KH(sdt_KH)
                        .email(email)
                        .build();

                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public ArrayList<nhanVien> getComboBoxNV() {
        ArrayList<nhanVien> list = new ArrayList<>();
        String sql = """
                 SELECT [ID]
                       ,[Ten_NV]
                   FROM [dbo].[Nhan_Vien]
                     WHERE TrangThai = 1
                 """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt(1); // Lấy ID từ cột thứ nhất
                String ten_NV = rs.getString(2); // Lấy tên khách hàng từ cột thứ hai

                nhanVien nv = nhanVien.builder()
                        .id(id)
                        .ten_NV(ten_NV)
                        .build();

                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public ArrayList<Dich_Vu> getComboBoxDV() {
        ArrayList<Dich_Vu> list = new ArrayList<>();
        String sql = """
                 SELECT [ID]    
                       ,[Ten_DV]
                   FROM [dbo].[Dich_Vu]
                     WHERE TrangThai = 1
                 """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt(1); // Lấy ID từ cột thứ nhất
                String ten_DV = rs.getString(2); // Lấy tên khách hàng từ cột thứ hai

                Dich_Vu dv = Dich_Vu.builder()
                        .id(id)
                        .ten_DV(ten_DV)
                        .build();
                list.add(dv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<LichHenResponse> getReservationsByDate(Date date) {
        List<LichHenResponse> listlh = new ArrayList<>();
        String query = """
                       SELECT dbo.LichHen.ID, dbo.LichHen.NgayHen, dbo.LichHen.GioHen, dbo.LichHen.GhiChu, dbo.LichHen.ID_KH, dbo.Khach_Hang.Ten_KH, dbo.LichHen.ID_NV, dbo.Nhan_Vien.Ten_NV, dbo.LichHen.ID_DV, 
                                                 dbo.Dich_Vu.Ten_DV, dbo.Khach_Hang.SDT_KH
                                    FROM   dbo.LichHen INNER JOIN
                                                 dbo.Khach_Hang ON dbo.LichHen.ID_KH = dbo.Khach_Hang.ID INNER JOIN
                                                 dbo.Nhan_Vien ON dbo.LichHen.ID_NV = dbo.Nhan_Vien.ID INNER JOIN
                                                 dbo.Dich_Vu ON dbo.LichHen.ID_DV = dbo.Dich_Vu.ID
                       WHERE dbo.LichHen.NgayHen = ?
                       """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                LichHenResponse lhrp = new LichHenResponse();
                lhrp.setIdLH(rs.getInt(1));
                lhrp.setNgayHen(rs.getDate(2));
                lhrp.setGioHen(rs.getTime(3));
                lhrp.setGhiChu(rs.getString(4));
                lhrp.setId_KH(rs.getInt(5));
                lhrp.setTen_KH(rs.getString(6));
                lhrp.setId_NV(rs.getInt(7));
                lhrp.setTen_NV(rs.getString(8));
                lhrp.setId_DV(rs.getInt(9));
                lhrp.setTen_DV(rs.getString(10));
                lhrp.setSdt_KH(rs.getString(11));

                listlh.add(lhrp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listlh;
    }

    public ArrayList<LichHenResponse> lichHenThanhToan() {
        ArrayList<LichHenResponse> lists = new ArrayList<>();
        sql = """
           SELECT dbo.Hoa_Don.ID, dbo.Hoa_Don.Ma_HD, dbo.Khach_Hang.ID AS Expr1, dbo.Khach_Hang.Ten_KH, dbo.Khach_Hang.SDT_KH, dbo.LichHen.NgayHen, dbo.LichHen.GioHen, dbo.LichHen.TienCoc, dbo.Hoa_Don.TongTien, 
                                            dbo.LichHen.TrangThai,dbo.Hoa_Don.LichHenId
                          FROM     dbo.Hoa_Don INNER JOIN
                                            dbo.Khach_Hang ON dbo.Hoa_Don.ID_KH = dbo.Khach_Hang.ID INNER JOIN
                                            dbo.LichHen ON dbo.Hoa_Don.LichHenId = dbo.LichHen.ID where NgayHen = ? and dbo.LichHen.TrangThai = 1
             """;

        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, LocalDate.now());
            rs = ps.executeQuery();
            while (rs.next()) {
                LichHenResponse respone = LichHenResponse.builder()
                        .idHD(rs.getInt(1))
                        .maHD(rs.getString(2))
                        .idKH(rs.getInt(3))
                        .tenKh(rs.getString(4))
                        .sdt_KH(rs.getString(5))
                        .ngayHen(rs.getDate(6))
                        .gioHen(rs.getTime(7))
                        .tienCoc(rs.getFloat(8))
                        .tongTien(rs.getFloat(9))
                        .trangThaiLH(rs.getInt(10))
                        .idLH(rs.getInt(11))
                        .build();
                lists.add(respone);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LichHenRepository().LichSuDatLich());
    }
}
