package cz.cvut.fit.SemStew.backend;

import org.jooq.DSLContext;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class PostgreSQLConnection {
    /**
     *  database connection
     */
    private static Connection con;
    /**
     *  database context
     */
    protected static DSLContext ctx;

    /**
     * PostgreSQLConnection constructor
     *
     * Use {@link #PostgreSQLConnection()} to create PostgreSQLConnection class and connect to database
     */
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

    /**
     * Getter for connection
     *
     * Use {@link #getConnection()} to get Connection
     *
     * @return Connection of current PostgreSQLConnection class instance
     */
    public static Connection getConnection(){
        return con;
    }
}

