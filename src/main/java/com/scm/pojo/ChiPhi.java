/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ChiPhi")
@NamedQueries({
        @NamedQuery(name = "ChiPhi.findAll", query = "SELECT c FROM ChiPhi c"),
        @NamedQuery(name = "ChiPhi.findByDonHangId", query = "SELECT c FROM ChiPhi c WHERE c.donHang.dhId = :donHangId"),
        @NamedQuery(name = "ChiPhi.findByLoaiChiPhi", query = "SELECT c FROM ChiPhi c WHERE LOWER(c.loaiChiPhi) LIKE LOWER(:loaiChiPhi)"),
        @NamedQuery(name = "ChiPhi.findByKhoangThoiGian", query = "SELECT c FROM ChiPhi c WHERE c.ngayPhatSinh BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = "ChiPhi.sortByNgayPhatSinhDesc", query = "SELECT c FROM ChiPhi c ORDER BY c.ngayPhatSinh DESC")
})
public class ChiPhi implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CP_ID")
    private Integer cpId;

    @Column(name = "LoaiChiPhi", nullable = false, length = 100)
    private String loaiChiPhi;

    @Column(name = "MoTa")
    private String moTa;

    @Column(name = "SoTien", nullable = false, precision = 15, scale = 2)
    private BigDecimal soTien;

    @Column(name = "NgayPhatSinh")
    private Date ngayPhatSinh;

    @ManyToOne
    @JoinColumn(name = "DH_ID")
    private DonHang donHang;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    public ChiPhi() {
    }

    public ChiPhi(Integer cpId, String loaiChiPhi, String moTa, BigDecimal soTien, Date ngayPhatSinh, DonHang donHang, Date ngayTao, Date ngayCapNhat) {
        this.cpId = cpId;
        this.loaiChiPhi = loaiChiPhi;
        this.moTa = moTa;
        this.soTien = soTien;
        this.ngayPhatSinh = ngayPhatSinh;
        this.donHang = donHang;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
    }

    
    /**
     * @return the cpId
     */
    public Integer getCpId() {
        return cpId;
    }

    /**
     * @param cpId the cpId to set
     */
    public void setCpId(Integer cpId) {
        this.cpId = cpId;
    }

    /**
     * @return the loaiChiPhi
     */
    public String getLoaiChiPhi() {
        return loaiChiPhi;
    }

    /**
     * @param loaiChiPhi the loaiChiPhi to set
     */
    public void setLoaiChiPhi(String loaiChiPhi) {
        this.loaiChiPhi = loaiChiPhi;
    }

    /**
     * @return the moTa
     */
    public String getMoTa() {
        return moTa;
    }

    /**
     * @param moTa the moTa to set
     */
    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    /**
     * @return the soTien
     */
    public BigDecimal getSoTien() {
        return soTien;
    }

    /**
     * @param soTien the soTien to set
     */
    public void setSoTien(BigDecimal soTien) {
        this.soTien = soTien;
    }

    /**
     * @return the ngayPhatSinh
     */
    public Date getNgayPhatSinh() {
        return ngayPhatSinh;
    }

    /**
     * @param ngayPhatSinh the ngayPhatSinh to set
     */
    public void setNgayPhatSinh(Date ngayPhatSinh) {
        this.ngayPhatSinh = ngayPhatSinh;
    }

    /**
     * @return the donHang
     */
    public DonHang getDonHang() {
        return donHang;
    }

    /**
     * @param donHang the donHang to set
     */
    public void setDonHang(DonHang donHang) {
        this.donHang = donHang;
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
    
    
}
