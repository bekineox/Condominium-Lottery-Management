package com.condolottery.model;

import com.condolottery.enums.RegistrationStatus;

/**
 * Represents a lottery registration linking an applicant to a preferred unit.
 * Demonstrates: encapsulation and enum usage (RegistrationStatus).
 */
public class Registration {

    // Private fields — encapsulation
    private String registrationId;
    private String applicantId;
    private String unitId;
    private String registrationDate;
    private RegistrationStatus status; // Enum usage

    /**
     * Constructs a Registration with all required details.
     * @param registrationId unique registration ID
     * @param applicantId ID of the applicant
     * @param unitId ID of the desired unit
     * @param registrationDate date of registration (yyyy-MM-dd)
     * @param status current registration status (enum)
     */
    public Registration(String registrationId, String applicantId, String unitId,
                        String registrationDate, RegistrationStatus status) {
        this.registrationId = registrationId;
        this.applicantId = applicantId;
        this.unitId = unitId;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    // ==================== Getters and Setters ====================

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }

    // ==================== Display ====================

    /**
     * Returns a formatted string with the registration's details.
     * @return formatted info string
     */
    public String displayInfo() {
        return String.format(
            "╔══════════════════════════════════════════╗%n" +
            "║         REGISTRATION DETAILS             ║%n" +
            "╠══════════════════════════════════════════╣%n" +
            "║ Reg. ID:      %-26s║%n" +
            "║ Applicant ID: %-26s║%n" +
            "║ Unit ID:      %-26s║%n" +
            "║ Date:         %-26s║%n" +
            "║ Status:       %-26s║%n" +
            "╚══════════════════════════════════════════╝",
            registrationId, applicantId, unitId, registrationDate,
            status.getDescription()
        );
    }
}
