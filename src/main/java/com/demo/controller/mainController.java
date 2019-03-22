package com.demo.controller;

import org.flywaydb.core.Flyway;

public class mainController {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:mysql://127.0.0.1:3306/datascourse4";
        String username = "root";
        String password = "123456";
        Flyway flyway = Flyway.configure().dataSource(jdbcUrl, username, password).locations("db/migrations").baselineOnMigrate(true).load();
//                flyway.baseline();
        flyway.repair();
        flyway.migrate();
        flyway.info();
    }
}
