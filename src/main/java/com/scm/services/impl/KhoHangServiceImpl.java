/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.KhoHang;
import com.scm.repositories.KhoHangRepository;
import com.scm.services.KhoHangService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class KhoHangServiceImpl implements KhoHangService {

    @Autowired
    private KhoHangRepository khoRepo;

    @Override
    public List<KhoHang> getKhoHang() {
        return this.khoRepo.getKhoHang();
    }

    @Override
    public List<KhoHang> getKhoHangByName(String tenKho) {
        return this.khoRepo.getKhoHangByName(tenKho);
    }

    @Override
    public List<KhoHang> getKhoHangByDiaChi(String diaChiKho) {
        return this.khoRepo.getKhoHangByDiaChi(diaChiKho);
    }

}
