/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.ChiTietDonHangXuat;
import com.scm.repositories.ChiTietDonHangXuatRepository;
import com.scm.services.ChiTietDonHangXuatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class ChiTietDonHangXuatServiceImpl implements ChiTietDonHangXuatService {

    @Autowired
    private ChiTietDonHangXuatRepository dhxRepo;

    @Override
    public List<ChiTietDonHangXuat> getDonHangXuat() {
        return this.dhxRepo.getDonHangXuat();
    }

    @Override
    public ChiTietDonHangXuat createDonHangXuat(ChiTietDonHangXuat dhXuat) {
        return this.dhxRepo.createDonHangXuat(dhXuat);
    }

}
