/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.ChiTietDonHangNhap;
import com.scm.pojo.DonHang;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface ChiTietDonHangNhapService {

    List<ChiTietDonHangNhap> getDonHangNhap();

    List<ChiTietDonHangNhap> getDonHangNhapById(int dhId);

    ChiTietDonHangNhap createDonHangNhap(ChiTietDonHangNhap dhNhap);
}
