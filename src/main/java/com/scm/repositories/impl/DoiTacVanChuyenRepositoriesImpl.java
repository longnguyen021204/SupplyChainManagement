/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.DoiTacVanChuyen;
import com.scm.repositories.DoiTacVanChuyenRepository;
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
public class DoiTacVanChuyenRepositoriesImpl implements DoiTacVanChuyenRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<DoiTacVanChuyen> getDoiTacByName(String tenDoiTac) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DoiTacVanChuyen.findByTenDoiTac", DoiTacVanChuyen.class);
        q.setParameter("tenDoiTac", tenDoiTac);
        return q.getResultList();
    }

    @Override
    public List<DoiTacVanChuyen> getDoiTacByDiemDGDesc() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DoiTacVanChuyen.findTopNByDiemDanhGia", DoiTacVanChuyen.class);
        return q.getResultList();
    }

    @Override
    public DoiTacVanChuyen themDTVC(DoiTacVanChuyen d) {
        Session s = this.factory.getObject().getCurrentSession();
        if (d.getDtvcId() == null) {
            s.persist(d);
        } else {
            s.merge(d);
        }
        s.refresh(d);
        return d;
    }

    @Override
    public List<DoiTacVanChuyen> getDoiTac() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("DoiTacVanChuyen.findAll", DoiTacVanChuyen.class);
        return q.getResultList();
    }

}
