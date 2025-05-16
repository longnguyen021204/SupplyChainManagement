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
@Table(name = "NhaCungCap")
@NamedQueries({
        @NamedQuery(name = "NhaCungCap.findAll", query = "SELECT n FROM NhaCungCap n"),
        @NamedQuery(name = "NhaCungCap.findByTenNCC", query = "SELECT n FROM NhaCungCap n WHERE LOWER(n.tenNCC) LIKE LOWER(:tenNCC)"),
        @NamedQuery(name = "NhaCungCap.findByDiaChi", query = "SELECT n FROM NhaCungCap n WHERE LOWER(n.diaChi) LIKE LOWER(:diaChi)"),
        @NamedQuery(name = "NhaCungCap.findByEmail", query = "SELECT n FROM NhaCungCap n WHERE LOWER(n.email) LIKE LOWER(:email)"),
        @NamedQuery(name = "NhaCungCap.sortByDiemDanhGiaDesc", query = "SELECT n FROM NhaCungCap n ORDER BY n.diemDanhGia DESC"),
        @NamedQuery(name = "NhaCungCap.findByNameOrAddress", query = "SELECT n FROM NhaCungCap n WHERE LOWER(n.tenNCC) LIKE LOWER(:keyword) OR LOWER(n.diaChi) LIKE LOWER(:keyword)")
})
public class NhaCungCap implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NCC_ID")
    private Integer nccId;

    @Column(name = "TenNCC", nullable = false, length = 255)
    private String tenNCC;

    @Column(name = "DiaChi", nullable = false, length = 255)
    private String diaChi;

    @Column(name = "SoDienThoai", nullable = false, length = 20)
    private String soDienThoai;

    @Column(name = "Email", length = 100)
    private String email;

    @Column(name = "ThongTinLienHe")
    private String thongTinLienHe;

    @Column(name = "DieuKienThanhToan", columnDefinition = "TEXT")
    private String dieuKienThanhToan;

    @Column(name = "DiemDanhGia")
    private Float diemDanhGia;

    @Column(name = "SoLanDanhGia")
    private Integer soLanDanhGia;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    @OneToMany(mappedBy = "nhaCungCap")
    @JsonIgnore
    private List<ChiTietDonHangNhap> chiTietDonHangNhap;

    @OneToMany(mappedBy = "nhaCungCap")
    @JsonIgnore
    private List<DonGia> donGia;

   
    public NhaCungCap() {
    }

    public NhaCungCap(Integer nccId, String tenNCC, String diaChi, String soDienThoai, String email, String thongTinLienHe, String dieuKienThanhToan, Float diemDanhGia, Integer soLanDanhGia, Date ngayTao, Date ngayCapNhat, List<ChiTietDonHangNhap> chiTietDonHangNhap, List<DonGia> donGia) {
        this.nccId = nccId;
        this.tenNCC = tenNCC;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.thongTinLienHe = thongTinLienHe;
        this.dieuKienThanhToan = dieuKienThanhToan;
        this.diemDanhGia = diemDanhGia;
        this.soLanDanhGia = soLanDanhGia;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.chiTietDonHangNhap = chiTietDonHangNhap;
        this.donGia = donGia;
    }

    
    /**
     * @return the nccId
     */
    public Integer getNccId() {
        return nccId;
    }

    /**
     * @param nccId the nccId to set
     */
    public void setNccId(Integer nccId) {
        this.nccId = nccId;
    }

    /**
     * @return the tenNCC
     */
    public String getTenNCC() {
        return tenNCC;
    }

    /**
     * @param tenNCC the tenNCC to set
     */
    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
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
     * @return the soDienThoai
     */
    public String getSoDienThoai() {
        return soDienThoai;
    }

    /**
     * @param soDienThoai the soDienThoai to set
     */
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the thongTinLienHe
     */
    public String getThongTinLienHe() {
        return thongTinLienHe;
    }

    /**
     * @param thongTinLienHe the thongTinLienHe to set
     */
    public void setThongTinLienHe(String thongTinLienHe) {
        this.thongTinLienHe = thongTinLienHe;
    }

    /**
     * @return the dieuKienThanhToan
     */
    public String getDieuKienThanhToan() {
        return dieuKienThanhToan;
    }

    /**
     * @param dieuKienThanhToan the dieuKienThanhToan to set
     */
    public void setDieuKienThanhToan(String dieuKienThanhToan) {
        this.dieuKienThanhToan = dieuKienThanhToan;
    }

    /**
     * @return the diemDanhGia
     */
    public Float getDiemDanhGia() {
        return diemDanhGia;
    }

    /**
     * @param diemDanhGia the diemDanhGia to set
     */
    public void setDiemDanhGia(Float diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
    }

    /**
     * @return the soLanDanhGia
     */
    public Integer getSoLanDanhGia() {
        return soLanDanhGia;
    }

    /**
     * @param soLanDanhGia the soLanDanhGia to set
     */
    public void setSoLanDanhGia(Integer soLanDanhGia) {
        this.soLanDanhGia = soLanDanhGia;
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
        this.setNgayTao(ngayTao);
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
        this.setNgayCapNhat(ngayCapNhat);
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
     * @return the donGia
     */
    public List<DonGia> getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     */
    public void setDonGia(List<DonGia> donGia) {
        this.donGia = donGia;
    }

}
