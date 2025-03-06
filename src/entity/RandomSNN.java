/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.security.SecureRandom;

/**
 *
 * @author default
 */
public class RandomSNN {
    static final String az="0123456789ABCDEFGHIJKMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz";
    static SecureRandom rd= new SecureRandom();
    public String soNgauNhienString(int n){
        StringBuffer str= new StringBuffer();
        for (int i = 0; i < n; i++) {
           str.append(az.charAt(rd.nextInt(az.length())));
            
        }
        return str.toString();
    }
}
