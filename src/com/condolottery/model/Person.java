package com.condolottery.model;


public class Person {

    
    private String id;
    private String fullName;
    private String phone;

    
    public Person(String id, String fullName, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    

    
    public String displayInfo() {
        return "ID: " + id + "\nName: " + fullName + "\nPhone: " + phone;
    }
}
