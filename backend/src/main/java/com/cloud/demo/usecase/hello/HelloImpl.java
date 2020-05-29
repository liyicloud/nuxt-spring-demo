package com.cloud.demo.usecase.hello;

import org.springframework.stereotype.Component;

@Component
public class HelloImpl implements Hello {
    @Override
    public String HelloAct(HelloDto param) {
        return "Hello Cloud ! [id=" + param.getId() + ", value=" + param.getValue() + "]";
    }
}
