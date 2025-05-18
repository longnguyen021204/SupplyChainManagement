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
@Table(name = "HoTroKhachHang")
@NamedQueries({
        @NamedQuery(name = "HoTroKhachHang.findAll", query = "SELECT h FROM HoTroKhachHang h"),
        @NamedQuery(name = "HoTroKhachHang.findByDonHangId", query = "SELECT h FROM HoTroKhachHang h WHERE h.donHang.dhId = :donHangId"),
        @NamedQuery(name = "HoTroKhachHang.findByTrangThaiHT", query = "SELECT h FROM HoTroKhachHang h WHERE h.trangThaiHT = :trangThaiHT"),
        @NamedQuery(name = "HoTroKhachHang.findByNgayYeuCauRange", query = "SELECT h FROM HoTroKhachHang h WHERE h.ngayTao BETWEEN :startDate AND :endDate ORDER BY h.ngayTao DESC"),
        @NamedQuery(name = "HoTroKhachHang.sortByNgayYeuCauDesc", query = "SELECT h FROM HoTroKhachHang h ORDER BY h.ngayTao DESC")
})
public class HoTroKhachHang implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HTKH_ID")
    private Integer htkhId;

    @ManyToOne
    @JoinColumn(name = "DH_ID")
    private DonHang donHang;

    @Column(name = "TenKhachHang", nullable = false, length = 255)
    private String tenKhachHang;

    @Column(name = "EmailKhachHang", nullable = false, length = 100)
    private String emailKhachHang;

    @Column(name = "SoDienThoaiKhachHang", nullable = false, length = 20)
    private String soDienThoaiKhachHang;

    @Column(name = "TieuDe", nullable = false, length = 255)
    private String tieuDe;

    @Column(name = "NoiDung", nullable = false, columnDefinition = "TEXT")
    private String noiDung;

    @Column(name = "TrangThaiHT", length = 50)
    private String trangThaiHT;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    public HoTroKhachHang() {
    }

    public HoTroKhachHang(Integer htkhId, DonHang donHang, String tenKhachHang, String emailKhachHang, String soDienThoaiKhachHang, String tieuDe, String noiDung, String trangThaiHT, Date ngayTao, Date ngayCapNhat) {
        this.htkhId = htkhId;
        this.donHang = donHang;
        this.tenKhachHang = tenKhachHang;
        this.emailKhachHang = emailKhachHang;
        this.soDienThoaiKhachHang = soDienThoaiKhachHang;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.trangThaiHT = trangThaiHT;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
    }

    /**
     * @return the htkhId
     */
    public Integer getHtkhId() {
        return htkhId;
    }

    /**
     * @param htkhId the htkhId to set
     */
    public void setHtkhId(Integer htkhId) {
        this.htkhId = htkhId;
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
     * @return the tenKhachHang
     */
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    /**
     * @param tenKhachHang the tenKhachHang to set
     */
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    /**
     * @return the emailKhachHang
     */
    public String getEmailKhachHang() {
        return emailKhachHang;
    }

    /**
     * @param emailKhachHang the emailKhachHang to set
     */
    public void setEmailKhachHang(String emailKhachHang) {
        this.emailKhachHang = emailKhachHang;
    }

    /**
     * @return the soDienThoaiKhachHang
     */
    public String getSoDienThoaiKhachHang() {
        return soDienThoaiKhachHang;
    }

    /**
     * @param soDienThoaiKhachHang the soDienThoaiKhachHang to set
     */
    public void setSoDienThoaiKhachHang(String soDienThoaiKhachHang) {
        this.soDienThoaiKhachHang = soDienThoaiKhachHang;
    }

    /**
     * @return the tieuDe
     */
    public String getTieuDe() {
        return tieuDe;
    }

    /**
     * @param tieuDe the tieuDe to set
     */
    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    /**
     * @return the noiDung
     */
    public String getNoiDung() {
        return noiDung;
    }

    /**
     * @param noiDung the noiDung to set
     */
    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    /**
     * @return the trangThaiHT
     */
    public String getTrangThaiHT() {
        return trangThaiHT;
    }

    /**
     * @param trangThaiHT the trangThaiHT to set
     */
    public void setTrangThaiHT(String trangThaiHT) {
        this.trangThaiHT = trangThaiHT;
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
