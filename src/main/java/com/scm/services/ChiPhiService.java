/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.ChiPhi;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface ChiPhiService {

    List<ChiPhi> getByDonHangId(int id);

    List<ChiPhi> getByLoaiChiPhi(String loaiCP);
}
