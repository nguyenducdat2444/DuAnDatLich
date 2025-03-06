/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import entity.Dich_Vu;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;

/**
 *
 * @author Admin
 */
public class DichVuRepsitory {
    Connection con = null; 
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    public DichVuRepsitory(){
        con = DBConnect.getConnection();
    }
    public ArrayList<Dich_Vu>getAll(){
        sql ="""
             SELECT [ID]
                   ,[Ma_DV]
                   ,[Ten_DV]
                   ,[ThoiGianDV]
                   ,[GiaTien]
                   ,[ID_LoaiDV]
                   ,[TrangThai]
               FROM [dbo].[Dich_Vu]
             """;
        ArrayList<Dich_Vu>lists = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Dich_Vu dv = Dich_Vu.builder()
                        .id(rs.getInt(1))
                        .ma_DV(rs.getString(2))
                        .ten_DV(rs.getString(3))
                        .ThoiGianDV(rs.getTime(4))
                        .gia_Tien(rs.getFloat(5))
                        .id_LoaiDV(rs.getInt(6))
                        .trangThai(rs.getInt(7))
                        .build();
                lists.add(dv);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.println(new DichVuRepsitory().getAll());
    }
}
