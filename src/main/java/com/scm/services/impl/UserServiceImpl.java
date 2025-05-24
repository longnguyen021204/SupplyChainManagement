/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.services.impl;

import com.scm.pojo.TaiKhoan;
import com.scm.repositories.UserRepository;
import com.scm.services.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author LENOVO
 */
@Service("userDetailService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository uRepo;
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<TaiKhoan> getAllUser() {
        return this.uRepo.getAllUser();
    }

    @Override
    public List<TaiKhoan> getUserByRole(String role) {
        return this.uRepo.getAllUser();
    }

    @Override
    public TaiKhoan getUserByUsername(String username) {
        return this.uRepo.getUserByUsername(username);
    }

    @Override
    public TaiKhoan getUserByEmail(String email) {
        return this.uRepo.getUserByEmail(email);
    }

    @Override
    public TaiKhoan addAccount(TaiKhoan t) {
        TaiKhoan tk = new TaiKhoan();
        tk.setHoTen(t.getHoTen());
        tk.setTenDangNhap(t.getTenDangNhap());
        tk.setEmail(t.getEmail());
        tk.setMatKhau(this.passEncoder.encode(t.getMatKhau()));
        tk.setSoDienThoai(t.getSoDienThoai());
        tk.setVaiTro(t.getVaiTro());

        return this.uRepo.addAccount(tk);
    }

    @Override
    public boolean authenticate(String username, String pwd) {
        return this.uRepo.authenticate(username, pwd);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        TaiKhoan tk = this.uRepo.getUserByUsername(username);

        if (tk == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(tk.getVaiTro()));

        return new org.springframework.security.core.userdetails.User(tk.getTenDangNhap(), tk.getMatKhau(), authorities);
    }

}
