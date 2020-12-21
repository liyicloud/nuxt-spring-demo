package com.cloud.demo.infra.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    private static final Logger logger = LoggerFactory.getLogger(DemoController.class);

    @RequestMapping("/demo")
    public String HelloCloud() {
        logger.info("DemoController#HelloCloud");
        return "Hello Cloud !";
    }

    @RequestMapping(value = "/hey", method = RequestMethod.POST)
    public String postMethod() {
        return "hey post";
    }
}
