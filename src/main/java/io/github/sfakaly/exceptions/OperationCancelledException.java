package io.github.sfakaly.exceptions;

public class OperationCancelledException extends RuntimeException {
    public OperationCancelledException(String message) {
        super(message);
    }
}
