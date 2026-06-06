package com.condolottery.model;

/**
 * Base class representing a person in the system.
 * Subclasses: Applicant, Admin
 */
public class Person {

    // Private fields — encapsulation
    private String id;
    private String fullName;
    private String phone;

    /**
     * Constructs a Person with the given details.
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

    // ==================== Method (Polymorphism basis) ====================

    /**
     * Returns a formatted string with the person's details.
     * Overridden by subclasses.
     * @return formatted info string
     */
    public String displayInfo() {
        return "ID: " + id + "\nName: " + fullName + "\nPhone: " + phone;
    }
}
