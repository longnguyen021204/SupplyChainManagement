/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.DonHang;
import com.scm.pojo.HoTroKhachHang;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface HoTroKhachHangRepository {
    List<HoTroKhachHang> getAll();
    List<HoTroKhachHang> getByNameKH(String tenKhachHang);
    List<HoTroKhachHang> getByDonHang(DonHang dh);
    List<HoTroKhachHang> getByEmailKH(String emailKH);
    List<HoTroKhachHang> getByPhoneNumber(String phone);
    List<HoTroKhachHang> getByTitle(String title);
    List<HoTroKhachHang> getByContent(String content);
    List<HoTroKhachHang> getByTrangThai(String status);
    List<HoTroKhachHang> getByCreateDate(LocalDateTime ngayTao);
}
