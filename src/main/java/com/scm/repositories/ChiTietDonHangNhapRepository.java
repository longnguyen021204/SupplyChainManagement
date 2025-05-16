/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.ChiTietDonHangNhap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface ChiTietDonHangNhapRepository {
    List<ChiTietDonHangNhap> getDonHangNhap(Map<String, String> params);
    
}
