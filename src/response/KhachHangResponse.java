/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

//import java.sql.Timestamp;
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
import java.time.LocalTime;
/**
 *
 * @author admin
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class KhachHangResponse {

    
    
    private Integer id;
    
    private String ma_LH;
    
    private String ten_KH;
    
    private String ten_DV;
    
    private String ten_NV;
    
    private Integer id_KH;
    
    private Integer id_DV;
    
    private Integer id_LH;
    
    private Date ngayHen;
    
    private Time gioHen;
    
    private LocalDateTime ngayTao;
    
    private LocalDateTime ngaySua;
    
    private Double tien_Coc;
    
    private String ghiChu;
    
    private Integer trangThai;

    private Double thanhTien;
  
}
