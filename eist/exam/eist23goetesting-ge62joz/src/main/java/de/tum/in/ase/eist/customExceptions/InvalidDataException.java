package de.tum.in.ase.eist.customExceptions;

public class InvalidDataException extends RuntimeException {
    public InvalidDataException(String errorMessage) {
        super(errorMessage);
    }
}
