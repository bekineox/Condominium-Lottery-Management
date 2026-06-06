package com.condolottery.model;

/**
 * Represents an administrator who manages the lottery system.
 */
public class Admin extends Person {

    // Private fields — encapsulation
    private String role;
    private String department;

    /**
     * Constructs an Admin with all required details.
     * @param id unique admin ID
     * @param fullName full name
     * @param phone phone number
     * @param role admin role (e.g., "Manager", "Coordinator")
     * @param department department name
     */
    public Admin(String id, String fullName, String phone,
                 String role, String department) {
        super(null, null, null); // Call to parent constructor is required, we use nulls initially
        
        // Use inherited setters
        setId(id);
        setFullName(fullName);
        setPhone(phone);
        
        this.role = role;
        this.department = department;
    }

    // ==================== Getters and Setters ====================

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

    // ==================== Polymorphism — Method Overriding ====================

    /**
     * Displays admin-specific information.
     * Overrides the method in Person — demonstrates polymorphism and dynamic binding.
     * @return formatted admin details
     */
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

    // Helper method for padding string
    private String padRight(String s, int n) {
        String result = s;
        while (result.length() < n) {
            result += " ";
        }
        return result;
    }
}
