package dev.marcosgonzalez.employeecrmapi.controller;

import dev.marcosgonzalez.employeecrmapi.dto.CreateEmployeeBody;
import dev.marcosgonzalez.employeecrmapi.model.Employee;
import dev.marcosgonzalez.employeecrmapi.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees(Authentication authentication) {
        return employeeService.getEmployees(authentication);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable String id, Authentication authentication) {
        return employeeService.getEmployee(id, authentication);
    }

    @PostMapping
    public Employee createEmployee(@RequestBody @Valid CreateEmployeeBody body, Authentication authentication) {
        return employeeService.createEmployee(body, authentication);
    }

    @PutMapping("/{id}")
    public String updateEmployee(@PathVariable String id) {
        return "UPDATE employee: " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable String id) {
        return "DELETE employee: " + id;
    }
}
