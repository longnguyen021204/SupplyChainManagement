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
@Table(name = "VanChuyen")
@NamedQueries({
        @NamedQuery(name = "VanChuyen.findAll", query = "SELECT v FROM VanChuyen v"),
        @NamedQuery(name = "VanChuyen.findByDonHangId", query = "SELECT v FROM VanChuyen v WHERE v.donHang.dhId = :donHangId"),
        @NamedQuery(name = "VanChuyen.findByTrangThaiVC", query = "SELECT v FROM VanChuyen v WHERE v.trangThaiVC = :trangThaiVC"),
        @NamedQuery(name = "VanChuyen.findByNgayLenLichBefore", query = "SELECT v FROM VanChuyen v WHERE v.ngayLenLich < :ngay"),
        @NamedQuery(name = "VanChuyen.findByDoiTacVC", query = "SELECT v FROM VanChuyen v WHERE v.doiTacVC.dtvcId = :doiTacVCId")
})
public class VanChuyen implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VC_ID")
    private Integer vcId;

    @OneToOne
    @JoinColumn(name = "DH_ID", nullable = false, unique = true)
    private DonHang donHang;

    @ManyToOne
    @JoinColumn(name = "DoiTacVC_ID")
    private DoiTacVanChuyen doiTacVC;

    @Column(name = "NgayLenLich", nullable = false)
    private Date ngayLenLich;

    @Column(name = "TrangThaiVC", length = 50)
    private String trangThaiVC;

    @Column(name = "ThongTinTheoDoi")
    private String thongTinTheoDoi;

    @Column(name = "NgayTao")
    private Date ngayTao;

    @Column(name = "NgayCapNhat")
    private Date ngayCapNhat;

    public VanChuyen() {
    }

    public VanChuyen(Integer vcId, DonHang donHang, DoiTacVanChuyen doiTacVC, Date ngayLenLich, String trangThaiVC, String thongTinTheoDoi) {
        this.vcId = vcId;
        this.donHang = donHang;
        this.doiTacVC = doiTacVC;
        this.ngayLenLich = ngayLenLich;
        this.trangThaiVC = trangThaiVC;
        this.thongTinTheoDoi = thongTinTheoDoi;
    }

    /**
     * @return the vcId
     */
    public Integer getVcId() {
        return vcId;
    }

    /**
     * @param vcId the vcId to set
     */
    public void setVcId(Integer vcId) {
        this.vcId = vcId;
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
     * @return the doiTacVC
     */
    public DoiTacVanChuyen getDoiTacVC() {
        return doiTacVC;
    }

    /**
     * @param doiTacVC the doiTacVC to set
     */
    public void setDoiTacVC(DoiTacVanChuyen doiTacVC) {
        this.doiTacVC = doiTacVC;
    }

    /**
     * @return the ngayLenLich
     */
    public Date getNgayLenLich() {
        return ngayLenLich;
    }

    /**
     * @param ngayLenLich the ngayLenLich to set
     */
    public void setNgayLenLich(Date ngayLenLich) {
        this.ngayLenLich = ngayLenLich;
    }

    /**
     * @return the trangThaiVC
     */
    public String getTrangThaiVC() {
        return trangThaiVC;
    }

    /**
     * @param trangThaiVC the trangThaiVC to set
     */
    public void setTrangThaiVC(String trangThaiVC) {
        this.trangThaiVC = trangThaiVC;
    }

    /**
     * @return the thongTinTheoDoi
     */
    public String getThongTinTheoDoi() {
        return thongTinTheoDoi;
    }

    /**
     * @param thongTinTheoDoi the thongTinTheoDoi to set
     */
    public void setThongTinTheoDoi(String thongTinTheoDoi) {
        this.thongTinTheoDoi = thongTinTheoDoi;
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
