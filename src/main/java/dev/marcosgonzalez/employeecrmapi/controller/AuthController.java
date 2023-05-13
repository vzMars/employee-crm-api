package dev.marcosgonzalez.employeecrmapi.controller;

import dev.marcosgonzalez.employeecrmapi.dto.AuthResponse;
import dev.marcosgonzalez.employeecrmapi.dto.SignupBody;
import dev.marcosgonzalez.employeecrmapi.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping()
    public AuthResponse authStatus(Authentication authentication) {
        return authService.authStatus(authentication);
    }

    @PostMapping("/login")
    public String login() {
        return "POST login";
    }

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody @Valid SignupBody body) {
        return authService.signup(body);
    }
}
