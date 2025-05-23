/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.DonGia;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface DonGiaRepository {
    DonGia getDonGiaByMaSP(String maSanPham);
    List<DonGia> getDonGiaByNCC(String tenNCC);
    List<DonGia> getDonGiaBetween(BigDecimal min, BigDecimal max);
    List<DonGia> getDonGiaByNgayApDung(Date ngayApDung);
    
}
