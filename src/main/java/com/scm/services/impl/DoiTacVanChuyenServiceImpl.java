/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.DoiTacVanChuyen;
import com.scm.repositories.DoiTacVanChuyenRepository;
import com.scm.services.DoiTacVanChuyenService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class DoiTacVanChuyenServiceImpl implements DoiTacVanChuyenService {

    @Autowired
    private DoiTacVanChuyenRepository dtvcRepo;

    @Override
    public DoiTacVanChuyen themDTVC(DoiTacVanChuyen d) {
        return this.dtvcRepo.themDTVC(d);
    }

    @Override
    public List<DoiTacVanChuyen> getDoiTacByName(String tenDoiTac) {
        return this.dtvcRepo.getDoiTacByName(tenDoiTac);
    }

    @Override
    public List<DoiTacVanChuyen> getDoiTacByDiemDGDesc() {
        return this.dtvcRepo.getDoiTacByDiemDGDesc();
    }

    @Override
    public List<DoiTacVanChuyen> getDoiTac() {
        return this.dtvcRepo.getDoiTac();
    }

}
