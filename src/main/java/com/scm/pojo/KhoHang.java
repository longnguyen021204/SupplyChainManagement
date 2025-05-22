/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "KhoHang")
@NamedQueries({
        @NamedQuery(name = "KhoHang.findAll", query = "SELECT k FROM KhoHang k"),
        @NamedQuery(name = "KhoHang.findByTen", query = "SELECT k FROM KhoHang k WHERE LOWER(k.tenKho) LIKE LOWER(:tenKho)"),
        @NamedQuery(name = "KhoHang.findByDiaChi", query = "SELECT k FROM KhoHang k WHERE LOWER(k.diaChi) LIKE LOWER(:diaChi)"),
        
})
public class KhoHang implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Kho_ID")
    private Integer khoId;

    @Column(name = "TenKho", nullable = false, length = 255)
    private String tenKho;

    @Column(name = "DiaChi", nullable = false, length = 255)
    private String diaChi;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    @OneToMany(mappedBy = "khoHang")
    @JsonIgnore
    private List<QuanLyKho> quanLyKho;

    public KhoHang() {
    }

    public KhoHang(Integer khoId, String tenKho, String diaChi,  List<QuanLyKho> quanLyKho) {
        this.khoId = khoId;
        this.tenKho = tenKho;
        this.diaChi = diaChi;
//        this.ngayTao = ngayTao;
//        this.ngayCapNhat = ngayCapNhat;
        this.quanLyKho = quanLyKho;
    }

    /**
     * @return the khoId
     */
    public Integer getKhoId() {
        return khoId;
    }

    /**
     * @param khoId the khoId to set
     */
    public void setKhoId(Integer khoId) {
        this.khoId = khoId;
    }

    /**
     * @return the tenKho
     */
    public String getTenKho() {
        return tenKho;
    }

    /**
     * @param tenKho the tenKho to set
     */
    public void setTenKho(String tenKho) {
        this.tenKho = tenKho;
    }

    /**
     * @return the diaChi
     */
    public String getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
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
     * @return the quanLyKho
     */
    public List<QuanLyKho> getQuanLyKho() {
        return quanLyKho;
    }

    /**
     * @param quanLyKho the quanLyKho to set
     */
    public void setQuanLyKho(List<QuanLyKho> quanLyKho) {
        this.quanLyKho = quanLyKho;
    }
    
    
}
