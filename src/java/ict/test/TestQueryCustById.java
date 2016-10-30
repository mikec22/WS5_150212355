/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.test;

import ict.db.CustomerDB;

/**
 *
 * @author Mike
 */
public class TestQueryCustById {

    public static void main(String[] arg) {
        String url = "jdbc:mysql://localhost:3306/ITP4912_DB";
        String username = "root";
        String password = "";
        CustomerDB custDb = new CustomerDB(url, username, password);
        System.out.print(custDb.queryCustByID("1") + "\n");
    }
}
