/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.QuanLyKho;
import com.scm.repositories.QuanLyKhoRepository;
import com.scm.services.QuanLyKhoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class QuanLyKhoServiceImpl implements QuanLyKhoService {

    @Autowired
    private QuanLyKhoRepository qlkRepo;

    @Override
    public List<QuanLyKho> getAllSanPham() {
        return this.qlkRepo.getAllSanPham();
    }

    @Override
    public List<QuanLyKho> getSanPhamByMaSP(String maSanPham) {
        return this.qlkRepo.getSanPhamByMaSP(maSanPham);
    }

    @Override
    public List<QuanLyKho> getSanPhamByName(String tenSanPham) {
        return this.qlkRepo.getSanPhamByName(tenSanPham);
    }

    @Override
    public List<QuanLyKho> getSanPhamHetHang() {
        return this.qlkRepo.getSanPhamHetHang();
    }

    @Override
    public QuanLyKho addOrUpdateSanPham(QuanLyKho sp) {
        return this.qlkRepo.addOrUpdateSanPham(sp);
    }

}
