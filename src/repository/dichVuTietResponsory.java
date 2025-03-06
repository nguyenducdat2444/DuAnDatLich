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
import java.util.List;
import response.DichVuRespon;

/**
 *
 * @author default
 */
public class dichVuTietResponsory {

    public List<DichVuRespon> getAll() {
        //B1: tạo cau 
        String sql = """
                 	SELECT dbo.Dich_Vu.ID, dbo.Dich_Vu.Ma_DV, dbo.Dich_Vu.Ten_DV, dbo.Dich_Vu.Anh,
                                                dbo.Dich_Vu.ThoiGianDV, dbo.Dich_Vu.GiaTien, dbo.Dich_Vu.MoTa, dbo.Dich_Vu.ID_LoaiDV, 
                                              dbo.Loai_DV.TenDV, dbo.Dich_Vu.TrangThai
                                                FROM   dbo.Dich_Vu INNER JOIN
                                             dbo.Loai_DV ON dbo.Dich_Vu.ID_LoaiDV = dbo.Loai_DV.ID """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<DichVuRespon> list = new ArrayList<>();
            while (rs.next()) {
                DichVuRespon kh = DichVuRespon.builder()
                        .id(rs.getInt(1))
                        .ma_DV(rs.getString(2))
                        .ten_DV(rs.getString(3))
                        .anh(rs.getString(4))
                        .ThoiGianDV(rs.getTime(5))
                        .gia_Tien(rs.getDouble(6))
                        .moTa(rs.getString(7))
                        .id_LoaiDV(rs.getInt(8))
                        .ten_LoaiDV(rs.getString(9))
                        .trangThai(rs.getInt(10))
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

    public List<DichVuRespon> getAll1() {
        //B1: tạo cau 
        String sql = """
                 	SELECT dbo.Dich_Vu.ID, dbo.Dich_Vu.Ma_DV, dbo.Dich_Vu.Ten_DV, dbo.Dich_Vu.Anh,
                                                dbo.Dich_Vu.ThoiGianDV, dbo.Dich_Vu.GiaTien, dbo.Dich_Vu.MoTa, dbo.Dich_Vu.ID_LoaiDV, 
                                              dbo.Loai_DV.TenDV, dbo.Dich_Vu.TrangThai
                                                FROM   dbo.Dich_Vu INNER JOIN
                                             dbo.Loai_DV ON dbo.Dich_Vu.ID_LoaiDV = dbo.Loai_DV.ID where dbo.Dich_Vu.TrangThai=1""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<DichVuRespon> list = new ArrayList<>();
            while (rs.next()) {
                DichVuRespon kh = DichVuRespon.builder()
                        .id(rs.getInt(1))
                        .ma_DV(rs.getString(2))
                        .ten_DV(rs.getString(3))
                        .anh(rs.getString(4))
                        .ThoiGianDV(rs.getTime(5))
                        .gia_Tien(rs.getDouble(6))
                        .moTa(rs.getString(7))
                        .id_LoaiDV(rs.getInt(8))
                        .ten_LoaiDV(rs.getString(9))
                        .trangThai(rs.getInt(10))
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

    public List<DichVuRespon> getAllBy(Integer id) {
        //B1: tạo cau 
        String sql = """
                  	SELECT dbo.Dich_Vu.ID, dbo.Dich_Vu.Ma_DV, dbo.Dich_Vu.Ten_DV, dbo.Dich_Vu.Anh,
                                                                        dbo.Dich_Vu.ThoiGianDV, dbo.Dich_Vu.GiaTien, dbo.Dich_Vu.MoTa, dbo.Dich_Vu.ID_LoaiDV, 
                                                                      dbo.Loai_DV.TenDV, dbo.Dich_Vu.TrangThai
                                                                        FROM   dbo.Dich_Vu INNER JOIN
                                                                     dbo.Loai_DV ON dbo.Dich_Vu.ID_LoaiDV = dbo.Loai_DV.ID where dbo.Dich_Vu.TrangThai=1 and dbo.Dich_Vu.ID=?""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<DichVuRespon> list = new ArrayList<>();
            while (rs.next()) {
                DichVuRespon kh = DichVuRespon.builder()
                        .id(rs.getInt(1))
                        .ma_DV(rs.getString(2))
                        .ten_DV(rs.getString(3))
                        .anh(rs.getString(4))
                        .ThoiGianDV(rs.getTime(5))
                        .gia_Tien(rs.getDouble(6))
                        .moTa(rs.getString(7))
                        .id_LoaiDV(rs.getInt(8))
                        .ten_LoaiDV(rs.getString(9))
                        .trangThai(rs.getInt(10))
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

    public List<DichVuRespon> getPhanTrang(int page, int limit) {
        //B1: tạo cau 
        String sql = """
                  	SELECT dbo.Dich_Vu.ID, dbo.Dich_Vu.Ma_DV, dbo.Dich_Vu.Ten_DV, dbo.Dich_Vu.Anh,
                                                                        dbo.Dich_Vu.ThoiGianDV, dbo.Dich_Vu.GiaTien, dbo.Dich_Vu.MoTa, dbo.Dich_Vu.ID_LoaiDV, 
                                                                      dbo.Loai_DV.TenDV, dbo.Dich_Vu.TrangThai
                                                                        FROM   dbo.Dich_Vu INNER JOIN
                                                                     dbo.Loai_DV ON dbo.Dich_Vu.ID_LoaiDV = dbo.Loai_DV.ID where dbo.Dich_Vu.TrangThai=1 """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        sql += "ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, (page - 1) * limit + 1);
            ps.setObject(2, limit);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<DichVuRespon> list = new ArrayList<>();
            while (rs.next()) {
                DichVuRespon kh = DichVuRespon.builder()
                        .id(rs.getInt(1))
                        .ma_DV(rs.getString(2))
                        .ten_DV(rs.getString(3))
                        .anh(rs.getString(4))
                        .ThoiGianDV(rs.getTime(5))
                        .gia_Tien(rs.getDouble(6))
                        .moTa(rs.getString(7))
                        .id_LoaiDV(rs.getInt(8))
                        .ten_LoaiDV(rs.getString(9))
                        .trangThai(rs.getInt(10))
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

    public List<DichVuRespon> KhoangGia(double Min, double Max) {
        //B1: tạo cau 
        String sql = """
                 		SELECT dbo.Dich_Vu.ID, dbo.Dich_Vu.Ma_DV, dbo.Dich_Vu.Ten_DV, dbo.Dich_Vu.Anh,
                                                                        dbo.Dich_Vu.ThoiGianDV, dbo.Dich_Vu.GiaTien, dbo.Dich_Vu.MoTa, dbo.Dich_Vu.ID_LoaiDV, 
                                                                      dbo.Loai_DV.TenDV, dbo.Dich_Vu.TrangThai
                                                                        FROM   dbo.Dich_Vu INNER JOIN
                                                                     dbo.Loai_DV ON dbo.Dich_Vu.ID_LoaiDV = dbo.Loai_DV.ID where dbo.Dich_Vu.GiaTien between ? and ? """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, Min);
            ps.setObject(2, Max);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<DichVuRespon> list = new ArrayList<>();
            while (rs.next()) {
                DichVuRespon kh = DichVuRespon.builder()
                        .id(rs.getInt(1))
                        .ma_DV(rs.getString(2))
                        .ten_DV(rs.getString(3))
                        .anh(rs.getString(4))
                        .ThoiGianDV(rs.getTime(5))
                        .gia_Tien(rs.getDouble(6))
                        .moTa(rs.getString(7))
                        .id_LoaiDV(rs.getInt(8))
                        .ten_LoaiDV(rs.getString(9))
                        .trangThai(rs.getInt(10))
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

    public boolean add(Dich_Vu kh) {
        int check = 0;
        String sql = """
                   	INSERT INTO [dbo].[Dich_Vu]
                                    ([Ma_DV]
                                    ,[Ten_DV]
                                    ,[Anh]
                                    ,[ThoiGianDV]
                                    ,[GiaTien]
                                    ,[MoTa]
                                    ,[ID_LoaiDV]
                                    ,[TrangThai])
                              VALUES
                                    (?,?,?,?,?,?,?,1)
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getMa_DV());
            ps.setString(2, kh.getTen_DV());
            ps.setString(3, kh.getAnh());
            ps.setObject(4, kh.getThoiGianDV());
            ps.setDouble(5, kh.getGia_Tien());
            ps.setString(6, kh.getMoTa());
            ps.setInt(7, kh.getId_LoaiDV());

            //trang thái = 1 => chưa xóa
            //trạng thái =0 => xóa
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public int up(Dich_Vu Kh) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String sSQL = """
                            UPDATE [dbo].[Dich_Vu]
                                SET 
                                   [Ten_DV] = ?
                                   ,[Anh] = ?
                                   ,[ThoiGianDV] =?
                                   ,[GiaTien] = ?
                                   ,[MoTa] = ?
                                   ,[ID_LoaiDV] = ?
                                  
                              WHERE [Ma_DV] = ?
                          """;
            conn = DBConnect.getConnection();
            ps = conn.prepareStatement(sSQL);
            ps.setString(1, Kh.getTen_DV());
            ps.setString(2, Kh.getAnh());
            ps.setObject(3, Kh.getThoiGianDV());
            ps.setDouble(4, Kh.getGia_Tien());
            ps.setString(5, Kh.getMoTa());
            ps.setInt(6, Kh.getId_LoaiDV());
            ps.setString(7, Kh.getMa_DV());

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

    public boolean xoa(Integer id) {
        int check = 0;
        String sql = """
                    UPDATE [dbo].[Dich_Vu]
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

    public List<DichVuRespon> Search(String trangThai, String KeyWord, double Min, double Max) {
        //B1: tạo cau 
        String sql = """
                 	SELECT dbo.Dich_Vu.ID, dbo.Dich_Vu.Ma_DV, dbo.Dich_Vu.Ten_DV, dbo.Dich_Vu.Anh,
                            dbo.Dich_Vu.ThoiGianDV, dbo.Dich_Vu.GiaTien, dbo.Dich_Vu.MoTa, dbo.Dich_Vu.ID_LoaiDV, 
                             dbo.Loai_DV.TenDV, dbo.Dich_Vu.TrangThai
                              FROM   dbo.Dich_Vu INNER JOIN
                              dbo.Loai_DV ON dbo.Dich_Vu.ID_LoaiDV = dbo.Loai_DV.ID where dbo.Loai_DV.TenDV=? and (dbo.Dich_Vu.GiaTien between ? and ?)""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        if (KeyWord.length() > 0) {
            sql += """
                and (
                dbo.Dich_Vu.MoTa like ? or dbo.Dich_Vu.Ma_DV like ? or dbo.Dich_Vu.Ten_DV like ? )
                
                """;
        }
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            int index = 1;// vi tri dau tien cua dau hoi
            ps.setObject(index++, trangThai);
            ps.setObject(index++, Min);
            ps.setObject(index++, Max);
            if (KeyWord.length() > 0) {
                String value = "%" + KeyWord + "%";
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
            }
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<DichVuRespon> list = new ArrayList<>();
            while (rs.next()) {
                DichVuRespon kh = DichVuRespon.builder()
                        .id(rs.getInt(1))
                        .ma_DV(rs.getString(2))
                        .ten_DV(rs.getString(3))
                        .anh(rs.getString(4))
                        .ThoiGianDV(rs.getTime(5))
                        .gia_Tien(rs.getDouble(6))
                        .moTa(rs.getString(7))
                        .id_LoaiDV(rs.getInt(8))
                        .ten_LoaiDV(rs.getString(9))
                        .trangThai(rs.getInt(10))
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

    public List<DichVuRespon> PhanTrang(String trangThai, int page, int limit, double Min, double Max, String KeyWord) {
        //B1: tạo cau 
        String sql = """
                 SELECT dbo.Dich_Vu.ID, dbo.Dich_Vu.Ma_DV, dbo.Dich_Vu.Ten_DV, dbo.Dich_Vu.Anh,
                                    dbo.Dich_Vu.ThoiGianDV, dbo.Dich_Vu.GiaTien, dbo.Dich_Vu.MoTa, dbo.Dich_Vu.ID_LoaiDV, 
                                    dbo.Loai_DV.TenDV, dbo.Dich_Vu.TrangThai
                                    FROM   dbo.Dich_Vu INNER JOIN
                                    dbo.Loai_DV ON dbo.Dich_Vu.ID_LoaiDV = dbo.Loai_DV.ID where dbo.Loai_DV.TenDV=? and (dbo.Dich_Vu.GiaTien between ? and ?)""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        if (KeyWord.length() > 0) {
            sql += """
                and (
                dbo.Dich_Vu.MoTa  like ? or dbo.Dich_Vu.Ma_DV like ? or dbo.Dich_Vu.Ten_DV like ? )
                
                """;
        }
        sql += "ORDER BY dbo.Dich_Vu.ID_LoaiDV OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            // vi tri dau tien cua dau hoi
            int index = 1;
            ps.setObject(index++, trangThai);
            ps.setObject(index++, Min);
            ps.setObject(index++, Max);

            if (KeyWord.length() > 0) {
                String value = "%" + KeyWord + "%";
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, value);
                ps.setObject(index++, (page - 1) * limit + 1);
                ps.setObject(index++, limit);
            } else {
                ps.setObject(index++, (page - 1) * limit + 1);
                ps.setObject(index++, limit);
            }
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<DichVuRespon> list = new ArrayList<>();
            while (rs.next()) {
                DichVuRespon kh = DichVuRespon.builder()
                        .id(rs.getInt(1))
                        .ma_DV(rs.getString(2))
                        .ten_DV(rs.getString(3))
                        .anh(rs.getString(4))
                        .ThoiGianDV(rs.getTime(5))
                        .gia_Tien(rs.getDouble(6))
                        .moTa(rs.getString(7))
                        .id_LoaiDV(rs.getInt(8))
                        .ten_LoaiDV(rs.getString(9))
                        .trangThai(rs.getInt(10))
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
    
     public List<Dich_Vu> getDich_Vu() {
        //B1: tạo cau 
        String sql = """
                 	SELECT [ID]
                                                   ,[Ma_DV]
                                                   ,[Ten_DV]
                                                   ,[Anh]
                                                   ,[ThoiGianDV]
                                                   ,[GiaTien]
                                                   ,[MoTa]
                                                   ,[ID_LoaiDV]
                                                   ,[TrangThai]
                                               FROM [dbo].[Dich_Vu]
                                               """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<Dich_Vu> list = new ArrayList<>();
            while (rs.next()) {
                Dich_Vu kh = Dich_Vu.builder()
                        .id(rs.getInt(1))
                        .ma_DV(rs.getString(2))
                        .ten_DV(rs.getString(3))
                        .anh(rs.getString(4))
                        .ThoiGianDV(rs.getTime(5))
                        .gia_Tien(rs.getDouble(6))
                        .moTa(rs.getString(7))
                        .id_LoaiDV(rs.getInt(8))              
                        .trangThai(rs.getInt(9))
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

     public Dich_Vu chucVu(String ten) {
        //B1: tạo cau 
        String sql = """
                 	SELECT [ID]
                                                   ,[Ma_DV]
                                                   ,[Ten_DV]
                                                   ,[Anh]
                                                   ,[ThoiGianDV]
                                                   ,[GiaTien]
                                                   ,[MoTa]
                                                   ,[ID_LoaiDV]
                                                   ,[TrangThai]
                                               FROM [dbo].[Dich_Vu]
                     WHERE [Ten_DV] = ?
                                               """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            
            while (rs.next()) {
                Dich_Vu kh = Dich_Vu.builder()
                        .id(rs.getInt(1))
                        .ma_DV(rs.getString(2))
                        .ten_DV(rs.getString(3))
                        .anh(rs.getString(4))
                        .ThoiGianDV(rs.getTime(5))
                        .gia_Tien(rs.getDouble(6))
                        .moTa(rs.getString(7))
                        .id_LoaiDV(rs.getInt(8))              
                        .trangThai(rs.getInt(9))
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
