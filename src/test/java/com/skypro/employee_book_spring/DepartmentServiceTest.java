package com.skypro.employee_book_spring;

import com.skypro.employee_book_spring.model.Employee;
import com.skypro.employee_book_spring.service.DepartmentService;
import com.skypro.employee_book_spring.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private DepartmentService departmentService;

    private List<Employee> actualListEmployees;

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("Olga", "Ivanova", 1, 15000);
        Employee employee2 = new Employee("Inna", "Frolova", 2, 17000);
        Employee employee3 = new Employee("Irina", "Kotova", 2, 23000);
        Employee employee4 = new Employee("Ivan", "Lebedev", 1, 17000);
        Employee employee5 = new Employee("Roman", "Iakovlev", 3, 19000);

        actualListEmployees = new ArrayList<>(List.of(employee1, employee2, employee3, employee4, employee5));

        when(employeeServiceMock.getAllEmployees()).thenReturn(actualListEmployees);

    }

    @Test
    public void shouldReturnEmployeesByDepartment() {
        int department = 1;
        List<Employee> expected = departmentService.getEmployeesByDepartment(department);
        List<Employee> actual = actualListEmployees.stream().filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnSumSalaryByDepartment() {
        int department = 1;
        int expected = departmentService.sumSalaryByDepartment(department);
        int actual = actualListEmployees.stream().filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList()).stream().mapToInt(Employee::getSalary).sum();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMinSalaryByDepartment() {
        int department = 1;
        int expected = departmentService.minSalaryByDepartment(department);
        int actual = actualListEmployees.stream().filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList()).stream().mapToInt(Employee::getSalary).min().orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnMaxSalaryByDepartment() {
        int department = 1;
        int expected = departmentService.maxSalaryByDepartment(department);
        int actual = actualListEmployees.stream().filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList()).stream().mapToInt(Employee::getSalary).max().orElseThrow();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnAllEmployeesByAllDepartments() {
        Map<Integer, List<Employee>> expected = departmentService.getAllEmployeesByDepartments();
        Map<Integer, List<Employee>> actual = actualListEmployees.stream().map(Employee::getDepartment)
                .collect(Collectors.toSet()).stream().collect(Collectors.toMap(departments -> departments, dept -> actualListEmployees
                        .stream().filter(employee -> employee.getDepartment() == dept).collect(Collectors.toList())));
        assertEquals(expected, actual);
    }

}
