package dev.marcosgonzalez.employeecrmapi.service;

import dev.marcosgonzalez.employeecrmapi.dto.CreateEmployeeBody;
import dev.marcosgonzalez.employeecrmapi.exception.EmployeeNotFoundException;
import dev.marcosgonzalez.employeecrmapi.exception.InvalidMongoIdException;
import dev.marcosgonzalez.employeecrmapi.model.Employee;
import dev.marcosgonzalez.employeecrmapi.model.Status;
import dev.marcosgonzalez.employeecrmapi.model.User;
import dev.marcosgonzalez.employeecrmapi.repository.EmployeeRepository;
import org.bson.types.ObjectId;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return employeeRepository.findEmployeesByUserId(user.getId());
    }

    public Employee getEmployee(String id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!ObjectId.isValid(id)) {
            throw new InvalidMongoIdException("Invalid MongoDB ID.");
        }

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty() || !employee.get().getUserId().equals(user.getId())) {
            throw new EmployeeNotFoundException("Employee not found.");
        }

        return employee.get();
    }

    public Employee createEmployee(CreateEmployeeBody body, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Employee employee = new Employee(body.getName(), body.getAddress(), body.getCity(), body.getState(),
                body.getZipcode(), body.getJobTitle(), body.getStatus(), user.getId());

        return employeeRepository.save(employee);
    }

    public Employee updateEmployeeStatus(String id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        if (!ObjectId.isValid(id)) {
            throw new InvalidMongoIdException("Invalid MongoDB ID.");
        }

        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty() || !employee.get().getUserId().equals(user.getId())) {
            throw new EmployeeNotFoundException("Employee not found.");
        }

        Status status = employee.get().getStatus();

        if (status.equals(Status.ACTIVE)) {
            employee.get().setStatus(Status.INACTIVE);
        } else {
            employee.get().setStatus(Status.ACTIVE);
        }

        return employeeRepository.save(employee.get());
    }
}
