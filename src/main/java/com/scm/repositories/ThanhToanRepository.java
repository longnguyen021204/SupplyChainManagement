/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.DonHang;
import com.scm.pojo.ThanhToan;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface ThanhToanRepository {
    List<ThanhToan> getThanhToanByDonHang(DonHang dh);
    List<ThanhToan> getThanhToanByType(String loaiTT);
    List<ThanhToan> getThanhToanByTrangThai(String status);
    List<ThanhToan> getThanhToanByDateRange(Date start, Date end);
  
}
