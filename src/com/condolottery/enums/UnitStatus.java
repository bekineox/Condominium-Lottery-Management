package com.condolottery.enums;


public enum UnitStatus {
    AVAILABLE("Available"),
    RESERVED("Reserved"),
    SOLD("Sold");

    private final String description;

    UnitStatus(String description) {
        this.description = description;
    }

  
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
