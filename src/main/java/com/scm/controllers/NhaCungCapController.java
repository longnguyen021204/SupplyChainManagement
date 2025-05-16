/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.services.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author LENOVO
 */
@Controller
public class NhaCungCapController {
    @Autowired
    private NhaCungCapService nccService;
    
    @GetMapping("/suppliers")
    public String suppliersView(Model model){
        model.addAttribute("supplier", this.nccService.getAllNCC());
        return "index";
    }
}
