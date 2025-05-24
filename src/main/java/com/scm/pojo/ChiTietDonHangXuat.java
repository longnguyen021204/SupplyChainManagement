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
@Table(name = "ChiTietDonHangXuat")
@NamedQueries({
    @NamedQuery(name = "ChiTietDonHangXuat.findAll", query = "SELECT c FROM ChiTietDonHangXuat c"),
    @NamedQuery(name = "ChiTietDonHangXuat.findByDonHangId", query = "SELECT c FROM ChiTietDonHangXuat c WHERE c.donHang.dhId = :dhId")
})
public class ChiTietDonHangXuat implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CTDHX_ID")
    private Integer ctdhxId;

    @ManyToOne
    @JoinColumn(name = "DH_ID", nullable = false)
    private DonHang donHang;

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

    @Column(name = "NgayXuatKho")
    private Date ngayXuatKho;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    public ChiTietDonHangXuat(Integer ctdhxId, DonHang donHang, String maSanPham, String tenSanPham, Integer soLuong, BigDecimal donGia, BigDecimal thanhTien, Date ngayXuatKho) {
        this.ctdhxId = ctdhxId;
        this.donHang = donHang;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
        this.ngayXuatKho = ngayXuatKho;
//        this.ngayTao = ngayTao;
//        this.ngayCapNhat = ngayCapNhat;
    }

    
    /**
     * @return the ctdhxId
     */
    public Integer getCtdhxId() {
        return ctdhxId;
    }

    /**
     * @param ctdhxId the ctdhxId to set
     */
    public void setCtdhxId(Integer ctdhxId) {
        this.ctdhxId = ctdhxId;
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
     * @return the ngayXuatKho
     */
    public Date getNgayXuatKho() {
        return ngayXuatKho;
    }

    /**
     * @param ngayXuatKho the ngayXuatKho to set
     */
    public void setNgayXuatKho(Date ngayXuatKho) {
        this.ngayXuatKho = ngayXuatKho;
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
