package com.condolottery.model;

/**
 * Represents an applicant who registers for the condominium lottery.
 * Demonstrates: inheritance (extends Person), polymorphism (overrides displayInfo()),
 * constructor chaining (super()), and encapsulation (private fields with getters/setters).
 */
public class Applicant extends Person {

    // Private fields — encapsulation
    private String email;
    private String address;
    private String registrationDate;

    /**
     * Constructs an Applicant with all required details.
     * Demonstrates: constructor chaining using super() to call parent constructor.
     * @param id unique applicant ID
     * @param fullName full name
     * @param phone phone number
     * @param email email address
     * @param address residential address
     * @param registrationDate date of registration (yyyy-MM-dd)
     */
    public Applicant(String id, String fullName, String phone,
                     String email, String address, String registrationDate) {
        super(id, fullName, phone); // Constructor chaining — calls Person constructor
        this.email = email;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    // ==================== Getters and Setters ====================

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

    // ==================== Polymorphism — Method Overriding ====================

    /**
     * Displays applicant-specific information.
     * Overrides the abstract method in Person — demonstrates polymorphism and dynamic binding.
     * @return formatted applicant details
     */
    public String displayInfo() {
        return String.format(
            "╔══════════════════════════════════════════╗%n" +
            "║           APPLICANT DETAILS              ║%n" +
            "╠══════════════════════════════════════════╣%n" +
            "║ ID:                %-22s║%n" +
            "║ Name:              %-22s║%n" +
            "║ Phone:             %-22s║%n" +
            "║ Email:             %-22s║%n" +
            "║ Address:           %-22s║%n" +
            "║ Registration Date: %-22s║%n" +
            "╚══════════════════════════════════════════╝",
            getId(), getFullName(), getPhone(), email, address, registrationDate
        );
    }
}
