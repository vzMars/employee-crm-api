package dev.marcosgonzalez.employeecrmapi.dto;

public class UserInfo {

    private String id;
    private String email;
    private String username;

    public UserInfo(String id, String email, String username) {
        this.id = id;
        this.email = email;
        this.username = username;
    }

    public String getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }
}
