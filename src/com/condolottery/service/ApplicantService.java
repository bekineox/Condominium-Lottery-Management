package com.condolottery.service;

import com.condolottery.exception.InvalidDataException;

import com.condolottery.model.Applicant;
import com.condolottery.util.MenuHelper;
import com.condolottery.util.UserMessages;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing Applicant operations.
 * Demonstrates: interface implementation (Manageable), generic List,
 * file I/O, Streams, and exception handling.
 */
public class ApplicantService implements Manageable<Applicant> {

    private final List<Applicant> applicants = new ArrayList<>();
    private static final String FILE_PATH = "data/applicants.txt";

    /**
     * Constructs an ApplicantService and loads existing data from file.
     */
    public ApplicantService() {
        loadFromFile();
    }

    @Override
    public void add(Applicant applicant) {
        if (searchById(applicant.getId()) != null) {
            throw new InvalidDataException(UserMessages.alreadyExists("Applicant", applicant.getId()));
        }
        applicants.add(applicant);
        saveToFile();
        MenuHelper.displaySuccess(UserMessages.addedSuccess("applicant", applicant.getFullName()));
    }

    @Override
    public void displayAll() {
        if (applicants.isEmpty()) {
            MenuHelper.displayInfo(UserMessages.listEmpty("applicants"));
            return;
        }
        System.out.println("\n  📋 ALL REGISTERED APPLICANTS (" + applicants.size() + ")\n");
        applicants.forEach(applicant -> System.out.println(applicant.displayInfo()));
    }

    @Override
    public Applicant searchById(String applicantId) {
        return applicants.stream()
                .filter(applicant -> applicant.getId().equalsIgnoreCase(applicantId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(Applicant updatedApplicant) {
        for (int i = 0; i < applicants.size(); i++) {
            if (applicants.get(i).getId().equalsIgnoreCase(updatedApplicant.getId())) {
                applicants.set(i, updatedApplicant);
                saveToFile();
                return;
            }
        }
        throw new InvalidDataException(UserMessages.notFoundById("applicant", updatedApplicant.getId()));
    }

    @Override
    public void delete(String applicantId) {
        Applicant applicantToRemove = searchById(applicantId);
        if (applicantToRemove == null) {
            throw new InvalidDataException(UserMessages.notFoundById("applicant", applicantId));
        }
        applicants.remove(applicantToRemove);
        saveToFile();
        MenuHelper.displaySuccess(UserMessages.deletedSuccess("applicant", applicantToRemove.getFullName()));
    }

    public List<Applicant> getAll() {
        return applicants;
    }

    /**
     * Loads applicant data from the text file.
     */
    private void loadFromFile() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) return;
        try {
            for (String line : Files.readAllLines(path)) {
                if (line.trim().isEmpty()) continue;
                try {
                    String[] parts = line.split("\\|");
                    applicants.add(new Applicant(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]));
                } catch (Exception e) {
                    System.err.println("  [WARNING] Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to read applicants file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try {
            Files.createDirectories(Paths.get("data"));
            List<String> lines = new ArrayList<>();
            for (Applicant applicant : applicants) {
                lines.add(String.join("|",
                    applicant.getId(),
                    applicant.getFullName(),
                    applicant.getPhone(),
                    applicant.getEmail(),
                    applicant.getAddress(),
                    applicant.getRegistrationDate()
                ));
            }
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to write applicants file: " + e.getMessage());
        }
    }
}
