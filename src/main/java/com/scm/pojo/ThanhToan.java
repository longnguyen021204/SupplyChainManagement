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
@Table(name = "ThanhToan")
@NamedQueries({
        @NamedQuery(name = "ThanhToan.findAll", query = "SELECT t FROM ThanhToan t"),
        @NamedQuery(name = "ThanhToan.findByDonHangId", query = "SELECT t FROM ThanhToan t WHERE t.donHang.dhId = :donHangId"),
        @NamedQuery(name = "ThanhToan.findByPhuongThuc", query = "SELECT t FROM ThanhToan t WHERE LOWER(t.loaiThanhToan) LIKE LOWER(:loaiThanhToan)"),
        @NamedQuery(name = "ThanhToan.findByTrangThaiTT", query = "SELECT t FROM ThanhToan t WHERE t.trangThaiTT = :trangThaiTT"),
        @NamedQuery(name = "ThanhToan.findByKhoangThoiGian", query = "SELECT t FROM ThanhToan t WHERE t.ngayThanhToan BETWEEN :startDate AND :endDate"),
        @NamedQuery(name = "ThanhToan.sortByNgayThanhToanDesc", query = "SELECT t FROM ThanhToan t ORDER BY t.ngayThanhToan DESC")
})
public class ThanhToan implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TT_ID")
    private Integer ttId;

    @ManyToOne
    @JoinColumn(name = "DH_ID", nullable = false)
    private DonHang donHang;

    @Column(name = "LoaiThanhToan", nullable = false, length = 50)
    private String loaiThanhToan;

    @Column(name = "SoTien", nullable = false, precision = 15, scale = 2)
    private BigDecimal soTien;

    @Column(name = "NgayThanhToan")
    private Date ngayThanhToan;

    @Column(name = "TrangThaiTT", length = 50)
    private String trangThaiTT;

    @Column(name = "ThongTinTT")
    private String thongTinTT;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    public ThanhToan() {
    }

    public ThanhToan(Integer ttId, DonHang donHang, String loaiThanhToan, BigDecimal soTien, Date ngayThanhToan, String trangThaiTT, String thongTinTT, Date ngayTao, Date ngayCapNhat) {
        this.ttId = ttId;
        this.donHang = donHang;
        this.loaiThanhToan = loaiThanhToan;
        this.soTien = soTien;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThaiTT = trangThaiTT;
        this.thongTinTT = thongTinTT;

    }

    /**
     * @return the ttId
     */
    public Integer getTtId() {
        return ttId;
    }

    /**
     * @param ttId the ttId to set
     */
    public void setTtId(Integer ttId) {
        this.ttId = ttId;
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
     * @return the loaiThanhToan
     */
    public String getLoaiThanhToan() {
        return loaiThanhToan;
    }

    /**
     * @param loaiThanhToan the loaiThanhToan to set
     */
    public void setLoaiThanhToan(String loaiThanhToan) {
        this.loaiThanhToan = loaiThanhToan;
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
     * @return the ngayThanhToan
     */
    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    /**
     * @param ngayThanhToan the ngayThanhToan to set
     */
    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    /**
     * @return the trangThaiTT
     */
    public String getTrangThaiTT() {
        return trangThaiTT;
    }

    /**
     * @param trangThaiTT the trangThaiTT to set
     */
    public void setTrangThaiTT(String trangThaiTT) {
        this.trangThaiTT = trangThaiTT;
    }

    /**
     * @return the thongTinTT
     */
    public String getThongTinTT() {
        return thongTinTT;
    }

    /**
     * @param thongTinTT the thongTinTT to set
     */
    public void setThongTinTT(String thongTinTT) {
        this.thongTinTT = thongTinTT;
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
