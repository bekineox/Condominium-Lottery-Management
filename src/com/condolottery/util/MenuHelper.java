package com.condolottery.util;

/**
 * Helper class for displaying consistent menu interfaces.
 * Reduces code duplication and ensures uniform menu formatting.
 */
public class MenuHelper {

    private static final String BOX_TOP = "╔══════════════════════════════════════════╗";
    private static final String BOX_MID = "╠══════════════════════════════════════════╣";
    private static final String BOX_BOT = "╚══════════════════════════════════════════╝";

    public static void displayMainMenu() {
        System.out.println("\n" + BOX_TOP);
        System.out.println("║              MAIN MENU                   ║");
        System.out.println(BOX_MID);
        System.out.println("║  1. Manage Applicants                    ║");
        System.out.println("║  2. Manage Condominiums                  ║");
        System.out.println("║  3. Manage Condominium Units             ║");
        System.out.println("║  4. Manage Registrations                 ║");
        System.out.println("║  5. Conduct Lottery Draw                 ║");
        System.out.println("║  6. View Lottery Results                 ║");
        System.out.println("║  7. Demonstrate Polymorphism             ║");
        System.out.println("║  8. Exit                                 ║");
        System.out.println(BOX_BOT);
    }

    public static void displaySubMenu(String title, String... options) {
        System.out.println("\n  --- " + title + " ---");
        for (int i = 0; i < options.length; i++) {
            System.out.println("  " + (i + 1) + ". " + options[i]);
        }
    }

    public static void displayHeader(String title) {
        System.out.println("\n╔══════════════════════════════════════════════════════╗");
        System.out.println("║  " + padCenter(title, 48) + "  ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }

    public static void displaySuccess(String message) {
        System.out.println("  ✓ " + message);
    }

    public static void displayInfo(String message) {
        System.out.println("  ℹ " + message);
    }

    public static void displayError(String message) {
        System.out.println("  ✗ " + message);
    }

    public static void displayWarning(String message) {
        System.out.println("  ⚠ " + message);
    }

    private static String padCenter(String text, int width) {
        if (text.length() >= width) return text.substring(0, width);
        int leftPad = (width - text.length()) / 2;
        int rightPad = width - text.length() - leftPad;
        return " ".repeat(leftPad) + text + " ".repeat(rightPad);
    }
}
