package com.skypro.employee_book_spring.service;

import com.skypro.employee_book_spring.model.Employee;
import com.skypro.employee_book_spring.record.EmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeService {

    private Map<Integer, Employee> employeeMap = new HashMap<>();

    public Collection<Employee> getAllEmployees(){
        return this.employeeMap.values();
    }

    public Employee addEmployee(EmployeeRequest employeeRequest){
        if(employeeRequest.getFirstName() == null || employeeRequest.getFirstName() == null){
            throw new IllegalArgumentException("Не указаны данные сотрудника");
        } else {
            Employee employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName(), employeeRequest.getDepartment(), employeeRequest.getSalary());
            this.employeeMap.put(employee.getId(), employee);
            return employee;
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
