/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import config.DBConnect;
import entity.CongViec;

public class CongViecRepository {

        public ArrayList<CongViec> getAll() {
            String sql = """
                          SELECT [ID]
                                ,[MaCV]
                                ,[TenCV]
                                ,[MucLuong]
                                ,[MoTa]
                                
                            FROM [dbo].[CongViec]
                         
                          """;
            try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                ArrayList<CongViec> lists = new ArrayList<>();
                while (rs.next()) {
                    CongViec cv = CongViec.builder()
                            .id(rs.getInt(1))
                            .maCV(rs.getString(2))
                            .tenCV(rs.getString(3))
                            .mucLuong(rs.getDouble(4))
                            .moTa(rs.getString(5))
                            
                            .build();
                    lists.add(cv);
                }
                return lists;
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
            return null;
        }

public CongViec getcongviec(String tencongviec){
        String sql = """
                      SELECT [ID]
                              ,[MaCV]
                              ,[TenCV]
                              ,[MucLuong]
                              ,[MoTa]
                              ,[trangThai]
                          FROM [dbo].[CongViec]
                         WHERE [MaCV] = ?
                      """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            // Set gia tri cho dau hoi cham 
            ps.setObject(1,tencongviec );
            ResultSet rs = ps.executeQuery(); // Lay ket qua

            while (rs.next()) {
                CongViec cv = new CongViec(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDouble(4),rs.getString(5),rs.getInt(6));
                return cv;
            }
        } catch (Exception e) {
            // loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }
public static void main(String[] args) {
        System.out.println(new CongViecRepository().getAll());
    }
    
}
