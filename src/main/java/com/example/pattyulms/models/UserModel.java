package com.example.pattyulms.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserModel 
{

    @Transient
    public static final String SEQUENCE_NAME = "user_sequence";


    @Id
    private Long userID;
    private String email;
    private String username;
    private String password;
    private int role;
    private String firstname;
    private String lastname;

    //Default Constructor
    public UserModel() {

    }

    public UserModel(Long userID, String email, String username, String password, int role, String firstname, String lastname)
    {
        super();
        this.userID = userID;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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

    @Override
    public String toString() {
        return "UserModel [userID=" + userID + ", email=" + email + ", username=" + username + ", password=" + password
                + ", role=" + role + ", firstname=" + firstname + ", lastname=" + lastname + "]";
    }
    
}
