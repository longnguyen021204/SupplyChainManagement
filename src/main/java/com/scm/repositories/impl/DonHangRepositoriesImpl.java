/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.DonHang;
import com.scm.repositories.DonHangRepository;
import jakarta.persistence.Query;
import java.time.LocalDateTime;
import java.util.Date;
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
public class DonHangRepositoriesImpl implements DonHangRepository{

    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public DonHang getDonHangByMaDH(String maDH) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DonHang.findByMaDH", DonHang.class);
        q.setParameter("maDH", maDH);
        return (DonHang) q.getSingleResult();
    }

    @Override
    public List<DonHang> getDonHangByTrangThai(String status) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DonHang.findByTrangThai", DonHang.class);
        q.setParameter("trangThai", status);
        return q.getResultList();
    }

    @Override
    public List<DonHang> getDonHangByNgayDatHangBetween(Date startDate, Date endDate) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DonHang.findByNgayDatHangRange", DonHang.class);
        q.setParameter("startDate", startDate);
        q.setParameter("endDate", endDate);
        return q.getResultList();
    }

    @Override
    public List<DonHang> getDonHangByNgayDatHang(Date ngayDatHang) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DonHang.findByNgayDatHang", DonHang.class);
        q.setParameter("ngayDatHang", ngayDatHang);
        return q.getResultList();
    }

    @Override
    public DonHang createDonHang(DonHang dh) {
        Session s = this.factory.getObject().getCurrentSession();
        if (dh.getDhId() == null) {
            s.persist(dh);
        } else {
            s.merge(dh);
        }
        return dh;
    }

    @Override
    public void cancelDonHang(String maDH) {
        Session s = this.factory.getObject().getCurrentSession();
        DonHang d = this.getDonHangByMaDH(maDH);
        s.remove(d);
    }
    
    
    
}
