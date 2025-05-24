/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.ChiTietDonHangXuat;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface ChiTietDonHangXuatService {

    List<ChiTietDonHangXuat> getDonHangXuat();

    List<ChiTietDonHangXuat> getDonHangXuatById(int dhId);

    ChiTietDonHangXuat createDonHangXuat(ChiTietDonHangXuat dhXuat);
}
