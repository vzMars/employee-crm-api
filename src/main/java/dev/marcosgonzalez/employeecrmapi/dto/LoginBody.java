package dev.marcosgonzalez.employeecrmapi.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginBody {

    @NotBlank(message = "Please complete all required fields.")
    private String username;

    @NotBlank(message = "Please complete all required fields.")
    private String password;

    public LoginBody(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
