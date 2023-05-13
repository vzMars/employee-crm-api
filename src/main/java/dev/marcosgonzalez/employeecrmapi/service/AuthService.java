package dev.marcosgonzalez.employeecrmapi.service;

import dev.marcosgonzalez.employeecrmapi.dto.AuthResponse;
import dev.marcosgonzalez.employeecrmapi.dto.LoginBody;
import dev.marcosgonzalez.employeecrmapi.dto.SignupBody;
import dev.marcosgonzalez.employeecrmapi.dto.UserInfo;
import dev.marcosgonzalez.employeecrmapi.exception.DuplicateUserException;
import dev.marcosgonzalez.employeecrmapi.model.User;
import dev.marcosgonzalez.employeecrmapi.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityContextRepository securityContextRepository;
    private final SecurityContextHolderStrategy securityContextHolderStrategy =
            SecurityContextHolder.getContextHolderStrategy();
    private final AuthenticationManager authenticationManager;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       SecurityContextRepository securityContextRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.securityContextRepository = securityContextRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse authStatus(Authentication authentication) {
        if (authentication == null) {
            return new AuthResponse(null);
        }

        User user = (User) authentication.getPrincipal();
        UserInfo userInfo = new UserInfo(user.getId(), user.getEmail(), user.getUsername());
        return new AuthResponse(userInfo);
    }

    public AuthResponse login(LoginBody body, HttpServletRequest req, HttpServletResponse res) {
        UsernamePasswordAuthenticationToken token =
                UsernamePasswordAuthenticationToken.unauthenticated(body.getUsername(), body.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);

        SecurityContext context = securityContextHolderStrategy.createEmptyContext();
        context.setAuthentication(authentication);
        securityContextHolderStrategy.setContext(context);
        securityContextRepository.saveContext(context, req, res);

        User user = (User) context.getAuthentication().getPrincipal();
        UserInfo userInfo = new UserInfo(user.getId(), user.getEmail(), user.getUsername());

        return new AuthResponse(userInfo);
    }

    public AuthResponse signup(SignupBody body, HttpServletRequest req, HttpServletResponse res) {
        if (userRepository.existsByUsernameOrEmail(body.getUsername(), body.getEmail())) {
            throw new DuplicateUserException("The username or email has already been taken.");
        }

        User user = new User(body.getUsername(), body.getEmail(), passwordEncoder.encode(body.getPassword()));

        userRepository.save(user);
        LoginBody loginBody = new LoginBody(body.getUsername(), body.getPassword());

        return login(loginBody, req, res);
    }
}
