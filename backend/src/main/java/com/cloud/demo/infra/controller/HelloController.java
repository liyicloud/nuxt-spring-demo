package com.cloud.demo.infra.controller;

import com.cloud.demo.usecase.hello.Hello;
import com.cloud.demo.usecase.hello.HelloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private Hello hello;

    @RequestMapping(method = RequestMethod.GET)
    public String getMethod(HelloDto param) {
        return hello.HelloAct(param);
    }

    @RequestMapping(value = "/sql", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getSql(HelloDto param) {
        return hello.HelloSql(param);
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
