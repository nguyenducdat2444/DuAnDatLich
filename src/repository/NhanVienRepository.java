/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;

import entity.nhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import response.nhanVienResponse;

/**
 *
 * @author default
 */
public class NhanVienRepository {

    public List<nhanVienResponse> getAll() {
        //B1: tạo cau 
        String sql = """
       SELECT dbo.Nhan_Vien.ID, dbo.Nhan_Vien.Ma_NV, dbo.Nhan_Vien.Ten_NV, dbo.Nhan_Vien.GioiTinh, dbo.Nhan_Vien.Email, dbo.Nhan_Vien.DiaChi, dbo.Nhan_Vien.SDT, dbo.Nhan_Vien.NgaySinh, dbo.Nhan_Vien.ID_Role, dbo.Nhan_Vien.ID_CV, dbo.CongViec.TenCV, 
                                     dbo.Nhan_Vien.TrangThai
                        FROM   dbo.Nhan_Vien INNER JOIN
                                     dbo.CongViec ON dbo.Nhan_Vien.ID_CV = dbo.CongViec.ID where dbo.Nhan_Vien.TrangThai =1  """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql

        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<nhanVienResponse> list = new ArrayList<>();
            while (rs.next()) {
                nhanVienResponse kh = nhanVienResponse.builder()
                        .id(rs.getInt(1))
                        .ma_NV(rs.getString(2))
                        .ten_NV(rs.getString(3))
                        .gioiTinh(rs.getInt(4))
                        .email(rs.getString(5))
                        .diaChi(rs.getString(6))
                        .sdt_NV(rs.getString(7))
                        .ngaySinh(rs.getDate(8))
                        .id_Role(rs.getInt(9))
                        .id_CongViec(rs.getInt(10))
                        .tenCV(rs.getString(11))
                        .trangThai(rs.getInt(12))
                        .build();
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            //loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public List<nhanVienResponse> PhanTrang(int page, int limit) {
        //B1: tạo cau 
        String sql = """
       SELECT dbo.Nhan_Vien.ID, dbo.Nhan_Vien.Ma_NV, dbo.Nhan_Vien.Ten_NV, dbo.Nhan_Vien.GioiTinh, dbo.Nhan_Vien.Email, dbo.Nhan_Vien.DiaChi, dbo.Nhan_Vien.SDT, dbo.Nhan_Vien.NgaySinh, dbo.Nhan_Vien.ID_Role, dbo.Nhan_Vien.ID_CV, dbo.CongViec.TenCV, 
                                     dbo.Nhan_Vien.TrangThai
                        FROM   dbo.Nhan_Vien INNER JOIN
                                     dbo.CongViec ON dbo.Nhan_Vien.ID_CV = dbo.CongViec.ID where dbo.Nhan_Vien.TrangThai =1  """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        sql += "ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, (page - 1) * limit + 1);
            ps.setObject(2, limit);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<nhanVienResponse> list = new ArrayList<>();
            while (rs.next()) {
                nhanVienResponse kh = nhanVienResponse.builder()
                        .id(rs.getInt(1))
                        .ma_NV(rs.getString(2))
                        .ten_NV(rs.getString(3))
                        .gioiTinh(rs.getInt(4))
                        .email(rs.getString(5))
                        .diaChi(rs.getString(6))
                        .sdt_NV(rs.getString(7))
                        .ngaySinh(rs.getDate(8))
                        .id_Role(rs.getInt(9))
                        .id_CongViec(rs.getInt(10))
                        .tenCV(rs.getString(11))
                        .trangThai(rs.getInt(12))
                        .build();
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            //loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean add(nhanVien kh) {
        int check = 0;
        String sql = """
                   	INSERT INTO [dbo].[Nhan_Vien]
                                   ([Ma_NV]
                                   ,[Ten_NV]
                                   ,[GioiTinh]
                                   ,[Email]
                                   ,[DiaChi]
                                   ,[SDT]
                                   ,[NgaySinh]
                                   ,[ID_Role]
                                   ,[ID_CV]
                                   ,[TrangThai])
                             VALUES
                                   (?,?,?,?,?,?,?,2,?,1)
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getMa_NV());
            ps.setString(2, kh.getTen_NV());
            ps.setInt(3, kh.getGioiTinh());
            ps.setObject(4, kh.getEmail());

            ps.setString(5, kh.getDiaChi());
            ps.setString(6, kh.getSdt_NV());
            ps.setObject(7, kh.getNgaySinh());
            ps.setInt(8, kh.getId_CongViec());

            //trang thái = 1 => chưa xóa
            //trạng thái =0 => xóa
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public boolean xoa(Integer id) {
        int check = 0;
        String sql = """
                    UPDATE [dbo].[Nhan_Vien]
                    SET 
                    [TrangThai] = 0
                    WHERE [id]=?
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            //trang thái = 1 => chưa xóa
            //trạng thái =0 => xóa
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public int up(nhanVien Kh) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sSQL = """
          UPDATE [dbo].[Nhan_Vien]
                               SET 
                                  [Ten_NV] = ?
                                  ,[GioiTinh] = ?
                                  ,[Email] =?
                                  ,[DiaChi] = ?
                                  ,[SDT] = ?
                                  ,[NgaySinh] = ?
                                 
                                  ,[ID_CV] = ?
                                 
                             WHERE [Ma_NV]=?
                          """;
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sSQL);
            ps.setString(1, Kh.getTen_NV());
            ps.setInt(2, Kh.getGioiTinh());
            ps.setObject(3, Kh.getEmail());
            ps.setString(5, Kh.getSdt_NV());
            ps.setString(4, Kh.getDiaChi());
            ps.setObject(6, Kh.getNgaySinh());
            ps.setInt(7, Kh.getId_CongViec());
            ps.setString(8, Kh.getMa_NV());

            if (ps.executeUpdate() > 0) {
                System.out.println("upDate Thanh cong");

                return 1;
            }

        } catch (Exception e) {
            System.out.println("Error:" + e.toString());
        } finally {
            try {
                conn.close();
                ps.close();
            } catch (Exception e) {
            }
        }
        return -1;
    }

    public List<nhanVien> getNhanVien() {
        //B1: tạo cau 
        String sql = """
                 	SELECT [ID]
                              ,[Ma_NV]
                              ,[Ten_NV]
                              ,[GioiTinh]
                              ,[Email]
                              ,[DiaChi]
                              ,[SDT]
                              ,[NgaySinh]
                              ,[ID_Role]
                              ,[ID_CV]
                              ,[TrangThai]
                          FROM [dbo].[Nhan_Vien]""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<nhanVien> list = new ArrayList<>();
            while (rs.next()) {
                nhanVien kh = nhanVien.builder()
                        .id(rs.getInt(1))
                        .ma_NV(rs.getString(2))
                        .ten_NV(rs.getString(3))
                        .gioiTinh(rs.getInt(4))
                        .email(rs.getString(5))
                        .diaChi(rs.getString(6))
                        .sdt_NV(rs.getString(7))
                        .ngaySinh(rs.getDate(8))
                        .id_Role(rs.getInt(9))
                        .id_CongViec(rs.getInt(10))
                        .trangThai(rs.getInt(11))
                        .build();
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            //loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public nhanVien getChuVuNhanVien(String ten) {
        //B1: tạo cau 
        String sql = """
            SELECT [ID]
                                            ,[Ma_NV]
                                            ,[Ten_NV]
                                            ,[GioiTinh]
                                            ,[Email]
                                            ,[DiaChi]
                                            ,[SDT]
                                            ,[NgaySinh]
                                            ,[ID_Role]
                                            ,[ID_CV]
                                            ,[TrangThai]
                                        FROM [dbo].[Nhan_Vien] where [Ten_NV]=?""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)

            while (rs.next()) {
                nhanVien nvr = nhanVien.builder()
                        .id(rs.getInt(1))
                        .ma_NV(rs.getString(2))
                        .ten_NV(rs.getString(3))
                        .gioiTinh(rs.getInt(4))
                        .email(rs.getString(5))
                        .diaChi(rs.getString(6))
                        .sdt_NV(rs.getString(7))
                        .ngaySinh(rs.getDate(8))
                        .id_Role(rs.getInt(9))
                        .id_CongViec(rs.getInt(10))
                        .trangThai(rs.getInt(11))
                        .build();

                return nvr;
//     
            }

        } catch (Exception e) {
            //loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;

    }

    public List<nhanVienResponse> Search(String trangThai,Integer minAge, Integer maxAge, String KeyWord) {
        //B1: tạo cau 
        String sql = """
       SELECT dbo.Nhan_Vien.ID, dbo.Nhan_Vien.Ma_NV, dbo.Nhan_Vien.Ten_NV, dbo.Nhan_Vien.GioiTinh, dbo.Nhan_Vien.Email, dbo.Nhan_Vien.DiaChi, dbo.Nhan_Vien.SDT, dbo.Nhan_Vien.NgaySinh, dbo.Nhan_Vien.ID_Role, dbo.Nhan_Vien.ID_CV, dbo.CongViec.TenCV, 
                                     dbo.Nhan_Vien.TrangThai
                        FROM   dbo.Nhan_Vien INNER JOIN
                                     dbo.CongViec ON dbo.Nhan_Vien.ID_CV = dbo.CongViec.ID where DATEDIFF(YEAR, [NgaySinh], GETDATE()) BETWEEN ? AND ? AND dbo.CongViec.TenCV=? """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        if (KeyWord.length() > 0) {
            sql += """
                and (
                dbo.Nhan_Vien.Ma_NV like ? or dbo.Nhan_Vien.Ten_NV like ? or dbo.Nhan_Vien.Email like ? or  dbo.Nhan_Vien.SDT like ?)
                
                """;
        }
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            int index = 1;//vị trí đầu tiên của ?
           
            ps.setObject(index++, minAge);
            ps.setObject(index++, maxAge);
            ps.setObject(index++, trangThai);
            if (KeyWord.length() > 0) {
                String value = "%" + KeyWord + "%";
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
            }
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<nhanVienResponse> list = new ArrayList<>();
            while (rs.next()) {
                nhanVienResponse kh = nhanVienResponse.builder()
                        .id(rs.getInt(1))
                        .ma_NV(rs.getString(2))
                        .ten_NV(rs.getString(3))
                        .gioiTinh(rs.getInt(4))
                        .email(rs.getString(5))
                        .diaChi(rs.getString(6))
                        .sdt_NV(rs.getString(7))
                        .ngaySinh(rs.getDate(8))
                        .id_Role(rs.getInt(9))
                        .id_CongViec(rs.getInt(10))
                        .tenCV(rs.getString(11))
                        .trangThai(rs.getInt(12))
                        .build();
                list.add(kh);
            }
            return list;
        } catch (Exception e) {
            //loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

    public  nhanVien chucVu(String Ten) {
        //B1: tạo cau 
        String sql = """
                 	SELECT [ID]
                              ,[Ma_NV]
                              ,[Ten_NV]
                              ,[GioiTinh]
                              ,[Email]
                              ,[DiaChi]
                              ,[SDT]
                              ,[NgaySinh]
                              ,[ID_Role]
                              ,[ID_CV]
                              ,[TrangThai]
                          FROM [dbo].[Nhan_Vien]
                          WHERE [Ten_NV] = ?
                     """;
        
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setString(1, Ten); // Sử dụng setString cho chuỗi
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
          
            while (rs.next()) {
                nhanVien kh = nhanVien.builder()
                        .id(rs.getInt(1))
                        .ma_NV(rs.getString(2))
                        .ten_NV(rs.getString(3))
                        .gioiTinh(rs.getInt(4))
                        .email(rs.getString(5))
                        .diaChi(rs.getString(6))
                        .sdt_NV(rs.getString(7))
                        .ngaySinh(rs.getDate(8))
                        .id_Role(rs.getInt(9))
                        .id_CongViec(rs.getInt(10))
                        .trangThai(rs.getInt(11))
                        .build();
                return kh;
            }
           
        } catch (Exception e) {
            //loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

   
}
