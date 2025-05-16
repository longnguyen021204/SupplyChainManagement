/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.repositories;

import com.scm.pojo.DoiTacVanChuyen;
import com.scm.pojo.DonHang;
import com.scm.pojo.VanChuyen;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface VanChuyenRepository {
    List<VanChuyen> getVanChuyenByDonHangId(DonHang dh_id);
    List<VanChuyen> getVanChuyenByTrangThai(String trangthai);
    List<VanChuyen> getVanChuyenByDoiTacVC(DoiTacVanChuyen dt);
    List<VanChuyen> getVanChuyenByDate(LocalDateTime ngayLenLich);
  
    VanChuyen addVanChuyen(VanChuyen m);
}
