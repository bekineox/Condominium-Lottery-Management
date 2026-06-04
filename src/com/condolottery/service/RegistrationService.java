package com.condolottery.service;

import com.condolottery.enums.RegistrationStatus;
import com.condolottery.enums.UnitType;
import com.condolottery.exception.RegistrationException;
import com.condolottery.model.Registration;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Service class for managing lottery Registration operations.
 * Demonstrates: interface implementation (Manageable), generic List,
 * traditional file I/O, loops, and exception handling.
 */
public class RegistrationService implements Manageable<Registration> {

    private List<Registration> registrations = new ArrayList<>();
    private String FILE_PATH = "data/registrations.txt";

    /**
     * Constructs a RegistrationService and loads existing data from file.
     */
    public RegistrationService() {
        loadFromFile();
    }

    public void add(Registration registration) {
        if (searchById(registration.getRegistrationId()) != null) {
            throw new IllegalArgumentException("Registration with ID '"
                    + registration.getRegistrationId() + "' already exists.");
        }
        registrations.add(registration);
        saveToFile();
        System.out.println("  [SUCCESS] Registration added: " + registration.getRegistrationId());
    }

    public void displayAll() {
        if (registrations.isEmpty()) {
            System.out.println("  [INFO] No registrations found.");
            return;
        }
        System.out.println("\n  ======= ALL REGISTRATIONS (" + registrations.size() + ") =======\n");
        for (int i = 0; i < registrations.size(); i++) {
            Registration registration = registrations.get(i);
            System.out.println(registration.displayInfo());
        }
    }

    public Registration searchById(String registrationId) {
        for (int i = 0; i < registrations.size(); i++) {
            Registration registration = registrations.get(i);
            if (registration.getRegistrationId().equalsIgnoreCase(registrationId)) {
                return registration;
            }
        }
        return null;
    }

    public void update(Registration updatedRegistration) {
        for (int i = 0; i < registrations.size(); i++) {
            Registration registration = registrations.get(i);
            if (registration.getRegistrationId().equalsIgnoreCase(updatedRegistration.getRegistrationId())) {
                registrations.set(i, updatedRegistration);
                saveToFile();
                return;
            }
        }
        throw new IllegalArgumentException("Registration with ID '"
                + updatedRegistration.getRegistrationId() + "' not found.");
    }

    public void delete(String registrationId) {
        Registration regToRemove = searchById(registrationId);
        if (regToRemove == null) {
            throw new IllegalArgumentException("Registration with ID '" + registrationId + "' not found.");
        }
        registrations.remove(regToRemove);
        saveToFile();
        System.out.println("  [SUCCESS] Registration deleted: " + registrationId);
    }

    /**
     * Returns all registrations for a specific unit type with PENDING status.
     * Demonstrates: traditional loop and conditional checking.
     */
    public List<Registration> getPendingRegistrationsByType(UnitType unitType) {
        List<Registration> pendingList = new ArrayList<>();
        for (int i = 0; i < registrations.size(); i++) {
            Registration registration = registrations.get(i);
            if (registration.getUnitType() == unitType
                    && registration.getStatus() == RegistrationStatus.PENDING) {
                pendingList.add(registration);
            }
        }
        return pendingList;
    }

    public List<Registration> getAll() {
        return registrations;
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    Scanner lineScanner = new Scanner(line);
                    lineScanner.useDelimiter("\\|");
                    Registration registration = new Registration(
                        lineScanner.next(),
                        lineScanner.next(),
                        UnitType.valueOf(lineScanner.next()),
                        lineScanner.next(),
                        RegistrationStatus.valueOf(lineScanner.next())
                    );
                    registrations.add(registration);
                } catch (Exception e) {
                    System.err.println("  [WARNING] Skipping malformed line: " + line);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to read registrations file: " + e.getMessage());
        }
    }

    public void saveToFile() {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            
            File file = new File(FILE_PATH);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for (int i = 0; i < registrations.size(); i++) {
                Registration registration = registrations.get(i);
                String line = registration.getRegistrationId() + "|" +
                              registration.getApplicantId() + "|" +
                              registration.getUnitType().name() + "|" +
                              registration.getRegistrationDate() + "|" +
                              registration.getStatus().name();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to write registrations file: " + e.getMessage());
        }
    }
}
