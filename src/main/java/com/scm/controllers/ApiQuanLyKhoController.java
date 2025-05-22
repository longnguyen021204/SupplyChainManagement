/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.QuanLyKho;
import com.scm.services.QuanLyKhoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    
}
