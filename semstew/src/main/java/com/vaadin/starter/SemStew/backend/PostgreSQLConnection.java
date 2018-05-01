package com.vaadin.starter.SemStew.backend;

import JOOQ.tables.AllergensName;
import JOOQ.tables.records.AboutUsConfigRecord;
import com.vaadin.starter.SemStew.backend.Services.AboutUsConfigService;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class PostgreSQLConnection {
    private static Connection con;
    protected static DSLContext ctx;

    public PostgreSQLConnection(){
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            System.out.println("Dependency on JDBC PostgreSQL driver was not found with an exception: "
                    + e.toString());
            return;
        }

        System.out.println("JDBC driver was succesfully found...");

        try{
            con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/SemStew",
                    "postgres", "postolka11");
        } catch (SQLException e){
            System.out.println("Couln't connect to database due to an exception: "
                    + e.toString());
            return;
        }
    }

    public static Connection getConnection(){
        return con;
    }
}

