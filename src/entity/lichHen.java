/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class lichHen {

    private Integer id;
    
    private Integer id_NV;
    
    private Integer id_KH;
    
    private Integer id_DV;
    
    private String ma_LH;
    
    private Date ngayHen;
    
    private Time gioHen;
    
    private LocalDateTime ngayTao;
    
    private LocalDateTime ngaySua;
    
    private double tien_Coc;
    
    private String ghiChu;
    
    private Integer trangThai;
}
