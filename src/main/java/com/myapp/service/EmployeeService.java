package com.myapp.service;

import com.myapp.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO registerEmp(EmployeeDTO employeeDTO);

    EmployeeDTO authenticateUser(String emailId, String password);

    EmployeeDTO findEmployeeById(int employeeId);

    List<EmployeeDTO> findAllEmployees();

    void deleteEmployeeById(int employeeId);

    void registerEmpAfterUpdate(EmployeeDTO employeeDTO);



    /// In hibernate UPDATE & SAVE both are same so we can make the same call on them,
    /// but I have created 2-METHODS,
    //     EmployeeDTO registerEmp(EmployeeDTO employeeDTO); -> for save purpose only
    //     void registerEmpAfterUpdate(EmployeeDTO employeeDTO); -> for update purpose only
    /// REASON:
    /// I have added the timestamp before saved & returned the same object back to display on
    /// postman while doing the saving operation
    /// Also, as  lose coupling methods, in case if I want to modify with some other
    /// additional condition in future

}

