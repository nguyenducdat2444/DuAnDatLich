/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import entity.CongViec;
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
public class TenCongViecResponsory {
    public List<CongViec> getAll() {
        //B1: tạo cau 
        String sql = """
                 	SELECT [ID]
                              ,[MaCV]
                              ,[TenCV]
                              ,[MucLuong]
                              ,[MoTa]
                              ,[TrangThai]
                          FROM [dbo].[CongViec]""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<CongViec> list = new ArrayList<>();
            while (rs.next()) {
                CongViec kh = CongViec.builder()
                        .id(rs.getInt(1))
                        
                        .tenCV(rs.getString(3))
                        .maCV(rs.getString(2))
                        .mucLuong(rs.getDouble(4))
                        .moTa(rs.getString(5))
                        .trangThai(rs.getInt(6))
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
     public CongViec getChuVu(String ten) {
        //B1: tạo cau 
        String sql = """
              SELECT [ID]
                                            ,[MaCV]
                                            ,[TenCV]
                                            ,[MucLuong]
                                            ,[MoTa]
                                            ,[TrangThai]
                                        FROM [dbo].[CongViec] where [TenCV]=?""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, ten);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)

            while (rs.next()) {
                CongViec nvr = CongViec.builder()
                       .id(rs.getInt(1))
                        
                        .tenCV(rs.getString(3))
                        .maCV(rs.getString(2))
                        .mucLuong(rs.getDouble(4))
                        .moTa(rs.getString(5))
                        .trangThai(rs.getInt(6))
                        .build();
                return nvr;

            }

        } catch (Exception e) {
            //loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;

    }
}
