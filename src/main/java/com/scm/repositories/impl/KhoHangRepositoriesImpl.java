/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.KhoHang;
import com.scm.repositories.KhoHangRepository;
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
public class KhoHangRepositoriesImpl implements KhoHangRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public List<KhoHang> getKhoHangByName(String tenKho) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("KhoHang.findByTen", KhoHang.class);
        q.setParameter("tenKho", tenKho);
        return q.getResultList();
    }

    @Override
    public List<KhoHang> getKhoHangByDiaChi(String diaChiKho) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("KhoHang.findByDiaChi", KhoHang.class);
        q.setParameter("diaChi", diaChiKho);
        return q.getResultList();
    }

    @Override
    public List<KhoHang> getKhoHang() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("KhoHang.findAll", KhoHang.class);
        return q.getResultList();
    }
    
}
