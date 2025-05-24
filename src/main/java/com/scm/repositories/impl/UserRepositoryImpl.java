/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.repositories.impl;

import com.scm.pojo.TaiKhoan;
import com.scm.repositories.UserRepository;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<TaiKhoan> getUserByRole(String role) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("TaiKhoan.findByVaiTro", TaiKhoan.class);
        q.setParameter("vaiTro", role);
        return q.getResultList();
    }

    @Override
    public TaiKhoan getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("TaiKhoan.findByTenDangNhap", TaiKhoan.class);
        q.setParameter("tenDangNhap", username);
        return (TaiKhoan) q.getSingleResult();
    }

    @Override
    public TaiKhoan addAccount(TaiKhoan a) {
        Session s = this.factory.getObject().getCurrentSession();
        s.persist(a);
        s.refresh(a);
        return a;
    }

    @Override
    public boolean authenticate(String username, String pwd) {
        TaiKhoan a = this.getUserByUsername(username);

        return this.passwordEncoder.matches(pwd, a.getMatKhau());
    }

    @Override
    public List<TaiKhoan> getAllUser() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("TaiKhoan.findAll", TaiKhoan.class);
        return q.getResultList();
    }

    @Override
    public TaiKhoan getUserByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("TaiKhoan.findByEmail", TaiKhoan.class);
        q.setParameter("email", email);
        return (TaiKhoan) q.getSingleResult();
    }

}
