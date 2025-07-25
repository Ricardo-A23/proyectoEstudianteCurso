package com.proyecto.estudiante.curso.ejemplo.util;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionBd {


    private static String url = "jdbc:mysql://localhost:3306/cursojava";
    private static String user = "root";
    private static String password = "1234";
    private static BasicDataSource pool; //crear un pool de conexiones a la base de datos

    public static BasicDataSource getInstance() throws SQLException {
        if(pool == null) {
            pool = new BasicDataSource();
            pool.setUrl(url);
            pool.setUsername(user);
            pool.setPassword(password);
            pool.setInitialSize(3); //hilos o conexion al principio
            pool.setMinIdle(5); //indicamos cual será el minimo de conexiones inactivas
            pool.setMaxIdle(8); //indicamos cual será el max de conexiones inactivas
            pool.setMaxTotal(8); //el maximo de conexiones ya sean inactivas o activas
        }
        return pool;
    }


    //para devolver una conexion del pool
    public static Connection getConnection() throws SQLException {
        return getInstance().getConnection(); //conexion a la base de datos desde el pool
    }
}
