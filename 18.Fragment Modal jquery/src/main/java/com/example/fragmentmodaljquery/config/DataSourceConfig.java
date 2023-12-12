package com.example.fragmentmodaljquery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring_data_demo_01");
        dataSource.setUsername("spring");
        dataSource.setPassword("spring");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        return dataSource;
    }

//    @Bean(name = "postgres")
//    public DataSource dataSource1() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl("jdbc:postgres://localhost:3306/spring_data_demo_01");
//        dataSource.setUsername("spring");
//        dataSource.setPassword("spring");
//        dataSource.setDriverClassName("com.postgres.jdbc.Driver");
//        return dataSource;
//    }

}
