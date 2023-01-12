package com.skypro.employee_book_spring.controller;

import com.skypro.employee_book_spring.model.Employee;
import com.skypro.employee_book_spring.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {
  private final DepartmentService departmentService;

  public DepartmentController(DepartmentService departmentService) {
    this.departmentService = departmentService;
  }

  @GetMapping("/employees")
  public List<Employee> getEmployeesByDepartment (int department){
    return this.departmentService.getEmployeesByDepartment(department);
  }


  @GetMapping("/{department}/salary/sum")
  @ResponseBody
  public int sumSalaryByDepartment(@PathVariable int department){
     return this.departmentService.sumSalaryByDepartment(department);
  }

  @GetMapping("/{department}/salary/max")
  @ResponseBody
  public int maxSalaryByDepartment(@PathVariable int department){
     return this.departmentService.maxSalaryByDepartment(department);
  }

  @GetMapping("/{department}/salary/min")
  @ResponseBody
  public int minSalaryByDepartment(@PathVariable int department){
    return this.departmentService.minSalaryByDepartment(department);
  }
  @GetMapping("/employees/mapDepartment")
  public Map<Integer, List<Employee>> getAllEmployeesByDepartments(){
    return this.departmentService.getAllEmployeesByDepartments();
  }

}
