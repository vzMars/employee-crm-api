package dev.marcosgonzalez.employeecrmapi.service;

import dev.marcosgonzalez.employeecrmapi.dto.AuthResponse;
import dev.marcosgonzalez.employeecrmapi.dto.UserInfo;
import dev.marcosgonzalez.employeecrmapi.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public AuthResponse authStatus(Authentication authentication) {
        if (authentication == null) {
            return new AuthResponse(null);
        }

        User user = (User) authentication.getPrincipal();
        UserInfo userInfo = new UserInfo(user.getId(), user.getEmail(), user.getUsername());
        return new AuthResponse(userInfo);
    }
}
