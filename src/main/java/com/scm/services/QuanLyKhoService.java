/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.QuanLyKho;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface QuanLyKhoService {
    List<QuanLyKho> getAllSanPham ();
    List<QuanLyKho> getSanPhamByMaSP (String maSanPham);
    QuanLyKho getSPByKhoIdAndMaSP(int khoId, String maSanPham);
    List<QuanLyKho> getSanPhamByName(String tenSanPham);
    List<QuanLyKho> getSanPhamHetHang();
    
    QuanLyKho addOrUpdateSanPham(QuanLyKho sp);
}
