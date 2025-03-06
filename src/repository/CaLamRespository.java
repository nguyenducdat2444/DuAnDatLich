/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import entity.Ca_Lam;
import entity.Giao_Ca;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import response.CaLamResponse;

/**
 *
 * @author default
 */
public class CaLamRespository {

    public List<Ca_Lam> getCaLam() {
        //B1: tạo cau 
        String sql = """
                 	SELECT [ID]
                              ,[ID_LichLam]
                              ,[TG_BD]
                              ,[TG_KT]
                              ,[TenCaLam]
                          FROM [dbo].[Ca_Lam]""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<Ca_Lam> list = new ArrayList<>();
            while (rs.next()) {
                Ca_Lam kh = Ca_Lam.builder()
                        .id(rs.getInt(1))
                        .id_LichLam(rs.getInt(2))
                        .tg_BD(rs.getTime(3))
                        .tg_KT(rs.getTime(4))
                        .tenCaLam(rs.getString(5))
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

    public Ca_Lam getChuVu(String ten) {
        //B1: tạo cau 
        String sql = """
           SELECT [ID]
                                            ,[ID_LichLam]
                                            ,[TG_BD]
                                            ,[TG_KT]
                                            ,[TenCaLam]
                                        FROM [dbo].[Ca_Lam] where [TenCaLam]=?""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)

            while (rs.next()) {
                Ca_Lam nvr = Ca_Lam.builder()
                        .id(rs.getInt(1))
                        .id_LichLam(rs.getInt(2))
                        .tg_BD(rs.getTime(3))
                        .tg_KT(rs.getTime(4))
                        .tenCaLam(rs.getString(5))
                        .build();
                return nvr;
//                KhachHang kh= new KhachHang(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),
//                rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12));  
            }

        } catch (Exception e) {
            //loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;

    }

    public List<CaLamResponse> getAll() {
        //B1: tạo cau 
        String sql = """
                 	SELECT dbo.Giao_Ca.ID, dbo.Giao_Ca.ID_NV_Nhan, dbo.Nhan_Vien.Ten_NV, dbo.Giao_Ca.ID_CaLam, dbo.Ca_Lam.TenCaLam, dbo.Giao_Ca.Ngay_Ca_Lam, dbo.Giao_Ca.Tien_Ca_Lam, dbo.Giao_Ca.GhiChu, dbo.Giao_Ca.TrangThai
                        FROM   dbo.Nhan_Vien INNER JOIN
                                     dbo.Giao_Ca ON dbo.Nhan_Vien.ID = dbo.Giao_Ca.ID_NV_Nhan INNER JOIN
                                     dbo.Ca_Lam ON dbo.Giao_Ca.ID_CaLam = dbo.Ca_Lam.ID where dbo.Giao_Ca.TrangThai=1""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<CaLamResponse> list = new ArrayList<>();
            while (rs.next()) {
                CaLamResponse kh = CaLamResponse.builder()
                        .id(rs.getInt(1))
                        .id_NV_Nhan(rs.getInt(2))
                        .ten_NV(rs.getString(3))
                        .id_CaLam(rs.getInt(4))
                        .tenCaLam(rs.getString(5))
                        .ngayCaLam(rs.getDate(6))
                        .tien_Ca_Lam(rs.getDouble(7))
                        .ghiChu(rs.getString(8))
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

    public boolean add(Giao_Ca kh) {
        int check = 0;
        String sql = """
                   	INSERT INTO [dbo].[Giao_Ca]
                                   ([ID_CaLam]
                                   ,[ID_NV_Nhan]
                                   ,[GhiChu]
                                   ,[Tien_Ca_Lam]
                                   ,[Ngay_Ca_Lam]
                                   ,[TrangThai])
                             VALUES
                                   (?,?,?,?,?,1)
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, kh.getId_CaLam());
            ps.setObject(2, kh.getId_NV_Nhan());
            ps.setObject(3, kh.getGhiChu());
            ps.setObject(4, kh.getTien_Ca_Lam());
            ps.setObject(5, kh.getNgayCaLam());
            //trang thái = 1 => chưa xóa
            //trạng thái =0 => xóa
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }

    public boolean update(Integer id, Giao_Ca kh) {
        int check = 0;
        String sql = """
             UPDATE [dbo].[Giao_Ca]
                   SET [ID_CaLam] = ?
                      ,[ID_NV_Nhan] = ?
                      ,[GhiChu] = ?
                      ,[Tien_Ca_Lam] = ?
                      ,[Ngay_Ca_Lam] = ?
                    
                 WHERE id=?
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, kh.getId_CaLam());
            ps.setObject(2, kh.getId_NV_Nhan());
            ps.setObject(3, kh.getGhiChu());
            ps.setObject(4, kh.getTien_Ca_Lam());
            ps.setObject(5, kh.getNgayCaLam());
            ps.setInt(6, id);
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
                   UPDATE [dbo].[Giao_Ca]
                                     SET 
                                      [TrangThai]=0
                                   WHERE id=?
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
      
     public List<CaLamResponse> Search(String KeyWord,Date Dau, Date Cuoi) {
        //B1: tạo cau 
        String sql = """
                 	SELECT dbo.Giao_Ca.ID, dbo.Giao_Ca.ID_NV_Nhan, dbo.Nhan_Vien.Ten_NV, dbo.Giao_Ca.ID_CaLam, dbo.Ca_Lam.TenCaLam, dbo.Giao_Ca.Ngay_Ca_Lam, dbo.Giao_Ca.Tien_Ca_Lam, dbo.Giao_Ca.GhiChu, dbo.Giao_Ca.TrangThai
                        FROM   dbo.Nhan_Vien INNER JOIN
                                     dbo.Giao_Ca ON dbo.Nhan_Vien.ID = dbo.Giao_Ca.ID_NV_Nhan INNER JOIN
                                     dbo.Ca_Lam ON dbo.Giao_Ca.ID_CaLam = dbo.Ca_Lam.ID where dbo.Nhan_Vien.Ten_NV like ? and (dbo.Giao_Ca.Ngay_Ca_Lam between ? and ?)""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
             ps.setObject(1, "%" + KeyWord + "%");
             ps.setObject(2, Dau);
             ps.setObject(3, Cuoi);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<CaLamResponse> list = new ArrayList<>();
            while (rs.next()) {
                CaLamResponse kh = CaLamResponse.builder()
                        .id(rs.getInt(1))
                        .id_NV_Nhan(rs.getInt(2))
                        .ten_NV(rs.getString(3))
                        .id_CaLam(rs.getInt(4))
                        .tenCaLam(rs.getString(5))
                        .ngayCaLam(rs.getDate(6))
                        .tien_Ca_Lam(rs.getDouble(7))
                        .ghiChu(rs.getString(8))
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

}
