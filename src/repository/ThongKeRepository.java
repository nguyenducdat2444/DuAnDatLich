/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import config.DBConnect;
import entity.Hoa_Don;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import response.ThongKeRespon;

/**
 *
 * @author default
 */
public class ThongKeRepository {

    public List<ThongKeRespon> getAll() {
        //B1: tạo cau 
        String sql = """
              	 SELECT dbo.Hoa_Don.ID, dbo.Hoa_Don.Ma_HD, dbo.Khach_Hang.Ten_KH, dbo.Hoa_Don.NgayTao, dbo.Hoa_Don.TongTien
                 FROM   dbo.Khach_Hang INNER JOIN
                              dbo.Hoa_Don ON dbo.Khach_Hang.ID = dbo.Hoa_Don.ID_KH""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<ThongKeRespon> list = new ArrayList<>();
            while (rs.next()) {
                ThongKeRespon kh = ThongKeRespon.builder()
                        .id(rs.getInt(1))
                        .ma_HD(rs.getString(2))
                        .ten_KH(rs.getString(3))
                        
                        .ngayTao(rs.getDate(4))
                        .tong_Tien(rs.getDouble(5))
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

    public List<ThongKeRespon> getAllKhachHangCaoNhat() {
        //B1: tạo cau 
        String sql = """
           SELECT dbo.Hoa_Don.ID, dbo.Hoa_Don.Ma_HD, dbo.Khach_Hang.Ten_KH, dbo.Hoa_Don.NgayTao, dbo.Hoa_Don.TongTien, dbo.Khach_Hang.SDT_KH, dbo.Khach_Hang.GioiTinh, dbo.Khach_Hang.DiaChi, dbo.Khach_Hang.Email, dbo.Khach_Hang.NgaySinh
              FROM   dbo.Khach_Hang INNER JOIN
                           dbo.Hoa_Don ON dbo.Khach_Hang.ID = dbo.Hoa_Don.ID_KH""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<ThongKeRespon> list = new ArrayList<>();
            while (rs.next()) {
                ThongKeRespon kh = ThongKeRespon.builder()
                        .id(rs.getInt(1))
                        .ma_HD(rs.getString(2))
                        .ten_KH(rs.getString(3))
                       
                        .ngayTao(rs.getDate(4))
                        .tong_Tien(rs.getDouble(5))
                        .sdt_KH(rs.getString(6))
                        .gioiTinh(rs.getInt(7))
                        .diaChi(rs.getString(8))
                        .email(rs.getString(9))
                        .ngaySinh(rs.getDate(10))
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

    public List<ThongKeRespon> khachHangTiemNang() {
        //B1: tạo cau 
        String sql = """
        SELECT top 1 dbo.Hoa_Don.ID, dbo.Hoa_Don.Ma_HD, dbo.Khach_Hang.Ten_KH, dbo.Hoa_Don.NgayTao, dbo.Hoa_Don.TongTien, dbo.Khach_Hang.SDT_KH, dbo.Khach_Hang.GioiTinh, dbo.Khach_Hang.DiaChi, dbo.Khach_Hang.Email, dbo.Khach_Hang.NgaySinh
                 FROM   dbo.Khach_Hang INNER JOIN
                 dbo.Hoa_Don ON dbo.Khach_Hang.ID = dbo.Hoa_Don.ID_KH 
                 group by dbo.Hoa_Don.ID, dbo.Hoa_Don.Ma_HD, dbo.Khach_Hang.Ten_KH, dbo.Hoa_Don.NgayTao, dbo.Hoa_Don.TongTien, dbo.Khach_Hang.SDT_KH, dbo.Khach_Hang.GioiTinh, dbo.Khach_Hang.DiaChi, dbo.Khach_Hang.Email, dbo.Khach_Hang.NgaySinh
                 order by dbo.Hoa_Don.TongTien desc""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<ThongKeRespon> list = new ArrayList<>();
            while (rs.next()) {
                 ThongKeRespon kh = ThongKeRespon.builder()
                        .id(rs.getInt(1))
                        .ma_HD(rs.getString(2))
                        .ten_KH(rs.getString(3))
                       
                        .ngayTao(rs.getDate(4))
                        .tong_Tien(rs.getDouble(5))
                        .sdt_KH(rs.getString(6))
                        .gioiTinh(rs.getInt(7))
                        .diaChi(rs.getString(8))
                        .email(rs.getString(9))
                        .ngaySinh(rs.getDate(10))
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

    public List<ThongKeRespon> getAllKhachHangCaoNhat1(Integer hd) {
        //B1: tạo cau 
        String sql = """
           SELECT dbo.Hoa_Don.ID, dbo.Hoa_Don.Ma_HD, dbo.Khach_Hang.Ten_KH, dbo.Hoa_Don.NgayTao, dbo.Hoa_Don.TongTien, dbo.Khach_Hang.SDT_KH, dbo.Khach_Hang.GioiTinh, dbo.Khach_Hang.DiaChi, dbo.Khach_Hang.Email, dbo.Khach_Hang.NgaySinh
                 FROM   dbo.Khach_Hang INNER JOIN
                              dbo.Hoa_Don ON dbo.Khach_Hang.ID = dbo.Hoa_Don.ID_KH where dbo.Hoa_Don.ID = ?""";
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ps.setObject(1, hd);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<ThongKeRespon> list = new ArrayList<>();
            while (rs.next()) {
                ThongKeRespon kh = ThongKeRespon.builder()
                        .id(rs.getInt(1))
                        .ma_HD(rs.getString(2))
                        .ten_KH(rs.getString(3))
                       
                        .ngayTao(rs.getDate(4))
                        .tong_Tien(rs.getDouble(5))
                        .sdt_KH(rs.getString(6))
                        .gioiTinh(rs.getInt(7))
                        .diaChi(rs.getString(8))
                        .email(rs.getString(9))
                        .ngaySinh(rs.getDate(10))
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

    public List<ThongKeRespon> Search1(Date Dau, Date Cuoi) {
        //B1: tạo cau 
        String sql = """
     			 SELECT dbo.Hoa_Don.ID, dbo.Hoa_Don.Ma_HD, dbo.Khach_Hang.Ten_KH, dbo.Hoa_Don.NgayTao, dbo.Hoa_Don.TongTien
        FROM   dbo.Khach_Hang INNER JOIN
                     dbo.Hoa_Don ON dbo.Khach_Hang.ID = dbo.Hoa_Don.ID_KH where  dbo.Hoa_Don.NgayTao between ? and ? """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
           
            ps.setObject(1, Dau);
            ps.setObject(2, Cuoi);
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<ThongKeRespon> lis = new ArrayList<>();
            while (rs.next()) {
               ThongKeRespon kh = ThongKeRespon.builder()
                        .id(rs.getInt(1))
                        .ma_HD(rs.getString(2))
                        .ten_KH(rs.getString(3))
                        
                        .ngayTao(rs.getDate(4))
                        .tong_Tien(rs.getDouble(5))
                        .build();
                lis.add(kh);
            }
            return lis;
        } catch (Exception e) {
            //loi => nhay vao catch
            e.printStackTrace(System.out);
        }
        return null;
    }

}
