package com.saransh.springpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by CryptoSingh1337 on 6/10/2021
 */
@Controller
public class IndexController {

    @GetMapping({"", "/", "index", "index.html"})
    public String indexPage() {
        return "index";
    }

    @GetMapping("/oups")
    public String oupsHandler() {
        return "notimplemented";
    }
}
