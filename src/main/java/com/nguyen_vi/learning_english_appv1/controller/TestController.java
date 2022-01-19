package com.nguyen_vi.learning_english_appv1.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/hello-messages")
    public String getHelloMessage()
    {
        return "Hello World";
    }
}
