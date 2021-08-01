package com.creditsuisse.project.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.creditsuisse.project.common.constants.AppConstants.CREATE_TABLE_STATEMENT;

@Configuration
@ComponentScan(basePackages = {"com.creditsuisse"})
@Slf4j
public class AppDatabaseConfiguration {

    private static final String CONNECTION_URL = "jdbc:hsqldb:file:eventdb;ifexists=false";
    private static final String USER_NAME = "user";
    private static final String PASSWORD = "";
    private static final String HSQL_DRIVER_CONSTANTS = "org.hsqldb.jdbc.JDBCDriver";

    @Bean
    public ObjectMapper objectMapper() {
         ObjectMapper objectMapper = new ObjectMapper();
         objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
         return objectMapper;
    }

    @Bean
    public Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName(HSQL_DRIVER_CONSTANTS);
        Connection connection = DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
        connection.createStatement().execute(CREATE_TABLE_STATEMENT);
        return connection;
    }
}
