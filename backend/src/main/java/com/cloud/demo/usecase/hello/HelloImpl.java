package com.cloud.demo.usecase.hello;

import com.cloud.demo.dao.Pet;
import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.UroboroSQL;
import jp.co.future.uroborosql.config.SqlConfig;
import jp.co.future.uroborosql.filter.DebugSqlFilter;
import jp.co.future.uroborosql.filter.SqlFilterManagerImpl;
import jp.co.future.uroborosql.store.NioSqlManagerImpl;
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
    public List<Pet> HelloSql(HelloDto param) {
        SqlAgent agent = getSqlConfig().agent();
        List<Pet> pets = agent.query("pets-find")
                .param("ownerId", param.getId())
                .collect(Pet.class);
        return pets;
    }

    /**
     * UroboroSQLのサンプル
     * @return
     */
    private SqlConfig getSqlConfig() {
        SqlConfig sqlConfig = UroboroSQL.builder(jdbcTemplate.getDataSource())
                .setSqlManager(new NioSqlManagerImpl(false))
                //.setSqlFilterManager(new SqlFilterManagerImpl().addSqlFilter(new DumpResultSqlFilter()))
                .setSqlFilterManager(new SqlFilterManagerImpl().addSqlFilter(new DebugSqlFilter()))
                .build();
        return sqlConfig;
    }
}
