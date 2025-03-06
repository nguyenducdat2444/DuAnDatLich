/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Admin
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class LichHenResponse {

    private int idLH;

    private Integer id_NV;

    private Integer id_KH;

    private Integer id_DV;

    private String maHD;

    private String maKH;

    private Date ngayHen;

    private Time gioHen;

    private float tienCoc;

    private LocalDateTime ngayTao;

    private int trangThai;

    private String ghiChu;

    private String ten_KH;

    private String ten_DV;

    private String ten_NV;
    
    private String sdt_KH;
    
    private int idHD;

    private int idKH;

    private String tenKh;

    private int trangThaiLH;

    private float tongTien;
    
    private Double thanhTien;
}
