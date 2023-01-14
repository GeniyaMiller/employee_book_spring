package com.skypro.employee_book_spring.record;

import com.skypro.employee_book_spring.exception.EmployeeDataException;
import com.skypro.employee_book_spring.model.Employee;
import org.apache.commons.lang3.StringUtils;

public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private int department;
    private int salary;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
