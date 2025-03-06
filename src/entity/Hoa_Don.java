/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.sql.Time;
import java.time.LocalDate;

@AllArgsConstructor//contrutor full tham so
@NoArgsConstructor // contrutor ko tham so
@Getter
@Setter
@ToString
@Builder
public class Hoa_Don {

    private Integer id;
    private Integer id_NV;
    private Integer id_KH;
    private String ma_HD;
    private String ten_HD;
    private LocalDate ngayTao;
    private LocalDate ngaySua;
    private double tong_Tien;
    private String moTa;
    private Integer trangThai;
}
