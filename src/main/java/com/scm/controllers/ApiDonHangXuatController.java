/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.ChiTietDonHangNhap;
import com.scm.pojo.ChiTietDonHangXuat;
import com.scm.services.ChiTietDonHangXuatService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ApiDonHangXuatController {
    @Autowired
    private ChiTietDonHangXuatService dhxService;
    
    @GetMapping("/xuatkho")
    public ResponseEntity<List<ChiTietDonHangXuat>> getDonXuatKho() {
        return new ResponseEntity<>(this.dhxService.getDonHangXuat(), HttpStatus.OK);
    }
    
    @PostMapping("/xuat-hang/new")
    public ResponseEntity<ChiTietDonHangXuat> xuatHang(@RequestBody ChiTietDonHangXuat dhx){
        try {
            return new ResponseEntity<>(this.dhxService.createDonHangXuat(dhx), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
