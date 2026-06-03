package com.condolottery.service;

import com.condolottery.enums.UnitStatus;
import com.condolottery.enums.UnitType;
import com.condolottery.exception.InvalidDataException;
import com.condolottery.model.Unit;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing condominium Unit operations.
 * Demonstrates: interface implementation (Manageable), generic List,
 * file I/O, Streams, and exception handling.
 */
public class UnitService implements Manageable<Unit> {

    private final List<Unit> units = new ArrayList<>();
    private static final String FILE_PATH = "data/units.txt";

    /**
     * Constructs a UnitService and loads existing data from file.
     */
    public UnitService() {
        loadFromFile();
    }

    @Override
    public void add(Unit unit) {
        if (searchById(unit.getUnitId()) != null) {
            throw new InvalidDataException("Unit with ID '" + unit.getUnitId() + "' already exists.");
        }
        units.add(unit);
        saveToFile();
        System.out.println("  [SUCCESS] Unit added successfully: " + unit.getUnitNumber());
    }

    @Override
    public void displayAll() {
        if (units.isEmpty()) {
            System.out.println("  [INFO] No units registered yet.");
            return;
        }
        System.out.println("\n  ======= ALL CONDOMINIUM UNITS (" + units.size() + ") =======\n");
        units.forEach(unit -> System.out.println(unit.displayInfo()));
    }

    @Override
    public Unit searchById(String unitId) {
        return units.stream()
                .filter(unit -> unit.getUnitId().equalsIgnoreCase(unitId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Unit updatedUnit) {
        for (int i = 0; i < units.size(); i++) {
            if (units.get(i).getUnitId().equalsIgnoreCase(updatedUnit.getUnitId())) {
                units.set(i, updatedUnit);
                saveToFile();
                return;
            }
        }
        throw new InvalidDataException("Unit with ID '" + updatedUnit.getUnitId() + "' not found.");
    }

    @Override
    public void delete(String unitId) {
        Unit unitToRemove = searchById(unitId);
        if (unitToRemove == null) {
            throw new InvalidDataException("Unit with ID '" + unitId + "' not found.");
        }
        units.remove(unitToRemove);
        saveToFile();
        System.out.println("  [SUCCESS] Unit deleted successfully: " + unitToRemove.getUnitNumber());
    }

    public List<Unit> getAll() {
        return units;
    }

    /**
     * Loads unit data from the text file.
     */
    private void loadFromFile() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) return;
        try {
            for (String line : Files.readAllLines(path)) {
                if (line.trim().isEmpty()) continue;
                try {
                    String[] parts = line.split("\\|");
                    units.add(new Unit(
                        parts[0],
                        parts[1],
                        Integer.parseInt(parts[2]),
                        parts[3],
                        Double.parseDouble(parts[4]),
                        Double.parseDouble(parts[5]),
                        UnitType.valueOf(parts[6]),
                        UnitStatus.valueOf(parts[7])
                    ));
                } catch (Exception e) {
                    System.err.println("  [WARNING] Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to read units file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try {
            Files.createDirectories(Paths.get("data"));
            List<String> lines = new ArrayList<>();
            for (Unit unit : units) {
                lines.add(String.join("|",
                    unit.getUnitId(),
                    unit.getCondoId(),
                    String.valueOf(unit.getFloorNumber()),
                    unit.getUnitNumber(),
                    String.valueOf(unit.getArea()),
                    String.valueOf(unit.getPrice()),
                    unit.getUnitType().name(),
                    unit.getStatus().name()
                ));
            }
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to write units file: " + e.getMessage());
        }
    }
}
