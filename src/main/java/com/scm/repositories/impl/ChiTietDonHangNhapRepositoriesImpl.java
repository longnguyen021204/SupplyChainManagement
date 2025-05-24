/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.ChiTietDonHangNhap;
import com.scm.pojo.DonHang;
import com.scm.pojo.HoTroKhachHang;
import com.scm.repositories.ChiTietDonHangNhapRepository;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Map;
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
public class ChiTietDonHangNhapRepositoriesImpl implements ChiTietDonHangNhapRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ChiTietDonHangNhap> getDonHangNhap() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ChiTietDonHangNhap.findAll", ChiTietDonHangNhap.class);
        return q.getResultList();
    }

    @Override
    public ChiTietDonHangNhap createDonHangNhap(ChiTietDonHangNhap dhNhap) {
        Session s = this.factory.getObject().getCurrentSession();
        if (dhNhap.getCtdhnId() == null) {
            s.persist(dhNhap);
        } else {
            s.merge(dhNhap);
        }
        s.refresh(dhNhap);
        return dhNhap;
    }

    @Override
    public List<ChiTietDonHangNhap> getDonHangNhapById(int dhId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ChiTietDonHangNhap.findByDonHangId", ChiTietDonHangNhap.class);
        q.setParameter("dhId", dhId);
        return q.getResultList();
    }

}
