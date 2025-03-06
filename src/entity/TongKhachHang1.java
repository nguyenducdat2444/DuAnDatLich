/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author default
 */
public class TongKhachHang1 {

    public int countCustomerNumber() {
        int total = 0;
        try {
            Connection cons = DBConnect.getConnection();

            String sql = """
                SELECT COUNT(DISTINCT [ID]) AS tong  FROM [dbo].[Khach_Hang]""";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt(1);
            }
            return total;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total;
    }

    public double countCustomerNumberDT() {
       double total1 = 0;
        try {
            Connection cons = DBConnect.getConnection();

            String sql = """
                SELECT sum(DISTINCT [TongTien]) FROM [dbo].[Hoa_Don] """;
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total1 = rs.getInt(1);
            }
            return total1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total1;
    }

    public int countCustomerNumberSHD() {
        int total2 = 0;
        try {
            Connection cons = DBConnect.getConnection();

            String sql = """
                SELECT Sum(DISTINCT [ID]) FROM [dbo].[Hoa_Don]  """;
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total2 = rs.getInt(1);
            }
            return total2;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total2;
    }
     public double countCustomerNumberNam() {
       double total1 = 0;
        try {
            Connection cons = DBConnect.getConnection();

            String sql = """
               SELECT sum(DISTINCT [TongTien]),YEAR(ngaytao) FROM [dbo].[Hoa_Don] group by YEAR(ngaytao)""";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total1 = rs.getInt(1);
            }
            return total1;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return total1;
    }
}
