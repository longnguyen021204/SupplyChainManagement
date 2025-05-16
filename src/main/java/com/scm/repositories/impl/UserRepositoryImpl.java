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
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<TaiKhoan> getUserByRole(String role) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        return a;
    }

    @Override
    public boolean authenticate(String username, String pwd) {
        TaiKhoan a = this.getUserByUsername(username);

//        return this.passwordEncoder.matches(pwd, a.getMatKhau());
        return false;
    }

    @Override
    public List<TaiKhoan> getAllUser() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public TaiKhoan getUserByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
