package com.skypro.employee_book_spring.service;

import com.skypro.employee_book_spring.model.Employee;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;


    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public List<Employee> getEmployeesByDepartment(int department) {
        return this.employeeService.getAllEmployees().stream().filter(employee -> employee.getDepartment()==department).collect(Collectors.toList());
    }

    public int sumSalaryByDepartment(int department) {
        return getEmployeesByDepartment(department).stream().mapToInt(Employee::getSalary).sum();
    }

    public int maxSalaryByDepartment(int department) {
        return getEmployeesByDepartment(department).stream().mapToInt(Employee::getSalary).max().orElseThrow();
    }

    public int minSalaryByDepartment(int department) {
        return getEmployeesByDepartment(department).stream().mapToInt(Employee::getSalary).min().orElseThrow();
    }

    public Set<Integer> getExistingDepartment(){
        return this.employeeService.getAllEmployees().stream().mapToInt(Employee::getDepartment).collect(Collectors.toSet());
    }

    public Map<Integer, List<Employee>> getAllEmployeesByDepartments() {
        return getExistingDepartment().stream().collect(Collectors.toMap(departments->departments, this::getEmployeesByDepartment));
    }


}
