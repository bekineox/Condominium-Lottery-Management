package com.condolottery.service;

import com.condolottery.model.Applicant;

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
 * Service class for managing Applicant operations.
 * traditional file I/O, loops, and exception handling.
 */
public class ApplicantService implements Manageable<Applicant> {

    private List<Applicant> applicants = new ArrayList<>();
    private String FILE_PATH = "data/applicants.txt";

    /**
     * Constructs an ApplicantService and loads existing data from file.
     */
    public ApplicantService() {
        loadFromFile();
    }

    public void add(Applicant applicant) {
        if (searchById(applicant.getId()) != null) {
            throw new IllegalArgumentException("Applicant with ID '" + applicant.getId() + "' already exists.");
        }
        applicants.add(applicant);
        saveToFile();
        System.out.println("  [SUCCESS] Applicant added: " + applicant.getFullName());
    }

    public void displayAll() {
        if (applicants.isEmpty()) {
            System.out.println("  [INFO] No applicants found.");
            return;
        }
        System.out.println("\n  📋 ALL REGISTERED APPLICANTS (" + applicants.size() + ")\n");
        for (int i = 0; i < applicants.size(); i++) {
            Applicant applicant = applicants.get(i);
            System.out.println(applicant.displayInfo());
        }
    }

    public Applicant searchById(String applicantId) {
        for (int i = 0; i < applicants.size(); i++) {
            Applicant applicant = applicants.get(i);
            if (applicant.getId().equalsIgnoreCase(applicantId)) {
                return applicant;
            }
        }
        return null;
    }

    public void update(Applicant updatedApplicant) {
        for (int i = 0; i < applicants.size(); i++) {
            Applicant applicant = applicants.get(i);
            if (applicant.getId().equalsIgnoreCase(updatedApplicant.getId())) {
                applicants.set(i, updatedApplicant);
                saveToFile();
                return;
            }
        }
        throw new IllegalArgumentException("Applicant with ID '" + updatedApplicant.getId() + "' not found.");
    }

    public void delete(String applicantId) {
        Applicant applicantToRemove = searchById(applicantId);
        if (applicantToRemove == null) {
            throw new IllegalArgumentException("Applicant with ID '" + applicantId + "' not found.");
        }
        applicants.remove(applicantToRemove);
        saveToFile();
        System.out.println("  [SUCCESS] Applicant deleted: " + applicantToRemove.getFullName());
    }

    public List<Applicant> getAll() {
        return applicants;
    }

    /**
     * Loads applicant data from the text file.
     */
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
                try (Scanner lineScanner = new Scanner(line)) {
                    lineScanner.useDelimiter("\\|");
                    Applicant applicant = new Applicant(
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.next(),
                        lineScanner.next()
                    );
                    applicants.add(applicant);
                } catch (Exception e) {
                    System.err.println("  [WARNING] Skipping malformed line: " + line);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to read applicants file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            
            File file = new File(FILE_PATH);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            
            for (int i = 0; i < applicants.size(); i++) {
                Applicant applicant = applicants.get(i);
                String line = applicant.getId() + "|" +
                              applicant.getFullName() + "|" +
                              applicant.getPhone() + "|" +
                              applicant.getEmail() + "|" +
                              applicant.getAddress() + "|" +
                              applicant.getRegistrationDate();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to write applicants file: " + e.getMessage());
        }
    }
}
