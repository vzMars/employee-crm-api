package dev.marcosgonzalez.employeecrmapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping()
    public String authStatus() {
        return "GET authStatus";
    }

    @PostMapping("/login")
    public String login() {
        return "POST login";
    }

    @PostMapping("/signup")
    public String signup() {
        return "POST signup";
    }
}
