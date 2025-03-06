/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import java.sql.Time;
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
public class DichVuRespon {

    private Integer id;
    private String ma_DV;
    private String ten_DV;
    private String anh;
    private Time ThoiGianDV;
    private double gia_Tien;
    private String moTa;
    private Integer id_LoaiDV;
    private Integer trangThai;
    private String ten_LoaiDV;
}
