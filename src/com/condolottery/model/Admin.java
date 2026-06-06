package com.condolottery.model;


public class Admin extends Person {

    
    private String role;
    private String department;

   
    public Admin(String id, String fullName, String phone,
                 String role, String department) {
        super(null, null, null); 
        setId(id);
        setFullName(fullName);
        setPhone(phone);
        
        this.role = role;
        this.department = department;
    }

   

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    
    public String displayInfo() {
        return "╔══════════════════════════════════════════╗\n" +
               "║             ADMIN DETAILS                ║\n" +
               "╠══════════════════════════════════════════╣\n" +
               "║ ID:         " + padRight(getId(), 29) + "║\n" +
               "║ Name:       " + padRight(getFullName(), 29) + "║\n" +
               "║ Phone:      " + padRight(getPhone(), 29) + "║\n" +
               "║ Role:       " + padRight(role, 29) + "║\n" +
               "║ Department: " + padRight(department, 29) + "║\n" +
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
