package com.condolottery.util;

import java.util.Scanner;

/**
 * Helper class for input validation and user prompting.
 * Provides safe, user-friendly input methods with validation.
 */
public class InputValidator {

    private final Scanner scanner;

    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readString(String prompt) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            MenuHelper.displayWarning("Input cannot be empty. Please try again.");
        }
    }

    public String readStringOptional(String prompt, String defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? defaultValue : input;
    }

    public int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    MenuHelper.displayWarning("Please enter a valid number.");
                    continue;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                MenuHelper.displayWarning("Invalid input. Please enter a whole number.");
            }
        }
    }

    public double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    MenuHelper.displayWarning("Please enter a valid number.");
                    continue;
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                MenuHelper.displayWarning("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    public int readChoice(int minChoice, int maxChoice) {
        while (true) {
            try {
                System.out.print("  Enter your choice (" + minChoice + "-" + maxChoice + "): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    MenuHelper.displayWarning("Please enter a number between " + minChoice + " and " + maxChoice + ".");
                    continue;
                }
                int choice = Integer.parseInt(input);
                if (choice >= minChoice && choice <= maxChoice) {
                    return choice;
                }
                MenuHelper.displayWarning("Please enter a number between " + minChoice + " and " + maxChoice + ".");
            } catch (NumberFormatException e) {
                MenuHelper.displayWarning("Invalid input. Please enter a number.");
            }
        }
    }

    public boolean readConfirmation(String message) {
        System.out.print("  " + message + " (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        return input.equals("yes") || input.equals("y");
    }

    public int readIntOptional(String prompt, int defaultValue) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return defaultValue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                MenuHelper.displayWarning("Invalid input. Please enter a whole number.");
            }
        }
    }

    public double readDoubleOptional(String prompt, double defaultValue) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return defaultValue;
            }
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                MenuHelper.displayWarning("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    public int readChoiceOptional(String prompt, int minChoice, int maxChoice, int defaultChoice) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return defaultChoice;
            }
            try {
                int choice = Integer.parseInt(input);
                if (choice >= minChoice && choice <= maxChoice) {
                    return choice;
                }
                MenuHelper.displayWarning("Please enter a number between " + minChoice + " and " + maxChoice + ".");
            } catch (NumberFormatException e) {
                MenuHelper.displayWarning("Invalid input. Please enter a number.");
            }
        }
    }
}

