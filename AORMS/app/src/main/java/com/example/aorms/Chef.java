package com.example.aorms;

import java.util.ArrayList;

public class Chef implements java.io.Serializable{
    int id;
    String name;
    String speciality;
    int threshold;
    int currentWorkload;

    ArrayList<ChefOrderQueue>chefOrderQueues = new ArrayList<>();

    public Chef() {}
    public Chef(String name, String speciality) {
        this.name = name;
        this.speciality = speciality;
    }
    public Chef(int id,String name, String speciality, int threshold, int currentWorkload) {
        this.id=id;
        this.name = name;
        this.speciality = speciality;
        this.threshold = threshold;
        this.currentWorkload = currentWorkload;
    }
    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return speciality;
    }

    public void setSpecialty(String specialty) {
        this.speciality = specialty;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public int getCurrentWorkload() {
        return currentWorkload;
    }

    public void setCurrentWorkload(int currentWorkload) {
        this.currentWorkload = currentWorkload;
    }

    public ArrayList<ChefOrderQueue> getChefOrderQueues() { return chefOrderQueues; }

    public void setChefOrderQueues(ArrayList<ChefOrderQueue> chefOrderQueues) { this.chefOrderQueues = chefOrderQueues; }
}