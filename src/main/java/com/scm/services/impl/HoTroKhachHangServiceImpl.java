/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.DonHang;
import com.scm.pojo.HoTroKhachHang;
import com.scm.repositories.HoTroKhachHangRepository;
import com.scm.services.HoTroKhachHangService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class HoTroKhachHangServiceImpl implements HoTroKhachHangService{
    
    @Autowired
    private HoTroKhachHangRepository HTKHRepo;
    
    @Override
    public List<HoTroKhachHang> getAll() {
        return this.HTKHRepo.getAll();
    }

    @Override
    public List<HoTroKhachHang> getByNameKH(String tenKhachHang) {
        return this.HTKHRepo.getByNameKH(tenKhachHang);
    }

    @Override
    public List<HoTroKhachHang> getByDonHang(DonHang dh) {
        return this.HTKHRepo.getByDonHang(dh);
    }

    @Override
    public List<HoTroKhachHang> getByEmailKH(String emailKH) {
        return this.HTKHRepo.getByEmailKH(emailKH);
    }

    @Override
    public List<HoTroKhachHang> getByTrangThai(String status) {
        return this.HTKHRepo.getByTrangThai(status);
    }

    @Override
    public List<HoTroKhachHang> getByDateRange(Date start, Date end) {
        return this.HTKHRepo.getByDateRange(start, end);
    }

    @Override
    public HoTroKhachHang getByCreateDate(Date ngayTao) {
        return this.HTKHRepo.getByCreateDate(ngayTao);
    }
    
}
