/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.scm.services;

import com.scm.pojo.TaiKhoan;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public interface UserService {
    List<TaiKhoan> getAllUser();
    List<TaiKhoan> getUserByRole(String role);
    TaiKhoan getUserByUsername(String username);
    TaiKhoan getUserByEmail(String email);
    TaiKhoan addAccount(TaiKhoan t);
    boolean authenticate(String username, String pwd);
}
