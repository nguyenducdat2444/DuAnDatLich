/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import entity.Dich_Vu;
import entity.loai_DV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author default
 */
public class Loai_DV_Responsory {

    public List<loai_DV> getAll() {
        //B1: tạo cau 
        String sql = """
                 	SELECT [ID]
                              ,[Ma_LoaiDV]
                              ,[TenDV]
                              ,[TrangThai]
                          FROM [dbo].[Loai_DV]""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<loai_DV> list = new ArrayList<>();
            while (rs.next()) {
                loai_DV kh = loai_DV.builder()
                        .id(rs.getInt(1))
                        .tenDV(rs.getString(3))
                        .ma_LoaiDV(rs.getString(2))
                        .trangThai(rs.getInt(4))
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

    public loai_DV getChuVu(String ten) {
        //B1: tạo cau 
        String sql = """
              SELECT [ID]
                                             ,[Ma_LoaiDV]
                                             ,[TenDV]
                                             ,[TrangThai]
                                         FROM [dbo].[Loai_DV] where [TenDV]=?""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)

            while (rs.next()) {
                loai_DV nvr = loai_DV.builder()
                        .id(rs.getInt(1))
                        .tenDV(rs.getString(3))
                        .ma_LoaiDV(rs.getString(2))
                        .trangThai(rs.getInt(4))
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

    public List<loai_DV> Search(String KeyWord) {
        //B1: tạo cau 
        String sql = """
                 	SELECT [ID]
                        ,[Ma_LoaiDV]
                        ,[TenDV]
                        ,[TrangThai]
                    FROM [dbo].[Loai_DV] where  [Ma_LoaiDV] like ? or [TenDV] like ?""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql

        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset

            if (KeyWord.length() > 0) {
                String value = "%" + KeyWord + "%";
                ps.setObject(1, value);
                ps.setObject(2, value);

            }
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<loai_DV> list = new ArrayList<>();
            while (rs.next()) {
                loai_DV kh = loai_DV.builder()
                        .id(rs.getInt(1))
                        .tenDV(rs.getString(3))
                        .ma_LoaiDV(rs.getString(2))
                        .trangThai(rs.getInt(4))
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

    public boolean add(loai_DV kh) {
        int check = 0;
        String sql = """
                   	INSERT INTO [dbo].[Loai_DV]
                                   ([Ma_LoaiDV]
                                   ,[TenDV]
                                   ,[TrangThai]
                                   )
                             VALUES
                                   (?,?,1)
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getMa_LoaiDV());
            ps.setString(2, kh.getTenDV());

            //trang thái = 1 => chưa xóa
            //trạng thái =0 => xóa
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }
}
