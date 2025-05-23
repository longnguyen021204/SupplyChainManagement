/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.HoaDon;
import com.scm.repositories.HoaDonRepository;
import com.scm.services.HoaDonService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class HoaDonServiceImpl implements HoaDonService{

    @Autowired
    private HoaDonRepository hoaDonRepo;
    @Override
    public List<HoaDon> getHoaDon() {
        return this.hoaDonRepo.getHoaDon();
    }

    @Override
    public HoaDon getHoaDonByMaHD(String maHD) {
        return this.hoaDonRepo.getHoaDonByMaHD(maHD);
    }

    @Override
    public List<HoaDon> getHoaDonByDateBetween(Date startDate, Date endDate) {
        return this.hoaDonRepo.getHoaDonByDateBetween(startDate, endDate);
    }

    @Override
    public List<HoaDon> getHoaDonByTrangThai(String status) {
        return this.hoaDonRepo.getHoaDonByTrangThai(status);
    }
    
}
