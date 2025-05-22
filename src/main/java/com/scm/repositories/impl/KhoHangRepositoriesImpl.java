/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.KhoHang;
import com.scm.pojo.NhaCungCap;
import com.scm.repositories.KhoHangRepository;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
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
    public List<KhoHang> getKhoHang(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
//        Query q = s.createNamedQuery("KhoHang.findAll", KhoHang.class);
//        return q.getResultList();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<KhoHang> q = b.createQuery(KhoHang.class);
        Root root = q.from(KhoHang.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("name");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("tenKho"), String.format("%%%s%%", kw)));
            }

            q.where(predicates.toArray(Predicate[]::new));

            String sort = params.get("sort");
            if (sort != null && !sort.isEmpty()) {
                q.orderBy(b.asc(root.get(sort)));
            }
        }
        Query query = s.createQuery(q);

        return query.getResultList();

    }

}
