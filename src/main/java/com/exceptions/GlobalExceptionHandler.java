package com.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for all controllers.
 *
 * <p>This class is annotated with {@link ControllerAdvice}, which makes it a centralized
 * exception handling component that applies to all {@code @Controller} and {@code @RestController}
 * classes in the application.</p>
 *
 * <p>It ensures consistent and well-structured error responses across the application by
 * catching and handling specific exceptions in one place.</p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    
    /// *******ExceptionHandler methods to catch specific exception types********///

    /**
     * Handles {@link DuplicateEmailException} thrown by any controller method.
     *
     * <p>This method is triggered automatically when a {@code DuplicateEmailException}
     * is thrown anywhere in the application. It uses {@link ExceptionHandler} to map this
     * exception type.</p>
     *
     * <p>It constructs a detailed error response containing:</p>
     * <ul>
     *     <li>The error message from the exception</li>
     *     <li>The path of the web request that caused the exception (from {@link WebRequest})</li>
     *     <li>The current timestamp</li>
     * </ul>
     *
     * <p>This structured response is returned with HTTP status 400 (Bad Request).</p>
     *
     * @param dee the caught DuplicateEmailException
     * @param webRequest the web request that caused the exception, also gives access to request-specific information 
     * from request body in general. e.g. Using getDescription(false) provides the URI of the request without the client address.
     * @return a {@link ResponseEntity} with a map of error details and HTTP 400 status
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEmailException(
            DuplicateEmailException dee, WebRequest webRequest) {

        // Created a map to store structured error details
        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", dee.getMessage()); // Custom exception message
        errorDetails.put("path", webRequest.getDescription(false)); // e.g., "uri=/registerEmployee"
        errorDetails.put("timestamp", LocalDateTime.now().toString()); // Current time

        // Instead of returning just the message, we bundle extra info (path and timestamp)
        // This provides a clearer and more useful error response to the client
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    

    @ExceptionHandler(NegativeIdException.class)
    public ResponseEntity<Map<String, String>> handleNegativeIdException(
            NegativeIdException nie, WebRequest webRequest) {

        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", nie.getMessage()); 
        errorDetails.put("path", webRequest.getDescription(false)); 
        errorDetails.put("timestamp", LocalDateTime.now().toString()); 

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEmployeeNotFoundException(
            EmployeeNotFoundException enf, WebRequest webRequest) {

        Map<String, String> errorDetails = new HashMap<>();
        errorDetails.put("error", enf.getMessage()); 
        errorDetails.put("path", webRequest.getDescription(false)); 
        errorDetails.put("timestamp", LocalDateTime.now().toString()); 

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAnyGenericException(Exception e) {

        return new ResponseEntity<>("Some other issues", HttpStatus.INTERNAL_SERVER_ERROR);
    }


}