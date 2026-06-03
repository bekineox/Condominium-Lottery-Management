package com.condolottery.model;

/**
 * Represents an administrator who manages the lottery system.
 * Demonstrates: inheritance (extends Person), polymorphism (overrides displayInfo()),
 * constructor chaining (super()), super keyword for method calls,
 * and encapsulation (private fields with getters/setters).
 */
public class Admin extends Person {

    // Private fields — encapsulation
    private String role;
    private String department;

    /**
     * Constructs an Admin with all required details.
     * Demonstrates: constructor chaining using super() to call parent constructor.
     * @param id unique admin ID
     * @param fullName full name
     * @param phone phone number
     * @param role admin role (e.g., "Manager", "Coordinator")
     * @param department department name
     */
    public Admin(String id, String fullName, String phone,
                 String role, String department) {
        super(id, fullName, phone); // Constructor chaining — calls Person constructor
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
     * Overrides the abstract method in Person — demonstrates polymorphism and dynamic binding.
     * @return formatted admin details
     */
    public String displayInfo() {
        return String.format(
            "╔══════════════════════════════════════════╗%n" +
            "║             ADMIN DETAILS                ║%n" +
            "╠══════════════════════════════════════════╣%n" +
            "║ ID:         %-28s║%n" +
            "║ Name:       %-28s║%n" +
            "║ Phone:      %-28s║%n" +
            "║ Role:       %-28s║%n" +
            "║ Department: %-28s║%n" +
            "╚══════════════════════════════════════════╝",
            getId(), getFullName(), getPhone(), role, department
        );
    }
}
