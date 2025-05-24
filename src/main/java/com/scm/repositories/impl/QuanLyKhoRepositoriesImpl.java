/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.QuanLyKho;
import com.scm.repositories.QuanLyKhoRepository;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Repository
@Transactional
public class QuanLyKhoRepositoriesImpl implements QuanLyKhoRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<QuanLyKho> getAllSanPham() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("QuanLyKho.findAll", QuanLyKho.class);
        return q.getResultList();
    }

    @Override
    public List<QuanLyKho> getSanPhamByMaSP(String maSanPham) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("QuanLyKho.findByMaSanPham", QuanLyKho.class);
        q.setParameter("maSanPham", maSanPham);
        return q.getResultList();
    }

    @Override
    public List<QuanLyKho> getSanPhamByName(String tenSanPham) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("QuanLyKho.findByTenSanPham", QuanLyKho.class);
        q.setParameter("tenSanPham", tenSanPham);
        return q.getResultList();
    }

    @Override
    public List<QuanLyKho> getSanPhamHetHang() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("QuanLyKho.findHetHang", QuanLyKho.class);
        return q.getResultList();
    }

    @Override
    public QuanLyKho addOrUpdateSanPham(QuanLyKho sp) {
        Session a = this.factory.getObject().getCurrentSession();
        QuanLyKho p = getSPByKhoIdAndMaSP(sp.getKhoHang().getKhoId(), sp.getMaSanPham());
        if (p == null) {
            a.persist(sp);
        } else {
            int soLuong = p.getSoLuongTon() + sp.getSoLuongTon();
            p.setSoLuongTon(soLuong);
        }
        return sp;
    }

    @Override
    public QuanLyKho getSPByKhoIdAndMaSP(int khoId, String maSanPham) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("QuanLyKho.findByKhoIdAndMaSanPham", QuanLyKho.class);
        q.setParameter("khoId", khoId);
        q.setParameter("maSanPham", maSanPham);

        List<QuanLyKho> result = q.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

}
