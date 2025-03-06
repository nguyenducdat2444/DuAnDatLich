/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dichVuTam;

import config.DBConnect;
import dichVuTam.DvTam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class DVTamRepository {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    
    public DVTamRepository(){
        con = DBConnect.getConnection(); 
    }
    
    public ArrayList<DvTam>getAll(){
        ArrayList<DvTam>lists = new ArrayList<>();
       sql="""
           SELECT [id]
                 ,[ten]
                 ,[thoiGian]
                 ,[giaTien]
             FROM [dbo].[DVTam]
           """;
       try{
           ps = con.prepareStatement(sql);
           rs = ps.executeQuery();
           while(rs.next()){
               DvTam dv = DvTam.builder()
                       .id(rs.getInt(1))
                       .tenDV(rs.getString(2))
                       .thoiwGianDV(rs.getString(3))
                       .giaTien(rs.getDouble(4))
                       .build();
               lists.add(dv);
           }
           return lists;
       }catch(Exception e){
           e.printStackTrace();
           return null;
       }
    }
    
    public int add(DvTam dv){
        sql="""
            INSERT INTO [dbo].[DVTam]
                       ([id]
                       ,[ten]
                       ,[thoiGian]
                       ,[giaTien])
                 VALUES
                       (?,?,?,?)
            """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, dv.getId());
            ps.setObject(2, dv.getTenDV());
            ps.setObject(3, dv.getThoiwGianDV());
            ps.setObject(4, dv.getGiaTien());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
    
    public int remove(Integer id){
        sql ="""
             DELETE FROM [dbo].[DVTam]
                   WHERE id = ?
             """;
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate();
        }catch(Exception e ){
            e.printStackTrace();
            return 0;
        }
    }
    
    
}
