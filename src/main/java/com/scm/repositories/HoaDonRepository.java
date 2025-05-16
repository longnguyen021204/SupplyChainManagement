/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.DonHang;
import com.scm.pojo.HoaDon;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface HoaDonRepository {
    List<HoaDon> getHoaDon();
    List<HoaDon> getHoaDonByMaHD(String maHD);
    List<HoaDon> getHoaDonByDonHang(DonHang dh);
    List<HoaDon> getHoaDonByDate(LocalDateTime ngayLap);
    List<HoaDon> getHoaDonByTrangThai(String status);
    List<HoaDon> getHoaDonByTongTien(BigDecimal tongTien);
}
