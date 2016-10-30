/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.Bean;

/**
 *
 * @author a1
 */
public class CustomerBean {

    private String custId, name, tel;
    private int age;

    public CustomerBean() {

    }

    public CustomerBean(String custId, String name, String tel, int age) {
        this.custId = custId;
        this.name = name;
        this.tel = tel;
        this.age = age;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Id: " + custId 
                + "\nName: " + name 
                + "\nTel: " + tel 
                + "\nAge: " + age 
                + "\n";
    }

}
