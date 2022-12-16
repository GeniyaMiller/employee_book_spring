package com.skypro.employee_book_spring.controller;

import com.skypro.employee_book_spring.exception.EmployeeDataException;
import com.skypro.employee_book_spring.model.Employee;
import com.skypro.employee_book_spring.record.EmployeeRequest;
import com.skypro.employee_book_spring.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public Collection<Employee> getAllEmployees(){
        return this.employeeService.getAllEmployees() ;
    }
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody EmployeeRequest employeeRequest) throws EmployeeDataException {
        return this.employeeService.addEmployee(employeeRequest);
    }
    @GetMapping("/employees/salary/sum")
    public Integer getSalarySum(){
        return this.employeeService.getSalarySum();
    }
    @GetMapping("/employee/salary/min")
    public Employee getMinSalary(){
        return this.employeeService.getEmployeeMinSalary();
    }
    @GetMapping("/employee/salary/max")
    public Employee getMaxSalary(){
        return this.employeeService.getEmployeeMaxSalary();
    }
    @GetMapping("/employee/high-salary")
    public List<Employee> getHighSalary(){
        return this.employeeService.getEmployeeHighSalary();
    }

}
