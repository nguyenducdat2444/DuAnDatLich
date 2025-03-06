package repository;

import Untils.XEmail;
import config.DBConnect;
import entity.Dich_Vu;
import entity.RandomSNN;
import entity.Tai_Khoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import response.taiKhoanRespon;


public class taiKhoanRepository {

    public List<taiKhoanRespon> getAll() {
        //B1: tạo cau 
        String sql = """
                 	SELECT dbo.Tai_Khoan.ID, dbo.Tai_Khoan.tenDangNhap, dbo.Tai_Khoan.matKhau, dbo.Tai_Khoan.ID_Role, dbo.Role.Ten
                        FROM   dbo.Tai_Khoan INNER JOIN
                                     dbo.Role ON dbo.Tai_Khoan.ID_Role = dbo.Role.ID """;
        //b2 mo cong ket noi
        //try ...with...resource=> tu dong cong ket noi sql
        try (Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            //table=> resultset
            ResultSet rs = ps.executeQuery();// doi voi cac cau sql, sd excureQuery => tra ve 1 bang (resultset)
            List<taiKhoanRespon> list = new ArrayList<>();
            while (rs.next()) {
                taiKhoanRespon kh = taiKhoanRespon.builder()
                        .id(rs.getInt(1))
                        .tenDangNhap(rs.getString(2))
                        .matKhau(rs.getString(3))
                        .id_Role(rs.getInt(4))
                        .ten(rs.getString(5))
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

    public boolean checkUser(String email) {

        String sql = """
                   	SELECT  * FROM [dbo].[Tai_Khoan] where Email =?
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, email);
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            if (count == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean Update(String email) {

        String sql = """
                   	UPDATE [dbo].[Tai_Khoan]
                           SET 
                              [matKhau] = ?
                              
                         WHERE [Email]=?
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, email);
            String password = new RandomSNN().soNgauNhienString(7);
            ps.setObject(1, password);
            ps.setObject(2, email);
            XEmail v = new XEmail();
           v.sendEmail(email, password);
           return ps.executeUpdate()>0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean add(Tai_Khoan kh) {
        int check = 0;
        String sql = """
                   	INSERT INTO [dbo].[Tai_Khoan]
                                   ([tenDangNhap]
                                   ,[matKhau]
                        		   ,[Email]
                                   ,[ID_Role]
                                   ,[TrangThai]
                                   )
                             VALUES
                                   (?,?,?,?,1)
                     """;

        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, kh.getTenDangNhap());
            ps.setString(2, kh.getMatKhau());
            ps.setString(3, kh.getEmail());
            ps.setObject(4, kh.getId_Role());

            //trang thái = 1 => chưa xóa
            //trạng thái =0 => xóa
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check > 0;
    }
}
