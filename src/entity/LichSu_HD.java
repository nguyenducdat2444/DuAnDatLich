/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
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
public class LichSu_HD {

    private Integer id;
    private Integer id_HD;
    private String nguoi_Sua;
    private LocalDate thoiGian;
    private String nd_NguoiSua;
    private Integer soLanThayDoi;
    private String moTa;
    private Integer trangThai;
}
