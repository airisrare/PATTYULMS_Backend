package com.example.pattyulms.payload.response;

import java.util.List;
//This is the JWT response with the token, roles and credentials

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long userID;
    private String username;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, Long userID, String username, String email, List<String> roles) {
        this.token = accessToken;
        this.userID = userID;
        this.email = email;
        this.username = username;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public Long getId() {
        return userID;
    }

    public void setId(Long userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "JwtResponse [token=" + token + ", type=" + type + ", userID=" + userID + ", username=" + username
                + ", email=" + email + ", roles=" + roles + "]";
    }

}
