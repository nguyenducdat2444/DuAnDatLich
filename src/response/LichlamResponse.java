/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import java.sql.Time;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class LichlamResponse {
    private Integer id;
    
    private String GhiChu;
    
    private Date ngayLamViec;
    
    private String ma_NV;
    
    private String ten_NV;
    
    private Time tg_BD;
    
    private Time tg_KT;
    
    private String tenCaLam;
    
    private Integer trangThai;
}
