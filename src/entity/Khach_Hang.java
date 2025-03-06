/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor//contrutor full tham so
@NoArgsConstructor // contrutor ko tham so
@Getter
@Setter
@ToString
@Builder


public class Khach_Hang {

    private Integer id;
    
    private String ma_KH;
    
    private String ten_KH;
    
    private String sdt_KH;
    
    private Integer gioiTinh;
    
    private String diaChi;
    
    private String email;
    
    private Date ngaySinh;
    
    private Integer id_Role;
    
    private Integer trangThai;
    
    private Date ngayTao;
    
    //this.ngayTao = new Date();
//     public Khach_Hang() {
//        this.ma_KH = "KH" + UUID.randomUUID().toString().substring(0, 7); // Ví dụ: KH-123e4567
//    }
    
    @Override
    public String toString() {
        return ten_KH; // Trả về tên khách hàng để hiển thị trong JComboBox
    }
}
