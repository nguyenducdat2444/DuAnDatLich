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
public class Tham_So {

    private Integer id;
    private String maThamSo;
    private String tenThamSo;
    private String gt_HienTai;
    private Date ngay_Ap_Dung;
    private Date ngay_Het_Han;
    private String moTa;
    private Integer trangThai;
}
