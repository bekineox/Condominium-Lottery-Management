package com.condolottery.enums;

/**
 * Enum representing the different types of condominium units.
 * Each type has a human-readable description.
 */
public enum UnitType {
    STUDIO("Studio Apartment"),
    ONE_BEDROOM("One Bedroom Apartment"),
    TWO_BEDROOM("Two Bedroom Apartment"),
    THREE_BEDROOM("Three Bedroom Apartment");

    private final String description;

    UnitType(String description) {
        this.description = description;
    }

    /**
     * Gets the human-readable description of the unit type.
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
