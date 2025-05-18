/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.DonHang;
import com.scm.pojo.HoaDon;
import com.scm.pojo.KhoHang;
import com.scm.repositories.HoaDonRepository;
import jakarta.persistence.Query;
import java.math.BigDecimal;
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
public class HoaDonRepositoriesImpl implements HoaDonRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<HoaDon> getHoaDon() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoaDon.findAll", HoaDon.class);
        return q.getResultList();
    }

    @Override
    public List<HoaDon> getHoaDonByDateBetween(Date start, Date end) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoaDon.findByNgayLapRange", HoaDon.class);
        q.setParameter("startDate", start);
        q.setParameter("endDate", end);
        return q.getResultList();
    }

    @Override
    public List<HoaDon> getHoaDonByTrangThai(String status) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoaDon.findByStatus", HoaDon.class);
        q.setParameter("status", status);
        return q.getResultList();
    }

    @Override
    public HoaDon getHoaDonByMaHD(String maHD) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoaDon.findByMaHD", HoaDon.class);
        q.setParameter("maHD", maHD);
        return (HoaDon) q.getSingleResult();
    }

}
