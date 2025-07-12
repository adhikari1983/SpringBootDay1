package com.myapp.entity;
/**
 * this EmployeeEntity is used to create the table & it will be used for the purpose of populating data from
 * DAO layer to service layer or vice-versa
 */

import javax.persistence.*;
import java.sql.Timestamp;

@Entity //table creation
@Table(name = "Demo_Table") // optional to rename the table as Demo_Table
public class EmployeeEntity {
    // these 5 attribute will be inserted as the table's column's name at the time of its creation
    private int employeeId;
    private String employeeName;
    private int salary;
    private String emailId;
    private String password;
    private Timestamp dateOfEntry;

    public EmployeeEntity() {
        super();
    }

    public EmployeeEntity(int employeeId, String employeeName, int salary, String emailId, String password, Timestamp dateOfEntry) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.salary = salary;
        this.emailId = emailId;
        this.password = password;
        this.dateOfEntry = dateOfEntry;
    }

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.AUTO) //PK ids will be generated automatically
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

    @Column(unique = true)
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
        return "EmployeeEntity{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", salary=" + salary +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
                ", dateOfEntry=" + dateOfEntry +
                '}';
    }
}
