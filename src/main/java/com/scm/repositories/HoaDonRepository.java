/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.DonHang;
import com.scm.pojo.HoaDon;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface HoaDonRepository {
    List<HoaDon> getHoaDon();
    HoaDon getHoaDonByMaHD(String maHD);
    List<HoaDon> getHoaDonByDateBetween(Date startDate, Date endDate);
    List<HoaDon> getHoaDonByTrangThai(String status);
}
