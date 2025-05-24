/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.ChiTietDonHangNhap;
import com.scm.pojo.DonHang;
import com.scm.repositories.ChiTietDonHangNhapRepository;
import com.scm.services.ChiTietDonHangNhapService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class ChiTietDonHangNhapServiceImpl implements ChiTietDonHangNhapService {

    @Autowired
    private ChiTietDonHangNhapRepository dhnRepo;

    @Override
    public List<ChiTietDonHangNhap> getDonHangNhap() {
        return this.dhnRepo.getDonHangNhap();
    }

    @Override
    public ChiTietDonHangNhap createDonHangNhap(ChiTietDonHangNhap dhNhap) {
        return this.dhnRepo.createDonHangNhap(dhNhap);
    }

    @Override
    public List<ChiTietDonHangNhap> getDonHangNhapById(int dhId) {
        return this.dhnRepo.getDonHangNhapById(dhId);
    }

}
