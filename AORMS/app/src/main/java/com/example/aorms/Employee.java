package com.example.aorms;


public class Employee {
    String name;
    String password;
    String Designation;

    public Employee()
    {

    }

    public Employee(String name, String password, String designation) {
        this.name = name;
        this.password = password;
        Designation = designation;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }
}
