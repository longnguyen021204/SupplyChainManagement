/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.DonHang;
import com.scm.pojo.ThanhToan;
import com.scm.repositories.ThanhToanRepository;
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
public class ThanhToanRepositoriesImpl implements ThanhToanRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ThanhToan> getThanhToanByDonHang(DonHang dh) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ThanhToan.findByDonHangId", ThanhToan.class);
        q.setParameter("donHangId", dh.getDhId());
        return q.getResultList();
    }

    @Override
    public List<ThanhToan> getThanhToanByType(String loaiTT) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ThanhToan.findByPhuongThuc", ThanhToan.class);
        q.setParameter("loaiThanhToan", loaiTT);
        return q.getResultList();
    }

    @Override
    public List<ThanhToan> getThanhToanByTrangThai(String status) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ThanhToan.findByTrangThaiTT", ThanhToan.class);
        q.setParameter("trangThaiTT", status);
        return q.getResultList();
    }


    @Override
    public List<ThanhToan> getThanhToanByDateRange (Date start, Date end) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ThanhToan.findByKhoangThoiGian", ThanhToan.class);
        q.setParameter("startDate", start);
        q.setParameter("endDate", end);
        return q.getResultList();
    }

}
