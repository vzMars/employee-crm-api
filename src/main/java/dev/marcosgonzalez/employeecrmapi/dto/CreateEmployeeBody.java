package dev.marcosgonzalez.employeecrmapi.dto;

import dev.marcosgonzalez.employeecrmapi.model.Status;
import jakarta.validation.constraints.NotBlank;

public class CreateEmployeeBody {

    @NotBlank(message = "Please complete all required fields.")
    private String name;

    @NotBlank(message = "Please complete all required fields.")
    private String address;

    @NotBlank(message = "Please complete all required fields.")
    private String city;

    @NotBlank(message = "Please complete all required fields.")
    private String state;

    @NotBlank(message = "Please complete all required fields.")
    private String zipcode;

    @NotBlank(message = "Please complete all required fields.")
    private String jobTitle;

    private Status status;

    public CreateEmployeeBody(String name, String address, String city, String state, String zipcode, String jobTitle, Status status) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.jobTitle = jobTitle;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public String getJobTitle() {
        return this.jobTitle;
    }

    public Status getStatus() {
        return this.status;
    }
}
