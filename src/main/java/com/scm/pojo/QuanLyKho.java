/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author LENOVO
 */

@Entity
@Table(name = "QuanLyKho", uniqueConstraints = {@UniqueConstraint(columnNames = {"Kho_ID", "MaSanPham"})})
@NamedQueries({
        @NamedQuery(name = "QuanLyKho.findAll", query = "SELECT q FROM QuanLyKho q"),
        @NamedQuery(name = "QuanLyKho.findByMaSanPham", query = "SELECT q FROM QuanLyKho q WHERE q.maSanPham = :maSanPham"),
        @NamedQuery(name = "QuanLyKho.findByTenSanPham", query = "SELECT q FROM QuanLyKho q WHERE q.tenSanPham = :tenSanPham"),
        @NamedQuery(name = "QuanLyKho.findHetHang", query = "SELECT q FROM QuanLyKho q WHERE q.soLuongTon = 0"),
        @NamedQuery(name = "QuanLyKho.findSapHetHang", query = "SELECT q FROM QuanLyKho q WHERE q.soLuongTon <= :nguong")
})
public class QuanLyKho implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "QLK_ID")
    private Integer qlkId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "Kho_ID", referencedColumnName = "Kho_ID", nullable = false)
    private KhoHang khoHang;

    @Column(name = "MaSanPham", nullable = false, length = 50)
    private String maSanPham;

    @Column(name = "TenSanPham", nullable = false, length = 255)
    private String tenSanPham;

    @Column(name = "SoLuongTon", nullable = false)
    private Integer soLuongTon;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    public QuanLyKho() {
    }

    public QuanLyKho(Integer qlkId, KhoHang khoHang, String maSanPham, String tenSanPham, Integer soLuongTon) {
        this.qlkId = qlkId;
        this.khoHang = khoHang;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.soLuongTon = soLuongTon;
//        this.ngayCapNhat = ngayCapNhat;
    }

    /**
     * @return the qlkId
     */
    public Integer getQlkId() {
        return qlkId;
    }

    /**
     * @param qlkId the qlkId to set
     */
    public void setQlkId(Integer qlkId) {
        this.qlkId = qlkId;
    }

    /**
     * @return the khoHang
     */
    public KhoHang getKhoHang() {
        return khoHang;
    }

    /**
     * @param khoHang the khoHang to set
     */
    public void setKhoHang(KhoHang khoHang) {
        this.khoHang = khoHang;
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
     * @return the soLuongTon
     */
    public Integer getSoLuongTon() {
        return soLuongTon;
    }

    /**
     * @param soLuongTon the soLuongTon to set
     */
    public void setSoLuongTon(Integer soLuongTon) {
        this.soLuongTon = soLuongTon;
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
