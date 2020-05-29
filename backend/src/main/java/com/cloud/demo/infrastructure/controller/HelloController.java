package com.cloud.demo.infrastructure.controller;

import com.cloud.demo.usecase.hello.Hello;
import com.cloud.demo.usecase.hello.HelloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private Hello hello;

    @RequestMapping(method = RequestMethod.GET)
    public String getMethod(HelloDto param) {
        return hello.HelloAct(param);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postMethod1() {
        return "post";
    }

    @RequestMapping(value = "/hey", method = RequestMethod.POST)
    public String postMethod2() {
        return "hey post";
    }
}
