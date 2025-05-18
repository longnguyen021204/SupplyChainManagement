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
@Table(name = "HoaDon")
@NamedQueries({
        @NamedQuery(name = "HoaDon.findAll", query = "SELECT h FROM HoaDon h"),
        @NamedQuery(name = "HoaDon.findByMaHD", query = "SELECT h FROM HoaDon h WHERE h.maHD = :maHD"),
        @NamedQuery(name = "HoaDon.findByStatus", query = "SELECT h FROM HoaDon h WHERE h.trangThaiHD = :status"),
        @NamedQuery(name = "HoaDon.findByNgayLapRange", query = "SELECT h FROM HoaDon h WHERE h.ngayLapHD BETWEEN :startDate AND :endDate ORDER BY h.ngayLapHD DESC"),
        @NamedQuery(name = "HoaDon.sortByNgayLapDesc", query = "SELECT h FROM HoaDon h ORDER BY h.ngayLapHD DESC")
})
public class HoaDon implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HD_ID")
    private Integer hdId;

    @Column(name = "MaHD", unique = true, nullable = false, length = 50)
    private String maHD;

    @OneToOne
    @JoinColumn(name = "DH_ID", nullable = false)
    private DonHang donHang;

    @Column(name = "NgayLapHD")
    private Date ngayLapHD;

    @Column(name = "TongTien", nullable = false, precision = 15, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "TrangThaiHD", length = 50)
    private String trangThaiHD;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    public HoaDon(){
    }

    public HoaDon(Integer hdId, String maHD, DonHang donHang, Date ngayLapHD, BigDecimal tongTien, String trangThaiHD, Date ngayTao, Date ngayCapNhat) {
        this.hdId = hdId;
        this.maHD = maHD;
        this.donHang = donHang;
        this.ngayLapHD = ngayLapHD;
        this.tongTien = tongTien;
        this.trangThaiHD = trangThaiHD;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
    }
    
    
    /**
     * @return the hdId
     */
    public Integer getHdId() {
        return hdId;
    }

    /**
     * @param hdId the hdId to set
     */
    public void setHdId(Integer hdId) {
        this.hdId = hdId;
    }

    /**
     * @return the maHD
     */
    public String getMaHD() {
        return maHD;
    }

    /**
     * @param maHD the maHD to set
     */
    public void setMaHD(String maHD) {
        this.maHD = maHD;
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
     * @return the ngayLapHD
     */
    public Date getNgayLapHD() {
        return ngayLapHD;
    }

    /**
     * @param ngayLapHD the ngayLapHD to set
     */
    public void setNgayLapHD(Date ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    /**
     * @return the tongTien
     */
    public BigDecimal getTongTien() {
        return tongTien;
    }

    /**
     * @param tongTien the tongTien to set
     */
    public void setTongTien(BigDecimal tongTien) {
        this.tongTien = tongTien;
    }

    /**
     * @return the trangThaiHD
     */
    public String getTrangThaiHD() {
        return trangThaiHD;
    }

    /**
     * @param trangThaiHD the trangThaiHD to set
     */
    public void setTrangThaiHD(String trangThaiHD) {
        this.trangThaiHD = trangThaiHD;
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
