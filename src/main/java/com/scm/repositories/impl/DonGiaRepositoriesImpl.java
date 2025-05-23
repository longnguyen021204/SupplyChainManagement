/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.DonGia;
import com.scm.repositories.DonGiaRepository;
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
public class DonGiaRepositoriesImpl implements DonGiaRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public DonGia getDonGiaByMaSP(String maSanPham) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DonGia.findByMaSanPham", DonGia.class);
        q.setParameter("maSanPham", maSanPham);
        return (DonGia) q.getSingleResult();
    }

    @Override
    public List<DonGia> getDonGiaByNCC(String tenNCC) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DonGia.findByNhaCungCap", DonGia.class);
        q.setParameter("tenNCC", tenNCC);
        return q.getResultList();
    }

    @Override
    public List<DonGia> getDonGiaBetween(BigDecimal min, BigDecimal max) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DonGia.findByPriceRange", DonGia.class);
        q.setParameter("minPrice", min);
        q.setParameter("maxPrice", max);
        return q.getResultList();
    }

    @Override
    public List<DonGia> getDonGiaByNgayApDung(Date ngayApDung) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DonGia.findByNgayApDung", DonGia.class);
        q.setParameter("ngayApDung", ngayApDung);
        return q.getResultList();
    }

}
