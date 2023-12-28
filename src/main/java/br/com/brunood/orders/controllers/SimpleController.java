package br.com.brunood.orders.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class SimpleController {

    @GetMapping("/hello")
    public String helloSpring() {
        return "Hello world";
    }
}
