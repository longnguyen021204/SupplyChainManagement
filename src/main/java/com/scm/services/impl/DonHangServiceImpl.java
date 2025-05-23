/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.DonHang;
import com.scm.repositories.DonHangRepository;
import com.scm.services.DonHangService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    private DonHangRepository dhRepo;

    @Override
    public DonHang getDonHangByMaDH(String maDH) {
        return this.dhRepo.getDonHangByMaDH(maDH);
    }

    @Override
    public List<DonHang> getDonHangByTrangThai(String status) {
        return this.dhRepo.getDonHangByTrangThai(status);
    }

    @Override
    public List<DonHang> getDonHangByNgayDatHangBetween(Date startDate, Date endDate) {
        return this.dhRepo.getDonHangByNgayDatHangBetween(startDate, endDate);
    }

    @Override
    public List<DonHang> getDonHangByNgayDatHang(Date ngayDatHang) {
        return this.dhRepo.getDonHangByNgayDatHang(ngayDatHang);
    }

    @Override
    public DonHang createDonHang(DonHang dh) {
        return this.dhRepo.createDonHang(dh);
    }

    @Override
    public void cancelDonHang(String maDH) {
        this.dhRepo.cancelDonHang(maDH);
    }

}
