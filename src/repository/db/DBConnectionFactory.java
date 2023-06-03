/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 *
 * @author Admin
 */
public class DBConnectionFactory {
    private static Connection connection;
    private static DBConnectionFactory instance;

    public DBConnectionFactory() {
    }
    
    public static DBConnectionFactory getInstance(){
        if(instance == null){
            instance = new DBConnectionFactory();
        }
        return instance;
    }
    
    public static Connection getConnection() throws Exception{
        if(connection == null || connection.isClosed()){
            Properties properties = new Properties();
            properties.load(new FileInputStream("config/dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
            System.out.println("Uspesno uspostavljena konekcija");
        }
        return connection;
    }
    
}
