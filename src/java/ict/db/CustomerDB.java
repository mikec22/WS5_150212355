/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import java.sql.Statement;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mike
 */
public class CustomerDB implements Serializable {

    private String url, username, password;

    public CustomerDB() {
        this.url = "";
        this.username = "";
        this.password = "";
    }

    public CustomerDB(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Connection getConnection() throws SQLException, IOException {
        System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, username, password);
    }

    public void createCustTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql = "CREATE TABLe IF NOT EXISTS customer ("
                    + "custID varchar(5) NOT NULL,"
                    + "name varchar(25) NOT NULL,"
                    + "tel varchar(10) NOT NULL,"
                    + "age int(11) NOT NULL,"
                    + "PRIMARY KEY(custId)"
                    + ")";
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {

        } catch (IOException ex){
            
        }
    }
}
