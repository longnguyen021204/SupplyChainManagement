/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "DoiTacVanChuyen")
@NamedQueries({
        @NamedQuery(name = "DoiTacVanChuyen.findAll", query = "SELECT d FROM DoiTacVanChuyen d"),
        @NamedQuery(name = "DoiTacVanChuyen.findByTenDoiTac", query = "SELECT d FROM DoiTacVanChuyen d WHERE LOWER(d.tenDoiTac) LIKE LOWER(:tenDoiTac)"),
        @NamedQuery(name = "DoiTacVanChuyen.sortByDiemDanhGiaDesc", query = "SELECT d FROM DoiTacVanChuyen d ORDER BY d.diemDanhGia DESC"),
        @NamedQuery(name = "DoiTacVanChuyen.findTopNByDiemDanhGia", query = "SELECT d FROM DoiTacVanChuyen d ORDER BY d.diemDanhGia DESC") // Cần thêm LIMIT trong code
})
public class DoiTacVanChuyen implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DTVC_ID")
    private Integer dtvcId;

    @Column(name = "TenDoiTac", nullable = false, length = 255)
    private String tenDoiTac;

    @Column(name = "ThongTinLienHe", nullable = false, length = 255)
    private String thongTinLienHe;

    @Column(name = "DiemDanhGia")
    private Float diemDanhGia;

    @Column(name = "SoLanDanhGia")
    private Integer soLanDanhGia;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    @OneToMany(mappedBy = "doiTacVC")
    private List<VanChuyen> vanChuyen;

    public DoiTacVanChuyen() {
    }

    public DoiTacVanChuyen(Integer dtvcId, String tenDoiTac, String thongTinLienHe, Float diemDanhGia, Integer soLanDanhGia, Date ngayTao, Date ngayCapNhat, List<VanChuyen> vanChuyens) {
        this.dtvcId = dtvcId;
        this.tenDoiTac = tenDoiTac;
        this.thongTinLienHe = thongTinLienHe;
        this.diemDanhGia = diemDanhGia;
        this.soLanDanhGia = soLanDanhGia;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.vanChuyen = vanChuyens;
    }

    /**
     * @return the dtvcId
     */
    public Integer getDtvcId() {
        return dtvcId;
    }

    /**
     * @param dtvcId the dtvcId to set
     */
    public void setDtvcId(Integer dtvcId) {
        this.dtvcId = dtvcId;
    }

    /**
     * @return the tenDoiTac
     */
    public String getTenDoiTac() {
        return tenDoiTac;
    }

    /**
     * @param tenDoiTac the tenDoiTac to set
     */
    public void setTenDoiTac(String tenDoiTac) {
        this.tenDoiTac = tenDoiTac;
    }

    /**
     * @return the thongTinLienHe
     */
    public String getThongTinLienHe() {
        return thongTinLienHe;
    }

    /**
     * @param thongTinLienHe the thongTinLienHe to set
     */
    public void setThongTinLienHe(String thongTinLienHe) {
        this.thongTinLienHe = thongTinLienHe;
    }

    /**
     * @return the diemDanhGia
     */
    public Float getDiemDanhGia() {
        return diemDanhGia;
    }

    /**
     * @param diemDanhGia the diemDanhGia to set
     */
    public void setDiemDanhGia(Float diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
    }

    /**
     * @return the soLanDanhGia
     */
    public Integer getSoLanDanhGia() {
        return soLanDanhGia;
    }

    /**
     * @param soLanDanhGia the soLanDanhGia to set
     */
    public void setSoLanDanhGia(Integer soLanDanhGia) {
        this.soLanDanhGia = soLanDanhGia;
    }

    /**
     * @return the ngayTao
     */
    public Date getNgayTao() {
        return ngayTao;
    }

    /**
     * @param ngayTao the ngayTao to set
     */
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    /**
     * @return the ngayCapNhat
     */
    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    /**
     * @param ngayCapNhat the ngayCapNhat to set
     */
    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    /**
     * @return the vanChuyens
     */
    public List<VanChuyen> getVanChuyens() {
        return vanChuyen;
    }

    /**
     * @param vanChuyens the vanChuyens to set
     */
    public void setVanChuyens(List<VanChuyen> vanChuyens) {
        this.vanChuyen = vanChuyens;
    }
    
    
}
