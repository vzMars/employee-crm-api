package dev.marcosgonzalez.employeecrmapi.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "employees")
public class Employee {

    @Id
    private String id;

    private String name;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String jobTitle;
    private Status status;
    private String userId;

    public Employee(String name, String address, String city, String state, String zipcode, String jobTitle, Status status, String userId) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.jobTitle = jobTitle;
        this.status = status;
        this.userId = userId;
    }

    public String getId() {
        return this.id;
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

    public String getUserId() {
        return this.userId;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
