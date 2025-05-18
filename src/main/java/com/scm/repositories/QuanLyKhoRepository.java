/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.QuanLyKho;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface QuanLyKhoRepository {
    List<QuanLyKho> getAllSanPham ();
    List<QuanLyKho> getSanPhamByMaSP (String maSanPham);
    List<QuanLyKho> getSanPhamByName(String tenSanPham);
    List<QuanLyKho> getSanPhamHetHang();
    
    QuanLyKho addOrUpdateSanPham(QuanLyKho sp);
    
}
