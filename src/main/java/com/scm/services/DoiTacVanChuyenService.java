/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.DoiTacVanChuyen;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface DoiTacVanChuyenService {

    DoiTacVanChuyen themDTVC(DoiTacVanChuyen d);

    List<DoiTacVanChuyen> getDoiTacByName(String tenDoiTac);

    List<DoiTacVanChuyen> getDoiTacByDiemDGDesc();
    List<DoiTacVanChuyen> getDoiTac();
}
