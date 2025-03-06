/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import entity.Lich_Lam;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import response.LichlamResponse;

public class LichLamRepository {

    public ArrayList<LichlamResponse> getAll() {
        String sql = """
                          SELECT        dbo.Lich_Lam.ID, dbo.Nhan_Vien.Ma_NV, dbo.Nhan_Vien.Ten_NV,  
                                        dbo.Lich_Lam.TenCaLam,dbo.Lich_Lam.NgayLamViec, dbo.Lich_Lam.TG_BD, dbo.Lich_Lam.TG_KT,dbo.Lich_Lam.GhiChu, 
                                        dbo.Nhan_Vien.TrangThai
                          FROM            dbo.Lich_Lam INNER JOIN
                                                   dbo.Nhan_Vien ON dbo.Lich_Lam.ID_NhanVien = dbo.Nhan_Vien.ID
                         WHERE Nhan_Vien.TrangThai = 1
                          """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ArrayList<LichlamResponse> lists = new ArrayList<>();
            while (rs.next()) {
                LichlamResponse lich = LichlamResponse.builder()
                        .id(rs.getInt(1))
                        .ma_NV(rs.getString(2))
                        .ten_NV(rs.getString(3))
                        .tenCaLam(rs.getString(4))
                        .ngayLamViec(rs.getDate(5))
                        .tg_BD(rs.getTime(6))
                        .tg_KT(rs.getTime(7))
                        .GhiChu(rs.getString(8))
                        .trangThai(rs.getInt(9))
                        .build();
                lists.add(lich);
            }
            return lists;
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean addLichLam(Lich_Lam lichLam) {
        String sql = "INSERT INTO Lich_Lam (ID_NhanVien, TenCaLam, NgayLamViec, TG_BD, TG_KT,GhiChu) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, lichLam.getId_NhanVien());
            ps.setString(2, lichLam.getTenCaLam());
            ps.setDate(3, new Date(lichLam.getNgayLamViec().getTime()));
            ps.setTime(4, lichLam.getTg_BD());
            ps.setTime(5, lichLam.getTg_KT());
            ps.setString(6, lichLam.getGhiChu());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLichLam(Lich_Lam lichLam, Integer id) {
        String sql = """
                 UPDATE Lich_Lam SET 
                 TenCaLam = ?
                 , NgayLamViec = ?
                 , TG_BD = ?
                 , TG_KT = ?
                 , GhiChu = ? 
                 WHERE ID = ?
                 """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lichLam.getTenCaLam());
            ps.setDate(2, new Date(lichLam.getNgayLamViec().getTime()));
            ps.setTime(3, lichLam.getTg_BD());
            ps.setTime(4, lichLam.getTg_KT());
            ps.setString(5, lichLam.getGhiChu());
            ps.setInt(6, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(Integer id) {
        int check = 0;
        String sql = """
                     DELETE FROM [dbo].[Lich_Lam]
                           WHERE id = ?
                     """;
        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }

    public static void main(String[] args) {
        System.out.println(new LichLamRepository().getAll());
    }
}
