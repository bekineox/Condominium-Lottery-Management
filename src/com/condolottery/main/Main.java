package com.condolottery.main;

import com.condolottery.enums.RegistrationStatus;
import com.condolottery.enums.UnitStatus;
import com.condolottery.enums.UnitType;
import com.condolottery.exception.InvalidDataException;
import com.condolottery.exception.RegistrationException;
import com.condolottery.model.*;
import com.condolottery.service.*;
import com.condolottery.util.MenuHelper;
import com.condolottery.util.InputValidator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Entry point of the Condominium Lottery Registration System.
 * Demonstrates OOP principles: inheritance, polymorphism, abstraction, encapsulation, interfaces, and generics.
 */
public class Main {

    private static ApplicantService applicantService;
    private static CondominiumService condominiumService;
    private static UnitService unitService;
    private static RegistrationService registrationService;
    private static LotteryService lotteryService;
    private static Scanner scanner;
    private static InputValidator validator;

    public static void main(String[] args) {
        initializeServices();
        displayWelcome();

        while (true) {
            MenuHelper.displayMainMenu();
            int choice = validator.readChoice(1, 8);
            if (choice == 8) {
                MenuHelper.displaySuccess("Thank you for using the Condominium Lottery Registration System. Goodbye!");
                break;
            }
            switch (choice) {
                case 1: manageApplicants(); break;
                case 2: manageCondominiums(); break;
                case 3: manageUnits(); break;
                case 4: manageRegistrations(); break;
                case 5: conductLotteryDraw(); break;
                case 6: lotteryService.displayAllResults(); break;
                case 7: demonstratePolymorphism(); break;
            }
        }
        scanner.close();
    }

    private static void initializeServices() {
        applicantService = new ApplicantService();
        condominiumService = new CondominiumService();
        unitService = new UnitService();
        registrationService = new RegistrationService();
        lotteryService = new LotteryService();
        scanner = new Scanner(System.in);
        validator = new InputValidator(scanner);
    }

    private static void displayWelcome() {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║   CONDOMINIUM LOTTERY REGISTRATION SYSTEM           ║");
        System.out.println("║   Developed with Java OOP Principles                ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    private static int getSubmenuChoice(String title, String... options) {
        MenuHelper.displaySubMenu(title, options);
        return validator.readChoice(1, options.length);
    }

    // ==================== APPLICANT MANAGEMENT ====================

    private static void manageApplicants() {
        while (true) {
            int choice = getSubmenuChoice("Applicant Management",
                "Add Applicant", "Display All Applicants", "Search by ID", "Update Applicant", "Delete Applicant", "Back to Main Menu");
            if (choice == 6) return;
            try {
                switch (choice) {
                    case 1: addApplicant(); break;
                    case 2: applicantService.displayAll(); break;
                    case 3: searchApplicant(); break;
                    case 4: updateApplicant(); break;
                    case 5: deleteApplicant(); break;
                }
            } catch (InvalidDataException e) {
                MenuHelper.displayError(e.getMessage());
            }
        }
    }

    private static void addApplicant() {
        MenuHelper.displayHeader("Add New Applicant");
        applicantService.add(new Applicant(
            validator.readString("  Applicant ID: "),
            validator.readString("  Full Name: "),
            validator.readString("  Phone Number: "),
            validator.readString("  Email Address: "),
            validator.readString("  Home Address: "),
            LocalDate.now().toString()
        ));
    }

    private static void searchApplicant() {
        String id = validator.readString("  Applicant ID to search: ");
        Applicant found = applicantService.searchById(id);
        System.out.println(found != null ? found.displayInfo() : "  [INFO] No applicant found with ID: " + id);
    }

    private static void updateApplicant() {
        MenuHelper.displayHeader("Update Applicant");
        String id = validator.readString("  Applicant ID to update: ");
        Applicant existing = applicantService.searchById(id);
        if (existing == null) {
            throw new InvalidDataException("Applicant with ID '" + id + "' not found.");
        }

        System.out.println("  Current details:\n" + existing.displayInfo());

        existing.setFullName(validator.readStringOptional("  New Full Name [" + existing.getFullName() + "]: ", existing.getFullName()));
        existing.setPhone(validator.readStringOptional("  New Phone [" + existing.getPhone() + "]: ", existing.getPhone()));
        existing.setEmail(validator.readStringOptional("  New Email [" + existing.getEmail() + "]: ", existing.getEmail()));
        existing.setAddress(validator.readStringOptional("  New Address [" + existing.getAddress() + "]: ", existing.getAddress()));

        applicantService.update(existing);
        MenuHelper.displaySuccess("Applicant has been updated successfully.");
    }

    private static void deleteApplicant() {
        MenuHelper.displayHeader("Delete Applicant");
        String id = validator.readString("  Applicant ID to delete: ");
        if (validator.readConfirmation("Are you sure you want to delete applicant '" + id + "'?")) {
            applicantService.delete(id);
        } else {
            MenuHelper.displayInfo("Deletion cancelled.");
        }
    }

    // ==================== CONDOMINIUM MANAGEMENT ====================

    private static void manageCondominiums() {
        while (true) {
            int choice = getSubmenuChoice("Condominium Management",
                "Add Condominium", "Display All", "Search by ID", "Update Condominium", "Delete Condominium", "Back to Main Menu");
            if (choice == 6) return;
            try {
                switch (choice) {
                    case 1: addCondominium(); break;
                    case 2: condominiumService.displayAll(); break;
                    case 3: searchCondominium(); break;
                    case 4: updateCondominium(); break;
                    case 5: deleteCondominium(); break;
                }
            } catch (InvalidDataException e) {
                MenuHelper.displayError(e.getMessage());
            }
        }
    }

    private static void addCondominium() {
        MenuHelper.displayHeader("Add New Condominium");
        condominiumService.add(new Condominium(
            validator.readString("  Condominium ID: "),
            validator.readString("  Condominium Name: "),
            validator.readString("  Location: "),
            validator.readInt("  Total Floors: "),
            validator.readInt("  Total Units: ")
        ));
    }

    private static void searchCondominium() {
        String id = validator.readString("  Condominium ID to search: ");
        Condominium found = condominiumService.searchById(id);
        System.out.println(found != null ? found.displayInfo() : "  [INFO] No condominium found with ID: " + id);
    }

    private static void updateCondominium() {
        MenuHelper.displayHeader("Update Condominium");
        String id = validator.readString("  Condominium ID to update: ");
        Condominium existing = condominiumService.searchById(id);
        if (existing == null) {
            throw new InvalidDataException("Condominium with ID '" + id + "' not found.");
        }

        System.out.println("  Current details:\n" + existing.displayInfo());

        existing.setName(validator.readStringOptional("  New Name [" + existing.getName() + "]: ", existing.getName()));
        existing.setLocation(validator.readStringOptional("  New Location [" + existing.getLocation() + "]: ", existing.getLocation()));
        existing.setTotalFloors(validator.readIntOptional("  New Total Floors [" + existing.getTotalFloors() + "]: ", existing.getTotalFloors()));
        existing.setTotalUnits(validator.readIntOptional("  New Total Units [" + existing.getTotalUnits() + "]: ", existing.getTotalUnits()));

        condominiumService.update(existing);
        MenuHelper.displaySuccess("Condominium has been updated successfully.");
    }

    private static void deleteCondominium() {
        MenuHelper.displayHeader("Delete Condominium");
        String id = validator.readString("  Condominium ID to delete: ");
        if (validator.readConfirmation("Are you sure you want to delete condominium '" + id + "'?")) {
            condominiumService.delete(id);
        } else {
            MenuHelper.displayInfo("Deletion cancelled.");
        }
    }

    // ==================== UNIT MANAGEMENT ====================

    private static void manageUnits() {
        while (true) {
            int choice = getSubmenuChoice("Unit Management",
                "Add Unit", "Display All Units", "Search by ID", "Update Unit", "Delete Unit", "Back to Main Menu");
            if (choice == 6) return;
            try {
                switch (choice) {
                    case 1: addUnit(); break;
                    case 2: unitService.displayAll(); break;
                    case 3: searchUnit(); break;
                    case 4: updateUnit(); break;
                    case 5: deleteUnit(); break;
                }
            } catch (InvalidDataException e) {
                MenuHelper.displayError(e.getMessage());
            }
        }
    }

    private static void addUnit() {
        MenuHelper.displayHeader("Add New Unit");
        String unitId = validator.readString("  Unit ID: ");
        String condoId = validator.readString("  Condominium ID: ");
        int floor = validator.readInt("  Floor Number: ");
        String unitNum = validator.readString("  Unit Number: ");
        double area = validator.readDouble("  Area (sqm): ");
        double price = validator.readDouble("  Price (ETB): ");

        System.out.println("  \n  Available Unit Types:");
        UnitType[] types = UnitType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println("    " + (i + 1) + ". " + types[i].getDescription());
        }
        int typeChoice = validator.readChoice(1, types.length) - 1;
        UnitType unitType = types[typeChoice];

        unitService.add(new Unit(unitId, condoId, floor, unitNum, area, price, unitType, UnitStatus.AVAILABLE));
    }

    private static void searchUnit() {
        String id = validator.readString("  Unit ID to search: ");
        Unit found = unitService.searchById(id);
        System.out.println(found != null ? found.displayInfo() : "  [INFO] No unit found with ID: " + id);
    }

    private static void updateUnit() {
        MenuHelper.displayHeader("Update Unit");
        String id = validator.readString("  Unit ID to update: ");
        Unit existing = unitService.searchById(id);
        if (existing == null) {
            throw new InvalidDataException("Unit with ID '" + id + "' not found.");
        }

        System.out.println("  Current details:\n" + existing.displayInfo());

        existing.setCondoId(validator.readStringOptional("  New Condominium ID [" + existing.getCondoId() + "]: ", existing.getCondoId()));
        existing.setFloorNumber(validator.readIntOptional("  New Floor Number [" + existing.getFloorNumber() + "]: ", existing.getFloorNumber()));
        existing.setUnitNumber(validator.readStringOptional("  New Unit Number [" + existing.getUnitNumber() + "]: ", existing.getUnitNumber()));
        existing.setArea(validator.readDoubleOptional("  New Area (sqm) [" + existing.getArea() + "]: ", existing.getArea()));
        existing.setPrice(validator.readDoubleOptional("  New Price (ETB) [" + existing.getPrice() + "]: ", existing.getPrice()));

        System.out.println("  \n  Available Unit Types:");
        UnitType[] types = UnitType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println("    " + (i + 1) + ". " + types[i].getDescription() + (types[i] == existing.getUnitType() ? " (Current)" : ""));
        }
        int typeChoice = validator.readChoiceOptional("  Select Type [Current]: ", 1, types.length, -1);
        if (typeChoice != -1) {
            existing.setUnitType(types[typeChoice - 1]);
        }

        System.out.println("  \n  Available Unit Statuses:");
        UnitStatus[] statuses = UnitStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println("    " + (i + 1) + ". " + statuses[i].getDescription() + (statuses[i] == existing.getStatus() ? " (Current)" : ""));
        }
        int statusChoice = validator.readChoiceOptional("  Select Status [Current]: ", 1, statuses.length, -1);
        if (statusChoice != -1) {
            existing.setStatus(statuses[statusChoice - 1]);
        }

        unitService.update(existing);
        MenuHelper.displaySuccess("Unit has been updated successfully.");
    }

    private static void deleteUnit() {
        MenuHelper.displayHeader("Delete Unit");
        String id = validator.readString("  Unit ID to delete: ");
        if (validator.readConfirmation("Are you sure you want to delete unit '" + id + "'?")) {
            unitService.delete(id);
        } else {
            MenuHelper.displayInfo("Deletion cancelled.");
        }
    }

    // ==================== REGISTRATION MANAGEMENT ====================

    private static void manageRegistrations() {
        while (true) {
            int choice = getSubmenuChoice("Registration Management",
                "New Registration", "Display All", "Search by ID", "Update Registration", "Delete Registration", "Back to Main Menu");
            if (choice == 6) return;
            try {
                switch (choice) {
                    case 1: addRegistration(); break;
                    case 2: registrationService.displayAll(); break;
                    case 3: searchRegistration(); break;
                    case 4: updateRegistration(); break;
                    case 5: deleteRegistration(); break;
                }
            } catch (RegistrationException | InvalidDataException e) {
                MenuHelper.displayError(e.getMessage());
            }
        }
    }

    private static void addRegistration() throws RegistrationException {
        MenuHelper.displayHeader("Create New Registration");
        String regId = validator.readString("  Registration ID: ");
        String applicantId = validator.readString("  Applicant ID: ");
        String unitId = validator.readString("  Unit ID: ");

        if (applicantService.searchById(applicantId) == null) {
            throw new RegistrationException("Applicant '" + applicantId + "' does not exist.");
        }

        Unit unit = unitService.searchById(unitId);
        if (unit == null) {
            throw new RegistrationException("Unit '" + unitId + "' does not exist.");
        }
        if (unit.getStatus() != UnitStatus.AVAILABLE) {
            throw new RegistrationException("Unit '" + unit.getUnitNumber() + "' is not available for registration.");
        }

        boolean registrationExists = registrationService.getAll().stream()
                .anyMatch(reg -> reg.getApplicantId().equalsIgnoreCase(applicantId)
                        && reg.getUnitId().equalsIgnoreCase(unitId));
        if (registrationExists) {
            throw new RegistrationException("Applicant is already registered for this unit.");
        }

        registrationService.add(new Registration(regId, applicantId, unitId, LocalDate.now().toString(), RegistrationStatus.APPROVED));

        unit.setStatus(UnitStatus.RESERVED);
        unitService.update(unit);
    }

    private static void searchRegistration() {
        String id = validator.readString("  Registration ID to search: ");
        Registration found = registrationService.searchById(id);
        System.out.println(found != null ? found.displayInfo() : "  [INFO] No registration found with ID: " + id);
    }

    private static void updateRegistration() {
        MenuHelper.displayHeader("Update Registration");
        String id = validator.readString("  Registration ID to update: ");
        Registration existing = registrationService.searchById(id);
        if (existing == null) {
            throw new InvalidDataException("Registration with ID '" + id + "' not found.");
        }

        System.out.println("  Current details:\n" + existing.displayInfo());

        String newApplicantId = validator.readStringOptional("  New Applicant ID [" + existing.getApplicantId() + "]: ", existing.getApplicantId());
        String newUnitId = validator.readStringOptional("  New Unit ID [" + existing.getUnitId() + "]: ", existing.getUnitId());

        if (!newApplicantId.equalsIgnoreCase(existing.getApplicantId())) {
            if (applicantService.searchById(newApplicantId) == null) {
                throw new InvalidDataException("Applicant '" + newApplicantId + "' does not exist.");
            }
            existing.setApplicantId(newApplicantId);
        }

        if (!newUnitId.equalsIgnoreCase(existing.getUnitId())) {
            Unit newUnit = unitService.searchById(newUnitId);
            if (newUnit == null) {
                throw new InvalidDataException("Unit '" + newUnitId + "' does not exist.");
            }
            if (newUnit.getStatus() != UnitStatus.AVAILABLE) {
                throw new InvalidDataException("Unit '" + newUnit.getUnitNumber() + "' is not available for registration.");
            }
            Unit oldUnit = unitService.searchById(existing.getUnitId());
            if (oldUnit != null) {
                oldUnit.setStatus(UnitStatus.AVAILABLE);
                unitService.update(oldUnit);
            }
            newUnit.setStatus(UnitStatus.RESERVED);
            unitService.update(newUnit);
            existing.setUnitId(newUnitId);
        }

        System.out.println("  \n  Available Registration Statuses:");
        RegistrationStatus[] statuses = RegistrationStatus.values();
        for (int i = 0; i < statuses.length; i++) {
            System.out.println("    " + (i + 1) + ". " + statuses[i].getDescription() + (statuses[i] == existing.getStatus() ? " (Current)" : ""));
        }
        int statusChoice = validator.readChoiceOptional("  Select Status [Current]: ", 1, statuses.length, -1);
        if (statusChoice != -1) {
            existing.setStatus(statuses[statusChoice - 1]);
        }

        registrationService.update(existing);
        MenuHelper.displaySuccess("Registration has been updated successfully.");
    }

    private static void deleteRegistration() {
        MenuHelper.displayHeader("Delete Registration");
        String id = validator.readString("  Registration ID to delete: ");

        Registration existing = registrationService.searchById(id);
        if (existing == null) {
            throw new InvalidDataException("Registration with ID '" + id + "' not found.");
        }

        if (validator.readConfirmation("Are you sure you want to delete registration '" + id + "'?")) {
            Unit unit = unitService.searchById(existing.getUnitId());
            if (unit != null && unit.getStatus() == UnitStatus.RESERVED) {
                unit.setStatus(UnitStatus.AVAILABLE);
                unitService.update(unit);
            }
            registrationService.delete(id);
        } else {
            MenuHelper.displayInfo("Deletion cancelled.");
        }
    }

    // ==================== LOTTERY OPERATIONS ====================

    private static void conductLotteryDraw() {
        try {
            MenuHelper.displayHeader("Conduct Lottery Draw");
            unitService.displayAll();
            String unitId = validator.readString("  Unit ID for lottery draw: ");

            Unit unit = unitService.searchById(unitId);
            if (unit == null) {
                MenuHelper.displayError("Unit '" + unitId + "' does not exist.");
                return;
            }

            List<Registration> approvedRegistrations = registrationService.getApprovedRegistrationsByUnit(unitId);
            lotteryService.conductDraw(unit, approvedRegistrations, registrationService, unitService);
        } catch (RegistrationException e) {
            MenuHelper.displayError(e.getMessage());
        }
    }

    // ==================== POLYMORPHISM DEMONSTRATION ====================

    private static void demonstratePolymorphism() {
        MenuHelper.displayHeader("Polymorphism & Dynamic Binding Demo");
        System.out.println("  Creating objects of different types and demonstrating dynamic binding...\n");

        List<Person> people = new ArrayList<>();
        people.add(new Applicant("APP001", "Abebe Kebede", "0911223344",
                "abebe@email.com", "Addis Ababa", "2025-01-15"));
        people.add(new Applicant("APP002", "Tigist Hailu", "0922334455",
                "tigist@email.com", "Dire Dawa", "2025-02-20"));
        people.add(new Admin("ADM001", "Yohannes Taye", "0933445566",
                "System Manager", "IT Department"));
        people.add(new Admin("ADM002", "Sara Mekonnen", "0944556677",
                "Lottery Coordinator", "Housing Department"));

        System.out.println("  Calling displayInfo() on each object...");
        System.out.println("  (The correct method is called at runtime based on object type)\n");

        for (Person person : people) {
            System.out.println("  📋 Object: " + person.getClass().getSimpleName());
            System.out.println(person.displayInfo());
            System.out.println();
        }

        System.out.println("  ✓ Demo Complete!");
        System.out.println("    Same method, different outputs — this is polymorphism!\n");
    }
}
