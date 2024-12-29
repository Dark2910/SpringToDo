/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EEspindola.ToDo_WebService.Configuration;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author Eduardo
 */
@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setUrl("jdbc:oracle:thin:@tcp://localhost:1522/FREE");
        dataSource.setUsername("EEspindolaToDo");
        dataSource.setPassword("password1");
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");

        return dataSource;
    }
}
