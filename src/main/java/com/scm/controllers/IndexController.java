/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.scm.controllers;

import com.scm.services.NhaCungCapService;
import com.scm.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author LENOVO
 */
@Controller
public class IndexController {
    @Autowired
    private NhaCungCapService nccService;
    private UserService uService;
    
    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params){
//        model.addAttribute("supplier", this.nccService.getSuppliers(params));
        
        return "index";
    }
}
