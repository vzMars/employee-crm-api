package dev.marcosgonzalez.employeecrmapi.controller;

import dev.marcosgonzalez.employeecrmapi.dto.AuthResponse;
import dev.marcosgonzalez.employeecrmapi.dto.LoginBody;
import dev.marcosgonzalez.employeecrmapi.dto.SignupBody;
import dev.marcosgonzalez.employeecrmapi.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public AuthResponse login(@RequestBody @Valid LoginBody body, HttpServletRequest req, HttpServletResponse res) {
        return authService.login(body, req, res);
    }

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody @Valid SignupBody body) {
        return authService.signup(body);
    }
}
