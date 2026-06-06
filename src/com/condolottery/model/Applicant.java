package com.condolottery.model;

/**
 * Represents an applicant who registers for the condominium lottery.
 */
public class Applicant extends Person {

    // Private fields — encapsulation
    private String email;
    private String address;
    private String registrationDate;

    /**
     * Constructs an Applicant with all required details.
     * @param id unique applicant ID
     * @param fullName full name
     * @param phone phone number
     * @param email email address
     * @param address residential address
     * @param registrationDate date of registration (yyyy-MM-dd)
     */
    public Applicant(String id, String fullName, String phone,
                     String email, String address, String registrationDate) {
        super(null, null, null); // Call to parent constructor is required, we use nulls initially
        
        // Use inherited setters
        setId(id);
        setFullName(fullName);
        setPhone(phone);
        
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
     * Overrides the method in Person — demonstrates polymorphism and dynamic binding.
     * @return formatted applicant details
     */
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

    // Helper method for padding string
    private String padRight(String s, int n) {
        String result = s;
        while (result.length() < n) {
            result += " ";
        }
        return result;
    }
}
