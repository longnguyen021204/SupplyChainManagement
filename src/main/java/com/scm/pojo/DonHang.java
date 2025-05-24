/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "DonHang")
@NamedQueries({
    @NamedQuery(name = "DonHang.findAll", query = "SELECT d FROM DonHang d"),
    @NamedQuery(name = "DonHang.findByMaDH", query = "SELECT d FROM DonHang d WHERE d.maDH = :maDH"),
    @NamedQuery(name = "DonHang.findByDonHangNhap", query = "SELECT d FROM DonHang d WHERE d.khoNhap.khoId = :khoId"),
    @NamedQuery(name = "DonHang.findByDonHangXuat", query = "SELECT d FROM DonHang d WHERE d.khoXuat.khoId = :khoId"),
    @NamedQuery(name = "DonHang.findByTrangThai", query = "SELECT d FROM DonHang d WHERE d.trangThai = :trangThai"),
    @NamedQuery(name = "DonHang.findByNgayDatHangRange", query = "SELECT d FROM DonHang d WHERE d.ngayDatHang BETWEEN :startDate AND :endDate ORDER BY d.ngayDatHang DESC"),
    @NamedQuery(name = "DonHang.findByNgayDatHang", query = "SELECT d FROM DonHang d WHERE d.ngayDatHang = :ngayDatHang"),
    @NamedQuery(name = "DonHang.sortByNgayDatHangDesc", query = "SELECT d FROM DonHang d ORDER BY d.ngayDatHang DESC")
})
public class DonHang implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DH_ID")
    private Integer dhId;

    @Column(name = "MaDH", unique = true, nullable = false, length = 50)
    private String maDH;

    @Column(name = "NgayDatHang")
    private Date ngayDatHang;

    @Column(name = "TrangThai", length = 50)
    private String trangThai;

    @Column(name = "TongTien", nullable = false, precision = 15, scale = 2)
    private BigDecimal tongTien;

    @Column(name = "GhiChu")
    private String ghiChu;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    @ManyToOne
    @JoinColumn(name = "KhoNhapId", referencedColumnName = "Kho_ID")
    private KhoHang khoNhap;

    @ManyToOne
    @JoinColumn(name = "KhoXuatId", referencedColumnName = "Kho_ID")
    private KhoHang khoXuat;
   
    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ChiTietDonHangNhap> chiTietDonHangNhap;

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ChiTietDonHangXuat> chiTietDonHangXuat;

    @OneToOne(mappedBy = "donHang", cascade = CascadeType.ALL)
    @JsonIgnore
    private VanChuyen vanChuyen;

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ThanhToan> thanhToan;

    @OneToOne(mappedBy = "donHang", cascade = CascadeType.ALL)
    @JsonIgnore
    private HoaDon hoaDon;

    @OneToMany(mappedBy = "donHang", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<HoTroKhachHang> hoTroKhachHang;

    @OneToMany(mappedBy = "donHang")
    @JsonIgnore
    private List<ChiPhi> chiPhi;

    public DonHang() {
        this.ngayDatHang = new Date();
        this.ngayTao = new Date();
    }

    public DonHang(Integer dhId, String maDH, String trangThai, BigDecimal tongTien, String ghiChu, Date ngayTao, KhoHang khoNhap, KhoHang khoXuat) {
        this.dhId = dhId;
        this.maDH = maDH;
//        this.ngayDatHang = ngayDatHang;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
//        this.ngayTao = ngayTao;
//        this.ngayCapNhat = ngayCapNhat;
        this.khoNhap = khoNhap;
        this.khoXuat = khoXuat;
//        this.chiTietDonHangNhap = chiTietDonHangNhap;
//        this.chiTietDonHangXuat = chiTietDonHangXuat;
//        this.vanChuyen = vanChuyen;
//        this.thanhToan = thanhToan;
//        this.hoaDon = hoaDon;
//        this.hoTroKhachHang = hoTroKhachHang;
//        this.chiPhi = chiPhi;
    }

    

    /**
     * @return the dhId
     */
    public Integer getDhId() {
        return dhId;
    }

    /**
     * @param dhId the dhId to set
     */
    public void setDhId(Integer dhId) {
        this.dhId = dhId;
    }

    /**
     * @return the maDH
     */
    public String getMaDH() {
        return maDH;
    }

    /**
     * @param maDH the maDH to set
     */
    public void setMaDH(String maDH) {
        this.maDH = maDH;
    }

    /**
     * @return the ngayDatHang
     */
    public Date getNgayDatHang() {
        return ngayDatHang;
    }

    /**
     * @param ngayDatHang the ngayDatHang to set
     */
    public void setNgayDatHang(Date ngayDatHang) {
        this.ngayDatHang = ngayDatHang;
    }

    /**
     * @return the trangThai
     */
    public String getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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
     * @return the ghiChu
     */
    public String getGhiChu() {
        return ghiChu;
    }

    /**
     * @param ghiChu the ghiChu to set
     */
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
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
     * @return the chiTietDonHangNhap
     */
    public List<ChiTietDonHangNhap> getChiTietDonHangNhap() {
        return chiTietDonHangNhap;
    }

    /**
     * @param chiTietDonHangNhap the chiTietDonHangNhap to set
     */
    public void setChiTietDonHangNhap(List<ChiTietDonHangNhap> chiTietDonHangNhap) {
        this.chiTietDonHangNhap = chiTietDonHangNhap;
    }

    /**
     * @return the chiTietDonHangXuat
     */
    public List<ChiTietDonHangXuat> getChiTietDonHangXuat() {
        return chiTietDonHangXuat;
    }

    /**
     * @param chiTietDonHangXuat the chiTietDonHangXuat to set
     */
    public void setChiTietDonHangXuat(List<ChiTietDonHangXuat> chiTietDonHangXuat) {
        this.chiTietDonHangXuat = chiTietDonHangXuat;
    }

    /**
     * @return the vanChuyen
     */
    public VanChuyen getVanChuyen() {
        return vanChuyen;
    }

    /**
     * @param vanChuyen the vanChuyen to set
     */
    public void setVanChuyen(VanChuyen vanChuyen) {
        this.vanChuyen = vanChuyen;
    }

    /**
     * @return the thanhToan
     */
    public List<ThanhToan> getThanhToan() {
        return thanhToan;
    }

    /**
     * @param thanhToan the thanhToan to set
     */
    public void setThanhToan(List<ThanhToan> thanhToan) {
        this.thanhToan = thanhToan;
    }

    /**
     * @return the hoaDon
     */
    public HoaDon getHoaDon() {
        return hoaDon;
    }

    /**
     * @param hoaDon the hoaDon to set
     */
    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    /**
     * @return the hoTroKhachHang
     */
    public List<HoTroKhachHang> getHoTroKhachHang() {
        return hoTroKhachHang;
    }

    /**
     * @param hoTroKhachHang the hoTroKhachHang to set
     */
    public void setHoTroKhachHang(List<HoTroKhachHang> hoTroKhachHang) {
        this.hoTroKhachHang = hoTroKhachHang;
    }

    /**
     * @return the chiPhi
     */
    public List<ChiPhi> getChiPhi() {
        return chiPhi;
    }

    /**
     * @param chiPhi the chiPhi to set
     */
    public void setChiPhi(List<ChiPhi> chiPhi) {
        this.chiPhi = chiPhi;
    }

    /**
     * @return the khoNhap
     */
    public KhoHang getKhoNhap() {
        return khoNhap;
    }

    /**
     * @param khoNhap the khoNhap to set
     */
    public void setKhoNhap(KhoHang khoNhap) {
        this.khoNhap = khoNhap;
    }

    /**
     * @return the khoXuat
     */
    public KhoHang getKhoXuat() {
        return khoXuat;
    }

    /**
     * @param khoXuat the khoXuat to set
     */
    public void setKhoXuat(KhoHang khoXuat) {
        this.khoXuat = khoXuat;
    }

}
