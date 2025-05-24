/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "ChiTietDonHangNhap")
@NamedQueries({
    @NamedQuery(name = "ChiTietDonHangNhap.findAll", query = "SELECT c FROM ChiTietDonHangNhap c"),
    @NamedQuery(name = "ChiTietDonHangNhap.findByDonHangId", query = "SELECT c FROM ChiTietDonHangNhap c WHERE c.donHang.dhId = :dhId")
})
public class ChiTietDonHangNhap implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTDHN_ID")
    private Integer ctdhnId;

    @ManyToOne
    @JoinColumn(name = "DH_ID", nullable = false)
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "NCC_ID", nullable = false)
    private NhaCungCap nhaCungCap;

    @Column(name = "MaSanPham", nullable = false, length = 50)
    private String maSanPham;

    @Column(name = "TenSanPham", nullable = false, length = 255)
    private String tenSanPham;

    @Column(name = "SoLuong", nullable = false)
    private Integer soLuong;

    @Column(name = "DonGia", nullable = false, precision = 15, scale = 2)
    private BigDecimal donGia;

    @Column(name = "ThanhTien", precision = 15, scale = 2)
    private BigDecimal thanhTien;

    @Column(name = "NgayNhapKho")
    private Date ngayNhapKho;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    public ChiTietDonHangNhap() {
    }

    

    public ChiTietDonHangNhap(Integer ctdhnId, DonHang donHang, NhaCungCap nhaCungCap, String maSanPham, String tenSanPham, Integer soLuong, BigDecimal donGia) {
        this.ctdhnId = ctdhnId;
        this.donHang = donHang;
        this.nhaCungCap = nhaCungCap;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
//        this.thanhTien = thanhTien;
//        this.ngayNhapKho = ngayNhapKho;
//        this.ngayTao = ngayTao;
//        this.ngayCapNhat = ngayCapNhat;
    }

    
    /**
     * @return the ctdhnId
     */
    public Integer getCtdhnId() {
        return ctdhnId;
    }

    /**
     * @param ctdhnId the ctdhnId to set
     */
    public void setCtdhnId(Integer ctdhnId) {
        this.ctdhnId = ctdhnId;
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
     * @return the nhaCungCap
     */
    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    /**
     * @param nhaCungCap the nhaCungCap to set
     */
    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    /**
     * @return the maSanPham
     */
    public String getMaSanPham() {
        return maSanPham;
    }

    /**
     * @param maSanPham the maSanPham to set
     */
    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    /**
     * @return the tenSanPham
     */
    public String getTenSanPham() {
        return tenSanPham;
    }

    /**
     * @param tenSanPham the tenSanPham to set
     */
    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    /**
     * @return the soLuong
     */
    public Integer getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the donGia
     */
    public BigDecimal getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     */
    public void setDonGia(BigDecimal donGia) {
        this.donGia = donGia;
    }

    /**
     * @return the thanhTien
     */
    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    /**
     * @param thanhTien the thanhTien to set
     */
    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }

    /**
     * @return the ngayNhapKho
     */
    public Date getNgayNhapKho() {
        return ngayNhapKho;
    }

    /**
     * @param ngayNhapKho the ngayNhapKho to set
     */
    public void setNgayNhapKho(Date ngayNhapKho) {
        this.ngayNhapKho = ngayNhapKho;
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
