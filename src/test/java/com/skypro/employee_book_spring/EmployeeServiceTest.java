package com.skypro.employee_book_spring;

import com.skypro.employee_book_spring.exception.EmployeeDataException;
import com.skypro.employee_book_spring.model.Employee;
import com.skypro.employee_book_spring.record.EmployeeRequest;
import com.skypro.employee_book_spring.service.EmployeeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    private EmployeeService employeeService = new EmployeeService();

    private List<Employee> actualListEmployees;

    @BeforeEach
    public void setUp() {
        Employee employee1 = new Employee("Olga", "Ivanova", 1, 15000);
        Employee employee2 = new Employee("Inna", "Frolova", 2, 17000);
        Employee employee3 = new Employee("Irina", "Kotova", 2, 23000);
        Employee employee4 = new Employee("Ivan", "Lebedev", 1, 17000);
        Employee employee5 = new Employee("Roman", "Iakovlev", 3, 19000);

        actualListEmployees = new ArrayList<>(List.of(employee1, employee2, employee3, employee4, employee5));
        Map<Integer, Employee> testMap = new HashMap<>();
        testMap.put(employee1.getId(), employee1);
        testMap.put(employee2.getId(), employee2);
        testMap.put(employee3.getId(), employee3);
        testMap.put(employee4.getId(), employee4);
        testMap.put(employee5.getId(), employee5);

        employeeService.setEmployeeMap(testMap);

    }

    @Test
    public void shouldReturnListOfEmployeesWhenGetAllEmployees() {
        List<Employee> expected = employeeService.getAllEmployees();
        assertEquals(expected, actualListEmployees);

    }

    @Test
    public void shouldReturnSalarySum() {
        Integer expected = employeeService.getSalarySum();
        Integer actual = actualListEmployees.stream().mapToInt(Employee::getSalary).sum();
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmployeeWithMinSalary() {
        Employee expected = employeeService.getEmployeeMinSalary();
        Employee actual = actualListEmployees.stream().min(Comparator.comparingInt(Employee::getSalary)).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmployeeWithMaxSalary() {
        Employee expected = employeeService.getEmployeeMaxSalary();
        Employee actual = actualListEmployees.stream().max(Comparator.comparingInt(Employee::getSalary)).orElse(null);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldReturnEmployeeWithHighSalary() {
        List<Employee> expected = employeeService.getEmployeeHighSalary();
        List<Employee> actual = actualListEmployees.stream().filter(employee -> employee.getSalary() > actualListEmployees.stream().mapToInt(Employee::getSalary).average().getAsDouble()).toList();
        assertEquals(expected, actual);
    }
}
