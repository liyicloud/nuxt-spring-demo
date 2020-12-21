package com.cloud.demo.config;

import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.UroboroSQL;
import jp.co.future.uroborosql.config.SqlConfig;
import jp.co.future.uroborosql.filter.DebugSqlFilter;
import jp.co.future.uroborosql.filter.DumpResultSqlFilter;
import jp.co.future.uroborosql.filter.SqlFilterManagerImpl;
import jp.co.future.uroborosql.store.NioSqlManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Spring configuration
 *
 * @author LiYi
 */
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    private static final Logger LOG = LoggerFactory.getLogger(AppConfig.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${testdata.dbautoinit}")
    private Boolean dbAutoInit;

    @Bean
    public DataSource dataSource() {
        com.zaxxer.hikari.HikariDataSource ds = new com.zaxxer.hikari.HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        // ds.setDriverClassName(driverClassName);
        return ds;
    }

    @Bean
    public SqlConfig sqlConfig(DataSource dataSource) {
        SqlConfig sqlConfig = UroboroSQL.builder(dataSource)
                .setSqlManager(new NioSqlManagerImpl(false))
                //.setSqlFilterManager(new SqlFilterManagerImpl().addSqlFilter(new DumpResultSqlFilter()))
                .setSqlFilterManager(new SqlFilterManagerImpl().addSqlFilter(new DebugSqlFilter()))
                .build();
        return sqlConfig;
    }

//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
//        bean.setOrder(0);
//        return bean;
//    }
//
//    @Bean
//    public String initDatabase(DataSource dataSource) throws SQLException {
//        if (dbAutoInit) {
//            SqlConfig config = sqlConfig(dataSource);
//            try (SqlAgent agent = config.agent()) {
//                agent.required(() -> {
//                    agent.update("setup/schema").count();
//                    agent.update("setup/data").count();
//                });
//            }
//            LOG.info("Complete database initialization.");
//        } else {
//            LOG.info("Skip database initialization.");
//        }
//        return null;
//    }
}
