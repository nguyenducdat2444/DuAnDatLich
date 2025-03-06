/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import entity.Dich_Vu;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import entity.Khach_Hang;
import java.security.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import response.KhachHangResponse;

/**
 *
 * @author admin
 */
public class KhachHangRepository {

    public ArrayList<Khach_Hang> getAll() {
        ArrayList<Khach_Hang> lists = new ArrayList<>();
        String sql = """
            SELECT  
                  [ID]
                ,[Ma_KH]
                ,[Ten_KH]
                ,[SDT_KH]
                ,[GioiTinh]
                ,[NgaySinh]
                ,[DiaChi]
                ,[Email]  
                ,[NgayTao]
                ,[TrangThai]               
            FROM [dbo].[Khach_Hang]
            WHERE [TrangThai] = 1
                    
        """;
        //ORDER BY [NgayThem] DESC; -- Sắp xếp theo ngày thêm, mới nhất trước
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Khach_Hang kh = new Khach_Hang();
                kh.setId(rs.getInt(1));
                kh.setMa_KH(rs.getString(2));
                kh.setTen_KH(rs.getString(3));
                kh.setSdt_KH(rs.getString(4));
                kh.setGioiTinh(rs.getInt(5));
                kh.setNgaySinh(rs.getDate(6));
                kh.setDiaChi(rs.getString(7));
                kh.setEmail(rs.getString(8));
                kh.setNgayTao(rs.getDate(9));
                kh.setTrangThai(rs.getInt(10));

                lists.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public ArrayList<Khach_Hang> getPhanTrang(int page, int limit) {
        ArrayList<Khach_Hang> lists = new ArrayList<>();
        String sql = """
            SELECT  
                  [ID]
                ,[Ma_KH]
                ,[Ten_KH]
                ,[SDT_KH]
                ,[GioiTinh]
                ,[NgaySinh]
                ,[DiaChi]
                ,[Email]  
                ,[NgayTao]
                ,[TrangThai]               
            FROM [dbo].[Khach_Hang]
            WHERE [TrangThai] = 1
                    
        """;
        sql += "ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        //ORDER BY [NgayThem] DESC; -- Sắp xếp theo ngày thêm, mới nhất trước
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, (page - 1) * limit + 1);
            ps.setObject(2, limit);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Khach_Hang kh = new Khach_Hang();
                kh.setId(rs.getInt(1));
                kh.setMa_KH(rs.getString(2));
                kh.setTen_KH(rs.getString(3));
                kh.setSdt_KH(rs.getString(4));
                kh.setGioiTinh(rs.getInt(5));
                kh.setNgaySinh(rs.getDate(6));
                kh.setDiaChi(rs.getString(7));
                kh.setEmail(rs.getString(8));
                kh.setNgayTao(rs.getDate(9));
                kh.setTrangThai(rs.getInt(10));

                lists.add(kh);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public Khach_Hang chucVu(String ten, String sdt) {
        String sql = """
        SELECT  
              [ID]
            ,[Ma_KH]
            ,[Ten_KH]
            ,[SDT_KH]
            ,[GioiTinh]
            ,[NgaySinh]
            ,[DiaChi]
            ,[Email]  
            ,[TrangThai]               
        FROM [dbo].[Khach_Hang]
        WHERE [Ten_KH]= ? AND [SDT_KH] = ?;
    """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ten); // Sử dụng setString cho chuỗi
            ps.setString(2, sdt); // Sử dụng setString cho chuỗi

            ResultSet rs = ps.executeQuery();

            while (rs.next()) { // Sử dụng if thay vì while nếu chỉ mong đợi một kết quả
                Khach_Hang kh = Khach_Hang.builder()
                        .id(rs.getInt("ID"))
                        .ma_KH(rs.getString("Ma_KH"))
                        .ten_KH(rs.getString("Ten_KH"))
                        .sdt_KH(rs.getString("SDT_KH"))
                        .gioiTinh(rs.getInt("GioiTinh"))
                        .ngaySinh(rs.getDate("NgaySinh"))
                        .diaChi(rs.getString("DiaChi"))
                        .email(rs.getString("Email"))
                        .trangThai(rs.getInt("TrangThai"))
                        .build();

                return kh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy khách hàng
    }

    public int Add(Khach_Hang kh) {

        String sql = """
                     INSERT INTO [dbo].[Khach_Hang]
                                ([Ma_KH]
                                ,[Ten_KH]
                                ,[SDT_KH]
                                ,[GioiTinh]
                                ,[NgaySinh]
                                ,[DiaChi]
                                ,[Email]  
                                ,[NgayTao]                                 
                                ,[TrangThai]
                                )
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,? 
                                ,?
                                ,?
                                ,1
                                )
                     """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kh.getMa_KH());
            ps.setString(2, kh.getTen_KH());
            ps.setString(3, kh.getSdt_KH());
            ps.setInt(4, kh.getGioiTinh());
            ps.setObject(5, kh.getNgaySinh());
            ps.setString(6, kh.getDiaChi());
            ps.setString(7, kh.getEmail());
            ps.setObject(8, kh.getNgayTao());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return 1;

    }

    public int Update(Khach_Hang kh, Integer ID) {
        String sql = """
                 UPDATE [dbo].[Khach_Hang]
                 SET  
                     [Ten_KH] = ?,
                     [GioiTinh] = ?,
                     [NgaySinh] = ?,
                     [DiaChi] = ?,
                     [SDT_KH] = ?,      
                     [Email] = ?                           
                 WHERE ID = ?
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, kh.getTen_KH());
            ps.setObject(2, kh.getGioiTinh());
            ps.setObject(3, kh.getNgaySinh());
            ps.setString(4, kh.getDiaChi());
            ps.setString(5, kh.getSdt_KH());
            ps.setObject(6, kh.getEmail());
            ps.setInt(7, ID);

            // Trả về số hàng bị ảnh hưởng bởi lệnh UPDATE
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        // Trả về 0 nếu có ngoại lệ xảy ra
        return 0;
    }

    public int Remove(Integer id) {

        String sql = """
                     UPDATE [dbo].[Khach_Hang]
                                   SET [TrangThai] = 0                                   
                                 WHERE ID = ?
                     """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }

        return 1;
    }

    public ArrayList<Khach_Hang> findCustomerByAll(Integer minAge, Integer maxAge, Integer GioiTinh, String keyword) {
        ArrayList<Khach_Hang> lists = new ArrayList<>();

        // Tạo StringBuilder để xây dựng câu lệnh SQL
        StringBuilder sql = new StringBuilder(
                "SELECT "
                + "[Ma_KH], "
                + "[Ten_KH], "
                + "[GioiTinh], "
                + "[NgaySinh], "
                + "[DiaChi], "
                + "[SDT_KH], "
                + "[Email] "
                + "FROM [dbo].[Khach_Hang] "
                + "WHERE DATEDIFF(YEAR, [NgaySinh], GETDATE()) BETWEEN ? AND ?"
        );
        // Thêm điều kiện gioiTinh nếu có
        if (GioiTinh != null) {
            sql.append(" AND [GioiTinh] = ?");
        }

        // Thêm điều kiện tìm kiếm nếu có
        if (!keyword.isEmpty()) {
            sql.append("""
              AND 
                (
                  [Ten_KH] LIKE ?
                  OR 
                  [Email] LIKE ?
                  OR 
                  [SDT_KH] LIKE ?  
                )
              """);
        }

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            int index = 1; // Vị trí của dấu hỏi chấm đầu tiên
            ps.setInt(index++, minAge); // Thiết lập tham số minAge
            ps.setInt(index++, maxAge); // Thiết lập tham số maxAge
            // Thiết lập tham số GioiTinh nếu có
            if (GioiTinh != null) {
                ps.setInt(index++, GioiTinh);
            }
            if (!keyword.isEmpty()) {
                String value = "%" + keyword + "%";
                // Thiết lập các tham số cho các điều kiện tìm kiếm
                ps.setString(index++, value); // Thiết lập giá trị cho Ten_KH
                ps.setString(index++, value); // Thiết lập giá trị cho Email
                ps.setString(index++, value); // Thiết lập giá trị cho SDT_KH
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Khach_Hang kh = new Khach_Hang();
                    //kh.setId(rs.getInt("MaKH")); // Use column name
                    kh.setMa_KH(rs.getString("Ma_KH")); // Use column name
                    kh.setTen_KH(rs.getString("Ten_KH")); // Use column name
                    kh.setGioiTinh((Integer) rs.getObject("GioiTinh")); // Use column name
                    kh.setNgaySinh(rs.getDate("NgaySinh")); // Use column name
                    kh.setDiaChi(rs.getString("DiaChi")); // Use column name
                    kh.setSdt_KH(rs.getString("SDT_KH")); // Use column name
                    kh.setEmail(rs.getString("Email")); // Use column name

                    lists.add(kh);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return lists;
    }

//    public ArrayList<Khach_Hang> findCustomerBySex(Boolean gioiTinh) {
//        ArrayList<Khach_Hang> lists = new ArrayList<>();
//
//        String sql = """
//                SELECT [Ma_KH]
//                                                                      ,[Ten_KH]
//                                                                      ,[GioiTinh]
//                                                                      ,[NgaySinh]
//                                                                      ,[DiaChi]
//                                                                      ,[SDT_KH]
//                                                                      ,[Email]                
//                  FROM [dbo].[Khach_Hang]
//                WHERE gioiTinh = ?
//                 """;
//
//        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setBoolean(1, gioiTinh); // Set the value for the LIKE clause
//            try (ResultSet rs = ps.executeQuery()) {
//                while (rs.next()) {
//                    Khach_Hang kh = new Khach_Hang();
//                    //kh.setId(rs.getInt("MaKH")); // Use column name
//                    kh.setMa_KH(rs.getString("MaKH")); // Use column name
//                    kh.setTen_KH(rs.getString("TenKH")); // Use column name
//                    kh.setGioiTinh((Integer) rs.getObject("GioiTinh")); // Use column name
//                    kh.setNgaySinh(rs.getDate("NgaySinh")); // Use column name
//                    kh.setDiaChi(rs.getString("DiaChi")); // Use column name
//                    kh.setSdt_KH(rs.getString("SDT_KH")); // Use column name
//                    kh.setEmail(rs.getString("Email")); // Use column name
//
//                    lists.add(kh);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace(); // Log the exception for debugging
//        }
//        return lists;
//    }
    public ArrayList<Khach_Hang> findCustomerByAge(Integer minAge, Integer maxAge) {
        ArrayList<Khach_Hang> lists = new ArrayList<>();

        String sql = """
                SELECT [Ma_KH]
                                  ,[Ten_KH]
                                  ,[GioiTinh]
                                  ,[NgaySinh]
                                  ,[DiaChi]
                                  ,[SDT_KH]
                                  ,[Email]
                            FROM [dbo].[Khach_Hang]
                            WHERE DATEDIFF(YEAR, [NgaySinh], GETDATE()) BETWEEN ? AND ?;
                 """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, minAge);
            ps.setInt(2, maxAge);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Khach_Hang kh = new Khach_Hang();
                    //kh.setId(rs.getInt("MaKH")); // Use column name
                    kh.setMa_KH(rs.getString("Ma_KH")); // Use column name
                    kh.setTen_KH(rs.getString("Ten_KH")); // Use column name
                    kh.setGioiTinh((Integer) rs.getObject("GioiTinh")); // Use column name
                    kh.setNgaySinh(rs.getDate("NgaySinh")); // Use column name
                    kh.setDiaChi(rs.getString("DiaChi")); // Use column name
                    kh.setSdt_KH(rs.getString("SDT_KH")); // Use column name
                    kh.setEmail(rs.getString("Email")); // Use column name

                    lists.add(kh);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        }
        return lists;
    }

    public ArrayList<KhachHangResponse> LichSuDatLich() {
        ArrayList<KhachHangResponse> lists = new ArrayList<>();
        String sql = """
            SELECT       dbo.Khach_Hang.Ten_KH, dbo.Dich_Vu.Ten_DV, dbo.LichHen.NgayHen, dbo.LichHen.GioHen, dbo.LichHen.TrangThai, dbo.LichHen.TienCoc, dbo.LichHen.GhiChu
            FROM          dbo.Khach_Hang    INNER JOIN
                                     dbo.Dich_Vu ON dbo.Dich_Vu.ID = dbo.Khach_Hang.ID INNER JOIN
                                     dbo.LichHen ON dbo.Dich_Vu.ID = dbo.LichHen.ID_DV AND dbo.Khach_Hang.ID = dbo.LichHen.ID_KH
        """;
        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tenKH = rs.getString("Ten_KH"); // Đọc chuỗi
                String tenDV = rs.getString("Ten_DV"); // Đọc chuỗi
                Date ngayHen = rs.getDate("NgayHen");
                Time gioHen = rs.getTime("GioHen"); // Đọc Time
                int trangThai = rs.getInt("TrangThai"); // Đọc số nguyên
                double tienCoc = rs.getDouble("TienCoc"); // Đọc số thực
                String ghiChu = rs.getString("GhiChu"); // Đọc chuỗi
                //LocalDateTime toLocalDateTime = null;

                // Tạo đối tượng KhachHangResponse
                KhachHangResponse dv = KhachHangResponse.builder()
                        //            .id(id) // Đọc ID nếu là int
                        .ten_KH(tenKH)
                        .ten_DV(tenDV)
                        .ngayHen(ngayHen) // Nếu thuộc tính là LocalDateTime
                        .gioHen(gioHen)
                        .trangThai(trangThai)
                        .tien_Coc(tienCoc)
                        .ghiChu(ghiChu)
                        .build();

                lists.add(dv);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists;
    }

    public ArrayList<Khach_Hang> getThongTinCaNhan(String sdt) {
        ArrayList<Khach_Hang> lists = new ArrayList<>();
        String sql = """
        SELECT 
               [Ma_KH]
              ,[Ten_KH]
              ,[SDT_KH]
              ,[GioiTinh]
              ,[DiaChi]
              ,[Email]
              ,[NgaySinh]
          FROM [dbo].[Khach_Hang]
          WHERE [SDT_KH] = ?          
    """;

        try (Connection conn = DBConnect.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            // Gán tham số số điện thoại
            ps.setString(1, sdt);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Khach_Hang kh = new Khach_Hang();

                // Gán giá trị từ ResultSet vào đối tượng Khach_Hang
                kh.setMa_KH(rs.getString("Ma_KH"));
                kh.setTen_KH(rs.getString("Ten_KH"));
                kh.setSdt_KH(rs.getString("SDT_KH"));
                kh.setGioiTinh(rs.getInt("GioiTinh"));
                kh.setDiaChi(rs.getString("DiaChi"));
                kh.setEmail(rs.getString("Email"));
                kh.setNgaySinh(rs.getDate("NgaySinh"));
                // kh.setSdt_KH(sdt); // Dòng này không cần thiết vì sdt đã được gán trong câu truy vấn

                lists.add(kh);
            }

        } catch (Exception e) {
            // In ra lỗi tổng quát
            e.printStackTrace();
        }
        return lists;
    }
}
