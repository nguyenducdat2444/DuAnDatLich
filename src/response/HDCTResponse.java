/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import java.time.LocalTime;
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
public class HDCTResponse {
    private int idHDCT;
    
    private String maHD;
    
    private String maDV;
    
    private String tenDV;
    
    private int soLuong;
    
    private float donGia;
    
    private float thanhTien;
    
    private int idHd;

    private int idDV;

    private LocalTime thoiGianDV;
    
}
