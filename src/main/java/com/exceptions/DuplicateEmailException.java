package com.exceptions;

// Custom Exception class
public class DuplicateEmailException extends RuntimeException{

    // Constructor that passes the custom error message to the base RuntimeException class
    // In this case here :
    // Initializes DuplicateEmailException with a specific error message,
    // which will be returned in the response when this exception is thrown,
    // for entering the invalid email ID by the user
    public DuplicateEmailException(String message) {
        super(message);
    }

}
