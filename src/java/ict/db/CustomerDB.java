/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.db;

import ict.Bean.CustomerBean;
import java.sql.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

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
            String sql = "CREATE TABLE IF NOT EXISTS customer ("
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
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean addRecord(String custId, String name, String tel, int age) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "INSERT INTO CUSTOMER VALUES (?,?,?,?)";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, custId);
            pStmnt.setString(2, name);
            pStmnt.setString(3, tel);
            pStmnt.setInt(4, age);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount >= 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }

    public CustomerBean queryCustByID(String id) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        CustomerBean cb = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM CUSTOMER WHERE CUSTID = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, id);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.next()) {
                cb = new CustomerBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return cb;
    }

    public ArrayList<CustomerBean> queryCustByName(String name) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<CustomerBean> aLCB = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM CUSTOMER WHERE name = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, name);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.first()) {
                aLCB = new ArrayList();
                aLCB.add(new CustomerBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                while (rs.next()) {
                    aLCB.add(new CustomerBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                }
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return aLCB;
    }
    
    public ArrayList<CustomerBean> queryCustByTel(String tel) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<CustomerBean> aLCB = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM CUSTOMER WHERE tel = ?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, tel);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.first()) {
                aLCB = new ArrayList();
                aLCB.add(new CustomerBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                while (rs.next()) {
                    aLCB.add(new CustomerBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                }
            }

            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return aLCB;
    }
    
    public ArrayList<CustomerBean> queryCust() {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        ArrayList<CustomerBean> aLCB = null;
        try {
            cnnct = getConnection();
            String preQueryStatement = "SELECT * FROM CUSTOMER";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            ResultSet rs = null;
            rs = pStmnt.executeQuery();
            if (rs.first()) {
                aLCB = new ArrayList();
                aLCB.add(new CustomerBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                while (rs.next()) {
                    aLCB.add(new CustomerBean(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
                }
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return aLCB;
    }
    
    public boolean delRecord(String custId) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "DELETE FROM CUSTOMER "
                    + "WHERE custId=?";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, custId);
            int rowCount = pStmnt.executeUpdate();
            if (rowCount == 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public boolean editRecord(CustomerBean cb) {
        Connection cnnct = null;
        PreparedStatement pStmnt = null;
        boolean isSuccess = false;
        try {
            cnnct = getConnection();
            String preQueryStatement = "UPDATE customer SET name = ?, "
                    + "tel = ?, "
                    + "age = ? "
                    + "WHERE custID = ?;";
            pStmnt = cnnct.prepareStatement(preQueryStatement);
            pStmnt.setString(1, cb.getName());
            pStmnt.setString(2, cb.getTel());
            pStmnt.setInt(3, cb.getAge());
            pStmnt.setString(4, cb.getCustId());
            int rowCount = pStmnt.executeUpdate();
            if (rowCount == 1) {
                isSuccess = true;
            }
            pStmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return isSuccess;
    }
    
    public void dropCustTable() {
        Connection cnnct = null;
        Statement stmnt = null;
        try {
            cnnct = getConnection();
            stmnt = cnnct.createStatement();
            String sql = "DROP TABLE IF EXISTS customer";
            stmnt.execute(sql);
            stmnt.close();
            cnnct.close();
        } catch (SQLException ex) {
            while (ex != null) {
                ex.printStackTrace();
                ex = ex.getNextException();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
