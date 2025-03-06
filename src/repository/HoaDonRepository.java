/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

/**
 *
 * @author Admin
 */
import config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import response.HoaDonRespone;

public class HoaDonRepository {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public HoaDonRepository() {
        con = DBConnect.getConnection();
    }

    public ArrayList<HoaDonRespone> getAll() {
        sql = """
            SELECT dbo.Hoa_Don.Ma_HD, dbo.Khach_Hang.Ten_KH, dbo.Khach_Hang.SDT_KH, dbo.Hoa_Don.NgayTao, dbo.Hoa_Don.NgaySua, dbo.Hoa_Don.TongTien, dbo.Nhan_Vien.Ma_NV, dbo.Hoa_Don.TrangThai
            FROM     dbo.Hoa_Don INNER JOIN
                              dbo.Khach_Hang ON dbo.Hoa_Don.ID_KH = dbo.Khach_Hang.ID INNER JOIN
                              dbo.Nhan_Vien ON dbo.Hoa_Don.ID_NV = dbo.Nhan_Vien.ID
            """;
        ArrayList<HoaDonRespone> lists = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            DateTimeFormatter fomat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            while (rs.next()) {
                HoaDonRespone respone = HoaDonRespone.builder()
                        .maHD(rs.getString(1))
                        .tenKH(rs.getString(2))
                        .sdtKH(rs.getString(3))
                        .ngayTao(LocalDateTime.parse(rs.getString(4), fomat))
                        .ngaySua(rs.getString(5) != null ? LocalDateTime.parse(rs.getString(5), fomat) : null)
                        .tongTien(rs.getFloat(6))
                        .maNV(rs.getString(7))
                        .trangThai(rs.getInt(8))
                        .build();
                lists.add(respone);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HoaDonRespone> search(int trangThai, String text) {
        ArrayList<HoaDonRespone> lists = new ArrayList<>();
        sql = """
            SELECT dbo.Hoa_Don.Ma_HD, dbo.Khach_Hang.Ten_KH, dbo.Khach_Hang.SDT_KH, dbo.Hoa_Don.NgayTao, dbo.Hoa_Don.TongTien, dbo.Nhan_Vien.Ma_NV, dbo.Hoa_Don.TrangThai
                        FROM     dbo.Hoa_Don INNER JOIN
                                          dbo.Khach_Hang ON dbo.Hoa_Don.ID_KH = dbo.Khach_Hang.ID INNER JOIN
                                          dbo.Nhan_Vien ON dbo.Hoa_Don.ID_NV = dbo.Nhan_Vien.ID where dbo.Hoa_Don.TrangThai =?
            """;

        if (text.length() > 0) {
            sql += """
                         and(
                                dbo.Hoa_Don.Ma_HD like ?
                                or
                                dbo.Khach_Hang.Ten_KH like ?
                                or
                                dbo.Khach_Hang.SDT_KH like ?
                                or
                                dbo.Nhan_Vien.Ma_NV like ?
                                or
                                 dbo.Hoa_Don.NgayTao like ?
                         )
                         """;
        }

        try {
            ps = con.prepareStatement(sql);
            int index = 1;
            ps.setObject(index++, trangThai);
            String value = "%" + text + "%";
            if (text.length() > 0) {
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
            }

            rs = ps.executeQuery();
            DateTimeFormatter fomat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            while (rs.next()) {
                HoaDonRespone respone = HoaDonRespone.builder()
                        .maHD(rs.getString(1))
                        .tenKH(rs.getString(2))
                        .sdtKH(rs.getString(3))
                        .ngayTao(LocalDateTime.parse(rs.getString(4), fomat))
//                        .ngaySua(rs.getString(5) != null ? LocalDateTime.parse(rs.getString(5), fomat) : null)
                        .tongTien(rs.getFloat(5))
                        .maNV(rs.getString(6))
                        .trangThai(rs.getInt(7))
                        .build();
                lists.add(respone);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HoaDonRespone ScanQRcode(String maHD) {
        sql = """
            SELECT dbo.Hoa_Don.Ma_HD, dbo.Khach_Hang.Ten_KH, dbo.Khach_Hang.SDT_KH, dbo.Hoa_Don.NgayTao, dbo.Hoa_Don.NgaySua, dbo.Hoa_Don.TongTien, dbo.Nhan_Vien.Ma_NV, dbo.Hoa_Don.TrangThai
            FROM     dbo.Hoa_Don INNER JOIN
                              dbo.Khach_Hang ON dbo.Hoa_Don.ID_KH = dbo.Khach_Hang.ID INNER JOIN
                              dbo.Nhan_Vien ON dbo.Hoa_Don.ID_NV = dbo.Nhan_Vien.ID where dbo.Hoa_Don.Ma_HD = ?
            """;
        HoaDonRespone hd = null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, maHD);
            rs = ps.executeQuery();
            DateTimeFormatter fomat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            while (rs.next()) {
                hd = HoaDonRespone.builder()
                        .maHD(rs.getString(1))
                        .tenKH(rs.getString(2))
                        .sdtKH(rs.getString(3))
                        .ngayTao(LocalDateTime.parse(rs.getString(4), fomat))
                        .ngaySua(rs.getString(5) != null ? LocalDateTime.parse(rs.getString(5), fomat) : null)
                        .tongTien(rs.getFloat(6))
                        .maNV(rs.getString(7))
                        .trangThai(rs.getInt(8))
                        .build();
            }
            return hd;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int updateTrangThaiHD(int idHD, int idLH){
        sql="""
            UPDATE [dbo].[Hoa_Don]
               SET 
                 [TrangThai] =0
                 
             WHERE ID =?
            
            UPDATE [dbo].[LichHen]
               SET 
                  [TrangThai] = 0
             WHERE ID =?
            """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, idHD);
            ps.setObject(2, idLH);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
//    public static void main(String[] args) {
//        System.out.println(new HoaDonRepository().search(1, "0"));
//    }
}
