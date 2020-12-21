package com.cloud.demo.usecase.hello;

import com.cloud.demo.dao.Pet;
import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.UroboroSQL;
import jp.co.future.uroborosql.config.SqlConfig;
import jp.co.future.uroborosql.filter.DebugSqlFilter;
import jp.co.future.uroborosql.filter.SqlFilterManagerImpl;
import jp.co.future.uroborosql.store.NioSqlManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HelloImpl implements Hello {
    private final SqlConfig sqlConfig;

    public HelloImpl(SqlConfig sqlConfig) {
        this.sqlConfig = sqlConfig;
    }

    @Override
    public String HelloAct(HelloDto param) {
        return "Hello Cloud ! [id=" + param.getId() + ", value=" + param.getValue() + "]";
    }

    @Override
    public List<Pet> HelloSql(HelloDto param) {
        SqlAgent agent = sqlConfig.agent();
        List<Pet> pets = agent.query("pets-find")
                .param("ownerId", param.getId())
                .collect(Pet.class);

//        Optional<Pet> petone = agent.query("pets-find")
//                .param("ownerId", param.getId())
//                .findOne(Pet.class);
//
//        System.out.println(petone.isEmpty());

        return pets;
    }
}
