/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.DoiTacVanChuyen;
import com.scm.services.DoiTacVanChuyenService;
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
public class ApiDoiTacVanChuyenController {

    @Autowired
    private DoiTacVanChuyenService dtvcService;

    @GetMapping("/dtvc/{name}")
    public ResponseEntity<List<DoiTacVanChuyen>> list(@PathVariable(value = "name") String tenDoiTac) {
        return new ResponseEntity<>(this.dtvcService.getDoiTacByName(tenDoiTac), HttpStatus.OK);
    }

    @GetMapping("/dtvc")
    public ResponseEntity<List<DoiTacVanChuyen>> list() {
        return new ResponseEntity<>(this.dtvcService.getDoiTac(), HttpStatus.OK);
    }

    @PostMapping("/dtvc/new")
    public ResponseEntity<DoiTacVanChuyen> themDoiTac(@RequestBody DoiTacVanChuyen d) {
        try {
            DoiTacVanChuyen created = this.dtvcService.themDTVC(d);
            return new ResponseEntity<>(created, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
