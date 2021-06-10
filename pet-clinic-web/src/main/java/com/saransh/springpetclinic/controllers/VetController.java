package com.saransh.springpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
@Controller
@RequestMapping("/vets")
public class VetController {

    @GetMapping({"", "/", "/index", "/index.html"})
    public String listVets() {
        return "vets/index";
    }
}
