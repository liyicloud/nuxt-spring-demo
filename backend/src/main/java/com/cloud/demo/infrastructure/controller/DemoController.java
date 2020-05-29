package com.cloud.demo.infrastructure.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @RequestMapping("/test")
    public String HelloCloud() {
        return "Hello Cloud !";
    }
}
