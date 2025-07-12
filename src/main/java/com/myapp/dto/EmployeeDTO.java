package com.myapp.dto;

import java.sql.Timestamp;

//it will work for view resolver, controller and service layer.
public class EmployeeDTO {
    private int employeeId;
    private String employeeName;
    private int salary;
    private String emailId;
    private String password;
    private Timestamp dateOfEntry;

    public EmployeeDTO() {
        super();
    }

    public EmployeeDTO(int employeeId, String employeeName, int salary, String emailId, String password, Timestamp dateOfEntry) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.emailId = emailId;
        this.password = password;
        this.dateOfEntry = dateOfEntry;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(Timestamp dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", dateOfEntry=" + dateOfEntry +
                '}';
    }
}
