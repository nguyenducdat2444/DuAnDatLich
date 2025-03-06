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
public class CaLamResponse {

    private Integer id;
    private Integer id_CaLam;
    private Integer id_NV_Nhan;
    private String ten_NV;
    private Date ngayCaLam;
    private String ghiChu;
    private double tien_Ca_Lam;
    private String tenCaLam;
    private Integer trangThai;
}
