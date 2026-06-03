package com.condolottery.exception;

/**
 * Custom checked exception thrown when a registration operation fails.
 * Examples: duplicate registration, unit not available, applicant not found.
 */
public class RegistrationException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new RegistrationException with the specified message.
     * @param message the detail message
     */
    public RegistrationException(String message) {
        super(message);
    }

    /**
     * Constructs a new RegistrationException with a message and cause.
     * @param message the detail message
     * @param cause the cause of this exception
     */
    public RegistrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
