/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.DoiTacVanChuyen;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface DoiTacVanChuyenRepository {
    List<DoiTacVanChuyen> getDoiTacByName(String tenDoiTac);
    List<DoiTacVanChuyen> getDoiTacByDiemDG(Float diemDG);
}
