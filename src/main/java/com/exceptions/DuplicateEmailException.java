package com.exceptions;

/**      ✅ How @ControllerAdvice and DuplicateEmailException are connected
 Even though your GlobalExceptionHandler class does not extend any specific superclass, it is still wired into Spring’s
 exception-handling mechanism via the @ControllerAdvice annotation.

 🔗 Connection:
 @ControllerAdvice makes the class a global interceptor for exceptions thrown in controller methods.

 Inside the GlobalExceptionHandler, you define @ExceptionHandler methods to catch specific exception types, like
 DuplicateEmailException.
 When a controller (like your @PostMapping("/registerEmployee")) throws a DuplicateEmailException, Spring automatically
 scans for a matching handler method in any class annotated with @ControllerAdvice.

 📌 Summary:
 So the connection is annotation-driven, not inheritance-based. The @ControllerAdvice annotation is what enables Spring
 to call your handler method for DuplicateEmailException.

 */
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
