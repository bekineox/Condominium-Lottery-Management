package com.condolottery.service;

import com.condolottery.enums.RegistrationStatus;
import com.condolottery.exception.InvalidDataException;
import com.condolottery.model.Registration;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing lottery Registration operations.
 * Demonstrates: interface implementation (Manageable), generic List,
 * file I/O, Streams, and exception handling.
 */
public class RegistrationService implements Manageable<Registration> {

    private final List<Registration> registrations = new ArrayList<>();
    private static final String FILE_PATH = "data/registrations.txt";

    /**
     * Constructs a RegistrationService and loads existing data from file.
     */
    public RegistrationService() {
        loadFromFile();
    }

    @Override
    public void add(Registration registration) {
        if (searchById(registration.getRegistrationId()) != null) {
            throw new InvalidDataException("Registration with ID '"
                    + registration.getRegistrationId() + "' already exists.");
        }
        registrations.add(registration);
        saveToFile();
        System.out.println("  [SUCCESS] Registration added: " + registration.getRegistrationId());
    }

    @Override
    public void displayAll() {
        if (registrations.isEmpty()) {
            System.out.println("  [INFO] No registrations found.");
            return;
        }
        System.out.println("\n  ======= ALL REGISTRATIONS (" + registrations.size() + ") =======\n");
        registrations.forEach(registration -> System.out.println(registration.displayInfo()));
    }

    @Override
    public Registration searchById(String registrationId) {
        return registrations.stream()
                .filter(registration -> registration.getRegistrationId().equalsIgnoreCase(registrationId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Registration updatedRegistration) {
        for (int i = 0; i < registrations.size(); i++) {
            if (registrations.get(i).getRegistrationId()
                    .equalsIgnoreCase(updatedRegistration.getRegistrationId())) {
                registrations.set(i, updatedRegistration);
                saveToFile();
                return;
            }
        }
        throw new InvalidDataException("Registration with ID '"
                + updatedRegistration.getRegistrationId() + "' not found.");
    }

    @Override
    public void delete(String registrationId) {
        Registration regToRemove = searchById(registrationId);
        if (regToRemove == null) {
            throw new InvalidDataException("Registration with ID '" + registrationId + "' not found.");
        }
        registrations.remove(regToRemove);
        saveToFile();
        System.out.println("  [SUCCESS] Registration deleted: " + registrationId);
    }

    /**
     * Returns all registrations for a specific unit with APPROVED status.
     * Demonstrates: Streams and lambda expressions.
     */
    public List<Registration> getApprovedRegistrationsByUnit(String unitId) {
        return registrations.stream()
                .filter(registration -> registration.getUnitId().equalsIgnoreCase(unitId)
                        && registration.getStatus() == RegistrationStatus.APPROVED)
                .collect(Collectors.toList());
    }

    public List<Registration> getAll() {
        return registrations;
    }

    private void loadFromFile() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) return;
        try {
            for (String line : Files.readAllLines(path)) {
                if (line.trim().isEmpty()) continue;
                try {
                    String[] parts = line.split("\\|");
                    registrations.add(new Registration(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        RegistrationStatus.valueOf(parts[4])
                    ));
                } catch (Exception e) {
                    System.err.println("  [WARNING] Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to read registrations file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try {
            Files.createDirectories(Paths.get("data"));
            List<String> lines = new ArrayList<>();
            for (Registration registration : registrations) {
                lines.add(String.join("|",
                    registration.getRegistrationId(),
                    registration.getApplicantId(),
                    registration.getUnitId(),
                    registration.getRegistrationDate(),
                    registration.getStatus().name()
                ));
            }
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to write registrations file: " + e.getMessage());
        }
    }
}
