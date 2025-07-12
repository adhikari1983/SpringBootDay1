package com.myapp.controllers;

import com.myapp.dto.EmployeeDTO;
import com.myapp.service.EmployeeService;
import com.utility.ApplicationMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class RestControllerv1 {

    @Autowired
    private EmployeeService employeeService;

    /// Exposing the all RESTAPIs end points.

    /// SpringApplication is a special method called by URI( url + endPoint ). That's why it is called the resource.
    /// Resource with conceptual entity mapped with unique URI & represents the data.
    /// It can be any kind of content like text, file, image, videos, etc.
    //fetch all employees
    @GetMapping("/allEmployees") //localhost:444/v1/allEmployees
    @ResponseStatus(code = HttpStatus.OK)
    public List<EmployeeDTO> findAllEmployees() {
        return employeeService.findAllEmployees();
    }

    // fetch a particular employee
    @GetMapping("/findById") //localhost:444/v1/findById
    //@GetMapping("/findById/{employeeId}")
    @ResponseStatus(code = HttpStatus.OK)
    public EmployeeDTO findEmployee(@RequestParam("employeeId") int employeeId) {
        //public EmployeeDTO findEmployeeById(@PathVariable("employeeId") int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }


    /**
     * Registers a new employee by receiving data in JSON format through a POST request.
     *
     * <p>This method handles POST requests sent to {@code /registerEmployee}.
     * It uses the {@link RequestBody} annotation to read the request body,
     * which is expected to contain employee data in JSON format. Spring
     * automatically converts this JSON into an {@link EmployeeDTO} object using
     * the Jackson library.</p>
     *
     * <p>The method then calls the service layer’s {@code registerEmp()} method
     * to save the employee details into the database.</p>
     *
     * <p>If this method returned an object (instead of void), Spring would
     * convert the returned object back into JSON and send it in the HTTP response.
     * This is useful for tools like Postman or frontend frameworks like Angular and React.
     *
     * @param employeeDTO the employee data sent in the request body
     * @see org.springframework.web.bind.annotation.PostMapping
     * @see org.springframework.web.bind.annotation.RequestBody
     * // @RequestBody is used in REST APIs to read the data coming from the request body (or request payload)
     * // The data is usually in JSON (or XML) format, and Spring automatically converts it into the
     * // EmployeeDTO object. This method registerEmployee() will be called when a POST request is sent to:
     * // http://localhost:444/registerEmployee
     */
   /* @PostMapping("/registerEmployee") [{ ... THIS METHOD WORKS ONLY IF WE ARE SENDING THE REQUEST W/EMPLOYEE ID AS WELL ... }]
    public ApplicationMessage registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        // Send the data to the service layer to save it into the database
        employeeService.registerEmp(employeeDTO);

        // If this method returned an object instead of void,
        // Spring would convert that object (employeeDTO)  back into JSON using Jackson Mapper
        // and send it as a response (viewable in Postman, Angular, React, etc.)
        //but here were are sending the message as application message to postman
        ApplicationMessage responseMessage = new ApplicationMessage();
        responseMessage.setCode(200);
        responseMessage.setMessage("Employee updated successfully");
        return  responseMessage;
    }*/
    @PostMapping("/registerEmployee") //localhost:444/v1/registerEmployee
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeDTO registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.registerEmp(employeeDTO);
    }

    @PutMapping("/updateOneEmployee") //localhost:444/v1/updateOneEmployee
    @ResponseStatus(HttpStatus.OK)
    public ApplicationMessage updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.registerEmpAfterUpdate(employeeDTO);
        ApplicationMessage responseMessage = new ApplicationMessage();
        responseMessage.setCode(200);
        responseMessage.setMessage("Employee updated successfully!!!...");
        return responseMessage;
    }
    // in case of returning the message with updated employee info
    //public EmployeeDTO updateEmployee(@RequestBody EmployeeDTO employeeDTO) {
    //    return employeeService.updateEmployeeRecord(employeeDTO); }



    /**
     * Deletes the employee record with the specified ID.
     *
     * <p>This method handles HTTP DELETE requests and supports two ways to pass the employee ID:</p>
     *
     * <p><b>Using {@link RequestParam}:</b><br>
     * The ID is extracted from a query parameter in the URL, such as
     * {@code /deleteEmployee?employeeId=101}.
     * and binds with the method parameter</p>
     *
     * <p><b>Using {@link PathVariable}:</b><br>
     * The ID is passed as a part of the URL path, such as
     * {@code /deleteEmployee/101}. It is mapped to the method parameter using
     * {@code @PathVariable("emid")}.</p>
     *
     * <p>Once the ID is retrieved, the method calls the service layer's
     * {@code deleteEmployeeRecord()} method to remove the employee from the database.</p>
     *
     * <p>This approach is commonly used in RESTful APIs, where resources can be deleted by
     * specifying their unique identifier either as a query parameter or as a path variable.</p>
     *
     * @param employeeId the ID of the employee to delete
     * @return EmployeeDTO(responseMessage)
     * Your Initial Confusion:
     * You thought:
     * "If I'm returning EmployeeDTO, it must be coming back from the service layer or DB."
     * <p>
     * But in reality:
     * I'm in the controller layer, and I'm just building a new response object manually — no DB involved.
     * <p>
     * What's Really Happening:
     * You're returning an EmployeeDTO object from the controller, not from the DB.
     * <p>
     * You're using it as a plain response wrapper — not because you're fetching employee data,
     * but just because you need a way to send a success message back to the client (Postman).
     * @see org.springframework.web.bind.annotation.DeleteMapping
     * @see org.springframework.web.bind.annotation.RequestParam
     * @see org.springframework.web.bind.annotation.PathVariable
     */
    @DeleteMapping("/deleteOneEmployee") //localhost:444/v1/deleteOneEmployee
    @ResponseStatus(code = HttpStatus.OK)
    public ApplicationMessage deleteEmployee(@RequestParam("employeeId") int employeeId) {
        // @DeleteMapping("/deleteEmployee/{emid}")
        // public void deleteEmployee(@PathVariable("emid") int employeeId) {
        employeeService.deleteEmployeeById(employeeId);                    // Delete from DB
        ApplicationMessage responseMessage = new ApplicationMessage();     // NOT from DB
        responseMessage.setCode(200);
        responseMessage.setMessage("Employee deleted successfully");       // Set message to,
        return responseMessage;                                            // Sent as JSON to Postman
    }

    @PatchMapping("/partialUpdate")//localhost:444/v1/partialUpdate
    @ResponseStatus(HttpStatus.OK)
    public ApplicationMessage patchEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.registerEmpAfterUpdate(employeeDTO);
        ApplicationMessage responseMessage = new ApplicationMessage();
        responseMessage.setCode(200);
        responseMessage.setMessage("Employee partially updated successfully");
        return responseMessage;
    }

}

