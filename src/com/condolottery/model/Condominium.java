package com.condolottery.model;

/**
 * Represents a condominium building in the system.
 * Demonstrates: encapsulation with private fields and public getters/setters,
 * and the this keyword for field assignment.
 */
public class Condominium {

    // Private fields — encapsulation
    private String condoId;
    private String name;
    private String location;
    private int totalFloors;
    private int totalUnits;

    /**
     * Constructs a Condominium with all required details.
     * @param condoId unique condominium ID
     * @param name name of the condominium
     * @param location address/location
     * @param totalFloors total number of floors
     * @param totalUnits total number of units
     */
    public Condominium(String condoId, String name, String location,
                       int totalFloors, int totalUnits) {
        this.condoId = condoId;
        this.name = name;
        this.location = location;
        this.totalFloors = totalFloors;
        this.totalUnits = totalUnits;
    }

    // ==================== Getters and Setters ====================

    public String getCondoId() {
        return condoId;
    }

    public void setCondoId(String condoId) {
        this.condoId = condoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getTotalFloors() {
        return totalFloors;
    }

    public void setTotalFloors(int totalFloors) {
        this.totalFloors = totalFloors;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    // ==================== Display ====================

    /**
     * Returns a formatted string with the condominium's details.
     * @return formatted info string
     */
    public String displayInfo() {
        return String.format(
            "╔══════════════════════════════════════════╗%n" +
            "║         CONDOMINIUM DETAILS              ║%n" +
            "╠══════════════════════════════════════════╣%n" +
            "║ Condo ID:     %-26s║%n" +
            "║ Name:         %-26s║%n" +
            "║ Location:     %-26s║%n" +
            "║ Total Floors: %-26d║%n" +
            "║ Total Units:  %-26d║%n" +
            "╚══════════════════════════════════════════╝",
            condoId, name, location, totalFloors, totalUnits
        );
    }
}
