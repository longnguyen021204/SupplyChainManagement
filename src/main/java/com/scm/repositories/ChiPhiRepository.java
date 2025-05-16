/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.ChiPhi;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface ChiPhiRepository {
    List<ChiPhi> getByDonHangId(int id);
    List<ChiPhi> getByLoaiChiPhi(String loaiCP);
}
