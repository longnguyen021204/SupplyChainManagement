/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.DonHang;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface DonHangService {

    DonHang getDonHangByMaDH(String maDH);

    List<DonHang> getDonHangNhap(int khoId);

    List<DonHang> getDonHang();

    List<DonHang> getDonHangXuat(int khoId);

    List<DonHang> getDonHangByTrangThai(String status);

    List<DonHang> getDonHangByNgayDatHangBetween(Date startDate, Date endDate);

    List<DonHang> getDonHangByNgayDatHang(Date ngayDatHang);

    DonHang createDonHang(DonHang dh);

    void cancelDonHang(String maDH);
}
