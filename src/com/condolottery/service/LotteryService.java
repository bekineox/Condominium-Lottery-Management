package com.condolottery.service;

import com.condolottery.enums.RegistrationStatus;
import com.condolottery.enums.UnitStatus;
import com.condolottery.exception.RegistrationException;
import com.condolottery.model.LotteryResult;
import com.condolottery.model.Registration;
import com.condolottery.model.Unit;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Service class that conducts lottery draws and manages results.
 * Demonstrates: generic List, Streams, file I/O,
 * and custom checked exception handling.
 */
public class LotteryService {

    private final List<LotteryResult> results = new ArrayList<>();
    private final Random random = new Random();
    private static final String FILE_PATH = "data/results.txt";

    /**
     * Constructs a LotteryService and loads existing results from file.
     */
    public LotteryService() {
        loadFromFile();
    }

    /**
     * Conducts a lottery draw for a specific unit.
     * Randomly selects one winner from approved registrations.
     * @param unit the unit to draw for
     * @param approvedRegistrations list of approved registrations for this unit
     * @param registrationService service to update registration statuses
     * @param unitService service to update unit status
     * @throws RegistrationException if no approved registrations exist
     */
    public void conductDraw(Unit unit, List<Registration> approvedRegistrations,
                            RegistrationService registrationService,
                            UnitService unitService) throws RegistrationException {

        if (approvedRegistrations.isEmpty()) {
            throw new RegistrationException(
                "No approved registrations found for unit '" + unit.getUnitNumber() + "'.");
        }

        int winnerIndex = random.nextInt(approvedRegistrations.size());
        String drawDate = LocalDate.now().toString();
        int resultCounter = results.size();

        System.out.println("\n  ========================================");
        System.out.println("         LOTTERY DRAW IN PROGRESS...");
        System.out.println("  ========================================");
        System.out.println("  Unit: " + unit.getUnitNumber() + " (" + unit.getUnitType() + ")");
        System.out.println("  Participants: " + approvedRegistrations.size());
        System.out.println("  Draw Date: " + drawDate);
        System.out.println("  ========================================\n");

        for (int i = 0; i < approvedRegistrations.size(); i++) {
            Registration registration = approvedRegistrations.get(i);
            boolean isWinner = (i == winnerIndex);
            resultCounter++;

            LotteryResult result = new LotteryResult(
                "RES" + String.format("%04d", resultCounter),
                registration.getRegistrationId(),
                registration.getApplicantId(),
                unit.getUnitId(),
                drawDate,
                isWinner
            );
            results.add(result);

            registration.setStatus(isWinner ? RegistrationStatus.WON : RegistrationStatus.LOST);
            registrationService.update(registration);

            if (isWinner) {
                System.out.println("  *** WINNER: Applicant ID " + registration.getApplicantId()
                    + " (Registration: " + registration.getRegistrationId() + ") ***");
            }
        }

        unit.setStatus(UnitStatus.SOLD);
        unitService.update(unit);

        saveToFile();
        System.out.println("\n  [SUCCESS] Lottery draw completed for unit: " + unit.getUnitNumber());
    }

    /**
     * Displays all lottery results.
     */
    public void displayAllResults() {
        if (results.isEmpty()) {
            System.out.println("  [INFO] No lottery results yet.");
            return;
        }
        System.out.println("\n  ======= ALL LOTTERY RESULTS (" + results.size() + ") =======\n");
        results.forEach(result -> System.out.println(result.displayInfo()));
    }

    /**
     * Displays only the winning results.
     * Demonstrates: Streams and lambda expressions.
     */
    public void displayWinners() {
        List<LotteryResult> winners = results.stream()
                .filter(LotteryResult::isWinner)
                .collect(Collectors.toList());

        if (winners.isEmpty()) {
            System.out.println("  [INFO] No winners yet.");
            return;
        }
        System.out.println("\n  ======= LOTTERY WINNERS (" + winners.size() + ") =======\n");
        winners.forEach(winner -> System.out.println(winner.displayInfo()));
    }

    private void loadFromFile() {
        Path path = Paths.get(FILE_PATH);
        if (!Files.exists(path)) return;
        try {
            for (String line : Files.readAllLines(path)) {
                if (line.trim().isEmpty()) continue;
                try {
                    String[] parts = line.split("\\|");
                    results.add(new LotteryResult(
                        parts[0],
                        parts[1],
                        parts[2],
                        parts[3],
                        parts[4],
                        Boolean.parseBoolean(parts[5])
                    ));
                } catch (Exception e) {
                    System.err.println("  [WARNING] Skipping malformed line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to read results file: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try {
            Files.createDirectories(Paths.get("data"));
            List<String> lines = new ArrayList<>();
            for (LotteryResult result : results) {
                lines.add(String.join("|",
                    result.getResultId(),
                    result.getRegistrationId(),
                    result.getApplicantId(),
                    result.getUnitId(),
                    result.getDrawDate(),
                    String.valueOf(result.isWinner())
                ));
            }
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException e) {
            System.err.println("  [ERROR] Failed to write results file: " + e.getMessage());
        }
    }
}
