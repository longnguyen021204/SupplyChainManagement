/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.NhaCungCap;
import com.scm.services.NhaCungCapService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LENOVO
 */
@RestController
@RequestMapping("/api")
public class ApiNhaCungCapController {

    @Autowired
    private NhaCungCapService nccService;

    @GetMapping("/suppliers")
    public ResponseEntity<List<NhaCungCap>> list() {
        return new ResponseEntity<>(this.nccService.getAllNCC(), HttpStatus.OK);
    }

    @GetMapping("/suppliers/{supplierId}")
    public ResponseEntity<NhaCungCap> retrieve(@PathVariable(value = "supplierId") int id) {
        return new ResponseEntity<>(this.nccService.getNCCById(id), HttpStatus.OK);
    }

    @DeleteMapping("/suppliers/{supplierId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable(value = "supplierId") int id) {
        this.nccService.deleteNCC(id);
    }

    @PostMapping("/suppliers/add")
    public ResponseEntity<NhaCungCap> addSupplier(@RequestBody NhaCungCap ncc) {
        try {
            if (ncc.getNccId() == null) {
                NhaCungCap created = this.nccService.addOrUpdateNCC(ncc);
                return new ResponseEntity<>(created, HttpStatus.CREATED);
            }
            else 
                return new ResponseEntity<>(this.nccService.addOrUpdateNCC(ncc), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
