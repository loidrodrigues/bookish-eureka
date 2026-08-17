package br.com.loidpadre.segundo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "Ola, o servidor esta no ar";
    }

}
