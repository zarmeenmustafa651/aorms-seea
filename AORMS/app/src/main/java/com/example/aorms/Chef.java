package com.example.aorms;

public class Chef{
    String name;
    String specialty;
    public Chef(String n, String s){
        this.name = n;
        this.specialty=s;
    }

    public String getName() {
        return name;
    }


    public String getSpecialty() {
        return specialty;
    }
}