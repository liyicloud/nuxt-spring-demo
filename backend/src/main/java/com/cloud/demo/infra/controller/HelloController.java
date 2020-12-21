package com.cloud.demo.infra.controller;

import com.cloud.demo.dao.Pet;
import com.cloud.demo.usecase.hello.Hello;
import com.cloud.demo.usecase.hello.HelloDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class HelloController {

    private Hello hello;

    @RequestMapping(method = RequestMethod.GET)
    public String printParam(HelloDto param) {
        return hello.HelloAct(param);
    }

    @RequestMapping(value = "/sql", method = RequestMethod.GET)
    @ResponseBody
    public List<Pet> getSql(HelloDto param) {
        return hello.HelloSql(param);
    }

    @Autowired
    public void setHello(Hello hello) {
        this.hello = hello;
    }
}
