/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.KhoHang;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface KhoHangService {

    List<KhoHang> getKhoHang();

    List<KhoHang> getKhoHangByName(String tenKho);

    List<KhoHang> getKhoHangByDiaChi(String diaChiKho);
}
