/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.NhaCungCap;
import com.scm.repositories.NhaCungCapRepository;
import com.scm.services.NhaCungCapService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class NhaCungCapServiceImpl implements NhaCungCapService {

    @Autowired
    private NhaCungCapRepository nccRepo;

    @Override
    public List<NhaCungCap> getAllNCC() {
        return this.nccRepo.getAllNCC();
    }

    @Override
    public NhaCungCap getNCCByName(String tenNCC) {
        return this.nccRepo.getNCCByName(tenNCC);

    }

    @Override
    public NhaCungCap getNCCById(int id) {
        return this.nccRepo.getNCCById(id);
    }

    @Override
    public NhaCungCap getNCCByEmail(String email) {
        return this.nccRepo.getNCCByEmail(email);
    }

    @Override
    public List<NhaCungCap> sortNCCByDiemDanhGiaDesc() {
        return this.nccRepo.sortNCCByDiemDanhGiaDesc();
    }

    @Override
    public NhaCungCap addOrUpdateNCC(NhaCungCap s) {
        return this.nccRepo.addOrUpdateNCC(s);
    }

    @Override
    public void deleteNCC(int id) {
        this.nccRepo.deleteNCC(id);
    }

    @Override
    public List<NhaCungCap> getSuppliers(Map<String, String> params) {
        return this.nccRepo.getSuppliers(params);
    }

}
