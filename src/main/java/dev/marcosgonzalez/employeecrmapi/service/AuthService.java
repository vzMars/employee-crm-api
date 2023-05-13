package dev.marcosgonzalez.employeecrmapi.service;

import dev.marcosgonzalez.employeecrmapi.dto.AuthResponse;
import dev.marcosgonzalez.employeecrmapi.dto.SignupBody;
import dev.marcosgonzalez.employeecrmapi.dto.UserInfo;
import dev.marcosgonzalez.employeecrmapi.exception.DuplicateUserException;
import dev.marcosgonzalez.employeecrmapi.model.User;
import dev.marcosgonzalez.employeecrmapi.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse authStatus(Authentication authentication) {
        if (authentication == null) {
            return new AuthResponse(null);
        }

        User user = (User) authentication.getPrincipal();
        UserInfo userInfo = new UserInfo(user.getId(), user.getEmail(), user.getUsername());
        return new AuthResponse(userInfo);
    }

    public AuthResponse signup(SignupBody body) {
        if (userRepository.existsByUsernameOrEmail(body.getUsername(), body.getEmail())) {
            throw new DuplicateUserException("The username or email has already been taken.");
        }

        User user = new User(body.getUsername(), body.getEmail(), passwordEncoder.encode(body.getPassword()));

        User savedUser = userRepository.save(user);
        UserInfo userInfo = new UserInfo(savedUser.getId(), savedUser.getEmail(), savedUser.getUsername());

        return new AuthResponse(userInfo);
    }
}
