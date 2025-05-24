/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.ChiTietDonHangNhap;
import com.scm.pojo.DonHang;
import com.scm.pojo.NhaCungCap;
import com.scm.services.ChiTietDonHangNhapService;
import jakarta.ws.rs.Path;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api")
public class ApiDonHangNhapController {
    @Autowired
    private ChiTietDonHangNhapService dhnService;
    
    @GetMapping("/nhapkho")
    public ResponseEntity<List<ChiTietDonHangNhap>> getDonNhapKho() {
        return new ResponseEntity<>(this.dhnService.getDonHangNhap(), HttpStatus.OK);
    }
    @GetMapping("/nhapkho/{dhId}")
    public ResponseEntity<List<ChiTietDonHangNhap>> getDonNhapKhoById(@PathVariable(value = "dhId") int id) {
        return new ResponseEntity<>(this.dhnService.getDonHangNhapById(id), HttpStatus.OK);
    }
    
    @PostMapping("/nhap-hang/new")
    public ResponseEntity<ChiTietDonHangNhap> nhapHang(@RequestBody ChiTietDonHangNhap dhn){
        try {
            return new ResponseEntity<>(this.dhnService.createDonHangNhap(dhn), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(dhn, HttpStatus.BAD_REQUEST);
        }
    }
    
}
