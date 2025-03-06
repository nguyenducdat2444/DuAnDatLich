/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import entity.ThongKe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import response.DichVuRespon;

/**
 *
 * @author default
 */
public class ThongKeRepositoryBieuDo {
   
       public ThongKe getTongTheoThang(int month,int year) {
        //B1: tạo cau 
        String sql = """
                     SELECT 
                     Month([NgayTao])
                   ,Sum([TongTien])
                     FROM [dbo].[Hoa_Don] where  Month([NgayTao])=? and  Year([NgayTao]) =? group by Month([NgayTao])""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, month);
             ps.setObject(2, year);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
           
            while (rs.next()) {
                ThongKe tk= new ThongKe();
                tk.setNgayTao(rs.getString(1));
                tk.setTong(rs.getDouble(2));
                return tk;
            }
            
        } catch (Exception e) {
            //loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
        public List<ThongKe> getAll() {
        //B1: tạo cau 
        String sql = """
             SELECT dbo.Dich_Vu.Ten_DV, count(dbo.HDCT.ID_DV)
                        FROM   dbo.HDCT INNER JOIN
                                     dbo.Dich_Vu ON dbo.HDCT.ID_DV = dbo.Dich_Vu.ID group by dbo.Dich_Vu.Ten_DV """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<ThongKe> list = new ArrayList<>();
            while (rs.next()) {
                ThongKe kh = ThongKe.builder()
                        .ten_DV(rs.getString(1))
                        .soLuong(rs.getInt(2))
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
