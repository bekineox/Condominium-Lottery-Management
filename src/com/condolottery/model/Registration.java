package com.condolottery.model;

import com.condolottery.enums.RegistrationStatus;
import com.condolottery.enums.UnitType;

/**
 * Represents a lottery registration.
 */
public class Registration {

    private String registrationId;
    private String applicantId;
    private UnitType unitType;
    private String registrationDate;
    private RegistrationStatus status;

    /**
     * Constructs a new Registration.
     */
    public Registration(String registrationId, String applicantId, UnitType unitType, String registrationDate, RegistrationStatus status) {
        this.registrationId = registrationId;
        this.applicantId = applicantId;
        this.unitType = unitType;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    // Getters and Setters demonstrate standard Encapsulation.

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

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
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

    /**
     * Displays formatted details of the registration.
     */
    public String displayInfo() {
        String info = "";
        info = info + "  [Registration Info]\n";
        info = info + "  Registration ID : " + registrationId + "\n";
        info = info + "  Applicant ID    : " + applicantId + "\n";
        info = info + "  Unit Type       : " + unitType.name() + "\n";
        info = info + "  Date            : " + registrationDate + "\n";
        info = info + "  Status          : " + status.name() + "\n";
        return info;
    }
}
