package com.condolottery.model;

/**
 * Abstract base class representing a person in the system.
 * Demonstrates: abstraction, encapsulation, and polymorphism (via abstract method).
 * Subclasses: Applicant, Admin
 */
public abstract class Person {

    // Private fields — encapsulation
    private String id;
    private String fullName;
    private String phone;

    /**
     * Constructs a Person with the given details.
     * Demonstrates: this keyword for field assignment.
     * @param id unique identifier
     * @param fullName full name of the person
     * @param phone phone number
     */
    public Person(String id, String fullName, String phone) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
    }

    // ==================== Getters and Setters ====================

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

    // ==================== Abstract Method (Polymorphism) ====================

    /**
     * Returns a formatted string with the person's details.
     * Must be overridden by all subclasses — enables dynamic binding.
     * @return formatted info string
     */
    public abstract String displayInfo();
}
