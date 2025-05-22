/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.DonHang;
import com.scm.pojo.HoTroKhachHang;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface HoTroKhachHangService {
    List<HoTroKhachHang> getAll();
    List<HoTroKhachHang> getByNameKH(String tenKhachHang);
    List<HoTroKhachHang> getByDonHang(DonHang dh);
    List<HoTroKhachHang> getByEmailKH(String emailKH);
    List<HoTroKhachHang> getByTrangThai(String status);
    List<HoTroKhachHang> getByDateRange(Date start, Date end);
    HoTroKhachHang getByCreateDate(Date ngayTao);
}
