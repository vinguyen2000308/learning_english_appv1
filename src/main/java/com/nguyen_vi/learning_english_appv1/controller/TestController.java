package com.nguyen_vi.learning_english_appv1.controller;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMethod("/hello-messages")
    public String getHelloMessage()
    {
        return "Hello World";
    }
}
