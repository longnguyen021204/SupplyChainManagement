/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.KhoHang;
import com.scm.services.KhoHangService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api")
public class ApiKhoHangController {
    @Autowired
    private KhoHangService khoService;
    
    @GetMapping("/warehouse/")
    public ResponseEntity<List<KhoHang>> list(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.khoService.getKhoHang(params), HttpStatus.OK);
    }
}
