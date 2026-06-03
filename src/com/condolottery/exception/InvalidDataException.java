package com.condolottery.exception;

/**
 * Custom unchecked exception thrown when input data is invalid or malformed.
 * Examples: empty required fields, invalid ID format, negative prices.
 */
public class InvalidDataException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InvalidDataException with the specified message.
     * @param message the detail message
     */
    public InvalidDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidDataException with a message and cause.
     * @param message the detail message
     * @param cause the cause of this exception
     */
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
