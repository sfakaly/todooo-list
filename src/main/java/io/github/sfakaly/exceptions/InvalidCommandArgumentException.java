package io.github.sfakaly.exceptions;

public class InvalidCommandArgumentException extends RuntimeException {
    public InvalidCommandArgumentException(String message) {
        super(message);
    }
}
