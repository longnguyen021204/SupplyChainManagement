/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.NhaCungCap;
import com.scm.pojo.QuanLyKho;
import com.scm.services.QuanLyKhoService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api")
public class ApiQuanLyKhoController {

    @Autowired
    private QuanLyKhoService qlkService;

    @GetMapping("/quanlykho")
    public ResponseEntity<List<QuanLyKho>> list() {
        return new ResponseEntity<>(this.qlkService.getAllSanPham(), HttpStatus.OK);
    }
    @GetMapping("/quanlykho/het-hang")
    public ResponseEntity<List<QuanLyKho>> SanPhamHetHang() {
        return new ResponseEntity<>(this.qlkService.getSanPhamHetHang(), HttpStatus.OK);
    }

    @GetMapping("/quanlykho/{maSP}")
    public ResponseEntity<List<QuanLyKho>> sanPhamView(@PathVariable(value = "maSP") String maSP) {
        return new ResponseEntity<>(this.qlkService.getSanPhamByMaSP(maSP), HttpStatus.OK);
    }

    @PostMapping("/quanlykho/add")
    public ResponseEntity<QuanLyKho> addOrUpdateProduct(@RequestBody QuanLyKho sp) {
        try {
            QuanLyKho addSp = this.qlkService.addOrUpdateSanPham(sp);
            return new ResponseEntity<>(addSp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
}
