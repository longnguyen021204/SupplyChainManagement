/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.pojo.NhaCungCap;
import com.scm.services.NhaCungCapService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LENOVO
 */
@Controller
public class NhaCungCapController {

    @Autowired
    private NhaCungCapService nccService;

//    @GetMapping("/suppliers")
//    public String suppliersView(Model model, @RequestParam Map<String, String> params) {
//        model.addAttribute("supplier", this.nccService.getSuppliers(params));
//        return "index";
//    }
//
//    @GetMapping("/suppliers/{supplierId}")
//    public String supplierView(Model model, @PathVariable(value = "supplierId") int id) {
//        model.addAttribute("supplier", this.nccService.getNCCById(id));
//        return "index";
//    }
//    
//    @PostMapping("/suppliers/add")
//    public String addSupplier(@ModelAttribute(value = "supplier") NhaCungCap ncc){
////        this.nccService.addOrUpdateNCC(ncc);
//        return "redirect:/";
//    }
}
