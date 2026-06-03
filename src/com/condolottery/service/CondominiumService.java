package com.condolottery.service;

import com.condolottery.exception.InvalidDataException;
import com.condolottery.model.Condominium;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing Condominium operations.
 * Demonstrates: interface implementation (Manageable), generic List,
 * file I/O, Streams, and exception handling.
 */
public class CondominiumService implements Manageable<Condominium> {

    private final List<Condominium> condominiums = new ArrayList<>();
    private static final String FILE_PATH = "data/condominiums.txt";

    /**
     * Constructs a CondominiumService and loads existing data from file.
     */
    public CondominiumService() {
        loadFromFile();
    }

    @Override
    public void add(Condominium condominium) {
        if (searchById(condominium.getCondoId()) != null) {
            throw new InvalidDataException("Condominium with ID '"
                    + condominium.getCondoId() + "' already exists.");
        }
        condominiums.add(condominium);
        saveToFile();
        System.out.println("  [SUCCESS] Condominium added successfully: " + condominium.getName());
    }

    @Override
    public void displayAll() {
        if (condominiums.isEmpty()) {
            System.out.println("  [INFO] No condominiums registered yet.");
            return;
        }
        System.out.println("\n  ======= ALL CONDOMINIUMS (" + condominiums.size() + ") =======\n");
        condominiums.forEach(condominium -> System.out.println(condominium.displayInfo()));
    }

    @Override
    public Condominium searchById(String condoId) {
        return condominiums.stream()
                .filter(condominium -> condominium.getCondoId().equalsIgnoreCase(condoId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Condominium updatedCondominium) {
        for (int i = 0; i < condominiums.size(); i++) {
            if (condominiums.get(i).getCondoId().equalsIgnoreCase(updatedCondominium.getCondoId())) {
                condominiums.set(i, updatedCondominium);
                saveToFile();
                return;
            }
        }
        throw new InvalidDataException("Condominium with ID '"
                + updatedCondominium.getCondoId() + "' not found.");
    }

    @Override
    public void delete(String condoId) {
        Condominium condoToRemove = searchById(condoId);
        if (condoToRemove == null) {
            throw new InvalidDataException("Condominium with ID '" + condoId + "' not found.");
        }
        condominiums.remove(condoToRemove);
        saveToFile();
        System.out.println("  [SUCCESS] Condominium deleted successfully: " + condoToRemove.getName());
    }

    public List<Condominium> getAll() {
        return condominiums;
    }

    /**
     * Loads condominium data from the text file.
     */
    private void loadFromFile() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) return;
        try {
            for (String line : Files.readAllLines(path)) {
                if (line.trim().isEmpty()) continue;
                try {
                    String[] parts = line.split("\\|");
                    condominiums.add(new Condominium(
                        parts[0],
                        parts[1],
                        parts[2],
                        Integer.parseInt(parts[3]),
                        Integer.parseInt(parts[4])
                    ));
                } catch (Exception e) {
                    System.err.println("  [WARNING] Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to read condominiums file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try {
            Files.createDirectories(Paths.get("data"));
            List<String> lines = new ArrayList<>();
            for (Condominium condominium : condominiums) {
                lines.add(String.join("|",
                    condominium.getCondoId(),
                    condominium.getName(),
                    condominium.getLocation(),
                    String.valueOf(condominium.getTotalFloors()),
                    String.valueOf(condominium.getTotalUnits())
                ));
            }
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to write condominiums file: " + e.getMessage());
        }
    }
}
