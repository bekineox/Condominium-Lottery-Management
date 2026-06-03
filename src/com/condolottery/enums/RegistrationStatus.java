package com.condolottery.enums;

/**
 * Enum representing the status of a lottery registration.
 */
public enum RegistrationStatus {
    PENDING("Pending Review"),
    APPROVED("Approved for Draw"),
    WON("Won the Lottery"),
    LOST("Did Not Win");

    private final String description;

    RegistrationStatus(String description) {
        this.description = description;
    }

    /**
     * Gets the human-readable description of the registration status.
     * @return the description string
     */
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
