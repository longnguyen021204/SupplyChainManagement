/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.ChiTietDonHangXuat;
import com.scm.pojo.DonHang;
import com.scm.repositories.ChiTietDonHangXuatRepository;
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
public class ChiTietDonHangXuatRepositoriesImpl implements ChiTietDonHangXuatRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<ChiTietDonHangXuat> getDonHangXuat() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ChiTietDonHangXuat.findAll", ChiTietDonHangXuat.class);
        return q.getResultList();
    }

    @Override
    public ChiTietDonHangXuat createDonHangXuat(ChiTietDonHangXuat dhXuat) {
        Session s = this.factory.getObject().getCurrentSession();
        if (dhXuat.getCtdhxId() == null) {
            s.persist(dhXuat);
        } else {
            s.merge(dhXuat);
        }
        s.refresh(dhXuat);
        return dhXuat;
    }

    @Override
    public List<ChiTietDonHangXuat> getDonHangXuatById(int dhId) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("ChiTietDonHangXuat.findAll", ChiTietDonHangXuat.class);
        q.setParameter("dhId", dhId);
        return q.getResultList();
    }

}
