/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.NhaCungCap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public interface NhaCungCapService {

    List<NhaCungCap> getAllNCC();

    List<NhaCungCap> getSuppliers(Map<String, String> params);

    NhaCungCap getNCCByName(String tenNCC);

    NhaCungCap getNCCById(int id);

    NhaCungCap getNCCByEmail(String email);

    List<NhaCungCap> sortNCCByDiemDanhGiaDesc();

    NhaCungCap addOrUpdateNCC(NhaCungCap s);

    void deleteNCC(int id);
}
