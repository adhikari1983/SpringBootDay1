package com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

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

// This class is a centralized exception handling component for all controllers.
// It handles exceptions globally and returns consistent error responses.
@ControllerAdvice
public class GlobalExceptionHandler1 {

    /// *******ExceptionHandler methods to catch specific exception types********///

    // Handles DuplicateEmailException across all controllers.
    // Returns a custom error message with a 400 Bad Request status.
    // @ WebRequest -> if you want pass any thing related to the web request
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmailException(DuplicateEmailException dee, WebRequest webRequest){

        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", dee.getMessage());
        errorDetails.put("path", webRequest.getDescription(false)); // O/P: "uri=/registerEmployee" | that caused the exception
        errorDetails.put("timestamp", LocalDateTime.now().toString());

        // just sending the custom error message -> dee.getMessage()
        // return new ResponseEntity<>(dee.getMessage(), HttpStatus.BAD_REQUEST);
        
        // Instead of just sending the custom error message (dee.getMessage()),
        // we're using WebRequest and timestamp, bundling everything into a map
        // to return a detailed structured response.
        // NOTE: there are many thing in the request body, when request is sent, so if we want to know about the web 
        // web request, this will help
        return new ResponseEntity<Map<String, String>>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
