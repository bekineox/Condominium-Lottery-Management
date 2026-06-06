package com.condolottery.model;


public class Applicant extends Person {

    
    private String email;
    private String address;
    private String registrationDate;

   
    public Applicant(String id, String fullName, String phone,
                     String email, String address, String registrationDate) {
        super(null, null, null); 
        
        
        setId(id);
        setFullName(fullName);
        setPhone(phone);
        
        this.email = email;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    
    public String displayInfo() {
        return "╔══════════════════════════════════════════╗\n" +
               "║           APPLICANT DETAILS              ║\n" +
               "╠══════════════════════════════════════════╣\n" +
               "║ ID:                " + padRight(getId(), 22) + "║\n" +
               "║ Name:              " + padRight(getFullName(), 22) + "║\n" +
               "║ Phone:             " + padRight(getPhone(), 22) + "║\n" +
               "║ Email:             " + padRight(email, 22) + "║\n" +
               "║ Address:           " + padRight(address, 22) + "║\n" +
               "║ Registration Date: " + padRight(registrationDate, 22) + "║\n" +
               "╚══════════════════════════════════════════╝";
    }

    
    private String padRight(String s, int n) {
        String result = s;
        while (result.length() < n) {
            result += " ";
        }
        return result;
    }
}
