/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.DonHang;
import com.scm.services.DonHangService;
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
public class ApiDonHangController {
    @Autowired
    private DonHangService dhService;
    
    @GetMapping("/donhang")
    public ResponseEntity<List<DonHang>> donHang() {
        return new ResponseEntity<>(this.dhService.getDonHang(), HttpStatus.OK);
    }
    @GetMapping("/donhang/donhang-xuat/{khoId}")
    public ResponseEntity<List<DonHang>> getDonHangXuatKhoId(@PathVariable(value = "khoId") int khoId) {
        return new ResponseEntity<>(this.dhService.getDonHangXuat(khoId), HttpStatus.OK);
    }
    @GetMapping("/donhang/donhang-nhap/{khoId}")
    public ResponseEntity<List<DonHang>> getDonHangNhapKhoId(@PathVariable(value = "khoId") int khoId) {
        return new ResponseEntity<>(this.dhService.getDonHangNhap(khoId), HttpStatus.OK);
    }
    
    @PostMapping("/donhang/new")
    public ResponseEntity<DonHang> themDonHang(@RequestBody DonHang dh){
        return new ResponseEntity<>(this.dhService.createDonHang(dh), HttpStatus.CREATED);
    }
    
    
}
