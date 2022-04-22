package com.FileValidator.exceptions;

public class InvalidFileException extends Throwable {
    public InvalidFileException() {

    }
    public InvalidFileException(String message) {
        super(message);
    }

    public InvalidFileException(String message, Throwable cause) {
        super(message, cause);
    }

}
