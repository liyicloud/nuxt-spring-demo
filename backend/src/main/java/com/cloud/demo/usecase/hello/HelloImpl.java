package com.cloud.demo.usecase.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HelloImpl implements Hello {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String HelloAct(HelloDto param) {
        return "Hello Cloud ! [id=" + param.getId() + ", value=" + param.getValue() + "]";
    }

    @Override
    public List<Map<String,Object>> HelloSql(HelloDto param) {
        List<Map<String,Object>> pets = jdbcTemplate.queryForList("select * from pets");
        return pets;
    }
}
