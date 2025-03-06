/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Time;
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
public class Lich_Lam {

    private Integer id;
    
    private String GhiChu;
    
    private Date ngayLamViec;
    
    private Integer id_NhanVien;

    private Time tg_BD;
    
    private Time tg_KT;
    
    private String tenCaLam;
}
