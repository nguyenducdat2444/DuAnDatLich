/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.ArrayList;
import response.HDCTResponse;

/**
 *
 * @author Admin
 */
public class HDCTRepository {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;

    public HDCTRepository() {
        con = DBConnect.getConnection();
    }
   

    public ArrayList<HDCTResponse> getAll(String maHD) {
        ArrayList<HDCTResponse> lists = new ArrayList<>();
        sql = """
            SELECT dbo.HDCT.ID, dbo.Hoa_Don.Ma_HD, dbo.Dich_Vu.Ma_DV, dbo.Dich_Vu.Ten_DV, dbo.HDCT.So_Luong, dbo.Dich_Vu.GiaTien, dbo.HDCT.Thanh_Tien
                              FROM     dbo.Dich_Vu INNER JOIN
                                                dbo.HDCT ON dbo.Dich_Vu.ID = dbo.HDCT.ID_DV INNER JOIN
                                                dbo.Hoa_Don ON dbo.HDCT.ID_HD = dbo.Hoa_Don.ID where  dbo.Hoa_Don.Ma_HD = ?
            """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, maHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                HDCTResponse response = HDCTResponse.builder()
                        .idHDCT(rs.getInt(1))
                        .maHD(rs.getString(2))
                        .maDV(rs.getString(3))
                        .tenDV(rs.getString(4))
                        .soLuong(rs.getInt(5))
                        .donGia(rs.getFloat(6))
                        .thanhTien(rs.getFloat(7))
                        .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateTongTien() {
        sql = """
             update Hoa_Don
             set TongTien=(select SUM(Thanh_Tien)
                            From HDCT
                            where HDCT.ID_HD = Hoa_Don.ID
                            Group by HDCT.ID_HD)
             """;

        try (PreparedStatement ps = con.prepareStatement(sql)) {

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public int UpdateTien(int sl, float dg, int id) {
        sql = """
              UPDATE [dbo].[HDCT]
                 SET [Thanh_Tien] = ?
               WHERE ID =?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, sl * dg);
            ps.setObject(2, id);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int UpdateSLHDCT(int idHD, int idDV, int luotDung) {
        sql = """
           UPDATE [dbo].[HDCT]
              SET 
                 [So_Luong] = ?
                 
            WHERE ID_HD =? and ID_DV =?
           """;

        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, luotDung);
            ps.setObject(2, idHD);
            ps.setObject(3, idDV);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<HDCTResponse> getAllHDCTTT(int idHD) {
        sql = """
            SELECT dbo.Hoa_Don.ID,dbo.HDCT.ID, dbo.Hoa_Don.Ma_HD, dbo.Dich_Vu.ID AS Expr1, dbo.Dich_Vu.Ten_DV, dbo.Dich_Vu.ThoiGianDV, dbo.Dich_Vu.GiaTien, dbo.HDCT.So_Luong, dbo.HDCT.Thanh_Tien
            FROM     dbo.Hoa_Don INNER JOIN
                              dbo.HDCT ON dbo.Hoa_Don.ID = dbo.HDCT.ID_HD INNER JOIN
                              dbo.Dich_Vu ON dbo.HDCT.ID_DV = dbo.Dich_Vu.ID where  dbo.Hoa_Don.ID = ?
            """;
        ArrayList<HDCTResponse> lists = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, idHD);
            rs = ps.executeQuery();
            while (rs.next()) {
                HDCTResponse response = HDCTResponse.builder()
                        .idHd(rs.getInt(1))
                        .idHDCT(rs.getInt(2))
                        .maHD(rs.getString(3))
                        .idDV(rs.getInt(4))
                        .tenDV(rs.getString(5))
                        .thoiGianDV(LocalTime.parse(rs.getString(6)))
                        .donGia(rs.getFloat(7))
                        .soLuong(rs.getInt(8))
                        .thanhTien(rs.getFloat(9))
                        .build();
                lists.add(response);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int AddHDCT(int idHD, int idDV) {
        sql = """
            INSERT INTO [dbo].[HDCT]
                       ([ID_HD]
                       ,[ID_DV]
                       ,[So_Luong])
                 VALUES
                       (?,?,1)
            """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, idHD);
            ps.setObject(2, idDV);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(new HDCTRepository().getAll());
//    }
}
