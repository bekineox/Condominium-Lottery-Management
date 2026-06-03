package com.condolottery.model;

import com.condolottery.enums.UnitStatus;
import com.condolottery.enums.UnitType;

/**
 * Represents an individual condominium unit available for the lottery.
 * Demonstrates: encapsulation, usage of enum types (UnitType, UnitStatus),
 * and the this keyword for field assignment.
 */
public class Unit {

    // Private fields — encapsulation
    private String unitId;
    private String condoId;
    private int floorNumber;
    private String unitNumber;
    private double area;       // in square meters
    private double price;
    private UnitType unitType;   // Enum usage
    private UnitStatus status;   // Enum usage

    /**
     * Constructs a Unit with all required details.
     * @param unitId unique unit ID
     * @param condoId ID of the parent condominium
     * @param floorNumber floor number
     * @param unitNumber unit number/label
     * @param area area in square meters
     * @param price price in currency
     * @param unitType type of unit (enum)
     * @param status availability status (enum)
     */
    public Unit(String unitId, String condoId, int floorNumber, String unitNumber,
                double area, double price, UnitType unitType, UnitStatus status) {
        this.unitId = unitId;
        this.condoId = condoId;
        this.floorNumber = floorNumber;
        this.unitNumber = unitNumber;
        this.area = area;
        this.price = price;
        this.unitType = unitType;
        this.status = status;
    }

    // ==================== Getters and Setters ====================

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getCondoId() {
        return condoId;
    }

    public void setCondoId(String condoId) {
        this.condoId = condoId;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public UnitStatus getStatus() {
        return status;
    }

    public void setStatus(UnitStatus status) {
        this.status = status;
    }

    // ==================== Display ====================

    /**
     * Returns a formatted string with the unit's details.
     * @return formatted info string
     */
    public String displayInfo() {
        return String.format(
            "╔══════════════════════════════════════════╗%n" +
            "║              UNIT DETAILS                ║%n" +
            "╠══════════════════════════════════════════╣%n" +
            "║ Unit ID:      %-26s║%n" +
            "║ Condo ID:     %-26s║%n" +
            "║ Floor:        %-26d║%n" +
            "║ Unit Number:  %-26s║%n" +
            "║ Area (sqm):   %-26.2f║%n" +
            "║ Price (ETB):  %-26.2f║%n" +
            "║ Type:         %-26s║%n" +
            "║ Status:       %-26s║%n" +
            "╚══════════════════════════════════════════╝",
            unitId, condoId, floorNumber, unitNumber, area, price,
            unitType.getDescription(), status.getDescription()
        );
    }
}
