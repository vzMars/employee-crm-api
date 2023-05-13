package dev.marcosgonzalez.employeecrmapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @GetMapping
    public String getEmployees() {
        return "GET employees";
    }

    @GetMapping("/{id}")
    public String getEmployee(@PathVariable String id) {
        return "GET employee: " + id;
    }

    @PostMapping
    public String createEmployee() {
        return "CREATE new employee";
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
