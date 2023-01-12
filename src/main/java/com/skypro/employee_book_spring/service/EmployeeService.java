package com.skypro.employee_book_spring.service;

import com.skypro.employee_book_spring.exception.EmployeeDataException;
import com.skypro.employee_book_spring.model.Employee;
import com.skypro.employee_book_spring.record.EmployeeRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private Map<Integer, Employee> employeeMap = new HashMap<>();

    public Collection<Employee> getAllEmployees(){
        return this.employeeMap.values();
    }


    public Employee addEmployee(EmployeeRequest employeeRequest)throws EmployeeDataException {
        checkDateEmployee(employeeRequest);
        String capitalizeFirstName = StringUtils.capitalize(employeeRequest.getFirstName());
        String capitalizeLastName = StringUtils.capitalize(employeeRequest.getLastName());
        Employee employee = new Employee(capitalizeFirstName, capitalizeLastName, employeeRequest.getDepartment(), employeeRequest.getSalary());
            this.employeeMap.put(employee.getId(), employee);
            return employee;
    }

    private void checkDateEmployee(EmployeeRequest employeeRequest)throws EmployeeDataException{
        boolean checkFirstName = StringUtils.isAlpha(employeeRequest.getFirstName());
        boolean checkLastName = StringUtils.isAlpha(employeeRequest.getLastName());
        if (!checkFirstName||!checkLastName) {
            throw new EmployeeDataException ("Неверно введены данные сотрудника");
        }
    }

    public Integer getSalarySum() {
        return employeeMap.values().stream()
                .mapToInt(Employee::getSalary)
                .sum();
    }

    public Employee getEmployeeMinSalary() {
        return employeeMap.values().stream()
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }


    public Employee getEmployeeMaxSalary() {
        return employeeMap.values().stream()
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }


    public List<Employee> getEmployeeHighSalary() {
        double averageSalary = employeeMap.values().stream()
                .mapToInt(Employee::getSalary).average().getAsDouble();
        return employeeMap.values().stream().filter(employee -> employee.getSalary()>averageSalary).toList();
    }

}
