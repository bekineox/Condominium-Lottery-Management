package com.condolottery.util;

/**
 * Utility class for centralizing user-facing messages.
 */
public class UserMessages {

    public static String addedSuccess(String type, String name) {
        return "The " + type + " '" + name + "' has been successfully added.";
    }

    public static String updatedSuccess(String type) {
        return "The " + type + " has been successfully updated.";
    }

    public static String deletedSuccess(String type, String name) {
        return "The " + type + " '" + name + "' has been successfully deleted.";
    }

    public static String notFoundById(String type, String id) {
        return "No " + type + " found with ID: " + id;
    }

    public static String listEmpty(String type) {
        return "There are no " + type + " currently registered in the system.";
    }

    public static String alreadyExists(String type, String id) {
        return "A " + type + " with ID '" + id + "' already exists.";
    }

    public static String unitNotAvailable(String unitNumber) {
        return "Unit '" + unitNumber + "' is not available for registration.";
    }

    public static String farewell() {
        return "Thank you for using the Condominium Lottery Registration System. Goodbye!";
    }
}
