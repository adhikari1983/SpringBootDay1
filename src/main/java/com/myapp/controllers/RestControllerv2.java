package com.myapp.controllers;

import com.myapp.dto.EmployeeDTO;
import com.myapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2")
public class RestControllerv2 {

    @Autowired
    private EmployeeService employeeService;

    // Following the Industry standard, ideally what is supposed to be done to follow & satisfy
    // the requirement protocol as well as the end user comfort of usability of the application.


    // Why is the return type ResponseEntity<?> instead of ResponseEntity<EmployeeDTO>?
    // Because the method can return different types of responses at runtime:
    // - If the employee is found, it returns an EmployeeDTO object.
    // - If not, it returns a String message ("Employee does not exist").
    // Using the wildcard <?> in ResponseEntity<?> allows us to handle both return types
    // without compromising type safety, making the method flexible for varying response bodies.
    @GetMapping("/findById/{employeeId}")
    public ResponseEntity<?> findEmployee(@PathVariable("employeeId") int employeeId) {
        EmployeeDTO employeeDTO = employeeService.findEmployeeById(employeeId);
        if (employeeDTO != null) {
            // return employeeDTO;
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Employee does not exist", HttpStatus.OK);
        }
    }

    //fetch all employees
    @GetMapping("/allEmployees")
    public ResponseEntity<List<EmployeeDTO>> findAllEmployees() {
        return new ResponseEntity<>(employeeService.findAllEmployees(), HttpStatus.OK);
    }

    //create an employee
    @PostMapping("/registerEmployee")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO employeeDTO) {
        try {
            employeeService.registerEmp(employeeDTO);
            return new ResponseEntity<>("Employee has been registered successfully!!!...", HttpStatus.CREATED);
        } catch (DataIntegrityViolationException eee) {
            return new ResponseEntity<>("Duplicate email IDs are not allowed plz provide unique email ID", HttpStatus.OK);
        }

    }

    //update an employee
    @PutMapping("/updateOneEmployee")
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDTO employeeDTO) {

        EmployeeDTO employeeById = employeeService.findEmployeeById(employeeDTO.getEmployeeId());

        if (employeeById != null) {
            employeeService.registerEmpAfterUpdate(employeeDTO);
            return new ResponseEntity<>("Employee updated successfully!!!...", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Employee not found!!!...", HttpStatus.NOT_FOUND);
        }
    }

    //delete an employee
    @DeleteMapping("/deleteOneEmployee")
    public ResponseEntity<String> deleteEmployee(@RequestParam("employeeId") int employeeId) {
        try {
            employeeService.deleteEmployeeById(employeeId);
            return new ResponseEntity<>("Employee deleted successfully!!!...", HttpStatus.OK);
        } catch (EmptyResultDataAccessException ee) {
            return new ResponseEntity<>("Deletion issue => Employee doesn't exit!!!...", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Deletion issue => due to some unexpected problem!!!...", HttpStatus.OK);
        }
    }

    //Partial update an Employee
    @PatchMapping("/partialUpdate")
    public ResponseEntity<String> patchEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employeeById = employeeService.findEmployeeById(employeeDTO.getEmployeeId());
        if (employeeById != null) {
            employeeService.registerEmpAfterUpdate(employeeDTO);
            return new ResponseEntity<>("Employee updated successfully!!!...", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Employee not found!!!...", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * When you return a ResponseEntity<String> with HttpStatus.NO_CONTENT (HTTP 204), the HTTP spec says:
     * <p>
     * 204 No Content means the server successfully processed the request, but is not returning any content
     * in the response body.
     * <p>
     * So this line:
     *              return new ResponseEntity<>("Employee not found!!!...", HttpStatus.NO_CONTENT);
     * silently drops the message body "Employee not found!!!..." because 204 means "no content".
     */
}

