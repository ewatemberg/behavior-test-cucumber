package com.example.cucumber.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class EmilioController {

    @GetMapping("/hello")
    public String sayHello(HttpServletResponse response) {
        return "hello";
    }

    @PostMapping("/emilio")
    public String sayHelloPost(HttpServletResponse response) {
        return "hello";
    }
}