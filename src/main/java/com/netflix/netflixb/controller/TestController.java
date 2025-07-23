package com.netflix.netflixb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test123")
    public String test() {
        return "Test çalışıyor!";
    }
}
