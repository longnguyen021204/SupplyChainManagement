/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.ChiTietDonHangNhap;
import com.scm.pojo.ChiTietDonHangXuat;
import com.scm.pojo.DonHang;
import com.scm.pojo.KhoHang;
import com.scm.repositories.ChiTietDonHangNhapRepository;
import com.scm.repositories.ChiTietDonHangXuatRepository;
import com.scm.repositories.DonHangRepository;
import com.scm.repositories.KhoHangRepository;
import com.scm.services.DonHangService;
import com.scm.services.KhoHangService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Service
public class DonHangServiceImpl implements DonHangService {

    @Autowired
    private DonHangRepository dhRepo;
    @Autowired
    private ChiTietDonHangNhapRepository dhnRepo;
    @Autowired
    private ChiTietDonHangXuatRepository dhxRepo;
    @Autowired
    private KhoHangRepository khoRepo;

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
    public void cancelDonHang(String maDH) {
        this.dhRepo.cancelDonHang(maDH);
    }

    @Override
    public List<DonHang> getDonHang() {
        return this.dhRepo.getDonHang();
    }

    @Override
    public List<DonHang> getDonHangNhap(int khoId) {
        return this.dhRepo.getDonHangNhap(khoId);
    }

    @Override
    public List<DonHang> getDonHangXuat(int khoId) {
        return this.dhRepo.getDonHangXuat(khoId);
    }

//    private void validateKho(Integer khoNhapId, Integer khoXuatId) {
//        boolean isNhapNull = (khoNhapId == null);
//        boolean isXuatNull = (khoXuatId == null);
//
//        if ((isNhapNull && isXuatNull) || (!isNhapNull && !isXuatNull)) {
//            throw new RuntimeException("Lỗi tham số!");
//        }
//    }
    
    @Transactional
    public void updateTongTien(String maDonHang){
        DonHang dh = dhRepo.getDonHangByMaDH(maDonHang);
        
        BigDecimal total = BigDecimal.ZERO;
        
        if (dh.getKhoNhap() != null) {
            // Nên truy vấn trực tiếp từ repository ChiTiet để đảm bảo lấy được tất cả chi tiết mới nhất
            List<ChiTietDonHangNhap> chiTiets = dhnRepo.getDonHangNhapById(dh.getDhId());
            for (ChiTietDonHangNhap chiTiet : chiTiets) {
                if (chiTiet.getThanhTien() != null) {
                    total = total.add(chiTiet.getThanhTien());
                }
            }
        }
        // Nếu là đơn hàng xuất, tính tổng từ ChiTietDonHangXuat
        else if (dh.getKhoXuat() != null) {
            List<ChiTietDonHangXuat> chiTiets = dhxRepo.getDonHangXuatById(dh.getDhId());
            for (ChiTietDonHangXuat chiTiet : chiTiets) {
                if (chiTiet.getThanhTien() != null) {
                    total = total.add(chiTiet.getThanhTien());
                }
            }
        }
        dh.setTongTien(total.setScale(2, RoundingMode.HALF_UP));
    }
    
    @Override
    @Transactional
    public DonHang createDonHang(DonHang dh) {
        return this.dhRepo.createDonHang(dh);
    }
}
