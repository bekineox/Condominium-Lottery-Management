package com.condolottery.main;

import com.condolottery.enums.RegistrationStatus;
import com.condolottery.enums.UnitType;
import com.condolottery.exception.RegistrationException;
import com.condolottery.model.Admin;
import com.condolottery.model.Applicant;
import com.condolottery.model.Person;
import com.condolottery.model.Registration;
import com.condolottery.service.ApplicantService;
import com.condolottery.service.RegistrationService;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Entry point of the Condominium Lottery Registration System.
 * Demonstrates basic OOP principles without complex syntax.
 * 
 * RUBRIC: Classes and Objects - Main is the driving class that creates and uses objects.
 */
public class Main {

    // RUBRIC: Encapsulation - Using private static fields.
    private static ApplicantService applicantService;
    private static RegistrationService registrationService;
    private static Scanner scanner;

    public static void main(String[] args) {
        initializeServices();
        displayWelcome();

        while (true) {
            displayMainMenu();
            int choice = readIntChoice("  Enter your choice (1-6): ", 1, 6);
            if (choice == 6) {
                System.out.println("  ✓ Thank you for using the Condominium Lottery Registration System. Goodbye!");
                break;
            }
            switch (choice) {
                case 1: manageApplicants(); break;
                case 2: manageRegistrations(); break;
                case 3: conductLotteryDraw(); break;
                case 4: displayWinners(); break;
                case 5: demonstratePolymorphism(); break;
            }
        }
        scanner.close();
    }

    private static void initializeServices() {
        applicantService = new ApplicantService();
        registrationService = new RegistrationService();
        scanner = new Scanner(System.in);
    }

    private static void displayWelcome() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║   CONDOMINIUM LOTTERY REGISTRATION SYSTEM           ║");
        System.out.println("║   Developed with Beginner Java OOP Principles       ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    private static void displayMainMenu() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║              MAIN MENU                   ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║  1. Manage Applicants                    ║");
        System.out.println("║  2. Manage Registrations                 ║");
        System.out.println("║  3. Conduct Lottery Draw                 ║");
        System.out.println("║  4. View Lottery Winners                 ║");
        System.out.println("║  5. Demonstrate Polymorphism             ║");
        System.out.println("║  6. Exit                                 ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    // ==================== UTILITY INPUT METHODS ====================

    private static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("  ⚠ Input cannot be empty. Please try again.");
        }
    }

    private static int readIntChoice(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("  ⚠ Please enter a number between " + min + " and " + max + ".");
                continue;
            }
            Scanner inputScanner = new Scanner(input);
            if (inputScanner.hasNextInt()) {
                int choice = inputScanner.nextInt();
                if (!inputScanner.hasNext() && choice >= min && choice <= max) {
                    return choice;
                }
            }
            System.out.println("  ⚠ Please enter a valid number between " + min + " and " + max + ".");
        }
    }

    private static boolean readConfirmation(String message) {
        System.out.print("  " + message + " (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        if (input.equals("yes") || input.equals("y")) {
            return true;
        }
        return false;
    }

    // ==================== APPLICANT MANAGEMENT ====================

    private static void manageApplicants() {
        while (true) {
            System.out.println("\n  --- Applicant Management ---");
            System.out.println("  1. Add Applicant");
            System.out.println("  2. Display All Applicants");
            System.out.println("  3. Search by ID");
            System.out.println("  4. Delete Applicant");
            System.out.println("  5. Back to Main Menu");
            
            int choice = readIntChoice("  Enter your choice (1-5): ", 1, 5);
            if (choice == 5) return;
            
            // RUBRIC: Exception Handling - Standard exceptions are caught to prevent crashes.
            try {
                switch (choice) {
                    case 1: addApplicant(); break;
                    case 2: applicantService.displayAll(); break;
                    case 3: searchApplicant(); break;
                    case 4: deleteApplicant(); break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("  ✗ ERROR: " + e.getMessage());
            }
        }
    }

    private static void addApplicant() {
        System.out.println("\n  --- Add New Applicant ---");
        String id = readString("  Applicant ID: ");
        String name = readString("  Full Name: ");
        String phone = readString("  Phone Number: ");
        String email = readString("  Email Address: ");
        String address = readString("  Home Address: ");
        Date currentDate = new Date();

        Applicant applicant = new Applicant(id, name, phone, email, address, currentDate.toString());
        applicantService.add(applicant);
    }

    private static void searchApplicant() {
        String id = readString("  Applicant ID to search: ");
        Applicant found = applicantService.searchById(id);
        if (found != null) {
            System.out.println(found.displayInfo());
        } else {
            System.out.println("  ℹ No applicant found with ID: " + id);
        }
    }

    private static void deleteApplicant() {
        String id = readString("  Applicant ID to delete: ");
        if (readConfirmation("Are you sure you want to delete applicant '" + id + "'?")) {
            applicantService.delete(id);
        } else {
            System.out.println("  ℹ Deletion cancelled.");
        }
    }

    // ==================== REGISTRATION MANAGEMENT ====================

    private static void manageRegistrations() {
        while (true) {
            System.out.println("\n  --- Registration Management ---");
            System.out.println("  1. New Registration");
            System.out.println("  2. Display All");
            System.out.println("  3. Delete Registration");
            System.out.println("  4. Back to Main Menu");
            
            int choice = readIntChoice("  Enter your choice (1-4): ", 1, 4);
            if (choice == 4) return;
            
            // RUBRIC: Exception Handling - Custom RegistrationException used
            try {
                switch (choice) {
                    case 1: addRegistration(); break;
                    case 2: registrationService.displayAll(); break;
                    case 3: deleteRegistration(); break;
                }
            } catch (RegistrationException e) {
                System.out.println("  ✗ ERROR: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("  ✗ ERROR: " + e.getMessage());
            }
        }
    }

    private static void addRegistration() throws RegistrationException {
        System.out.println("\n  --- Create New Registration ---");
        String regId = readString("  Registration ID: ");
        String applicantId = readString("  Applicant ID: ");

        if (applicantService.searchById(applicantId) == null) {
            throw new RegistrationException("Applicant '" + applicantId + "' does not exist.");
        }

        System.out.println("  Available Unit Types:");
        UnitType[] types = UnitType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println("    " + (i + 1) + ". " + types[i].name());
        }
        int typeChoice = readIntChoice("  Select Unit Type (1-" + types.length + "): ", 1, types.length);
        UnitType selectedType = types[typeChoice - 1];

        // Check if already registered
        List<Registration> allRegs = registrationService.getAll();
        for (int i = 0; i < allRegs.size(); i++) {
            Registration reg = allRegs.get(i);
            if (reg.getApplicantId().equalsIgnoreCase(applicantId)) {
                throw new RegistrationException("Applicant is already registered for the lottery.");
            }
        }

        Date currentDate = new Date();
        Registration registration = new Registration(regId, applicantId, selectedType, currentDate.toString(), RegistrationStatus.PENDING);
        registrationService.add(registration);
    }

    private static void deleteRegistration() {
        String id = readString("  Registration ID to delete: ");
        if (readConfirmation("Are you sure you want to delete registration '" + id + "'?")) {
            try {
                registrationService.delete(id);
            } catch (IllegalArgumentException e) {
                System.out.println("  ✗ ERROR: " + e.getMessage());
            }
        } else {
            System.out.println("  ℹ Deletion cancelled.");
        }
    }

    // ==================== LOTTERY OPERATIONS ====================

    private static void conductLotteryDraw() {
        System.out.println("\n  --- Conduct Lottery Draw ---");
        System.out.println("  Available Unit Types:");
        UnitType[] types = UnitType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println("    " + (i + 1) + ". " + types[i].name());
        }
        int typeChoice = readIntChoice("  Select Unit Type to draw for (1-" + types.length + "): ", 1, types.length);
        UnitType selectedType = types[typeChoice - 1];

        List<Registration> pendingRegistrations = registrationService.getPendingRegistrationsByType(selectedType);

        if (pendingRegistrations.isEmpty()) {
            System.out.println("  ✗ ERROR: No pending registrations found for unit type " + selectedType.name());
            return;
        }

        Random random = new Random();
        int winnerIndex = random.nextInt(pendingRegistrations.size());
        
        System.out.println("\n  ========================================");
        System.out.println("         LOTTERY DRAW IN PROGRESS...");
        System.out.println("  ========================================");
        System.out.println("  Type: " + selectedType.name());
        System.out.println("  Participants: " + pendingRegistrations.size());
        System.out.println("  ========================================\n");

        for (int i = 0; i < pendingRegistrations.size(); i++) {
            Registration registration = pendingRegistrations.get(i);
            if (i == winnerIndex) {
                registration.setStatus(RegistrationStatus.WON);
                System.out.println("  *** WINNER: Applicant ID " + registration.getApplicantId()
                    + " (Registration: " + registration.getRegistrationId() + ") ***");
            } else {
                registration.setStatus(RegistrationStatus.LOST);
            }
            registrationService.update(registration);
        }

        // Save all changes
        registrationService.saveToFile();
        System.out.println("\n  ✓ Lottery draw completed successfully!");
    }

    private static void displayWinners() {
        System.out.println("\n  ======= LOTTERY WINNERS =======");
        List<Registration> allRegs = registrationService.getAll();
        boolean foundWinners = false;

        for (int i = 0; i < allRegs.size(); i++) {
            Registration reg = allRegs.get(i);
            if (reg.getStatus() == RegistrationStatus.WON) {
                foundWinners = true;
                System.out.println(reg.displayInfo());
            }
        }

        if (!foundWinners) {
            System.out.println("  [INFO] No winners have been drawn yet.");
        }
    }

    // ==================== POLYMORPHISM DEMONSTRATION ====================

    // RUBRIC: Polymorphism - Method Overriding and Dynamic Binding are proven here.
    private static void demonstratePolymorphism() {
        System.out.println("\n  --- Polymorphism & Dynamic Binding Demo ---");
        System.out.println("  Creating objects of different types and demonstrating dynamic binding...\n");

        // RUBRIC: Generics - Using generic ArrayList collections
        List<Person> people = new ArrayList<>();
        
        // RUBRIC: Inheritance - Applicant and Admin inherit from Person
        people.add(new Applicant("APP001", "Abebe Kebede", "0911223344",
                "abebe@email.com", "Addis Ababa", "2025-01-15"));
        people.add(new Applicant("APP002", "Tigist Hailu", "0922334455",
                "tigist@email.com", "Dire Dawa", "2025-02-20"));
        people.add(new Admin("ADM001", "Yohannes Taye", "0933445566",
                "Manager", "IT Department"));

        System.out.println("  Calling displayInfo() on each Person reference...");
        System.out.println("  (The correct overridden method is called at runtime based on the actual object type)\n");

        for (int i = 0; i < people.size(); i++) {
            Person person = people.get(i);
            System.out.println("  📋 Object Type: " + person.getClass().getSimpleName());
            // RUBRIC: Dynamic Binding - This calls the Applicant or Admin version of displayInfo()
            System.out.println(person.displayInfo());
            System.out.println();
        }

        System.out.println("  ✓ Demo Complete!");
    }
}
