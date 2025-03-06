/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
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
public class nhanVien {

    private Integer id;
    
    private String ma_NV;
    
    private String ten_NV;
    
    private String sdt_NV;
    
    private Integer gioiTinh;
    
    private String diaChi;
    
    private String email;
    
    private Date ngaySinh;
    
    private Integer id_Role;
    
    private Integer id_CongViec;
    
    private Integer trangThai;
    
    
    
}
