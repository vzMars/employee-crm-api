package dev.marcosgonzalez.employeecrmapi.service;

import dev.marcosgonzalez.employeecrmapi.dto.CreateEmployeeBody;
import dev.marcosgonzalez.employeecrmapi.model.Employee;
import dev.marcosgonzalez.employeecrmapi.model.User;
import dev.marcosgonzalez.employeecrmapi.repository.EmployeeRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(CreateEmployeeBody body, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Employee employee = new Employee(body.getName(), body.getAddress(), body.getCity(), body.getState(),
                body.getZipcode(), body.getJobTitle(), body.getStatus(), user.getId());

        return employeeRepository.save(employee);
    }
}
