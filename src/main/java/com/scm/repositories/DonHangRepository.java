/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.DonHang;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface DonHangRepository {

    DonHang getDonHangByMaDH(String maDH);

    List<DonHang> getDonHangByTrangThai(String status);

    List<DonHang> getDonHangByNgayDatHangBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    List<DonHang> getDonHangByNgayDatHang(LocalDateTime ngayDatHang);
    
    DonHang createDonHang(DonHang dh);
}
