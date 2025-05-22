/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.DonHang;
import com.scm.pojo.HoTroKhachHang;
import com.scm.repositories.HoTroKhachHangRepository;
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
public class HoTroKhachHangRepositoriesImpl implements HoTroKhachHangRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<HoTroKhachHang> getAll() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoTroKhachHang.findAll", HoTroKhachHang.class);
        return q.getResultList();
    }

    @Override
    public List<HoTroKhachHang> getByNameKH(String tenKhachHang) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoTroKhachHang.findByName", HoTroKhachHang.class);
        q.setParameter("nameKH", tenKhachHang);
        return q.getResultList();

    }

    @Override
    public List<HoTroKhachHang> getByDonHang(DonHang dh) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoTroKhachHang.findByName", HoTroKhachHang.class);
        q.setParameter("donHangId", dh.getDhId());
        return q.getResultList();
    }

    @Override
    public List<HoTroKhachHang> getByEmailKH(String emailKH) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoTroKhachHang.findByEmail", HoTroKhachHang.class);
        q.setParameter("emailKhachHang", emailKH);
        return q.getResultList();
    }

    @Override
    public List<HoTroKhachHang> getByTrangThai(String status) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoTroKhachHang.findByEmail", HoTroKhachHang.class);
        q.setParameter("trangThaiHT", status);
        return q.getResultList();
    }

    @Override
    public HoTroKhachHang getByCreateDate(Date ngayTao) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoTroKhachHang.findByCreateDate", HoTroKhachHang.class);
        q.setParameter("ngayTao", ngayTao);
        return (HoTroKhachHang) q.getSingleResult();
    }

    @Override
    public List<HoTroKhachHang> getByDateRange(Date start, Date end) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("HoTroKhachHang.findByNgayYeuCauRange", HoTroKhachHang.class);
        q.setParameter("startDate", start);
        q.setParameter("endDate", end);
        return q.getResultList();
    }

}
