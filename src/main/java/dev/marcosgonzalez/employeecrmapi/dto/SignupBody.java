package dev.marcosgonzalez.employeecrmapi.dto;

import dev.marcosgonzalez.employeecrmapi.annotations.Password;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class SignupBody {

    @NotBlank(message = "Please complete all required fields.")
    @Length(min = 2, max = 20, message = "The username must be between 2-20 characters.")
    @Pattern(regexp = "^[A-Za-z0-9]*$", message = "The username may only contain letters and numbers.")
    private String username;

    @NotBlank(message = "Please complete all required fields.")
    @Email(message = "The email must be a valid email address.")
    private String email;

    @NotBlank(message = "Please complete all required fields.")
    @Password
    private String password;

    public SignupBody(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEmail() {
        return this.email;
    }
}
