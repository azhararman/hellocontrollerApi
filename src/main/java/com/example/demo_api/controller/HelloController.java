package com.example.demo_api.controller;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Jenkins + Docker + Git Demo!+ WELCOME AZHAR YOU DID GREAT JOB";
    }
}
