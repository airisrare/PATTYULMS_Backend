package com.example.pattyulms.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserModel {

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";

    @Id
    private Long userID;

    private String email;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 15)
    private String password;

    @NotBlank
    @Size(max = 15)
    private String firstname;
    @NotBlank
    @Size(max = 15)
    private String lastname;

    @DBRef
    private Set<UserRole> roles = new HashSet<>();

    // Default Constructor
    public UserModel() {

    }

    public UserModel(Long userID, String username, String email, String password, String firstname,
            String lastname) {
        super();
        this.userID = userID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserModel [userID=" + userID + ", email=" + email + ", username=" + username + ", password=" + password
                + ", firstname=" + firstname + ", lastname=" + lastname + "]";
    }

}
