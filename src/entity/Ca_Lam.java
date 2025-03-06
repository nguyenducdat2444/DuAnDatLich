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

@AllArgsConstructor//contrutor full tham so
@NoArgsConstructor // contrutor ko tham so
@Getter
@Setter
@ToString
@Builder
public class Ca_Lam {

    private Integer id;
    private Integer id_LichLam;
    private Time tg_BD;
    private Time tg_KT;
    private String tenCaLam;
}
