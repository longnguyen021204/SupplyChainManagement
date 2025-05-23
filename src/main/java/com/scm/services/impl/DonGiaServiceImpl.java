/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.DonGia;
import com.scm.repositories.DonGiaRepository;
import com.scm.services.DonGiaService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class DonGiaServiceImpl implements DonGiaService {

    @Autowired
    private DonGiaRepository donGiaRepo;

    @Override
    public DonGia getDonGiaByMaSP(String maSanPham) {
        return this.donGiaRepo.getDonGiaByMaSP(maSanPham);
    }

    @Override
    public List<DonGia> getDonGiaByNCC(String tenNCC) {
        return this.donGiaRepo.getDonGiaByNCC(tenNCC);
    }

    @Override
    public List<DonGia> getDonGiaBetween(BigDecimal min, BigDecimal max) {
        return this.donGiaRepo.getDonGiaBetween(min, max);
    }

    @Override
    public List<DonGia> getDonGiaByNgayApDung(Date ngayApDung) {
        return this.donGiaRepo.getDonGiaByNgayApDung(ngayApDung);
    }

}
