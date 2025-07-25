/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.KhoHang;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface KhoHangService {

    List<KhoHang> getKhoHang(Map<String, String> params);

    List<KhoHang> getKhoHangByName(String tenKho);
    
    KhoHang getKhoHangById(int khoId);

    List<KhoHang> getKhoHangByDiaChi(String diaChiKho);
}
