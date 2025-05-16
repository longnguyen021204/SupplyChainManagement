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
@Table(name = "DonGia", uniqueConstraints = {@UniqueConstraint(columnNames = {"MaSanPham", "NCC_ID"})})
@NamedQueries({
        @NamedQuery(name = "DonGia.findAll", query = "SELECT d FROM DonGia d"),
        @NamedQuery(name = "DonGia.findByMaSanPham", query = "SELECT d FROM DonGia d WHERE d.maSanPham = :maSanPham"),
        @NamedQuery(name = "DonGia.findByNhaCungCapId", query = "SELECT d FROM DonGia d WHERE d.nhaCungCap.nccId = :nccId"),
        @NamedQuery(name = "DonGia.findByMaSanPhamAndNhaCungCapId", query = "SELECT d FROM DonGia d WHERE d.maSanPham = :maSanPham AND d.nhaCungCap.nccId = :nccId"),
        @NamedQuery(name = "DonGia.findLatestByMaSanPham", query = "SELECT d FROM DonGia d WHERE d.maSanPham = :maSanPham ORDER BY d.ngayApDung DESC"), // Cần lấy bản ghi đầu tiên sau khi sắp xếp
        @NamedQuery(name = "DonGia.findByPriceRange", query = "SELECT d FROM DonGia d WHERE d.donGiaMua BETWEEN :minPrice AND :maxPrice")
})
public class DonGia implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DG_ID")
    private Integer dgId;
    
    @Column(name = "MaSanPham", nullable = false, length = 50)
    private String maSanPham;

    @ManyToOne
    @JoinColumn(name = "NCC_ID", nullable = false)
    private NhaCungCap nhaCungCap;

    @Column(name = "DonGiaMua", nullable = false, precision = 15, scale = 2)
    private BigDecimal donGiaMua;

    @Column(name = "NgayApDung")
    private Date ngayApDung;

    public DonGia() {
    }

    public DonGia(Integer dgId, String maSanPham, NhaCungCap nhaCungCap, BigDecimal donGiaMua, Date ngayApDung) {
        this.dgId = dgId;
        this.maSanPham = maSanPham;
        this.nhaCungCap = nhaCungCap;
        this.donGiaMua = donGiaMua;
        this.ngayApDung = ngayApDung;
    }

    /**
     * @return the dgId
     */
    public Integer getDgId() {
        return dgId;
    }

    /**
     * @param dgId the dgId to set
     */
    public void setDgId(Integer dgId) {
        this.dgId = dgId;
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
     * @return the donGiaMua
     */
    public BigDecimal getDonGiaMua() {
        return donGiaMua;
    }

    /**
     * @param donGiaMua the donGiaMua to set
     */
    public void setDonGiaMua(BigDecimal donGiaMua) {
        this.donGiaMua = donGiaMua;
    }

    /**
     * @return the ngayApDung
     */
    public Date getNgayApDung() {
        return ngayApDung;
    }

    /**
     * @param ngayApDung the ngayApDung to set
     */
    public void setNgayApDung(Date ngayApDung) {
        this.ngayApDung = ngayApDung;
    }
    
    
}
