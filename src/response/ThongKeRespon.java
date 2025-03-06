/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

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
public class ThongKeRespon {

    private Integer id;
    private Date ngayTao;
    private String ma_HD;
    private String ten_KH;
    private String ten_DV;
    private double tong_Tien;
    private String sdt_KH;
    private Integer gioiTinh;
    private String diaChi;
    private String email;
    private Date ngaySinh;
}
