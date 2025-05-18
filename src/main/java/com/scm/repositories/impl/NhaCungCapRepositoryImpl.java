/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.NhaCungCap;
import com.scm.repositories.NhaCungCapRepository;
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
public class NhaCungCapRepositoryImpl implements NhaCungCapRepository{

    private static final int PAGE_SIZE = 6;
    @Autowired
    private LocalSessionFactoryBean factory;
    @Override
    public NhaCungCap getNCCById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(NhaCungCap.class, id);
    }

    @Override
    public NhaCungCap addOrUpdateNCC(NhaCungCap s) {
        Session a = this.factory.getObject().getCurrentSession();
        if (s.getNccId() == null) {
            a.persist(s);
        } else {
            a.merge(s);
        }
        return s;
    }

    @Override
    public void deleteNCC(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        NhaCungCap ncc = this.getNCCById(id);
        s.remove(ncc);
    }

    @Override
    public NhaCungCap getNCCByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("NhaCungCap.findByEmail", NhaCungCap.class);
        q.setParameter("email", email);
        return (NhaCungCap) q.getSingleResult();
    }

    @Override
    public List<NhaCungCap> sortNCCByDiemDanhGiaDesc() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("NhaCungCap.sortByDiemDanhGiaDesc", NhaCungCap.class);
        return q.getResultList();
    }

    @Override
    public List<NhaCungCap> getAllNCC() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM NhaCungCap", NhaCungCap.class);
        return q.getResultList();
    }

    @Override
    public NhaCungCap getNCCByName(String tenNCC) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("NhaCungCap.findByTenNCC", NhaCungCap.class);
        q.setParameter("tenNCC", tenNCC);
        return (NhaCungCap) q.getSingleResult();
    }

    @Override
    public List<NhaCungCap> getSuppliers(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<NhaCungCap> q = b.createQuery(NhaCungCap.class);
        Root root = q.from(NhaCungCap.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("name");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("tenNCC"), String.format("%%%s%%", kw)));
            }

//            String fromPrice = params.get("fromPrice");
//            if (fromPrice != null && !fromPrice.isEmpty()) {
//                predicates.add(b.greaterThanOrEqualTo(root.get("price"), fromPrice));
//            }
//
//            String toPrice = params.get("toPrice");
//            if (toPrice != null && !toPrice.isEmpty()) {
//                predicates.add(b.lessThanOrEqualTo(root.get("price"), toPrice));
//            }

//            String c = params.get("category");
//            if (c != null && !c.isEmpty()) {
//                predicates.add(b.equal(root.get("categoryId").as(Integer.class), cateId));
//            }

            q.where(predicates.toArray(Predicate[]::new));

            String sort = params.get("sort");
            if (sort != null && !sort.isEmpty()) {
                q.orderBy(b.asc(root.get(sort)));
            }
        }

        Query query = s.createQuery(q);

        if (params != null && params.containsKey("page")) {
            int page = Integer.parseInt(params.get("page"));
            int start = (page - 1) * PAGE_SIZE;
            query.setMaxResults(PAGE_SIZE);
            query.setFirstResult(start);
        }

        return query.getResultList();
    }


    
}
